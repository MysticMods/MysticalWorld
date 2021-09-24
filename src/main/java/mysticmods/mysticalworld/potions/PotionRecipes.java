package mysticmods.mysticalworld.potions;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModEffects;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import noobanidus.libs.noobutil.types.LazyIngredient;

public class PotionRecipes {
  public static void registerRecipes() {

    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.of(ModItems.ANTLERS.get())), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH));
    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), new LazyIngredient(() -> Ingredient.of(MWTags.Items.AMETHYST_GEM)), PotionUtils.setPotion(new ItemStack(Items.POTION), ModEffects.SERENDIPITY_POTION.get()));
    BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE)), new LazyIngredient(() -> Ingredient.of(ModBlocks.STONEPETAL.get())), PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING));
  }
}