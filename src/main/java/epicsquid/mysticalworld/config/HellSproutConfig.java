package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class HellSproutConfig extends MobConfig {
  protected int growChance;

  protected ForgeConfigSpec.IntValue configGrowChance;

  public HellSproutConfig(String name, int chance, int min, int max, List<String> biomes, int growChance) {
    super(name, chance, min, max, biomes);
    this.growChance = growChance;
  }

  public int getGrowChance() {
    return configGrowChance.get();
  }

  @Override
  protected void doApply(ForgeConfigSpec.Builder builder) {
    super.doApply(builder);
    configGrowChance = builder.comment("how often a hell sprout should attempt to plant nether wart (1 in X, 0 for never)").defineInRange("chance", growChance, 0, Integer.MAX_VALUE);
  }
}
