package com.feroov.frv.item;

import com.feroov.frv.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


public class ModTiers
{
    public static final Tier COPPER = TierSortingRegistry.registerTier(
            new ForgeTier(1, 160, 4.9f, 0.9f, 10, ModBlocks.Tags.NEEDS_COPPER_TOOL,
                    () -> Ingredient.of(Items.COPPER_INGOT)),
            new ResourceLocation("frv:needs_copper_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier TIN = TierSortingRegistry.registerTier(
            new ForgeTier(2, 210, 5.5f, 1.7f, 10, ModBlocks.Tags.NEEDS_TIN_TOOL,
                    () -> Ingredient.of(ModItems.TIN_INGOT.get())),
            new ResourceLocation("frv:needs_tin_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier QUARTZ = TierSortingRegistry.registerTier(
            new ForgeTier(2, 320, 6.5f, 2.0f, 10, ModBlocks.Tags.NEEDS_QUARTZ_TOOL,
                    () -> Ingredient.of(Items.QUARTZ)),
            new ResourceLocation("frv:needs_quartz_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier LEAD = TierSortingRegistry.registerTier(
            new ForgeTier(2, 350, 6.7f, 2.5f, 10, ModBlocks.Tags.NEEDS_LEAD_TOOL,
                    () -> Ingredient.of(ModItems.LEAD_INGOT.get())),
            new ResourceLocation("frv:needs_lead_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier SILVER = TierSortingRegistry.registerTier(
            new ForgeTier(2, 650, 7.4f, 2.8f, 10, ModBlocks.Tags.NEEDS_SILVER_TOOL,
                    () -> Ingredient.of(ModItems.SILVER_INGOT.get())),
            new ResourceLocation("frv:needs_silver_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier PLATINUM = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1320, 7.9f, 3.0f, 10, ModBlocks.Tags.NEEDS_PLATINUM_TOOL,
                    () -> Ingredient.of(ModItems.PLATINUM_INGOT.get())),
            new ResourceLocation("frv:needs_platinum_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier AMETHYST = TierSortingRegistry.registerTier(
            new ForgeTier(3, 1863, 8.6f, 3.8f, 10, ModBlocks.Tags.NEEDS_AMETHYST_TOOL,
                    () -> Ingredient.of(Items.AMETHYST_SHARD)),
            new ResourceLocation("frv:needs_amethyst_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier METEORITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2863, 12.5f, 6.3f, 15, ModBlocks.Tags.NEEDS_METEORITE_TOOL,
                    () -> Ingredient.of(ModItems.METEORITE_INGOT.get())),
            new ResourceLocation("frv:needs_meteorite_tool"),
            List.of(Tiers.NETHERITE), List.of());

    public static final Tier FUNGHONITE = TierSortingRegistry.registerTier(
            new ForgeTier(6, 3863, 14.5f, 9.5f, 15, ModBlocks.Tags.NEEDS_FUNGHONITE_TOOL,
                    () -> Ingredient.of(ModItems.METEORITE_INGOT.get())),
            new ResourceLocation("frv:needs_funghonite_tool"),
            List.of(ModTiers.METEORITE), List.of());

    public static final Tier HELLSTONE = TierSortingRegistry.registerTier(
            new ForgeTier(7, 5163, 16.5f, 11.5f, 25, ModBlocks.Tags.NEEDS_HELLSTONE_TOOL,
                    () -> Ingredient.of(ModItems.METEORITE_INGOT.get())),
            new ResourceLocation("frv:needs_hellstone_tool"),
            List.of(ModTiers.FUNGHONITE), List.of());

    public static final Tier ENDRIUM = TierSortingRegistry.registerTier(
            new ForgeTier(8, 7163, 21.5f, 13.5f, 25, ModBlocks.Tags.NEEDS_ENDRIUM_TOOL,
                    () -> Ingredient.of(ModItems.METEORITE_INGOT.get())),
            new ResourceLocation("frv:needs_endrium_tool"),
            List.of(ModTiers.HELLSTONE), List.of());

    public static final Tier VOID = TierSortingRegistry.registerTier(
            new ForgeTier(9, 12163, 24.5f, 17.5f, 25, ModBlocks.Tags.NEEDS_VOID_TOOL,
                    () -> Ingredient.of(ModItems.VOID_GEM.get())),
            new ResourceLocation("frv:needs_void_tool"),
            List.of(ModTiers.ENDRIUM), List.of());

    public static final Tier CORRUPT = TierSortingRegistry.registerTier(
            new ForgeTier(10, 15163, 30.5f, 25.5f, 25, ModBlocks.Tags.NEEDS_CORRUPT_TOOL,
                    () -> Ingredient.of(ModItems.CORRUPT_PIECE.get())),
            new ResourceLocation("frv:needs_corrupt_tool"),
            List.of(ModTiers.VOID), List.of());

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
