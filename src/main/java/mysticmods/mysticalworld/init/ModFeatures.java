package mysticmods.mysticalworld.init;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.FeatureConfig;
import mysticmods.mysticalworld.config.OreConfig;
import mysticmods.mysticalworld.world.SupplierBlockStateProvider;
import mysticmods.mysticalworld.world.feature.SupplierOreFeature;
import mysticmods.mysticalworld.world.feature.SupplierOreFeatureConfig;
import mysticmods.mysticalworld.world.placement.DimensionConfig;
import mysticmods.mysticalworld.world.placement.DimensionCountPlacement;
import mysticmods.mysticalworld.world.placement.DimensionCountRangeConfig;
import mysticmods.mysticalworld.world.placement.DimensionPlacement;
import mysticmods.mysticalworld.world.test.OreGenTest;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
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
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.libs.noobutil.type.LazySupplier;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;

public class ModFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalWorld.MODID, WorldGenRegistries.CONFIGURED_FEATURE);

  public static final IRuleTestType<OreGenTest> ORE_GEN = IRuleTestType.register("ore_gen", OreGenTest.CODEC);

  public static final RegistryEntry<SupplierOreFeature> SUPPLIER_ORE = MysticalWorld.REGISTRATE.simple("supplier_ore_feature", Feature.class, () -> new SupplierOreFeature(SupplierOreFeatureConfig.CODEC));

  private static final RegistryEntry<DimensionCountPlacement> DIMENSION_COUNT_PLACEMENT = MysticalWorld.REGISTRATE.simple("dimension_count_placement", Placement.class, () -> new DimensionCountPlacement(DimensionCountRangeConfig.CODEC));

  private static final RegistryEntry<DimensionPlacement> DIMENSION_PLACEMENT = MysticalWorld.REGISTRATE.simple("dimension_placement", Placement.class, () -> new DimensionPlacement(DimensionConfig.CODEC));

  public static final RegistryEntry<BlockStateProviderType<SupplierBlockStateProvider>> SUPPLIER_STATE_PROVIDER = MysticalWorld.REGISTRATE.simple("supplier_state_provider", BlockStateProviderType.class, () -> new BlockStateProviderType<>(SupplierBlockStateProvider.CODEC));

  public static ConfiguredFeature<?, ?> CHARRED_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SupplierBlockStateProvider(MysticalWorld.MODID, "charred_log"), new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()), new FancyFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(4), 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, (float) ConfigManager.DEAD_TREE_CONFIG.getChance(), 1)));

  public static Supplier<ConfiguredFeature<?, ?>> STONEPETAL_PATCH = new LazySupplier<>(() -> Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.STONEPETAL.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(ConfigManager.STONEPETAL_CONFIG.getTries()).whitelist(Sets.newHashSet(Blocks.STONE)).build()).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(ConfigManager.STONEPETAL_CONFIG.getRepeats()));

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
      RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
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

  private static MethodHandle GETCODEC_METHOD = null;

  public static void onWorldLoad(final WorldEvent.Load event) {
    if (event.getWorld() instanceof ServerWorld) {
      ServerWorld world = (ServerWorld) event.getWorld();
      if (world.getChunkSource().getGenerator() instanceof FlatChunkGenerator && world.dimension().equals(World.OVERWORLD)) {
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

      ResourceLocation chunkGen = null;
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

      if (world.dimension().equals(World.OVERWORLD)) {
        Map<Structure<?>, StructureSeparationSettings> temp = new HashMap<>(world.getChunkSource().generator.getSettings().structureConfig());
        temp.put(ModStructures.BARROW_STRUCTURE, DimensionStructuresSettings.DEFAULTS.get(ModStructures.BARROW_STRUCTURE));
        temp.put(ModStructures.HUT_STRUCTURE, DimensionStructuresSettings.DEFAULTS.get(ModStructures.HUT_STRUCTURE));
        temp.put(ModStructures.RUINED_HUT_STRUCTURE, DimensionStructuresSettings.DEFAULTS.get(ModStructures.RUINED_HUT_STRUCTURE));
        temp.put(ModStructures.SAND_HOUSE_STRUCTURE, DimensionStructuresSettings.DEFAULTS.get(ModStructures.SAND_HOUSE_STRUCTURE));
        world.getChunkSource().generator.getSettings().structureConfig = temp;
      }
    }
  }
}
