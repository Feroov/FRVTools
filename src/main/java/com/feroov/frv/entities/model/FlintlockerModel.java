package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Flintlocker;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.render.FemaleHunterRenderer;
import com.feroov.frv.entities.render.FlintlockerRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class FlintlockerModel extends AnimatedGeoModel<Flintlocker>
{
    public FlintlockerModel() {}

    @Override
    public ResourceLocation getModelLocation(Flintlocker object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/flintlocker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Flintlocker entity)
    {
        return FlintlockerRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Flintlocker object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/pirate.animation.json");
    }

    @Override
    public void setLivingAnimations(Flintlocker entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
