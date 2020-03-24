package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.RegistryEntry;
import epicsquid.mysticalworld.effects.SlowRegenerationEffect;
import epicsquid.mysticalworld.effects.WakefulEffect;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Registrate is done

public class ModEffects {
  public static final RegistryEntry<WakefulEffect> WAKEFUL = REGISTRATE.effect("wakeful", WakefulEffect::new).register();
  public static final RegistryEntry<SlowRegenerationEffect> SLOW_REGEN = REGISTRATE.effect("slow_regeneration", SlowRegenerationEffect::new).register();

  public static void load() {
  }
}
