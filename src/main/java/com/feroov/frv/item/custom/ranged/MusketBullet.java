package com.feroov.frv.item.custom.ranged;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MusketBullet extends Item
{
    public final float damage;

    public MusketBullet(Properties properties, float damageIn)
    {
        super(properties);
        this.damage = damageIn;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("Press R to reload"));
    }
}
