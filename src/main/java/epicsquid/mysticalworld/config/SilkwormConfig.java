package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SilkwormConfig implements IConfig {

  private boolean leafDrops;
  private int leafDropChance;
  private int successChance;
  private int growthChance;

  private ForgeConfigSpec.BooleanValue configLeafDrops;
  private ForgeConfigSpec.ConfigValue<Integer> configLeafDropChance;
  private ForgeConfigSpec.ConfigValue<Integer> configSuccessChance;
  private ForgeConfigSpec.ConfigValue<Integer> configGrowthChance;

  public SilkwormConfig(boolean leafDrops, int leafDropChance, int successChance, int growthChance) {
    this.leafDrops = leafDrops;
    this.leafDropChance = leafDropChance;
    this.successChance = successChance;
    this.growthChance = growthChance;
  }

  public boolean getLeafDropsEnabled() {
    return configLeafDrops.get();
  }

  public int getLeafDropChance() {
    return configLeafDropChance.get();
  }

  public int getSuccessChance() {
    return configSuccessChance.get();
  }

  public int getGrowthChance() {
    return configGrowthChance.get();
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Silkworm spawn config.").push("silkworm_spawn");
    configLeafDrops = builder.comment("Whether or not silkworm eggs drop from leaves").define("leafDrops", leafDrops);
    configLeafDropChance = builder.comment("Chance to spawn whenever a leaf is broken (1 in X)").define("leafDropChance", leafDropChance);
    configSuccessChance = builder.comment("Chance for silkworm eggs to spawn a silkworm (1 in X, 1 is guaranteed)").define("successChance", successChance);
    configGrowthChance = builder.comment("Chance per tick for silkworms to grown (1 in X)").define("growthChance", growthChance);
    builder.pop();
  }
}
