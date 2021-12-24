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

    /**
    public static final ConfiguredFeature<?, ?> TEST_CF = Feature.ORE.configured(new OreConfiguration
            (new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD), Blocks.SHROOMLIGHT.defaultBlockState(), 10,0.5f ));
    public static final PlacedFeature TEST_PF = TEST_CF.placed(CountPlacement.of(60), InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(10), VerticalAnchor.belowTop(80)), BiomeFilter.biome());**/



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

        //OVERWORLD_ORES.add(Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "glowstone_ore"), OreGen.TEST_PF));
    }

    public static void registerConfigured()
    {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;

        //Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "glowstone_ore"), OreGen.TEST_CF);
    }
}