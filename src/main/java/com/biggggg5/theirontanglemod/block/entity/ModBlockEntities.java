package com.biggggg5.theirontanglemod.block.entity;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.block.entity.custom.DestinationCalculatorBlockEntity;
import com.biggggg5.theirontanglemod.block.entity.custom.RailwayPortalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TheIronTangleMod.MOD_ID);

    public static final Supplier<BlockEntityType<RailwayPortalBlockEntity>> RAILWAY_BE =
            BLOCK_ENTITIES.register("railway_be", () -> new BlockEntityType<>(
                    RailwayPortalBlockEntity::new, ModBlocks.RAILWAY_PORTAL_BLOCK.get()));

    public static final Supplier<BlockEntityType<DestinationCalculatorBlockEntity>> CALCULATOR_BE =
            BLOCK_ENTITIES.register("calculator_be", () -> new BlockEntityType<>(
                    DestinationCalculatorBlockEntity::new, ModBlocks.DESTINATION_CALCULATOR.get()));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}