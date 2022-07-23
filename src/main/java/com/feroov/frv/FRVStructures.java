package com.feroov.frv;

import com.feroov.frv.world.gen.FRVStructuresMain;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FRVStructures
{
    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, Frv.MOD_ID);

    public static final RegistryObject<StructureType<FRVStructuresMain>> FRV_STRUCTURES = DEFERRED_REGISTRY_STRUCTURE.register("frv_structures", () -> typeConvert(FRVStructuresMain.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec)
    {
        return () -> codec;
    }
}