package com.feroov.frv.events;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.*;
import com.feroov.frv.entities.misc.CorruptBoard;
import com.feroov.frv.entities.passive.*;
import com.feroov.frv.entities.projectiles.CorruptExplosion;
import com.feroov.frv.entities.projectiles.Electricity;
import com.feroov.frv.entities.projectiles.Explosion;
import com.feroov.frv.entities.projectiles.TheSword;
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
        event.put(ModEntityTypes.LOST_PERSON.get(), LostPerson.createAttributes().build());
        event.put(ModEntityTypes.GUARD.get(), Guard.createAttributes().build());
        /** Hostile **/
        event.put(ModEntityTypes.PIRATE_CAPTAIN.get(), PirateCaptain.createAttributes().build());
        event.put(ModEntityTypes.FLINTLOCKER.get(), Flintlocker.createAttributes().build());
        event.put(ModEntityTypes.PIRATE.get(), Pirate.createAttributes().build());
        event.put(ModEntityTypes.CANNON.get(), Cannon.createAttributes().build());
        event.put(ModEntityTypes.LOST_SOUL.get(), LostSoul.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT.get(), Corrupt.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT_MINION.get(), CorruptMinion.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT_ANGEL.get(), CorruptAngel.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT_ZOMBIE.get(), CorruptZombie.createAttributes().build());
        event.put(ModEntityTypes.LORD_OF_CORRUPTION.get(), LordOfCorruption.createAttributes().build());
        event.put(ModEntityTypes.ELECTRICITY.get(), Electricity.createAttributes().build());
        event.put(ModEntityTypes.EXPLOSION.get(), Explosion.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT_EXPLOSION.get(), CorruptExplosion.createAttributes().build());
        event.put(ModEntityTypes.THE_SWORD.get(), TheSword.createAttributes().build());
        event.put(ModEntityTypes.MIMIC.get(), Mimic.createAttributes().build());
        event.put(ModEntityTypes.CORRUPT_BOARD.get(), CorruptBoard.createAttributes().build());
    }
}
