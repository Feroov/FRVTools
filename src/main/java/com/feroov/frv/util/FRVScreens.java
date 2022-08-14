package com.feroov.frv.util;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.tile.gui.VCTableScreenHandler;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FRVScreens
{

    public static final DeferredRegister<MenuType<?>> CONTAIN = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Frv.MOD_ID);

    public static final RegistryObject<MenuType<VCTableScreenHandler>> SCREEN_HANDLER_TYPE = CONTAIN
            .register("vigorous_crafting_table_gui", () -> new MenuType<>(VCTableScreenHandler::new));
}
