package epicsquid.mysticalworld.world;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.OreConfig;
import epicsquid.mysticalworld.init.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGen {
  private static void addOreGeneration(Biome biome) {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        biome.addFeature(
            GenerationStage.Decoration.UNDERGROUND_ORES,
            Feature.ORE.withConfiguration(
                new OreFeatureConfig(
                    MysticalWorld.ORE_GEN,
                    config.getOre().getDefaultState(),
                    config.getChance()
                )
            ).withPlacement(
                ModFeatures.DIMENSION_COUNT.get().configure(
                    new DimensionCountRangeConfig(
                        config.getSize(),
                        config.getMinY(),
                        0,
                        config.getMaxY() - config.getMinY(),
                        config.getDimensionsAsArray())
                )
            )
        );
      }
    }
  }

  public static void registerOreGeneration() {
    ForgeRegistries.BIOMES.forEach(OreGen::addOreGeneration);
  }
}
