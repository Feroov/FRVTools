package com.feroov.frv.item;

import com.feroov.frv.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModItemGroup
{
    public static final CreativeModeTab FRV_TAB = new CreativeModeTab("frvtoolsModTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Items.AMETHYST_SHARD);
        }
    };

    public static final CreativeModeTab FRV_TAB_MISC = new CreativeModeTab("frvtoolsMisc")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.DIAMOND_DETECTOR.get());
        }
    };

    public static final CreativeModeTab FRV_TAB_BLOCKS_ITEMS = new CreativeModeTab("frvtoolsBlocksItems")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModBlocks.TIN_ORE.get());
        }
    };

    public static final CreativeModeTab FRV_TAB_EGGS = new CreativeModeTab("frvtoolsEgg")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.CROAKER_PIC.get());
        }
    };

}
