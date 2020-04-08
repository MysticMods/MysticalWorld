/*package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.recipe.DamagedIngredient;
import epicsquid.mysticalworld.recipe.SpindleRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;

public class SpindleWrapper implements ICraftingCategoryExtension {
  public SpindleWrapper(SpindleRecipe recipe) {
  }

  @Override
  public void setIngredients(IIngredients iIngredients) {
    iIngredients.setInputIngredients(Arrays.asList(Ingredient.fromItems(ModItems.SILK_COCOON.get()), DamagedIngredient.getInstance(ModItems.SPINDLE.get())));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModItems.SILK_THREAD.get(), 9));
  }

  @Nullable
  @Override
  public ResourceLocation getRegistryName() {
    return new ResourceLocation(MysticalWorld.MODID, "spindle_recipe");
  }
}*/
