package com.feroov.frv.item.custom.items;

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

public class CoalDetectorPrime extends Item
{
    public CoalDetectorPrime(Properties pProperties)
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
                player.sendSystemMessage(Component.translatable("item.frv.coal_detector.no_valuables"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow)
    {

        player.sendSystemMessage(Component.literal("\u00A7a\nDetected Coal Ore\n" + "\nat" + "(X:"
                + blockPos.getX() + " , " + "Z:"
                + blockPos.getZ() + ")\n"));
    }

    private boolean isValuableBlock(Block block)
    {
        return block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE;
    }
}