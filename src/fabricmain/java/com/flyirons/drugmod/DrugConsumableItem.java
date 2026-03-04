package com.flyirons.drugmod;

import com.flyirons.drugmod.content.DrugDefinition;
import com.flyirons.drugmod.state.PhysiologyManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DrugConsumableItem extends Item {
    private final DrugDefinition definition;

    public DrugConsumableItem(Settings settings, DrugDefinition definition) {
        super(settings.food(ModFoodComponent.DRUG_FOOD));
        this.definition = definition;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);
        if (!world.isClient && user instanceof net.minecraft.server.network.ServerPlayerEntity player) {
            PhysiologyManager.applyDose(player, definition);
        }
        return result;
    }
}
