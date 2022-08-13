package com.feroov.frv.entities.hostile.renderer;


import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.hostile.model.PirateCaptainModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class PirateCaptainRenderer extends GeoEntityRenderer<PirateCaptain>
{

    public PirateCaptainRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new PirateCaptainModel());
        this.shadowRadius = 0.65F;
    }
    @Override
    public RenderType getRenderType(PirateCaptain animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }

    @Override
    public void renderEarly(PirateCaptain animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(1.25F,1.25F,1.25F);
    }

    @Override
    protected float getDeathMaxRotation(PirateCaptain entityLivingBaseIn)
    {
        return 0;
    }

}