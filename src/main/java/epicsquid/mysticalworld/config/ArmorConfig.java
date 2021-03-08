package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.config.IArmorConfig;

public class ArmorConfig extends AbstractConfig implements IArmorConfig {
  protected ForgeConfigSpec.IntValue configFeet;
  protected ForgeConfigSpec.IntValue configLegs;
  protected ForgeConfigSpec.IntValue configChest;
  protected ForgeConfigSpec.IntValue configHead;
  protected ForgeConfigSpec.DoubleValue configToughness;

  private int feet = -9999;
  private int legs = -9999;
  private int chest = -9999;
  private int head = -9999;
  private float toughness = -9999;

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
    configToughness = builder.comment("Toughness for armor made of this material").defineInRange("toughness", (double) this.defaultToughness, 0, Double.MAX_VALUE);
    builder.pop();
  }

  @Override
  public void reset() {
    feet = -9999;
    legs = -9999;
    chest = -9999;
    head = -9999;
    toughness = -9999;
  }

  public int getFeet() {
    if (feet == -9999) {
      feet = configFeet.get();
    }
    return feet;
  }

  public int getLegs() {
    if (legs == -9999) {
      legs = configLegs.get();
    }
    return legs;
  }

  public int getChest() {
    if (chest == -9999) {
      chest = configChest.get();
    }
    return chest;
  }

  public int getHead() {
    if (head == -9999) {
      head = configHead.get();
    }
    return head;
  }

  public float getToughness() {
    if (toughness == -9999) {
      toughness = (float) (double) configToughness.get();
    }
    return toughness;
  }
}
