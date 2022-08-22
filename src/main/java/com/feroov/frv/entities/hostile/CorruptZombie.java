package com.feroov.frv.entities.hostile;



import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
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


public class CorruptZombie extends Monster implements IAnimatable, IAnimationTickable
{
    public int textureTimer;
    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(CorruptZombie.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(CorruptZombie.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(CorruptZombie.class, EntityDataSerializers.BOOLEAN);

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {

        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }

        if(isAttacking())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }

        if (!(animationSpeed > -0.10F && animationSpeed < 0.10F) && !this.isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        if(isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }


        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<CorruptZombie>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    /*********************************************************************************/


    /******************************** Constructor *************************************/
    public CorruptZombie(EntityType<? extends Monster> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
    }
    /*********************************************************************************/


    public void setAttacking(boolean attack) {
        this.entityData.set(ATTACKING, attack);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(ATTACKING, false);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.72D)
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.ATTACK_DAMAGE, 33.0D);
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
        this.goalSelector.addGoal(1, new CorruptZombieAttack(this, 0.55, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Hunter.class, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FemaleHunter.class, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    /*************************** Attack Goal *********************************/
    public class CorruptZombieAttack extends MeleeAttackGoal
    {
        private CorruptZombie entity;
        private int animCounter = 0;
        private int animTickLength = 19;

        public CorruptZombieAttack(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen)
        {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
            if(mob instanceof CorruptZombie c)
            {
                entity = c;
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_)
        {
            if (p_25558_ <= this.getAttackReachSqr(p_25557_) && this.getTicksUntilNextAttack() <= 0)
            {
                if(entity != null)
                {
                    entity.setAttacking(true);
                    animCounter = 0;
                }
            }

            super.checkAndPerformAttack(p_25557_, p_25558_);
        }

        @Override
        public void tick()
        {
            super.tick();
            if(entity.isAttacking())
            {
                animCounter++;

                if(animCounter >= animTickLength)
                {
                    animCounter = 0;
                    entity.setAttacking(false);
                }
            }
        }

        @Override
        public void stop()
        {
            animCounter = 0;
            entity.setAttacking(false);
            super.stop();
        }
    }
    /*************************************************************************/

    /************************* Texture anim tick stuff ****************************************/
    @Override
    public void aiStep()
    {
        super.aiStep();
        if (this.level.isClientSide) {
            for(int i = 0; i < 2; ++i) {
                this.level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), this.getRandomX(0.5D),
                        this.getRandomY() - 0.85D, this.getRandomZ(0.5D), (this.random.nextDouble()
                                - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
        textureTimer = (textureTimer + 1) % 27;
    }


    public int getTextureTimer() {
        return textureTimer;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }
    /*********************************************************************************************/

    /****************************** Sounds **********************************/

    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.CORRUPT_FIRE.get(), 1.0F, 1.2F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.GLITCH.get(), 1.0F, 1.2F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.CORRUPT_DEATH.get(), 1.0F, 1.2F);
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
        if (this.deathTime == 40 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)40);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public int getExperienceReward()
    {
        return 20;
    }

    @Override
    public boolean isBaby()
    {
        return false;
    }

    @Override
    public boolean causeFallDamage(float p_148859_, float p_148860_, DamageSource p_148861_) {
        return false;
    }
}
