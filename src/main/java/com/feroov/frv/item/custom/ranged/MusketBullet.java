package com.feroov.frv.item.custom.ranged;

import com.feroov.frv.entities.projectiles.MusketAmmo;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MusketBullet extends Item
{
    public final float damage;

    public MusketBullet(Properties properties, float damageIn) {
        super(properties);
        this.damage = damageIn;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent(
                "Press R to reload").withStyle(ChatFormatting.BOLD));
    }

    public MusketAmmo createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        MusketAmmo arrowentity = new MusketAmmo(worldIn, shooter);
        arrowentity.setBaseDamage(this.damage);
        return arrowentity;

    }
}
