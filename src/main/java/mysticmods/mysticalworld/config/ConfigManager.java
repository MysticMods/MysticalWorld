package mysticmods.mysticalworld.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModFeatures;
import mysticmods.mysticalworld.init.ModMaterials;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import noobanidus.libs.noobutil.config.IArmorConfig;

import java.nio.file.Path;
import java.util.*;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigManager {

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
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
  public static MobConfig DUCK_CONFIG;
  public static ClamConfig CLAM_CONFIG;
  public static OreConfig TIN_ORE;
  public static OreConfig SILVER_ORE;
  public static OreConfig LEAD_ORE;
  public static OreConfig SAPPHIRE_ORE;
  public static OreConfig GRANITE_QUARTZ_ORE;
  public static WildAubergineConfig WILD_AUBERGINE;

  public static ForgeConfigSpec.BooleanValue SEED_INJECTION;

  public static List<AbstractConfig> CONFIGS = new ArrayList<>();

  public static Map<String, ArmorConfig> ARMOR_CONFIGS = new HashMap<>();

  public static IArmorConfig getArmorConfig(String name) {
    return ARMOR_CONFIGS.get(name);
  }

  public static List<OreConfig> ORE_CONFIGS;

  static {
    List<ResourceKey<Level>> defaultDimensions = Collections.singletonList(Level.OVERWORLD);
    COMMON_BUILDER.comment("Ore Generation").push("oregen");
    TIN_ORE = new OreConfig("Tin", 7, 40, 180, 8, defaultDimensions);
    SILVER_ORE = new OreConfig("Silver", 4, -64, 64, 8, defaultDimensions);
    LEAD_ORE = new OreConfig("Lead", 4, -32, 50, 8, defaultDimensions);
    SAPPHIRE_ORE = new OreConfig("Sapphire", 4, -64, 50, 3, defaultDimensions);
    GRANITE_QUARTZ_ORE = new OreConfig("Quartz", 90, -64, 256, 5, defaultDimensions);
    ORE_CONFIGS = List.of(TIN_ORE, SILVER_ORE, LEAD_ORE, SAPPHIRE_ORE, GRANITE_QUARTZ_ORE);

    TIN_ORE.apply(COMMON_BUILDER);
    SILVER_ORE.apply(COMMON_BUILDER);
    LEAD_ORE.apply(COMMON_BUILDER);
    SAPPHIRE_ORE.apply(COMMON_BUILDER);
    GRANITE_QUARTZ_ORE.apply(COMMON_BUILDER);
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Mob Spawn Configuration").push("mob_spawns");
    registerMobConfigs();
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Hat Configuration").push("hat_config");
    HAT_CONFIG.apply(COMMON_BUILDER);
    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Armor Configuration").push("armor_config");

    ARMOR_CONFIGS.put(ModMaterials.SAPPHIRE_NAME, new ArmorConfig(ModMaterials.SAPPHIRE_NAME, 3, 6, 8, 3, 2.0f));
    ARMOR_CONFIGS.put(ModMaterials.COPPER_NAME, new ArmorConfig(ModMaterials.COPPER_NAME, 2, 5, 6, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.LEAD_NAME, new ArmorConfig(ModMaterials.LEAD_NAME, 2, 5, 6, 2, 1f));
    ARMOR_CONFIGS.put(ModMaterials.ORICHALCUM_NAME, new ArmorConfig(ModMaterials.ORICHALCUM_NAME, 1, 3, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.SILVER_NAME, new ArmorConfig(ModMaterials.SILVER_NAME, 1, 3, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.TIN_NAME, new ArmorConfig(ModMaterials.TIN_NAME, 1, 4, 5, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.CARAPACE_NAME, new ArmorConfig(ModMaterials.CARAPACE_NAME, 2, 5, 6, 2, 0f));
    ARMOR_CONFIGS.put(ModMaterials.ANTLER_NAME, new ArmorConfig(ModMaterials.ANTLER_NAME, 3, 0, 0, 0, 1f));
    ARMOR_CONFIGS.values().forEach(o -> o.apply(COMMON_BUILDER));

    COMMON_BUILDER.pop();
    COMMON_BUILDER.comment("Feature Spawn Configuration").push("feature_spawns");
    DEAD_TREE_CONFIG = new TreeConfig(0.02, Arrays.asList(BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.WASTELAND), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END), defaultDimensions);
    DEAD_TREE_CONFIG.apply(COMMON_BUILDER);
    STONEPETAL_CONFIG = new StonepetalConfig(1, 7, Arrays.asList(BiomeDictionary.Type.MOUNTAIN), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END), defaultDimensions);
    STONEPETAL_CONFIG.apply(COMMON_BUILDER);
    WILD_AUBERGINE = new WildAubergineConfig(1, 20, Arrays.asList(BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE), Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END), defaultDimensions);
    WILD_AUBERGINE.apply(COMMON_BUILDER);
    SEED_INJECTION = COMMON_BUILDER.comment("Whether or not the aubergine loot table is injected into the grass seed table").define("seed_injection", true);
    COMMON_BUILDER.pop();

    COMMON_CONFIG = COMMON_BUILDER.build();
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
    DUCK_CONFIG = new MobConfig("Duck", 5, 1, 4, Arrays.asList("wet", "swamp", "river", "beach"));
    HAT_CONFIG = new HatConfig();
    CLAM_CONFIG = new ClamConfig("Clam", 2, 1, 1, Arrays.asList("ocean", "river", "beach", "swamp", "wet"), 31, 2300, 5100);

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
    DUCK_CONFIG.apply(COMMON_BUILDER);
    CLAM_CONFIG.apply(COMMON_BUILDER);
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }

  public static void configReload(ModConfigEvent event) {
    CONFIGS.forEach(AbstractConfig::reset);
    ModFeatures.ORE_FEATURES = null;
  }

  @SubscribeEvent
  public static void onConfigReload(ModConfigEvent.Reloading event) {
    configReload(event);
  }

  @SubscribeEvent
  public static void onConfigLoaded(ModConfigEvent.Loading event) {
    configReload(event);
  }
}
