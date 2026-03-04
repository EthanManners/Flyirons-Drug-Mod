package com.flyirons.drugmod;

import com.flyirons.drugmod.network.SyncPhysiologyS2CPacket;
import com.flyirons.drugmod.registry.ModBlocks;
import com.flyirons.drugmod.registry.ModItems;
import com.flyirons.drugmod.state.PhysiologyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class DrugMod implements ModInitializer {
    public static final String MOD_ID = "flyirons_drug_mod";

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();

        PayloadTypeRegistry.playS2C().register(SyncPhysiologyS2CPacket.ID, SyncPhysiologyS2CPacket.CODEC);

        ServerTickEvents.END_SERVER_TICK.register(server -> server.getPlayerManager().getPlayerList().forEach(PhysiologyManager::tickPlayer));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> dispatcher.register(
                CommandManager.literal("drugs").executes(ctx -> {
                    ctx.getSource().sendFeedback(() -> Text.literal("Use keybind [J] for physiology panel."), false);
                    return 1;
                })
        ));
    }
}
