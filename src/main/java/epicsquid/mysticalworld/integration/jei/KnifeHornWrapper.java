package epicsquid.mysticalworld.integration.jei;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.recipe.DamagedIngredient;
import epicsquid.mysticalworld.recipe.KnifeHornRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;

public class KnifeHornWrapper implements ICraftingCategoryExtension {
  public KnifeHornWrapper(KnifeHornRecipe recipe) {
  }

  @Override
  public void setIngredients(IIngredients iIngredients) {
    iIngredients.setInputIngredients(Arrays.asList(Ingredient.fromItems(Items.NAUTILUS_SHELL), DamagedIngredient.getInstance(MWTags.Items.KNIVES)));
    iIngredients.setOutput(VanillaTypes.ITEM, new ItemStack(ModItems.NAUTILUS_HORN.get(), 1));
  }

  @Nullable
  @Override
  public ResourceLocation getRegistryName() {
    return new ResourceLocation(MysticalWorld.MODID, "horn_recipe");
  }
}
