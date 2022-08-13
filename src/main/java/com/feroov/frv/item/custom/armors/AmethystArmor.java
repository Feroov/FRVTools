package com.feroov.frv.item.custom.armors;

import com.feroov.frv.item.ModArmorMaterial;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;


import java.util.Map;

public class AmethystArmor extends GeoArmorItem implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<AmethystArmor>(this, "controller", 20, this::predicate));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }




    public AmethystArmor(ArmorMaterial material, EquipmentSlot slot, Properties settings)
    {
        super(material, slot, settings);
    }

    private static final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT_MAP = new ImmutableMap.Builder<ArmorMaterial,
            MobEffect>().put(ModArmorMaterial.AMETHYST, MobEffects.MOVEMENT_SPEED).build();


    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected)
    {
        if(!world.isClientSide()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;

                if (hasFullSuitOfArmorOn(player)) {
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

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffect mapStatusEffect)
    {
        if(hasCorrectArmorOn(mapArmorMaterial, player))
        {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 150,3));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 150));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 250, 3));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 250,1));
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 550));
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