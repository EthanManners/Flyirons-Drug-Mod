package com.flyirons.drugmod.client;

import com.flyirons.drugmod.network.SyncPhysiologyS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DrugModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding openScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.flyirons_drug_mod.drug_screen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "category.flyirons_drug_mod"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openScreen.wasPressed()) {
                client.setScreen(new DrugStatusScreen());
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) {
                return;
            }
            drawContext.drawTextWithShadow(client.textRenderer,
                    String.format("Phys: TOL %.0f%% | ADD %.0f%% | OD %.0f%%", ClientPhysiologySnapshot.tolerance * 100f, ClientPhysiologySnapshot.addiction * 100f, ClientPhysiologySnapshot.overdose * 100f),
                    8, 8, 0xFFFFFF);
        });

        ClientPlayNetworking.registerGlobalReceiver(SyncPhysiologyS2CPacket.ID, (payload, context) -> context.client().execute(() -> {
            ClientPhysiologySnapshot.drugId = payload.drugId();
            ClientPhysiologySnapshot.tolerance = payload.tolerance();
            ClientPhysiologySnapshot.addiction = payload.addiction();
            ClientPhysiologySnapshot.overdose = payload.overdose();
            ClientPhysiologySnapshot.withdrawalTicks = payload.withdrawalTicks();
        }));
    }
}
