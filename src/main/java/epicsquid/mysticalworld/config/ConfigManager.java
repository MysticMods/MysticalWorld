package epicsquid.mysticalworld.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ConfiguredStructures;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModFeatures;
import epicsquid.mysticalworld.init.ModMaterials;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import noobanidus.libs.noobutil.config.IArmorConfig;

import java.nio.file.Path;
import java.util.*;

public class ConfigManager {

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  public static List<OreConfig> ORE_CONFIG = new ArrayList<>();
  public static MobConfig DEER_CONFIG;
  public static MobConfig SPROUT_CONFIG;
  public static HellSproutConfig HELL_SPROUT_CONFIG;
  public static MobConfig BEETLE_CONFIG;
  public static MobConfig SILVER_FOX_CONFIG;
  public static MobConfig FROG_CONFIG;
  public static MobConfig ENDERMINI_CONFIG;
  public static MobConfig LAVA_CAT_CONFIG;
  public static MobConfig OWL_CONFIG;
  public static SilkwormConfig SILKWORM_CONFIG;
  public static HatConfig HAT_CONFIG;
  public static TreeConfig DEAD_TREE_CONFIG;
  public static StonepetalConfig STONEPETAL_CONFIG;
  public static StructureConfig HUT_CONFIG;
  public static StructureConfig BARROW_CONFIG;

  public static List<AbstractConfig> CONFIGS = new ArrayList<>();

  public static Map<String, ArmorConfig> ARMOR_CONFIGS = new HashMap<>();

  public static IArmorConfig getArmorConfig (String name) {
    return ARMOR_CONFIGS.get(name);
  }

