package epicsquid.mysticalworld.recipe.ingredients;

import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CompoundIngredient;

import java.util.Arrays;
import java.util.List;

public class AccessibleCompoundIngredient extends CompoundIngredient {
  public AccessibleCompoundIngredient(Ingredient ... ingredients) {
    super(Arrays.asList(ingredients));
  }

  public AccessibleCompoundIngredient(List<Ingredient> children) {
    super(children);
  }
}
