package com.flyirons.drugmod.content;

import com.flyirons.drugmod.DrugMod;
import com.flyirons.drugmod.world.CannabisCropBlock;
import com.flyirons.drugmod.world.LeafDryerBlock;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class ModBlocks {
    public static final Block CANNABIS_CROP = new CannabisCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).mapColor(MapColor.DARK_GREEN));
    public static final Block LEAF_DRYER = new LeafDryerBlock(AbstractBlock.Settings.create().strength(2.5f).sounds(BlockSoundGroup.STONE).pistonBehavior(PistonBehavior.BLOCK));

    private ModBlocks() {}

    public static void bootstrap() {
        Registry.register(Registries.BLOCK, Identifier.of(DrugMod.MOD_ID, "cannabis_crop"), CANNABIS_CROP);
        Registry.register(Registries.BLOCK, Identifier.of(DrugMod.MOD_ID, "leaf_dryer"), LEAF_DRYER);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CANNABIS_BUD, 0.3f);
    }
}
