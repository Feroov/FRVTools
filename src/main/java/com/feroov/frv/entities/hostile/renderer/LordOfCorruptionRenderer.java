package com.feroov.frv.entities.hostile.renderer;



import com.feroov.frv.entities.hostile.LordOfCorruption;
import com.feroov.frv.entities.hostile.model.LordOfCorruptionModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class LordOfCorruptionRenderer extends GeoEntityRenderer<LordOfCorruption>
{

    public LordOfCorruptionRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new LordOfCorruptionModel());
        this.shadowRadius = 0.00F;
    }
    @Override
    public RenderType getRenderType(LordOfCorruption animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.dragonExplosionAlpha(textureLocation);
    }

    @Override
    public void renderEarly(LordOfCorruption animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(3.95F,3.95F,3.95F);
    }

    @Override
    protected float getDeathMaxRotation(LordOfCorruption entityLivingBaseIn)
    {
        return 0;
    }
}