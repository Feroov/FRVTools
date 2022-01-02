package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.CannonBall;
import com.feroov.frv.entities.projectiles.MusketAmmo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MusketAmmoModel extends AnimatedGeoModel<MusketAmmo> {
    @Override
    public ResourceLocation getModelLocation(MusketAmmo object) {
        return new ResourceLocation(Frv.MOD_ID, "geo/cannon_ball.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MusketAmmo object) {
        return new ResourceLocation(Frv.MOD_ID, "textures/item/cannon_ball.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MusketAmmo animatable) {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
