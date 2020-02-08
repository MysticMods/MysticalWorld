package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.effects.SlowRegenerationEffect;
import epicsquid.mysticalworld.effects.WakefulEffect;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

public class ModEffects {
  public static final RegistryObject<WakefulEffect> WAKEFUL = REGISTRY.registerEffect("wakeful", WakefulEffect::new);
  public static final RegistryObject<SlowRegenerationEffect> SLOW_REGEN = REGISTRY.registerEffect("slow_regeneration", SlowRegenerationEffect::new);
}
