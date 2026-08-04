package com.biggggg5.theirontanglemod.datagen;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;

public class ModModelProvider  extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, TheIronTangleMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.REMOTE_PORTAL_CONTROLLER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ANCIENT_ENGINE_MINECART.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createTrivialCube(ModBlocks.REFINED_OBSIDIAN.get());
        blockModels.createTrivialCube(ModBlocks.RAILWAY_PORTAL_BLOCK.get());
        blockModels.createPassiveRail(ModBlocks.FLINT_RAIL_BLOCK.get());
        blockModels.createHorizontallyRotatedBlock(ModBlocks.DESTINATION_CALCULATOR.get(), TexturedModel.ORIENTABLE);

        blockModels.createNonTemplateModelBlock(ModBlocks.ANCIENT_ENGINE.get());
    }
}
