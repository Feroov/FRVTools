package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.CannonBall;
import com.feroov.frv.entities.projectiles.PirateCaptainMelee;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CannonBallModel extends AnimatedGeoModel<CannonBall> {
    @Override
    public ResourceLocation getModelLocation(CannonBall object) {
        return new ResourceLocation(Frv.MOD_ID, "geo/cannon_ball.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CannonBall object) {
        return new ResourceLocation(Frv.MOD_ID, "textures/item/cannon_ball.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CannonBall animatable) {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
