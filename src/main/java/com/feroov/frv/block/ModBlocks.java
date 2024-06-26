package com.feroov.frv.block;

import com.feroov.frv.Frv;
import com.feroov.frv.block.custom.Cloud;
import com.feroov.frv.block.custom.portals.MatrixPortalBlock;
import com.feroov.frv.block.custom.VigorousCraftingTable;
import com.feroov.frv.block.custom.portals.VoidPortalBlock;
import com.feroov.frv.block.custom.tnt.*;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Frv.MOD_ID);

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

    public static final RegistryObject<Block> METEORITE = registerBlock("meteorite",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(30.0F, 40.0F)
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

    public static final RegistryObject<Block> FUNGHONITE_ORE = registerBlock("funghonite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(45.0F, 50.0F)
                    .requiresCorrectToolForDrops().lightLevel((light) -> {return 2;}))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 140));
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> HELLSTONE = registerBlock("hellstone",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(75.0F, 70.0F)
                    .requiresCorrectToolForDrops().lightLevel((light) -> {return 15;}))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 140));
                            entity.setSecondsOnFire(15);
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> ENDRIUM = registerBlock("endrium",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(95.0F, 75.0F)
                    .requiresCorrectToolForDrops()
                    .sound(new
                            ForgeSoundType(1f,1f, () -> SoundEvents.ENDERMAN_TELEPORT,
                            () -> SoundEvents.ENDERMITE_AMBIENT, //step
                            () -> SoundEvents.ENDERMAN_TELEPORT, //place
                            () -> SoundEvents.DEEPSLATE_HIT, //hit
                            () -> SoundEvents.ENDERMAN_TELEPORT)))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 140));
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> VOID_FABRIC = registerBlock("void_fabric",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2.5F)
                    .sound(new
                            ForgeSoundType(1f,1f, () -> SoundEvents.WOOL_BREAK,
                            () -> SoundEvents.WOOL_STEP, //step
                            () -> SoundEvents.WOOL_PLACE, //place
                            () -> SoundEvents.WOOL_HIT, //hit
                            () -> SoundEvents.WOOL_FALL))));

    public static final RegistryObject<Block> VOID_ORE = registerBlock("void_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(125.0F, 90.0F)
                    .requiresCorrectToolForDrops()
                    .sound(new
                            ForgeSoundType(1f,1f, () -> SoundEvents.ENDERMAN_TELEPORT,
                            () -> SoundEvents.WOOL_STEP, //step
                            () -> SoundEvents.WOOL_PLACE, //place
                            () -> SoundEvents.WOOL_HIT, //hit
                            () -> SoundEvents.WOOL_FALL)))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 500));
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> VOID_GATEWAY = registerBlock("void_gateway",
            () -> new VoidPortalBlock(BlockBehaviour.Properties.of(Material.STONE).strength(15.0F, 15.0F)
                    .sound(SoundType.STONE).lightLevel((light) -> {return 15;})));

    public static final RegistryObject<Block> CORRUPT_ORE = registerBlock("corrupt_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength
                    (60.0f, 70.0F).requiresCorrectToolForDrops().sound(new
                    ForgeSoundType(1f,1f, () -> ModSoundEvents.GLITCH.get(),
                    () -> ModSoundEvents.SILENT.get(), //step
                    () -> ModSoundEvents.CORRUPT_ORE_PLACE.get(), //place
                    () -> ModSoundEvents.SILENT.get(), //hit
                    () -> ModSoundEvents.SILENT.get()) //fall
            ).lightLevel((light) -> {return 15;})));
    /******************************************************************************************/


    /********************************** Other blocks *************************************/
    public static final RegistryObject<Block> MATRIX = registerBlock("matrix",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(20.0F, 70.0F).requiresCorrectToolForDrops().lightLevel((light) -> {return 15;}))
            {
                @Override
                public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
                    if(!pLevel.isClientSide())
                    {
                        if(pEntity instanceof LivingEntity)
                        {
                            LivingEntity entity = ((LivingEntity) pEntity);
                            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40));
                        }
                    }

                    super.stepOn(pLevel, pPos, pState, pEntity);
                }
            });

    public static final RegistryObject<Block> MATRIX_PORTAL = registerBlock("matrix_portal",
            () -> new MatrixPortalBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(new
                    ForgeSoundType(1f,1f,
                    () -> ModSoundEvents.CORRUPT_DEATH.get(),
                    () -> ModSoundEvents.SILENT.get(), //step
                    () -> ModSoundEvents.CORRUPT_FIRE.get(), //place
                    () -> ModSoundEvents.SILENT.get(), //hit
                    () -> ModSoundEvents.SILENT.get()) //fall
            ).strength(15.0F, 15.0F).lightLevel((light) -> {return 15;}))
    );
    public static final RegistryObject<Block> CORRUPTED_BLOCK = registerBlock("corrupted_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(0.2F)
                    .sound(new
                            ForgeSoundType(1f,1f,
                            () -> ModSoundEvents.GLITCH.get(),
                            () -> ModSoundEvents.CORRUPT_STEP.get(), //step
                            () -> ModSoundEvents.CORRUPT_FIRE.get(), //place
                            () -> ModSoundEvents.SILENT.get(), //hit
                            () -> ModSoundEvents.CORRUPT_HURT.get())).lightLevel((light) -> {return 9;})));
    /******************************************************************************************/

    public static final RegistryObject<Block> VIGOROUS_CRAFTING_TABLE = registerBlock("vigorous_crafting_table",
            () -> new VigorousCraftingTable((BlockBehaviour.Properties.of(Material.STONE).explosionResistance(30).strength(4.0F)
                    .sound(SoundType.STONE).noOcclusion())));

    public static final RegistryObject<Block> METEORITE_TNT = registerBlock("meteorite_tnt",
            () -> new MeteoriteTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(SoundType.STONE))));

    public static final RegistryObject<Block> FUNGHONITE_TNT = registerBlock("funghonite_tnt",
            () -> new FunghoniteTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(SoundType.SCULK))));

    public static final RegistryObject<Block> HELLSTONE_TNT = registerBlock("hellstone_tnt",
            () -> new HellstoneTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(SoundType.ANCIENT_DEBRIS))));

    public static final RegistryObject<Block> ENDRIUM_TNT = registerBlock("endrium_tnt",
            () -> new EndriumTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(new
                    ForgeSoundType(1f,1f,
                    () -> SoundEvents.ENDERMAN_DEATH,
                    () -> SoundEvents.ENDERMAN_TELEPORT, //step
                    () -> SoundEvents.ENDERMAN_TELEPORT, //place
                    () -> SoundEvents.ENDERMAN_TELEPORT, //hit
                    () -> SoundEvents.ENDERMAN_DEATH)).strength(1.0f))));

    public static final RegistryObject<Block> VOID_TNT = registerBlock("dvoid_tnt",
            () -> new VoidTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(SoundType.WOOL).strength(1.5f)))
            {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> components, TooltipFlag tooltipFlag)
                {
                    super.appendHoverText(stack, blockGetter, components, tooltipFlag);
                    components.add(Component.translatable("Pulsating with power, flash warning!"));
                }
            });

    public static final RegistryObject<Block> CORRUPT_TNT = registerBlock("corrupt_tnt",
            () -> new CorruptTNT((BlockBehaviour.Properties.of(Material.EXPLOSIVE).sound(new
                    ForgeSoundType(1f,1f,
                    () -> ModSoundEvents.GLITCH.get(),
                    () -> ModSoundEvents.CORRUPT_STEP.get(), //step
                    () -> ModSoundEvents.CORRUPT_FIRE.get(), //place
                    () -> ModSoundEvents.SILENT.get(), //hit
                    () -> ModSoundEvents.CORRUPT_HURT.get())).strength(1.0f)))
            {
                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> components, TooltipFlag tooltipFlag)
                {
                    super.appendHoverText(stack, blockGetter, components, tooltipFlag);
                    components.add(Component.translatable("Pulsating with power, flash warning!"));
                }
            });


    public static final RegistryObject<Block> CLOUD = registerBlock("cloud",
            () -> new Cloud(BlockBehaviour.Properties.of(Material.POWDER_SNOW).strength(0.12f).sound(new
                    ForgeSoundType(1f,1f,
                    () -> ModSoundEvents.SILENT.get(),
                    () -> ModSoundEvents.SILENT.get(), //step
                    () -> ModSoundEvents.SILENT.get(), //place
                    () -> ModSoundEvents.SILENT.get(), //hit
                    () -> ModSoundEvents.SILENT.get())).noCollission()));




    public static class Tags
    {
        public static final TagKey<Block> NEEDS_COPPER_TOOL = create("needs_copper_tool");
        public static final TagKey<Block> NEEDS_TIN_TOOL = create("needs_tin_tool");
        public static final TagKey<Block> NEEDS_QUARTZ_TOOL = create("needs_quartz_tool");
        public static final TagKey<Block> NEEDS_LEAD_TOOL = create("needs_lead_tool");
        public static final TagKey<Block> NEEDS_SILVER_TOOL = create("needs_silver_tool");
        public static final TagKey<Block> NEEDS_PLATINUM_TOOL = create("needs_platinum_tool");
        public static final TagKey<Block> NEEDS_AMETHYST_TOOL = create("needs_amethyst_tool");
        public static final TagKey<Block> NEEDS_METEORITE_TOOL = create("needs_meteorite_tool");
        public static final TagKey<Block> NEEDS_FUNGHONITE_TOOL = create("needs_funghonite_tool");
        public static final TagKey<Block> NEEDS_HELLSTONE_TOOL = create("needs_hellstone_tool");
        public static final TagKey<Block> NEEDS_ENDRIUM_TOOL = create("needs_endrium_tool");
        public static final TagKey<Block> NEEDS_VOID_TOOL = create("needs_void_tool");
        public static final TagKey<Block> NEEDS_CORRUPT_TOOL = create("needs_corrupt_tool");
        public static final TagKey<Block> NEEDS_THE_TOOL = create("needs_the_tool");

        private static TagKey<Block> create(String location)
        {
            return BlockTags.create(new ResourceLocation(Frv.MOD_ID, location));
        }
    }

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
