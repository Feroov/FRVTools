package com.feroov.frv.block.custom.tnt.renderer;

import com.feroov.frv.Frv;
import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.entities.misc.PrimedEndrium;
import com.feroov.frv.entities.misc.PrimedVoid;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VoidTNTRenderer extends EntityRenderer<PrimedVoid>
{
    private final BlockRenderDispatcher blockRenderer;

    public VoidTNTRenderer(EntityRendererProvider.Context p_174426_)
    {
        super(p_174426_);
        this.shadowRadius = 0.5F;
        this.blockRenderer = p_174426_.getBlockRenderDispatcher();
    }

    public void render(PrimedVoid primedVoid, float v, float v1, PoseStack poseStack, MultiBufferSource multiBufferSource, int i1)
    {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        int i = primedVoid.getFuse();
        if ((float)i - v1 + 1.0F < 10.0F)
        {
            float f = 1.0F - ((float)i - v1 + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            poseStack.scale(f1, f1, f1);
        }

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5D, -0.5D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, ModBlocks.VOID_TNT.get().defaultBlockState(), poseStack,
                multiBufferSource, i1, i / 5 % 2 == 0);
        poseStack.popPose();

        super.render(primedVoid, v, v1, poseStack, multiBufferSource, i1);
    }

    public ResourceLocation getTextureLocation(PrimedVoid primedVoid)
    {
        return new ResourceLocation(Frv.MOD_ID, "textures/block/void_tnt.png");
    }
}