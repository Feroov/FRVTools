package com.feroov.frv.entities.hostile;


import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.CorruptAngelFire;
import com.feroov.frv.entities.projectiles.CorruptFire;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
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

import static com.feroov.frv.sound.ModSoundEvents.MATRIX_AMBIENCE;


public class LordOfCorruption extends Monster implements IAnimatable, IAnimationTickable, RangedAttackMob {

    public int textureTimer;

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(LordOfCorruption.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(LordOfCorruption.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(LordOfCorruption.class, EntityDataSerializers.INT);

     protected int spellCastingTickCount;
    private static final EntityDataAccessor<Byte> DATA_SPELL_CASTING_ID = SynchedEntityData.defineId(SpellcasterIllager.class, EntityDataSerializers.BYTE);
    private LordOfCorruption.SpellType currentSpell = LordOfCorruption.SpellType.NONE;

    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    /******************************** Animation stuff ****************************************/
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!(animationSpeed > -0.10F && animationSpeed < 0.10F) && !this.isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
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
        data.addAnimationController(new AnimationController<LordOfCorruption>
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
    public LordOfCorruption(EntityType<? extends Monster> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
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
                .add(Attributes.MAX_HEALTH, 1024.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.42D)
                .add(Attributes.FOLLOW_RANGE, 60.0D)
                .add(Attributes.ATTACK_DAMAGE, 50.0D);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
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
        this.goalSelector.addGoal(0, new LordOfCorruption.AttackSpellGoal());
        this.targetSelector.addGoal(1, new CorruptAttackGoal(this, 0.0D, true, 3));//These are combined
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new LordOfCorruption.CorruptRangedAttackGoal(this, 0.10D, 100.3D, 50.0F, 0)); // These are combined
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }
    /************************* Texture anim tick stuff ****************************************/
    @Override
    public void aiStep()
    {
        super.aiStep();
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
    public class CorruptAttackGoal extends MeleeAttackGoal {
        private final LordOfCorruption entity;
        private final double speedModifier;
        private int statecheck;
        private int ticksUntilNextAttack;
        private int ticksUntilNextPathRecalculation;
        private final boolean followingTargetEvenIfNotSeen;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;

        public CorruptAttackGoal(LordOfCorruption zombieIn, double speedIn, boolean longMemoryIn, int state) {
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

    public static class CorruptRangedAttackGoal extends Goal {
        private final LordOfCorruption mob;
        private final LordOfCorruption rangedAttackMob;
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

        public CorruptRangedAttackGoal(LordOfCorruption femaleHunter, double speedIn, double dpsIn, float rangeIn, int state) {
            this(femaleHunter, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public CorruptRangedAttackGoal(LordOfCorruption femaleHunter, double speedIn, double atckIntervalMin, double atckIntervalMax, float atckRadius, int state) {
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



    public void performRangedAttack(LivingEntity livingEntity, float p_32142_) {
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
                ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            }
            return true;
        }
    }
    /*************************************************************************/



    /****************************** Sounds **********************************/
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.LORD_OF_CORRUPTION_AMBIENT.get(), 10.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.CORRUPT_HURT.get(), 10.0F, 0.1F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.CORRUPT_DEATH.get(), 10.0F, 0.1F);
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
        return 17.75F;
    }

    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 270 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)270);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }
    /*************************************************************************/

    /*************************** Boss Bar ****************************************/
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }



    @Override
    public void setCustomName(Component name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }
    /*************************************************************************/

    /************************************************** Spell casting stuff ******************************************************************************/

    public class CastingASpellGoal extends Goal {
        public CastingASpellGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return LordOfCorruption.this.getSpellCastingTime() > 0;
        }

        public void start() {
            super.start();
            LordOfCorruption.this.navigation.stop();
        }

        public void stop() {
            super.stop();
            LordOfCorruption.this.setIsCastingSpell(LordOfCorruption.SpellType.NONE);
        }

        public void tick() {
            if (LordOfCorruption.this.getTarget() != null) {
                LordOfCorruption.this.getLookControl().setLookAt(LordOfCorruption.this.getTarget(), (float)LordOfCorruption.this.getMaxHeadYRot(),
                        (float)LordOfCorruption.this.getMaxHeadXRot());
            }

        }
    }

    public void setIsCastingSpell(LordOfCorruption.SpellType p_193081_1_) {
        this.currentSpell = p_193081_1_;
        this.entityData.set(DATA_SPELL_CASTING_ID, (byte)p_193081_1_.id);
    }

    protected int getSpellCastingTime() {
        return this.spellCastingTickCount;
    }

    public enum SpellType {
        NONE(0, 0.0D, 0.0D, 0.0D),
        SUMMON_SPIDER(1, 0.7D, 0.7D, 0.8D);

        private final int id;
        private final double[] spellColor;

        private SpellType(int p_i47561_3_, double p_i47561_4_, double p_i47561_6_, double p_i47561_8_) {
            this.id = p_i47561_3_;
            this.spellColor = new double[]{p_i47561_4_, p_i47561_6_, p_i47561_8_};
        }

        public static SpellType byId(int p_193337_0_) {
            for(LordOfCorruption.SpellType spellcastingillagerentity$spelltype : values()) {
                if (p_193337_0_ == spellcastingillagerentity$spelltype.id) {
                    return spellcastingillagerentity$spelltype;
                }
            }
            return NONE;
        }
    }

