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
    public static final RegistryObject<SoundEvent> CROAKER_AMBIENT = SOUND_EVENTS.register("croaker_ambient", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_ambient")));
    public static final RegistryObject<SoundEvent> CROAKER_AMBIENT2 = SOUND_EVENTS.register("croaker_ambient2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_ambient2")));
    public static final RegistryObject<SoundEvent> CROAKER_AMBIENT3 = SOUND_EVENTS.register("croaker_ambient3", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_ambient3")));
    public static final RegistryObject<SoundEvent> CROAKER_HURT = SOUND_EVENTS.register("croaker_hurt", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_hurt")));
    public static final RegistryObject<SoundEvent> CROAKER_HURT2 = SOUND_EVENTS.register("croaker_hurt2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_hurt2")));
    public static final RegistryObject<SoundEvent> CROAKER_HURT3 = SOUND_EVENTS.register("croaker_hurt3", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_hurt3")));
    public static final RegistryObject<SoundEvent> CROAKER_DEATH = SOUND_EVENTS.register("croaker_death", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_death")));
    public static final RegistryObject<SoundEvent> CROAKER_DEATH2 = SOUND_EVENTS.register("croaker_death2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_death2")));
    public static final RegistryObject<SoundEvent> CROAKER_DEATH3 = SOUND_EVENTS.register("croaker_death3", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_death3")));
    public static final RegistryObject<SoundEvent> CROAKER_YES = SOUND_EVENTS.register("croaker_yes", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_yes")));
    public static final RegistryObject<SoundEvent> CROAKER_YES2 = SOUND_EVENTS.register("croaker_yes2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_yes2")));
    public static final RegistryObject<SoundEvent> CROAKER_YES3 = SOUND_EVENTS.register("croaker_yes3", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_yes3")));
    public static final RegistryObject<SoundEvent> CROAKER_NO = SOUND_EVENTS.register("croaker_no", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_no")));
    public static final RegistryObject<SoundEvent> CROAKER_NO2 = SOUND_EVENTS.register("croaker_no2", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_no2")));
    public static final RegistryObject<SoundEvent> CROAKER_NO3 = SOUND_EVENTS.register("croaker_no3", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_no3")));
    public static final RegistryObject<SoundEvent> CROAKER_NO4 = SOUND_EVENTS.register("croaker_no4", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "croaker_no4")));
    /****************************/

    /*********** Misc ***********/
    public static final RegistryObject<SoundEvent> DETECTOR_SOUND = SOUND_EVENTS.register("detector_sound", () -> new SoundEvent(new ResourceLocation(Frv.MOD_ID, "detector_sound")));
    /****************************/

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}