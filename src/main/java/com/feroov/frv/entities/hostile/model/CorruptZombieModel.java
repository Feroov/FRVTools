package com.feroov.frv.entities.hostile.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Corrupt;
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
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_zombie/corrupt_zombie7.png") };

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
