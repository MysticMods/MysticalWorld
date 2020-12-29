package epicsquid.mysticalworld.config;

import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeConfig extends FeatureConfig<TreeConfig> {
  private double chance;
  private ForgeConfigSpec.DoubleValue configChance;

  public TreeConfig(double chance, List<BiomeDictionary.Type> biomeTypes, List<BiomeDictionary.Type> biomeRestrictions) {
    super(biomeTypes, biomeRestrictions);
    this.chance = chance;
  }

  public double getChance() {
    return configChance.get();
  }

  @Override
  public boolean shouldSpawn() {
    return getChance() != 0;
  }

  @Override
  public GenerationStage.Decoration getStage() {
    return GenerationStage.Decoration.VEGETAL_DECORATION;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Charred Tree Generation").push("charred_tree");
    configChance = builder.comment("Number of charred trees per chunk (set to 0 to disable).").defineInRange("chance", chance, 0, 256);
    StringJoiner sb = new StringJoiner(",");
    biomes.forEach(o -> sb.add(o.getName()));
    configBiomes = builder.comment("List of biome types to spawn (default [" + sb.toString() + "], pass empty list for every biome").define("biomes", sb.toString());
    StringJoiner sb2 = new StringJoiner(",");
    biomeRestrictions.forEach(biome -> sb2.add(biome.getName()));
    configBiomeRestrictions = builder.comment("Which biome types this tree shouldn't spawn in (default END, NETHER)").define("biomeRestrictions", sb2.toString());
    builder.pop();
  }
}
