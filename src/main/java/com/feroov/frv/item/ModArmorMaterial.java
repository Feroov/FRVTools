package com.feroov.frv.item;

import com.feroov.frv.Frv;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;


import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial
{
    COPPER("copper", 15, new int[]{1, 3, 4, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F, () -> {return Ingredient.of(Items.COPPER_INGOT);}),

    TIN("tin", 15, new int[]{2, 4, 4, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F, () -> {return Ingredient.of(ModItems.TIN_INGOT.get());}),

    QUARTZ("quartz", 19, new int[]{2, 5, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_DIAMOND,
            0.0F, 0.0F, () -> {return Ingredient.of(Items.QUARTZ);}),

    LEAD("lead", 23, new int[]{3, 5, 6, 4}, 14, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F, () -> {return Ingredient.of(ModItems.LEAD_INGOT.get());}),

    SILVER("silver", 26, new int[]{3, 6, 7, 2}, 15, SoundEvents.ARMOR_EQUIP_IRON,
            0.5F, 0.0F, () -> {return Ingredient.of(ModItems.SILVER_INGOT.get());}),

    PLATINUM("platinum", 30, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_IRON,
            1.35F, 0.0F, () -> {return Ingredient.of(ModItems.PLATINUM_INGOT.get());}),

    AMETHYST("amethyst", 36, new int[]{3, 6, 8, 3}, 18, SoundEvents.AMETHYST_CLUSTER_HIT,
            1.8F, 0.1F, () -> {return Ingredient.of(Items.AMETHYST_SHARD);}),

    METEORITE("meteorite", 42, new int[]{3, 6, 8, 3}, 18, SoundEvents.ANCIENT_DEBRIS_BREAK,
            5.5F, 1.0F, () -> {return Ingredient.of(ModItems.METEORITE_INGOT.get());}),

    FUNGHONITE("funghonite", 49, new int[]{3, 6, 8, 3}, 21, SoundEvents.SCULK_BLOCK_BREAK,
        6.5F, 1.2F, () -> {return Ingredient.of(ModItems.METEORITE_INGOT.get());}),

    HELLSTONE("hellstone", 57, new int[]{3, 6, 8, 3}, 31, SoundEvents.ANCIENT_DEBRIS_BREAK,
        9.5F, 2.2F, () -> {return Ingredient.of(ModItems.METEORITE_INGOT.get());});

    /**
     *    LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.LEATHER);
     *    }),
     *    CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.IRON_INGOT);
     *    }),
     *    IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.IRON_INGOT);
     *    }),
     *    GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.GOLD_INGOT);
     *    }),
     *    DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.DIAMOND);
     *    }),
     *    TURTLE("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> {
     *       return Ingredient.of(Items.SCUTE);
     *    }),
     *    NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
     *       return Ingredient.of(Items.NETHERITE_INGOT);
     *    });
     */

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterial(String p_40474_, int p_40475_, int[] p_40476_, int p_40477_,
                             SoundEvent p_40478_, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_)
    {
        this.name = p_40474_;
        this.durabilityMultiplier = p_40475_;
        this.slotProtections = p_40476_;
        this.enchantmentValue = p_40477_;
        this.sound = p_40478_;
        this.toughness = p_40479_;
        this.knockbackResistance = p_40480_;
        this.repairIngredient = new LazyLoadedValue<>(p_40481_);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot)
    {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot)
    {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound()
    {
        return this.sound;
    }

    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    public String getName()
    {
        return Frv.MOD_ID + ":" + this.name;
    }

    public float getToughness()
    {
        return this.toughness;
    }

    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}