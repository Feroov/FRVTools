package com.feroov.frv.world.structure;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.world.FrvStructures;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Optional;

public class CampsiteStructure extends StructureFeature<JigsawConfiguration>
{
    public CampsiteStructure(Codec<JigsawConfiguration> codec)
    {
        super(codec, CampsiteStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }


    private static final Lazy<List<MobSpawnSettings.SpawnerData>> STRUCTURE_CREATURES = Lazy.of(() -> ImmutableList.of(
            new MobSpawnSettings.SpawnerData(ModEntityTypes.HUNTER.get(), 100, 1, 1),
            new MobSpawnSettings.SpawnerData(ModEntityTypes.FEMALE_HUNTER.get(), 100, 1, 1)
    ));

    // Hooked up in StructureTutorialMain. You can move this elsewhere or change it up.
    public static void setupStructureSpawns(final StructureSpawnListGatherEvent event) {
        if(event.getStructure() == FrvStructures.CAMP_SITE.get()) {
            event.addEntitySpawns(MobCategory.CREATURE, STRUCTURE_CREATURES.get());
        }
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        BlockPos blockPos = context.chunkPos().getWorldPosition();
        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(blockPos.getX(), blockPos.getZ(), context.heightAccessor());
        BlockState topBlock = columnOfBlocks.getBlock(landHeight);
        return topBlock.getFluidState().isEmpty();
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        if (!CampsiteStructure.isFeatureChunk(context)) {
            return Optional.empty();
        }
        JigsawConfiguration newConfig = new JigsawConfiguration(() -> context.registryAccess().ownedRegistryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                .get(new ResourceLocation(Frv.MOD_ID, "camp_site/start_pool")), 10);


        PieceGeneratorSupplier.Context<JigsawConfiguration> newContext = new PieceGeneratorSupplier.Context<>(
                context.chunkGenerator(),
                context.biomeSource(),
                context.seed(),
                context.chunkPos(),
                newConfig,
                context.heightAccessor(),
                context.validBiome(),
                context.structureManager(),
                context.registryAccess()
        );

        // changes structure position on Y level negativee below, higher above ground
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(newContext, PoolElementStructurePiece::new, blockpos, false, true);
        if(structurePiecesGenerator.isPresent()) {
            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            // This is returning the coordinates of the center starting piece.
            Frv.LOGGER.log(Level.DEBUG, "Camp at " + blockpos);
        }
        return structurePiecesGenerator;
    }
}
