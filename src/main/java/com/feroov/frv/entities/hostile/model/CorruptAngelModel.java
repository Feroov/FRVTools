package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.CorruptAngel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CorruptAngelModel extends AnimatedTickingGeoModel<CorruptAngel>
{
    public CorruptAngelModel() {}

    private static final ResourceLocation[] TEX = {
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_angel/corrupt_angel7.png") };

    @Override
    public ResourceLocation getModelResource(CorruptAngel object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_angel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptAngel entity)
    {
        return TEX[(entity.getTextureTimer())];
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptAngel object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/corrupt_angel.animation.json");
    }

    @Override
    public void setLivingAnimations(CorruptAngel entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
