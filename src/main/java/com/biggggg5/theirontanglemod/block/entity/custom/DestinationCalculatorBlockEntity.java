package com.biggggg5.theirontanglemod.block.entity.custom;

import com.biggggg5.theirontanglemod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class DestinationCalculatorBlockEntity extends BlockEntity {

    private ResourceKey<Level> targetDimension;
    private ResourceKey<Level> localDimension;
    private @Nullable BlockPos targetPos;
    private @Nullable BlockPos localPos;
    private Direction entrydirection;
    private Direction exitdirection;
    private String name;
    private String color;

    public DestinationCalculatorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CALCULATOR_BE.get(), worldPosition, blockState);
    }
}
