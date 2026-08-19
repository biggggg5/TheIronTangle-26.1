package com.biggggg5.theirontanglemod.item.custom;

import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.block.entity.custom.DestinationCalculatorBlockEntity;
import com.biggggg5.theirontanglemod.menu.custom.ListMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class ControllerItem extends Item {
    public ControllerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        BlockPos pos = player.blockPosition();
        boolean found = false;

        if (!level.isClientSide()) {
            for (int i = -30; i <= 30; i++) {
                for (int j = -30; j <= 30; j++) {
                    for (int k = -30; k <= 30; k++) {
                        BlockPos checkedPos = pos.offset(i, j, k);
                        BlockState checkState = level.getBlockState(checkedPos);

                        if (checkState.is(ModBlocks.DESTINATION_CALCULATOR.get())) {
                            player.sendOverlayMessage(Component.literal("You found a Calculator!"));
                            found = true;
                            if (level.getBlockEntity(checkedPos) instanceof DestinationCalculatorBlockEntity calculatorBE) {
                                player.openMenu(calculatorBE.getListMenuProvider(), checkedPos);
                            }
                            break;
                        }
                    }
                }
            }
            if (!found) {
                player.sendOverlayMessage(Component.translatable("item.theirontanglemod.remote_portal_controller.none_in_range"));
            }
        }
        return InteractionResult.SUCCESS;
    }
}
