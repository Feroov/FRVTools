package com.feroov.frv.entities.passive.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.Guard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GuardModel extends AnimatedGeoModel<Guard>
{
    public GuardModel() {}

    @Override
    public ResourceLocation getModelResource(Guard object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/guard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Guard entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/guard/guard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Guard object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/guard.animation.json");
    }

    @Override
    public void setLivingAnimations(Guard entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
