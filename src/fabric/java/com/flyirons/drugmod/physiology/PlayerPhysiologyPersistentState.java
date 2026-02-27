package com.flyirons.drugmod.physiology;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

public class PlayerPhysiologyPersistentState extends PersistentState {
    public static final String ID = "flyirons_player_physiology";

    private final Map<UUID, Map<String, DrugUseState>> playerStates = new HashMap<>();

    public static PlayerPhysiologyPersistentState get(MinecraftServer server) {
        PersistentStateManager manager = server.getOverworld().getPersistentStateManager();
        return manager.getOrCreate(PlayerPhysiologyPersistentState.TYPE, ID);
    }

    public static final Type<PlayerPhysiologyPersistentState> TYPE = new Type<>(
            PlayerPhysiologyPersistentState::new,
            PlayerPhysiologyPersistentState::fromNbt,
            null
    );

    private static PlayerPhysiologyPersistentState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        PlayerPhysiologyPersistentState state = new PlayerPhysiologyPersistentState();
        NbtList players = nbt.getList("players", NbtElement.COMPOUND_TYPE);
        for (NbtElement element : players) {
            NbtCompound playerNbt = (NbtCompound) element;
            UUID playerId = playerNbt.getUuid("uuid");
            NbtCompound drugs = playerNbt.getCompound("drugs");
            Map<String, DrugUseState> drugMap = new HashMap<>();
            for (String key : drugs.getKeys()) {
                drugMap.put(key, DrugUseState.fromNbt(drugs.getCompound(key)));
            }
            state.playerStates.put(playerId, drugMap);
        }
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        NbtList players = new NbtList();
        for (Map.Entry<UUID, Map<String, DrugUseState>> playerEntry : playerStates.entrySet()) {
            NbtCompound playerNbt = new NbtCompound();
            playerNbt.putUuid("uuid", playerEntry.getKey());
            NbtCompound drugs = new NbtCompound();
            for (Map.Entry<String, DrugUseState> drugEntry : playerEntry.getValue().entrySet()) {
                drugs.put(drugEntry.getKey(), drugEntry.getValue().toNbt());
            }
            playerNbt.put("drugs", drugs);
            players.add(playerNbt);
        }
        nbt.put("players", players);
        return nbt;
    }

    public Map<String, DrugUseState> getPlayer(UUID id) {
        return playerStates.computeIfAbsent(id, ignored -> new HashMap<>());
    }
}
