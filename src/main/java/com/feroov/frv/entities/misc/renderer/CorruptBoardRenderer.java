package com.feroov.frv.entities.misc.renderer;

import com.feroov.frv.entities.misc.CorruptBoard;
import com.feroov.frv.entities.misc.model.CorruptBoardModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CorruptBoardRenderer extends GeoEntityRenderer<CorruptBoard>
{
    public CorruptBoardRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CorruptBoardModel());
    }

    @Override
    public RenderType getRenderType(CorruptBoard animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        return RenderType.dragonExplosionAlpha(getTextureLocation(animatable));
    }


    @Override
    protected float getDeathMaxRotation(CorruptBoard entityLivingBaseIn)
    {
        return 0;
    }
}
