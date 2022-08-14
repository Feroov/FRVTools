package com.feroov.frv.util.packets;

import com.feroov.frv.entities.tile.gui.VCTableScreenHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FrvCraftingPacket
{
    private int index;

    public FrvCraftingPacket(final FriendlyByteBuf packetBuffer) {
        this.index = packetBuffer.readInt();
    }

    public FrvCraftingPacket(int index) {
        this.index = index;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(index);
    }

    public void handle(Supplier<NetworkEvent.Context> sup) {
        final NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            NetworkEvent.Context context = sup.get();
            PacketListener handler = context.getNetworkManager().getPacketListener();
            if (handler instanceof ServerGamePacketListenerImpl) {
                ServerPlayer playerEntity = ((ServerGamePacketListenerImpl) handler).player;
                AbstractContainerMenu container = playerEntity.containerMenu;
                if (container instanceof VCTableScreenHandler) {
                    VCTableScreenHandler gunTableScreenHandler = (VCTableScreenHandler) container;
                    gunTableScreenHandler.setRecipeIndex(index);
                    gunTableScreenHandler.switchTo(index);
                }
            }
        });
        ctx.setPacketHandled(true);
    }
}
