/*package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.recipe.DamagedIngredient;
import epicsquid.mysticalworld.recipe.FlintAndSteelRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;

public class FlintAndSteelWrapper implements ICraftingCategoryExtension {
  public FlintAndSteelWrapper(FlintAndSteelRecipe recipe) {
  }

  @Override
  public void setIngredients(IIngredients iIngredients) {
    iIngredients.setInputIngredients(Arrays.asList(Ingredient.fromTag(ItemTags.LOGS), DamagedIngredient.getInstance(Items.FLINT_AND_STEEL)));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModBlocks.CHARRED_LOG.get()));
  }

  @Nullable
  @Override
  public ResourceLocation getRegistryName() {
    return new ResourceLocation(MysticalWorld.MODID, "flint_and_steel_recipe");
  }
}*/
