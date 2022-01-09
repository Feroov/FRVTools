package com.feroov.frv;


import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.init.Keybindings;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.item.custom.RangedItems;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.util.packets.FrvPacketHandler;
import com.feroov.frv.world.FrvConfiguredStructures;
import com.feroov.frv.world.FrvStructures;
import com.feroov.frv.world.gen.ModEntityEvents;
import com.feroov.frv.world.gen.OreGen;
import com.feroov.frv.world.structure.CampsiteStructure;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.KeyMapping;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib3.GeckoLib;

import java.util.HashMap;
import java.util.Map;


@Mod(Frv.MOD_ID)
public class Frv
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "frv";

    public Frv()
    {
        GeckoLib.initialize();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FrvStructures.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        ModSoundEvents.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntityEvents.register(eventBus);
        ModEntityTypes.ENTITIES.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);
        IEventBus eventBus1 = MinecraftForge.EVENT_BUS;
        eventBus1.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        eventBus1.addListener(EventPriority.NORMAL, CampsiteStructure::setupStructureSpawns);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        Keybindings.RELOAD = new KeyMapping("key." + Frv.MOD_ID + ".reload", GLFW.GLFW_KEY_R, "key.categories." + Frv.MOD_ID);
        ClientRegistry.registerKeyBinding(Keybindings.RELOAD);

        SpawnPlacements.register(ModEntityTypes.CROAKER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.HUNTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.FEMALE_HUNTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);

        RangedItems.addRanged();
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        FrvPacketHandler.register();
        event.enqueueWork(() ->
        {
            OreGen.registerConfigured();
            OreGen.registerPlaced();
        });

        event.enqueueWork(() -> {
            FrvStructures.setupStructures();
            FrvConfiguredStructures.registerConfiguredStructures();
        });
    }


    public void addDimensionalSpacing(final WorldEvent.Load event)
    {
        if(event.getWorld() instanceof ServerLevel serverLevel)
        {
            ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
            if (chunkGenerator instanceof FlatLevelSource && serverLevel.dimension().equals(Level.OVERWORLD))
            {
                return;
            }
            StructureSettings worldStructureConfig = chunkGenerator.getSettings();

            HashMap<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> FrvStructureToMultiMap = new HashMap<>();
            /********************************* Structure biome spawning **********************************************************************************/
            ImmutableSet<ResourceKey<Biome>> campsite = ImmutableSet.<ResourceKey<Biome>>builder()
                    .add(Biomes.PLAINS).add(Biomes.FOREST).add(Biomes.JUNGLE).add(Biomes.SAVANNA).build();
            campsite.forEach(biomeKey -> associateBiomeToConfiguredStructure(FrvStructureToMultiMap, FrvConfiguredStructures.CONFIGURED_CAMP_SITE, biomeKey));

            ImmutableSet<ResourceKey<Biome>> corruption = ImmutableSet.<ResourceKey<Biome>>builder()
                    .add(Biomes.PLAINS).add(Biomes.DESERT).add(Biomes.END_BARRENS).add(Biomes.END_HIGHLANDS).add(Biomes.END_MIDLANDS).build();
            corruption.forEach(biomeKey -> associateBiomeToConfiguredStructure(FrvStructureToMultiMap, FrvConfiguredStructures.CONFIGURED_CORRUPTION, biomeKey));

            ImmutableSet<ResourceKey<Biome>> pirateship = ImmutableSet.<ResourceKey<Biome>>builder()
                    .add(Biomes.DEEP_OCEAN).add(Biomes.DEEP_COLD_OCEAN).add(Biomes.DEEP_LUKEWARM_OCEAN).build();
            pirateship.forEach(biomeKey -> associateBiomeToConfiguredStructure(FrvStructureToMultiMap, FrvConfiguredStructures.CONFIGURED_PIRATE_SHIP, biomeKey));
            /**************************************************************************************************************************************/


            ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>,
                    ResourceKey<Biome>>> tempStructureToMultiMap = ImmutableMap.builder();
            worldStructureConfig.configuredStructures.entrySet().stream().filter(entry ->
                    !FrvStructureToMultiMap.containsKey(entry.getKey())).forEach(tempStructureToMultiMap::put);


            FrvStructureToMultiMap.forEach((key, value) -> tempStructureToMultiMap.put(key, ImmutableMultimap.copyOf(value)));
            worldStructureConfig.configuredStructures = tempStructureToMultiMap.build();

            /********************************* Configured stuff  **********************************************************************************/
            Map<StructureFeature<?>, StructureFeatureConfiguration> campsiteMap = new HashMap<>(worldStructureConfig.structureConfig());
            campsiteMap.putIfAbsent(FrvStructures.CAMP_SITE.get(), StructureSettings.DEFAULTS.get(FrvStructures.CAMP_SITE.get()));
            worldStructureConfig.structureConfig = campsiteMap;

            Map<StructureFeature<?>, StructureFeatureConfiguration> corruptionMap = new HashMap<>(worldStructureConfig.structureConfig());
            corruptionMap.putIfAbsent(FrvStructures.CORRUPTION.get(), StructureSettings.DEFAULTS.get(FrvStructures.CAMP_SITE.get()));
            worldStructureConfig.structureConfig = corruptionMap;

            Map<StructureFeature<?>, StructureFeatureConfiguration> pirateshipMap = new HashMap<>(worldStructureConfig.structureConfig());
            pirateshipMap.putIfAbsent(FrvStructures.PIRATE_SHIP.get(), StructureSettings.DEFAULTS.get(FrvStructures.PIRATE_SHIP.get()));
            worldStructureConfig.structureConfig = pirateshipMap;
            /**************************************************************************************************************************************/
        }
    }

    private static void associateBiomeToConfiguredStructure(Map<StructureFeature<?>, HashMultimap<ConfiguredStructureFeature<?, ?>,
            ResourceKey<Biome>>> STStructureToMultiMap, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeRegistryKey)
    {
        STStructureToMultiMap.putIfAbsent(configuredStructureFeature.feature, HashMultimap.create());
        HashMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> configuredStructureToBiomeMultiMap =
                STStructureToMultiMap.get(configuredStructureFeature.feature);
        if(configuredStructureToBiomeMultiMap.containsValue(biomeRegistryKey))
        {
            Frv.LOGGER.error("",
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureFeature),
                    BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE.getId(configuredStructureToBiomeMultiMap.entries()
                            .stream().filter(e -> e.getValue() == biomeRegistryKey).findFirst().get().getKey()),
                    biomeRegistryKey
            );
        }
        else
        {
            configuredStructureToBiomeMultiMap.put(configuredStructureFeature, biomeRegistryKey);
        }
    }
}
