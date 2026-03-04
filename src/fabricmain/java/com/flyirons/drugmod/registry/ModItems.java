package com.flyirons.drugmod.registry;

import com.flyirons.drugmod.DrugConsumableItem;
import com.flyirons.drugmod.DrugMod;
import com.flyirons.drugmod.content.DrugDefinition;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {}

    private static Item.Settings settings(String id) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DrugMod.MOD_ID, id)));
    }

    public static final Item EMBERLEAF_SEEDS = register("emberleaf_seeds", new BlockItem(ModBlocks.EMBERLEAF_CROP, settings("emberleaf_seeds")));
    public static final Item EMBERLEAF_BUNDLE = register("emberleaf_bundle", new Item(settings("emberleaf_bundle")));
    public static final Item EMPTY_CART_SHELL = register("empty_cart_shell", new Item(settings("empty_cart_shell")));
    public static final Item TAINTED_POWDER = register("tainted_powder", new Item(settings("tainted_powder")));

    public static final Item GREEN_HAZE_JOINT = register("green_haze_joint", new DrugConsumableItem(settings("green_haze_joint").maxCount(16), DrugDefinition.GREEN_HAZE));
    public static final Item SPARK_CART_ITEM = register("spark_cart", new DrugConsumableItem(settings("spark_cart").maxCount(16), DrugDefinition.SPARK_CART));

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DrugMod.MOD_ID, id), item);
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(EMBERLEAF_SEEDS);
            entries.add(EMBERLEAF_BUNDLE);
            entries.add(EMPTY_CART_SHELL);
            entries.add(TAINTED_POWDER);
            entries.add(GREEN_HAZE_JOINT);
            entries.add(SPARK_CART_ITEM);
        });
    }
}
