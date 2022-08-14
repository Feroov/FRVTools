package com.feroov.frv.item.custom.items;

import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class AllOreDetector extends Item
{
    public AllOreDetector(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext)
    {
        if(pContext.getLevel().isClientSide())
        {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++)
            {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if(isValuableBlock(blockBelow))
                {
                    foundBlock = true;
                    pContext.getLevel().playSound(player, positionClicked, ModSoundEvents.DETECTOR_SOUND.get(),
                            SoundSource.BLOCKS, 1f,1f);
                    if(blockBelow == Blocks.COAL_ORE){ spawnCoal(pContext, positionClicked); }
                    if(blockBelow == Blocks.IRON_ORE){ spawnIron(pContext, positionClicked); }
                    if(blockBelow == Blocks.GOLD_ORE){ spawnGold(pContext, positionClicked); }
                    if(blockBelow == Blocks.EMERALD_ORE){ spawnEmerald(pContext, positionClicked); }
                    if(blockBelow == Blocks.DIAMOND_ORE){ spawnDiamond(pContext, positionClicked); }
                    if(blockBelow == Blocks.REDSTONE_ORE){ spawnRedstone(pContext, positionClicked); }
                    if(blockBelow == Blocks.LAPIS_ORE){ spawnLapis(pContext, positionClicked); }
                    if(blockBelow == Blocks.ANCIENT_DEBRIS){ spawnNetherite(pContext, positionClicked); }

                    break;
                }
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void spawnCoal(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.COAL_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnIron(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.IRON_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnGold(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.GOLD_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnRedstone(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.REDSTONE_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnLapis(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.LAPIS_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnEmerald(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.EMERALD_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnDiamond(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.DIAMOND_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private void spawnNetherite(UseOnContext pContext, BlockPos positionClicked)
    {
        for(int i = 0; i < 360; i++)
        {
            if(i % 20 == 0)
            {
                pContext.getLevel().addParticle(ModParticles.NETHERITE_PARTICLES.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }

    private boolean isValuableBlock(Block block)
    {
        return block == Blocks.COAL_ORE ||
               block == Blocks.IRON_ORE ||
                block == Blocks.GOLD_ORE ||
                block == Blocks.EMERALD_ORE ||
                block == Blocks.DIAMOND_ORE ||
                block == Blocks.LAPIS_ORE ||
                block == Blocks.REDSTONE_ORE ||
                block == Blocks.ANCIENT_DEBRIS;
    }
}