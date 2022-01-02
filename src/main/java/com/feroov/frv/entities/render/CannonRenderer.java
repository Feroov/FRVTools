package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.Cannon;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.model.CannonModel;
import com.feroov.frv.entities.model.PirateCaptainModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class CannonRenderer extends GeoEntityRenderer<Cannon>
{

    public CannonRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CannonModel());
        this.shadowRadius = 0.0F;
    }
    @Override
    public RenderType getRenderType(Cannon animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }



    @Override
    protected float getDeathMaxRotation(Cannon entityLivingBaseIn)
    {
        return 0;
    }

}