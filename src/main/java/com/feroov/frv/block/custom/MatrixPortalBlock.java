package com.feroov.frv.block.custom;


import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.world.FRVTeleporter;
import com.feroov.frv.world.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MatrixPortalBlock extends Block {

    public MatrixPortalBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            if (!pPlayer.isCrouching()) {
                MinecraftServer server = pLevel.getServer();

                if (server != null) {
                    if (pLevel.dimension() == ModDimensions.KJDIM_KEY) {
                        ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
                        if (overWorld != null) {
                            pPlayer.changeDimension(overWorld, new FRVTeleporter(pPos, false));
                        }
                    } else {
                        ServerLevel kjDim = server.getLevel(ModDimensions.KJDIM_KEY);
                        if (kjDim != null) {
                            pPlayer.changeDimension(kjDim, new FRVTeleporter(pPos, true));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }


    public void animateTick(BlockState blockstate, Level level, BlockPos blockPos, RandomSource randomSource)
    {
        if (randomSource.nextInt(100) == 0) {
            double d0 = (double)blockPos.getX() + randomSource.nextDouble();
            double d1 = (double)blockPos.getY() + 1.0D;
            double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
            level.addParticle(ParticleTypes.TOTEM_OF_UNDYING, d0, d1, d2, 0.0D, 1.0D, 0.0D);
            level.addParticle(ParticleTypes.TOTEM_OF_UNDYING, d0, d1, d2, 1.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.TOTEM_OF_UNDYING, d0, d1, d2, 0.0D, 0.0D, 1.0D);
            level.playLocalSound(d0, d1, d2, ModSoundEvents.CORRUPT_AMBIENT.get(), SoundSource.BLOCKS, 0.2F +
                    randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
        }

        if (randomSource.nextInt(200) == 0) {
            level.playLocalSound((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(),
                    ModSoundEvents.CORRUPT_AMBIENT.get(), SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() *
                            0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
        }
    }
}