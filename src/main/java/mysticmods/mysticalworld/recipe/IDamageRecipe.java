package mysticmods.mysticalworld.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import noobanidus.libs.noobutil.ingredient.LazyIngredient;
import noobanidus.libs.noobutil.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

public interface IDamageRecipe {
  String TAG = "damage_item";
  String DAMAGE = "damage_amount";

  default LazyIngredient createDamageIngredient(Ingredient ingredient) {
    return new LazyIngredient(() -> {
      List<ItemStack> matchingStacks = new ArrayList<>();
      for (ItemStack stack : ingredient.getItems()) {
        if (!stack.isDamageableItem()) {
          throw new IllegalArgumentException("Invalid itemstack '" + stack + "' for DamageRecipe: flagged as damage item, but not damageable.");
        }
        for (int i = 0; i < stack.getMaxDamage(); i++) {
          ItemStack copy = stack.copy();
          copy.setDamageValue(i);
          matchingStacks.add(copy);
        }
      }
      return Ingredient.of(matchingStacks.toArray(new ItemStack[0]));
    });
  }

  default NonNullList<ItemStack> getRemainingItems(CraftingContainer inv, Ingredient damageIngredient, int damageAmount) {
    NonNullList<ItemStack> result = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

    for (int i = 0; i < result.size(); i++) {
      ItemStack current = inv.getItem(i);
      if (current.isEmpty()) {
        continue;
      }

      if (damageIngredient.test(current)) {
        current = current.copy();
        if (current.hurt(damageAmount, MathUtil.rand, null)) {
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
