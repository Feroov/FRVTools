package com.feroov.frv.entities.projectiles.render;


import com.feroov.frv.entities.projectiles.CorruptAngelFire;
import com.feroov.frv.entities.projectiles.CorruptFire;
import com.feroov.frv.entities.projectiles.model.CorruptAngelFireModel;
import com.feroov.frv.entities.projectiles.model.CorruptFireModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

import javax.annotation.Nullable;

public class CorruptAngelFireRender extends GeoProjectilesRenderer<CorruptAngelFire> {

    public CorruptAngelFireRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CorruptAngelFireModel());
    }

    protected int getBlockLightLevel(CorruptFire entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderType getRenderType(CorruptAngelFire animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderEarly(CorruptAngelFire animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(1.9F,1.9F,1.9F);
    }
}