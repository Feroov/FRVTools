package com.feroov.frv.entities.hostile;



import com.feroov.frv.entities.variants.PirateVariant;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class Pirate extends Monster implements IAnimatable
{

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Pirate.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Pirate.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(Pirate.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Pirate.class, EntityDataSerializers.INT);

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.10F) && !this.isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            if (level.isClientSide)
            {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
                return PlayState.CONTINUE;
            }
        }
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<Pirate>
                (this, "controller", 0, this::predicate));
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
    public Pirate(EntityType<? extends Monster> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
    }
    /*********************************************************************************/


    /******************************** Variant methods *****************************/
    @Override
    public void addAdditionalSaveData(CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21815_)
    {
        super.readAdditionalSaveData(p_21815_);
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_21815_.getInt("Variant"));
    }

    public PirateVariant getVariant()
    {
        return PirateVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(PirateVariant variant)
    {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    private int getTypeVariant()
    {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_)
    {
        PirateVariant variant = Util.getRandom(PirateVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }
    /*********************************************************************************/

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(ATTACK, 1);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.62D)
                .add(Attributes.FOLLOW_RANGE, 17.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D);
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
        this.goalSelector.addGoal(0, new FloatGoal(this));
        //this.targetSelector.addGoal(1, new PirateAttackGoal(this, 0.42D, true, 0));
        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 0.42D, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }


    /*************************** Attack Goal *********************************/
    /** Must combine ranged and normal attack in order to anim ranged (state stuff)**/
    public class PirateAttackGoal extends MeleeAttackGoal {
        private final Pirate entity;
        private final double speedModifier;
        private int statecheck;
        private int ticksUntilNextAttack;
        private int ticksUntilNextPathRecalculation;
        private final boolean followingTargetEvenIfNotSeen;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;

        public PirateAttackGoal(Pirate zombieIn, double speedIn, boolean longMemoryIn, int state) {
            super(zombieIn, speedIn, longMemoryIn);
            this.entity = zombieIn;
            this.statecheck = state;
            this.speedModifier = speedIn;
            this.followingTargetEvenIfNotSeen = longMemoryIn;
        }

        public void start() {
            super.start();
        }

        public boolean canUse() {
            return super.canUse();
        }

        public void stop() {
            super.stop();
            this.entity.setAggressive(false);
            this.entity.setAttackingState(0);
        }

        public void tick() {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null) {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity))
                        && this.ticksUntilNextPathRecalculation <= 0
                        && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D
                        || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY,
                        this.pathedTargetZ) >= 1.0D
                        || this.mob.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = livingentity.getX();
                    this.pathedTargetY = livingentity.getY();
                    this.pathedTargetZ = livingentity.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (d0 > 1024.0D) {
                        this.ticksUntilNextPathRecalculation += 10;
                    } else if (d0 > 256.0D) {
                        this.ticksUntilNextPathRecalculation += 5;
                    }

                    if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }

                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 0, 0);
                this.checkAndPerformAttack(livingentity, d0);
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity livingentity, double squaredDistance) {
            double d0 = this.getAttackReachSqr(livingentity);
            if (squaredDistance <= d0 && this.getTicksUntilNextAttack() <= 0) {
                this.resetAttackCooldown();
                this.entity.setAttackingState(statecheck);
                this.mob.doHurtTarget(livingentity);
            }
        }

        @Override
        protected int getAttackInterval() {
            return 50;
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double) (this.mob.getBbWidth() * 1.0F * this.mob.getBbWidth() * 1.0F + attackTarget.getBbWidth());
        }
    }
    /*************************************************************************/


    /****************************** Sounds **********************************/

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.PIRATE_AMBIENT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.PIRATE_HURT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.PIRATE_DEATH.get(), 1.0F, 1.0F);
        return null;
    }
    /*************************************************************************/


    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
    {
        return 1.65F;
    }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 60 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }
}
