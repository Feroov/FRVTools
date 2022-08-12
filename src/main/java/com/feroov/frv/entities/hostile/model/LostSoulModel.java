package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.LostSoul;
import com.feroov.frv.entities.hostile.Pirate;
import com.feroov.frv.entities.hostile.renderer.PirateRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LostSoulModel extends AnimatedGeoModel<LostSoul>
{
    public LostSoulModel() {}

    @Override
    public ResourceLocation getModelResource(LostSoul object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/lost_soul.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LostSoul entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/entity/lost_soul/lost_soul.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LostSoul object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/lost_soul.animation.json");
    }

    @Override
    public void setLivingAnimations(LostSoul entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
