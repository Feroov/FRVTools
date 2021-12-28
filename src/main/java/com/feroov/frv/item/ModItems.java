package com.feroov.frv.item;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.item.custom.armors.AmethystArmorItem;
import com.feroov.frv.item.custom.items.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Frv.MOD_ID);
    /************************************(Misc/Unobtainable)************************************************/
    public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword",
            () -> new SwordItem(ModTiers.ADMIN, 0, 9996f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC)));

    /****** (Tab pics) *****/
    public static final RegistryObject<Item> CROAKER_PIC = ITEMS.register("croaker_pic",
            () -> new Item(new Item.Properties()));
    /***********************/

    /***********************************************************************************************/


    /************************************(MOBS EGGS)************************************************/
    public static final RegistryObject<ModSpawnEggItem> CROAKER_SPAWN_EGG = ITEMS.register("croaker_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.CROAKER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> HUNTER_SPAWN_EGG = ITEMS.register("hunter_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.HUNTER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> FEMALE_HUNTER_SPAWN_EGG = ITEMS.register("female_hunter_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.FEMALE_HUNTER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    /***********************************************************************************************/


    /************************************(DETECTORS)************************************************/
    public static final RegistryObject<Item> COAL_DETECTOR = ITEMS.register("coal_detector",
            () -> new CoalDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(50)));

    public static final RegistryObject<Item> IRON_DETECTOR = ITEMS.register("iron_detector",
            () -> new IronDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(100)));

    public static final RegistryObject<Item> GOLD_DETECTOR = ITEMS.register("gold_detector",
            () -> new GoldDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(145)));

    public static final RegistryObject<Item> REDSTONE_DETECTOR = ITEMS.register("redstone_detector",
            () -> new RedstoneDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(320)));

    public static final RegistryObject<Item> EMERALD_DETECTOR = ITEMS.register("emerald_detector",
            () -> new EmeraldDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(120)));

    public static final RegistryObject<Item> DIAMOND_DETECTOR = ITEMS.register("diamond_detector",
            () -> new DiamondDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(215)));

    public static final RegistryObject<Item> NETHERITE_DETECTOR = ITEMS.register("netherite_detector",
            () -> new NetheriteDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(50)));

    public static final RegistryObject<Item> COAL_DETECTOR_PRIME = ITEMS.register("coal_detector_prime",
            () -> new CoalDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(165)));

    public static final RegistryObject<Item> IRON_DETECTOR_PRIME = ITEMS.register("iron_detector_prime",
            () -> new IronDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(245)));

    public static final RegistryObject<Item> GOLD_DETECTOR_PRIME = ITEMS.register("gold_detector_prime",
            () -> new GoldDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(225)));

    public static final RegistryObject<Item> REDSTONE_DETECTOR_PRIME = ITEMS.register("redstone_detector_prime",
            () -> new RedstoneDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(565)));

    public static final RegistryObject<Item> EMERALD_DETECTOR_PRIME = ITEMS.register("emerald_detector_prime",
            () -> new EmeraldDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(265)));

    public static final RegistryObject<Item> DIAMOND_DETECTOR_PRIME = ITEMS.register("diamond_detector_prime",
            () -> new DiamondDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(385)));

    public static final RegistryObject<Item> NETHERITE_DETECTOR_PRIME = ITEMS.register("netherite_detector_prime",
            () -> new NetheriteDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(100)));

    public static final RegistryObject<Item> ALL_DETECTOR = ITEMS.register("all_detector",
            () -> new AllOreDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(200)));

    public static final RegistryObject<Item> ALL_DETECTOR_PRIME = ITEMS.register("all_detector_prime",
            () -> new AllOreDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(700))
            {
                @Override
                public boolean isFoil(ItemStack p_41453_)
                {
                    return true;
                }
            }
            );
    /***********************************************************************************************/

    /************************************(AMETHYST)************************************************/
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModTiers.AMETHYST, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe",
            () -> new PickaxeItem(ModTiers.AMETHYST, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
            () -> new AxeItem(ModTiers.AMETHYST, 6, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
            () -> new ShovelItem(ModTiers.AMETHYST, 2.5f, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe",
            () -> new HoeItem(ModTiers.AMETHYST, -1, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet",
            () -> new AmethystArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new ArmorItem(ModArmorMaterial.AMETHYST, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /************************************(QUARTZ)************************************************/
    public static final RegistryObject<Item> QUARTZ_SWORD = ITEMS.register("quartz_sword",
            () -> new SwordItem(ModTiers.QUARTZ, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_PICKAXE = ITEMS.register("quartz_pickaxe",
            () -> new PickaxeItem(ModTiers.QUARTZ, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_AXE = ITEMS.register("quartz_axe",
            () -> new AxeItem(ModTiers.QUARTZ, 6, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_SHOVEL = ITEMS.register("quartz_shovel",
            () -> new ShovelItem(ModTiers.QUARTZ, 2.5f, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_HOE = ITEMS.register("quartz_hoe",
            () -> new HoeItem(ModTiers.QUARTZ, -1, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_HELMET = ITEMS.register("quartz_helmet",
            () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_CHESTPLATE = ITEMS.register("quartz_chestplate",
            () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_LEGGINGS = ITEMS.register("quartz_leggings",
            () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> QUARTZ_BOOTS = ITEMS.register("quartz_boots",
            () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

/**
    @Override
    public boolean hurtEnemy(ItemStack p_41395_, LivingEntity p_41396_, LivingEntity p_41397_)
    {
        if(!p_41397_.level.isClientSide())
        {
            p_41396_.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 1));
        }
        return super.hurtEnemy(p_41395_, p_41396_, p_41397_);
    }**/





    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
