package com.feroov.frv.item.custom.armors.models;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.armors.AmethystArmor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AmethystArmorModel extends AnimatedGeoModel<AmethystArmor> {
    @Override
    public ResourceLocation getModelLocation(AmethystArmor object) {
        return new ResourceLocation(Frv.MOD_ID, "geo/amethyst_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AmethystArmor object) {
        return new ResourceLocation(Frv.MOD_ID, "textures/models/armor/amethyst_armor_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AmethystArmor animatable) {
        return new ResourceLocation(Frv.MOD_ID, "animations/armor_animation.json");
    }
}