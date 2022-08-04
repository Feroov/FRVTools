package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.CorruptAngelFire;
import com.feroov.frv.entities.projectiles.CorruptFire;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorruptAngelFireModel extends AnimatedGeoModel<CorruptAngelFire> {

    public CorruptAngelFireModel() {}

    @Override
    public ResourceLocation getModelResource(CorruptAngelFire object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_fire.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptAngelFire object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/item/corrupt_fire.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptAngelFire animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
