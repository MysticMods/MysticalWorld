package epicsquid.mysticalworld.recipe;

import com.google.gson.JsonObject;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class IngredientFlintAndSteel extends Ingredient {
  public static IngredientFlintAndSteel instance = new IngredientFlintAndSteel();

  public IngredientFlintAndSteel() {
    super(0);
  }

  @Override
  public boolean apply(@Nullable ItemStack p_apply_1_) {
    return p_apply_1_ != null && p_apply_1_.getItem() == Items.FLINT_AND_STEEL;
  }

  public static class Factory implements IIngredientFactory {
    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json) {
      return instance;
    }
  }
}
