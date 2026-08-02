package com.biggggg5.theirontanglemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import org.jspecify.annotations.Nullable;

public class RailwayPortalBlock extends BaseEntityBlock implements Portal {
    public RailwayPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return null;
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        return null;
    }
}
