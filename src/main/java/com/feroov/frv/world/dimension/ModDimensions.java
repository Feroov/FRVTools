package com.feroov.frv.world.dimension;


import com.feroov.frv.Frv;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions
{
    public static final ResourceKey<Level> KJDIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Frv.MOD_ID, "kjdim"));
    public static final ResourceKey<DimensionType> KJDIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, KJDIM_KEY.registry());

    public static void register() {
        System.out.println("Registering ModDimensions for " + Frv.MOD_ID);
    }
}