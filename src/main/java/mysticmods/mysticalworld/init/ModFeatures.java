package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.FeatureConfig;
import mysticmods.mysticalworld.world.DebugOreFeature;
import mysticmods.mysticalworld.world.DebugPlacement;
import mysticmods.mysticalworld.world.DimensionPlacement;
import mysticmods.mysticalworld.world.GraniteTest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ModFeatures {
  private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<RuleTestType<?>> RULE_TESTS = DeferredRegister.create(Registry.RULE_TEST_REGISTRY, MysticalWorld.MODID);

  public static final RegistryObject<PlacementModifierType<?>> DIMENSION_PLACEMENT = PLACEMENT_MODIFIERS.register("dimension_placement", () -> new DimensionPlacement.Type());
  public static final RegistryObject<PlacementModifierType<?>> DEBUG_PLACEMENT = PLACEMENT_MODIFIERS.register("debug_placement", () -> new DebugPlacement.Type());

  public static final RegistryObject<RuleTestType<?>> GRANITE_TEST = RULE_TESTS.register("granite_test", () -> new GraniteTest.Type());

  public static final RegistryObject<DebugOreFeature> DEBUG_ORE_FEATURE = FEATURES.register("debug_ore_feature", () -> new DebugOreFeature(OreConfiguration.CODEC));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_CHARRED_TREE = CONFIGURED_FEATURES.register("charred_tree", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.CHARRED_LOG.getDefaultState()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build())));

  public static final RegistryObject<PlacedFeature> CHARRED_TREE = PLACED_FEATURES.register("charred_tree", () -> new PlacedFeature(CONFIGURED_CHARRED_TREE.getHolder().get(), List.of(PlacementUtils.countExtra(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceWaterDepthFilter.forMaxDepth(0), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), DimensionPlacement.of(ConfigManager.DEAD_TREE_CONFIG.getDimensions()), BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL = CONFIGURED_FEATURES.register("stonepetal", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STONEPETAL.getDefaultState()))));

  private static final RegistryObject<PlacedFeature> STONEPETAL = PLACED_FEATURES.register("stonepetal", () -> new PlacedFeature(CONFIGURED_STONEPETAL.getHolder().get(), List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 0)))))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL_PATCH = CONFIGURED_FEATURES.register("stonepetal_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(ConfigManager.STONEPETAL_CONFIG.getTries(), 7, 3, STONEPETAL.getHolder().get())));

  public static final RegistryObject<PlacedFeature> STONEPETAL_PATCH = PLACED_FEATURES.register("stonepetal_patch", () -> new PlacedFeature(CONFIGURED_STONEPETAL_PATCH.getHolder().get(), List.of(CountPlacement.of(ConfigManager.STONEPETAL_CONFIG.getRepeats()), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(3), DimensionPlacement.of(ConfigManager.STONEPETAL_CONFIG.getDimensions()), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WILD_AUBERGINE = CONFIGURED_FEATURES.register("wild_aubergine", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WILD_AUBERGINE.getDefaultState()))));

  private static final RegistryObject<PlacedFeature> WILD_AUBERGINE = PLACED_FEATURES.register("wild_aubergine", () -> new PlacedFeature(CONFIGURED_WILD_AUBERGINE.getHolder().get(), List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.wouldSurvive(ModBlocks.WILD_AUBERGINE.getDefaultState(), BlockPos.ZERO))))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_WILD_AUBERGINE_PATCH = CONFIGURED_FEATURES.register("wild_aubergine_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 7, 3, WILD_AUBERGINE.getHolder().get())));

  public static final RegistryObject<PlacedFeature> WILD_AUBERGINE_PATCH = PLACED_FEATURES.register("wild_aubergine_patch", () -> new PlacedFeature(CONFIGURED_WILD_AUBERGINE_PATCH.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery(ConfigManager.WILD_AUBERGINE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.WILD_AUBERGINE.getDimensions()), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_TIN_ORE = CONFIGURED_FEATURES.register("tin_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.getDefaultState())), ConfigManager.TIN_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> TIN_ORE = PLACED_FEATURES.register("tin_ore", () -> new PlacedFeature(CONFIGURED_TIN_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.TIN_ORE.getChance()), InSquarePlacement.spread(), BiomeFilter.biome(), DimensionPlacement.of(ConfigManager.TIN_ORE.getDimensions()), HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigManager.TIN_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.TIN_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_SILVER_ORE = CONFIGURED_FEATURES.register("silver_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.getDefaultState())), ConfigManager.SILVER_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> SILVER_ORE = PLACED_FEATURES.register("silver_ore", () -> new PlacedFeature(CONFIGURED_SILVER_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.SILVER_ORE.getChance()), InSquarePlacement.spread(), BiomeFilter.biome(), DimensionPlacement.of(ConfigManager.SILVER_ORE.getDimensions()), HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigManager.SILVER_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.SILVER_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_LEAD_ORE = CONFIGURED_FEATURES.register("lead_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.getDefaultState())), ConfigManager.LEAD_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> LEAD_ORE = PLACED_FEATURES.register("lead_ore", () -> new PlacedFeature(CONFIGURED_LEAD_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.LEAD_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.LEAD_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigManager.LEAD_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.LEAD_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_SAPPHIRE_ORE = CONFIGURED_FEATURES.register("sapphire_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAPPHIRE_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState())), ConfigManager.SAPPHIRE_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE = PLACED_FEATURES.register("sapphire_ore", () -> new PlacedFeature(CONFIGURED_SAPPHIRE_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.SAPPHIRE_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.SAPPHIRE_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigManager.SAPPHIRE_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.SAPPHIRE_ORE.getMaxY())))));

  private static final RuleTest GRANITE_REPLACEMENT = new GraniteTest();

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_GRANITE_QUARTZ_ORE = CONFIGURED_FEATURES.register("granite_quartz_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(GRANITE_REPLACEMENT, ModBlocks.GRANITE_QUARTZ_ORE.getDefaultState())), ConfigManager.GRANITE_QUARTZ_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> GRANITE_QUARTZ_ORE = PLACED_FEATURES.register("granite_quartz_ore", () -> new PlacedFeature(CONFIGURED_GRANITE_QUARTZ_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.GRANITE_QUARTZ_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.GRANITE_QUARTZ_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.uniform(VerticalAnchor.absolute(ConfigManager.GRANITE_QUARTZ_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.GRANITE_QUARTZ_ORE.getMaxY())))));

  public static void register(IEventBus bus) {
    CONFIGURED_FEATURES.register(bus);
    PLACED_FEATURES.register(bus);
    PLACEMENT_MODIFIERS.register(bus);
    RULE_TESTS.register(bus);
    FEATURES.register(bus);
  }

  private static void tryPlaceFeature(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types, FeatureConfig config, RegistryObject<PlacedFeature> feature) {
    if (!config.shouldSpawn()) {
      return;
    }
    for (BiomeDictionary.Type rest : config.getBiomeRestrictions()) {
      if (types.contains(rest)) {
        return;
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
      return;
    }
    if (config.isFeature()) {
      event.getGeneration().getFeatures(config.getStage()).add(feature.getHolder().get());
    }
  }

  public static List<Holder<PlacedFeature>> ORE_FEATURES = null;

  @SubscribeEvent
  public static void onBiomeLoad(BiomeLoadingEvent event) {
    if (ORE_FEATURES == null) {
      ORE_FEATURES = new ArrayList<>();
      if (ConfigManager.TIN_ORE.shouldGenerate()) {
        ORE_FEATURES.add(TIN_ORE.getHolder().get());
      }
      if (ConfigManager.SILVER_ORE.shouldGenerate()) {
        ORE_FEATURES.add(SILVER_ORE.getHolder().get());
      }
      if (ConfigManager.LEAD_ORE.shouldGenerate()) {
        ORE_FEATURES.add(LEAD_ORE.getHolder().get());
      }
      if (ConfigManager.SAPPHIRE_ORE.shouldGenerate()) {
        ORE_FEATURES.add(SAPPHIRE_ORE.getHolder().get());
      }
      if (ConfigManager.GRANITE_QUARTZ_ORE.shouldGenerate()) {
        ORE_FEATURES.add(GRANITE_QUARTZ_ORE.getHolder().get());
      }
    }
    event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).addAll(ORE_FEATURES);
    if (event.getName() != null) {
      ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
      Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
      ModEntities.registerEntity(event, types);
      if (!ModList.get().isLoaded("dynamictrees")) {
        tryPlaceFeature(event, types, ConfigManager.DEAD_TREE_CONFIG, CHARRED_TREE);
      }
      tryPlaceFeature(event, types, ConfigManager.STONEPETAL_CONFIG, STONEPETAL_PATCH);
      tryPlaceFeature(event, types, ConfigManager.WILD_AUBERGINE, WILD_AUBERGINE_PATCH);
    }
  }
}
