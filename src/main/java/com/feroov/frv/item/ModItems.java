package com.feroov.frv.item;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import com.feroov.frv.item.custom.armors.*;
import com.feroov.frv.item.custom.items.*;
import com.feroov.frv.item.custom.ranged.Musket;
import com.feroov.frv.item.custom.ranged.MusketBullet;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Frv.MOD_ID);
    /********************************** (Misc/Unobtainable) *******************************************/
    public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword", () -> new SwordItem(ModTiers.ADMIN, 0, 9996f, new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC))
            {
                @Override
                public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
                {
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                    tooltip.add(Component.translatable("Banish every being off the screen").withStyle(ChatFormatting.RED));
                }});

    /* ***** (Tab pics) *****/
    public static final RegistryObject<Item> MOB_PIC = ITEMS.register("mob_pic", () -> new Item(new Item.Properties()));
    /***********************************************************************************************/


    /********************************** (Food) **************************************/
    public static final RegistryObject<Item> ALE = ITEMS.register("ale", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS).food(ModFoods.ALE).stacksTo(1))
            {
                @Override
                public SoundEvent getEatingSound()
                {
                    return SoundEvents.GENERIC_DRINK;
                }
            });
    /************************************************************************************/


    /********************************** (Materials) **************************************/
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> METEORITE_SHARDS = ITEMS.register("meteorite_shards", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> HARDENED_METEORITE = ITEMS.register("hardened_meteorite", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> METEORITE_INGOT = ITEMS.register("meteorite_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> HARD_FUNGUS = ITEMS.register("hard_fungus", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> HELLSTONE_INGOT = ITEMS.register("hellstone_ingot", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> ENDRIUM_GEM = ITEMS.register("endrium_gem", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)){ @Override public boolean isFoil(ItemStack p_41453_){ return true; }});
    public static final RegistryObject<Item> VOID_GEM = ITEMS.register("void_gem", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> CORRUPT_PIECE = ITEMS.register("corrupt_piece", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    public static final RegistryObject<Item> CORRUPT_ENERGY = ITEMS.register("corrupt_energy", () -> new Item(new Item.Properties().tab(ModItemGroup.FRV_TAB_BLOCKS_ITEMS)));
    /*************************************************************************************/

    /**********************************  (MOBS EGGS) **********************************************/
    public static final RegistryObject<ModSpawnEggItem> CROAKER_SPAWN_EGG = ITEMS.register("croaker_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CROAKER, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> HUNTER_SPAWN_EGG = ITEMS.register("hunter_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.HUNTER, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> FEMALE_HUNTER_SPAWN_EGG = ITEMS.register("female_hunter_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.FEMALE_HUNTER, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> LOST_PERSON_SPAWN_EGG = ITEMS.register("lost_person_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.LOST_PERSON, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> PIRATE_CAPTAIN_SPAWN_EGG = ITEMS.register("pirate_captain_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.PIRATE_CAPTAIN, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> PIRATE = ITEMS.register("pirate_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.PIRATE, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> FLINTLOCKER = ITEMS.register("flintlocker_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.FLINTLOCKER, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> CANNON = ITEMS.register("cannon_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CANNON, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> MIMIC = ITEMS.register("mimic_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.MIMIC, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> CORRUPT_BOARD = ITEMS.register("corrupt_board_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CORRUPT_BOARD, 0X087D62, 0X087A62, new Item.Properties().fireResistant().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> CORRUPT_ZOMBIE = ITEMS.register("corrupt_zombie_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CORRUPT_ZOMBIE, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> CORRUPT_ANGEL = ITEMS.register("corrupt_angel_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CORRUPT_ANGEL, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> CORRUPT = ITEMS.register("corrupt_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CORRUPT, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    public static final RegistryObject<ModSpawnEggItem> LORD_OF_CORRUPTION = ITEMS.register("lord_of_corruption_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.LORD_OF_CORRUPTION, 0X087D62, 0X087A62, new Item.Properties().fireResistant().tab(ModItemGroup.FRV_TAB_EGGS)));
    /***********************************************************************************************/


    /********************************** (DETECTORS) **********************************************/
    public static final RegistryObject<Item> COAL_DETECTOR = ITEMS.register("coal_detector", () -> new CoalDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(50)));
    public static final RegistryObject<Item> IRON_DETECTOR = ITEMS.register("iron_detector", () -> new IronDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(100)));
    public static final RegistryObject<Item> GOLD_DETECTOR = ITEMS.register("gold_detector", () -> new GoldDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(145)));
    public static final RegistryObject<Item> REDSTONE_DETECTOR = ITEMS.register("redstone_detector", () -> new RedstoneDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(320)));
    public static final RegistryObject<Item> LAPIS_DETECTOR = ITEMS.register("lapis_detector", () -> new LapisDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(320)));
    public static final RegistryObject<Item> EMERALD_DETECTOR = ITEMS.register("emerald_detector", () -> new EmeraldDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(250)));
    public static final RegistryObject<Item> DIAMOND_DETECTOR = ITEMS.register("diamond_detector", () -> new DiamondDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(300)));
    public static final RegistryObject<Item> NETHERITE_DETECTOR = ITEMS.register("netherite_detector", () -> new NetheriteDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(300)));
    public static final RegistryObject<Item> COAL_DETECTOR_PRIME = ITEMS.register("coal_detector_prime", () -> new CoalDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(165)));
    public static final RegistryObject<Item> IRON_DETECTOR_PRIME = ITEMS.register("iron_detector_prime", () -> new IronDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(245)));
    public static final RegistryObject<Item> GOLD_DETECTOR_PRIME = ITEMS.register("gold_detector_prime", () -> new GoldDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(225)));
    public static final RegistryObject<Item> REDSTONE_DETECTOR_PRIME = ITEMS.register("redstone_detector_prime", () -> new RedstoneDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(565)));
    public static final RegistryObject<Item> LAPIS_DETECTOR_PRIME = ITEMS.register("lapis_detector_prime", () -> new LapisDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(565)));
    public static final RegistryObject<Item> EMERALD_DETECTOR_PRIME = ITEMS.register("emerald_detector_prime", () -> new EmeraldDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(500)));
    public static final RegistryObject<Item> DIAMOND_DETECTOR_PRIME = ITEMS.register("diamond_detector_prime", () -> new DiamondDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(600)));
    public static final RegistryObject<Item> NETHERITE_DETECTOR_PRIME = ITEMS.register("netherite_detector_prime", () -> new NetheriteDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(600)));
    public static final RegistryObject<Item> ALL_DETECTOR = ITEMS.register("all_detector", () -> new AllOreDetector(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(400)));
    public static final RegistryObject<Item> ALL_DETECTOR_PRIME = ITEMS.register("all_detector_prime",
            () -> new AllOreDetectorPrime(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC).durability(900))
            {
                @Override
                public boolean isFoil(ItemStack p_41453_)
                {
                    return true;
                }
            });

    // Ranged stuff
    public static final RegistryObject<Item> MUSKET = ITEMS.register("musket", () -> new Musket());
    public static final RegistryObject<Item> MUSKET_BULLET = ITEMS.register("musket_bullet", () -> new MusketBullet(new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC), 1));
    /***********************************************************************************************/

    /********************************** (COPPER) **********************************************/
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", () -> new SwordItem(ModTiers.COPPER, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModTiers.COPPER, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", () -> new AxeItem(ModTiers.COPPER, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", () -> new ShovelItem(ModTiers.COPPER, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", () -> new HoeItem(ModTiers.COPPER, -1, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /********************************** (Tin) **********************************************/
    public static final RegistryObject<Item> TIN_SWORD = ITEMS.register("tin_sword", () -> new SwordItem(ModTiers.TIN, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_PICKAXE = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(ModTiers.TIN, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_AXE = ITEMS.register("tin_axe", () -> new AxeItem(ModTiers.TIN, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_SHOVEL = ITEMS.register("tin_shovel", () -> new ShovelItem(ModTiers.TIN, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_HOE = ITEMS.register("tin_hoe", () -> new HoeItem(ModTiers.TIN, -1, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_HELMET = ITEMS.register("tin_helmet", () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_CHESTPLATE = ITEMS.register("tin_chestplate", () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_LEGGINGS = ITEMS.register("tin_leggings", () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> TIN_BOOTS = ITEMS.register("tin_boots", () -> new ArmorItem(ModArmorMaterial.TIN, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /********************************** (QUARTZ) **********************************************/
    public static final RegistryObject<Item> QUARTZ_SWORD = ITEMS.register("quartz_sword", () -> new SwordItem(ModTiers.QUARTZ, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_PICKAXE = ITEMS.register("quartz_pickaxe", () -> new PickaxeItem(ModTiers.QUARTZ, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_AXE = ITEMS.register("quartz_axe", () -> new AxeItem(ModTiers.QUARTZ, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_SHOVEL = ITEMS.register("quartz_shovel", () -> new ShovelItem(ModTiers.QUARTZ, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_HOE = ITEMS.register("quartz_hoe", () -> new HoeItem(ModTiers.QUARTZ, -1, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_HELMET = ITEMS.register("quartz_helmet", () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_CHESTPLATE = ITEMS.register("quartz_chestplate", () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_LEGGINGS = ITEMS.register("quartz_leggings", () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> QUARTZ_BOOTS = ITEMS.register("quartz_boots", () -> new ArmorItem(ModArmorMaterial.QUARTZ, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /*************************************  Lead  ************************************************/
    public static final RegistryObject<Item> LEAD_SWORD = ITEMS.register("lead_sword", () -> new SwordItem(ModTiers.LEAD, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_PICKAXE = ITEMS.register("lead_pickaxe", () -> new PickaxeItem(ModTiers.LEAD, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_AXE = ITEMS.register("lead_axe", () -> new AxeItem(ModTiers.LEAD, 6, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_SHOVEL= ITEMS.register("lead_shovel", () -> new ShovelItem(ModTiers.LEAD, 0, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_HOE = ITEMS.register("lead_hoe", () -> new HoeItem(ModTiers.LEAD, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_HELMET = ITEMS.register("lead_helmet", () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_CHESTPLATE = ITEMS.register("lead_chestplate", () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_LEGGINGS = ITEMS.register("lead_leggings", () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> LEAD_BOOTS = ITEMS.register("lead_boots", () -> new ArmorItem(ModArmorMaterial.LEAD, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));

    /*************************************  Silver  ************************************************/
    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword", () -> new SwordItem(ModTiers.SILVER, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () -> new PickaxeItem(ModTiers.SILVER, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe", () -> new AxeItem(ModTiers.SILVER, 6, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_SHOVEL= ITEMS.register("silver_shovel", () -> new ShovelItem(ModTiers.SILVER, 0, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe", () -> new HoeItem(ModTiers.SILVER, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet", () -> new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate", () -> new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings", () -> new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots", () -> new ArmorItem(ModArmorMaterial.SILVER, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /********************************** (PLATINUM) **********************************************/
    public static final RegistryObject<Item> PLATINUM_SWORD = ITEMS.register("platinum_sword", () -> new SwordItem(ModTiers.PLATINUM, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = ITEMS.register("platinum_pickaxe", () -> new PickaxeItem(ModTiers.PLATINUM, 3, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_AXE = ITEMS.register("platinum_axe", () -> new AxeItem(ModTiers.PLATINUM, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_SHOVEL = ITEMS.register("platinum_shovel", () -> new ShovelItem(ModTiers.PLATINUM, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_HOE = ITEMS.register("platinum_hoe", () -> new HoeItem(ModTiers.PLATINUM, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_HELMET = ITEMS.register("platinum_helmet", () -> new ArmorItem(ModArmorMaterial.PLATINUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate", () -> new ArmorItem(ModArmorMaterial.PLATINUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings", () -> new ArmorItem(ModArmorMaterial.PLATINUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> PLATINUM_BOOTS = ITEMS.register("platinum_boots", () -> new ArmorItem(ModArmorMaterial.PLATINUM, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /**********************************  (AMETHYST)  **********************************************/
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword", () -> new SwordItem(ModTiers.AMETHYST, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_PICKAXE = ITEMS.register("amethyst_pickaxe", () -> new PickaxeItem(ModTiers.AMETHYST, 3, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe", () -> new AxeItem(ModTiers.AMETHYST, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel", () -> new ShovelItem(ModTiers.AMETHYST, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_HOE = ITEMS.register("amethyst_hoe", () -> new HoeItem(ModTiers.AMETHYST, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () -> new AmethystArmor(ModArmorMaterial.AMETHYST, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB)));
    /***********************************************************************************************/

    /**********************************  (METEORITE)  **********************************************/
    public static final RegistryObject<Item> METEORITE_SWORD = ITEMS.register("meteorite_sword", () -> new SwordItem(ModTiers.METEORITE, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_PICKAXE = ITEMS.register("meteorite_pickaxe", () -> new PickaxeItem(ModTiers.METEORITE, 2, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_AXE = ITEMS.register("meteorite_axe", () -> new AxeItem(ModTiers.METEORITE, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_SHOVEL = ITEMS.register("meteorite_shovel", () -> new ShovelItem(ModTiers.METEORITE, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_HOE = ITEMS.register("meteorite_hoe", () -> new HoeItem(ModTiers.METEORITE, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_HELMET = ITEMS.register("meteorite_helmet", () -> new MeteoriteArmor(ModArmorMaterial.METEORITE, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_CHESTPLATE = ITEMS.register("meteorite_chestplate", () -> new MeteoriteArmor(ModArmorMaterial.METEORITE, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_LEGGINGS = ITEMS.register("meteorite_leggings", () -> new MeteoriteArmor(ModArmorMaterial.METEORITE, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> METEORITE_BOOTS = ITEMS.register("meteorite_boots", () -> new MeteoriteArmor(ModArmorMaterial.METEORITE, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    /***********************************************************************************************/

    /**********************************  (FUNGHONITE)  **********************************************/
    public static final RegistryObject<Item> FUNGHONITE_SWORD = ITEMS.register("funghonite_sword", () -> new SwordItem(ModTiers.FUNGHONITE, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_PICKAXE = ITEMS.register("funghonite_pickaxe", () -> new PickaxeItem(ModTiers.FUNGHONITE, 1, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_AXE = ITEMS.register("funghonite_axe", () -> new AxeItem(ModTiers.FUNGHONITE, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_SHOVEL = ITEMS.register("funghonite_shovel", () -> new ShovelItem(ModTiers.FUNGHONITE, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_HOE = ITEMS.register("funghonite_hoe", () -> new HoeItem(ModTiers.FUNGHONITE, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_HELMET = ITEMS.register("funghonite_helmet", () -> new FunghoniteArmor(ModArmorMaterial.FUNGHONITE, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_CHESTPLATE = ITEMS.register("funghonite_chestplate", () -> new FunghoniteArmor(ModArmorMaterial.FUNGHONITE, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_LEGGINGS = ITEMS.register("funghonite_leggings", () -> new FunghoniteArmor(ModArmorMaterial.FUNGHONITE, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> FUNGHONITE_BOOTS = ITEMS.register("funghonite_boots", () -> new FunghoniteArmor(ModArmorMaterial.FUNGHONITE, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    /***********************************************************************************************/

    /**********************************  (HELLSTONE)  **********************************************/
    public static final RegistryObject<Item> HELLSTONE_SWORD = ITEMS.register("hellstone_sword", () -> new SwordItem(ModTiers.HELLSTONE, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()) { @Override public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) { livingEntity.setSecondsOnFire(30); return super.hurtEnemy(itemStack, livingEntity, livingEntity1); }});
    public static final RegistryObject<Item> HELLSTONE_PICKAXE = ITEMS.register("hellstone_pickaxe", () -> new PickaxeItem(ModTiers.HELLSTONE, 1, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_AXE = ITEMS.register("hellstone_axe", () -> new AxeItem(ModTiers.HELLSTONE, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_SHOVEL = ITEMS.register("hellstone_shovel", () -> new ShovelItem(ModTiers.HELLSTONE, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_HOE = ITEMS.register("hellstone_hoe", () -> new HoeItem(ModTiers.HELLSTONE, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_HELMET = ITEMS.register("hellstone_helmet", () -> new HellstoneArmor(ModArmorMaterial.HELLSTONE, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_CHESTPLATE = ITEMS.register("hellstone_chestplate", () -> new HellstoneArmor(ModArmorMaterial.HELLSTONE, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_LEGGINGS = ITEMS.register("hellstone_leggings", () -> new HellstoneArmor(ModArmorMaterial.HELLSTONE, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> HELLSTONE_BOOTS = ITEMS.register("hellstone_boots", () -> new HellstoneArmor(ModArmorMaterial.HELLSTONE, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    /***********************************************************************************************/

    /**********************************  (ENDRIUM)  **********************************************/
    public static final RegistryObject<Item> ENDRIUM_SWORD = ITEMS.register("endrium_sword", () -> new SwordItem(ModTiers.ENDRIUM, 3, -2.4f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()) { @Override  public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1) { livingEntity.setSecondsOnFire(30); if(!livingEntity.level.isClientSide()) { ServerLevel world = (ServerLevel) livingEntity.level; BlockPos position = livingEntity.blockPosition(); EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);EntityType.LIGHTNING_BOLT.spawn(world, null, null, position, MobSpawnType.TRIGGERED, true, true);} return super.hurtEnemy(itemStack, livingEntity, livingEntity1); }});
    public static final RegistryObject<Item> ENDRIUM_PICKAXE = ITEMS.register("endrium_pickaxe", () -> new PickaxeItem(ModTiers.ENDRIUM, 1, -2.8f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_AXE = ITEMS.register("endrium_axe", () -> new AxeItem(ModTiers.ENDRIUM, 6, -3f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_SHOVEL = ITEMS.register("endrium_shovel", () -> new ShovelItem(ModTiers.ENDRIUM, 0, -3.1f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_HOE = ITEMS.register("endrium_hoe", () -> new HoeItem(ModTiers.ENDRIUM, 0, 0f, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_HELMET = ITEMS.register("endrium_helmet", () -> new EndriumArmor(ModArmorMaterial.ENDRIUM, EquipmentSlot.HEAD, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_CHESTPLATE = ITEMS.register("endrium_chestplate", () -> new EndriumArmor(ModArmorMaterial.ENDRIUM, EquipmentSlot.CHEST, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_LEGGINGS = ITEMS.register("endrium_leggings", () -> new EndriumArmor(ModArmorMaterial.ENDRIUM, EquipmentSlot.LEGS, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    public static final RegistryObject<Item> ENDRIUM_BOOTS = ITEMS.register("endrium_boots", () -> new EndriumArmor(ModArmorMaterial.ENDRIUM, EquipmentSlot.FEET, new Item.Properties().tab(ModItemGroup.FRV_TAB).fireResistant()));
    /***********************************************************************************************/




//    @Override
//    public boolean hurtEnemy(ItemStack p_41395_, LivingEntity p_41396_, LivingEntity p_41397_)
//    {
//        if(!p_41397_.level.isClientSide())
//        {
//            p_41396_.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 1));
//        }
//        return super.hurtEnemy(p_41395_, p_41396_, p_41397_);
//    }

//@Override
//public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity1)
//{
//    livingEntity.setSecondsOnFire(30);
//    if(!livingEntity.level.isClientSide())
//    {
//        ServerLevel world = (ServerLevel) livingEntity.level;
//        BlockPos position = livingEntity.blockPosition();
//
//        EntityType.LIGHTNING_BOLT.spawn(world, null, null, position,
//                MobSpawnType.TRIGGERED, true, true);
//
//        EntityType.LIGHTNING_BOLT.spawn(world, null, null, position,
//                MobSpawnType.TRIGGERED, true, true);
//
//        EntityType.LIGHTNING_BOLT.spawn(world, null, null, position,
//                MobSpawnType.TRIGGERED, true, true);
//
//        ModEntityTypes.ELECTRICITY.get().spawn(world, null, null, position,
//                MobSpawnType.TRIGGERED, true, true);
//
//    }
//    return super.hurtEnemy(itemStack, livingEntity, livingEntity1);
//}
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
