package com.flyirons.drugmod.physiology;

import net.minecraft.nbt.NbtCompound;

public class DrugUseState {
    public float tolerance;
    public float addiction;
    public int activeTicks;
    public int withdrawalTicks;
    public float overdoseRisk;

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putFloat("tolerance", tolerance);
        nbt.putFloat("addiction", addiction);
        nbt.putInt("activeTicks", activeTicks);
        nbt.putInt("withdrawalTicks", withdrawalTicks);
        nbt.putFloat("overdoseRisk", overdoseRisk);
        return nbt;
    }

    public static DrugUseState fromNbt(NbtCompound nbt) {
        DrugUseState state = new DrugUseState();
        state.tolerance = nbt.getFloat("tolerance");
        state.addiction = nbt.getFloat("addiction");
        state.activeTicks = nbt.getInt("activeTicks");
        state.withdrawalTicks = nbt.getInt("withdrawalTicks");
        state.overdoseRisk = nbt.getFloat("overdoseRisk");
        return state;
    }
}
