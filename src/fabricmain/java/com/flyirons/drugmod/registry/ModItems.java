package com.flyirons.drugmod.registry;

import com.flyirons.drugmod.DrugConsumableItem;
import com.flyirons.drugmod.DrugMod;
import com.flyirons.drugmod.content.DrugDefinition;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
    private ModItems() {}

    public static final Item EMBERLEAF_SEEDS = register("emberleaf_seeds", new AliasedBlockItem(ModBlocks.EMBERLEAF_CROP, new Item.Settings()));
    public static final Item EMBERLEAF_BUNDLE = register("emberleaf_bundle", new Item(new Item.Settings()));
    public static final Item EMPTY_CART_SHELL = register("empty_cart_shell", new Item(new Item.Settings()));
    public static final Item TAINTED_POWDER = register("tainted_powder", new Item(new Item.Settings()));

    public static final Item GREEN_HAZE_JOINT = register("green_haze_joint", new DrugConsumableItem(new Item.Settings().maxCount(16), DrugDefinition.GREEN_HAZE));
    public static final Item SPARK_CART_ITEM = register("spark_cart", new DrugConsumableItem(new Item.Settings().maxCount(16), DrugDefinition.SPARK_CART));

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
