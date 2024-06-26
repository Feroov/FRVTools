package com.feroov.frv.block.custom.portals;


import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.world.CorruptionTeleporter;
import com.feroov.frv.world.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MatrixPortalBlock extends Block
{

    public MatrixPortalBlock(Properties p_49795_) { super(p_49795_); }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        if (!pLevel.isClientSide())
        {
            if (!pPlayer.isCrouching())
            {
                MinecraftServer server = pLevel.getServer();

                if (server != null)
                {
                    if (pLevel.dimension() == ModDimensions.CORRUPT_KEY)
                    {
                        ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
                        if (overWorld != null)
                        {
                            pPlayer.changeDimension(overWorld, new CorruptionTeleporter(pPos, false));
                        }
                    }
                    else
                    {
                        ServerLevel kjDim = server.getLevel(ModDimensions.CORRUPT_KEY);
                        if (kjDim != null)
                        {
                            pPlayer.changeDimension(kjDim, new CorruptionTeleporter(pPos, true));
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
        if (randomSource.nextInt(10) == 0)
        {
            double d0 = (double)blockPos.getX() + randomSource.nextDouble();
            double d1 = (double)blockPos.getY() + 1.0D;
            double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
            level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), d0, d1, d2, 0.2D, 0.0D, 0.0D);
            level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), d0, d1, d2, 0.0D, 0.2D, 0.0D);
            level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), d0, d1, d2, 0.0D, 0.0D, 0.2D);
            level.playLocalSound(d0, d1, d2, ModSoundEvents.CORRUPT_AMBIENT.get(), SoundSource.BLOCKS, 0.2F +
                    randomSource.nextFloat() * 0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
        }

        if (randomSource.nextInt(200) == 0)
        {
            level.playLocalSound((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(),
                    ModSoundEvents.CORRUPT_AMBIENT.get(), SoundSource.BLOCKS, 0.2F + randomSource.nextFloat() *
                            0.2F, 0.9F + randomSource.nextFloat() * 0.15F, false);
        }
    }
}