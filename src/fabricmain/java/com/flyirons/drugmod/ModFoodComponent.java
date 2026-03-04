package com.flyirons.drugmod;

import net.minecraft.component.type.FoodComponent;

public final class ModFoodComponent {
    private ModFoodComponent() {}

    public static final FoodComponent DRUG_FOOD = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.0f)
            .alwaysEdible()
            .build();
}
