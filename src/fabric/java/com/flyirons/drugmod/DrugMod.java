package com.flyirons.drugmod;

import com.flyirons.drugmod.content.ModBlocks;
import com.flyirons.drugmod.content.ModCommands;
import com.flyirons.drugmod.content.ModDrugDefinitions;
import com.flyirons.drugmod.content.ModItems;
import com.flyirons.drugmod.network.ModNetworking;
import com.flyirons.drugmod.physiology.PlayerPhysiologyManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrugMod implements ModInitializer {
    public static final String MOD_ID = "flyirons_drug_mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModDrugDefinitions.bootstrap();
        ModItems.bootstrap();
        ModBlocks.bootstrap();
        ModNetworking.bootstrap();
        ModCommands.bootstrap();

        ServerTickEvents.END_SERVER_TICK.register(PlayerPhysiologyManager::tickServer);
        LOGGER.info("Flyirons Drug Mod initialized");
    }
}
