package epicsquid.mysticalworld.recipe;

import com.google.gson.JsonObject;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "deprecation"})
public class IngredientFlintAndSteel extends Ingredient {
  private static IngredientFlintAndSteel instance = null;

  public static IngredientFlintAndSteel getInstance() {
    if (instance == null) {
      int max = Items.FLINT_AND_STEEL.getMaxDamage();

      List<ItemStack> matchingStacks = new ArrayList<>();
      for (int i = 0; i <= max; i++) {
        ItemStack stack = new ItemStack(Items.FLINT_AND_STEEL, 1);
        stack.setItemDamage(i);
        matchingStacks.add(stack);
      }

      instance = new IngredientFlintAndSteel(matchingStacks.toArray(new ItemStack[0]));
    }

    return instance;
  }

  public IngredientFlintAndSteel(ItemStack... stacks) {
    super(stacks);
  }

  @Override
  public boolean apply(@Nullable ItemStack p_apply_1_) {
    return p_apply_1_ != null && p_apply_1_.getItem() == Items.FLINT_AND_STEEL;
  }

  public static class Factory implements IIngredientFactory {
    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json) {
      return getInstance();
    }
  }
}
