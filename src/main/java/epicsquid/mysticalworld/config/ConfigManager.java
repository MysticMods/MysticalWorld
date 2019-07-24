package epicsquid.mysticalworld.config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import epicsquid.mysticalworld.blocks.ModBlocks;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigManager {

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec COMMON_CONFIG;
	public static List<OreConfig> ORE_CONFIG = new ArrayList<>();

	static {
		COMMON_BUILDER.comment("Ore Generation").push("oregen");
		registerOreGeneration();
		COMMON_BUILDER.pop();

		COMMON_CONFIG = COMMON_BUILDER.build();
	}

	private static void registerOreGeneration() {
		ORE_CONFIG.add(new OreConfig("copper", 6, 32, 76, 12, () -> ModBlocks.COPPER_ORE));
		ORE_CONFIG.add(new OreConfig("tin", 6, 24, 64, 8, () -> ModBlocks.TIN_ORE));
		ORE_CONFIG.add(new OreConfig("silver", 4, 0,  24, 8, () -> ModBlocks.SILVER_ORE));
		ORE_CONFIG.add(new OreConfig("lead", 4, 8,  32, 8, () -> ModBlocks.LEAD_ORE));
		ORE_CONFIG.add(new OreConfig("quicksilver", 3, 0,  16, 4, () -> ModBlocks.QUICKSILVER_ORE));
		ORE_CONFIG.add(new OreConfig("amethyst", 2, 0,  24, 4, () -> ModBlocks.AMETHYST_ORE));

		ORE_CONFIG.forEach(ore -> ore.apply(COMMON_BUILDER));
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
		configData.load();
		spec.setConfig(configData);
	}
}
