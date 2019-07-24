package epicsquid.mysticalworld.world;

import java.util.ArrayList;
import java.util.List;

import epicsquid.mysticallib.world.OreGenerator;
import epicsquid.mysticallib.world.OreProperties;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.config.OreConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class OreGen {

	private static List<OreGenerator> generators = new ArrayList<>();

	public static void registerOreGeneration() {
		ConfigManager.ORE_CONFIG.stream().filter(OreConfig::shouldRegister).forEach(ore -> generators.add(new OreGenerator(
				new OreProperties(
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ore.getOre().getDefaultState(), ore.getChance()),
						new CountRangeConfig(ore.getSize(), ore.getMinY(), 0, ore.getMaxY() - ore.getMinY()))
		)));

		generators.forEach(OreGenerator::init);
	}
}
