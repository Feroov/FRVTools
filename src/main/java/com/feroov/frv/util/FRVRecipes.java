package com.feroov.frv.util;

import com.feroov.frv.Frv;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FRVRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIAL = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Frv.MOD_ID);

    public static final RegistryObject<RecipeSerializer<?>> GUN_TABLE_RECIPE_SERIALIZER = SERIAL.register("vigorous_crafting_table",
            () -> new FRVRecipeSerializer());
}
