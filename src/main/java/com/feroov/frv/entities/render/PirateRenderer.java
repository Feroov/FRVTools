package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.Pirate;
import com.feroov.frv.entities.model.HunterModel;
import com.feroov.frv.entities.model.PirateModel;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.variants.HunterVariant;
import com.feroov.frv.entities.variants.PirateVariant;
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

public class PirateRenderer extends GeoEntityRenderer<Pirate>
{
    public static final Map<PirateVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PirateVariant.class), (p_114874_) ->
            {
                p_114874_.put(PirateVariant.MAIN, new ResourceLocation("frv:textures/entity/pirates/pirate.png"));
                p_114874_.put(PirateVariant.PIRATE2, new ResourceLocation("frv:textures/entity/pirates/pirate2.png"));
                p_114874_.put(PirateVariant.PIRATE3, new ResourceLocation("frv:textures/entity/pirates/pirate3.png"));
                p_114874_.put(PirateVariant.PIRATE4, new ResourceLocation("frv:textures/entity/pirates/pirate4.png"));
                p_114874_.put(PirateVariant.PIRATE5, new ResourceLocation("frv:textures/entity/pirates/pirate5.png"));
                p_114874_.put(PirateVariant.PIRATE6, new ResourceLocation("frv:textures/entity/pirates/pirate6.png"));
            });

    public PirateRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new PirateModel());
        this.shadowRadius = 0.55F;
    }
    @Override
    public RenderType getRenderType(Pirate animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(Pirate animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    protected float getDeathMaxRotation(Pirate entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public ResourceLocation getTextureLocation(Pirate entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}