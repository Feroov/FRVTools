package com.feroov.frv.entities.projectiles;

import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;



public class Melee extends AbstractArrow implements IAnimatable {

    private int lifeTicks = 1;

    private AnimationFactory factory = new AnimationFactory(this);

    public Melee(EntityType<? extends AbstractArrow> type, Level world) {
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
        data.addAnimationController(new AnimationController<Melee>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public Melee(Level world, LivingEntity owner) {
        super(ModEntityTypes.MELEE.get(), owner, world);
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
}