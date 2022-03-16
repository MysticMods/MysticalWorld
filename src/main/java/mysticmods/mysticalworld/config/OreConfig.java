package mysticmods.mysticalworld.config;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.block.BaseBlocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OreConfig extends AbstractConfig {

  private final String name;
  private final int chance;
  private final int minY;
  private final int maxY;
  private final int size;
  private final List<ResourceKey<Level>> dimensions;
  private final Supplier<RegistryObject<BaseBlocks.OreBlock>> ore;

  private ForgeConfigSpec.IntValue configChance;
  private ForgeConfigSpec.IntValue configMinY;
  private ForgeConfigSpec.IntValue configMaxY;
  private ForgeConfigSpec.IntValue configSize;
  private ForgeConfigSpec.ConfigValue<List<? extends String>> configDimensions;

  private final RuleTest rule;

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<ResourceKey<Level>> dimensions, Supplier<RegistryObject<BaseBlocks.OreBlock>> ore) {
    this(name, chance, minY, maxY, size, dimensions, ore, /*OreGenTest.INSTANCE*/ null);
  }

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<ResourceKey<Level>> dimensions, Supplier<RegistryObject<BaseBlocks.OreBlock>> ore, RuleTest test) {
    super();
    this.name = name;
    this.chance = chance;
    this.minY = minY;
    this.maxY = maxY;
    this.size = size;
    this.ore = ore;
    this.dimensions = dimensions;
    this.rule = test;
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

  public RuleTest getRule() {
    return rule;
  }

  public ResourceLocation getOreKey() {
    return ore.get().getId();
  }

  private Set<ResourceKey<Level>> storedDimension = null;

  public Set<ResourceKey<Level>> getDimensions() {
    if (storedDimension == null) {
      storedDimension = configDimensions.get().stream().map(o -> ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(o))).collect(Collectors.toSet());
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
    configDimensions = builder.comment("The dimensions that this ore should spawn in as a list (default [\"minecraft:overworld\"])").defineList("dimensions", dimensions.stream().map(ResourceKey::location).map(ResourceLocation::toString).collect(Collectors.toList()), (o) -> o instanceof String);
    builder.pop();
  }

  @Override
  public void reset() {
    storedDimension = null;
  }
}
