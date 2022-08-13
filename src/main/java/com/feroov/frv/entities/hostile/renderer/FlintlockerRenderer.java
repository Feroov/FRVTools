package com.feroov.frv.entities.hostile.renderer;


import com.feroov.frv.entities.hostile.Flintlocker;
import com.feroov.frv.entities.hostile.model.FlintlockerModel;
import com.feroov.frv.entities.variants.FlintlockerVariant;
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

public class FlintlockerRenderer extends GeoEntityRenderer<Flintlocker>
{


    public static final Map<FlintlockerVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(FlintlockerVariant.class), (p_114874_) ->
            {
                p_114874_.put(FlintlockerVariant.MAIN, new ResourceLocation("frv:textures/entity/pirates/flintlocker.png"));
                p_114874_.put(FlintlockerVariant.FLINT2, new ResourceLocation("frv:textures/entity/pirates/flintlocker2.png"));
                p_114874_.put(FlintlockerVariant.FLINT3, new ResourceLocation("frv:textures/entity/pirates/flintlocker3.png"));
                p_114874_.put(FlintlockerVariant.FLINT4, new ResourceLocation("frv:textures/entity/pirates/flintlocker4.png"));
                p_114874_.put(FlintlockerVariant.FLINT5, new ResourceLocation("frv:textures/entity/pirates/flintlocker5.png"));
                p_114874_.put(FlintlockerVariant.FLINT6, new ResourceLocation("frv:textures/entity/pirates/flintlocker6.png"));
            });

    public FlintlockerRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FlintlockerModel());
        this.shadowRadius = 0.44F;
    }
    @Override
    public RenderType getRenderType(Flintlocker animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutoutNoCull(textureLocation);
    }

    @Override
    public void renderEarly(Flintlocker animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }



    @Override
    protected float getDeathMaxRotation(Flintlocker entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public ResourceLocation getTextureLocation(Flintlocker entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}