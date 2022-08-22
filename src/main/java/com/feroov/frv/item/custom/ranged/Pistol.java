package com.feroov.frv.item.custom.ranged;


import com.feroov.frv.entities.projectiles.NineMMBullet;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.network.PacketDistributor;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class Pistol extends RangedItemsComplex
{
    public Pistol()
    {
        super(new Properties().tab(ModItemGroup.FRV_TAB_MISC).stacksTo(1).durability(16));
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

    public void execute(){}
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft)
    {
        if (livingEntity instanceof Player)
        {
            Player playerentity = (Player) livingEntity;
            if (stack.getDamageValue() < (stack.getMaxDamage() - 1))
            {
                playerentity.getCooldowns().addCooldown(this, 7);

                if (!level.isClientSide)
                {
                    NineMMBullet nineMMBullet = createArrow(level, stack, playerentity);
                    nineMMBullet.shootFromRotation(playerentity, playerentity.getXRot(), playerentity.getYRot(),
                            0.0F, 4.0F, 1.0F);

                    nineMMBullet.isNoGravity();

                    double x = playerentity.getX();
                    double y = playerentity.getY();
                    double z = playerentity.getZ();


                    if (level instanceof ServerLevel _level)
                    {
                        Vec3 vec32 = playerentity.getDeltaMovement();

                        _level.sendParticles(ParticleTypes.FLAME, x - (double)(playerentity.getBbWidth() + 2.0F) * 0.5D *
                                (double) Mth.sin(playerentity.yHeadRot * ((float)Math.PI / 180F)), playerentity.getEyeY() -
                                (double)0.1F, z + (double)(playerentity.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(playerentity.yHeadRot *
                                ((float)Math.PI / 180F)), 0, 0.0D, vec32.z, 0, 0);

                        _level.sendParticles(ParticleTypes.SMOKE, x - (double)(playerentity.getBbWidth() + 2.0F) * 0.5D *
                                (double) Mth.sin(playerentity.yHeadRot * ((float)Math.PI / 180F)), playerentity.getEyeY() -
                                (double)0.1F, z + (double)(playerentity.getBbWidth() + 1.0F) * 0.5D * (double)Mth.cos(playerentity.yHeadRot *
                                ((float)Math.PI / 180F)), 0, 0.0D, vec32.z, 0, 0);

//                        _level.sendParticles(ParticleTypes.FLAME, x, y + 1.5, z, 0, 0, 0, 0, 20);
//                        _level.sendParticles(ParticleTypes.SMOKE, x, y + 1.5, z, 0, 0, 0, 0, 20);
                    }

                    stack.hurtAndBreak(1, livingEntity, p -> p.broadcastBreakEvent(livingEntity.getUsedItemHand()));
                    level.addFreshEntity(nineMMBullet);

                    level.playSound((Player) null, playerentity.getX(), playerentity.getY(), playerentity.getZ(),
                            ModSoundEvents.PISTOL.get(), SoundSource.PLAYERS, 1.0F, 1.0F);

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


    public NineMMBullet createArrow(Level worldIn, ItemStack stack, LivingEntity shooter)
    {
        NineMMBullet nineMMBullet = new NineMMBullet(worldIn, shooter);
        return nineMMBullet;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (worldIn.isClientSide)
        {
            if (((Player) entityIn).getMainHandItem().getItem() instanceof Pistol)
            {
                while (Keybindings.RELOAD.consumeClick() && isSelected)
                {
                    FrvPacketHandler.MUSKET.sendToServer(new MusketLoadingPacket(itemSlot));
                }
            }
        }
    }

    public static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public static void reload(Player user, InteractionHand hand)
    {
        if (user.getMainHandItem().getItem() instanceof Pistol)
        {
            while (!user.isCreative() && user.getItemInHand(hand).getDamageValue() != 0
                    && user.getInventory().countItem(ModItems.NINE_MM_MAG.get()) > 0)
            {
                removeAmmo(ModItems.NINE_MM_MAG.get(), user);
                user.getMainHandItem().hurtAndBreak(-15, user, s -> user.broadcastBreakEvent(hand));
                user.getMainHandItem().setPopTime(3);
                user.getCommandSenderWorld().playSound((Player) null, user.getX(), user.getY(), user.getZ(),
                        ModSoundEvents.PISTOL_RELOAD.get(), SoundSource.PLAYERS, 1.00F, 1.0F);
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
