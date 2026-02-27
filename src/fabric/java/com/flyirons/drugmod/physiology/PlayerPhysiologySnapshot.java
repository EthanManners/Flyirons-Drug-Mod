package com.flyirons.drugmod.physiology;

public record PlayerPhysiologySnapshot(
        String focusDrug,
        float potencyPercent,
        float tolerance,
        float addiction,
        int withdrawalTicks,
        float overdoseRisk
) {
}
