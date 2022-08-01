package com.feroov.frv.world.feature;

import com.feroov.frv.Frv;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures
{
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Frv.MOD_ID);


    /**
        Check for OrePlacements.java for reference
    */


    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.TIN_ORE.getHolder().get(),
                    commonOrePlacement(11,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-10), VerticalAnchor.absolute(70)))));

    public static final RegistryObject<PlacedFeature> LEAD_ORE_PLACED = PLACED_FEATURES.register("lead_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.LEAD_ORE.getHolder().get(),
                    commonOrePlacement(8,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(60)))));

    public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED = PLACED_FEATURES.register("silver_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.SILVER_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-54), VerticalAnchor.absolute(40)))));

    public static final RegistryObject<PlacedFeature> PLATINUM_ORE_PLACED = PLACED_FEATURES.register("platinum_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.PLATINUM_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(40)))));

    public static final RegistryObject<PlacedFeature> CORRUPT_ORE = PLACED_FEATURES.register("corrupt_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.MATRIX_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(-1), VerticalAnchor.absolute(12)))));




    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_)
    {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_)
    {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus){ PLACED_FEATURES.register(eventBus); }

}
