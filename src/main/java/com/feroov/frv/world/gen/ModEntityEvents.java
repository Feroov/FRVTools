package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntityEvents
{
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Frv.MOD_ID);

    /***************** Spawning Entities etc *********************************/

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(final BiomeLoadingEvent event)
    {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome == null)
            return;
        if (biome.getBiomeCategory() == Biome.BiomeCategory.SWAMP)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CROAKER.get(), 200, 1, 3));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.PLAINS)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 65, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.PLAINS)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 65, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.EXTREME_HILLS)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 80, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.EXTREME_HILLS)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 80, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.FOREST)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 200, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.FOREST)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 200, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.JUNGLE)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 200, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.JUNGLE)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 200, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.SAVANNA)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 40, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.SAVANNA)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 40, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.TAIGA)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 80, 1, 2));

        if (biome.getBiomeCategory() == Biome.BiomeCategory.TAIGA)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 80, 1, 2));
    }

    /***************************************************************************/

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
