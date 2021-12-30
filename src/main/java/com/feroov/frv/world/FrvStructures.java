package com.feroov.frv.world;

import com.feroov.frv.Frv;
import com.feroov.frv.world.structure.CampsiteStructure;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class FrvStructures
{
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE
            = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Frv.MOD_ID);

    /**
     * StructureFeatureConfiguration( WHAT VALUE GOES HERE );
     * 1.) Stronghold, buried treasure, mineshaft 1/0
     * 2.) nether fossil 2/1
     * 3.) shipwreck 24/4
     * 4.) bastion, nether fortress 27/4
     * 5.) ocean monument 32/5
     * 6.) ocean ruin 20/8
     * 7.) village, igloo, pyramid, swamp hut, pillager outpost 32/8
     * 8.) end city 20/11
     * 9.) ruined portal 40/15
     * 10.) woodland mansion 80/20
     */

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CAMP_SITE =
            DEFERRED_REGISTRY_STRUCTURE.register("campsite", () -> (new CampsiteStructure(JigsawConfiguration.CODEC)));

    public static void setupStructures() {setupMapSpacingAndLand(CAMP_SITE.get(),
            new StructureFeatureConfiguration(20 , 8 , 1234567890), true);

    }

    public static <F extends StructureFeature<?>>
    void setupMapSpacingAndLand(F structure, StructureFeatureConfiguration structureFeatureConfiguration, boolean transformSurroundingLand)
    {

        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);


        if(transformSurroundingLand){
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureFeatureConfiguration)
                        .build();


        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();


            if(structureMap instanceof ImmutableMap){
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureFeatureConfiguration);
                settings.getValue().structureSettings().structureConfig = tempMap;
            }
            else{
                structureMap.put(structure, structureFeatureConfiguration);
            }
        });
    }
}
