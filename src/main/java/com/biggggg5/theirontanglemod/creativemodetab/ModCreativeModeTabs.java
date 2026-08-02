package com.biggggg5.theirontanglemod.creativemodetab;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheIronTangleMod.MOD_ID);



    public static final Supplier<CreativeModeTab> THE_IRON_TANGLE_TAB = CREATIVE_MODE_TABS.register("the_iron_tangle_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ANCIENT_ENGINE.get()))
                    .title(Component.translatable("creativetab.theirontanglemod.creative_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ANCIENT_ENGINE_MINECART);
                        output.accept(ModItems.REMOTE_PORTAL_CONTROLLER);
                        output.accept(ModBlocks.ANCIENT_ENGINE);
                        output.accept(ModBlocks.REFINED_OBSIDIAN);
                        output.accept(ModBlocks.DESTINATION_CALCULATOR);
                        output.accept(ModBlocks.FLINT_RAIL_BLOCK);


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
