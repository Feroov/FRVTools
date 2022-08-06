package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.*;
import com.feroov.frv.entities.misc.CorruptBoard;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.passive.LostPerson;
import com.feroov.frv.entities.projectiles.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
    private ModEntityTypes(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
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

    public static final RegistryObject<EntityType<LostPerson>> LOST_PERSON = ENTITIES.register("lost_person",
            () -> EntityType.Builder.of(LostPerson::new, MobCategory.CREATURE)
                    .sized(0.7f,1.8f).build("lost_person"));

    /** Hostile **/
    public static final RegistryObject<EntityType<PirateCaptain>> PIRATE_CAPTAIN = ENTITIES.register("pirate_captain",
            () -> EntityType.Builder.of(PirateCaptain::new, MobCategory.CREATURE).canSpawnFarFromPlayer()
                    .sized(0.7f,2.8f).build("pirate_captain"));

    public static final RegistryObject<EntityType<Pirate>> PIRATE = ENTITIES.register("pirate",
            () -> EntityType.Builder.of(Pirate::new, MobCategory.CREATURE).canSpawnFarFromPlayer()
                    .sized(0.7f,1.8f).build("pirate"));

    public static final RegistryObject<EntityType<Flintlocker>> FLINTLOCKER = ENTITIES.register("flintlocker",
            () -> EntityType.Builder.of(Flintlocker::new, MobCategory.CREATURE).canSpawnFarFromPlayer()
                    .sized(0.7f,1.8f).build("flintlocker"));

    public static final RegistryObject<EntityType<Corrupt>> CORRUPT = ENTITIES.register("corrupt",
            () -> EntityType.Builder.of(Corrupt::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(1.7f,5.3f).build("corrupt"));

    public static final RegistryObject<EntityType<CorruptMinion>> CORRUPT_MINION = ENTITIES.register("corrupt_minion",
            () -> EntityType.Builder.of(CorruptMinion::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(1.7f,5.3f).build("corrupt_minion"));

    public static final RegistryObject<EntityType<CorruptAngel>> CORRUPT_ANGEL = ENTITIES.register("corrupt_angel",
            () -> EntityType.Builder.of(CorruptAngel::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(6.3f,6.3f).build("corrupt_angel"));

    public static final RegistryObject<EntityType<CorruptZombie>> CORRUPT_ZOMBIE = ENTITIES.register("corrupt_zombie",
            () -> EntityType.Builder.of(CorruptZombie::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(0.7f,1.8f).build("corrupt_zombie"));

    public static final RegistryObject<EntityType<Mimic>> MIMIC = ENTITIES.register("mimic",
            () -> EntityType.Builder.of(Mimic::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(1.0f,1.0f).build("mimic"));

    public static final RegistryObject<EntityType<LordOfCorruption>> LORD_OF_CORRUPTION = ENTITIES.register("lord_of_corruption",
            () -> EntityType.Builder.of(LordOfCorruption::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(6.7f,17.8f).build("lord_of_corruption"));

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

    public static final RegistryObject<EntityType<CorruptFire>> CORRUPT_FIRE = ENTITIES.register("corrupt_fire",
            () -> EntityType.Builder.<CorruptFire>of(CorruptFire::new, MobCategory.MISC).sized(1.7F, 1.7F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "corrupt_fire").toString()));

    public static final RegistryObject<EntityType<CorruptAngelFire>> CORRUPT_ANGEL_FIRE = ENTITIES.register("corrupt_angel_fire",
            () -> EntityType.Builder.<CorruptAngelFire>of(CorruptAngelFire::new, MobCategory.MISC).sized(6.7F, 6.7F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "corrupt_angel_fire").toString()));


    // Other
    public static final RegistryObject<EntityType<Cannon>> CANNON = ENTITIES.register("cannon",
            () -> EntityType.Builder.<Cannon>of(Cannon::new, MobCategory.CREATURE).sized(0.8F, 0.8F).fireImmune()
                    .build(new ResourceLocation(Frv.MOD_ID, "cannon").toString()));


    public static final RegistryObject<EntityType<Electricity>> ELECTRICITY = ENTITIES.register("electricity",
            () -> EntityType.Builder.of(Electricity::new, MobCategory.MISC).fireImmune()
                    .sized(0.1f,20.0f).fireImmune().build("electricity"));

    public static final RegistryObject<EntityType<CorruptBoard>> CORRUPT_BOARD = ENTITIES.register("corrupt_board",
            () -> EntityType.Builder.of(CorruptBoard::new, MobCategory.CREATURE).fireImmune()
                    .sized(0.9f,0.9f).fireImmune().build("corrupt_board"));


}

