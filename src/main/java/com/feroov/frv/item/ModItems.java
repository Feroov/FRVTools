package com.feroov.frv.item;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.item.custom.armors.AmethystArmor;
import com.feroov.frv.item.custom.items.*;
import com.feroov.frv.item.custom.ranged.Musket;
import com.feroov.frv.item.custom.ranged.MusketBullet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Frv.MOD_ID);
    /********************************** (Misc/Unobtainable) *******************************************/
    public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword",
            () -> new SwordItem(ModTiers.ADMIN, 0, 9996f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC)));

    /* ***** (Tab pics) *****/
    public static final RegistryObject<Item> CROAKER_PIC = ITEMS.register("croaker_pic",
            () -> new Item(new Item.Properties()));
    /* ********************* */
    /***********************************************************************************************/

    /********************************** (Materials) **************************************/
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    /*************************************************************************************/

    /********************************** (Food) **************************************/

    public static final RegistryObject<Item> ALE = ITEMS.register("ale",
            () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS).food(ModFoods.ALE).stacksTo(1))
            {
                @Override
                public SoundEvent getEatingSound()
                {
                    return SoundEvents.GENERIC_DRINK;
                }
            });

    /************************************************************************************/


    /**********************************  (MOBS EGGS) **********************************************/
    public static final RegistryObject<ModSpawnEggItem> CROAKER_SPAWN_EGG = ITEMS.register("croaker_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.CROAKER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> HUNTER_SPAWN_EGG = ITEMS.register("hunter_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.HUNTER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> FEMALE_HUNTER_SPAWN_EGG = ITEMS.register("female_hunter_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.FEMALE_HUNTER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> PIRATE_CAPTAIN_SPAWN_EGG = ITEMS.register("pirate_captain_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.PIRATE_CAPTAIN, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> CANNON = ITEMS.register("cannon_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.CANNON, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> PIRATE = ITEMS.register("pirate_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.PIRATE, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> FLINTLOCKER = ITEMS.register("flintlocker_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.FLINTLOCKER, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));

    public static final RegistryObject<ModSpawnEggItem> CORRUPT = ITEMS.register("corrupt_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.CORRUPT, 0X087D62, 0X087A62,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    /***********************************************************************************************/


    /********************************** (DETECTORS) **********************************************/
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

    // Ranged stuff
    public static final RegistryObject<Item> MUSKET = ITEMS.register("musket", () -> new Musket());

    public static final RegistryObject<Item> MUSKET_BULLET = ITEMS.register("musket_bullet",
            () -> new MusketBullet(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC), 1));
    /***********************************************************************************************/

    /********************************** (COPPER) **********************************************/
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModTiers.COPPER, 3, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe",
            () -> new PickaxeItem(ModTiers.COPPER, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTiers.COPPER, 6, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER, 2.5f, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER, -1, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /********************************** (Tin) **********************************************/
    public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword",
            () -> new SwordItem(ModTiers.TIN, 3, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe",
            () -> new PickaxeItem(ModTiers.TIN, 2, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe",
            () -> new AxeItem(ModTiers.TIN, 6, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel",
            () -> new ShovelItem(ModTiers.TIN, 2.5f, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_HOE = ITEMS.register("tin_hoe",
            () -> new HoeItem(ModTiers.TIN, -1, 3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_HELMET = ITEMS.register("tin_helmet",
            () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_CHESTPLATE = ITEMS.register("tin_chestplate",
            () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_LEGGINGS = ITEMS.register("tin_leggings",
            () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> TIN_BOOTS = ITEMS.register("tin_boots",
            () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /********************************** (QUARTZ) **********************************************/
    public static final RegistryObject<Item> QUARTZ_SWORD = ITEMS.register("quartz_sword",
            () -> new SwordItem(ModTiers.QUARTZ, 3, 3f,
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

    /*************************************  Lead  ************************************************/
    public static final RegistryObject<Item> LEAD_SWORD = ITEMS.register("lead_sword",
            () -> new SwordItem(ModTiers.LEAD, 3, -2.4f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_PICKAXE = ITEMS.register("lead_pickaxe",
            () -> new PickaxeItem(ModTiers.LEAD, 0, -2.8f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_SHOVEL= ITEMS.register("lead_shovel",
            () -> new ShovelItem(ModTiers.LEAD, 0.1f, -3f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_AXE = ITEMS.register("lead_axe",
            () -> new AxeItem(ModTiers.LEAD, 5, -3.1f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_HOE = ITEMS.register("lead_hoe",
            () -> new HoeItem(ModTiers.LEAD, -3, 0f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_HELMET = ITEMS.register("lead_helmet",
            () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_CHESTPLATE = ITEMS.register("lead_chestplate",
            () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_LEGGINGS = ITEMS.register("lead_leggings",
            () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> LEAD_BOOTS = ITEMS.register("lead_boots",
            () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    
    /**********************************  (AMETHYST)  **********************************************/
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModTiers.AMETHYST, 3, 3f,
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
            () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate",
            () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings",
            () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots",
            () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.FEET,
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
