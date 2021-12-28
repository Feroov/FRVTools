package com.feroov.frv.entities.render;


import com.feroov.frv.entities.model.CroakerModel;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.variants.CroakerVariant;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

public class CroakerRenderer extends GeoEntityRenderer<Croaker>
{
    public static final Map<CroakerVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(CroakerVariant.class), (p_114874_) ->
            {
                p_114874_.put(CroakerVariant.ORANGE, new ResourceLocation("frv:textures/entity/croaker/croaker.png"));
                p_114874_.put(CroakerVariant.BLUE, new ResourceLocation("frv:textures/entity/croaker/croaker3.png"));
                p_114874_.put(CroakerVariant.GREEN, new ResourceLocation("frv:textures/entity/croaker/croaker2.png"));
                p_114874_.put(CroakerVariant.YELLOW, new ResourceLocation("frv:textures/entity/croaker/croaker4.png"));
                p_114874_.put(CroakerVariant.RED, new ResourceLocation("frv:textures/entity/croaker/croaker5.png"));
                p_114874_.put(CroakerVariant.BLACK, new ResourceLocation("frv:textures/entity/croaker/croaker6.png"));
            });

    public CroakerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CroakerModel());
        this.shadowRadius = 0.55F;
    }
    @Override
    public RenderType getRenderType(Croaker animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(Croaker animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.7F,0.7F,0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Croaker entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}