package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Cannon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CannonModel extends AnimatedGeoModel<Cannon>
{
    public CannonModel() {}

    @Override
    public ResourceLocation getModelResource(Cannon object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/cannon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Cannon entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/pirates/cannon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Cannon object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/cannon.animation.json");
    }

}
