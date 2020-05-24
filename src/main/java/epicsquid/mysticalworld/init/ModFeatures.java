package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.world.tree.DeadTreeFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static RegistryEntry<DeadTreeFeature> DEAD_TREE = REGISTRATE.feature("dead_tree", DeadTreeFeature::new, NoFeatureConfig::deserialize, false)
      .register();

  public static void load() {
  }

  public static void loadComplete() {
    // Dead Tree
    for (String biomeTag : ConfigManager.DEAD_TREE_CONFIG.getBiomes()) {
      BiomeDictionary.Type type = BiomeDictionary.Type.getType(biomeTag);
      for (Biome biome : BiomeDictionary.getBiomes(type)) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.DEAD_TREE.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, ConfigManager.DEAD_TREE_CONFIG.getChance(), 1)));
      }
    }
  }
}
