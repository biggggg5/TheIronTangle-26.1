package com.biggggg5.theirontanglemod.block.entity.custom;

import com.biggggg5.theirontanglemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class RailwayPortalBlockEntity extends BlockEntity {

    private ResourceKey<Level> targetDimension;
    private @Nullable BlockPos targetPos;
    private Direction entrydirection;
    private Direction exitdirection;
    private boolean exactTeleport;

    public RailwayPortalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.RAILWAY_BE.get(), worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
    }
}
