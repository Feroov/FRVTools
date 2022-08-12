package com.feroov.frv.item.custom.ranged;

import com.feroov.frv.entities.projectiles.MusketAmmo;
import com.feroov.frv.init.Keybindings;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.item.custom.RangedItemsComplex;
import com.feroov.frv.item.custom.ranged.render.MusketRender;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.util.packets.FrvPacketHandler;
import com.feroov.frv.util.packets.MusketLoadingPacket;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Musket extends RangedItemsComplex
{
    public Musket()
    {
        super(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).stacksTo(1).durability(2));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions()
        {
            private final BlockEntityWithoutLevelRenderer renderer = new MusketRender();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                return renderer;
            }
        });
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft)
    {
        if (entityLiving instanceof Player)
        {
            Player playerentity = (Player) entityLiving;
            if (stack.getDamageValue() < (stack.getMaxDamage() - 1))
            {
                playerentity.getCooldowns().addCooldown(this, 30);
                if (!worldIn.isClientSide)
                {
                    MusketAmmo abstractarrowentity = createArrow(worldIn, stack, playerentity);
                    abstractarrowentity.shootFromRotation(playerentity, playerentity.getXRot(), playerentity.getYRot(),
                            0.0F, 1.0F * 3.0F, 1.0F);
                    abstractarrowentity.isNoGravity();

                    stack.hurtAndBreak(1, entityLiving, p -> p.broadcastBreakEvent(entityLiving.getUsedItemHand()));
                    worldIn.addFreshEntity(abstractarrowentity);
                    worldIn.playSound((Player) null, playerentity.getX(), playerentity.getY(), playerentity.getZ(),
                            ModSoundEvents.MUSKET.get(), SoundSource.PLAYERS, 1.0F,
                            2.0F / (worldIn.random.nextFloat() * 0.4F + 10.2F) + 0.25F * 0.5F);

                    if (!worldIn.isClientSide)
                    {
                        final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) worldIn);
                        final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF
                                .with(() -> playerentity);
                        GeckoLibNetwork.syncAnimation(target, this, id, STATE);
                    }
                }
            }
        }
    }

    public MusketAmmo createArrow(Level worldIn, ItemStack stack, LivingEntity shooter)
    {
        MusketAmmo arrowentity = new MusketAmmo(worldIn, shooter);
        return arrowentity;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (worldIn.isClientSide)
        {
            if (((Player) entityIn).getMainHandItem().getItem() instanceof Musket)
            {
                while (Keybindings.RELOAD.consumeClick() && isSelected)
                {
                    FrvPacketHandler.MUSKET.sendToServer(new MusketLoadingPacket(itemSlot));
                }
            }
        }
    }


    public static void reload(Player user, InteractionHand hand)
    {
        if (user.getMainHandItem().getItem() instanceof Musket)
        {
            while (!user.isCreative() && user.getItemInHand(hand).getDamageValue() != 0
                    && user.getInventory().countItem(ModItems.MUSKET_BULLET.get()) > 0)
            {
                removeAmmo(ModItems.MUSKET_BULLET.get(), user);
                user.getMainHandItem().hurtAndBreak(-10, user, s -> user.broadcastBreakEvent(hand));
                user.getMainHandItem().setPopTime(3);
                user.getCommandSenderWorld().playSound((Player) null, user.getX(), user.getY(), user.getZ(),
                        ModSoundEvents.MUSKET_RELOAD.get(), SoundSource.PLAYERS, 1.00F, 1.0F);
            }
        }
    }

    @Override
    public boolean isRepairable(ItemStack stack)
    {
        return canRepair && isDamageable(stack);
    }


    public UseAnim getUseAnimation(ItemStack p_40678_)
    {
        return UseAnim.BOW;
    }
}
