package epicsquid.mysticalworld.world;

import java.util.ArrayList;
import java.util.List;

import epicsquid.mysticallib.world.OreGenerator;
import epicsquid.mysticallib.world.OreProperties;
import epicsquid.mysticalworld.blocks.ModBlocks;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class OreGen {
  
  public static List<OreGenerator> generators = new ArrayList<>();

  public static void registerOre() {
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.COPPER_ORE.getDefaultState(), 12),
        new CountRangeConfig(6, 0, 0, 64))));
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TIN_ORE.getDefaultState(), 8),
        new CountRangeConfig(6, 0, 0, 48))));
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SILVER_ORE.getDefaultState(), 8),
        new CountRangeConfig(4, 0, 0, 28))));
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.LEAD_ORE.getDefaultState(), 8),
        new CountRangeConfig(4, 0, 0, 28))));
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.QUICKSILVER_ORE.getDefaultState(), 4),
        new CountRangeConfig(3, 0, 0, 16))));
    generators.add(new OreGenerator(new OreProperties(
        new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.AMETHYST_ORE.getDefaultState(), 5),
        new CountRangeConfig(2, 0, 0, 32))));
  }
}
