package epicsquid.mysticalworld.registrate;

import com.tterrag.registrate.builders.AbstractBuilder;
import com.tterrag.registrate.builders.BuilderCallback;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class SoundEventBuilder<T extends SoundEvent, P> extends AbstractBuilder<SoundEvent, T, P, SoundEventBuilder<T, P>> {
  private final Supplier<? extends T> factory;

  public SoundEventBuilder(CustomRegistrate owner, P parent, String name, BuilderCallback callback, Supplier<? extends T> factory) {
    super(owner, parent, name, callback, SoundEvent.class);
    this.factory = factory;
  }

  @Override
  protected T createEntry() {
    return factory.get();
  }
}
