package com.feroov.frv.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;


public class ModFoods
{
    public static final FoodProperties ALE = new FoodProperties.Builder().nutrition(0).saturationMod(0.1f).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 200), 0.25F).build();
}