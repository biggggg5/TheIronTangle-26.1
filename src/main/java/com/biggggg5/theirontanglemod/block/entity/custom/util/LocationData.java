package com.biggggg5.theirontanglemod.block.entity.custom.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class LocationData {
    public String name;
    public Direction direction;
    public BlockPos pos;
    public String dimension;

    public LocationData ( String name, Direction direction, BlockPos pos, String dimension) {
        this.name = name;
        this.direction = direction;
        this.pos = pos;
        this.dimension = dimension;
    }



    public static final Codec<LocationData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(o -> o.name),
                    Direction.CODEC.fieldOf("direction").forGetter(o -> o.direction),
                    BlockPos.CODEC.fieldOf("pos").forGetter(o -> o.pos),
                    Codec.STRING.fieldOf("dimension").forGetter(o -> o.dimension)


    ).apply(instance, LocationData::new));
}
