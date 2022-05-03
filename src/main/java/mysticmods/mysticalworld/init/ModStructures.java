package mysticmods.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.world.structures.MWStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

public class ModStructures {
  public static final RegistryEntry<StructureFeature<?>> BARROW_STRUCTURE = MysticalWorld.REGISTRATE.simple("barrow", StructureFeature.class, MWStructure::new);
  public static final RegistryEntry<StructureFeature<?>> SAND_HUT_STRUCTURE = MysticalWorld.REGISTRATE.simple("sand_hut", StructureFeature.class, MWStructure::new);
  public static final RegistryEntry<StructureFeature<?>> RUINED_HUT_STRUCTURE = MysticalWorld.REGISTRATE.simple("ruined_hut", StructureFeature.class, MWStructure::new);
  public static final RegistryEntry<StructureFeature<?>> HUT_STRUCTURE = MysticalWorld.REGISTRATE.simple("hut", StructureFeature.class, MWStructure::new);

  public static void load () {
  }
}
