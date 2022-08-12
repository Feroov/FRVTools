package com.feroov.frv.world;

import com.feroov.frv.block.ModBlocks;
import com.feroov.frv.block.custom.MatrixPortalBlock;
import com.feroov.frv.sound.ModSoundEvents;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class CorruptionTeleporter implements ITeleporter
{
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    public CorruptionTeleporter(BlockPos pos, boolean insideDim)
    {
        thisPos = pos;
        insideDimension = insideDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity)
    {
        entity = repositionEntity.apply(false);
        double y = 69;

        if (!insideDimension)
        {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getMaterial() != Material.AIR) &&
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                destinationWorld.getBlockState(destinationPos.above()).getMaterial() != Material.AIR &&
                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && tries < 25)
        {
            destinationPos = destinationPos.above(2);
            tries++;
        }

        entity.teleportTo(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (insideDimension)
        {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.below(10).west(10).south(10), destinationPos.above(10).east(10).north(10)))
            {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof MatrixPortalBlock)
                {
                    doSetBlock = false;
                    break;
                }
            }
            if (doSetBlock)
            {
                destinationWorld.setBlock(destinationPos, ModBlocks.MATRIX_PORTAL.get().defaultBlockState(), 3);
            }
        }

        return entity;
    }

    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld)
    {
        return true;
    }
}