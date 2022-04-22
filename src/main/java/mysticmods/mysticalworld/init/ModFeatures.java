package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.FeatureConfig;
import mysticmods.mysticalworld.config.OreConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
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

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_CHARRED_TREE = FEATURES.register("charred_tree", () -> new ConfiguredFeature<>(Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(ModBlocks.CHARRED_LOG.getDefaultState()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build())));

  public static final RegistryObject<PlacedFeature> CHARRED_TREE = PLACED_FEATURES.register("charred_tree", () -> new PlacedFeature(CONFIGURED_CHARRED_TREE.getHolder().get(), List.of(PlacementUtils.countExtra(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, SurfaceWaterDepthFilter.forMaxDepth(0), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome())));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL = FEATURES.register("stonepetal", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.STONEPETAL.getDefaultState()))));

  private static final RegistryObject<PlacedFeature> STONEPETAL = PLACED_FEATURES.register("stonepetal", () -> new PlacedFeature(CONFIGURED_STONEPETAL.getHolder().get(), List.of(BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.replaceable(), BlockPredicate.matchesBlock(Blocks.STONE, new BlockPos(0, -1, 0)))))));

  private static final RegistryObject<ConfiguredFeature<?, ?>> CONFIGURED_STONEPETAL_PATCH = FEATURES.register("stonepetal_patch", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(ConfigManager.STONEPETAL_CONFIG.getTries(), 7, 3, STONEPETAL.getHolder().get())));

  public static final RegistryObject<PlacedFeature> STONEPETAL_PATCH = PLACED_FEATURES.register("stonepetal_patch", () -> new PlacedFeature(CONFIGURED_STONEPETAL_PATCH.getHolder().get(), List.of(CountPlacement.of(ConfigManager.STONEPETAL_CONFIG.getRepeats()), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(3), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

  public static void register(IEventBus bus) {
    FEATURES.register(bus);
    PLACED_FEATURES.register(bus);
  }

  /*  public static Supplier<ConfiguredFeature<?, ?>> STONEPETAL_PATCH = new LazySupplier<>(() -> Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(ModBlocks.STONEPETAL.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(ConfigManager.STONEPETAL_CONFIG.getTries()).whitelist(Sets.newHashSet(Blocks.STONE)).build()).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(ConfigManager.STONEPETAL_CONFIG.getRepeats()));*/

  public static void generateFeatures() {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        ConfiguredFeature<?, ?> feat;
/*        ORE_FEATURES.add(feat = SUPPLIER_ORE.get().configured(new SupplierOreFeatureConfig(config.getRule(), config.getOreKey(), config.getSize())
        ).decorated(
            ModFeatures.DIMENSION_COUNT_PLACEMENT.get().configured(new DimensionCountRangeConfig(config.getChance(), config.getMinY(), 0, config.getMaxY() - config.getMinY(), config.getDimensions())
            )
        ));
        REGISTRY.register(config.getName().toLowerCase(), feat);*/
      }
    }
  }

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
/*    for (ConfiguredFeature<?, ?> ore : ORE_FEATURES) {
      event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ore);
    }*/
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
