package com.feroov.frv.entities.render;


import com.feroov.frv.entities.model.FemaleHunterModel;
import com.feroov.frv.entities.model.HunterModel;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.variants.FemaleHunterVariant;
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

public class FemaleHunterRenderer extends GeoEntityRenderer<FemaleHunter>
{
    public static final Map<FemaleHunterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(FemaleHunterVariant.class), (p_114874_) ->
            {
                p_114874_.put(FemaleHunterVariant.MAIN, new ResourceLocation("frv:textures/entity/hunter/female_hunter.png"));
                p_114874_.put(FemaleHunterVariant.FEMHUNTER2, new ResourceLocation("frv:textures/entity/hunter/female_hunter2.png"));
                p_114874_.put(FemaleHunterVariant.FEMHUNTER3, new ResourceLocation("frv:textures/entity/hunter/female_hunter3.png"));
                p_114874_.put(FemaleHunterVariant.FEMHUNTER4, new ResourceLocation("frv:textures/entity/hunter/female_hunter4.png"));
                p_114874_.put(FemaleHunterVariant.FEMHUNTER5, new ResourceLocation("frv:textures/entity/hunter/female_hunter5.png"));
                p_114874_.put(FemaleHunterVariant.FEMHUNTER6, new ResourceLocation("frv:textures/entity/hunter/female_hunter6.png"));
            });

    public FemaleHunterRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FemaleHunterModel());
        this.shadowRadius = 0.55F;
    }
    @Override
    public RenderType getRenderType(FemaleHunter animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(FemaleHunter animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    protected float getDeathMaxRotation(FemaleHunter entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public ResourceLocation getTextureLocation(FemaleHunter entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}