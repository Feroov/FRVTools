package com.feroov.frv.item;

import com.feroov.frv.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup
{
    public static final CreativeModeTab FRV_TAB = new CreativeModeTab("frvtoolsModTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.SILVER_PICKAXE.get());
        }
    };

    public static final CreativeModeTab FRV_TAB_MISC = new CreativeModeTab("frvtoolsMisc")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.ALL_DETECTOR_PRIME.get());
        }
    };

    public static final CreativeModeTab FRV_TAB_BLOCKS_ITEMS = new CreativeModeTab("frvtoolsBlocksItems")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModBlocks.MATRIX.get());
        }
    };

    public static final CreativeModeTab FRV_TAB_EGGS = new CreativeModeTab("frvtoolsEgg")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.MOB_PIC.get());
        }
    };
}
