package com.flyirons.drugmod.state;

import java.util.HashMap;
import java.util.Map;

public class PlayerPhysiology {
    public final Map<String, DrugStat> perDrug = new HashMap<>();
    public float polyUseRisk;

    public DrugStat getOrCreate(String id) {
        return perDrug.computeIfAbsent(id, ignored -> new DrugStat());
    }
}
