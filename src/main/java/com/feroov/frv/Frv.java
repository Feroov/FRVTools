package com.feroov.frv;


import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.world.gen.ModEntityEvents;
import com.feroov.frv.world.gen.OreGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;


@Mod(Frv.MOD_ID)
public class Frv
{
    public static final String MOD_ID = "frv";
    private static final Logger LOGGER = LogManager.getLogger();

    public Frv()
    {
        GeckoLib.initialize();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModSoundEvents.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntityEvents.register(eventBus);
        ModEntityTypes.ENTITIES.register(eventBus);

        eventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            OreGen.registerConfigured();
            OreGen.registerPlaced();
        });
    }
}
