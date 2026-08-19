package com.biggggg5.theirontanglemod.menu.custom;

import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.block.entity.custom.DestinationCalculatorBlockEntity;
import com.biggggg5.theirontanglemod.menu.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import org.jspecify.annotations.Nullable;

public class ListMenu extends AbstractContainerMenu {
    public final DestinationCalculatorBlockEntity blockEntity;
    private final Level level;

    public ListMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new ItemStacksResourceHandler(1));
    }

    public ListMenu(int containerId, @Nullable Inventory inv, BlockEntity blockEntity, ItemStacksResourceHandler handler) {
        super(ModMenuTypes.LIST_MENU.get(), containerId);
        this.blockEntity = (DestinationCalculatorBlockEntity) blockEntity;
        this.level = inv.player.level();
    }


    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}

