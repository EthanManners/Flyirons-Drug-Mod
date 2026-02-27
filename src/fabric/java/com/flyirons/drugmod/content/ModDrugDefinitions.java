package com.flyirons.drugmod.content;

import java.util.HashMap;
import java.util.Map;

public final class ModDrugDefinitions {
    private static final Map<String, DrugDefinition> DRUGS = new HashMap<>();

    private ModDrugDefinitions() {}

    public static DrugDefinition CANNABIS_BLOOM;
    public static DrugDefinition SPARK_DUST;

    public static void bootstrap() {
        CANNABIS_BLOOM = register(new DrugDefinition("cannabis_bloom", DrugFamily.CANNABIS, 20 * 90, 1.0f, 0.06f, 0.04f, 0.03f, 20 * 100));
        SPARK_DUST = register(new DrugDefinition("spark_dust", DrugFamily.STIMULANT, 20 * 65, 1.3f, 0.12f, 0.07f, 0.1f, 20 * 120));
    }

    public static DrugDefinition get(String id) {
        return DRUGS.get(id);
    }

    private static DrugDefinition register(DrugDefinition definition) {
        DRUGS.put(definition.id(), definition);
        return definition;
    }
}
