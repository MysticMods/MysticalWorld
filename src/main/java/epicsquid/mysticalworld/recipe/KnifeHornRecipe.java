package epicsquid.mysticalworld.recipe;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.Tags;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KnifeHornRecipe extends SpecialRecipe {
  private static Ingredient SHELL_INGREDIENT = null;
  private static Ingredient KNIFE_INGREDIENT = null;

  public KnifeHornRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    boolean found_knife = false;
    boolean found_shell = false;

    if (SHELL_INGREDIENT == null) {
      SHELL_INGREDIENT = Ingredient.fromItems(Items.NAUTILUS_SHELL);
    }
    if (KNIFE_INGREDIENT == null) {
      KNIFE_INGREDIENT = DamagedIngredient.fromTag(Tags.Items.KNIVES);
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

      if (SHELL_INGREDIENT.test(current)) {
        if (!found_shell) {
          found_shell = true;
        } else {
          return false;
        }
      }

      if (KNIFE_INGREDIENT.test(current)) {
        if (!found_knife) {
          found_knife = true;
        } else {
          return false;
        }
      }
    }

    return found_shell && found_knife;
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (KNIFE_INGREDIENT.test(current)) {
        current.attemptDamageItem(1, Util.rand, null);
        result.set(i, current.copy());
        break;
      }
    }

    return result;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return new ItemStack(ModItems.NAUTILUS_HORN.get(), 1);
  }

  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.KNIFE_SERIALIZER.get();
  }
}
