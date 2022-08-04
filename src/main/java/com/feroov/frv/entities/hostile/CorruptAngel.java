package com.feroov.frv.entities.hostile;


import com.feroov.frv.entities.projectiles.CorruptAngelFire;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.EnumSet;


public class CorruptAngel extends FlyingMob implements IAnimatable, IAnimationTickable, RangedAttackMob {

    public int textureTimer;

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(CorruptAngel.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(CorruptAngel.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(CorruptAngel.class, EntityDataSerializers.INT);

    /******************************** Animation stuff ****************************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.isAggressive() && !(this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
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
        event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<CorruptAngel>
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
    public CorruptAngel(EntityType<? extends FlyingMob> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
        this.moveControl = new CorruptAngel.AngelMoveControl(this);
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
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.62D)
                .add(Attributes.FOLLOW_RANGE, 50.0D)
                .add(Attributes.FLYING_SPEED, 20.90D)
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
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new CorruptAngel.CorruptRangedAttackGoal(this, 0.10D, 48.3D, 37.0F, 0));
        this.goalSelector.addGoal(1, new CorruptAngel.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(2, new CorruptAngel.AngelLookGoal(this));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }
    /************************* Texture anim tick stuff ****************************************/
    @Override
    public void aiStep()
    {
        super.aiStep();
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), this.getRandomX(0.5D),
                        this.getRandomY() - 0.85D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D)
                                * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
        textureTimer = (textureTimer + 1) % 8;
    }

