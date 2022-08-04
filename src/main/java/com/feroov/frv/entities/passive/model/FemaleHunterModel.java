package com.feroov.frv.entities.passive.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.renderer.FemaleHunterRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class FemaleHunterModel extends AnimatedGeoModel<FemaleHunter>
{
    public FemaleHunterModel() {}

    @Override
    public ResourceLocation getModelResource(FemaleHunter object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/female_hunter.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FemaleHunter entity)
    {
        return FemaleHunterRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(FemaleHunter object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/female_hunter.animation.json");
    }

    @Override
    public void setLivingAnimations(FemaleHunter entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
