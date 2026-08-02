package com.biggggg5.theirontanglemod.item;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.item.custom.ControllerItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MinecartItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TheIronTangleMod.MOD_ID);


    public static final DeferredItem<Item> REMOTE_PORTAL_CONTROLLER = ITEMS.registerItem("remote_portal_controller",
            properties -> new ControllerItem(properties.stacksTo(1)));
    public static final DeferredItem<Item> ANCIENT_ENGINE_MINECART = ITEMS.registerItem("ancient_engine_minecart",
            properties -> new MinecartItem(EntityType.FURNACE_MINECART, properties.stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