    public abstract class UseSpellGoal extends Goal {
        protected int attackWarmupDelay;
        protected int nextAttackTickCount;

        protected UseSpellGoal() {
        }

        public boolean canUse() {
            LivingEntity livingentity = LordOfCorruption.this.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                if (LordOfCorruption.this.isCastingSpell()) {
                    return false;
                } else {
                    return LordOfCorruption.this.tickCount >= this.nextAttackTickCount;
                }
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = LordOfCorruption.this.getTarget();
            return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
        }

        public void start() {
            this.attackWarmupDelay = this.getCastWarmupTime();
            LordOfCorruption.this.spellCastingTickCount = this.getCastingTime();
            this.nextAttackTickCount = LordOfCorruption.this.tickCount + this.getCastingInterval();
            SoundEvent soundevent = this.getSpellPrepareSound();
            if (soundevent != null) {
                LordOfCorruption.this.playSound(soundevent, 0.0F, 1.0F);
            }
        }

        public void tick() {
            --this.attackWarmupDelay;
            if (this.attackWarmupDelay == 0) {
                this.performSpellCasting();
                LordOfCorruption.this.playSound(LordOfCorruption.this.getCastingSoundEvent(), 1.0F, 1.0F);
            }

        }

        protected abstract void performSpellCasting();

        protected int getCastWarmupTime() {
            return 20;
        }

        protected abstract int getCastingTime();

        protected abstract int getCastingInterval();

        @Nullable
        protected abstract SoundEvent getSpellPrepareSound();

        protected abstract LordOfCorruption.SpellType getSpell();
    }

    protected SoundEvent getCastingSoundEvent() {
        return null;
    }

    public boolean isCastingSpell() {
        if (this.level.isClientSide) {
            return this.entityData.get(DATA_SPELL_CASTING_ID) > 0;
        } else {
            return this.spellCastingTickCount > 0;
        }
    }

    class AttackSpellGoal extends LordOfCorruption.UseSpellGoal {
        private AttackSpellGoal() {
        }

        protected int getCastingTime() {
            return 40;
        }

        protected int getCastingInterval() {
            return 100;
        }

        protected void performSpellCasting() {
            LivingEntity livingentity = LordOfCorruption.this.getTarget();
            double d0 = Math.min(livingentity.getY(), LordOfCorruption.this.getY());
            double d1 = Math.max(livingentity.getY(), LordOfCorruption.this.getY()) + 1.0D;
            float f = (float) Mth.atan2(livingentity.getZ() -
                    LordOfCorruption.this.getZ(), livingentity.getX() - LordOfCorruption.this.getX());
            if (LordOfCorruption.this.distanceToSqr(livingentity) < 9.0D) {
                for(int i = 0; i < 5; ++i) {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    this.createSpellEntity(LordOfCorruption.this.getX() + (double)Mth.cos(f1) * 1.5D,
                            LordOfCorruption.this.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for(int k = 0; k < 8; ++k) {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                    this.createSpellEntity(LordOfCorruption.this.getX() + (double)Mth.cos(f2) * 2.5D,
                            LordOfCorruption.this.getZ() + (double)Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            } else {
                for(int l = 0; l < 16; ++l) {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    this.createSpellEntity(LordOfCorruption.this.getX() +
                            (double)Mth.cos(f) * d2, LordOfCorruption.this.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j);
                }
            }

        }

        private void createSpellEntity(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
            BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0D;

            do {
                BlockPos blockpos1 = blockpos.below();
                BlockState blockstate = LordOfCorruption.this.level.getBlockState(blockpos1);
                if (blockstate.isFaceSturdy(LordOfCorruption.this.level, blockpos1, Direction.UP)) {
                    if (!LordOfCorruption.this.level.isEmptyBlock(blockpos)) {
                        BlockState blockstate1 = LordOfCorruption.this.level.getBlockState(blockpos);
                        VoxelShape voxelshape = blockstate1.getCollisionShape(LordOfCorruption.this.level, blockpos);
                        if (!voxelshape.isEmpty()) {
                            d0 = voxelshape.max(Direction.Axis.Y);
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.below();
            } while(blockpos.getY() >= Mth.floor(p_190876_5_) - 1);

            ServerLevel serverworld = (ServerLevel)LordOfCorruption.this.level;

            for(int i = 0; i < 1; ++i) {
                BlockPos blockpos2 = LordOfCorruption.this.blockPosition().offset(-2 +
                        LordOfCorruption.this.random.nextInt(1), 1, -2 + LordOfCorruption.this.random.nextInt(1));
                CorruptMinion corruptMinion = ModEntityTypes.CORRUPT_MINION.get().create(LordOfCorruption.this.level);
                corruptMinion.moveTo(blockpos2, 0.0F, 0.0F);
                corruptMinion.finalizeSpawn(serverworld, LordOfCorruption.this.level.getCurrentDifficultyAt(blockpos),
                        MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                serverworld.addFreshEntityWithPassengers(corruptMinion);
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return ModSoundEvents.GLITCH.get();
        }

        protected LordOfCorruption.SpellType getSpell() {
            return SpellType.NONE;
        }
    }
    /***********************************************************************************************************************************************/
}
