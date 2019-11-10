package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.Tags;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.recipe.DamagedIngredient;
import epicsquid.mysticalworld.recipe.KnifeRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;

public class KnifeWrapper implements ICraftingCategoryExtension {
  public KnifeWrapper(KnifeRecipe recipe) {
  }

  @Override
  public void setIngredients(IIngredients iIngredients) {
    iIngredients.setInputIngredients(Arrays.asList(Ingredient.fromItems(Items.CARROT), DamagedIngredient.getInstance(Tags.Items.KNIVES)));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModItems.SLICED_CARROT.get(), 4));
  }

  @Nullable
  @Override
  public ResourceLocation getRegistryName() {
    return new ResourceLocation(MysticalWorld.MODID, "spindle_recipe");
  }
}
