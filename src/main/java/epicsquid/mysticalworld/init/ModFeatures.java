package epicsquid.mysticalworld.init;

public class ModFeatures {

  public static void load() {
  }

/*  public static void loadComplete() {
    for (String biomeTag : ConfigManager.DEAD_TREE_CONFIG.getBiomes()) {
      BiomeDictionary.Type type = BiomeDictionary.Type.getType(biomeTag);
      for (Biome biome : BiomeDictionary.getBiomes(type)) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FANCY_TREE.withConfiguration(CHARRED_LOG_CONFIG.get()).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, ConfigManager.DEAD_TREE_CONFIG.getChance(), 1))));
      }
    }
  }*/
}
