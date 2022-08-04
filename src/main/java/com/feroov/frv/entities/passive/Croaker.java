package com.feroov.frv.entities.passive;

import com.feroov.frv.entities.ai.goal.FollowPlayerGoal;
import com.feroov.frv.entities.passive.abstractentity.CroakerAbstractVillagerEntity;
import com.feroov.frv.entities.passive.abstractentity.ModVillagerTrades;
import com.feroov.frv.entities.variants.CroakerVariant;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;


public class Croaker extends CroakerAbstractVillagerEntity implements IAnimatable
{
    @Nullable
    private Player customer;
    @Nullable
    private BlockPos wanderTarget;
    private int despawnDelay;
    private Set<UUID> tradedCustomers = new HashSet<>();
    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Croaker.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Croaker.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Croaker.class, EntityDataSerializers.INT);
    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (!event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<Croaker>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    /*********************************************************************************/

    /******************************** Constructor *****************************/
    public Croaker(EntityType<? extends CroakerAbstractVillagerEntity> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
    }
    /*********************************************************************************/

    /******************************** Variant methods *****************************/
    @Override
    public void addAdditionalSaveData(CompoundTag tag)
    {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_21815_)
    {
        super.readAdditionalSaveData(p_21815_);
        this.entityData.set(DATA_ID_TYPE_VARIANT, p_21815_.getInt("Variant"));
    }

    public CroakerVariant getVariant()
    {
        return CroakerVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(CroakerVariant variant)
    {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    private int getTypeVariant()
    {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_,
                                        MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_)
    {
        CroakerVariant variant = Util.getRandom(CroakerVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }
    /*********************************************************************************/

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.62D)
                .add(Attributes.FOLLOW_RANGE, 25.0D);
    }
    public void setDespawnDelay(int despawnDelay)
    {
        this.despawnDelay = despawnDelay;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowPlayerGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new OpenDoorGoal(this,true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(5, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(5, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(7, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, Creeper.class, 12.0F, 0.5D, 0.5D));
    }

    @Override
    protected void updateControlFlags()
    {
        super.updateControlFlags();
        if(this.isStunned())
        {
            this.goalSelector.setControlFlag(Goal.Flag.MOVE, true);
            this.goalSelector.setControlFlag(Goal.Flag.JUMP, true);
            this.goalSelector.setControlFlag(Goal.Flag.LOOK, true);
        }
    }

    public boolean isPreviousCustomer(Player player)
    {
        return this.tradedCustomers.contains(player.getUUID());
    }
    public boolean isStunned()
    {
        return this.entityData.get(STUNNED);
    }
    /*************************** Trade with player goal ****************************/
    public class TradeWithPlayerGoal extends Goal
    {
        private final Croaker mob;

        public TradeWithPlayerGoal(Croaker p_25958_)
        {
            this.mob = p_25958_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse()
        {
            if (!this.mob.isAlive())
            {
                return false;
            }
            else if (this.mob.isInWater()) {

                return false;
            }
            else if (!this.mob.isOnGround())
            {
                return false;
            }
            else if (this.mob.hurtMarked)
            {
                return false;
            }
            else
            {
                Player player = this.mob.getTradingPlayer();
                if (player == null)
                {
                    return false;
                }
                else if (this.mob.distanceToSqr(player) > 16.0D)
                {
                    return false;
                }
                else
                {
                    return player.containerMenu != null;
                }
            }
        }

        public void start()
        {
            this.mob.getNavigation().stop();
        }

        public void stop()
        {
            this.mob.setTradingPlayer((Player)null);
        }
    }
    /******************************************************************************/
    /*************************** Look at player goal ****************************/
    public class LookAtTradingPlayerGoal extends LookAtPlayerGoal
    {
        private final Croaker villager;

        public LookAtTradingPlayerGoal(Croaker p_25538_)
        {
            super(p_25538_, Player.class, 8.0F);
            this.villager = p_25538_;
        }

        public boolean canUse()
        {
            if (this.villager.isTrading())
            {
                this.lookAt = this.villager.getTradingPlayer();
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    /******************************************************************************/
    @Override
    protected void rewardTradeXp(MerchantOffer p_213713_1_)
    {
        if (p_213713_1_.shouldRewardExp())
        {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    public boolean showProgressBar()
    {
        return false;
    }

    public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_)
    {
        ItemStack itemstack = p_35856_.getItemInHand(p_35857_);
        if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby())
        {
            if (p_35857_ == InteractionHand.MAIN_HAND)
            {
                p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty())
            {
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
            else
            {
                if (!this.level.isClientSide)
                {
                    this.setTradingPlayer(p_35856_);
                    this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        else
        {
            return super.mobInteract(p_35856_, p_35857_);
        }
    }
    /*************************** Main trading stuff ****************************/
    @Override
    protected void updateTrades()
    {
        ModVillagerTrades.ItemListing[] avillagertrades$itrade = ModVillagerTrades.CROAKER_TRADES.get(1);
        if (avillagertrades$itrade != null)
        {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, avillagertrades$itrade, 9);
            int i = this.random.nextInt(avillagertrades$itrade.length);
            ModVillagerTrades.ItemListing villagertrades$itemlisting = avillagertrades$itrade[i];
            MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
            if (merchantoffer != null)
            {
                merchantoffers.add(merchantoffer);
            }
        }
    }
    /*************************************************************************/


    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.CROAKER_AMBIENT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.CROAKER_HURT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.CROAKER_DEATH.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
    {
        return 1.5F;
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 50 && !this.level.isClientSide()) {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }


}
