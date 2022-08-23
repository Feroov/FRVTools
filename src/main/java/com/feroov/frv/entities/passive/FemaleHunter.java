package com.feroov.frv.entities.passive;


import com.feroov.frv.entities.ai.goal.FemaleHunterFindWaterPanicGoal;
import com.feroov.frv.entities.ai.goal.FemaleHunterRevengeGoal;
import com.feroov.frv.entities.ai.goal.FollowPlayerGoalFemaleHunter;
import com.feroov.frv.entities.ai.goal.FollowPlayerGoalHunter;
import com.feroov.frv.entities.passive.abstractentity.FemaleHunterAbstractVillagerEntity;
import com.feroov.frv.entities.passive.abstractentity.ModVillagerTrades;
import com.feroov.frv.entities.variants.FemaleHunterVariant;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.crafting.Ingredient;
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


public class FemaleHunter extends FemaleHunterAbstractVillagerEntity implements IAnimatable, Npc, RangedAttackMob
{

    @Nullable
    private Player customer;
    @Nullable
    private BlockPos wanderTarget;
    private Set<UUID> tradedCustomers = new HashSet<>();

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(FemaleHunter.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(FemaleHunter.class, EntityDataSerializers.INT);
    private static final Ingredient TEMP = Ingredient.of(Items.BEEF, Items.MUTTON, Items.PORKCHOP, Items.COD);
    public static final EntityDataAccessor<Integer> ATTACK = SynchedEntityData.defineId(FemaleHunter.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(FemaleHunter.class, EntityDataSerializers.INT);

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
        if (isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("bow", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<FemaleHunter>
                (this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController<FemaleHunter>
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
    public FemaleHunter(EntityType<? extends FemaleHunterAbstractVillagerEntity> p_i48549_1_, Level p_i48549_2_)
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

    public FemaleHunterVariant getVariant()
    {
        return FemaleHunterVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(FemaleHunterVariant variant)
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
        FemaleHunterVariant variant = Util.getRandom(FemaleHunterVariant.values(), this.random);
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
        this.entityData.define(ATTACK, 1);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.62D)
                .add(Attributes.FOLLOW_RANGE, 17.0D)
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
        this.goalSelector.addGoal(1, new FemaleHunter.TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(2, new FemaleHunterFindWaterPanicGoal(this, 0.5F));
        this.goalSelector.addGoal(3, new OpenDoorGoal(this,true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pig.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Salmon.class, true));
        this.goalSelector.addGoal(4, new FemaleHunterRevengeGoal(this));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Creeper.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Warden.class, 25.0F, 0.6D, 0.5D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper) && !(p_28879_ instanceof Warden);}));
        this.goalSelector.addGoal(5, new TemptGoal(this, 0.43F, TEMP, false));
        this.goalSelector.addGoal(5, new FollowPlayerGoalFemaleHunter(this));
        this.goalSelector.addGoal(6, new FemaleHunter.LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(9, new FemaleHunter.FemaleHunterAttackGoal(this, 0.0D, true, 3));//These are combined
        this.goalSelector.addGoal(9, new FemaleHunterRangedAttackGoal(this, 0.10D, 28, 17.0F, 0)); // These are combined
        this.goalSelector.addGoal(10, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(11, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(13, new InteractGoal(this, Player.class, 1.0F, 1.0F));

    }


    /*************************** Trade with player goal ****************************/
    public class TradeWithPlayerGoal extends Goal
    {
        private final FemaleHunter mob;

        public TradeWithPlayerGoal(FemaleHunter p_25958_)
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
        private final FemaleHunter villager;

        public LookAtTradingPlayerGoal(FemaleHunter p_25538_)
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
    /** Must combine ranged and normal attack in order to anim ranged (state stuff)**/
    static class FemaleHunterAttackGoal extends MeleeAttackGoal
    {
        private final FemaleHunter entity;
        private final double speedModifier;
        private int statecheck;
        private int ticksUntilNextAttack;
        private int ticksUntilNextPathRecalculation;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;

        public FemaleHunterAttackGoal(FemaleHunter zombieIn, double speedIn, boolean longMemoryIn, int state)
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
    public static class FemaleHunterRangedAttackGoal extends Goal {
        private final FemaleHunter mob;
        private final FemaleHunter rangedAttackMob;
        private int statecheck;
        @Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final int attackIntervalMin;
        private final int attackIntervalMax;
        private final float attackRadius;
        private final float attackRadiusSqr;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;

        public FemaleHunterRangedAttackGoal(FemaleHunter femaleHunter, double speedIn, int dpsIn, float rangeIn, int state) {
            this(femaleHunter, speedIn, dpsIn, dpsIn, rangeIn, state);
        }

        public FemaleHunterRangedAttackGoal(FemaleHunter femaleHunter, double speedIn, int atckIntervalMin, int atckIntervalMax, float atckRadius, int state) {
            if (femaleHunter == null) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.rangedAttackMob =  femaleHunter;
                this.mob =  femaleHunter;
                this.speedModifier = speedIn;
                this.attackIntervalMin = atckIntervalMin;
                this.attackIntervalMax = atckIntervalMax;
                this.attackRadius = atckRadius;
                this.attackRadiusSqr = atckRadius * atckRadius;
                this.statecheck = state;

                this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            }
        }


        public boolean canUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                this.target = livingentity;
                return true;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            return this.canUse() || !this.mob.getNavigation().isDone();
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }

            if (livingentity != null) {
                boolean flag1 = this.seeTime > 0;
                if (flag != flag1) {
                    this.seeTime = 0;
                }

                if (flag) {
                    ++this.seeTime;
                } else {
                    --this.seeTime;
                }
                // strafing going backwards type stuff
                if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                    this.mob.getNavigation().stop();
                    ++this.strafingTime;
                } else {
                    this.mob.getNavigation().moveTo(livingentity, this.speedModifier);
                    this.strafingTime = -1;
                }

                if (this.strafingTime >= 20) {
                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }

                    if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }

                    this.strafingTime = 0;
                }

                if (this.strafingTime > -1) {
                    if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
                        this.strafingBackwards = false;
                    } else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
                        this.strafingBackwards = true;
                    }                                                           //speed shit
                    this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.38F : 0.38F, this.strafingClockwise ? 0.38F : -0.38F);
                    this.mob.lookAt(livingentity, 30.0F, 30.0F);
                }
            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            if (--this.attackTime == 0) {
                if (!flag) {
                    return;
                }
                if (this.mob.isUsingItem()) {
                    if (!flag && this.seeTime < -60) {
                        this.mob.stopUsingItem();
                    } else if (flag) {
                        int i = this.mob.getTicksUsingItem();
                        if (i >= 19) {
                            this.mob.setAttackingState(statecheck);
                        }
                        if (i >= 20) {
                            this.mob.stopUsingItem();
                        }
                    }
                }
                float f = (float)Math.sqrt(d0) / this.attackRadius;
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                this.rangedAttackMob.performRangedAttack(this.target, f1);
                this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
            } else if (this.attackTime < 0)
            {
                this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0)
                        / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
            }

            }
        }
    }



    public void performRangedAttack(LivingEntity livingEntity, float p_32142_) {
        Arrow arrow = new Arrow(this.level, this);
        double d0 = livingEntity.getEyeY() - (double)1.1F;
        double d1 = livingEntity.getX() - this.getX();
        double d2 = d0 - arrow.getY();
        double d3 = livingEntity.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        arrow.shoot(d1, d2 + d4, d3, 1.6F, 4.0F);
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(arrow);

    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_32144_) {
        return p_32144_ == Items.BOW;
    }
    /*************************************************************************/


    /****************************** Sounds **********************************/
    @Override
    protected SoundEvent getAmbientSound()
    {
        this.playSound(ModSoundEvents.FEMALE_HUNTER_AMBIENT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn)
    {
        this.playSound(ModSoundEvents.FEMALE_HUNTER_HURT.get(), 1.0F, 1.0F);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        this.playSound(ModSoundEvents.FEMALE_HUNTER_DEATH.get(), 1.0F, 1.0F);
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
