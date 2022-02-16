package mysticmods.mysticalworld.config;

import net.minecraft.entity.EntityClassification;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class ClamConfig extends MobConfig {
  protected ForgeConfigSpec.IntValue configEnderChance;
  protected ForgeConfigSpec.IntValue configMaxAge;
  protected ForgeConfigSpec.IntValue configInitialAge;

  private final int defaultEnderChance;
  private final int defaultMaxAge;
  private final int defaultInitialAge;

  public ClamConfig(String name, int chance, int min, int max, List<String> biomes, int enderChance, int initialAge, int maxAge) {
    super(name, chance, min, max, biomes, BiomeDictionary.Type.OVERWORLD);
    this.defaultEnderChance = enderChance;
    this.defaultInitialAge = initialAge;
    this.defaultMaxAge = maxAge;
  }

  @Override
  public EntityClassification getClassification() {
    return EntityClassification.WATER_CREATURE;
  }

  public int getEnderChance() {
    return configEnderChance.get();
  }

  public int getInitialAge() {
    return configInitialAge.get();
  }

  public int getMaxAge() {
    return configMaxAge.get();
  }

  @Override
  protected void doApply(ForgeConfigSpec.Builder builder) {
    super.doApply(builder);
    configEnderChance = builder.comment("the chance of a clam spawning as an ender clam [0 to disable ender clams]").defineInRange("enderChance", defaultEnderChance, 0, Integer.MAX_VALUE);
    configInitialAge = builder.comment("the upper value of initial age for a clam").defineInRange("initialAge", defaultInitialAge, 0, Integer.MAX_VALUE);
    configMaxAge = builder.comment("the max age for a clam").defineInRange("maxAge", defaultMaxAge, 0, Integer.MAX_VALUE);
  }
}
