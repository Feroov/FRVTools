package com.feroov.frv.item.custom.armors.renders;


import com.feroov.frv.item.custom.armors.CorruptArmor;
import com.feroov.frv.item.custom.armors.models.CorruptArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CorruptArmorRenderer extends GeoArmorRenderer<CorruptArmor> {
    public CorruptArmorRenderer() {
        super(new CorruptArmorModel());

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