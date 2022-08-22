package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.NineMMBullet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NineMMModel extends AnimatedGeoModel<NineMMBullet>
{
    @Override
    public ResourceLocation getModelResource(NineMMBullet object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/nine_mm_bullet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NineMMBullet object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/item/nine_mm_bullet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NineMMBullet animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
