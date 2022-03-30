package com.feroov.frv.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures
{
    /**
        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80) Min height for ore to spawn (worldMinHeight + height)  (min height -64)
        VerticalAnchor.belowTop(175)) Max height for it to spawn (worldMaxHeight - height) (max height 319)
    */


    /************************************************* Tin Ore ***********************************************************/
    public static final Holder<PlacedFeature> TIN_ORE_PLACED = PlacementUtils.m_206509_("tin_ore_placed",
            ModConfiguredFeatures.TIN_ORE, ModOrePlacement.commonOrePlacement(9, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(255))));

    public static final Holder<PlacedFeature> DEEPSLATE_TIN_ORE_PLACED = PlacementUtils.m_206509_("deepslate_tin_ore_placed",
            ModConfiguredFeatures.DEEPSLATE_TIN_ORE, ModOrePlacement.commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330))));
    /************************************************************************************************************************/

    /************************************************* Lead Ore ***********************************************************/
    public static final Holder<PlacedFeature> LEAD_ORE_PLACED = PlacementUtils.m_206509_("lead_ore_placed",
            ModConfiguredFeatures.LEAD_ORE, ModOrePlacement.commonOrePlacement(8, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(281))));

    public static final Holder<PlacedFeature> DEEPSLATE_LEAD_ORE_PLACED = PlacementUtils.m_206509_("deepslate_lead_ore_placed",
            ModConfiguredFeatures.DEEPSLATE_LEAD_ORE, ModOrePlacement.commonOrePlacement(6, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330))));
    /************************************************************************************************************************/

    /************************************************* Silver Ore ***********************************************************/
    public static final Holder<PlacedFeature> SILVER_ORE_PLACED = PlacementUtils.m_206509_("silver_ore_placed",
            ModConfiguredFeatures.SILVER_ORE, ModOrePlacement.commonOrePlacement(6, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(287))));

    public static final Holder<PlacedFeature> DEEPSLATE_SILVER_ORE_PLACED = PlacementUtils.m_206509_("deepslate_silver_ore_placed",
            ModConfiguredFeatures.DEEPSLATE_SILVER_ORE, ModOrePlacement.commonOrePlacement(4, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330))));
    /************************************************************************************************************************/

    /************************************************* PLatinum Ore ***********************************************************/
    public static final Holder<PlacedFeature> PLATINUM_ORE_PLACED = PlacementUtils.m_206509_("platinum_ore_placed",
            ModConfiguredFeatures.PLATINUM_ORE, ModOrePlacement.commonOrePlacement(4, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(299))));

    public static final Holder<PlacedFeature> DEEPSLATE_PLATINUM_ORE_PLACED = PlacementUtils.m_206509_("deepslate_platinum_ore_placed",
            ModConfiguredFeatures.DEEPSLATE_PLATINUM_ORE, ModOrePlacement.commonOrePlacement(2, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330))));
    /************************************************************************************************************************/
}
