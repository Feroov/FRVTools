package com.feroov.frv.item.custom.armors.models;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.armors.FunghoniteArmor;
import com.feroov.frv.item.custom.armors.MeteoriteArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FunghoniteArmorModel extends AnimatedGeoModel<FunghoniteArmor>
{
    @Override
    public ResourceLocation getModelResource(FunghoniteArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/funghonite_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FunghoniteArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/models/armor/funghonite_armor_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FunghoniteArmor animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/armor_animation.json");
    }
}