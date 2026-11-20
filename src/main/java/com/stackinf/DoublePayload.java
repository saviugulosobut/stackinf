package com.stackinf;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DoublePayload() implements CustomPacketPayload {
    public static final Type<DoublePayload> TYPE =
        new Type<>(Identifier.fromNamespaceAndPath("stackinf", "double"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DoublePayload> CODEC =
        StreamCodec.unit(new DoublePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
