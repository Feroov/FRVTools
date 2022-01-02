package com.feroov.frv.entities.projectiles.render;

import com.feroov.frv.entities.projectiles.CannonBall;
import com.feroov.frv.entities.projectiles.PirateCaptainMelee;
import com.feroov.frv.entities.projectiles.model.CannonBallModel;
import com.feroov.frv.entities.projectiles.model.PirateCaptainMeleeModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class CannonBallRender extends GeoProjectilesRenderer<CannonBall> {

    public CannonBallRender(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CannonBallModel());
    }

    protected int getBlockLightLevel(CannonBall entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderType getRenderType(CannonBall animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void renderEarly(CannonBall animatable, PoseStack stackIn, float ticks,
                            MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn,
                            float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn,
                red, green, blue, partialTicks);
        stackIn.scale(animatable.tickCount > 2 ? 1F : 0.0F, animatable.tickCount > 2 ? 1F : 0.0F,
                animatable.tickCount > 2 ? 1F : 0.0F);
    }
}