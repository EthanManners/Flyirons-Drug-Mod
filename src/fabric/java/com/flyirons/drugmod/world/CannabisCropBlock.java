package com.flyirons.drugmod.world;

import com.flyirons.drugmod.content.ModItems;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;

public class CannabisCropBlock extends CropBlock {
    public CannabisCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.CANNABIS_SEEDS;
    }
}
