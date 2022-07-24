package com.feroov.frv.events;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.projectiles.render.*;
import com.feroov.frv.entities.render.*;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.item.custom.items.particles.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.IModBusEvent;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{

    public ClientModEvents(){}
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
        event.registerEntityRenderer(ModEntityTypes.HUNTER.get(), HunterRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FEMALE_HUNTER.get(), FemaleHunterRenderer::new);

        /** Hostile **/
        event.registerEntityRenderer(ModEntityTypes.PIRATE_CAPTAIN.get(), PirateCaptainRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.FLINTLOCKER.get(), FlintlockerRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PIRATE.get(), PirateRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CORRUPT.get(), CorruptRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.MIMIC.get(), MimicRenderer::new);

        /** Misc **/
        event.registerEntityRenderer(ModEntityTypes.PIRATE_CAPTAIN_MELEE.get(), PirateCaptainMeleeRender::new);
        event.registerEntityRenderer(ModEntityTypes.CANNON_BALL.get(), CannonBallRender::new);
        event.registerEntityRenderer(ModEntityTypes.MUSKET_AMMO.get(), MusketAmmoRender::new);
        event.registerEntityRenderer(ModEntityTypes.CORRUPT_FIRE.get(), CorruptFireRender::new);
        event.registerEntityRenderer(ModEntityTypes.CANNON.get(), CannonRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.ELECTRICITY.get(), ElectricityRenderer::new);
    }

    @Deprecated
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event)
    {
        Minecraft.getInstance().particleEngine.register(ModParticles.COAL_PARTICLES.get(), CoalParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.NETHERITE_PARTICLES.get(), NetheriteParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.DIAMOND_PARTICLES.get(), DiamondParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.GOLD_PARTICLES.get(), GoldParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.IRON_PARTICLES.get(), IronParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.EMERALD_PARTICLES.get(), EmeraldParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.LAPIS_PARTICLES.get(), LapisParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.REDSTONE_PARTICLES.get(), RedstoneParticles.Provider::new);
    }
}
