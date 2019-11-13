package epicsquid.mysticalworld.recipe;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SpindleRecipe extends SpecialRecipe {
  private static Ingredient COCOON_INGREDIENT = null;
  private static Ingredient SPINDLE_INGREDIENT = null;

  public SpindleRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    boolean found_spindle = false;
    boolean found_cocoon = false;

    if (COCOON_INGREDIENT == null) {
      COCOON_INGREDIENT = Ingredient.fromItems(ModItems.SILK_COCOON.get());
    }
    if (SPINDLE_INGREDIENT == null) {
      SPINDLE_INGREDIENT = DamagedIngredient.fromItems(ModItems.SPINDLE.get());
    }

    int count = 0;

    for (int i = 0; i < inv.getSizeInventory(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (count == 2) {
        return false;
      }

      count++;

      if (COCOON_INGREDIENT.test(current)) {
        if (!found_cocoon) {
          found_cocoon = true;
        } else {
          return false;
        }
      }

      if (SPINDLE_INGREDIENT.test(current)) {
        if (!found_spindle) {
          found_spindle = true;
        } else {
          return false;
        }
      }
    }

    return found_cocoon && found_spindle;
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (SPINDLE_INGREDIENT.test(current)) {
        current.attemptDamageItem(1, Util.rand, null);
        result.set(i, current.copy());
        break;
      }
    }

    return result;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return new ItemStack(ModItems.SILK_THREAD.get(), 9);
  }

  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.SPINDLE_SERIALIZER.get();
  }
}
