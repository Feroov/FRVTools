package com.feroov.frv.entities.projectiles;

import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;



public class PirateCaptainMelee extends AbstractArrow implements IAnimatable {

    private double lifeTicks = 1.35;

    private AnimationFactory factory = new AnimationFactory(this);

    public PirateCaptainMelee(EntityType<? extends AbstractArrow> type, Level world) {
        super(type, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<PirateCaptainMelee>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public PirateCaptainMelee(Level world, LivingEntity owner) {
        super(ModEntityTypes.PIRATE_CAPTAIN_MELEE.get(), owner, world);
    }

    public void tick()
    {
        super.tick();
        if (--this.lifeTicks < 0)
        {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent()
    {
        this.playSound(ModSoundEvents.SWORD_SWING.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getType() != HitResult.Type.ENTITY
                || !((EntityHitResult) entityHitResult).getEntity().is(entity)) {
            if (!this.level.isClientSide) {
                this.remove(RemovalReason.KILLED);
            }
        }
        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.arrow(this, this);
        } else {
            damagesource = DamageSource.arrow(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity) entity1).setLastHurtMob(entity);
            }
        }
        if (entity.hurt(damagesource, 20.0F)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity;
                if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) entity1, livingentity);
                }
                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player
                        && entity1 instanceof ServerPlayer && !this.isSilent()) {
                    ((ServerPlayer) entity1).connection
                            .send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }
            }
        } else {
            if (!this.level.isClientSide) {
                this.remove(RemovalReason.KILLED);
            }
        }
    }
}