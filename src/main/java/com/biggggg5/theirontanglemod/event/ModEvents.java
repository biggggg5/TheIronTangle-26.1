package com.biggggg5.theirontanglemod.event;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.entity.custom.DestinationCalculatorBlockEntity;
import com.biggggg5.theirontanglemod.networking.ClientPayloadHandler;
import com.biggggg5.theirontanglemod.networking.packet.SaveTextPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TheIronTangleMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.MAIN);

        registrar.playToServer(SaveTextPayload.TYPE, SaveTextPayload.STREAM_CODEC, (payload, context) -> {
            if (context.player().level().getBlockEntity(payload.pos()) instanceof DestinationCalculatorBlockEntity be) {
                be.setPortalName(payload.text());
            }
        });
    }
}