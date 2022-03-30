package com.feroov.frv.world.feature;

import com.feroov.frv.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TIN_ORE =
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_DEEPSLATE_TIN_ORE =
            List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_LEAD_ORE =
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_DEEPSLATE_LEAD_ORE =
            List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_SILVER_ORE =
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_DEEPSLATE_SILVER_ORE =
            List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_PLATINUM_ORE =
            List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PLATINUM_ORE.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_DEEPSLATE_PLATINUM_ORE =
            List.of(OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PLATINUM_ORE.get().defaultBlockState()));

    /**
     (OVERWORLD_....., 3) (EMERALD)
     (OVERWORLD_....., 6) (DIAMOND)
     (OVERWORLD_....., 8) (REDSTONE)
     (OVERWORLD_....., 9) (GOLD)
     (OVERWORLD_....., 9) (IRON)
     (OVERWORLD_....., 17) (COAL)
     */

    /************************************************ TIN ORE *****************************************************************/
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TIN_ORE = FeatureUtils.m_206488_("tin_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_TIN_ORE, 9));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPSLATE_TIN_ORE = FeatureUtils.m_206488_("deepslate_tin_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_TIN_ORE, 9));
    /************************************************************************************************************************/

    /************************************************ LEAD ORE *****************************************************************/
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> LEAD_ORE = FeatureUtils.m_206488_("lead_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_LEAD_ORE, 8));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPSLATE_LEAD_ORE = FeatureUtils.m_206488_("deepslate_lead_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_LEAD_ORE, 6));
    /************************************************************************************************************************/

    /************************************************ SILVER ORE *****************************************************************/
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SILVER_ORE = FeatureUtils.m_206488_("silver_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_SILVER_ORE, 6));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPSLATE_SILVER_ORE = FeatureUtils.m_206488_("deepslate_silver_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_SILVER_ORE, 4));
    /************************************************************************************************************************/

    /************************************************ PLATINUM ORE *****************************************************************/
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> PLATINUM_ORE = FeatureUtils.m_206488_("platinum_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_PLATINUM_ORE, 4));
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DEEPSLATE_PLATINUM_ORE = FeatureUtils.m_206488_("deepslate_platinum_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_DEEPSLATE_PLATINUM_ORE, 2));
    /************************************************************************************************************************/
}
