package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID)
public class ModEntityEvents
{
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Frv.MOD_ID);

    /***************** Spawning Entities etc *********************************/
   /* @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent event)
    {
        if(event.getName() == null)
            return;
        MobSpawnSettingsBuilder spawns = event.getSpawns();
        if(event.getCategory().equals(Biome.BiomeCategory.SWAMP) &&
           event.getCategory().equals(Biome.BiomeCategory.BEACH) &&
                event.getCategory().equals(Biome.BiomeCategory.DESERT) &&
                event.getCategory().equals(Biome.BiomeCategory.EXTREME_HILLS) &&
                event.getCategory().equals(Biome.BiomeCategory.FOREST) &&
                event.getCategory().equals(Biome.BiomeCategory.MOUNTAIN) &&
                event.getCategory().equals(Biome.BiomeCategory.PLAINS) &&
                event.getCategory().equals(Biome.BiomeCategory.TAIGA))
        {
            spawns.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntityTypes.CROAKER.get(), 100,1,2));
        }
    }

    */



    /***************************************************************************/

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
