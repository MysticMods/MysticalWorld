package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.OreConfig;
import epicsquid.mysticalworld.world.SupplierBlockStateProvider;
import epicsquid.mysticalworld.world.feature.SupplierOreFeature;
import epicsquid.mysticalworld.world.feature.SupplierOreFeatureConfig;
import epicsquid.mysticalworld.world.placement.DimensionCountPlacement;
import epicsquid.mysticalworld.world.placement.DimensionCountRangeConfig;
import epicsquid.mysticalworld.world.structures.BarrowStructure;
import epicsquid.mysticalworld.world.structures.HutStructure;
import epicsquid.mysticalworld.world.test.OreGenTest;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static IRuleTestType<OreGenTest> ORE_GEN = IRuleTestType.func_237129_a_("ore_gen", OreGenTest.CODEC);

  public static RegistryEntry<SupplierOreFeature> SUPPLIER_ORE = REGISTRATE.simple("supplier_ore_feature", Feature.class, () -> new SupplierOreFeature(SupplierOreFeatureConfig.CODEC));

  private static RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT_PLACEMENT = REGISTRATE.simple("dimension_count_placement", Placement.class, () -> new DimensionCountPlacement(DimensionCountRangeConfig.CODEC));
  public static RegistryEntry<BlockStateProviderType<SupplierBlockStateProvider>> SUPPLIER_STATE_PROVIDER = REGISTRATE.simple("supplier_state_provider", BlockStateProviderType.class, () -> new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC));

  private static final ConfiguredFeature<?, ?> CHARRED_TREE = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SupplierBlockStateProvider(MysticalWorld.MODID, "charred_log"), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build()).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1)));

  private static List<ConfiguredFeature<?, ?>> ORE_FEATURES = new ArrayList<>();

  public static void generateFeatures() {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        ConfiguredFeature<?, ?> feat;
        ORE_FEATURES.add(feat = SUPPLIER_ORE.get().withConfiguration(new SupplierOreFeatureConfig(OreGenTest.INSTANCE, config.getOreKey(), config.getSize())
        ).withPlacement(
            ModFeatures.DIMENSION_COUNT_PLACEMENT.get().configure(new DimensionCountRangeConfig(config.getChance(), config.getMinY(), 0, config.getMaxY() - config.getMinY(), config.getDimensions())
            )
        ));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalWorld.MODID, config.getName().toLowerCase()), feat);
      }
    }
    Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(MysticalWorld.MODID, "charred_tree"), CHARRED_TREE);
  }

  public static void load() {
  }

  public static void onBiomeLoad(BiomeLoadingEvent event) {
    if (ORE_FEATURES.isEmpty()) {
      generateFeatures();
    }
    for (ConfiguredFeature<?, ?> ore : ORE_FEATURES) {
      event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> ore);
    }
    if (event.getName() != null) {
      RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
      Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
      ModEntities.registerEntity(event, types);

      for (BiomeDictionary.Type type : types) {
        if (type != null && (ConfigManager.DEAD_TREE_CONFIG.getBiomes().contains(type) || ConfigManager.DEAD_TREE_CONFIG.getBiomes().isEmpty())) {
          for (BiomeDictionary.Type r : ConfigManager.DEAD_TREE_CONFIG.getBiomeRestrictions()) {
            if (r == type) {
              break;
            }
          }
          event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CHARRED_TREE);
          break;
        } else if (type != null && (ConfigManager.HUT_CONFIG.getBiomes().contains(type) || ConfigManager.HUT_CONFIG.getBiomes().isEmpty())) {
          for (BiomeDictionary.Type r : ConfigManager.HUT_CONFIG.getBiomeRestrictions()) {
            if (r == type) {
              break;
            }
          }
          event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_HUT);
          break;
        } else if (type != null && (ConfigManager.BARROW_CONFIG.getBiomes().contains(type) || ConfigManager.BARROW_CONFIG.getBiomes().isEmpty())) {
          for (BiomeDictionary.Type r : ConfigManager.BARROW_CONFIG.getBiomeRestrictions()) {
            if (r == type) {
              break;
            }
          }
          event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_BARROW);
          break;
        }
      }
      ModEntities.registerEntity(event, types);
    }
  }
}
