package epicsquid.mysticalworld.potions;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionRecipes {
  public static void registerRecipes() {
    BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE)), Ingredient.fromItems(ModItems.ANTLERS.get()), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), Potions.LEAPING));
  }
}
