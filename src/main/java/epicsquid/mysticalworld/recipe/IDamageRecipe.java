package epicsquid.mysticalworld.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import noobanidus.libs.noobutil.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

public interface IDamageRecipe {
  String TAG = "damage_item";
  String DAMAGE = "damage_amount";

  default Ingredient createDamageIngredient(Ingredient ingredient) {
    List<ItemStack> matchingStacks = new ArrayList<>();
    for (ItemStack stack : ingredient.getMatchingStacks()) {
      if (!stack.isDamageable()) {
        throw new IllegalArgumentException("Invalid itemstack '" + stack.toString() + "' for DamageRecipe: flagged as damage item, but not damageable.");
      }
      for (int i = 0; i < stack.getMaxDamage(); i++) {
        ItemStack copy = stack.copy();
        copy.setDamage(i);
        matchingStacks.add(copy);
      }
    }
    return Ingredient.fromStacks(matchingStacks.toArray(new ItemStack[0]));
  }

  default NonNullList<ItemStack> getRemainingItems(CraftingInventory inv, Ingredient damageIngredient, int damageAmount) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getStackInSlot(i);
      if (current.isEmpty()) {
        continue;
      }

      if (damageIngredient.test(current)) {
        current = current.copy();
        if (current.attemptDamageItem(damageAmount, MathUtil.rand, null)) {
          current.setCount(0);
        }
        if (!current.isEmpty()) {
          result.set(i, current);
        }
      }
    }

    return result;
  }
}
