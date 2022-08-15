package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.TheSword;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TheSwordModel extends AnimatedGeoModel<TheSword>
{
    public TheSwordModel() {}

    @Override
    public ResourceLocation getModelResource(TheSword object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/the_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TheSword object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/the_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TheSword object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/the_sword.animation.json");
    }
}
