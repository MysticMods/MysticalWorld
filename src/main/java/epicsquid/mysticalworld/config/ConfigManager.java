package epicsquid.mysticalworld.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  private static void registerOreGeneration() {
    List<Integer> defaultDimensions = Collections.singletonList(0);
    ORE_CONFIG.add(new OreConfig("Copper", 8, 32, 76, 12, defaultDimensions, () -> ModBlocks.COPPER_ORE));
    ORE_CONFIG.add(new OreConfig("Tin", 8, 24, 64, 8, defaultDimensions, () -> ModBlocks.TIN_ORE));
    ORE_CONFIG.add(new OreConfig("Silver", 4, 0, 24, 8, defaultDimensions, () -> ModBlocks.SILVER_ORE));
    ORE_CONFIG.add(new OreConfig("Lead", 4, 8, 32, 8, defaultDimensions, () -> ModBlocks.LEAD_ORE));
    ORE_CONFIG.add(new OreConfig("Quicksilver", 3, 0, 16, 4, defaultDimensions, () -> ModBlocks.QUICKSILVER_ORE));
    ORE_CONFIG.add(new OreConfig("Amethyst", 4, 0, 24, 3, defaultDimensions, () -> ModBlocks.AMETHYST_ORE));

    ORE_CONFIG.forEach(ore -> ore.apply(COMMON_BUILDER));
  }

  private static void registerMobConfigs() {
    DEER_CONFIG = new MobConfig("Deer", 6, 2, 4, Arrays.asList("FOREST", "COLD", "CONIFEROUS", "PLAINS"));
    SPROUT_CONFIG = new MobConfig("Sprout", 2, 2, 4, Arrays.asList("JUNGLE", "LUSH", "MAGICAL", "RIVER", "FOREST", "BEACH"));
    HELL_SPROUT_CONFIG = new HellSproutConfig("Hell Sprout", 8, 1, 3, Collections.singletonList("NETHER"), 100);
    SILVER_FOX_CONFIG = new MobConfig("Silver Fox", 4, 1, 3, Arrays.asList("FOREST", "COLD", "CONIFEROUS"));
    BEETLE_CONFIG = new MobConfig("Beetle", 5, 2, 4, Arrays.asList("SWAMP", "JUNGLE", "FOREST", "PLAINS"));
    FROG_CONFIG = new MobConfig("Frog", 6, 2, 4, Arrays.asList("SWAMP", "JUNGLE", "BEACH", "RIVER"));
    ENDERMINI_CONFIG = new MobConfig("Endermini", 1, 1, 2, Collections.singletonList("END"));
    OWL_CONFIG = new MobConfig("Owl", 9, 1, 3, Arrays.asList("CONIFEROUS", "SPOOKY", "MAGICAL", "FOREST", "MOUNTAIN", "SNOWY"));
    LAVA_CAT_CONFIG = new MobConfig("Lava Cat", 1, 1, 1, Collections.singletonList("NETHER")); // Test
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
}
