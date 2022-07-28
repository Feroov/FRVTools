package com.feroov.frv;


import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.init.Keybindings;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.init.ModParticles;
import com.feroov.frv.item.ModItems;
import com.feroov.frv.item.custom.RangedItems;
import com.feroov.frv.sound.ModSoundEvents;
import com.feroov.frv.util.ClientRegistry;
import com.feroov.frv.util.packets.FrvPacketHandler;
import com.feroov.frv.world.feature.ModConfiguredFeatures;
import com.feroov.frv.world.feature.ModPlacedFeatures;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib3.GeckoLib;



@Mod(Frv.MOD_ID)
public class Frv
{
    public static final String MOD_ID = "frv";

    public Frv()
    {
        GeckoLib.initialize();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FRVStructures.DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
        ModSoundEvents.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModParticles.register(eventBus);
        ModEntityTypes.ENTITIES.register(eventBus);
        ModConfiguredFeatures.register(eventBus);
        ModPlacedFeatures.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        Keybindings.RELOAD = new KeyMapping("key." + Frv.MOD_ID + ".reload", GLFW.GLFW_KEY_R, "key.categories." + Frv.MOD_ID);
        ClientRegistry.registerKeyBinding(Keybindings.RELOAD);

        SpawnPlacements.register(ModEntityTypes.CROAKER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.HUNTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.FEMALE_HUNTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);

        RangedItems.addRanged();
    }


    private void setup(final FMLCommonSetupEvent event)
    {
        FrvPacketHandler.register();
    }
}
