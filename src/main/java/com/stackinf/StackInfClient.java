package com.stackinf;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class StackInfClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("stackinf", "main"));

        KeyMapping doubleKey = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.stackinf.double",
                InputConstants.Type.KEYSYM, InputConstants.KEY_K, category));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (doubleKey.consumeClick()) {
                if (client.player != null) {
                    ClientPlayNetworking.send(new DoublePayload());
                }
            }
        });
    }
}
