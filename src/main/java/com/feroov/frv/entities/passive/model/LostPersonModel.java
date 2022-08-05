package com.feroov.frv.entities.passive.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.passive.LostPerson;
import com.feroov.frv.entities.passive.renderer.HunterRenderer;
import com.feroov.frv.entities.passive.renderer.LostPersonRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LostPersonModel extends AnimatedGeoModel<LostPerson>
{
    public LostPersonModel() {}

    @Override
    public ResourceLocation getModelResource(LostPerson object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/lost_person.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LostPerson entity)
    {
        return LostPersonRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(LostPerson object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/lost_person.animation.json");
    }

    @Override
    public void setLivingAnimations(LostPerson entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
