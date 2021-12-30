package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import com.feroov.frv.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class OreGen
{
    public static final List<PlacedFeature> OVERWORLD_ORES = new ArrayList<>();

    /*
    .defaultBlockState(), 10,0.5f));    // Size of vein
    CountPlacement.of(60) // Attempts per chunk
    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80) Min height for ore to spawn (worldMinHeight + height)  (min height -64)
    VerticalAnchor.belowTop(175)) Max height for it to spawn (worldMaxHeight - height) (max height 319)
     */
    /****************************************************** Tin Ore ******************************************************************************/
    // Normal
    public static final ConfiguredFeature<?, ?> TIN_CF = Feature.ORE.configured(new OreConfiguration
            (new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), ModBlocks.TIN_ORE.get().defaultBlockState(), 7,0.0f));
    public static final PlacedFeature TIN_PF = TIN_CF.placed(CountPlacement.of(9), InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(255)), BiomeFilter.biome());
    // Deepslate
    public static final ConfiguredFeature<?, ?> TIN_DEEP_CF = Feature.ORE.configured(new OreConfiguration
            (new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState(), 10,0.0f));
    public static final PlacedFeature TIN_DEEP_PF = TIN_DEEP_CF.placed(CountPlacement.of(5), InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330)), BiomeFilter.biome());
    /**********************************************************************************************************************************************/

    /****************************************************** Lead Ore ******************************************************************************/
    // Normal
    public static final ConfiguredFeature<?, ?> LEAD_CF = Feature.ORE.configured(new OreConfiguration
            (new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), ModBlocks.LEAD_ORE.get().defaultBlockState(), 6,0.0f));
    public static final PlacedFeature LEAD_PF = LEAD_CF.placed(CountPlacement.of(5), InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(80), VerticalAnchor.belowTop(275)), BiomeFilter.biome());
    // Deepslate
    public static final ConfiguredFeature<?, ?> LEAD_DEEP_CF = Feature.ORE.configured(new OreConfiguration
            (new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState(), 7,0.0f));
    public static final PlacedFeature LEAD_DEEP_PF = LEAD_DEEP_CF.placed(CountPlacement.of(3), InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(14), VerticalAnchor.belowTop(330)), BiomeFilter.biome());
    /**********************************************************************************************************************************************/


    @Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents
    {
        @SubscribeEvent
        public static void biomeLoading(BiomeLoadingEvent event)
        {
            final var features = event.getGeneration()
                    .getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

            OreGen.OVERWORLD_ORES.forEach(ore -> features.add(() -> ore));
        }
    }

    public static void registerPlaced()
    {
        Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;

        OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "tin_ore"), OreGen.TIN_PF));
        OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "deepslate_tin_ore"), OreGen.TIN_DEEP_PF));
        OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "lead_ore"), OreGen.LEAD_PF));
        OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "deepslate_lead_ore"), OreGen.LEAD_DEEP_PF));
    }

    public static void registerConfigured()
    {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "tin_ore"), OreGen.TIN_CF);
        Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "deepslate_tin_ore"), OreGen.TIN_DEEP_CF);
        Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "lead_ore"), OreGen.LEAD_CF);
        Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "deepslate_lead_ore"), OreGen.LEAD_DEEP_CF);
    }
}