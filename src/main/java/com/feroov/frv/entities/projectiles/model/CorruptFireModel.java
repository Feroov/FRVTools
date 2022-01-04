package com.feroov.frv.entities.projectiles.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.CorruptFire;
import com.feroov.frv.entities.projectiles.MusketAmmo;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class CorruptFireModel extends AnimatedGeoModel<CorruptFire> {

    public CorruptFireModel() {}



    @Override
    public ResourceLocation getModelLocation(CorruptFire object) {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_fire.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CorruptFire object) {
        return new ResourceLocation(Frv.MOD_ID, "textures/item/corrupt_fire.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CorruptFire animatable) {
        return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
    }
}
