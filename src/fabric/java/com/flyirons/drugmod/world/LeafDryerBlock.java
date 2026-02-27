package com.flyirons.drugmod.world;

import com.flyirons.drugmod.content.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class LeafDryerBlock extends Block {
    public LeafDryerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, net.minecraft.util.math.BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        ItemStack held = player.getStackInHand(Hand.MAIN_HAND);
        if (held.isOf(ModItems.CANNABIS_BUD)) {
            held.decrement(1);
            player.giveItemStack(new ItemStack(ModItems.CANNABIS_BLOOM));
            player.sendMessage(Text.literal("Leaf Dryer produced Cannabis Bloom."), true);
            return ActionResult.CONSUME;
        }

        if (held.isOf(ModItems.CRUDE_SPARK)) {
            held.decrement(1);
            player.giveItemStack(new ItemStack(ModItems.SPARK_DUST));
            player.incrementStat(Stats.USED.getOrCreateStat(asItem()));
            if (player instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.sendMessage(Text.literal("Leaf Dryer refined Spark Dust. Package it before sale."), false);
            }
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}
