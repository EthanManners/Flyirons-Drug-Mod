package com.flyirons.drugmod.content;

public record DrugDefinition(
        String id,
        DrugFamily family,
        int baseDurationTicks,
        float toleranceGain,
        float addictionGain,
        float overdosePressure,
        float withdrawalSeverity
) {
    public static final DrugDefinition GREEN_HAZE = new DrugDefinition(
            "green_haze", DrugFamily.CANNABIS, 20 * 45, 0.08f, 0.05f, 0.03f, 0.4f
    );

    public static final DrugDefinition SPARK_CART = new DrugDefinition(
            "spark_cart", DrugFamily.STIMULANT, 20 * 30, 0.11f, 0.08f, 0.09f, 0.6f
    );
}
