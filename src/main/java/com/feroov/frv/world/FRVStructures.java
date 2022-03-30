package com.feroov.frv.world;

import com.feroov.frv.Frv;
import com.feroov.frv.world.structure.Campsite;
import com.feroov.frv.world.structure.Corruption;
import com.feroov.frv.world.structure.Crater;
import com.feroov.frv.world.structure.PirateShip;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FRVStructures
{

    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Frv.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> CAMPSITE = DEFERRED_REGISTRY_STRUCTURE.register("camp_site", Campsite::new);
    public static final RegistryObject<StructureFeature<?>> PIRATESHIP = DEFERRED_REGISTRY_STRUCTURE.register("pirate_ship", PirateShip::new);
    public static final RegistryObject<StructureFeature<?>> CORRUPTION = DEFERRED_REGISTRY_STRUCTURE.register("corruption", Corruption::new);
    public static final RegistryObject<StructureFeature<?>> CRATER = DEFERRED_REGISTRY_STRUCTURE.register("crater", Crater::new);

    public static void register(IEventBus eventBus){ DEFERRED_REGISTRY_STRUCTURE.register(eventBus); }
}
