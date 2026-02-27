package com.flyirons.drugmod.block;

import com.flyirons.drugmod.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class EmberleafCropBlock extends CropBlock {
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.EMBERLEAF_SEEDS;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(floor, world, pos);
    }
}
