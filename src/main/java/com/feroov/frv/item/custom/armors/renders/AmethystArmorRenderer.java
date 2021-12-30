package com.feroov.frv.item.custom.armors.renders;


import com.feroov.frv.item.custom.armors.AmethystArmor;
import com.feroov.frv.item.custom.armors.models.AmethystArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AmethystArmorRenderer extends GeoArmorRenderer<AmethystArmor> {
    public AmethystArmorRenderer() {
        super(new AmethystArmorModel());

        this.headBone = "helmet";
        this.bodyBone = "chestplate";
        this.rightArmBone = "rightArm";
        this.leftArmBone = "leftArm";
        this.rightLegBone = "rightLeg";
        this.leftLegBone = "leftLeg";
        this.rightBootBone = "rightBoot";
        this.leftBootBone = "leftBoot";
    }
}