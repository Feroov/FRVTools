package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.CorruptZombie;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CorruptZombieModel extends AnimatedTickingGeoModel<CorruptZombie>
{
    public CorruptZombieModel() {}

    private static final ResourceLocation[] TEX = {
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/1.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/8.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/9.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/10.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/11.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/12.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/13.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/14.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/15.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/16.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/17.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/18.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/19.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/20.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/21.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/22.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/23.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/24.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/25.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/26.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_explosion/27.png")};

    @Override
    public ResourceLocation getModelResource(CorruptZombie object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_zombie.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptZombie entity)
    {
        return TEX[(entity.getTextureTimer())];
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptZombie object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/corrupt_zombie.animation.json");
    }

    @Override
    public void setLivingAnimations(CorruptZombie entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
