package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.armors.AmethystArmor;
import com.feroov.frv.item.custom.armors.CorruptArmor;
import com.feroov.frv.item.custom.armors.FunghoniteArmor;
import com.feroov.frv.item.custom.armors.MeteoriteArmor;
import com.feroov.frv.item.custom.armors.renders.AmethystArmorRenderer;
import com.feroov.frv.item.custom.armors.renders.CorruptArmorRenderer;
import com.feroov.frv.item.custom.armors.renders.FunghoniteArmorRenderer;
import com.feroov.frv.item.custom.armors.renders.MeteoriteArmorRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArmorRenderers
{
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event)
    {
        GeoArmorRenderer.registerArmorRenderer(AmethystArmor.class, new AmethystArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(CorruptArmor.class, new CorruptArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(MeteoriteArmor.class, new MeteoriteArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(FunghoniteArmor.class, new FunghoniteArmorRenderer());
    }
}
