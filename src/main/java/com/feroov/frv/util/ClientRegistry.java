package com.feroov.frv.util;

import org.apache.commons.lang3.ArrayUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

public class ClientRegistry
{
    public static synchronized void registerKeyBinding(KeyMapping key)
    {
        Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, key);
    }
}
