package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntityEvents
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Frv.MOD_ID);

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) { ModEntityEvents.onEntitySpawn(event); }

    public static void onEntitySpawn(final BiomeLoadingEvent event)
    {
        /** Croacker **/
        addEntityToSpecificBiomes(event, ModEntityTypes.CROAKER.get(), 200, 1, 3, Biomes.SWAMP);

        /** Hunters **/
        addEntityToSpecificBiomes(event, ModEntityTypes.HUNTER.get(), 65, 1, 2, Biomes.PLAINS);
        addEntityToSpecificBiomes(event, ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2, Biomes.PLAINS);
        addEntityToSpecificBiomes(event, ModEntityTypes.HUNTER.get(), 65, 1, 2, Biomes.FOREST);
        addEntityToSpecificBiomes(event, ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2, Biomes.FOREST);
        addEntityToSpecificBiomes(event, ModEntityTypes.HUNTER.get(), 65, 1, 2, Biomes.JUNGLE);
        addEntityToSpecificBiomes(event, ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2, Biomes.JUNGLE);
        addEntityToSpecificBiomes(event, ModEntityTypes.HUNTER.get(), 65, 1, 2, Biomes.SAVANNA);
        addEntityToSpecificBiomes(event, ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2, Biomes.SAVANNA);
        addEntityToSpecificBiomes(event, ModEntityTypes.HUNTER.get(), 65, 1, 2, Biomes.TAIGA);
        addEntityToSpecificBiomes(event, ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2, Biomes.TAIGA);

        /** Mimic **/
        addEntityToSpecificBiomes(event, ModEntityTypes.MIMIC.get(), 20, 1, 1, Biomes.PLAINS);
        addEntityToSpecificBiomes(event, ModEntityTypes.MIMIC.get(), 20, 1, 1, Biomes.DRIPSTONE_CAVES);
        addEntityToSpecificBiomes(event, ModEntityTypes.MIMIC.get(), 20, 1, 1, Biomes.LUSH_CAVES);
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes)
    {
        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) { addEntityToAllBiomes(event, type, weight, minCount, maxCount); }
    }

    private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                      int weight, int minCount, int maxCount)
    {
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER))
        {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
        base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
    }

    public static void register(IEventBus eventBus) { ENTITIES.register(eventBus); }
}
