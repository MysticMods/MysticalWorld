package epicsquid.mysticalworld.config;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.block.BaseBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OreConfig implements IConfig {

  private String name;
  private int chance;
  private int minY;
  private int maxY;
  private int size;
  private List<? extends Integer> dimensions;
  private Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore;

  private ForgeConfigSpec.IntValue configChance;
  private ForgeConfigSpec.IntValue configMinY;
  private ForgeConfigSpec.IntValue configMaxY;
  private ForgeConfigSpec.IntValue configSize;
  private ForgeConfigSpec.ConfigValue<List<? extends Integer>> configDimensions;

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<Integer> dimensions, Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore) {
    this.name = name;
    this.chance = chance;
    this.minY = minY;
    this.maxY = maxY;
    this.size = size;
    this.ore = ore;
    this.dimensions = dimensions;
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

  public List<? extends Integer> getDimensions() {
    return configDimensions.get();
  }

  public DimensionType[] getDimensionsAsArray() {
    List<DimensionType> dimensions = new ArrayList<>();
    configDimensions.get().forEach(o -> dimensions.add(DimensionType.getById(o)));
    return dimensions.toArray(new DimensionType[0]);
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " ore generation.").push(name + "_oregen");
    configChance = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("oreChances", chance, 0, 256);
    configSize = builder.comment("Max size of the vein.").defineInRange("veinSize", size, 1, 256);
    configMinY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("minY", minY, 0, 256);
    configMaxY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("maxY", maxY, 0, 256);
    configDimensions = builder.comment("The dimensions that this ore should spawn in as a list (default [0], overworld)").defineList("dimensions", dimensions, (o) -> true);
    builder.pop();
  }
}
