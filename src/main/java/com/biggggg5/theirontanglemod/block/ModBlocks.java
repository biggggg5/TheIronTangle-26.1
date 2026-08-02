package com.biggggg5.theirontanglemod.block;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.custom.DestinationCalculatorBlock;
import com.biggggg5.theirontanglemod.block.custom.FlintRailBlock;
import com.biggggg5.theirontanglemod.block.custom.RailwayPortalBlock;
import com.biggggg5.theirontanglemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(TheIronTangleMod.MOD_ID);

    //portal
    //destination calc
    //rails


    public static final DeferredBlock<Block> REFINED_OBSIDIAN = registerBlock("refined_obsidian",
            properties -> new Block(properties.strength(50f, 1200f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ANCIENT_ENGINE = registerBlock("ancient_engine",
            properties -> new Block(properties.strength(3.5f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> RAILWAY_PORTAL_BLOCK = registerBlock("railway_portal",
            properties -> new RailwayPortalBlock(properties.strength(-1f)
                    .requiresCorrectToolForDrops().sound(SoundType.GLASS)));
    public static final DeferredBlock<Block> DESTINATION_CALCULATOR = registerBlock("destination_calculator",
            properties -> new DestinationCalculatorBlock(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.IRON)));
    public static final DeferredBlock<Block> FLINT_RAIL_BLOCK = registerBlock("flint_rail",
            properties -> new FlintRailBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)));





    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}