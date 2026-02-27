package com.flyirons.drugmod.physiology;

import com.flyirons.drugmod.content.DrugDefinition;
import com.flyirons.drugmod.network.ModNetworking;
import java.util.Comparator;
import java.util.Map;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public final class PlayerPhysiologyManager {
    private PlayerPhysiologyManager() {}

    public static void applyDose(ServerPlayerEntity player, DrugDefinition drug) {
        var persistent = PlayerPhysiologyPersistentState.get(player.getServer());
        DrugUseState state = persistent.getPlayer(player.getUuid()).computeIfAbsent(drug.id(), id -> new DrugUseState());
        float potency = Math.max(0.2f, drug.basePotency() - state.tolerance);

        state.activeTicks = Math.max(state.activeTicks, drug.effectTicks());
        state.tolerance = Math.min(2.0f, state.tolerance + drug.toleranceGain());
        state.addiction = Math.min(2.0f, state.addiction + drug.addictionGain());
        state.overdoseRisk = Math.min(2.5f, state.overdoseRisk + drug.overdosePressure());
        state.withdrawalTicks = drug.withdrawalDelayTicks();

        if (drug.family().name().equals("STIMULANT")) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 20, potency > 1f ? 1 : 0));
        } else {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 12, 0));
        }

        persistent.markDirty();
        ModNetworking.sendSnapshot(player, snapshot(player));
    }

    public static void tickServer(MinecraftServer server) {
        var persistent = PlayerPhysiologyPersistentState.get(server);
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            Map<String, DrugUseState> drugs = persistent.getPlayer(player.getUuid());
            for (DrugUseState state : drugs.values()) {
                if (state.activeTicks > 0) {
                    state.activeTicks--;
                }
                if (state.withdrawalTicks > 0) {
                    state.withdrawalTicks--;
                } else if (state.addiction > 0.3f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 0, true, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0, true, false));
                }
                state.tolerance = Math.max(0, state.tolerance - 0.0008f);
                state.overdoseRisk = Math.max(0, state.overdoseRisk - 0.0005f);
            }
            if (server.getTicks() % 40 == 0) {
                ModNetworking.sendSnapshot(player, snapshot(player));
            }
        }
        persistent.markDirty();
    }

    public static PlayerPhysiologySnapshot snapshot(ServerPlayerEntity player) {
        var persistent = PlayerPhysiologyPersistentState.get(player.getServer());
        var entry = persistent.getPlayer(player.getUuid()).entrySet().stream()
                .max(Comparator.comparingInt(v -> v.getValue().activeTicks))
                .orElse(null);

        if (entry == null) {
            return new PlayerPhysiologySnapshot("none", 100, 0, 0, 0, 0);
        }
        DrugUseState state = entry.getValue();
        float potency = Math.max(20f, 100f * (1f - (state.tolerance * 0.5f)));
        return new PlayerPhysiologySnapshot(entry.getKey(), potency, state.tolerance, state.addiction, state.withdrawalTicks, state.overdoseRisk);
    }
}
