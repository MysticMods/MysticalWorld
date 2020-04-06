package epicsquid.mysticalworld.registrate;

import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.item.crafting.IRecipeSerializer;

import java.util.function.Supplier;

public class RecipeSerializerBuilder<T extends IRecipeSerializer<?>, P> extends AbstractBuilder<IRecipeSerializer<?>, T, P, RecipeSerializerBuilder<T, P>> {
  private final Supplier<? extends T> factory;

  public RecipeSerializerBuilder(CustomRegistrate owner, P parent, String name, BuilderCallback callback, Supplier<? extends T> factory) {
    super(owner, parent, name, callback, IRecipeSerializer.class);
    this.factory = factory;
  }

  @Override
  protected T createEntry() {
    return factory.get();
  }
}
