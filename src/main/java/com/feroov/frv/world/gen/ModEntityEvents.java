package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntityEvents
{
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Frv.MOD_ID);

    /***************** Spawning Entities etc *********************************/
    @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent event)
    {
        if(event.getName() == null)
            return;
        MobSpawnSettingsBuilder spawns = event.getSpawns();
        if(event.getCategory().equals(Biome.BiomeCategory.SWAMP))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CROAKER.get(), 1000,1,2));
        }
        if(event.getCategory().equals(Biome.BiomeCategory.PLAINS))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }
        if(event.getCategory().equals(Biome.BiomeCategory.MOUNTAIN))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }

        if(event.getCategory().equals(Biome.BiomeCategory.SAVANNA))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }
        if(event.getCategory().equals(Biome.BiomeCategory.JUNGLE))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }
        if(event.getCategory().equals(Biome.BiomeCategory.TAIGA))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }
        if(event.getCategory().equals(Biome.BiomeCategory.THEEND))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 1000,1,3));
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 1000,1,2));
        }
    }

    /***************************************************************************/

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
