package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.Explosion;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ExplosionModel extends AnimatedGeoModel<Explosion>
{
    public ExplosionModel() {}

    @Override
    public ResourceLocation getModelResource(Explosion object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/explosion.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Explosion object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/explosion.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Explosion object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/explosion.animation.json");
    }
}
