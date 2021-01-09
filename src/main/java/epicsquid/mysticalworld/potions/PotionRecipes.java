package epicsquid.mysticalworld.potions;

import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEffects;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.LootTable;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionRecipes {
  public static void registerRecipes() {
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.fromItems(ModItems.ANTLERS.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.STRENGTH));
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.fromTag(MWTags.Items.AMETHYST_GEM), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), ModEffects.SERENDIPITY_POTION.get()));
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.fromItems(ModBlocks.STONEPETAL.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LEAPING));
  }
}
