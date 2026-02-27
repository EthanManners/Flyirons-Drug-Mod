package com.flyirons.drugmod.content;

import com.flyirons.drugmod.network.ModNetworking;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public final class ModCommands {
    private ModCommands() {}

    public static void bootstrap() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> register(dispatcher));
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("drugs")
                .executes(ctx -> {
                    var player = ctx.getSource().getPlayerOrThrow();
                    ModNetworking.openDrugsMenu(player);
                    ctx.getSource().sendFeedback(() -> Text.literal("Opened physiology monitor UI."), false);
                    return 1;
                }));
    }
}
