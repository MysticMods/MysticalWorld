package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class FeatureConfig {

  private String name;
  private double chance;
  private List<String> biomes;

  private ForgeConfigSpec.DoubleValue configChance;
  private ForgeConfigSpec.ConfigValue<String> configBiomes;

  public FeatureConfig(String name, double chance, List<String> biomes) {
    this.name = name;
    this.chance = chance;
    this.biomes = biomes;
  }

  public float getChance() {
    return (float) (double) configChance.get();
  }

  public List<String> getBiomes() {
    String values = configBiomes.get();
    return Arrays.asList(values.split(","));
  }

  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " spawn config.").push(name + "_spawn");
    configChance = builder.comment("Percent chance to spawn (set to 0 to disable) per chunk.").defineInRange("spawnChance", chance, 0, 1);

    StringBuilder sb = new StringBuilder();
    biomes.forEach(biome -> {
      sb.append(biome);
      sb.append(",");
    });

    configBiomes = builder.comment("List of biome types to spawn.").define("biomes", sb.toString());
    builder.pop();
  }
}
