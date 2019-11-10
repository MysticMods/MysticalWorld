package epicsquid.mysticalworld.integration.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class SpecialRecipeWrapper<T extends SpecialRecipe> implements ICraftingCategoryExtension {
  private final T recipe;

  public SpecialRecipeWrapper(T recipe) {
    this.recipe = recipe;
  }

  @Override
  public void setIngredients(IIngredients iIngredients) {
    iIngredients.setInputIngredients(recipe.getIngredients());
    iIngredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
  }

  @Nullable
  @Override
  public ResourceLocation getRegistryName() {
    return recipe.getId();
  }
}
