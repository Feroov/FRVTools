package com.feroov.frv.item.custom.armors.models;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.armors.CorruptArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorruptArmorModel extends AnimatedGeoModel<CorruptArmor>
{
    @Override
    public ResourceLocation getModelResource(CorruptArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/models/armor/corrupt_armor_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptArmor animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/armor_animation.json");
    }
}