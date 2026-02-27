package com.flyirons.drugmod.content;

public record DrugDefinition(
        String id,
        DrugFamily family,
        int effectTicks,
        float basePotency,
        float toleranceGain,
        float addictionGain,
        float overdosePressure,
        int withdrawalDelayTicks
) {
}
