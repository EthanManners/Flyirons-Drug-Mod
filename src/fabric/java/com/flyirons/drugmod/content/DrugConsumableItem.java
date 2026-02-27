package com.flyirons.drugmod.content;

import com.flyirons.drugmod.physiology.PlayerPhysiologyManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DrugConsumableItem extends Item {
    private final DrugDefinition definition;

    public DrugConsumableItem(Settings settings, DrugDefinition definition) {
        super(settings);
        this.definition = definition;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            PlayerPhysiologyManager.applyDose(serverPlayer, definition);
            if (!user.isCreative()) {
                stack.decrement(1);
            }
        }
        return ActionResult.SUCCESS;
    }
}
