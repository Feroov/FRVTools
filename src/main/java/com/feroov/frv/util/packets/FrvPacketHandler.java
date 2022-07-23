package com.feroov.frv.util.packets;

import com.feroov.frv.Frv;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class FrvPacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    private static int channel_id = 0;

    public static final SimpleChannel MUSKET = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Frv.MOD_ID, "musket"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);


    public static void register() {

        MUSKET.registerMessage(channel_id++, MusketLoadingPacket.class, MusketLoadingPacket::encode,
                MusketLoadingPacket::new, MusketLoadingPacket::handle);
    }
}
