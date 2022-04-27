package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;

import com.feroov.frv.entities.hostile.Mimic;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MimicModel extends AnimatedGeoModel<Mimic>
{
    public MimicModel() {}

    @Override
    public ResourceLocation getModelLocation(Mimic object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/mimic.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Mimic entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/mimic/mimic.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Mimic object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/mimic.animation.json");
    }

}
