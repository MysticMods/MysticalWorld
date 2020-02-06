package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.effects.WakefulEffect;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

public class ModEffects {
  public static final RegistryObject<WakefulEffect> WAKEFUL = REGISTRY.registerEffect("wakeful", WakefulEffect::new);
}
