package com.feroov.frv.entities.projectiles;


import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class CorruptExplosion extends Zombie implements IAnimatable, IAnimationTickable
{

    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(CorruptExplosion.class, EntityDataSerializers.INT);
    private int lifeTicks = 95;
    public int textureTimer;

    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", false));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<CorruptExplosion>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()  { return this.factory; }

    public CorruptExplosion(EntityType<? extends Zombie> entityType, Level level)
    {
        super(entityType, level);
        this.setInvulnerable(true);
        this.knockback(0,0,0);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.ATTACK_DAMAGE, 25.0D)
                .add(Attributes.FOLLOW_RANGE, 45.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        textureTimer = (textureTimer + 1) % 27;
    }


    public int getTextureTimer() {
        return textureTimer;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }


    @Override
    public boolean doHurtTarget(Entity entityIn)
    {
        if (!super.doHurtTarget(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof LivingEntity)
            {
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 340));
            }
            return true;
        }
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Monster.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FlyingMob.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, false));
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(SoundEvents.GHAST_HURT, 0.0F, 0.0F);
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(SoundEvents.LIGHTNING_BOLT_IMPACT, 7.0F, 1.0F);
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(SoundEvents.LIGHTNING_BOLT_THUNDER, 7.0F, 1.0F);
        return null;
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
    public boolean isBaby()  { return false; }

    @Override
    public boolean isPushable()  { return false; }

    @Override
    protected void pushEntities() {}

    @Override
    public boolean ignoreExplosion()
    {
        return true;
    }
}
