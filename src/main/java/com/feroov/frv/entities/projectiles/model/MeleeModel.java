package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.Melee;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MeleeModel extends AnimatedGeoModel<Melee> {
    @Override
    public ResourceLocation getModelLocation(Melee object) {
        return new ResourceLocation(Frv.MOD_ID, "geo/melee_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Melee object) {
        return new ResourceLocation(Frv.MOD_ID, "textures/items/melee.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Melee animatable) {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
