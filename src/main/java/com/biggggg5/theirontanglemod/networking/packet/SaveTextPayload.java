package com.biggggg5.theirontanglemod.networking.packet;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SaveTextPayload(BlockPos pos, String text) implements CustomPacketPayload {
    public static final Type<SaveTextPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TheIronTangleMod.MOD_ID, "name_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SaveTextPayload> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            SaveTextPayload::pos,

            ByteBufCodecs.STRING_UTF8,
            SaveTextPayload::text,

            SaveTextPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

