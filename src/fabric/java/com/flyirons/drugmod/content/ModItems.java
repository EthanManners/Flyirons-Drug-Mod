package com.flyirons.drugmod.content;

import com.flyirons.drugmod.DrugMod;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
    public static final Item CANNABIS_SEEDS = new AliasedBlockItem(ModBlocks.CANNABIS_CROP, new Item.Settings());
    public static final Item CANNABIS_BUD = new Item(new Item.Settings());
    public static final Item CANNABIS_BLOOM = new DrugConsumableItem(new Item.Settings().maxCount(16), ModDrugDefinitions.CANNABIS_BLOOM);

    public static final Item CRUDE_SPARK = new Item(new Item.Settings());
    public static final Item SPARK_DUST = new DrugConsumableItem(new Item.Settings().maxCount(16), ModDrugDefinitions.SPARK_DUST);
    public static final Item PACKAGED_SPARK_DUST = new DrugConsumableItem(new Item.Settings().maxCount(16), ModDrugDefinitions.SPARK_DUST);

    public static final Item LEAF_DRYER_ITEM = new BlockItem(ModBlocks.LEAF_DRYER, new Item.Settings());

    private ModItems() {}

    public static void bootstrap() {
        register("cannabis_seeds", CANNABIS_SEEDS);
        register("cannabis_bud", CANNABIS_BUD);
        register("cannabis_bloom", CANNABIS_BLOOM);
        register("crude_spark", CRUDE_SPARK);
        register("spark_dust", SPARK_DUST);
        register("packaged_spark_dust", PACKAGED_SPARK_DUST);
        register("leaf_dryer", LEAF_DRYER_ITEM);
    }

    private static void register(String id, Item item) {
        Registry.register(Registries.ITEM, Identifier.of(DrugMod.MOD_ID, id), item);
    }
}
