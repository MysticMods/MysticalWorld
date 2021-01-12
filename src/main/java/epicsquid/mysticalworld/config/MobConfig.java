package epicsquid.mysticalworld.config;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MobConfig extends AbstractConfig {

  protected String name;
  protected int chance;
  protected int min;
  protected int max;
  protected List<String> biomes;
  protected BiomeDictionary.Type restriction;

  protected ForgeConfigSpec.IntValue configChance;
  protected ForgeConfigSpec.IntValue configMin;
  protected ForgeConfigSpec.IntValue configMax;
  protected ForgeConfigSpec.ConfigValue<String> configBiomes;

  private int cachedChance = -9999;
  private int cachedMin = -9999;
  private int cachedMax = -9999;

  public MobConfig(String name, int chance, int min, int max, List<String> biomes) {
    this(name, chance, min, max, biomes, BiomeDictionary.Type.OVERWORLD);
  }

  public MobConfig(String name, int chance, int min, int max, List<String> biomes, BiomeDictionary.Type restriction) {
    super();
    this.name = name;
    this.chance = chance;
    this.min = min;
    this.max = max;
    this.biomes = biomes;
    this.restriction = restriction;
  }

  public BiomeDictionary.Type getRestriction() {
    return restriction;
  }

  public int getChance() {
    if (cachedChance == -9999) {
      cachedChance = configChance.get();
    }
    return cachedChance;
  }

  public int getMin() {
    if (cachedMin == -9999) {
      cachedMin = configMin.get();
  }
    return cachedMin;
  }

  public int getMax() {
    if (cachedMax == -9999) {
      cachedMax = configMax.get();
    }
    return cachedMax;
  }

  private List<BiomeDictionary.Type> cachedBiomes = null;

  public List<BiomeDictionary.Type> getBiomes() {
    if (cachedBiomes == null) {
      cachedBiomes = Stream.of(configBiomes.get().split(",")).map(o -> BiomeDictionary.Type.getType(o)).collect(Collectors.toList());
    }
    return cachedBiomes;
  }

  public boolean shouldRegister() {
    return getChance() > 0;
  }

  protected void preApply(ForgeConfigSpec.Builder builder) {
  }

  protected void doApply(ForgeConfigSpec.Builder builder) {
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
  }

  protected void postApply(ForgeConfigSpec.Builder builder) {
    builder.pop();
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    preApply(builder);
    doApply(builder);
    postApply(builder);
  }

  @Override
  public void reset() {
    cachedBiomes = null;
    cachedChance = -9999;
    cachedMin = -9999;
    cachedMax = -9999;
  }
}
