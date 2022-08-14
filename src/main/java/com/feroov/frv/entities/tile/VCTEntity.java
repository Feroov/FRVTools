package com.feroov.frv.entities.tile;

import com.feroov.frv.entities.tile.gui.VCTableScreenHandler;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VCTEntity extends BlockEntity implements ImplementedInventory, MenuProvider
{

    private final NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);

    public VCTEntity(BlockPos pos, BlockState state) { super(ModEntityTypes.VCT_ENTITY.get(), pos, state); }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player)
    {
        return new VCTableScreenHandler(syncId, inventory, ContainerLevelAccess.create(level, worldPosition));
    }

    @Override
    public Component getDisplayName()
    {
        return Component.translatable("block.frv.vigorous_crafting_table_name");
    }

    @Override
    public NonNullList<ItemStack> getItems() { return items; }

    @Override
    public void load(CompoundTag tag) { super.load(tag); ContainerHelper.loadAllItems(tag, items); }
}

