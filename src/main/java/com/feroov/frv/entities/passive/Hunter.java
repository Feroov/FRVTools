package com.feroov.frv.entities.passive;

import com.feroov.frv.entities.ai.goal.HunterFindWaterPanicGoal;
import com.feroov.frv.entities.ai.goal.HunterRevengeGoal;
import com.feroov.frv.entities.trades.HunterAbstractVillagerEntity;
import com.feroov.frv.entities.trades.ModVillagerTrades;
import com.feroov.frv.entities.variants.HunterVariant;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.npc.Npc;
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

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Hunter extends HunterAbstractVillagerEntity implements IAnimatable, Npc
{
    @Nullable
    private Player customer;
    @Nullable
    private BlockPos wanderTarget;
    private Set<UUID> tradedCustomers = new HashSet<>();

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.INT);

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState attack(AnimationEvent<E> event)
    {
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }
        if(isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<Hunter>
                (this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController<Hunter>
                (this, "attackController", 0, this::attack));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public void setAttackingState(int time)
    {
        this.entityData.set(ATTACK, time);
    }
    /*********************************************************************************/


    /******************************** Constructor *************************************/
    public Hunter(EntityType<? extends HunterAbstractVillagerEntity> p_i48549_1_, Level p_i48549_2_)
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

    public HunterVariant getVariant()
    {
        return HunterVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(HunterVariant variant)
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
        HunterVariant variant = Util.getRandom(HunterVariant.values(), this.random);
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
        this.entityData.define(ATTACK, 0);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.62D)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D);
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

    public boolean canAttackBack() {
        return true;
    }

    public boolean isPreviousCustomer(Player player)
    {
        return this.tradedCustomers.contains(player.getUUID());
    }
    public boolean isStunned()
    {
        return this.entityData.get(STUNNED);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new HunterFindWaterPanicGoal(this, 0.5F));
        this.goalSelector.addGoal(2, new OpenDoorGoal(this,true));
        this.goalSelector.addGoal(3, new Hunter.TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pig.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);}));
        this.goalSelector.addGoal(5, new HunterRevengeGoal(this));
        //this.targetSelector.addGoal(5, new HunterAttackGoal(this, 0.67D, true, 5));
        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 0.66D, true));
        this.goalSelector.addGoal(6, new Hunter.LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(8, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 4.0F, 1.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, Creeper.class, 12.0F, 0.5D, 0.5D));
    }


    /*************************** Trade with player goal ****************************/
    public class TradeWithPlayerGoal extends Goal
    {
        private final Hunter mob;

        public TradeWithPlayerGoal(Hunter p_25958_)
        {
            this.mob = p_25958_;
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
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
        private final Hunter villager;

        public LookAtTradingPlayerGoal(Hunter p_25538_)
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
        ModVillagerTrades.ItemListing[] avillagertrades$itrade = ModVillagerTrades.HUNTER_TRADES.get(1);
        if (avillagertrades$itrade != null)
        {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, avillagertrades$itrade, 4);
            int i = this.random.nextInt(avillagertrades$itrade.length);
            ModVillagerTrades.ItemListing villagertrades$itrade = avillagertrades$itrade[i];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null)
            {
                merchantoffers.add(merchantoffer);
            }
        }
    }
    /*************************************************************************/


    /*************************** Attack Goal *********************************/
    static class HunterAttackGoal extends MeleeAttackGoal
    {
        private final Hunter entity;
        private final double speedModifier;
        private int statecheck;
        private int ticksUntilNextAttack;
        private int ticksUntilNextPathRecalculation;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;

        public HunterAttackGoal(Hunter zombieIn, double speedIn, boolean longMemoryIn, int state)
        {
            super(zombieIn, speedIn, longMemoryIn);
            this.entity = zombieIn;
            this.statecheck = state;
            this.speedModifier = speedIn;
        }

        public void start()
        {
            super.start();
        }

        public boolean canUse()
        {
            return super.canUse();
        }

        public void stop()
        {
            super.stop();
            this.entity.setAggressive(false);
            this.entity.setAttackingState(0);
        }

        public void tick()
        {
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity != null)
            {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if ((this.mob.getSensing().hasLineOfSight(livingentity))
                        && this.ticksUntilNextPathRecalculation <= 0
                        && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D
                        || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY,
                        this.pathedTargetZ) >= 1.0D
                        || this.mob.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = livingentity.getX();
                    this.pathedTargetY = livingentity.getY();
                    this.pathedTargetZ = livingentity.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (d0 > 1024.0D)
                    {
                        this.ticksUntilNextPathRecalculation += 10;
                    }
                    else if (d0 > 256.0D)
                    {
                        this.ticksUntilNextPathRecalculation += 5;
                    }

                    if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier))
                    {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 0, 0);
                this.checkAndPerformAttack(livingentity, d0);
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity livingentity, double squaredDistance)
        {
            double d0 = this.getAttackReachSqr(livingentity);
            if (squaredDistance <= d0 && this.getTicksUntilNextAttack() <= 0)
            {
                this.resetAttackCooldown();
                this.entity.setAttackingState(statecheck);
                this.mob.doHurtTarget(livingentity);
            }
        }

        @Override
        protected int getAttackInterval()
        {
            return 50;
        }

        @Override
        protected double getAttackReachSqr(LivingEntity attackTarget)
        {
            return this.mob.getBbWidth() * 1.0F * this.mob.getBbWidth() * 1.0F + attackTarget.getBbWidth();
        }
    }
    /*************************************************************************/


    /****************************** Sounds **********************************/
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.HUNTER_AMBIENT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.HUNTER_HURT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.HUNTER_DEATH.get(), 1.0F, 1.0F);
        return null;
    }
    /*************************************************************************/


    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
    {
        return 1.65F;
    }

    @Override
    protected void tickDeath()
    {
        ++this.deathTime;
        if (this.deathTime == 60 && !this.level.isClientSide())
        {
            this.level.broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }

    }

    @Override
    public boolean isBaby()
    {
        return false;
    }
}
