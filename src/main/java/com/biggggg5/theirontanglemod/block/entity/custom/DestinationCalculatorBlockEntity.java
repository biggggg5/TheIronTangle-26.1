package com.biggggg5.theirontanglemod.block.entity.custom;

import com.biggggg5.theirontanglemod.block.entity.ModBlockEntities;
import com.biggggg5.theirontanglemod.menu.custom.CalculatorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class DestinationCalculatorBlockEntity extends BlockEntity implements MenuProvider {

    private ResourceKey<Level> targetDimension;
    private ResourceKey<Level> localDimension;
    private @Nullable BlockPos targetPos;
    private @Nullable BlockPos localPos;
    private Direction entryDirection;
    private Direction exitDirection;
    private String portalName = "";

    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(1) {
        @Override
        protected void onContentsChanged(int index, ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            DestinationCalculatorBlockEntity.this.setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        protected int getCapacity(int index, ItemResource resource) {
            return 16;
        }
    };

    public DestinationCalculatorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.CALCULATOR_BE.get(), worldPosition, blockState);
    }

    public void clearContents() {
        inventory.set(0, ItemResource.EMPTY, 0);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.theirontanglemod.destination_calculator");
    }

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
        setChanged();
    }


    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putChild("inventory", inventory);
        output.putString("portalName", portalName);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        input.child("inventory").ifPresent(inventory::deserialize);
        this.setPortalName(input.getStringOr("portalName", ""));
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new CalculatorMenu(containerId, inventory, this, this.inventory);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Override
    public void onDataPacket(Connection net, ValueInput valueInput) {
        super.onDataPacket(net, valueInput);
    }
}
