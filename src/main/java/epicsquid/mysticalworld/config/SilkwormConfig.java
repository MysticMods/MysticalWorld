package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SilkwormConfig extends AbstractConfig {

  private boolean leafDrops;
  private int leafDropChance;
  private int successChance;
  private int growthChance;

  private ForgeConfigSpec.BooleanValue configLeafDrops;
  private ForgeConfigSpec.ConfigValue<Integer> configLeafDropChance;
  private ForgeConfigSpec.ConfigValue<Integer> configSuccessChance;
  private ForgeConfigSpec.ConfigValue<Integer> configGrowthChance;

  private int cachedLeaf = -9999;
  private int cachedDrop = -9999;
  private int cachedSuccess = -9999;
  private int cachedGrowth = -9999;

  public SilkwormConfig(boolean leafDrops, int leafDropChance, int successChance, int growthChance) {
    super();
    this.leafDrops = leafDrops;
    this.leafDropChance = leafDropChance;
    this.successChance = successChance;
    this.growthChance = growthChance;
  }

  public boolean getLeafDropsEnabled() {
    if (cachedDrop == -9999) {
      cachedDrop = configLeafDrops.get() ? 1 : 0;
    }
    return cachedDrop == 1;
  }

  public int getLeafDropChance() {
    if (cachedLeaf == -9999) {
      cachedLeaf = configLeafDropChance.get();
    }
    return cachedLeaf;
  }

  public int getSuccessChance() {
    if (cachedSuccess == -9999) {
      cachedSuccess = configSuccessChance.get();
    }
    return cachedSuccess;
  }

  public int getGrowthChance() {
    if (cachedGrowth == -9999) {
      cachedGrowth = configGrowthChance.get();
    }
    return cachedGrowth;
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

  @Override
  public void reset() {
    cachedLeaf = -9999;
    cachedDrop = -9999;
    cachedSuccess = -9999;
    cachedGrowth = -9999;
  }
}
