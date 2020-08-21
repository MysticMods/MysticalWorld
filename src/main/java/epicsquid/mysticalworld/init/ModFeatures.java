package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.world.DimensionCountPlacement;
import epicsquid.mysticalworld.world.DimensionCountRangeConfig;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT = REGISTRATE.placement("dimension_count", DimensionCountPlacement::new, DimensionCountRangeConfig::deserialize).register();

  public static void load() {
  }
}
