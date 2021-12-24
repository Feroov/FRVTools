package com.feroov.frv.item.custom.items;

import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetheriteDetectorPrime extends Item
{
    public NetheriteDetectorPrime(Properties pProperties)
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
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    pContext.getLevel().playSound(player, positionClicked, ModSoundEvents.DETECTOR_SOUND.get(),
                            SoundSource.BLOCKS, 1f,1f);
                    break;
                }
            }

            if(!foundBlock)
            {
                player.sendMessage(new TranslatableComponent("item.frv.netherite_detector.no_valuables"),
                        player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow)
    {
        player.sendMessage(new TextComponent("\u00A72\nDetected Ancient Debris at \u00A72" +
                "\u00A72(X: \u00A72" + blockPos.getX() + "\u00A72, \u00A72" +
                 "\u00A72Z: \u00A72" + blockPos.getZ() + "\u00A72)\n\u00A72"), player.getUUID());
    }

    private boolean isValuableBlock(Block block)
    {
        return block == Blocks.ANCIENT_DEBRIS;
    }
}