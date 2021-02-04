package epicsquid.mysticalworld.init;

import com.google.common.collect.Sets;
import com.tterrag.registrate.util.entry.RegistryEntry;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.FeatureConfig;
import epicsquid.mysticalworld.config.OreConfig;
import epicsquid.mysticalworld.world.SupplierBlockStateProvider;
import epicsquid.mysticalworld.world.feature.SupplierOreFeature;
import epicsquid.mysticalworld.world.feature.SupplierOreFeatureConfig;
import epicsquid.mysticalworld.world.placement.DimensionCountPlacement;
import epicsquid.mysticalworld.world.placement.DimensionCountRangeConfig;
import epicsquid.mysticalworld.world.test.OreGenTest;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.libs.noobutil.types.LazySupplier;

import java.util.*;
import java.util.function.Supplier;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, WorldGenRegistries.CONFIGURED_FEATURE);

  public static IRuleTestType<OreGenTest> ORE_GEN = IRuleTestType.func_237129_a_("ore_gen", OreGenTest.CODEC);

  public static RegistryEntry<SupplierOreFeature> SUPPLIER_ORE = REGISTRATE.simple("supplier_ore_feature", Feature.class, () -> new SupplierOreFeature(SupplierOreFeatureConfig.CODEC));

  private static RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT_PLACEMENT = REGISTRATE.simple("dimension_count_placement", Placement.class, () -> new DimensionCountPlacement(DimensionCountRangeConfig.CODEC));
  public static RegistryEntry<BlockStateProviderType<SupplierBlockStateProvider>> SUPPLIER_STATE_PROVIDER = REGISTRATE.simple("supplier_state_provider", BlockStateProviderType.class, () -> new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC));

  public static final ConfiguredFeature<?, ?> CHARRED_TREE = Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SupplierBlockStateProvider(MysticalWorld.MODID, "charred_log"), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build()).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1)));

  public static final Supplier<ConfiguredFeature<?, ?>> STONEPETAL_PATCH = new LazySupplier<>(() -> Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.STONEPETAL.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(ConfigManager.STONEPETAL_CONFIG.getTries()).whitelist(Sets.newHashSet(Blocks.STONE)).build()).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(ConfigManager.STONEPETAL_CONFIG.getRepeats()));

  private static List<ConfiguredFeature<?, ?>> ORE_FEATURES = new ArrayList<>();

  public static void generateFeatures() {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        ConfiguredFeature<?, ?> feat;
        ORE_FEATURES.add(feat = SUPPLIER_ORE.get().withConfiguration(new SupplierOreFeatureConfig(config.getRule(), config.getOreKey(), config.getSize())
        ).withPlacement(
            ModFeatures.DIMENSION_COUNT_PLACEMENT.get().configure(new DimensionCountRangeConfig(config.getChance(), config.getMinY(), 0, config.getMaxY() - config.getMinY(), config.getDimensions())
            )
        ));
        REGISTRY.register(config.getName().toLowerCase(), feat);
      }
    }
    REGISTRY.register("charred_tree", CHARRED_TREE);
    REGISTRY.register("stonepetal_patch", STONEPETAL_PATCH.get());
  }

  public static void load() {
  }

  private static boolean tryPlaceFeature(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types, FeatureConfig<?> config) {
    for (BiomeDictionary.Type rest : config.getBiomeRestrictions()) {
      if (types.contains(rest)) {
        return false;
      }
    }
    boolean place = false;
    if (config.getBiomes().isEmpty()) {
      place = true;
    } else {
      Set<BiomeDictionary.Type> biomeTypes = config.getBiomes();
      for (BiomeDictionary.Type poss : biomeTypes) {
        if (types.contains(poss)) {
          place = true;
          break;
        }
      }
    }
    if (!place) {
      return false;
    }
    if (config.isFeature()) {
      Supplier<ConfiguredFeature<?, ?>> sup = config.getFeature();
      if (sup == null) {
        return false;
      }
      event.getGeneration().getFeatures(config.getStage()).add(sup);
      return true;
    } else {
      Supplier<StructureFeature<?, ?>> sup = config.getStructure();
      if (sup == null) {
        return false;
      }
      event.getGeneration().getStructures().add(sup);
      if (config == ConfigManager.HUT_CONFIG) {
        event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_RUINED_HUT);
      }
      return true;
    }
  }

  public static void onBiomeLoad(BiomeLoadingEvent event) {
    for (ConfiguredFeature<?, ?> ore : ORE_FEATURES) {
      event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> ore);
    }
    if (event.getName() != null) {
      RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
      Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
      ModEntities.registerEntity(event, types);
      tryPlaceFeature(event, types, ConfigManager.DEAD_TREE_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.STONEPETAL_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.HUT_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.BARROW_CONFIG);
    }
  }

  public static void onWorldLoad(final WorldEvent.Load event) {
    if (event.getWorld() instanceof ServerWorld) {
      ServerWorld world = (ServerWorld) event.getWorld();
      if (world.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && world.getDimensionKey().equals(World.OVERWORLD)) {
        return;
      }

      Map<Structure<?>, StructureSeparationSettings> temp = new HashMap<>(world.getChunkProvider().generator.func_235957_b_().func_236195_a_());
      temp.put(ModStructures.BARROW_STRUCTURE, DimensionStructuresSettings.field_236191_b_.get(ModStructures.BARROW_STRUCTURE));
      temp.put(ModStructures.HUT_STRUCTURE, DimensionStructuresSettings.field_236191_b_.get(ModStructures.HUT_STRUCTURE));
      temp.put(ModStructures.RUINED_HUT_STRUCTURE, DimensionStructuresSettings.field_236191_b_.get(ModStructures.RUINED_HUT_STRUCTURE));
      world.getChunkProvider().generator.func_235957_b_().field_236193_d_ = temp;
    }
  }
}
