package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.LordOfCorruption;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LordOfCorruptionModel extends AnimatedTickingGeoModel<LordOfCorruption>
{
    public LordOfCorruptionModel() {}

    private static final ResourceLocation[] TEX = {
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/lord_of_corruption/lord_of_corruption7.png") };

    @Override
    public ResourceLocation getModelResource(LordOfCorruption object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/lord_of_corruption.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LordOfCorruption entity)
    {
        return TEX[(entity.getTextureTimer())];
    }

    @Override
    public ResourceLocation getAnimationResource(LordOfCorruption object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/lord_of_corruption.animation.json");
    }

    @Override
    public void setLivingAnimations(LordOfCorruption entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
