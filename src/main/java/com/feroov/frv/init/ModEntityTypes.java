package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.entities.hostile.*;
import com.feroov.frv.entities.misc.*;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.passive.LostPerson;
import com.feroov.frv.entities.projectiles.*;
import com.feroov.frv.entities.tile.VCTEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
    private ModEntityTypes(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Frv.MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> TILE_TYPES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, Frv.MOD_ID);

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

    public static final RegistryObject<EntityType<LostSoul>> LOST_SOUL = ENTITIES.register("lost_soul",
            () -> EntityType.Builder.of(LostSoul::new, MobCategory.CREATURE).canSpawnFarFromPlayer().fireImmune()
                    .sized(0.7f,1.8f).build("lost_soul"));

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

    public static final RegistryObject<EntityType<NineMMBullet>> NINE_MM_BULLET = ENTITIES.register("nine_mm_bullet",
            () -> EntityType.Builder.<NineMMBullet>of(NineMMBullet::new, MobCategory.MISC).sized(0.4F, 0.4F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "nine_mm_bullet").toString()));

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

    public static final RegistryObject<EntityType<Explosion>> EXPLOSION = ENTITIES.register("explosion",
            () -> EntityType.Builder.of(Explosion::new, MobCategory.MISC).fireImmune()
                    .sized(15.1f,3.0f).fireImmune().build("explosion"));

    public static final RegistryObject<EntityType<CorruptExplosion>> CORRUPT_EXPLOSION = ENTITIES.register("corrupt_explosion",
            () -> EntityType.Builder.of(CorruptExplosion::new, MobCategory.MISC).fireImmune()
                    .sized(15.1f,3.0f).fireImmune().build("corrupt_explosion"));

    public static final RegistryObject<EntityType<TheSword>> THE_SWORD = ENTITIES.register("the_sword",
            () -> EntityType.Builder.of(TheSword::new, MobCategory.MISC).fireImmune()
                    .sized(0.1f,1.0f).fireImmune().build("the_sword"));

    public static final RegistryObject<EntityType<CorruptBoard>> CORRUPT_BOARD = ENTITIES.register("corrupt_board",
            () -> EntityType.Builder.of(CorruptBoard::new, MobCategory.CREATURE).fireImmune()
                    .sized(0.9f,0.9f).fireImmune().build("corrupt_board"));

    // TNTS
    public static final RegistryObject<EntityType<PrimedMeteorite>> PRIMED_METEORITE = ENTITIES.register("primed_meteorite",
            () -> EntityType.Builder.<PrimedMeteorite>of(PrimedMeteorite::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_meteorite"));

    public static final RegistryObject<EntityType<PrimedFunghonite>> PRIMED_FUNGHONITE = ENTITIES.register("primed_funghonite",
            () -> EntityType.Builder.<PrimedFunghonite>of(PrimedFunghonite::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_funghonite"));

    public static final RegistryObject<EntityType<PrimedHellstone>> PRIMED_HELLSTONE = ENTITIES.register("primed_hellstone",
            () -> EntityType.Builder.<PrimedHellstone>of(PrimedHellstone::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_hellstone"));

    public static final RegistryObject<EntityType<PrimedEndrium>> PRIMED_ENDRIUM = ENTITIES.register("primed_endrium",
            () -> EntityType.Builder.<PrimedEndrium>of(PrimedEndrium::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_endrium"));

    public static final RegistryObject<EntityType<PrimedVoid>> PRIMED_VOID = ENTITIES.register("primed_void",
            () -> EntityType.Builder.<PrimedVoid>of(PrimedVoid::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_void"));

    public static final RegistryObject<EntityType<PrimedCorrupt>> PRIMED_CORRUPT = ENTITIES.register("primed_corrupt",
            () -> EntityType.Builder.<PrimedCorrupt>of(PrimedCorrupt::new, MobCategory.MISC).fireImmune()
                    .sized(0.98f,0.98f).fireImmune().clientTrackingRange(10).updateInterval(10).build("primed_corrupt"));




    // Tile Entities
    public static final RegistryObject<BlockEntityType<VCTEntity>> VCT_ENTITY = TILE_TYPES.register(
            "vctable", () -> BlockEntityType.Builder.of(VCTEntity::new, ModBlocks.VIGOROUS_CRAFTING_TABLE.get()).build(null));





    public static void registerAdditionalEntityInformation() { registerEntitySpawnRestrictions(); }

    private static void registerEntitySpawnRestrictions()
    {
        SpawnPlacements.register(LOST_SOUL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }
}

