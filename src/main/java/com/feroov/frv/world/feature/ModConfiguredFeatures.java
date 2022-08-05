package com.feroov.frv.world.feature;

import com.feroov.frv.Frv;
import com.feroov.frv.block.ModBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ModConfiguredFeatures
{

    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES=
             DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Frv.MOD_ID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TIN_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_LEAD_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILVER_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_PLATINUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PLATINUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PLATINUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> FUNGHONITE_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.FUNGHONITE_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> CORRUPT_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.CORRUPT_ORE.get().defaultBlockState())));


//    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_OREHERE_ORES = Suppliers.memoize(() -> List.of(
//            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.YOUR_ORE_HERE.get().defaultBlockState())));
//
//    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_OREHERE_ORES = Suppliers.memoize(() -> List.of(
//            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.YOUR_ORE_HERE.get().defaultBlockState())));

    /**
     (OVERWORLD_....., 3) (EMERALD)
     (OVERWORLD_....., 6) (DIAMOND)
     (OVERWORLD_....., 8) (REDSTONE)
     (OVERWORLD_....., 9) (GOLD)
     (OVERWORLD_....., 9) (IRON)
     (OVERWORLD_....., 17) (COAL)
     */


    /************************************************ TIN ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE = CONFIGURED_FEATURES.register("tin_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(OVERWORLD_TIN_ORES.get()), 9)));
    /************************************************************************************************************************/

    /************************************************ LEAD ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEAD_ORE = CONFIGURED_FEATURES.register("lead_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(OVERWORLD_LEAD_ORES.get()), 8)));
    /************************************************************************************************************************/

    /************************************************ SILVER ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE = CONFIGURED_FEATURES.register("silver_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(OVERWORLD_SILVER_ORES.get()), 8)));
    /************************************************************************************************************************/

    /************************************************ PLATINUM ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> PLATINUM_ORE = CONFIGURED_FEATURES.register("platinum_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(OVERWORLD_PLATINUM_ORES.get()), 7)));
    /************************************************************************************************************************/

    /************************************************ FUNGHONITE ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> FUNGAL_ORE = CONFIGURED_FEATURES.register("funghonite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(FUNGHONITE_ORE.get()), 7)));
    /************************************************************************************************************************/

    /************************************************ CORRUPT ORE *****************************************************************/
    public static final RegistryObject<ConfiguredFeature<?, ?>> MATRIX_ORE = CONFIGURED_FEATURES.register("corrupt_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(CORRUPT_ORE.get()), 7)));
    /************************************************************************************************************************/

    public static void register(IEventBus eventBus)  { CONFIGURED_FEATURES.register(eventBus); }
}