    public int getTextureTimer() {
        return textureTimer;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
    /*********************************************************************************************/



    /*************************** Attack Goal *********************************/
    public static class CorruptRangedAttackGoal extends Goal {
        private final CorruptAngel mob;
        private final CorruptAngel rangedAttackMob;
        private int statecheck;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final double attackIntervalMin;
        private final double attackIntervalMax;
        private final float attackRadius;
        private final float attackRadiusSqr;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public CorruptRangedAttackGoal(CorruptAngel femaleHunter, double speedIn, double dpsIn, float rangeIn, int state) {
            this(femaleHunter, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public CorruptRangedAttackGoal(CorruptAngel femaleHunter, double speedIn, double atckIntervalMin, double atckIntervalMax, float atckRadius, int state) {
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
            LivingEntity livingentity = this.mob.getTarget();
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

            if (livingentity != null) {
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) {
                    this.seeTime = 0;
                }

                if (flag) {
                    ++this.seeTime;
                } else {
                    --this.seeTime;
                }
                // strafing going backwards type stuff
                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else {
                    this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20) {
                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }

                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                        this.strafingBackwards = false;
                    } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                        this.strafingBackwards = true;
                    }                                                           //speed shit
                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.45F : 0.45F, this.strafingClockwise ? 0.45F : -0.45F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
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
                } else if (this.attackTime < 0)
                {
                    this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0)
                            / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
                }

            }
        }
    }



    public void performRangedAttack(LivingEntity livingEntity, float p_32142_)
    {
        CorruptAngelFire arrow = new CorruptAngelFire(this.level, this);
        double d0 = livingEntity.getX() - this.getX();
        double d1 = livingEntity.getY() - arrow.getY();
        double d2 = livingEntity.getZ() - this.getZ();
        double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
        arrow.shoot(d0, d1 + d3 * (double) 0, d2, 4.6F, 0.0F);
        this.level.addFreshEntity(arrow);

        CorruptAngelFire arrow2 = new CorruptAngelFire(this.level, this);
        double d4 = livingEntity.getX() - this.getX();
        double d5 = livingEntity.getY(0.2333333333333333D) - arrow2.getY();
        double d6 = livingEntity.getZ() - this.getZ();
        double d7 = (double) Mth.sqrt((float) (d0 * d0 + d6 * d6));
        arrow2.shoot(d4, d5 + d7 * (double) 0.05F, d6, 4.6F, 0.0F);
        this.level.addFreshEntity(arrow2);

        CorruptAngelFire arrow3 = new CorruptAngelFire(this.level, this);
        double a4 = livingEntity.getX() - this.getX();
        double a5 = livingEntity.getY(-3.166666666666) - arrow3.getY();
        double a6 = livingEntity.getZ() - this.getZ();
        double a7 = (double) Mth.sqrt((float) (d0 * d0 + a6 * a6));
        arrow3.shoot(a4, a5 + a7 * (double) 0.05F, a6, 4.6F, 0.0F);
        this.playSound(ModSoundEvents.CORRUPT_FIRE.get(), 4.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow3);

       //left
        CorruptAngelFire arrow4 = new CorruptAngelFire(this.level, this);
        double s4 = livingEntity.getX() - this.getX(0.5F);
        double s5 = livingEntity.getY(-1.1) - arrow4.getY();
        double s6 = livingEntity.getZ() - this.getZ();
        double s7 = (double) Mth.sqrt((float) (s4 * s4 + s6 * s6));
        arrow4.shoot(s4, s5 + s7 * (double) 0.05F, s6, 4.6F, 0.0F);
        this.playSound(ModSoundEvents.CORRUPT_FIRE.get(), 4.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow4);

       //right
        CorruptAngelFire arrow7 = new CorruptAngelFire(this.level, this);
        double f4 = livingEntity.getX() - this.getX(-0.5F);
        double f5 = livingEntity.getY(-1.1) - arrow7.getY();
        double f6 = livingEntity.getZ() - this.getZ();
        double f7 = (double) Mth.sqrt((float) (f4 * f4 + f6 * f6));
        arrow7.shoot(f4, f5 + f7 * (double) 0.05F, f6, 4.6F, 0.0F);
        this.playSound(ModSoundEvents.CORRUPT_FIRE.get(), 4.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow7);
    }


    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        return p_32144_ == Items.BOW;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() { return true; }
    /**************************** Effect on Attack *********************************************/
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
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER, 40));
            }
            return true;
        }
    }
    /*************************************************************************/

    static class AngelLookGoal extends Goal {
        private final CorruptAngel corruptAngel;

        public AngelLookGoal(CorruptAngel p_32762_) {
            this.corruptAngel = p_32762_;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.corruptAngel.getTarget() == null) {
                Vec3 vec3 = this.corruptAngel.getDeltaMovement();
                this.corruptAngel.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.corruptAngel.yBodyRot = this.corruptAngel.getYRot();
            } else {
                LivingEntity livingentity = this.corruptAngel.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.corruptAngel) < 4096.0D) {
                    double d1 = livingentity.getX() - this.corruptAngel.getX();
                    double d2 = livingentity.getZ() - this.corruptAngel.getZ();
                    this.corruptAngel.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.corruptAngel.yBodyRot = this.corruptAngel.getYRot();
                }
            }

        }
    }

    static class AngelMoveControl extends MoveControl {
        private final CorruptAngel corruptAngel;
        private int floatDuration;

        public AngelMoveControl(CorruptAngel p_32768_) {
            super(p_32768_);
            this.corruptAngel = p_32768_;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.corruptAngel.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.corruptAngel.getX(), this.wantedY -
                            this.corruptAngel.getY(), this.wantedZ - this.corruptAngel.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.corruptAngel.setDeltaMovement(this.corruptAngel.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 p_32771_, int p_32772_) {
            AABB aabb = this.corruptAngel.getBoundingBox();

            for(int i = 1; i < p_32772_; ++i) {
                aabb = aabb.move(p_32771_);
                if (!this.corruptAngel.level.noCollision(this.corruptAngel, aabb)) {
                    return false;
                }
            }
            return true;
        }
    }

    static class RandomFloatAroundGoal extends Goal {
        private final CorruptAngel corruptAngel;

        public RandomFloatAroundGoal(CorruptAngel p_32783_) {
            this.corruptAngel = p_32783_;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.corruptAngel.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.corruptAngel.getX();
                double d1 = movecontrol.getWantedY() - this.corruptAngel.getY();
                double d2 = movecontrol.getWantedZ() - this.corruptAngel.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.corruptAngel.getRandom();
            double d0 = this.corruptAngel.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.corruptAngel.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.corruptAngel.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.corruptAngel.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }



    /****************************** Sounds **********************************/
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.CORRUPT_AMBIENT.get(), 6.0F, 0.3F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.CORRUPT_HURT.get(), 3.0F, 0.3F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.CORRUPT_DEATH.get(), 6.0F, 0.3F);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos p_28864_, BlockState p_28865_)
    {
        // Sound is set to 0 on purpose
        this.playSound(ModSoundEvents.CORRUPT_AMBIENT2.get(), 0.0F, 0.0F);
    }
    /*************************************************************************/


    /*************************** Misc ****************************************/
    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
    {
        return 7.75F;
    }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 200 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)200);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }
    /*************************************************************************/

}
