package com.feroov.frv.entities.projectiles.render;


import com.feroov.frv.entities.projectiles.MusketAmmo;
import com.feroov.frv.entities.projectiles.NineMMBullet;
import com.feroov.frv.entities.projectiles.model.MusketAmmoModel;
import com.feroov.frv.entities.projectiles.model.NineMMModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

import javax.annotation.Nullable;

public class NineMMRender extends GeoProjectilesRenderer<NineMMBullet> {

    public NineMMRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new NineMMModel());
    }

    protected int getBlockLightLevel(NineMMBullet entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderType getRenderType(NineMMBullet animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderEarly(NineMMBullet animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.08F,0.08F,0.08F);
    }
}