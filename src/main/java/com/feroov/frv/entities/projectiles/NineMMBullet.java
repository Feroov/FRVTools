package com.feroov.frv.entities.projectiles;

import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class NineMMBullet extends AbstractArrow implements IAnimatable
{

    private AnimationFactory factory = new AnimationFactory(this);
    private int ticksInAir;
    public NineMMBullet(EntityType<? extends AbstractArrow> type, Level world) { super(type, world); }

    @Override
    protected ItemStack getPickupItem() { return null; }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) { return PlayState.CONTINUE; }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<NineMMBullet>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() { return this.factory; }

    public NineMMBullet(Level world, LivingEntity owner) { super(ModEntityTypes.NINE_MM_BULLET.get(), owner, world); }

    @Override
    public Packet<?> getAddEntityPacket() { return NetworkHooks.getEntitySpawningPacket(this); }

    public SoundEvent hitSound = this.getDefaultHitGroundSoundEvent();

    public void setSoundEvent(SoundEvent soundIn) { this.hitSound = soundIn; }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() { return SoundEvents.ARMOR_EQUIP_IRON; }

    @Override
    protected void onHitBlock(BlockHitResult p_36755_)  { this.setSoundEvent(SoundEvents.GENERIC_EXPLODE); }

    private int lifeTicks = 155;

    @Override
    public void tick()
    {
        super.tick();
        if (--this.lifeTicks < 0)
        {
            this.remove(RemovalReason.DISCARDED);
        }

        boolean flag = false;
        AABB aabb = this.getBoundingBox().inflate(0.2D);
        for(BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX),
                Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level.getBlockState(blockpos);
            Block block = blockstate.getBlock();
            if (block instanceof AbstractGlassBlock) {
                flag = this.level.destroyBlock(blockpos, true, this) || flag;
            }
            if (block instanceof IronBarsBlock) {
                flag = this.level.destroyBlock(blockpos, true, this) || flag;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getType() != HitResult.Type.ENTITY
                || !((EntityHitResult) entityHitResult).getEntity().is(entity))
        {
            if (!this.level.isClientSide)  { this.remove(RemovalReason.KILLED); }
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) { damagesource = DamageSource.arrow(this, this); }
        else
        {
            damagesource = DamageSource.arrow(this, entity1);
            if (entity1 instanceof LivingEntity) { ((LivingEntity) entity1).setLastHurtMob(entity); }
        }

        if (entity.hurt(damagesource, 9.0f))
        {
            if (entity instanceof LivingEntity)
            {
                LivingEntity livingentity = (LivingEntity) entity;
                if (!this.level.isClientSide && entity1 instanceof LivingEntity)
                {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) entity1, livingentity);
                }
                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player
                        && entity1 instanceof ServerPlayer && !this.isSilent())
                {
                    ((ServerPlayer) entity1).connection
                            .send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }
            }
        } else { if (!this.level.isClientSide) { this.remove(RemovalReason.KILLED); } }
    }

    @Override
    public boolean isNoGravity() {
        if (this.isInWater()) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    protected void onHit(HitResult result)
    {
        super.onHit(result);
        Entity entity = this.getOwner();
        if (result.getType() != HitResult.Type.ENTITY || !((EntityHitResult) result).getEntity().is(entity))
        {
            if (!this.level.isClientSide)  { this.remove(RemovalReason.KILLED); }
        }
    }
}