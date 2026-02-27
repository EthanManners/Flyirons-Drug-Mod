package com.flyirons.drugmod.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SyncPhysiologyS2CPacket(String drugId, float tolerance, float addiction, float overdose, int withdrawalTicks) implements CustomPayload {
    public static final Id<SyncPhysiologyS2CPacket> ID = new Id<>(Identifier.of("flyirons_drug_mod", "sync_physiology"));
    public static final PacketCodec<PacketByteBuf, SyncPhysiologyS2CPacket> CODEC = PacketCodec.of(
            (value, buf) -> {
                buf.writeString(value.drugId);
                buf.writeFloat(value.tolerance);
                buf.writeFloat(value.addiction);
                buf.writeFloat(value.overdose);
                buf.writeInt(value.withdrawalTicks);
            },
            buf -> new SyncPhysiologyS2CPacket(buf.readString(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readInt())
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
