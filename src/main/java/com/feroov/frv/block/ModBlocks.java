package com.feroov.frv.block;

import com.feroov.frv.Frv;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Frv.MOD_ID);

    /************************************* Stone Variant *************************************/
    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (3.3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    /******************************************************************************************/


    /********************************** Deepslate Variant *************************************/
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (4.5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (4.8f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    /******************************************************************************************/


    public static final RegistryObject<Block> MATRIX = registerBlock("matrix",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().lightLevel((light) -> {return 15;})));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
