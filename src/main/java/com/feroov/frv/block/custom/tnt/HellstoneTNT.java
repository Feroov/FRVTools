package com.feroov.frv.block.custom.tnt;


import com.feroov.frv.entities.misc.PrimedHellstone;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class HellstoneTNT extends Block
{
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public HellstoneTNT(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.valueOf(false)));
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter)
    {
        explode(world, pos, igniter);
    }

    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b)
    {
        if (!blockState1.is(blockState.getBlock()))
        {
            if (level.hasNeighborSignal(blockPos))
            {
                onCaughtFire(blockState, level, blockPos, null, null);
                level.removeBlock(blockPos, false);
            }
        }
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean b)
    {
        if (level.hasNeighborSignal(blockPos))
        {
            onCaughtFire(blockState, level, blockPos, null, null);
            level.removeBlock(blockPos, false);
        }
    }

    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player)
    {
        if (!level.isClientSide() && !player.isCreative() && blockState.getValue(UNSTABLE))
        {
            onCaughtFire(blockState, level, blockPos, null, null);
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion)
    {
        if (!level.isClientSide)
        {
            PrimedHellstone primedHellstone = new PrimedHellstone(level, (double)blockPos.getX() + 0.5D,
                    (double)blockPos.getY(), (double)blockPos.getZ()
                    + 0.5D, explosion.getSourceMob());
            int i = primedHellstone.getFuse();
            primedHellstone.setFuse((short)(level.random.nextInt(i / 4) + i / 8));
            level.addFreshEntity(primedHellstone);
        }
    }

    @Deprecated
    public static void explode(Level level, BlockPos blockPos)
    {
        explode(level, blockPos, (LivingEntity)null);
    }

    @Deprecated
    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity livingEntity)
    {
        if (!level.isClientSide)
        {
            PrimedHellstone primedHellstone = new PrimedHellstone(level, (double)blockPos.getX() + 0.5D,
                    (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, livingEntity);
            level.addFreshEntity(primedHellstone);
            level.playSound((Player)null, primedHellstone.getX(), primedHellstone.getY(), primedHellstone.getZ(),
                    SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(livingEntity, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
        ItemStack itemstack = player.getItemInHand(interactionHand);

        if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE))
        {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }
        else
        {
            onCaughtFire(blockState, level, blockPos, blockHitResult.getDirection(), player);
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 51);
            Item item = itemstack.getItem();
            if (!player.isCreative())
            {
                if (itemstack.is(Items.FLINT_AND_STEEL))
                {
                    itemstack.hurtAndBreak(1, player, (p_57425_) -> {
                        p_57425_.broadcastBreakEvent(interactionHand);
                    });
                }
                else
                {
                    itemstack.shrink(1);
                }
            }
            player.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    public void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile)
    {
        if (!level.isClientSide)
        {
            BlockPos blockpos = blockHitResult.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(level, blockpos))
            {
                onCaughtFire(blockState, level, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                level.removeBlock(blockpos, false);
            }
        }
    }

    public boolean dropFromExplosion(Explosion explosion)
    {
        return false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockBlockStateBuilder)
    {
        blockBlockStateBuilder.add(UNSTABLE);
    }
}
