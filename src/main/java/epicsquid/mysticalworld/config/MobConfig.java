package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class MobConfig {

  private String name;
  private int chance;
  private int min;
  private int max;
  private List<String> biomes;

  private ForgeConfigSpec.IntValue configChance;
  private ForgeConfigSpec.IntValue configMin;
  private ForgeConfigSpec.IntValue configMax;
  private ForgeConfigSpec.ConfigValue<String> configBiomes;

  public MobConfig(String name, int chance, int min, int max, List<String> biomes) {
    this.name = name;
    this.chance = chance;
    this.min = min;
    this.max = max;
    this.biomes = biomes;
  }

  public int getChance() {
    return configChance.get();
  }

  public int getMin() {
    return configMin.get();
  }

  public int getMax() {
    return configMax.get();
  }

  public List<String> getBiomes() {
    String values = configBiomes.get();
    return Arrays.asList(values.split(","));
  }

  public boolean shouldRegister() {
    return getChance() > 0;
  }

  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " spawn config.").push(name + "_spawn");
    configChance = builder.comment("Chance to spawn (set to 0 to disable).").defineInRange("spawnChance", chance, 0, 256);
    configMin = builder.comment("Min to spawn in a group.").defineInRange("min", min, 0, 256);
    configMax = builder.comment("Max to spawn in a group.").defineInRange("max", max, 0, 256);

    StringBuilder sb = new StringBuilder();
    biomes.forEach(biome -> {
      sb.append(biome);
      sb.append(",");
    });

    configBiomes = builder.comment("List of biome types to spawn.").define("biomes", sb.toString());
    builder.pop();
  }
}
