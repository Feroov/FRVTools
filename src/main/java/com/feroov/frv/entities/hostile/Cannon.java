package com.feroov.frv.entities.hostile;


import com.feroov.frv.entities.passive.Guard;
import com.feroov.frv.entities.projectiles.CannonBall;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;


public class Cannon extends Monster implements IAnimatable, RangedAttackMob
{
    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Cannon.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Cannon.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(Cannon.class, EntityDataSerializers.INT);

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attack(AnimationEvent<E> event)
    {
        if (isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<Cannon>
                (this, "predicate", 0, this::predicate));
        data.addAnimationController(new AnimationController<Cannon>
                (this, "attackController", 0, this::attack));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public void setAttackingState(int time)
    {
        this.entityData.set(ATTACK, time);
    }
    /*********************************************************************************/


    /******************************** Constructor *************************************/
    public Cannon(EntityType<? extends Monster> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
        this.isNoAi();
    }
    /*********************************************************************************/


    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(ATTACK, 1);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 20.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 100.0D);
    }

    @Override
    protected void updateControlFlags()
    {
        super.updateControlFlags();
        if(this.isStunned())
        {
            this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
            this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
            this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
        }
    }

    public boolean isStunned()
    {
        return this.entityData.get(STUNNED);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.targetSelector.addGoal(2, new CannonAttackGoal(this, 0.0D, true, 3));//These are combined
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Guard.class, true));
        this.goalSelector.addGoal(4, new CannonRangedAttackGoal(this, 0.0D, 80, 24.0F, 0)); // These are combined
    }


    /*************************** Attack Goal *********************************/
    /** Must combine ranged and normal attack in order to anim ranged (state stuff)**/
    static class CannonAttackGoal extends MeleeAttackGoal
    {
        private final Cannon entity;
        private final double speedModifier;
        private int statecheck;
        private int ticksUntilNextAttack;
        private int ticksUntilNextPathRecalculation;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;

        public CannonAttackGoal(Cannon zombieIn, double speedIn, boolean longMemoryIn, int state)
        {
            super(zombieIn, speedIn, longMemoryIn);
            this.entity = zombieIn;
            this.statecheck = state;
            this.speedModifier = speedIn;
        }

        public void start()
        {
            super.start();
        }

        public boolean canUse()
        {
            return super.canUse();
        }

        public void stop()
        {
            super.stop();
            this.entity.setAggressive(false);
            this.entity.setAttackingState(0);
        }

        public void tick()
        {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if ((this.mob.getSensing().hasLineOfSight(livingentity))
                        && this.ticksUntilNextPathRecalculation <= 0
                        && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D
                        || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY,
                        this.pathedTargetZ) >= 1.0D
                        || this.mob.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = livingentity.getX();
                    this.pathedTargetY = livingentity.getY();
                    this.pathedTargetZ = livingentity.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (d0 > 1024.0D)
                    {
                        this.ticksUntilNextPathRecalculation += 10;
                    }
                    else if (d0 > 256.0D)
                    {
                        this.ticksUntilNextPathRecalculation += 5;
                    }

                    if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier))
                    {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 0, 0);
                this.checkAndPerformAttack(livingentity, d0);
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity livingentity, double squaredDistance)
        {
            double d0 = this.getAttackReachSqr(livingentity);
            if (squaredDistance <= d0 && this.getTicksUntilNextAttack() <= 0)
            {
                this.resetAttackCooldown();
                this.entity.setAttackingState(statecheck);
                this.mob.doHurtTarget(livingentity);
            }
        }

        @Override
        protected int getAttackInterval()
        {
            return 0;
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget)
        {
            return this.mob.getBbWidth() * 0.0F * this.mob.getBbWidth() * 0.0F + attackTarget.getBbWidth();
        }
    }
    public static class CannonRangedAttackGoal extends Goal {
        private final Cannon mob;
        private final Cannon rangedAttackMob;
        private int statecheck;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final int attackIntervalMin;
        private final int attackIntervalMax;
        private final float attackRadius;
        private final float attackRadiusSqr;

        public CannonRangedAttackGoal(Cannon femaleHunter, double speedIn, int dpsIn, float rangeIn, int state) {
            this(femaleHunter, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public CannonRangedAttackGoal(Cannon femaleHunter, double speedIn, int atckIntervalMin, int atckIntervalMax, float atckRadius, int state) {
            if (femaleHunter == null) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.rangedAttackMob =  femaleHunter;
                this.mob =  femaleHunter;
                this.speedModifier = speedIn;
                this.attackIntervalMin = atckIntervalMin;
                this.attackIntervalMax = atckIntervalMax;
                this.attackRadius = atckRadius;
                this.attackRadiusSqr = atckRadius * atckRadius;
                this.statecheck = state;

                this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            }
        }


        public boolean canUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canUse() || !this.mob.getNavigation().isDone();
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
        public void tick() {
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }

            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            if (--this.attackTime == 0) {
                if (!flag) {
                    return;
                }
                if (this.mob.isUsingItem()) {
                    if (!flag && this.seeTime < -60) {
                        this.mob.stopUsingItem();
                    } else if (flag) {
                        int i = this.mob.getTicksUsingItem();
                        if (i >= 19) {
                            this.mob.setAttackingState(statecheck);
                        }
                        if (i >= 20) {
                            this.mob.stopUsingItem();
                        }
                    }
                }
                float f = (float)Math.sqrt(d0) / this.attackRadius;
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                this.rangedAttackMob.performRangedAttack(this.target, f1);
                this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
            } else if (this.attackTime < 0) {
                this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
            }

        }
    }


    public void performRangedAttack(LivingEntity livingEntity, float p_32142_) {
        CannonBall arrow = new CannonBall(this.level, this);
        double d0 = livingEntity.getEyeY() - (double)1.1F;
        double d1 = livingEntity.getX() - this.getX();
        double d2 = d0 - arrow.getY();
        double d3 = livingEntity.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
                                                  //speed          // accuracy
        arrow.shoot(d1, d2 + d4, d3, 1.6F, 6.0F);
        this.playSound(ModSoundEvents.CANNON_SHOOT.get(), 6.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);

    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        return p_32144_ == Items.BOW;
    }
    /*************************************************************************/


    /****************************** Sounds **********************************/
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.PIRATE_CAPTAIN_AMBIENT.get(), 0.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.PIRATE_CAPTAIN_HURT.get(), 0.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.PIRATE_CAPTAIN_DEATH.get(), 0.0F, 1.0F);
        return null;
    }
    /*************************************************************************/

    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public boolean isBaby()
    {
        return false;
    }

}
