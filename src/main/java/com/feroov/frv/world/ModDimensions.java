package com.feroov.frv.world;

import com.feroov.frv.Frv;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions
{
    public static final ResourceKey<Level> CORRUPT_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Frv.MOD_ID, "the_matrix"));
    public static final ResourceKey<DimensionType> CORRUPT_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, CORRUPT_KEY.registry());

    public static final ResourceKey<Level> VOID_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Frv.MOD_ID, "the_void"));
    public static final ResourceKey<DimensionType> VOID_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, VOID_KEY.registry());

    public static void register() {
        System.out.println("Registering ModDimensions for " + Frv.MOD_ID);
    }
}
