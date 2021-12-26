package com.feroov.frv.entities.render;


import com.feroov.frv.entities.model.HunterModel;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.variants.HunterVariant;
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

public class HunterRenderer extends GeoEntityRenderer<Hunter>
{
    public static final Map<HunterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HunterVariant.class), (p_114874_) ->
            {
                p_114874_.put(HunterVariant.MAIN, new ResourceLocation("frv:textures/entity/hunter/hunter.png"));
            });

    public HunterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HunterModel());
    }
    @Override
    public RenderType getRenderType(Hunter animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(Hunter animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    public ResourceLocation getTextureLocation(Hunter entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}