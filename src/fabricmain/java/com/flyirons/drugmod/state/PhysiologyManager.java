package com.flyirons.drugmod.state;

import com.flyirons.drugmod.content.DrugDefinition;
import com.flyirons.drugmod.network.SyncPhysiologyS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class PhysiologyManager {
    private PhysiologyManager() {}

    public static void applyDose(ServerPlayerEntity player, DrugDefinition definition) {
        PlayerPhysiology physiology = PhysiologyPersistentState.get(player.getServer()).forPlayer(player);
        DrugStat stat = physiology.getOrCreate(definition.id());
        float potencyMultiplier = Math.max(0.2f, 1.0f - stat.tolerance);

        stat.tolerance = Math.min(0.95f, stat.tolerance + definition.toleranceGain());
        stat.addiction = Math.min(1.0f, stat.addiction + definition.addictionGain());
        stat.overdoseRisk = Math.min(1.0f, stat.overdoseRisk + definition.overdosePressure() + physiology.polyUseRisk * 0.2f);
        stat.lastDoseTick = player.getServerWorld().getTime();
        stat.withdrawalTicks = 0;
        physiology.polyUseRisk = Math.min(1.0f, physiology.polyUseRisk + 0.08f);

        int effectiveDuration = (int) (definition.baseDurationTicks() * potencyMultiplier);
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, effectiveDuration, 0));
        if (stat.overdoseRisk > 0.65f) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 8, 0));
            player.sendMessage(Text.literal("[DrugMod] Warning: overdose pressure rising."), true);
        }

        ServerPlayNetworking.send(player, new SyncPhysiologyS2CPacket(definition.id(), stat.tolerance, stat.addiction, stat.overdoseRisk, stat.withdrawalTicks));
    }

    public static void tickPlayer(ServerPlayerEntity player) {
        PlayerPhysiology physiology = PhysiologyPersistentState.get(player.getServer()).forPlayer(player);
        long time = player.getServerWorld().getTime();
        physiology.polyUseRisk = Math.max(0f, physiology.polyUseRisk - 0.0015f);

        physiology.perDrug.forEach((drugId, stat) -> {
            if (stat.addiction > 0.35f && time - stat.lastDoseTick > 20L * 120L) {
                stat.withdrawalTicks = Math.min(20 * 60, stat.withdrawalTicks + 20);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
            }
            stat.overdoseRisk = Math.max(0f, stat.overdoseRisk - 0.0008f);
            stat.tolerance = Math.max(0f, stat.tolerance - 0.0002f);
        });
    }
}
