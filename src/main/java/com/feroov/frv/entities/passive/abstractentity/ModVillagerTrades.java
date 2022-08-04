package com.feroov.frv.entities.passive.abstractentity;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModVillagerTrades 
{
    public static final Int2ObjectMap<ModVillagerTrades.ItemListing[]> CROAKER_TRADES = toIntMap(ImmutableMap.of(
            1, new ModVillagerTrades.ItemListing[]
                    {
                            new ModVillagerTrades.EmeraldForItems(Items.COAL, 12, 10, 5),
                            new ModVillagerTrades.ItemsForEmeralds(Items.APPLE, 1, 20, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.IRON_INGOT, 1, 8, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.OAK_LOG, 1, 15, 5,1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.AMETHYST_SHARD, 13, 1, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.ARROW, 1, 23, 5,3),
                            new ModVillagerTrades.ItemsForEmeralds(Items.IRON_INGOT, 20, 45, 2, 1),
                            new ModVillagerTrades.ItemsForDiamond(Items.SPIDER_EYE, 1, 12, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.STRING, 1, 16, 5,2),
                            new ModVillagerTrades.ItemsForEmeralds(Items.GOLD_BLOCK, 1, 1, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.COOKED_BEEF, 1, 3, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.GOLDEN_CARROT, 1, 2, 5, 1),
                            new ModVillagerTrades.ItemsForDiamond(Items.PHANTOM_MEMBRANE, 1, 2, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.FERMENTED_SPIDER_EYE, 1, 4, 5, 1),
                            new ModVillagerTrades.ItemsForDiamond(Items.COOKED_CHICKEN, 1, 4, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.COOKED_COD, 1, 7, 5, 1),
                            new ModVillagerTrades.ItemsForDiamond(Items.COOKED_RABBIT, 1, 3, 5, 1),
                            new ModVillagerTrades.ItemsForEmeralds(Items.GLASS, 1, 13, 5, 1),
                            new ModVillagerTrades.EnchantBookForEmeralds(1),
                            new ModVillagerTrades.EnchantBookForEmeralds(1),
                            new ModVillagerTrades.EnchantBookForEmeralds(1)
                    }));

    public static final Int2ObjectMap<ModVillagerTrades.ItemListing[]> HUNTER_TRADES = toIntMap(ImmutableMap.of(
            1, new ModVillagerTrades.ItemListing[]
                    {
                            new ModVillagerTrades.WoodForItems(Items.STRING, 30, 13, 5, 1),
                            new ModVillagerTrades.WoodForItems(Items.COAL, 20, 20, 5, 1),
                            new ModVillagerTrades.WoodForItems(Items.SWEET_BERRIES, 10, 12, 5, 1),
                            new ModVillagerTrades.WoodForItems(Items.TORCH, 13, 26, 5, 1),
                            new ModVillagerTrades.WoodForItems(Items.LEATHER, 12, 16, 5, 1),
                            new ModVillagerTrades.WoodForItems(Items.IRON_INGOT, 23, 10, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.COD, 2, 6, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.SALMON, 3, 8, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.CHICKEN, 1, 4, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.RABBIT_STEW, 2, 4, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.SUSPICIOUS_STEW, 2, 1, 5, 1),
                            new ModVillagerTrades.IronForFood(Items.STRING, 1, 12, 5, 1),
                            new ModVillagerTrades.DiamondForFood(Items.COOKED_BEEF, 1, 20, 5, 1),
                            new ModVillagerTrades.DiamondForFood(Items.COOKED_PORKCHOP, 1, 25, 5, 1),
                            new ModVillagerTrades.DiamondForFood(Items.COOKED_CHICKEN, 1, 28, 5, 1),
                            new ModVillagerTrades.DiamondForFood(Items.COOKED_SALMON, 1, 21, 5, 1),
                            new ModVillagerTrades.DiamondForFood(Items.GOLDEN_CARROT, 1, 15, 5, 1),
                            new ModVillagerTrades.BeefForLeather(Items.BEEF, 3, 10, 5, 1),
                            new ModVillagerTrades.PorkForLeather(Items.PORKCHOP, 5, 14, 5, 1),
                            new ModVillagerTrades.PorkForLeather(Items.BEEF, 5, 6, 5, 1),
                            new ModVillagerTrades.PorkForLeather(Items.COD, 3, 8, 5, 1),
                    }));

    private static Int2ObjectMap<ModVillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, ModVillagerTrades.ItemListing[]> p_35631_) 
    {
        return new Int2ObjectOpenHashMap<>(p_35631_);
    }

    static class EmeraldForItems implements ModVillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_) {
            this.item = p_35657_.asItem();
            this.cost = p_35658_;
            this.maxUses = p_35659_;
            this.villagerXp = p_35660_;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_35662_, RandomSource p_35663_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class EmeraldsForVillagerTypeItem implements ModVillagerTrades.ItemListing {
        private final Map<VillagerType, Item> trades;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;

        public EmeraldsForVillagerTypeItem(int p_35669_, int p_35670_, int p_35671_, Map<VillagerType, Item> p_35672_) {
            Registry.VILLAGER_TYPE.stream().filter((p_35680_) -> {
                return !p_35672_.containsKey(p_35680_);
            }).findAny().ifPresent((p_35677_) -> {
                throw new IllegalStateException("Missing trade for villager type: " + Registry.VILLAGER_TYPE.getKey(p_35677_));
            });
            this.trades = p_35672_;
            this.cost = p_35669_;
            this.maxUses = p_35670_;
            this.villagerXp = p_35671_;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_35674_, RandomSource p_35675_) {
            if (p_35674_ instanceof VillagerDataHolder) {
                ItemStack itemstack = new ItemStack(this.trades.get(((VillagerDataHolder)p_35674_).getVillagerData().getType()), this.cost);
                return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, 0.05F);
            } else {
                return null;
            }
        }
    }

    static class EnchantBookForEmeralds implements ModVillagerTrades.ItemListing {
        private final int villagerXp;

        public EnchantBookForEmeralds(int p_35683_) {
            this.villagerXp = p_35683_;
        }

        public MerchantOffer getOffer(Entity p_35685_, RandomSource p_35686_)
        {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
            Enchantment enchantment = list.get(p_35686_.nextInt(list.size()));
            int i = Mth.nextInt((RandomSource) p_35686_, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
            int j = 2 + p_35686_.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.isTreasureOnly()) {
                j *= 2;
            }

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
        }
    }

    public interface ItemListing
    {
        @Nullable
        MerchantOffer getOffer(Entity p_35706_, RandomSource p_35707_);
    }

    static class ItemsAndEmeraldsToItems implements ModVillagerTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final int emeraldCost;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndEmeraldsToItems(ItemLike p_35725_, int p_35726_, Item p_35727_, int p_35728_, int p_35729_, int p_35730_) {
            this(p_35725_, p_35726_, 1, p_35727_, p_35728_, p_35729_, p_35730_);
        }

        public ItemsAndEmeraldsToItems(ItemLike p_35717_, int p_35718_, int p_35719_, Item p_35720_, int p_35721_, int p_35722_, int p_35723_) {
            this.fromItem = new ItemStack(p_35717_);
            this.fromCount = p_35718_;
            this.emeraldCost = p_35719_;
            this.toItem = new ItemStack(p_35720_);
            this.toCount = p_35721_;
            this.maxUses = p_35722_;
            this.villagerXp = p_35723_;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity p_35732_, RandomSource p_35733_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost),
                    new ItemStack(this.fromItem.getItem(), this.fromCount),
                    new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForEmeralds implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public ItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public ItemsForEmeralds(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public ItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public ItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForDiamond implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForDiamond(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public ItemsForDiamond(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public ItemsForDiamond(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public ItemsForDiamond(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public ItemsForDiamond(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_)
        {
            return new MerchantOffer(new ItemStack(Items.DIAMOND, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems),
                    this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class WoodForItems implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public WoodForItems(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public WoodForItems(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public WoodForItems(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public WoodForItems(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public WoodForItems(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Blocks.OAK_LOG, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class IronForFood implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public IronForFood(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public IronForFood(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public IronForFood(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public IronForFood(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public IronForFood(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Items.IRON_INGOT, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class DiamondForFood implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public DiamondForFood(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public DiamondForFood(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public DiamondForFood(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public DiamondForFood(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public DiamondForFood(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Items.DIAMOND, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class BeefForLeather implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public BeefForLeather(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public BeefForLeather(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public BeefForLeather(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public BeefForLeather(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public BeefForLeather(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Items.LEATHER, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class PorkForLeather implements ModVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public PorkForLeather(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
            this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
        }

        public PorkForLeather(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
            this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
        }

        public PorkForLeather(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
            this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
        }

        public PorkForLeather(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
            this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
        }

        public PorkForLeather(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
            this.itemStack = p_35758_;
            this.emeraldCost = p_35759_;
            this.numberOfItems = p_35760_;
            this.maxUses = p_35761_;
            this.villagerXp = p_35762_;
            this.priceMultiplier = p_35763_;
        }

        public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
            return new MerchantOffer(new ItemStack(Items.LEATHER, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
