package epicsquid.mysticalworld.config;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.block.BaseBlocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OreConfig implements IConfig {

  private String name;
  private int chance;
  private int minY;
  private int maxY;
  private int size;
  private List<RegistryKey<World>> dimensions;
  private Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore;

  private ForgeConfigSpec.IntValue configChance;
  private ForgeConfigSpec.IntValue configMinY;
  private ForgeConfigSpec.IntValue configMaxY;
  private ForgeConfigSpec.IntValue configSize;
  private ForgeConfigSpec.ConfigValue<List<? extends String>> configDimensions;

  private int cachedChance = -9999;
  private int cachedMin = -9999;
  private int cachedMax = -9999;
  private int cachedSize = -9999;

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<RegistryKey<World>> dimensions, Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore) {
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
    if (cachedChance == -9999) {
      cachedChance = configChance.get();
    }
    return cachedChance;
  }

  public int getMinY() {
    if (cachedMin == -9999) {
      cachedMin = configMinY.get();
    }
    return cachedMin;
  }

  public int getMaxY() {
    if (cachedMax == -9999) {
      cachedMax = configMaxY.get();
    }
    return cachedMax;
  }

  public int getSize() {
    if (cachedSize == -9999) {
      cachedSize = configSize.get();
    }
    return cachedSize;
  }

  public Block getOre() {
    return ore.get().get();
  }

  public ResourceLocation getOreKey() {
    return ore.get().getId();
  }

  private Set<RegistryKey<World>> storedDimension = null;

  public Set<RegistryKey<World>> getDimensions() {
    if (storedDimension == null) {
      storedDimension = configDimensions.get().stream().map(o -> RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(o))).collect(Collectors.toSet());
    }

    return storedDimension;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment(name + " ore generation.").push(name + "_oregen");
    configChance = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("oreChances", chance, 0, 256);
    configSize = builder.comment("Max size of the vein.").defineInRange("veinSize", size, 1, 256);
    configMinY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("minY", minY, 0, 256);
    configMaxY = builder.comment("Number of veins per chunk (set to 0 to disable).").defineInRange("maxY", maxY, 0, 256);
    configDimensions = builder.comment("The dimensions that this ore should spawn in as a list (default [\"minecraft:overworld\"])").defineList("dimensions", dimensions.stream().map(RegistryKey::getLocation).map(ResourceLocation::toString).collect(Collectors.toList()), (o) -> o instanceof String);
    builder.pop();
  }

  @Override
  public void reset() {
    cachedChance = -9999;
    cachedMin = -9999;
    cachedMax = -9999;
    cachedSize = -9999;
    storedDimension = null;
  }
}
