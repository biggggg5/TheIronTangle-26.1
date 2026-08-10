package com.biggggg5.theirontanglemod.datagen;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.block.ModBlocks;
import com.biggggg5.theirontanglemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "TheIronTangle Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

        shaped(RecipeCategory.TRANSPORTATION, ModBlocks.FLINT_RAIL_BLOCK.get(), 3)
                .pattern("FFF")
                .pattern("RRR")
                .define('F', Items.FLINT)
                .define('R', Items.RAIL)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .group("tangle")
                .save(output);

        shaped(RecipeCategory.TRANSPORTATION, ModBlocks.DESTINATION_CALCULATOR.get())
                .pattern("ROR")
                .pattern("OGO")
                .pattern("IOI")
                .define('R', Blocks.REDSTONE_BLOCK)
                .define('O', ModBlocks.REFINED_OBSIDIAN.get())
                .define('G', Blocks.GLASS)
                .define('I', Blocks.IRON_BLOCK)
                .unlockedBy(getHasName(ModBlocks.REFINED_OBSIDIAN.get()), has(ModBlocks.REFINED_OBSIDIAN.get()))
                .group("tangle")
                .save(output);

        shaped(RecipeCategory.TRANSPORTATION, ModItems.REMOTE_PORTAL_CONTROLLER.get())
                .pattern(" I ")
                .pattern("RGR")
                .pattern("IRI")
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .define('G', Blocks.GLASS)
                .unlockedBy(getHasName(ModBlocks.DESTINATION_CALCULATOR.get()), has(ModBlocks.DESTINATION_CALCULATOR.get()))
                .group("tangle")
                .save(output);

        shapeless(RecipeCategory.TRANSPORTATION, ModItems.ANCIENT_ENGINE_MINECART.get())
                .requires(ModBlocks.ANCIENT_ENGINE.get())
                .requires(Items.MINECART)
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ENGINE.get()), has(ModBlocks.ANCIENT_ENGINE.get()))
                .group("tangle")
                .save(output);

        List<ItemLike> TANGLESMELT = List.of(Blocks.OBSIDIAN);

        oreSmelting(TANGLESMELT, RecipeCategory.TRANSPORTATION, CookingBookCategory.BLOCKS, ModBlocks.REFINED_OBSIDIAN.get(), 0.25f, 200, "tangle");
        oreBlasting(TANGLESMELT, RecipeCategory.TRANSPORTATION, CookingBookCategory.BLOCKS, ModBlocks.REFINED_OBSIDIAN.get(), 0.25f, 100, "tangle");

    }
    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, TheIronTangleMod.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }
}

