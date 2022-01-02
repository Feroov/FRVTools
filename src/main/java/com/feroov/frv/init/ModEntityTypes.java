package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.Cannon;
import com.feroov.frv.entities.hostile.Flintlocker;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.projectiles.CannonBall;
import com.feroov.frv.entities.projectiles.MusketAmmo;
import com.feroov.frv.entities.projectiles.PirateCaptainMelee;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
    private ModEntityTypes(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Frv.MOD_ID);

    /** Passive **/
    public static final RegistryObject<EntityType<Croaker>> CROAKER = ENTITIES.register("croaker",
            () -> EntityType.Builder.of(Croaker::new, MobCategory.CREATURE)
                    .sized(0.7f,1.3f).build("croaker"));

    public static final RegistryObject<EntityType<Hunter>> HUNTER = ENTITIES.register("hunter",
            () -> EntityType.Builder.of(Hunter::new, MobCategory.CREATURE)
                    .sized(0.7f,1.8f).build("hunter"));

    public static final RegistryObject<EntityType<FemaleHunter>> FEMALE_HUNTER = ENTITIES.register("female_hunter",
            () -> EntityType.Builder.of(FemaleHunter::new, MobCategory.CREATURE)
                    .sized(0.7f,1.8f).build("female_hunter"));
    /** Hostile **/
    public static final RegistryObject<EntityType<PirateCaptain>> PIRATE_CAPTAIN = ENTITIES.register("pirate_captain",
            () -> EntityType.Builder.of(PirateCaptain::new, MobCategory.CREATURE)
                    .sized(0.7f,2.8f).build("pirate_captain"));

    public static final RegistryObject<EntityType<Flintlocker>> FLINTLOCKER = ENTITIES.register("flintlocker",
            () -> EntityType.Builder.of(Flintlocker::new, MobCategory.CREATURE)
                    .sized(0.7f,1.8f).build("flintlocker"));

    /** Misc **/
    // Projectiles
    public static final RegistryObject<EntityType<PirateCaptainMelee>> PIRATE_CAPTAIN_MELEE = ENTITIES.register("pirate_captain_melee",
            () -> EntityType.Builder.<PirateCaptainMelee>of(PirateCaptainMelee::new, MobCategory.MISC).sized(0.5F, 0.5F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "pirate_captain_melee").toString()));

    public static final RegistryObject<EntityType<CannonBall>> CANNON_BALL = ENTITIES.register("cannon_ball",
            () -> EntityType.Builder.<CannonBall>of(CannonBall::new, MobCategory.MISC).sized(1.0F, 1.0F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "cannon_ball").toString()));

    public static final RegistryObject<EntityType<MusketAmmo>> MUSKET_AMMO = ENTITIES.register("musket_bullet",
            () -> EntityType.Builder.<MusketAmmo>of(MusketAmmo::new, MobCategory.MISC).sized(0.5F, 0.5F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "musket_bullet").toString()));
    // Other
    public static final RegistryObject<EntityType<Cannon>> CANNON = ENTITIES.register("cannon",
            () -> EntityType.Builder.<Cannon>of(Cannon::new, MobCategory.CREATURE).sized(0.8F, 0.8F).fireImmune()
                    .build(new ResourceLocation(Frv.MOD_ID, "cannon").toString()));


}

