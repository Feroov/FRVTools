package com.feroov.frv.item.custom.items;

import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetheriteDetector extends Item
{
    public NetheriteDetector(Properties pProperties)
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
                    spawnFoundParticles(pContext, positionClicked);
                    break;
                }
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked)
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
        return block == Blocks.ANCIENT_DEBRIS;
    }
}