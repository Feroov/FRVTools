package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.passive.Croaker;
import com.feroov.frv.entities.passive.FemaleHunter;
import com.feroov.frv.entities.passive.Hunter;
import com.feroov.frv.entities.projectiles.Melee;
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

    /** Misc **/
    public static final RegistryObject<EntityType<Melee>> MELEE = ENTITIES.register("melee",
            () -> EntityType.Builder.<Melee>of(Melee::new, MobCategory.MISC).sized(0.5F, 0.5F)
                    .clientTrackingRange(9).build(new ResourceLocation(Frv.MOD_ID, "melee").toString()));


}

