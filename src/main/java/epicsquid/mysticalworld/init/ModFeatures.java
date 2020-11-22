package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.OreConfig;
import epicsquid.mysticalworld.world.DimensionCountPlacement;
import epicsquid.mysticalworld.world.DimensionCountRangeConfig;
import epicsquid.mysticalworld.world.OreGenTest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static IRuleTestType<OreGenTest> ORE_GEN = IRuleTestType.func_237129_a_("ore_gen", OreGenTest.CODEC);

  private static RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT_PLACEMENT = REGISTRATE.simple("dimension_count_placement", Placement.class, () -> new DimensionCountPlacement(DimensionCountRangeConfig.CODEC));

  private static List<ConfiguredFeature<?, ?>> ORE_FEATURES = new ArrayList<>();

  private static void generateFeatures() {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        ConfiguredFeature<?, ?> feat;
        ORE_FEATURES.add(feat = Feature.ORE.withConfiguration(new OreFeatureConfig(OreGenTest.INSTANCE, config.getOre().getDefaultState(), config.getChance())
        ).withPlacement(
            ModFeatures.DIMENSION_COUNT_PLACEMENT.get().configure(new DimensionCountRangeConfig(config.getSize(), config.getMinY(), 0, config.getMaxY() - config.getMinY(), config.getDimensions())
            )
        ));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalWorld.MODID, config.getName()), feat);
      }
    }
  }

  public static void load() {
    generateFeatures();
  }

  public static void onBiomeLoad (BiomeLoadingEvent event) {

  }
}
