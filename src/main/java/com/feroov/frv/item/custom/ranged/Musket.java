package com.feroov.frv.item.custom.ranged;

import com.feroov.frv.entities.projectiles.MusketAmmo;
import com.feroov.frv.init.Keybindings;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.item.ModItemGroup;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.item.custom.RangedItemsComplex;
import com.feroov.frv.item.custom.ranged.render.MusketRender;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.util.packets.FrvPacketHandler;
import com.feroov.frv.util.packets.MusketLoadingPacket;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof Player) {
            Player playerentity = (Player) livingEntity;
            if (stack.getDamageValue() < (stack.getMaxDamage() - 1)) {
                playerentity.getCooldowns().addCooldown(this, 30);
                if (!level.isClientSide) {
                    MusketAmmo abstractarrowentity = createArrow(level, stack, playerentity);
                    abstractarrowentity.shootFromRotation(playerentity, playerentity.getXRot(), playerentity.getYRot(),
                            0.0F, 3.0F, 1.0F);
                    abstractarrowentity.isNoGravity();

                    stack.hurtAndBreak(1, livingEntity, p -> p.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                    level.addFreshEntity(abstractarrowentity);
                    level.playSound((Player) null, playerentity.getX(), playerentity.getY(), playerentity.getZ(),
                            ModSoundEvents.MUSKET.get(), SoundSource.PLAYERS, 1.0F,
                            2.0F / (level.random.nextFloat() * 0.4F + 10.2F) + 0.25F * 1.0F);

                    double x = playerentity.getX();
                    double y = playerentity.getY();
                    double z = playerentity.getZ();


                    if (level instanceof ServerLevel _level)
                    {
                        Vec3 vec32 = playerentity.getViewVector(1f);

                        _level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x - (double)(playerentity.getBbWidth() + 2.0F) * 0.5D *
                                (double) Mth.sin(playerentity.yHeadRot * ((float)Math.PI / 180F)), playerentity.getEyeY() -
                                (double)0.1F, z + (double)(playerentity.getBbWidth() + 2.0F) * 0.5D * (double)Mth.cos(playerentity.yHeadRot *
                                ((float)Math.PI / 180F)), 0, 0.0D, vec32.z, 0, 0);

                        _level.sendParticles(ParticleTypes.SMOKE, x - (double)(playerentity.getBbWidth() + 2.0F) * 0.5D *
                                (double) Mth.sin(playerentity.yHeadRot * ((float)Math.PI / 180F)), playerentity.getEyeY() -
                                (double)0.1F, z + (double)(playerentity.getBbWidth() + 2.0F) * 0.5D * (double)Mth.cos(playerentity.yHeadRot *
                                ((float)Math.PI / 180F)), 0, 0.0D, vec32.z, 0, 0);

//                        _level.sendParticles(ParticleTypes.FLAME, x, y + 1.5, z, 0, 0, 0, 0, 20);
//                        _level.sendParticles(ParticleTypes.SMOKE, x, y + 1.5, z, 0, 0, 0, 0, 20);
                    }

                    if (!level.isClientSide)
                    {
                        final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel) level);
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


    public UseAnim getUseAnimation(ItemStack itemStack)
    {
        return UseAnim.BOW;
    }
}
