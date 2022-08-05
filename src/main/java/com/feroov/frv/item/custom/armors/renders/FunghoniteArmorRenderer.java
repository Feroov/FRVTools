package com.feroov.frv.item.custom.armors.renders;


import com.feroov.frv.item.custom.armors.FunghoniteArmor;
import com.feroov.frv.item.custom.armors.MeteoriteArmor;
import com.feroov.frv.item.custom.armors.models.FunghoniteArmorModel;
import com.feroov.frv.item.custom.armors.models.MeteoriteArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class FunghoniteArmorRenderer extends GeoArmorRenderer<FunghoniteArmor> {
    public FunghoniteArmorRenderer() {
        super(new FunghoniteArmorModel());

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