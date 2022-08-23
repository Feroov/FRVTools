package com.feroov.frv.entities.ai.goal;


import com.feroov.frv.entities.passive.FemaleHunter;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FollowPlayerGoalFemaleHunter extends Goal
{
    private Player potentialCustomer;
    private FemaleHunter entity;
    private int coolDown = 0;
    private int timeout = 600;

    public FollowPlayerGoalFemaleHunter(FemaleHunter entity)
    {
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse()
    {
        if(this.entity.getTradingPlayer() != null)
        {
            return false;
        }
        if(this.coolDown > 0)
        {
            this.coolDown--;
            return false;
        }
        if(this.entity.isStunned())
        {
            return false;
        }
        this.findCustomer();
        return this.potentialCustomer != null && this.potentialCustomer.isAlive() && !this.entity.isPreviousCustomer(this.potentialCustomer);
    }

    @Override
    public void tick()
    {
        this.entity.getLookControl().setLookAt(this.potentialCustomer, 10.0F, (float) this.entity.getHeadRotSpeed());
        if(this.entity.distanceTo(this.potentialCustomer) >= 2.0D)
        {
            this.entity.getNavigation().moveTo(this.potentialCustomer, 0.45F);
        }
        this.timeout--;
    }

    @Override
    public boolean canContinueToUse()
    {
        return this.potentialCustomer != null
                && this.potentialCustomer.isAlive()
                && this.entity.getTradingPlayer() == null
                && !this.entity.isPreviousCustomer(this.potentialCustomer)
                && this.entity.distanceTo(this.potentialCustomer) <= 10.0D && this.timeout > 0;
    }

    @Override
    public void stop()
    {
        this.entity.getNavigation().stop();
        this.potentialCustomer = null;
        this.timeout = 600;
        this.coolDown = 300;
    }

    @Nullable
    private void findCustomer()
    {
        List<Player> players = this.entity.level.getEntitiesOfClass(Player.class,
                this.entity.getBoundingBox().inflate(10), playerEntity -> !playerEntity.isCreative() && !playerEntity.isSpectator());
        if(players.size() > 0)
        {
            this.potentialCustomer = players.stream().min(Comparator.comparing(this.entity::distanceTo)).get();
        }
    }
}
