package com.feroov.frv.events;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.render.CroakerRenderer;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    private ClientModEvents(){}
    /**
     * @SubscribeEvent
     * public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
     * {
     *     event.registerLayerDefinition(ExampleEntityModel.LAYER_LOCATION, ExampleEntityModel::createBodyLayer);
     * }
     */

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        /** Passive **/
        event.registerEntityRenderer(ModEntityTypes.CROAKER.get(), CroakerRenderer::new);
    }
}
