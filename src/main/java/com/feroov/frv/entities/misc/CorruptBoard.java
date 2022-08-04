package com.feroov.frv.entities.misc;


import javax.annotation.Nullable;

import com.feroov.frv.init.ModParticles;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class CorruptBoard extends Animal implements IAnimatable, IAnimationTickable
{

    public int textureTimer;

    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {

        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", false));
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D);
    }

    public CorruptBoard(EntityType<? extends Animal> type, Level worldIn)
    {
        super(type, worldIn);
        this.noCulling = true;
        this.maxUpStep = 1.0F;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        if (this.level.isClientSide)
        {
            for(int i = 0; i < 2; ++i)
            {
                this.level.addParticle(ModParticles.CORRUPT_PARTICLES.get(), this.getRandomX(0.5D),
                        this.getRandomY() - 0.85D, this.getRandomZ(0.5D),
                        (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
        textureTimer = (textureTimer + 1) % 8;
    }


    public int getTextureTimer() { return textureTimer; }

    @Override
    public int tickTimer() { return tickCount; }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        if (!this.isVehicle())
        {
            player.startRiding(this);
            return super.mobInteract(player, hand);
        }

        return super.mobInteract(player, hand);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) { this.playSound(ModSoundEvents.CORRUPT_FIRE.get(), 0.1F, 0.1F); }

    @Override
    public boolean causeFallDamage(float p_148859_, float p_148860_, DamageSource p_148861_) { return false; }

    @Override
    public void travel(Vec3 pos)
    {
        if (this.isAlive())
        {
            if (this.isVehicle())
            {
                LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float f = livingentity.xxa * 0.5F;
                float f1 = livingentity.zza;
                if (f1 <= 0.0F) { f1 *= 0.25F; }

                this.flyingSpeed = this.getSpeed() * 0.1F;
                if (this.isControlledByLocalInstance())
                {
                    this.setSpeed((float)this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    super.travel(new Vec3((double)f, pos.y, (double)f1));
                } else if (livingentity instanceof Player) { this.setDeltaMovement(Vec3.ZERO); }

                this.setSpeed(0.4F);
                this.tryCheckInsideBlocks();
                super.travel(new Vec3((double) f, pos.y, (double) f1));
            }
        }
    }

    @Nullable
    public Entity getControllingPassenger() { return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0); }

    @Override
    public boolean isControlledByLocalInstance() { return true; }

    @Override
    public void registerControllers(AnimationData data) { data.addAnimationController(new AnimationController<CorruptBoard>(this, "controller", 0, this::predicate)); }

    @Override
    public AnimationFactory getFactory() { return this.factory; }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) { return null; }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)  { return 0.75F; }

    @Override
    protected SoundEvent getAmbientSound()  { this.playSound(ModSoundEvents.SILENT.get(), 1.0F, 2.0F); return null; }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)  { this.playSound(ModSoundEvents.GLITCH.get(), 1.0F, 1.0F); return null; }

    @Override
    protected SoundEvent getDeathSound()  { this.playSound(ModSoundEvents.CORRUPT_DEATH.get(), 1.0F, 1.6F); return null; }

    @Override
    public int getExperienceReward() { return 0; }

    public boolean canStandOnFluid(FluidState p_204067_) {
        return p_204067_.is(FluidTags.WATER);
    }
}
