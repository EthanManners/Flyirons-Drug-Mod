package com.flyirons.drugmod.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DrugStatusScreen extends Screen {
    protected DrugStatusScreen() {
        super(Text.literal("Drug Physiology"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 28, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "Recent Drug: " + ClientPhysiologySnapshot.drugId, 40, 70, 0xA5FFAE);
        context.drawTextWithShadow(this.textRenderer, String.format("Tolerance: %.0f%%", ClientPhysiologySnapshot.tolerance * 100f), 40, 88, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, String.format("Addiction: %.0f%%", ClientPhysiologySnapshot.addiction * 100f), 40, 106, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, String.format("Overdose Pressure: %.0f%%", ClientPhysiologySnapshot.overdose * 100f), 40, 124, 0xFF9191);
        context.drawTextWithShadow(this.textRenderer, "Withdrawal Ticks: " + ClientPhysiologySnapshot.withdrawalTicks, 40, 142, 0xFFE08A);
        super.render(context, mouseX, mouseY, delta);
    }
}
