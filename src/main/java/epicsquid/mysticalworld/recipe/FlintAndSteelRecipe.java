/*package epicsquid.mysticalworld.recipe;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModRecipes;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FlintAndSteelRecipe extends SpecialRecipe {
  private static Ingredient LOG_INGREDIENT = null;
  private static Ingredient FLINT_INGREDIENT = null;

  public FlintAndSteelRecipe(ResourceLocation idIn) {
    super(idIn);
  }

  @Override
  public boolean matches(CraftingInventory inv, World worldIn) {
    boolean found_flint = false;
    boolean found_log = false;

    if (LOG_INGREDIENT == null) {
      LOG_INGREDIENT = Ingredient.fromTag(ItemTags.LOGS);
    }
    if (FLINT_INGREDIENT == null) {
      FLINT_INGREDIENT = DamagedIngredient.fromItems(Items.FLINT_AND_STEEL);
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

      if (LOG_INGREDIENT.test(current)) {
        if (!found_log) {
          found_log = true;
        } else {
          return false;
        }
      }

      if (FLINT_INGREDIENT.test(current)) {
        if (!found_flint) {
          found_flint = true;
        } else {
          return false;
        }
      }
    }

    return found_log && found_flint;
  }

  @Override
  public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (FLINT_INGREDIENT.test(current)) {
        ItemStack newStack = Util.damageItem(1, current, inv);
        result.set(i, newStack.copy());
        break;
      }
    }

    return result;
  }

  @Override
  public ItemStack getCraftingResult(CraftingInventory inv) {
    return new ItemStack(ModBlocks.CHARRED_LOG.get());
  }

  @Override
  public boolean canFit(int width, int height) {
    return width * height >= 2;
  }

  @Override
  public IRecipeSerializer<?> getSerializer() {
    return ModRecipes.FLINT_SERIALIZER.get();
  }
}*/
