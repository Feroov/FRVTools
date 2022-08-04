package com.feroov.frv.entities.misc.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.misc.CorruptBoard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class CorruptBoardModel extends AnimatedTickingGeoModel<CorruptBoard> {

    public CorruptBoardModel() {}

    private static final ResourceLocation[] TEX = {
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board2.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board3.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board4.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board5.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board6.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board7.png"),
            new ResourceLocation(Frv.MOD_ID, "textures/entity/corrupt_board/corrupt_board7.png")};

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
