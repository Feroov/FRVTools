package com.feroov.frv.item.custom.armors.renders;


import com.feroov.frv.item.custom.armors.AmethystArmor;
import com.feroov.frv.item.custom.armors.MeteoriteArmor;
import com.feroov.frv.item.custom.armors.models.AmethystArmorModel;
import com.feroov.frv.item.custom.armors.models.MeteoriteArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class MeteoriteArmorRenderer extends GeoArmorRenderer<MeteoriteArmor> {
    public MeteoriteArmorRenderer() {
        super(new MeteoriteArmorModel());

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