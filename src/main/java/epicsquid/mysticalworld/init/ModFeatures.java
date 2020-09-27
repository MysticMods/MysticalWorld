package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.world.DimensionCountPlacement;
import epicsquid.mysticalworld.world.DimensionCountRangeConfig;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;

import java.util.function.Supplier;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static final Supplier<TreeFeatureConfig> CHARRED_LOG_CONFIG = () -> (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.CHARRED_LOG.get().getDefaultState()), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable) Blocks.DEAD_BUSH).build();

  public static RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT = REGISTRATE.placement("dimension_count", DimensionCountPlacement::new, DimensionCountRangeConfig::deserialize).register();

  public static void load() {
  }

  public static void loadComplete() {
    for (String biomeTag : ConfigManager.DEAD_TREE_CONFIG.getBiomes()) {
      BiomeDictionary.Type type = BiomeDictionary.Type.getType(biomeTag);
      for (Biome biome : BiomeDictionary.getBiomes(type)) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.FANCY_TREE.withConfiguration(CHARRED_LOG_CONFIG.get()).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, ConfigManager.DEAD_TREE_CONFIG.getChance(), 1))));
      }
    }
  }
}
