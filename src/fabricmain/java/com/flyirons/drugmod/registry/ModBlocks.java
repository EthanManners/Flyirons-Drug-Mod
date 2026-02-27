package com.flyirons.drugmod.registry;

import com.flyirons.drugmod.DrugMod;
import com.flyirons.drugmod.block.CompressionBenchBlock;
import com.flyirons.drugmod.block.EmberleafCropBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class ModBlocks {
    private ModBlocks() {}

    private static RegistryKey<Block> blockKey(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(DrugMod.MOD_ID, id));
    }

    public static final Block EMBERLEAF_CROP = Registry.register(Registries.BLOCK,
            Identifier.of(DrugMod.MOD_ID, "emberleaf_crop"),
            new EmberleafCropBlock(AbstractBlock.Settings.create()
                    .registryKey(blockKey("emberleaf_crop"))
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)));

    public static final Block COMPRESSION_BENCH = Registry.register(Registries.BLOCK,
            Identifier.of(DrugMod.MOD_ID, "compression_bench"),
            new CompressionBenchBlock(AbstractBlock.Settings.create()
                    .registryKey(blockKey("compression_bench"))
                    .strength(2.2f)
                    .sounds(BlockSoundGroup.METAL)));

    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of(DrugMod.MOD_ID, "compression_bench"),
                new BlockItem(COMPRESSION_BENCH, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DrugMod.MOD_ID, "compression_bench")))));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(COMPRESSION_BENCH));
    }
}
