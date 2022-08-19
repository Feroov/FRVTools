package com.feroov.frv.entities.misc;

import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PrimedEndrium extends Entity
{
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedEndrium.class, EntityDataSerializers.INT);

    private static final int DEFAULT_FUSE_TIME = 135;

    @Nullable
    private LivingEntity owner;

    public PrimedEndrium(Level level, double v, double v1, double v2, @Nullable LivingEntity livingEntity)
    {
        this(ModEntityTypes.PRIMED_ENDRIUM.get(), level);
        this.setPos(v, v1, v2);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(135);
        this.xo = v;
        this.yo = v1;
        this.zo = v2;
        this.owner = livingEntity;
    }

    public PrimedEndrium(EntityType<PrimedEndrium> primedEndriumEntityType, Level level)
    {
        super(primedEndriumEntityType, level);
        this.blocksBuilding = true;
    }


    protected void defineSynchedData()
    {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    protected MovementEmission getMovementEmission()
    {
        return MovementEmission.NONE;
    }

    public boolean isPickable()
    {
        return !this.isRemoved();
    }

    public void tick()
    {
        if (!this.isNoGravity())
        {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround)
        {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0)
        {
            this.discard();
            if (!this.level.isClientSide)
            {
                this.explode();
            }
        }
        else
        {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide)
            {
                this.level.addParticle(ParticleTypes.PORTAL, this.getX(), this.getY()
                        + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }


    protected void explode()
    {
        float f = 4.0F;
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 22.0F, Explosion.BlockInteraction.DESTROY);
    }

    protected void addAdditionalSaveData(CompoundTag compoundTag)
    {
        compoundTag.putShort("Fuse", (short)this.getFuse());
    }

    protected void readAdditionalSaveData(CompoundTag compoundTag)
    {
        this.setFuse(compoundTag.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner()
    {
        return this.owner;
    }

    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions)
    {
        return 0.15F;
    }

    public void setFuse(int i)
    {
        this.entityData.set(DATA_FUSE_ID, i);
    }

    public int getFuse()
    {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public Packet<?> getAddEntityPacket()
    {
        return new ClientboundAddEntityPacket(this);
    }

}