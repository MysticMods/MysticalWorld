package mysticmods.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.config.IArmorConfig;

public class ArmorConfig extends AbstractConfig implements IArmorConfig {
  protected ForgeConfigSpec.IntValue configFeet;
  protected ForgeConfigSpec.IntValue configLegs;
  protected ForgeConfigSpec.IntValue configChest;
  protected ForgeConfigSpec.IntValue configHead;
  protected ForgeConfigSpec.DoubleValue configToughness;

  private final int defaultFeet;
  private final int defaultLegs;
  private final int defaultChest;
  private final int defaultHead;
  private final float defaultToughness;
  private final String armor;

  public ArmorConfig(String armor, int defaultFeet, int defaultLegs, int defaultChest, int defaultHead, float defaultToughness) {
    super();
    this.armor = armor;
    this.defaultFeet = defaultFeet;
    this.defaultLegs = defaultLegs;
    this.defaultChest = defaultChest;
    this.defaultHead = defaultHead;
    this.defaultToughness = defaultToughness;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Configuration for " + this.armor + " armor").push(armor + "_armor");
    configChest = builder.comment("Armor value for chest piece").defineInRange("chest_armor", this.defaultChest, 0, Integer.MAX_VALUE);
    configFeet = builder.comment("Armor value for feet piece").defineInRange("feet_armor", this.defaultFeet, 0, Integer.MAX_VALUE);
    configHead = builder.comment("Armor value for head piece").defineInRange("head_armor", this.defaultHead, 0, Integer.MAX_VALUE);
    configLegs = builder.comment("Armor value for legs piece").defineInRange("legs_armor", this.defaultLegs, 0, Integer.MAX_VALUE);
    configToughness = builder.comment("Toughness for armor made of this material").defineInRange("toughness", this.defaultToughness, 0, Double.MAX_VALUE);
    builder.pop();
  }

  @Override
  public void reset() {

  }

  public int getFeet() {
    return configFeet.get();
  }

  public int getLegs() {
    return configLegs.get();
  }

  public int getChest() {
    return configChest.get();
  }

  public int getHead() {
    return configHead.get();
  }

  public float getToughness() {
    return (float) (double) configToughness.get();
  }
}
