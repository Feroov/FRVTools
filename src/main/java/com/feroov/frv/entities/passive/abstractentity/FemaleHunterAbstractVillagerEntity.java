package com.feroov.frv.entities.passive.abstractentity;

import com.feroov.frv.sound.ModSoundEvents;
import com.google.common.collect.Sets;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Set;

public class FemaleHunterAbstractVillagerEntity extends AgeableMob implements InventoryCarrier, Npc, Merchant
{
    private static final EntityDataAccessor<Integer> DATA_UNHAPPY_COUNTER =
            SynchedEntityData.defineId(FemaleHunterAbstractVillagerEntity.class, EntityDataSerializers.INT);
    public static final int VILLAGER_SLOT_OFFSET = 300;
    private static final int VILLAGER_INVENTORY_SIZE = 8;
    @Nullable
    private Player tradingPlayer;
    @Nullable
    protected MerchantOffers offers;
    private final SimpleContainer inventory = new SimpleContainer(8);

    public FemaleHunterAbstractVillagerEntity(EntityType<? extends FemaleHunterAbstractVillagerEntity> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_35282_, DifficultyInstance p_35283_,
                                        MobSpawnType p_35284_, @Nullable SpawnGroupData p_35285_, @Nullable CompoundTag p_35286_) {
        if (p_35285_ == null) {
            p_35285_ = new AgeableMobGroupData(false);
        }

        return super.finalizeSpawn(p_35282_, p_35283_, p_35284_, p_35285_, p_35286_);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public int getUnhappyCounter() {
        return this.entityData.get(DATA_UNHAPPY_COUNTER);
    }

    public void setUnhappyCounter(int p_35320_) {
        this.entityData.set(DATA_UNHAPPY_COUNTER, p_35320_);
    }

    public int getVillagerXp() {
        return 0;
    }

    protected float getStandingEyeHeight(Pose p_35297_, EntityDimensions p_35298_) {
        return this.isBaby() ? 0.81F : 1.62F;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_UNHAPPY_COUNTER, 0);
    }

    public void setTradingPlayer(@Nullable Player p_35314_) {
        this.tradingPlayer = p_35314_;
    }

    @Nullable
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    public boolean isTrading() {
        return this.tradingPlayer != null;
    }

    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }

        return this.offers;
    }

    public void overrideOffers(@Nullable MerchantOffers p_35276_) {
    }

    public void overrideXp(int p_35322_) {
    }

    public void notifyTrade(MerchantOffer p_35274_) {
        p_35274_.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.rewardTradeXp(p_35274_);
    }

    protected void rewardTradeXp(MerchantOffer p_35299_) {

    }

    public boolean showProgressBar() {
        return true;
    }

    public void notifyTradeUpdated(ItemStack p_35316_) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getTradeUpdatedSound(!p_35316_.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }

    }

    public SoundEvent getNotifyTradeSound() {
        return ModSoundEvents.FEMALE_HUNTER_YES.get();
    }

    protected SoundEvent getTradeUpdatedSound(boolean p_35323_) {
        return p_35323_ ? ModSoundEvents.FEMALE_HUNTER_YES.get() : ModSoundEvents.FEMALE_HUNTER_NO.get();
    }

    public void playCelebrateSound() {
        this.playSound(ModSoundEvents.FEMALE_HUNTER_YES.get(), this.getSoundVolume(), this.getVoicePitch());
    }

    public void addAdditionalSaveData(CompoundTag p_35301_) {
        super.addAdditionalSaveData(p_35301_);
        MerchantOffers merchantoffers = this.getOffers();
        if (!merchantoffers.isEmpty()) {
            p_35301_.put("Offers", merchantoffers.createTag());
        }

        p_35301_.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundTag p_35290_) {
        super.readAdditionalSaveData(p_35290_);
        if (p_35290_.contains("Offers", 10)) {
            this.offers = new MerchantOffers(p_35290_.getCompound("Offers"));
        }

        this.inventory.fromTag(p_35290_.getList("Inventory", 10));
    }

    @Nullable
    public Entity changeDimension(ServerLevel p_35295_, net.minecraftforge.common.util.ITeleporter teleporter) {
        this.stopTrading();
        return super.changeDimension(p_35295_, teleporter);
    }

    protected void stopTrading() {
        this.setTradingPlayer((Player)null);
    }

    public void die(DamageSource p_35270_) {
        super.die(p_35270_);
        this.stopTrading();
    }

    protected void addParticlesAroundSelf(ParticleOptions p_35288_) {
        for(int i = 0; i < 5; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(p_35288_, this.getRandomX(1.0D), this.getRandomY() + 1.0D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    public boolean canBeLeashed(Player p_35272_) {
        return false;
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public SlotAccess getSlot(int p_149995_) {
        int i = p_149995_ - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(p_149995_);
    }

    protected void updateTrades() {

    }

    protected void addOffersFromItemListings(MerchantOffers p_35278_, ModVillagerTrades.ItemListing[] p_35279_, int p_35280_) {
        Set<Integer> set = Sets.newHashSet();
        if (p_35279_.length > p_35280_) {
            while(set.size() < p_35280_) {
                set.add(this.random.nextInt(p_35279_.length));
            }
        } else {
            for(int i = 0; i < p_35279_.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            ModVillagerTrades.ItemListing villagertrades$itemlisting = p_35279_[integer];
            MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
            if (merchantoffer != null) {
                p_35278_.add(merchantoffer);
            }
        }

    }

    public Vec3 getRopeHoldPosition(float p_35318_) {
        float f = Mth.lerp(p_35318_, this.yBodyRotO, this.yBodyRot) * ((float)Math.PI / 180F);
        Vec3 vec3 = new Vec3(0.0D, this.getBoundingBox().getYsize() - 1.0D, 0.2D);
        return this.getPosition(p_35318_).add(vec3.yRot(-f));
    }

    public boolean isClientSide() {
        return this.level.isClientSide;
    }
}
