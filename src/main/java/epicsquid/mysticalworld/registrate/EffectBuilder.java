package epicsquid.mysticalworld.registrate;

import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.potion.Effect;

import java.util.function.Supplier;

public class EffectBuilder<T extends Effect, P> extends AbstractBuilder<Effect, T, P, EffectBuilder<T, P>> {
  private Supplier<? extends T> factory;

  public EffectBuilder(CustomRegistrate owner, P parent, String name, BuilderCallback callback, Supplier<? extends T> factory) {
    super(owner, parent, name, callback, Effect.class);
    this.factory = factory;
  }

  @Override
  protected T createEntry() {
    return factory.get();
  }
}
