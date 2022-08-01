package com.feroov.frv.entities.projectiles.render;


import com.feroov.frv.entities.hostile.CorruptZombie;
import com.feroov.frv.entities.projectiles.Electricity;
import com.feroov.frv.entities.projectiles.model.ElectricityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ElectricityRenderer extends GeoEntityRenderer<Electricity>
{


    public ElectricityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElectricityModel());
    }

    @Override
    public RenderType getRenderType(Electricity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.dragonExplosionAlpha(textureLocation);
    }

    @Override
    public void renderEarly(Electricity animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.9F,9.5F,0.9F);
    }


    @Override
    protected float getDeathMaxRotation(Electricity entityLivingBaseIn) {
        return 0.0F;
    }
}