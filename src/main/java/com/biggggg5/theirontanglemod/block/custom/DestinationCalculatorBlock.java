package com.biggggg5.theirontanglemod.block.custom;

import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.block.entity.custom.DestinationCalculatorBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class DestinationCalculatorBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final MapCodec<DestinationCalculatorBlock> CODEC = simpleCodec(DestinationCalculatorBlock::new);
    private static @Nullable BlockPattern portalShape;
    public DestinationCalculatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {

        return new DestinationCalculatorBlockEntity(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide()) {
            BlockPattern.BlockPatternMatch match = findPortal(level, pos, checkPortalShape());
            if (match == null) {
                player.sendSystemMessage (Component.literal("No reaction. The portal must not be built correctly."));
                return InteractionResult.FAIL;
            }

            placePortalBlocks(level, match);
            if (level.getBlockEntity(pos) instanceof DestinationCalculatorBlockEntity calculatorBE) {
                player.openMenu(calculatorBE.getFuelMenuProvider(), pos);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    public static BlockPattern checkPortalShape() {
        if (portalShape == null) {
            portalShape = BlockPatternBuilder.start()
                    .aisle(
                            "OOCOO",
                            "OAAAO",
                            "OAAAO",
                            "OARAO",
                            "OOOOO"
                    )
                    .aisle(
                            ".....",
                            ".OOO.",
                            "OAAAO",
                            "OARAO",
                            ".OOO."
                    )
                    .aisle(
                            ".....",
                            ".OOO.",
                            "OAAAO",
                            "OARAO",
                            ".OOO."
                    )
                    .aisle(
                            ".....",
                            ".....",
                            ".OOO.",
                            ".OAO.",
                            "..O.."
                    )
                    .aisle(
                            ".....",
                            ".....",
                            ".....",
                            "..O..",
                            "....."
                    )
                    .where('O', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.REFINED_OBSIDIAN.get())))
                    .where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.DESTINATION_CALCULATOR.get())))
                    .where('R', BlockInWorld.hasState(state -> state.is(ModBlocks.FLINT_RAIL_BLOCK.get()) || state.is(ModBlocks.RAILWAY_PORTAL_BLOCK.get())))
                    .where('A', BlockInWorld.hasState(state -> state.is(Blocks.AIR) || state.is(ModBlocks.RAILWAY_PORTAL_BLOCK.get())))
                    .where('.', BlockInWorld.hasState(state -> true))
                    .build();

        }
        return portalShape;
    }

    private void placePortalBlocks(Level level, BlockPattern.BlockPatternMatch match) {
        for (int x = 1; x < 4; x++) {
            for (int y = 1; y < 4; y++) {
                BlockInWorld blockInWorld = match.getBlock(x, y, 0);
                BlockPos targetPos = blockInWorld.getPos();
                level.setBlockAndUpdate(targetPos, ModBlocks.RAILWAY_PORTAL_BLOCK.get().defaultBlockState());
            }
        }
    }
    private static final Direction[] HORIZONTAL_FACINGS = {
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
    };

    private BlockPattern.BlockPatternMatch findPortal(Level level, BlockPos clickedPos, BlockPattern pattern) {
        for (Direction facing : HORIZONTAL_FACINGS) {
            BlockPos anchor = getAnchorFor(clickedPos, facing);
            BlockPattern.BlockPatternMatch match = pattern.matches(level, anchor, facing, Direction.UP);
            if (match != null) {
                return match;
            }
        }
        return null;
    }

    private BlockPos getAnchorFor(BlockPos clicked, Direction facing) {

        Direction right = facing.getClockWise();
        return clicked
                .relative(right.getOpposite(), 2)
                .relative(Direction.UP, 0);
    }
}