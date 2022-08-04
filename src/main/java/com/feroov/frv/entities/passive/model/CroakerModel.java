package com.feroov.frv.entities.passive.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.renderer.CroakerRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CroakerModel extends AnimatedGeoModel<Croaker>
{
    public CroakerModel() {}

    @Override
    public ResourceLocation getModelResource(Croaker object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/croaker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Croaker entity)
    {
        return CroakerRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(Croaker object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/croaker.animation.json");
    }

    @Override
    public void setLivingAnimations(Croaker entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
