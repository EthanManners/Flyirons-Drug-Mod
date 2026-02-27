package com.flyirons.drugmod.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PhysiologyPersistentState extends PersistentState {
    private final Map<UUID, PlayerPhysiology> players = new HashMap<>();

    public PlayerPhysiology forPlayer(ServerPlayerEntity player) {
        markDirty();
        return players.computeIfAbsent(player.getUuid(), ignored -> new PlayerPhysiology());
    }

    public PlayerPhysiology peek(UUID uuid) {
        return players.get(uuid);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList playersList = new NbtList();
        for (Map.Entry<UUID, PlayerPhysiology> entry : players.entrySet()) {
            NbtCompound p = new NbtCompound();
            p.putUuid("uuid", entry.getKey());
            p.putFloat("poly", entry.getValue().polyUseRisk);
            NbtList stats = new NbtList();
            entry.getValue().perDrug.forEach((drugId, stat) -> {
                NbtCompound s = new NbtCompound();
                s.putString("id", drugId);
                s.putFloat("tol", stat.tolerance);
                s.putFloat("add", stat.addiction);
                s.putFloat("od", stat.overdoseRisk);
                s.putLong("last", stat.lastDoseTick);
                s.putInt("wd", stat.withdrawalTicks);
                stats.add(s);
            });
            p.put("stats", stats);
            playersList.add(p);
        }
        nbt.put("players", playersList);
        return nbt;
    }

    public static PhysiologyPersistentState fromNbt(NbtCompound nbt) {
        PhysiologyPersistentState state = new PhysiologyPersistentState();
        NbtList playersList = nbt.getList("players", NbtElement.COMPOUND_TYPE);
        for (NbtElement e : playersList) {
            NbtCompound p = (NbtCompound) e;
            PlayerPhysiology physiology = new PlayerPhysiology();
            physiology.polyUseRisk = p.getFloat("poly");
            NbtList statsList = p.getList("stats", NbtElement.COMPOUND_TYPE);
            for (NbtElement se : statsList) {
                NbtCompound s = (NbtCompound) se;
                DrugStat stat = new DrugStat();
                stat.tolerance = s.getFloat("tol");
                stat.addiction = s.getFloat("add");
                stat.overdoseRisk = s.getFloat("od");
                stat.lastDoseTick = s.getLong("last");
                stat.withdrawalTicks = s.getInt("wd");
                physiology.perDrug.put(s.getString("id"), stat);
            }
            state.players.put(p.getUuid("uuid"), physiology);
        }
        return state;
    }

    public static PhysiologyPersistentState get(MinecraftServer server) {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
        return manager.getOrCreate(PhysiologyPersistentState::fromNbt, PhysiologyPersistentState::new, "flyirons_drug_physiology");
    }
}
