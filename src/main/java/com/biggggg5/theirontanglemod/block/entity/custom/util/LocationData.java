package com.biggggg5.theirontanglemod.block.entity.custom.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class LocationData {
    public String name;
    public Direction direction;
    public BlockPos pos;
    public ResourceKey<Level> dimension;
}
