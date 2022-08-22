package com.feroov.frv.entities.misc.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.misc.CorruptBoard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class CorruptBoardModel extends AnimatedTickingGeoModel<CorruptBoard> {

    public CorruptBoardModel() {}

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
    public ResourceLocation getAnimationResource(CorruptBoard entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/corrupt_board.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(CorruptBoard entity)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/corrupt_board.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptBoard entity)
    {
        return TEX[(entity.getTextureTimer())];
    }

}
