package com.feroov.frv.entities.ai.goal;

import com.feroov.frv.entities.passive.Guard;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class GuardRevengeGoal extends Goal
{
        private Guard entity;

        public GuardRevengeGoal(Guard entity)
        {
            this.entity = entity;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse()
        {
            return this.entity.canAttackBack() && this.entity.getLastHurtByMob()
                    != null && this.entity.getLastHurtByMob().isAlive()
                    && this.entity.distanceTo(this.entity.getLastHurtByMob())
                    <= 10.0F && (!(this.entity.getLastHurtByMob() instanceof Player)
                    || !((Player)this.entity.getLastHurtByMob()).isCreative());
        }

        @Override
        public void tick()
        {
            LivingEntity revengeTarget = this.entity.getLastHurtByMob();
            if(revengeTarget != null && !this.entity.isStunned())
            {
                this.entity.getLookControl().setLookAt(revengeTarget, 10.0F, (float) this.entity.getHeadRotSpeed());
                if(this.entity.distanceTo(revengeTarget) >= 1.5D)
                {
                    this.entity.getNavigation().moveTo(revengeTarget, 0.5F);
                }
                else
                {
                    revengeTarget.hurt(DamageSource.mobAttack(this.entity), 10.0F);
                    this.entity.swing(InteractionHand.MAIN_HAND);
                    this.entity.setLastHurtByMob(null);
                }
            }
        }

        @Override
        public boolean canContinueToUse()
        {
            return this.entity.getLastHurtByMob() != null && this.entity.getLastHurtByMob().isAlive()
                    && this.entity.distanceTo(this.entity.getLastHurtByMob())
                    <= 10.0F;
        }

        @Override
        public void stop()
        {
            this.entity.setLastHurtByMob(null);
        }
}
