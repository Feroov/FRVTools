package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.Corrupt;
import com.feroov.frv.entities.hostile.Pirate;
import com.feroov.frv.entities.model.CorruptModel;
import com.feroov.frv.entities.model.PirateModel;
import com.feroov.frv.entities.variants.PirateVariant;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

public class CorruptRenderer extends GeoEntityRenderer<Corrupt>
{

    public CorruptRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CorruptModel());
        this.shadowRadius = 0.00F;
    }
    @Override
    public RenderType getRenderType(Corrupt animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(Corrupt animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(1.95F,1.95F,1.95F);
    }

    @Override
    protected int getBlockLightLevel(Corrupt entityIn, BlockPos partialTicks) {
        return entityIn.getTextureTimer() == 1 ? 15 : 1;
    }

    @Override
    protected float getDeathMaxRotation(Corrupt entityLivingBaseIn)
    {
        return 0;
    }
}