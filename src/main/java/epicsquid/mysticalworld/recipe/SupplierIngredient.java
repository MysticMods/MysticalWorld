/*package epicsquid.mysticalworld.recipe;

import com.google.gson.JsonElement;
import epicsquid.mysticalworld.MysticalWorld;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SupplierIngredient extends Ingredient implements Supplier<Ingredient> {
  private final Supplier<? extends Ingredient> ingredient;

  private static MethodHandle invalidateHandle;

  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    Method invalidate = ObfuscationReflectionHelper.findMethod(Ingredient.class, "invalidate");
    invalidate.setAccessible(true);
    try {
      invalidateHandle = lookup.unreflect(invalidate);
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to unprotect invalidate", e);
    }
  }

  public SupplierIngredient(Supplier<? extends Ingredient> ingredient) {
    super(Stream.empty());
    this.ingredient = ingredient;
  }

  @Override
  public ItemStack[] getMatchingStacks() {
    return get().getMatchingStacks();
  }

  @Override
  public boolean test(@Nullable ItemStack p_test_1_) {
    return get().test(p_test_1_);
  }

  @Override
  public IntList getValidItemStacksPacked() {
    return get().getValidItemStacksPacked();
  }

  @Override
  public JsonElement serialize() {
    return get().serialize();
  }

  @Override
  public boolean hasNoMatchingItems() {
    return get().hasNoMatchingItems();
  }

  @Override
  protected void invalidate() {
    try {
      invalidateHandle.invoke(get());
    } catch (Throwable throwable) {
      MysticalWorld.LOG.error("Unable to invalidate ingredient", throwable);
    }
  }

  @Override
  public boolean isSimple() {
    return get().isSimple();
  }

  @Override
  public IIngredientSerializer<? extends Ingredient> getSerializer() {
    return super.getSerializer();
  }

  @Override
  public Ingredient get() {
    return ingredient.get();
  }

  @Override
  public Predicate<ItemStack> and(Predicate<? super ItemStack> other) {
    return get().and(other);
  }

  @Override
  public Predicate<ItemStack> negate() {
    return get().negate();
  }

  @Override
  public Predicate<ItemStack> or(Predicate<? super ItemStack> other) {
    return get().or(other);
  }
}*/
