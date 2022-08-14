package com.feroov.frv.entities.tile;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.tile.gui.VCTableInventory;
import com.feroov.frv.util.FRVRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

public class VCTableRecipe implements Recipe<VCTableInventory>, Comparable<VCTableRecipe>
{

    public static ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(Frv.MOD_ID, "frvitems");
    public final ResourceLocation id;
    public final Pair<Ingredient, Integer>[] ingredients;
    public final ItemStack output;

    public VCTableRecipe(ResourceLocation id, Pair<Ingredient, Integer>[] ingredients, ItemStack output)
    {
        this.id = id;
        this.ingredients = ingredients;
        this.output = output;
    }

    @Override
    public boolean matches(VCTableInventory inv, Level world)
    {
        for (int i = 0; i < 4; i++)
        {
            ItemStack slotStack = inv.getItem(i);
            Pair<Ingredient, Integer> pair = ingredients[i];
            Ingredient ingredient = pair.getLeft();
            int count = pair.getRight();
            if (slotStack.getCount() < count || !(ingredient.test(slotStack)))
            {
                return false;
            }
        }
        return true;
    }

    public Ingredient getIngredientForSlot(int index) { return ingredients[index].getLeft(); }

    public int countRequired(int index) { return ingredients[index].getRight(); }

    @Override
    public ItemStack assemble(VCTableInventory inv) { return this.getResultItem().copy(); }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    public ItemStack getResultItem() { return output; }

    @Override
    public ResourceLocation getId() { return id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return FRVRecipes.GUN_TABLE_RECIPE_SERIALIZER.get(); }

    public static class Type implements RecipeType<VCTableRecipe>
    {
        public static final Type INSTANCE = new Type();
        public static final String ID = "vigorous_crafting_table";
    }

    @Override
    public RecipeType<?> getType() { return Type.INSTANCE; }

    @Override
    public int compareTo(VCTableRecipe o)
    {
        Item outputThis = getResultItem().getItem();
        Item outputOther = o.getResultItem().getItem();
        return ForgeRegistries.ITEMS.getKey(outputThis).compareTo(ForgeRegistries.ITEMS.getKey(outputOther));
    }
}
