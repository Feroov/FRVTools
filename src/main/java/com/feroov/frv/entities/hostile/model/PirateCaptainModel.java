package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.PirateCaptain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PirateCaptainModel extends AnimatedGeoModel<PirateCaptain>
{
    public PirateCaptainModel() {}

    @Override
    public ResourceLocation getModelResource(PirateCaptain object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/pirate_captain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PirateCaptain entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/pirates/pirate_captain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PirateCaptain object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/pirate_captain.animation.json");
    }

    @Override
    public void setLivingAnimations(PirateCaptain entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
