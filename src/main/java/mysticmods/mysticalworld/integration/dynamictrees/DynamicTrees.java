package mysticmods.mysticalworld.integration.dynamictrees;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import mysticmods.mysticalworld.MysticalWorld;

public class DynamicTrees {
  public static void init () {
    RegistryHandler.setup(MysticalWorld.MODID);
  }
}
