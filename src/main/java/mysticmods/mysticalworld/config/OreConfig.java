package mysticmods.mysticalworld.config;

import com.tterrag.registrate.util.entry.RegistryEntry;
import mysticmods.mysticalworld.world.test.OreGenTest;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.block.BaseBlocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OreConfig extends AbstractConfig {

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

  private RuleTest rule;

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<RegistryKey<World>> dimensions, Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore) {
    this(name, chance, minY, maxY, size, dimensions, ore, OreGenTest.INSTANCE);
  }

  public OreConfig(String name, int chance, int minY, int maxY, int size, List<RegistryKey<World>> dimensions, Supplier<RegistryEntry<BaseBlocks.OreBlock>> ore, RuleTest test) {
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
    storedDimension = null;
  }
}
