package com.feroov.frv.world.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Optional;

public class PirateShip extends StructureFeature<JigsawConfiguration>
{
    public PirateShip()
    {
        super(JigsawConfiguration.CODEC, PirateShip::createPiecesGenerator, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() { return GenerationStep.Decoration.SURFACE_STRUCTURES; }


    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        ChunkPos chunkpos = context.chunkPos();


        return !context.chunkGenerator().hasFeatureChunkInRange(BuiltinStructureSets.OCEAN_MONUMENTS, context.seed(), chunkpos.x, chunkpos.z, 10);
    }

    public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {

        if (!PirateShip.isFeatureChunk(context)) { return Optional.empty(); }

        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);

        int topLandY = context.chunkGenerator().getFirstFreeHeight(blockpos.getX(), blockpos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
        blockpos = blockpos.above(topLandY + -1);

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(context, PoolElementStructurePiece::new, blockpos, false, false);

        if(structurePiecesGenerator.isPresent()) { LogManager.getLogger().log(Level.DEBUG, "Pirate ship at {}", blockpos); }
        return structurePiecesGenerator;
    }
}
