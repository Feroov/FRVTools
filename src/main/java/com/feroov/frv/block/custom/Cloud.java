package com.feroov.frv.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Cloud extends WebBlock
{

    public Cloud(Properties properties)
    {
        super(properties);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity)
    {
        entity.makeStuckInBlock(blockState, new Vec3(0.75D, (double)0.75F, 0.75D));
    }
}
