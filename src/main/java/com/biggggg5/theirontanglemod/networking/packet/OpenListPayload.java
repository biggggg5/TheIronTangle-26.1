package com.biggggg5.theirontanglemod.networking.packet;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record OpenListPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<OpenListPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TheIronTangleMod.MOD_ID, "open_list_packet"));

public static final StreamCodec<FriendlyByteBuf, OpenListPayload> STREAM_CODEC =
        StreamCodec.composite(
                BlockPos.STREAM_CODEC, OpenListPayload::pos,
                OpenListPayload::new
        );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
