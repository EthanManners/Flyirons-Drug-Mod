package com.flyirons.drugmod.ui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public final class PhysiologyHud {
    private PhysiologyHud() {}

    public static void bootstrap() {
        HudRenderCallback.EVENT.register((context, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.options.hudHidden) {
                return;
            }
            String text = String.format("Drug:%s Potency:%.0f%% Add:%.2f OD:%.2f",
                    PhysiologyViewModel.focusDrug(),
                    PhysiologyViewModel.potency(),
                    PhysiologyViewModel.addiction(),
                    PhysiologyViewModel.overdoseRisk());
            int y = client.getWindow().getScaledHeight() - 20;
            context.drawText(client.textRenderer, text, 8, y, 0xFFFFFF, true);
        });
    }
}
