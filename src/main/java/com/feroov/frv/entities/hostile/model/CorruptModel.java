package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Corrupt;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CorruptModel extends AnimatedTickingGeoModel<Corrupt>
{
    public CorruptModel() {}

    private static final ResourceLocation[] TEX = {
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt/corrupt7.png") };

    @Override
    public ResourceLocation getModelResource(Corrupt object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Corrupt entity)
    {
        return TEX[(entity.getTextureTimer())];
    }

    @Override
    public ResourceLocation getAnimationResource(Corrupt object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/corrupt.animation.json");
    }

    @Override
    public void setLivingAnimations(Corrupt entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
