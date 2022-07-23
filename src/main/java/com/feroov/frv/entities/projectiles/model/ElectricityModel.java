package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.Electricity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ElectricityModel extends AnimatedGeoModel<Electricity>
{
    public ElectricityModel() {}

    @Override
    public ResourceLocation getModelResource(Electricity object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/electricity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Electricity object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/electricity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Electricity object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/electricity.animation.json");
    }
}
