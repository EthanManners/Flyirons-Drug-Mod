package com.flyirons.drugmod.block;

import com.flyirons.drugmod.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class CompressionBenchBlock extends Block {
    public CompressionBenchBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, net.minecraft.util.math.BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        ItemStack leaf = findAndConsume(player, ModItems.EMBERLEAF_BUNDLE.getDefaultStack().getItem());
        ItemStack shell = findAndConsume(player, ModItems.EMPTY_CART_SHELL.getDefaultStack().getItem());
        if (!leaf.isEmpty() && !shell.isEmpty()) {
            player.giveItemStack(ModItems.SPARK_CART_ITEM.getDefaultStack());
            player.sendMessage(Text.literal("Compression bench output: Spark Cart"), true);
            if (world instanceof ServerWorld serverWorld && serverWorld.random.nextFloat() < 0.12f) {
                player.giveItemStack(ModItems.TAINTED_POWDER.getDefaultStack());
            }
            return ActionResult.CONSUME;
        }
        player.sendMessage(Text.literal("Need Emberleaf Bundle + Empty Cart Shell"), true);
        return ActionResult.PASS;
    }

    private ItemStack findAndConsume(PlayerEntity player, net.minecraft.item.Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                stack.decrement(1);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
