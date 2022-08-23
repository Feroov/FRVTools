package com.feroov.frv.entities.passive.renderer;

import com.feroov.frv.entities.passive.Guard;
import com.feroov.frv.entities.passive.model.GuardModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

public class GuardRenderer extends GeoEntityRenderer<Guard>
{

    public GuardRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new GuardModel());
        this.shadowRadius = 0.44F;
    }
    @Override
    public RenderType getRenderType(Guard animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }

    @Override
    public void renderEarly(Guard animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    protected float getDeathMaxRotation(Guard entityLivingBaseIn)
    {
        return 0;
    }
}