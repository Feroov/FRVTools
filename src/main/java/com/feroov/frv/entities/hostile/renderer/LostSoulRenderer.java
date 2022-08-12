package com.feroov.frv.entities.hostile.renderer;


import com.feroov.frv.entities.hostile.LostSoul;
import com.feroov.frv.entities.hostile.Pirate;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.hostile.model.LostSoulModel;
import com.feroov.frv.entities.hostile.model.PirateCaptainModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class LostSoulRenderer extends GeoEntityRenderer<LostSoul>
{

    public LostSoulRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new LostSoulModel());
        this.shadowRadius = 0.0F;
    }
    @Override
    public RenderType getRenderType(LostSoul animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void renderEarly(LostSoul animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    public void render(GeoModel model, LostSoul animatable, float partialTicks, RenderType type,
                       PoseStack matrixStackIn, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder,
                       int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn,
                packedOverlayIn, red, green, blue, 0.3F);
    }

    @Override
    protected float getDeathMaxRotation(LostSoul entityLivingBaseIn)
    {
        return 0;
    }

}