  static {
    COMMON_BUILDER.comment("Ore Generation").push("oregen");
    registerOreGeneration();
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Mob Spawn Configuration").push("mob_spawns");
    registerMobConfigs();
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Hat Configuration").push("hat_config");
    HAT_CONFIG.apply(COMMON_BUILDER);
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Armor Configuration").push("armor_config");

    ARMOR_CONFIGS.put(ModMaterials.AMETHYST_NAME, new ArmorConfig(ModMaterials.AMETHYST_NAME, 3, 6, 8, 3, 2.0f));
    ARMOR_CONFIGS.put(ModMaterials.COPPER_NAME, new ArmorConfig(ModMaterials.COPPER_NAME, 2, 5, 6, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.LEAD_NAME, new ArmorConfig(ModMaterials.LEAD_NAME, 2, 5, 6, 2, 1f));
    ARMOR_CONFIGS.put(ModMaterials.QUICKSILVER_NAME, new ArmorConfig(ModMaterials.QUICKSILVER_NAME, 1, 3, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.SILVER_NAME, new ArmorConfig(ModMaterials.SILVER_NAME, 1, 3, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.TIN_NAME, new ArmorConfig(ModMaterials.TIN_NAME, 1, 4, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.CARAPACE_NAME, new ArmorConfig(ModMaterials.CARAPACE_NAME, 3, 0, 0, 0, 1f));
    ARMOR_CONFIGS.put(ModMaterials.ANTLER_NAME, new ArmorConfig(ModMaterials.ANTLER_NAME, 3, 0, 0, 0, 1f));
    ARMOR_CONFIGS.values().forEach(o -> o.apply(COMMON_BUILDER));

    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Feature Spawn Configuration").push("feature_spawns");
    DEAD_TREE_CONFIG = new TreeConfig(0.04, Arrays.asList(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.WASTELAND), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END)).setFeature(() -> ModFeatures.CHARRED_TREE);
    DEAD_TREE_CONFIG.apply(COMMON_BUILDER);
    STONEPETAL_CONFIG = new StonepetalConfig(1, 7, Arrays.asList(BiomeDictionary.Type.MOUNTAIN), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END)).setSupplierFeature(() -> ModFeatures.STONEPETAL_PATCH);
    STONEPETAL_CONFIG.apply(COMMON_BUILDER);
    HUT_CONFIG = new StructureConfig("hut", Arrays.asList(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.PLAINS), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END)).setStructure(() -> ConfiguredStructures.CONFIGURED_HUT);
    HUT_CONFIG.apply(COMMON_BUILDER);
    BARROW_CONFIG = new StructureConfig("barrow", Arrays.asList(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.PLAINS), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END)).setStructure(() -> ConfiguredStructures.CONFIGURED_BARROW);
    BARROW_CONFIG.apply(COMMON_BUILDER);
    COMMON_BUILDER.pop();

    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  private static void registerOreGeneration() {
    List<RegistryKey<World>> defaultDimensions = Collections.singletonList(World.OVERWORLD);
    ORE_CONFIG.add(new OreConfig("Copper", 8, 32, 76, 12, defaultDimensions, () -> ModBlocks.COPPER_ORE));
    ORE_CONFIG.add(new OreConfig("Tin", 8, 24, 64, 8, defaultDimensions, () -> ModBlocks.TIN_ORE));
    ORE_CONFIG.add(new OreConfig("Silver", 4, 0, 24, 8, defaultDimensions, () -> ModBlocks.SILVER_ORE));
    ORE_CONFIG.add(new OreConfig("Lead", 4, 8, 32, 8, defaultDimensions, () -> ModBlocks.LEAD_ORE));
    ORE_CONFIG.add(new OreConfig("Quicksilver", 3, 0, 16, 4, defaultDimensions, () -> ModBlocks.QUICKSILVER_ORE));
    ORE_CONFIG.add(new OreConfig("Amethyst", 4, 0, 24, 3, defaultDimensions, () -> ModBlocks.AMETHYST_ORE));
    ORE_CONFIG.add(new OreConfig("Quartz", 13, 24, 76, 4, defaultDimensions, () -> ModBlocks.GRANITE_QUARTZ_ORE, new TagMatchRuleTest(MWTags.Blocks.BASE_STONE_GRANITE)));

    ORE_CONFIG.forEach(ore -> ore.apply(COMMON_BUILDER));
  }

  private static void registerMobConfigs() {
    DEER_CONFIG = new MobConfig("Deer", 6, 2, 4, Arrays.asList("forest", "icy", "taiga", "plains"));
    SPROUT_CONFIG = new MobConfig("Sprout", 2, 2, 4, Arrays.asList("jungle", "river", "forest", "beach"));
    HELL_SPROUT_CONFIG = new HellSproutConfig("Hell Sprout", 2, 1, 1, Collections.singletonList("nether"), 100);
    SILVER_FOX_CONFIG = new MobConfig("Silver Fox", 4, 1, 3, Arrays.asList("forest", "icy", "taiga"));
    BEETLE_CONFIG = new MobConfig("Beetle", 5, 2, 4, Arrays.asList("swamp", "jungle", "forest", "plains"));
    FROG_CONFIG = new MobConfig("Frog", 6, 2, 4, Arrays.asList("swamp", "jungle", "beach", "river"));
    ENDERMINI_CONFIG = new MobConfig("Endermini", 2, 1, 2, Collections.singletonList("end"), BiomeDictionary.Type.END);
    OWL_CONFIG = new MobConfig("Owl", 9, 1, 3, Arrays.asList("taiga", "extreme_hills", "forest", "icy"));
    LAVA_CAT_CONFIG = new MobConfig("Lava Cat", 2, 1, 1, Collections.singletonList("nether"), BiomeDictionary.Type.NETHER); // test
    SILKWORM_CONFIG = new SilkwormConfig(true, 65, 1, 68);
    HAT_CONFIG = new HatConfig();

    DEER_CONFIG.apply(COMMON_BUILDER);
    SPROUT_CONFIG.apply(COMMON_BUILDER);
    HELL_SPROUT_CONFIG.apply(COMMON_BUILDER);
    SILVER_FOX_CONFIG.apply(COMMON_BUILDER);
    BEETLE_CONFIG.apply(COMMON_BUILDER);
    FROG_CONFIG.apply(COMMON_BUILDER);
    ENDERMINI_CONFIG.apply(COMMON_BUILDER);
    OWL_CONFIG.apply(COMMON_BUILDER);
    LAVA_CAT_CONFIG.apply(COMMON_BUILDER);
    SILKWORM_CONFIG.apply(COMMON_BUILDER);
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }

  public static void configReload(ModConfig.ModConfigEvent event) {
    CONFIGS.forEach(AbstractConfig::reset);
  }
}
