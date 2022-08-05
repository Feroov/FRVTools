package com.feroov.frv.entities.passive.renderer;



import com.feroov.frv.entities.passive.LostPerson;
import com.feroov.frv.entities.passive.model.HunterModel;
import com.feroov.frv.entities.passive.model.LostPersonModel;
import com.feroov.frv.entities.variants.LostPersonVariant;
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

public class LostPersonRenderer extends GeoEntityRenderer<LostPerson>
{
    public static final Map<LostPersonVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(LostPersonVariant.class), (p_114874_) ->
            {
                p_114874_.put(LostPersonVariant.MAIN, new ResourceLocation("frv:textures/entity/lost_person/lost_person.png"));
                p_114874_.put(LostPersonVariant.HUNTER2, new ResourceLocation("frv:textures/entity/lost_person/lost_person2.png"));
                p_114874_.put(LostPersonVariant.HUNTER3, new ResourceLocation("frv:textures/entity/lost_person/lost_person3.png"));
                p_114874_.put(LostPersonVariant.HUNTER4, new ResourceLocation("frv:textures/entity/lost_person/lost_person4.png"));
                p_114874_.put(LostPersonVariant.HUNTER5, new ResourceLocation("frv:textures/entity/lost_person/lost_person5.png"));
                p_114874_.put(LostPersonVariant.HUNTER6, new ResourceLocation("frv:textures/entity/lost_person/lost_person6.png"));
            });

    public LostPersonRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new LostPersonModel());
        this.shadowRadius = 0.44F;
    }
    @Override
    public RenderType getRenderType(LostPerson animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(LostPerson animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(0.95F,0.95F,0.95F);
    }

    @Override
    protected float getDeathMaxRotation(LostPerson entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public ResourceLocation getTextureLocation(LostPerson entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}