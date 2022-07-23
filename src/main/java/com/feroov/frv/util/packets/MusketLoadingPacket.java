package com.feroov.frv.util.packets;

import com.feroov.frv.item.custom.ranged.Musket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MusketLoadingPacket
{

    public int slot;

    public MusketLoadingPacket(int slot) { this.slot = slot; }

    public MusketLoadingPacket(final FriendlyByteBuf packetBuffer) { this.slot = packetBuffer.readInt(); }

    public void encode(final FriendlyByteBuf packetBuffer) { packetBuffer.writeInt(this.slot); }

    public static void handle(MusketLoadingPacket packet, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            NetworkEvent.Context context = ctx.get();
            PacketListener handler = context.getNetworkManager().getPacketListener();
            if (handler instanceof ServerGamePacketListenerImpl)
            {
                ServerPlayer playerEntity = ((ServerGamePacketListenerImpl) handler).player;
                Musket.reload(playerEntity, InteractionHand.MAIN_HAND);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
