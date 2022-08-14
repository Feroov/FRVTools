package com.feroov.frv.entities.tile;

import com.feroov.frv.entities.tile.gui.VCTableInventory;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.Optional;


public class VCTableOutputSlot extends Slot
{
    private final VCTableInventory vcTableInventory;
    private final Player player;
    private int removeCount;

    public VCTableOutputSlot(Player player, VCTableInventory gunTableInventory, int index, int x, int y)
    {
        super(gunTableInventory, index, x, y);
        this.player = player;
        this.vcTableInventory = gunTableInventory;
    }

    @Override
    public boolean mayPlace(ItemStack stack) { return false; }

    @Override
    public ItemStack remove(int amount)
    {
        if (this.hasItem()) { this.removeCount += Math.min(amount, this.getItem().getCount()); }
        return super.remove(amount);
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount)
    {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack)
    {
        stack.onCraftedBy(this.player.level, this.player, this.removeCount);
        this.removeCount = 0;
    }

    @Override
    public void onTake(Player player, ItemStack stack)
    {
        this.checkTakeAchievements(stack);
        Optional<VCTableRecipe> optionalGunTableRecipe = player.level.getRecipeManager().getRecipeFor(VCTableRecipe.Type.INSTANCE,
                vcTableInventory, player.level);

        if (optionalGunTableRecipe.isPresent())
        {
            VCTableRecipe gunTableRecipe = optionalGunTableRecipe.get();
            NonNullList<ItemStack> NonNullList = gunTableRecipe.getRemainingItems(vcTableInventory);

            for (int i = 0; i < NonNullList.size(); ++i)
            {
                ItemStack itemStack = this.vcTableInventory.getItem(i);
                ItemStack itemStack2 = NonNullList.get(i);
                if (!itemStack.isEmpty())
                {
                    this.vcTableInventory.removeItem(i, gunTableRecipe.countRequired(i));
                    itemStack = this.vcTableInventory.getItem(i);
                }

                if (!itemStack2.isEmpty())
                {
                    if (itemStack.isEmpty())
                    {
                        this.vcTableInventory.setItem(i, itemStack2);
                    }
                    else if (ItemStack.isSameIgnoreDurability(itemStack, itemStack2) && ItemStack.isSame(itemStack, itemStack2))
                    {
                        itemStack2.shrink(itemStack.getCount());
                        this.vcTableInventory.setItem(i, itemStack2);
                    }
                    else if (!this.player.getInventory().add(itemStack2))
                    {
                        this.player.drop(itemStack2, false);
                    }
                }
            }
        }
        MinecraftForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(player, stack, this.container));
        this.setChanged();
    }
}

