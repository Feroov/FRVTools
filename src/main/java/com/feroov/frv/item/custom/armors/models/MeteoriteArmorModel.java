package com.feroov.frv.item.custom.armors.models;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.armors.MeteoriteArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MeteoriteArmorModel extends AnimatedGeoModel<MeteoriteArmor>
{
    @Override
    public ResourceLocation getModelResource(MeteoriteArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/meteorite_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MeteoriteArmor object)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/models/armor/meteorite_armor_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MeteoriteArmor animatable)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/armor_animation.json");
    }
}