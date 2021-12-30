package com.feroov.frv.world;

import com.feroov.frv.Frv;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.PlainVillagePools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class FrvConfiguredStructures
{
    public static ConfiguredStructureFeature<?, ?> CONFIGURED_CAMP_SITE = FrvStructures.CAMP_SITE.get()
            .configured(new JigsawConfiguration(() -> PlainVillagePools.START, 0));

    public static void registerConfiguredStructures() {
        Registry<ConfiguredStructureFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(Frv.MOD_ID, "configured_camp_site"), CONFIGURED_CAMP_SITE);
    }
}
