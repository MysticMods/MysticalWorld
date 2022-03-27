package mysticmods.mysticalworld.init;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraftforge.fml.common.Mod;

// TODO: PlacedFeature, etc.

@Mod.EventBusSubscriber(modid=MysticalWorld.MODID)
public class ModFeatures {
/*  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, BuiltinRegistries.CONFIGURED_FEATURE);

  public static final RuleTestType<OreGenTest> ORE_GEN = RuleTestType.register("ore_gen", OreGenTest.CODEC);

  public static final RegistryEntry<SupplierOreFeature> SUPPLIER_ORE = MysticalWorld.REGISTRATE.simple("supplier_ore_feature", Feature.class, () -> new SupplierOreFeature(SupplierOreFeatureConfig.CODEC));

  private static final RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT_PLACEMENT = MysticalWorld.REGISTRATE.simple("dimension_count_placement", FeatureDecorator.class, () -> new DimensionCountPlacement(DimensionCountRangeConfig.CODEC));

  private static final RegistryEntry<DimensionPlacement> DIMENSION_PLACEMENT = MysticalWorld.REGISTRATE.simple("dimension_placement", FeatureDecorator.class, () -> new DimensionPlacement(DimensionConfig.CODEC));

  public static final RegistryEntry<BlockStateProviderType<SupplierBlockStateProvider>> SUPPLIER_STATE_PROVIDER = MysticalWorld.REGISTRATE.simple("supplier_state_provider", BlockStateProviderType.class, () -> new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC));

  public static ConfiguredFeature<?, ?> CHARRED_TREE = Feature.TREE.configured((new TreeConfiguration.TreeConfigurationBuilder(new SupplierBlockStateProvider(MysticalWorld.MODID, "charred_log"), new SimpleStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(UniformInt.fixed(2), UniformInt.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build()).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1)));

  public static Supplier<ConfiguredFeature<?, ?>> STONEPETAL_PATCH = new LazySupplier<>(() -> Feature.RANDOM_PATCH.configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(ModBlocks.STONEPETAL.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(ConfigManager.STONEPETAL_CONFIG.getTries()).whitelist(Sets.newHashSet(Blocks.STONE)).build()).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(ConfigManager.STONEPETAL_CONFIG.getRepeats()));

  private static final List<ConfiguredFeature<?, ?>> ORE_FEATURES = new ArrayList<>();

  public static void generateFeatures() {
    for (OreConfig config : ConfigManager.ORE_CONFIG) {
      if (config.getChance() > 0) {
        ConfiguredFeature<?, ?> feat;
        ORE_FEATURES.add(feat = SUPPLIER_ORE.get().configured(new SupplierOreFeatureConfig(config.getRule(), config.getOreKey(), config.getSize())
        ).decorated(
            ModFeatures.DIMENSION_COUNT_PLACEMENT.get().configured(new DimensionCountRangeConfig(config.getChance(), config.getMinY(), 0, config.getMaxY() - config.getMinY(), config.getDimensions())
            )
        ));
        REGISTRY.register(config.getName().toLowerCase(), feat);
      }
    }
    CHARRED_TREE = CHARRED_TREE.decorated(ModFeatures.DIMENSION_PLACEMENT.get().configured(new DimensionConfig(ConfigManager.DEAD_TREE_CONFIG.getDimensions())));
    REGISTRY.register("charred_tree", CHARRED_TREE);
    ConfigManager.DEAD_TREE_CONFIG.setSupplierFeature(() -> () -> CHARRED_TREE);
    final ConfiguredFeature<?, ?> stonepetalPatch = STONEPETAL_PATCH.get().decorated(ModFeatures.DIMENSION_PLACEMENT.get().configured(new DimensionConfig(ConfigManager.STONEPETAL_CONFIG.getDimensions())));
    STONEPETAL_PATCH = () -> stonepetalPatch;
    REGISTRY.register("stonepetal_patch", stonepetalPatch);
    ConfigManager.STONEPETAL_CONFIG.setSupplierFeature(() -> STONEPETAL_PATCH);
  }

  public static void load() {
  }

  private static void tryPlaceFeature(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types, FeatureConfig<?> config) {
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
      Supplier<ConfiguredFeature<?, ?>> sup = config.getFeature();
      if (sup == null) {
        return;
      }
      event.getGeneration().getFeatures(config.getStage()).add(sup);
    } else {
      Supplier<ConfiguredStructureFeature<?, ?>> sup = config.getStructure();
      if (sup == null) {
        return;
      }
      event.getGeneration().getStructures().add(sup);
      if (config == ConfigManager.HUT_CONFIG) {
        event.getGeneration().getStructures().add(() -> ConfiguredStructures.CONFIGURED_RUINED_HUT);
      }
    }
  }

  @SubscribeEvent
  public static void onBiomeLoad(BiomeLoadingEvent event) {
    for (ConfiguredFeature<?, ?> ore : ORE_FEATURES) {
      event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ore);
    }
    if (event.getName() != null) {
      ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
      Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
      ModEntities.registerEntity(event, types);
      if (!ModList.get().isLoaded("dynamictrees")) {
        tryPlaceFeature(event, types, ConfigManager.DEAD_TREE_CONFIG);
      }
      tryPlaceFeature(event, types, ConfigManager.STONEPETAL_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.HUT_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.BARROW_CONFIG);
      tryPlaceFeature(event, types, ConfigManager.SAND_HOUSE_CONFIG);
    }
  }

  @SubscribeEvent
  public static void onWorldLoad(final WorldEvent.Load event) {
*//*    if (event.getWorld() instanceof ServerLevel) {
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
    }*//*
  }*/
}
