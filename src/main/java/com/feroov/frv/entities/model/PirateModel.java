package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Pirate;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.render.HunterRenderer;
import com.feroov.frv.entities.render.PirateRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PirateModel extends AnimatedGeoModel<Pirate>
{
    public PirateModel() {}

    @Override
    public ResourceLocation getModelResource(Pirate object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/pirate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Pirate entity)
    {
        return PirateRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(Pirate object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/pirate_melee.animation.json");
    }

    @Override
    public void setLivingAnimations(Pirate entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
