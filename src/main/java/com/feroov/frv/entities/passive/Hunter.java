package com.feroov.frv.entities.passive;

import com.feroov.frv.entities.ai.goal.FollowPlayerGoal;
import com.feroov.frv.entities.ai.goal.FollowPlayerGoalHunter;
import com.feroov.frv.entities.ai.goal.HunterFindWaterPanicGoal;
import com.feroov.frv.entities.ai.goal.HunterRevengeGoal;
import com.feroov.frv.entities.hostile.PirateCaptain;
import com.feroov.frv.entities.passive.abstractentity.HunterAbstractVillagerEntity;
import com.feroov.frv.entities.passive.abstractentity.ModVillagerTrades;
import com.feroov.frv.entities.variants.HunterVariant;
import com.feroov.frv.sound.ModSoundEvents;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
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
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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


public class Hunter extends HunterAbstractVillagerEntity implements IAnimatable, Npc
{
    @Nullable
    private Player customer;
    @Nullable
    private BlockPos wanderTarget;
    private Set<UUID> tradedCustomers = new HashSet<>();

    public static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.INT);
    private static final Ingredient TEMP = Ingredient.of(Items.BEEF, Items.MUTTON, Items.PORKCHOP, Items.COD);
    protected static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Hunter.class, EntityDataSerializers.INT);
    public EntityType<? extends Hunter> getType() {
        return (EntityType<? extends Hunter>)super.getType();
    }

    /******************************** Animation methods *****************************/
    private final AnimationFactory factory = new AnimationFactory(this);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if ((this.dead || this.getHealth() < 0.01 || this.isDeadOrDying()))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", false));
            return PlayState.CONTINUE;
        }

        if(isAttacking())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
            return PlayState.CONTINUE;
        }

        if (!(animationSpeed > -0.10F && animationSpeed < 0.10F) && !this.isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }

        if(isAggressive())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }


        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<Hunter>
                (this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }
    /*********************************************************************************/


    /******************************** Constructor *************************************/
    public Hunter(EntityType<? extends HunterAbstractVillagerEntity> p_i48549_1_, Level p_i48549_2_)
    {
        super(p_i48549_1_, p_i48549_2_);
        this.setCanPickUpLoot(true);
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

    public void setAttacking(boolean attack) {
        this.entityData.set(ATTACKING, attack);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(STUNNED, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
        this.entityData.define(ATTACKING, false);
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
        this.goalSelector.addGoal(1, new HunterMeleeAttack(this, 0.53, true));
        this.goalSelector.addGoal(2, new OpenDoorGoal(this,true));
        this.goalSelector.addGoal(3, new Hunter.TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pig.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cod.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
        this.goalSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PirateCaptain.class, true));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Creeper.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Warden.class, 25.0F, 0.6D, 0.5D));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper) && !(p_28879_ instanceof Warden);}));
        this.goalSelector.addGoal(5, new HunterRevengeGoal(this));
        this.goalSelector.addGoal(5, new FollowPlayerGoalHunter(this));
        this.goalSelector.addGoal(5, new TemptGoal(this, 0.43F, TEMP, false));
        this.goalSelector.addGoal(6, new Hunter.LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(8, new MoveTowardsRestrictionGoal(this, 0.4D));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 4.0F, 1.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

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


    public InteractionResult mobInteract(Player player, InteractionHand interactionHand)
    {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby())
        {
            if (interactionHand == InteractionHand.MAIN_HAND)
            {
                player.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty())
            {
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
            else
            {
                if (!this.level.isClientSide)
                {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        else
        {
            return super.mobInteract(player, interactionHand);
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
    public static class HunterMeleeAttack extends MeleeAttackGoal {
        private Hunter entity;
        private int animCounter = 0;
        private int animTickLength = 19;

        public HunterMeleeAttack(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen)
        {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
            if(mob instanceof Hunter c)
            {
                entity = c;
            }
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_25557_, double p_25558_)
        {
            if (p_25558_ <= this.getAttackReachSqr(p_25557_) && this.getTicksUntilNextAttack() <= 0)
            {
                if(entity != null)
                {
                    entity.setAttacking(true);
                    animCounter = 0;
                }
            }

            super.checkAndPerformAttack(p_25557_, p_25558_);
        }

        @Override
        public void tick()
        {
            super.tick();
            if(entity.isAttacking())
            {
                animCounter++;

                if(animCounter >= animTickLength)
                {
                    animCounter = 0;
                    entity.setAttacking(false);
                }
            }
        }

        @Override
        public void stop()
        {
            animCounter = 0;
            entity.setAttacking(false);
            super.stop();
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
