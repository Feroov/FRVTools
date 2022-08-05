package com.feroov.frv.sound;

import com.feroov.frv.Frv;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModSoundEvents
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Frv.MOD_ID);

    /*********** Mobs ***********/
    //Croaker
    public static final RegistryObject<SoundEvent> CROAKER_AMBIENT = SOUND_EVENTS.register("croaker_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_ambient")));
    public static final RegistryObject<SoundEvent> CROAKER_HURT = SOUND_EVENTS.register("croaker_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_hurt")));
    public static final RegistryObject<SoundEvent> CROAKER_DEATH = SOUND_EVENTS.register("croaker_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_death")));
    public static final RegistryObject<SoundEvent> CROAKER_YES = SOUND_EVENTS.register("croaker_yes", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_yes")));
    public static final RegistryObject<SoundEvent> CROAKER_NO = SOUND_EVENTS.register("croaker_no", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_no")));
    //Hunter
    public static final RegistryObject<SoundEvent> HUNTER_AMBIENT = SOUND_EVENTS.register("hunter_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "hunter_ambient")));
    public static final RegistryObject<SoundEvent> HUNTER_HURT = SOUND_EVENTS.register("hunter_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "hunter_hurt")));
    public static final RegistryObject<SoundEvent> HUNTER_DEATH = SOUND_EVENTS.register("hunter_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "hunter_death")));

    public static final RegistryObject<SoundEvent> HUNTER_YES = SOUND_EVENTS.register("hunter_yes", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "hunter_yes")));
    public static final RegistryObject<SoundEvent> HUNTER_NO = SOUND_EVENTS.register("hunter_no", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "hunter_no")));
    //Female Hunter
    public static final RegistryObject<SoundEvent> FEMALE_HUNTER_AMBIENT = SOUND_EVENTS.register("female_hunter_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "female_hunter_ambient")));
    public static final RegistryObject<SoundEvent> FEMALE_HUNTER_HURT = SOUND_EVENTS.register("female_hunter_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "female_hunter_hurt")));
    public static final RegistryObject<SoundEvent> FEMALE_HUNTER_DEATH = SOUND_EVENTS.register("female_hunter_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "female_hunter_death")));
    public static final RegistryObject<SoundEvent> FEMALE_HUNTER_YES = SOUND_EVENTS.register("female_hunter_yes", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "female_hunter_yes")));
    public static final RegistryObject<SoundEvent> FEMALE_HUNTER_NO = SOUND_EVENTS.register("female_hunter_no", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "female_hunter_no")));

    // Pirate Captain
    public static final RegistryObject<SoundEvent> PIRATE_CAPTAIN_AMBIENT = SOUND_EVENTS.register("pirate_captain_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_captain_ambient")));
    public static final RegistryObject<SoundEvent> PIRATE_CAPTAIN_HURT = SOUND_EVENTS.register("pirate_captain_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_captain_hurt")));
    public static final RegistryObject<SoundEvent> PIRATE_CAPTAIN_DEATH = SOUND_EVENTS.register("pirate_captain_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_captain_death")));

    // Flintlocker
    public static final RegistryObject<SoundEvent> FLINTLOCKER_AMBIENT = SOUND_EVENTS.register("flintlocker_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "flintlocker_ambient")));
    public static final RegistryObject<SoundEvent> FLINTLOCKER_HURT = SOUND_EVENTS.register("flintlocker_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "flintlocker_hurt")));
    public static final RegistryObject<SoundEvent> FLINTLOCKER_DEATH = SOUND_EVENTS.register("flintlocker_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "flintlocker_death")));

    // pirate
    public static final RegistryObject<SoundEvent> PIRATE_AMBIENT = SOUND_EVENTS.register("pirate_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_ambient")));
    public static final RegistryObject<SoundEvent> PIRATE_HURT = SOUND_EVENTS.register("pirate_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_hurt")));
    public static final RegistryObject<SoundEvent> PIRATE_DEATH = SOUND_EVENTS.register("pirate_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "pirate_death")));


    // Corrupt
    public static final RegistryObject<SoundEvent> CORRUPT_AMBIENT = SOUND_EVENTS.register("corrupt_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_ambient")));
    public static final RegistryObject<SoundEvent> CORRUPT_AMBIENT2 = SOUND_EVENTS.register("corrupt_ambient2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_ambient2")));
    public static final RegistryObject<SoundEvent> CORRUPT_HURT = SOUND_EVENTS.register("corrupt_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_hurt")));
    public static final RegistryObject<SoundEvent> CORRUPT_DEATH = SOUND_EVENTS.register("corrupt_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_death")));

    // Lost Person
    public static final RegistryObject<SoundEvent> LOST_PERSON = SOUND_EVENTS.register("lost_person", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "lost_person")));
    /****************************/


    public static final RegistryObject<SoundEvent> SILENT = SOUND_EVENTS.register("silent", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "silent")));


    /*********** Misc ***********/
    public static final RegistryObject<SoundEvent> DETECTOR_SOUND = SOUND_EVENTS.register("detector_sound", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "detector_sound")));
    public static final RegistryObject<SoundEvent> SWORD_SWING = SOUND_EVENTS.register("sword_swing", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "sword_swing")));
    public static final RegistryObject<SoundEvent> MUSKET = SOUND_EVENTS.register("musket", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "musket")));
    public static final RegistryObject<SoundEvent> MUSKET_RELOAD = SOUND_EVENTS.register("musket_reload", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "musket_reload")));
    public static final RegistryObject<SoundEvent> CANNON_SHOOT = SOUND_EVENTS.register("cannon_shoot", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "cannon_shoot")));
    public static final RegistryObject<SoundEvent> CORRUPT_FIRE = SOUND_EVENTS.register("corrupt_fire", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_fire")));
    public static final RegistryObject<SoundEvent> GLITCH = SOUND_EVENTS.register("glitch", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "glitch")));
    public static final RegistryObject<SoundEvent> CORRUPT_ORE_PLACE = SOUND_EVENTS.register("corrupt_ore_place", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_ore_place")));
    public static final RegistryObject<SoundEvent> CORRUPT_STEP = SOUND_EVENTS.register("corrupt_step", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_step")));
    public static final RegistryObject<SoundEvent> CORRUPT_BOARD_AMBIENT = SOUND_EVENTS.register("corrupt_board_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "corrupt_board_ambient")));
    /****************************/

    /*********** Musics ***********/
    public static final RegistryObject<SoundEvent> MATRIX_AMBIENCE = SOUND_EVENTS.register("matrix_ambience", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "matrix_ambience")));
    /****************************/


    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}