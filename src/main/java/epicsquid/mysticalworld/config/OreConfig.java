package epicsquid.mysticalworld.config;

import com.tterrag.registrate.util.RegistryEntry;
import epicsquid.mysticallib.block.BaseOreBlock;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class OreConfig {

  private String name;
  private int chance;
  private int minY;
  private int maxY;
  private int size;
  private Supplier<RegistryEntry<BaseOreBlock>> ore;

  private ForgeConfigSpec.IntValue configChance;
  private ForgeConfigSpec.IntValue configMinY;
  private ForgeConfigSpec.IntValue configMaxY;
  private ForgeConfigSpec.IntValue configSize;

  public OreConfig(String name, int chance, int minY, int maxY, int size, Supplier<RegistryEntry<BaseOreBlock>> ore) {
    this.name = name;
    this.chance = chance;
    this.minY = minY;
    this.maxY = maxY;
    this.size = size;
    this.ore = ore;
  }

  public String getName() {
    return name;
  }

  public int getChance() {
    return configChance.get();
  }

  public int getMinY() {
    return configMinY.get();
  }

  public int getMaxY() {
    return configMaxY.get();
  }

  public int getSize() {
    return configSize.get();
  }

  public Block getOre() {
    return ore.get().get();
  }

  public boolean shouldRegister() {
    return getChance() > 0;
  }

  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " ore generation.").push(name + "_oregen");
    configChance = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("oreChances", chance, 0, 256);
    configSize = builder.comment("Max size of the vein.").defineInRange("veinSize", size, 1, 256);
    configMinY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("minY", minY, 0, 256);
    configMaxY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("maxY", maxY, 0, 256);
    builder.pop();
  }
}
