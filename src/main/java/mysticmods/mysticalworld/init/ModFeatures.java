package mysticmods.mysticalworld.init;

import com.mojang.serialization.Codec;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.FeatureConfig;
import mysticmods.mysticalworld.config.OreConfig;
import mysticmods.mysticalworld.world.placement.DimensionPlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;

// TODO: PlacedFeature, etc.

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ModFeatures {
  private static final DeferredRegister<ConfiguredFeature<?, ?>> FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MysticalWorld.MODID);
  private static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(Registry.PLACEMENT_MODIFIER_REGISTRY, MysticalWorld.MODID);

  public static final RegistryObject<PlacementModifierType<?>> DIMENSION_PLACEMENT = PLACEMENT_MODIFIERS.register("dimension_placement", () -> new DimensionPlacement.Type());

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_CHARRED_TREE = FEATURES.register("charred_tree", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.CHARRED_LOG.getDefaultState()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build())));

  public static final RegistryObject<PlacedFeature> CHARRED_TREE = PLACED_FEATURES.register("charred_tree", () -> new PlacedFeature(CONFIGURED_CHARRED_TREE.getHolder().get(), List.of(PlacementUtils.countExtra(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceWaterDepthFilter.forMaxDepth(0), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL = FEATURES.register("stonepetal", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STONEPETAL.getDefaultState()))));

  private static final RegistryObject<PlacedFeature> STONEPETAL = PLACED_FEATURES.register("stonepetal", () -> new PlacedFeature(CONFIGURED_STONEPETAL.getHolder().get(), List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 0)))))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL_PATCH = FEATURES.register("stonepetal_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(ConfigManager.STONEPETAL_CONFIG.getTries(), 7, 3, STONEPETAL.getHolder().get())));

  public static final RegistryObject<PlacedFeature> STONEPETAL_PATCH = PLACED_FEATURES.register("stonepetal_patch", () -> new PlacedFeature(CONFIGURED_STONEPETAL_PATCH.getHolder().get(), List.of(CountPlacement.of(ConfigManager.STONEPETAL_CONFIG.getRepeats()), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(3), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_TIN_ORE = FEATURES.register("tin_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.getDefaultState())), ConfigManager.TIN_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> TIN_ORE = PLACED_FEATURES.register("tin_ore", () -> new PlacedFeature(CONFIGURED_TIN_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.TIN_ORE.getChance()), InSquarePlacement.spread(), BiomeFilter.biome(), DimensionPlacement.of(ConfigManager.TIN_ORE.getDimensions()), HeightRangePlacement.triangle(VerticalAnchor.absolute(ConfigManager.TIN_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.TIN_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_SILVER_ORE = FEATURES.register("silver_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.getDefaultState())), ConfigManager.SILVER_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> SILVER_ORE = PLACED_FEATURES.register("silver_ore", () -> new PlacedFeature(CONFIGURED_SILVER_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.SILVER_ORE.getChance()), InSquarePlacement.spread(), BiomeFilter.biome(), DimensionPlacement.of(ConfigManager.SILVER_ORE.getDimensions()), HeightRangePlacement.triangle(VerticalAnchor.absolute(ConfigManager.SILVER_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.SILVER_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_LEAD_ORE = FEATURES.register("lead_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEAD_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEAD_ORE.getDefaultState())), ConfigManager.LEAD_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> LEAD_ORE = PLACED_FEATURES.register("lead_ore", () -> new PlacedFeature(CONFIGURED_LEAD_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.LEAD_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.LEAD_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.absolute(ConfigManager.LEAD_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.LEAD_ORE.getMaxY())))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_SAPPHIRE_ORE = FEATURES.register("sapphire_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SAPPHIRE_ORE.getDefaultState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState())), ConfigManager.SAPPHIRE_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> SAPPHIRE_ORE = PLACED_FEATURES.register("sapphire_ore", () -> new PlacedFeature(CONFIGURED_SAPPHIRE_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.SAPPHIRE_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.SAPPHIRE_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.absolute(ConfigManager.SAPPHIRE_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.SAPPHIRE_ORE.getMaxY())))));

  private static final RuleTest GRANITE_REPLACEMENT = new TagMatchTest(MWTags.Blocks.BASE_STONE_GRANITE);

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_GRANITE_QUARTZ_ORE = FEATURES.register("granite_quartz_ore", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(GRANITE_REPLACEMENT, ModBlocks.GRANITE_QUARTZ_ORE.getDefaultState())), ConfigManager.GRANITE_QUARTZ_ORE.getSize())));

  public static final RegistryObject<PlacedFeature> GRANITE_QUARTZ_ORE = PLACED_FEATURES.register("granite_quartz_ore", () -> new PlacedFeature(CONFIGURED_GRANITE_QUARTZ_ORE.getHolder().get(), List.of(CountPlacement.of(ConfigManager.GRANITE_QUARTZ_ORE.getChance()), InSquarePlacement.spread(), DimensionPlacement.of(ConfigManager.GRANITE_QUARTZ_ORE.getDimensions()), BiomeFilter.biome(), HeightRangePlacement.triangle(VerticalAnchor.absolute(ConfigManager.GRANITE_QUARTZ_ORE.getMinY()), VerticalAnchor.absolute(ConfigManager.GRANITE_QUARTZ_ORE.getMaxY())))));

  public static void register(IEventBus bus) {
    FEATURES.register(bus);
    PLACED_FEATURES.register(bus);
    PLACEMENT_MODIFIERS.register(bus);
  }

  /*  public static Supplier<ConfiguredFeature<?, ?>> STONEPETAL_PATCH = new LazySupplier<>(() -> Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(ModBlocks.STONEPETAL.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(ConfigManager.STONEPETAL_CONFIG.getTries()).whitelist(Sets.newHashSet(Blocks.STONE)).build()).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(ConfigManager.STONEPETAL_CONFIG.getRepeats()));*/

  public static void load() {
  }

  private static void tryPlaceFeature(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types, FeatureConfig<?> config, RegistryObject<PlacedFeature> feature) {
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
    }/* else {
      Supplier<ConfiguredStructureFeature<?, ?>> sup = config.getStructure();
      if (sup == null) {
        return;
      }
      event.getGeneration().getStructures().add(sup);
      if (config == ConfigManager.HUT_CONFIG) {
        event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_RUINED_HUT);
      }
    }*/
  }

  @SubscribeEvent
  public static void onBiomeLoad(BiomeLoadingEvent event) {
    event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).addAll(List.of(TIN_ORE.getHolder().get(), SILVER_ORE.getHolder().get(), SAPPHIRE_ORE.getHolder().get(), GRANITE_QUARTZ_ORE.getHolder().get(), LEAD_ORE.getHolder().get()));
    if (event.getName() != null) {
      ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
      Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
      ModEntities.registerEntity(event, types);
      if (!ModList.get().isLoaded("dynamictrees")) {
        tryPlaceFeature(event, types, ConfigManager.DEAD_TREE_CONFIG, CHARRED_TREE);
      }
      tryPlaceFeature(event, types, ConfigManager.STONEPETAL_CONFIG, STONEPETAL_PATCH);
/*      tryPlaceFeature(event, types, ConfigManager.HUT_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.BARROW_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.SAND_HOUSE_CONFIG);*/
    }
  }

