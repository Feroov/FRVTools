package com.feroov.frv.events;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.*;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.projectiles.Electricity;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Bus.MOD)
public class ModEvents
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        /** Passive **/
        event.put(ModEntityTypes.CROAKER.get(), Croaker.createAttributes().build());
        event.put(ModEntityTypes.HUNTER.get(), Hunter.createAttributes().build());
        event.put(ModEntityTypes.FEMALE_HUNTER.get(), FemaleHunter.createAttributes().build());
        /** Hostile **/
        event.put(ModEntityTypes.PIRATE_CAPTAIN.get(), PirateCaptain.createAttributes().build());
        event.put(ModEntityTypes.FLINTLOCKER.get(), Flintlocker.createAttributes().build());
        event.put(ModEntityTypes.PIRATE.get(), Pirate.createAttributes().build());
        event.put(ModEntityTypes.CANNON.get(), Cannon.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT.get(), Corrupt.createAttributes().build());
        event.put(ModEntityTypes.ELECTRICITY.get(), Electricity.createAttributes().build());
        event.put(ModEntityTypes.MIMIC.get(), Mimic.createAttributes().build());
    }
}
