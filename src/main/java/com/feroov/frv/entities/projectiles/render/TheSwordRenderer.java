package com.feroov.frv.entities.projectiles.render;


import com.feroov.frv.entities.projectiles.TheSword;
import com.feroov.frv.entities.projectiles.model.TheSwordModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class TheSwordRenderer extends GeoEntityRenderer<TheSword>
{


    public TheSwordRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TheSwordModel());
    }

    @Override
    public RenderType getRenderType(TheSword animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.dragonExplosionAlpha(textureLocation);
    }


    @Override
    protected float getDeathMaxRotation(TheSword entityLivingBaseIn) {
        return 0.0F;
    }
}