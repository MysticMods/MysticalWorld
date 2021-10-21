package mysticmods.mysticalworld.config;

import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.StringJoiner;

public class StructureConfig extends FeatureConfig<StructureConfig> {
  private final String name;

  public StructureConfig(String name, List<BiomeDictionary.Type> biomeTypes, List<BiomeDictionary.Type> biomeRestrictions) {
    super(biomeTypes, biomeRestrictions);
    this.name = name;
  }

  @Override
  public GenerationStage.Decoration getStage() {
    return null;
  }

  @Override
  public boolean isFeature() {
    return false;
  }

  @Override
  public boolean shouldSpawn() {
    return true;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Structure Generation").push(name);
    StringJoiner sb = new StringJoiner(",");
    biomes.forEach(o -> sb.add(o.getName()));
    configBiomes = builder.comment("List of biome types to spawn (default [" + sb.toString() + "], pass empty list for every biome").define("biomes", sb.toString());
    StringJoiner sb2 = new StringJoiner(",");
    biomeRestrictions.forEach(biome -> sb2.add(biome.getName()));
    configBiomeRestrictions = builder.comment("Which biome types this structure shouldn't spawn in (default END, NETHER)").define("biomeRestrictions", sb2.toString());
    builder.pop();
  }
}
