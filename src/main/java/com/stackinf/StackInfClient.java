package com.stackinf;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;

public class StackInfClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMapping doubleKey = KeyBindingHelper.registerKeyBinding(
            new KeyMapping("key.stackinf.double",
                InputConstants.Type.KEYSYM, InputConstants.KEY_K, "key.categories.stackinf"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (doubleKey.consumeClick()) {
                if (client.player != null) {
                    ClientPlayNetworking.send(new DoublePayload());
                }
            }
        });
    }
}
