package epicsquid.mysticalworld.recipe;

import com.google.gson.JsonObject;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "deprecation"})
public class IngredientSpindle extends Ingredient {
  private static IngredientSpindle instance = null;

  public static IngredientSpindle getInstance() {
    if (instance == null) {
      int max = ModItems.spindle.getMaxDamage();

      List<ItemStack> matchingStacks = new ArrayList<>();
      for (int i = 0; i <= max; i++) {
        ItemStack stack = new ItemStack(ModItems.spindle, 1);
        stack.setItemDamage(i);
        matchingStacks.add(stack);
      }

      instance = new IngredientSpindle(matchingStacks.toArray(new ItemStack[0]));
    }

    return instance;
  }

  public IngredientSpindle(ItemStack... stacks) {
    super(stacks);
  }

  @Override
  public boolean apply(@Nullable ItemStack p_apply_1_) {
    return p_apply_1_ != null && p_apply_1_.getItem() == ModItems.spindle;
  }

  public static class Factory implements IIngredientFactory {
    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json) {
      return getInstance();
    }
  }
}
