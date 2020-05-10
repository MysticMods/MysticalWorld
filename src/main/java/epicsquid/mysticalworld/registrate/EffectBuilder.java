package epicsquid.mysticalworld.registrate;

import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.potion.Effect;

import java.util.function.Supplier;

public class EffectBuilder<T extends Effect, P> extends AbstractBuilder<Effect, T, P, EffectBuilder<T, P>> {
  private Supplier<T> factory;

  protected EffectBuilder(CustomRegistrate owner, P parent, String name, BuilderCallback callback, Supplier<T> factory) {
    super(owner, parent, name, callback, Effect.class);
    this.factory = factory;
  }

  public static <T extends Effect, P> EffectBuilder<T, P> create(CustomRegistrate owner, P parent, String name, BuilderCallback callback, Supplier<T> factory) {
    return new EffectBuilder<>(owner, parent, name, callback, factory).defaultLang();
  }

  @Override
  protected T createEntry() {
    return factory.get();
  }

  public EffectBuilder<T, P> defaultLang() {
    return this.lang(Effect::getName);
  }

  public EffectBuilder<T, P> lang(String name) {
    return this.lang(Effect::getName, name);
  }
}
