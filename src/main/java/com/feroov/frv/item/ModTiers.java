package com.feroov.frv.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers
{
    public static final ForgeTier COPPER = new ForgeTier(1,160,4.9f,1.0f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.QUARTZ));

    public static final ForgeTier TIN = new ForgeTier(2,210,5.5f,1.7f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.TIN_INGOT.get()));

    public static final ForgeTier QUARTZ = new ForgeTier(2,320,6.5f,2.0f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.QUARTZ));

    public static final ForgeTier LEAD = new ForgeTier(2,350,6.7f,2.5f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.LEAD_INGOT.get()));

    public static final ForgeTier SILVER = new ForgeTier(2,650,7.4f,2.8f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(ModItems.SILVER_INGOT.get()));

    public static final ForgeTier AMETHYST = new ForgeTier(3,1863,8.6f,3.8f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.AMETHYST_SHARD));

    public static final ForgeTier ADMIN = new ForgeTier(999,999999,999f,9999f,10,
            Tags.Blocks.NEEDS_GOLD_TOOL, () -> Ingredient.of(Items.AMETHYST_SHARD));

    /**
     *    WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
     *       return Ingredient.of(ItemTags.PLANKS);
     *    }),
     *    STONE(1, 131, 4.0F, 1.0F, 5, () -> {
     *       return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS);
     *    }),
     *    IRON(2, 250, 6.0F, 2.0F, 14, () -> {
     *       return Ingredient.of(Items.IRON_INGOT);
     *    }),
     *    DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
     *       return Ingredient.of(Items.DIAMOND);
     *    }),
     *    GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
     *       return Ingredient.of(Items.GOLD_INGOT);
     *    }),
     *    NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> {
     *       return Ingredient.of(Items.NETHERITE_INGOT);
     *    });
     */
}
