package com.feroov.frv.block;

import com.feroov.frv.Frv;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (3.6f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (4.2f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    /******************************************************************************************/


    /********************************** Deepslate Variant *************************************/
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (4.5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (4.8f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (5.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (5.7f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    /******************************************************************************************/


    /********************************** Other blocks *************************************/
    public static final RegistryObject<Block> MATRIX = registerBlock("matrix",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noLootTable().lightLevel((light) -> {return 15;}))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40));
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> METEORITE = registerBlock("meteorite",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(30.0F, 40.0F)
                    .requiresCorrectToolForDrops().lightLevel((light) -> {return 15;}))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.setSecondsOnFire(5);
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });
    /******************************************************************************************/





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
