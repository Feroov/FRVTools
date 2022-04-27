package com.feroov.frv.entities.render;



import com.feroov.frv.entities.hostile.Mimic;
import com.feroov.frv.entities.model.MimicModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class MimicRenderer extends GeoEntityRenderer<Mimic>
{

    public MimicRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MimicModel());
        this.shadowRadius = 0.0F;
    }
    @Override
    public RenderType getRenderType(Mimic animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }



    @Override
    protected float getDeathMaxRotation(Mimic entityLivingBaseIn)
    {
        return 0;
    }

}