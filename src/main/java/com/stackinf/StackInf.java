package com.stackinf;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class StackInf implements ModInitializer {
    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(DoublePayload.TYPE, DoublePayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(DoublePayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            ItemStack stack = player.getMainHandItem();
            if (stack.isEmpty()) return;
            long doubled = (long) stack.getCount() * 2;
            stack.setCount((int) Math.min(doubled, Integer.MAX_VALUE));
        });
    }
}
