package com.flyirons.drugmod.client;

import com.flyirons.drugmod.network.ModNetworking;
import com.flyirons.drugmod.ui.DrugsMainScreen;
import com.flyirons.drugmod.ui.PhysiologyHud;
import com.flyirons.drugmod.ui.PhysiologyViewModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class DrugModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ModNetworking.PHYSIOLOGY_SYNC, (client, handler, buf, responseSender) -> {
            String focus = buf.readString();
            float potency = buf.readFloat();
            float tolerance = buf.readFloat();
            float addiction = buf.readFloat();
            int withdrawal = buf.readInt();
            float overdose = buf.readFloat();
            client.execute(() -> PhysiologyViewModel.update(focus, potency, tolerance, addiction, withdrawal, overdose));
        });

        ClientPlayNetworking.registerGlobalReceiver(ModNetworking.OPEN_DRUGS_MENU, (client, handler, buf, responseSender) ->
                client.execute(() -> client.setScreen(new DrugsMainScreen()))
        );

        PhysiologyHud.bootstrap();
    }
}