/*  @SubscribeEvent
  public static void onWorldLoad(final WorldEvent.Load event) {
    if (event.getWorld() instanceof ServerLevel) {
      ServerLevel world = (ServerLevel) event.getWorld();
      if (world.getChunkSource().getGenerator() instanceof FlatLevelSource && world.dimension().equals(Level.OVERWORLD)) {
        return;
      }

      if (GETCODEC_METHOD == null) {
        Method codec = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
        MethodHandles.Lookup l = MethodHandles.lookup();
        try {
          GETCODEC_METHOD = l.unreflect(codec);
        } catch (IllegalAccessException e) {
          MysticalWorld.LOG.error("Unable to unreflect codec getter.", e);
          return;
        }
      }

      ResourceLocation chunkGen;
      try {
        //noinspection unchecked
        chunkGen = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invokeExact(world.getChunkSource().generator));
      } catch (Throwable throwable) {
        MysticalWorld.LOG.error("Unable to look up chunk provider's generator", throwable);
        return;
      }
      if (chunkGen != null && chunkGen.getNamespace().equals("terraforrged")) {
        return;
      }

      if (world.dimension().equals(Level.OVERWORLD)) {
        Map<StructureFeature<?>, StructureFeatureConfiguration> temp = new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());
        temp.put(ModStructures.BARROW_STRUCTURE, StructureSettings.DEFAULTS.get(ModStructures.BARROW_STRUCTURE));
        temp.put(ModStructures.HUT_STRUCTURE, StructureSettings.DEFAULTS.get(ModStructures.HUT_STRUCTURE));
        temp.put(ModStructures.RUINED_HUT_STRUCTURE, StructureSettings.DEFAULTS.get(ModStructures.RUINED_HUT_STRUCTURE));
        temp.put(ModStructures.SAND_HOUSE_STRUCTURE, StructureSettings.DEFAULTS.get(ModStructures.SAND_HOUSE_STRUCTURE));
        world.getChunkSource().generator.getSettings().structureConfig = temp;
      }
    }
  }*/
}
