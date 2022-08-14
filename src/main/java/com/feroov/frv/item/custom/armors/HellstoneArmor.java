package com.feroov.frv.item.custom.armors;

import com.feroov.frv.item.ModArmorMaterial;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class HellstoneArmor extends ArmorItem
{
    private static final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT_MAP = new ImmutableMap.Builder<ArmorMaterial,
            MobEffect>().put(ModArmorMaterial.HELLSTONE, MobEffects.DAMAGE_BOOST).build();

    public HellstoneArmor(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_)
    {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if(!world.isClientSide())
        {
            if (entity instanceof Player)
            {
                Player player = (Player) entity;

                if (hasFullSuitOfArmorOn(player))
                {
                    evaluateArmorEffects(player);
                }
            }
        }
    }

    private void evaluateArmorEffects(Player player)
    {
        for (Map.Entry<ArmorMaterial, MobEffect> entry : MATERIAL_TO_EFFECT_MAP.entrySet())
        {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player))
            {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 150));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 250));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 250));
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffect mapStatusEffect)
    {
        if(hasCorrectArmorOn(mapArmorMaterial, player))
        {
            player.addEffect(new MobEffectInstance(mapStatusEffect, 150));
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player)
    {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player)
    {
        for (ItemStack armorStack: player.getInventory().armor)
        {
            if(!(armorStack.getItem() instanceof ArmorItem))
            {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial()
                == material && leggings.getMaterial() == material && boots.getMaterial() == material;
    }

}