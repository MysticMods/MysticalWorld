package epicsquid.mysticalworld.config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import epicsquid.mysticalworld.blocks.ModBlocks;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigManager {

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec COMMON_CONFIG;
	public static List<OreConfig> ORE_CONFIG = new ArrayList<>();
	public static MobConfig DEER_CONFIG;
	public static MobConfig SPROUT_CONFIG;
	public static MobConfig BEETLE_CONFIG;
	public static MobConfig SILVER_FOX_CONFIG;
	public static MobConfig FROG_CONFIG;

	static {
		COMMON_BUILDER.comment("Ore Generation").push("oregen");
		registerOreGeneration();
		COMMON_BUILDER.pop();
		COMMON_BUILDER.comment("Mob Spawn Configuration").push("mob_spawns");
		registerMobConfigs();
		COMMON_BUILDER.pop();

		COMMON_CONFIG = COMMON_BUILDER.build();
	}

	private static void registerOreGeneration() {
		ORE_CONFIG.add(new OreConfig("Copper", 6, 32, 76, 12, () -> ModBlocks.COPPER_ORE));
		ORE_CONFIG.add(new OreConfig("Tin", 6, 24, 64, 8, () -> ModBlocks.TIN_ORE));
		ORE_CONFIG.add(new OreConfig("Silver", 4, 0,  24, 8, () -> ModBlocks.SILVER_ORE));
		ORE_CONFIG.add(new OreConfig("Lead", 4, 8,  32, 8, () -> ModBlocks.LEAD_ORE));
		ORE_CONFIG.add(new OreConfig("Quicksilver", 3, 0,  16, 4, () -> ModBlocks.QUICKSILVER_ORE));
		ORE_CONFIG.add(new OreConfig("Amethyst", 2, 0,  24, 4, () -> ModBlocks.AMETHYST_ORE));

		ORE_CONFIG.forEach(ore -> ore.apply(COMMON_BUILDER));
	}

	private static void registerMobConfigs() {
		DEER_CONFIG = new MobConfig("Deer", 6, 2, 4, Arrays.asList("FOREST", "COLD", "CONIFEROUS", "PLAINS"));
		SPROUT_CONFIG = new MobConfig("Sprout", 2, 2, 4, Arrays.asList("JUNGLE", "LUSH", "MAGICAL", "RIVER", "FOREST", "BEACH"));
		SILVER_FOX_CONFIG = new MobConfig("Silver Fox", 4, 1, 3, Arrays.asList("FOREST", "COLD", "CONIFEROUS"));
		BEETLE_CONFIG = new MobConfig("Beetle", 5, 2, 4, Arrays.asList("SWAMP", "JUNGLE", "FOREST", "PLAINS"));
		FROG_CONFIG = new MobConfig("Frog", 6, 2, 4, Arrays.asList("SWAMP", "JUNGLE", "BEACH", "RIVER"));

		DEER_CONFIG.apply(COMMON_BUILDER);
		SPROUT_CONFIG.apply(COMMON_BUILDER);
		SILVER_FOX_CONFIG.apply(COMMON_BUILDER);
		BEETLE_CONFIG.apply(COMMON_BUILDER);
		FROG_CONFIG.apply(COMMON_BUILDER);
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
		configData.load();
		spec.setConfig(configData);
	}
}
