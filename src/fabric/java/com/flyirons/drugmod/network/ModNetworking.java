package com.flyirons.drugmod.network;

import com.flyirons.drugmod.DrugMod;
import com.flyirons.drugmod.physiology.PlayerPhysiologySnapshot;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class ModNetworking {
    public static final Identifier PHYSIOLOGY_SYNC = Identifier.of(DrugMod.MOD_ID, "physiology_sync");
    public static final Identifier OPEN_DRUGS_MENU = Identifier.of(DrugMod.MOD_ID, "open_drugs_menu");

    private ModNetworking() {}

    public static void bootstrap() {}

    public static void sendSnapshot(ServerPlayerEntity player, PlayerPhysiologySnapshot snapshot) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(snapshot.focusDrug());
        buf.writeFloat(snapshot.potencyPercent());
        buf.writeFloat(snapshot.tolerance());
        buf.writeFloat(snapshot.addiction());
        buf.writeInt(snapshot.withdrawalTicks());
        buf.writeFloat(snapshot.overdoseRisk());
        ServerPlayNetworking.send(player, PHYSIOLOGY_SYNC, buf);
    }

    public static void openDrugsMenu(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, OPEN_DRUGS_MENU, PacketByteBufs.empty());
    }
}
