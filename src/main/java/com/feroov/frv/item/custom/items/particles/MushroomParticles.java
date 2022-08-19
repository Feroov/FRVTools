package com.feroov.frv.item.custom.items.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MushroomParticles extends TextureSheetParticle
{
    protected MushroomParticles(ClientLevel level, double xCoord, double yCoord, double zCoord,
                                SpriteSet spriteSet, double xd, double yd, double zd)
    {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.gravity = 0.75F;
        this.friction = 0.999F;
        this.xd *= (double)0.8F;
        this.yd *= (double)0.8F;
        this.zd *= (double)0.8F;
        this.yd = (double)(this.random.nextFloat() * 0.4F + 0.05F);
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.2F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.setSpriteFromAge(spriteSet);

    }

    @Override
    public void tick()
    {
        super.tick();
        fadeOut();

    }

    private void fadeOut()
    {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet)
        {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz)
        {
            return new MushroomParticles(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
