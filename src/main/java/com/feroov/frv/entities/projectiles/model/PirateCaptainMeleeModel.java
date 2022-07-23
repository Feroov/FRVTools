package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.PirateCaptainMelee;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PirateCaptainMeleeModel extends AnimatedGeoModel<PirateCaptainMelee>
{
    @Override
    public ResourceLocation getModelResource(PirateCaptainMelee object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/melee_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PirateCaptainMelee object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/items/melee.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PirateCaptainMelee animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
