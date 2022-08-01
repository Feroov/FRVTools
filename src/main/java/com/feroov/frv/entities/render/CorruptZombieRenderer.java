package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.CorruptZombie;
import com.feroov.frv.entities.model.CorruptZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class CorruptZombieRenderer extends GeoEntityRenderer<CorruptZombie>
{

    public CorruptZombieRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new CorruptZombieModel());
        this.shadowRadius = 0.44F;
    }
    @Override
    public RenderType getRenderType(CorruptZombie animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.dragonExplosionAlpha(textureLocation);
    }

    @Override
    public void renderEarly(CorruptZombie animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(1.0F,1.0F,1.0F);
    }

    @Override
    protected float getDeathMaxRotation(CorruptZombie entityLivingBaseIn)
    {
        return 0;
    }
}