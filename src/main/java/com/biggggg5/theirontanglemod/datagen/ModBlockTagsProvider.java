package com.biggggg5.theirontanglemod.datagen;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, TheIronTangleMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.REFINED_OBSIDIAN.get())
                .add(ModBlocks.FLINT_RAIL_BLOCK.get())
                .add(ModBlocks.DESTINATION_CALCULATOR.get())
                .add(ModBlocks.ANCIENT_ENGINE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.REFINED_OBSIDIAN.get());

        tag(BlockTags.RAILS)
                .add(ModBlocks.FLINT_RAIL_BLOCK.get());

    }
}
