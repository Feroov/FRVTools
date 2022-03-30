package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Frv.MOD_ID)
public class ModOreGeneration
{

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {
        ModOreGeneration.generateOres(event);
    }

    public static void generateOres(final BiomeLoadingEvent event)
    {
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.TIN_ORE_PLACED);
        base.add(ModPlacedFeatures.DEEPSLATE_TIN_ORE_PLACED);

        base.add(ModPlacedFeatures.LEAD_ORE_PLACED);
        base.add(ModPlacedFeatures.DEEPSLATE_LEAD_ORE_PLACED);

        base.add(ModPlacedFeatures.SILVER_ORE_PLACED);
        base.add(ModPlacedFeatures.DEEPSLATE_SILVER_ORE_PLACED);

        base.add(ModPlacedFeatures.PLATINUM_ORE_PLACED);
        base.add(ModPlacedFeatures.DEEPSLATE_PLATINUM_ORE_PLACED);
    }
}
