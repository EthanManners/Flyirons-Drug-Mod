package com.flyirons.drugmod.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class DrugsMainScreen extends Screen {
    public DrugsMainScreen() {
        super(Text.literal("Drug Physiology"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int x = this.width / 2 - 110;
        int y = this.height / 2 - 70;
        context.fill(x, y, x + 220, y + 140, 0xA0000000);

        context.drawText(this.textRenderer, "Focus Drug: " + PhysiologyViewModel.focusDrug(), x + 12, y + 16, 0xFFFFFF, false);
        context.drawText(this.textRenderer, String.format("Potency: %.1f%%", PhysiologyViewModel.potency()), x + 12, y + 36, 0x88FF88, false);
        context.drawText(this.textRenderer, String.format("Tolerance: %.2f", PhysiologyViewModel.tolerance()), x + 12, y + 54, 0xFFD37F, false);
        context.drawText(this.textRenderer, String.format("Addiction: %.2f", PhysiologyViewModel.addiction()), x + 12, y + 72, 0xFF8B8B, false);
        context.drawText(this.textRenderer, "Withdrawal timer: " + PhysiologyViewModel.withdrawalTicks() / 20 + "s", x + 12, y + 90, 0xAAAAFF, false);
        context.drawText(this.textRenderer, String.format("Overdose pressure: %.2f", PhysiologyViewModel.overdoseRisk()), x + 12, y + 108, 0xFF5555, false);
    }
}
