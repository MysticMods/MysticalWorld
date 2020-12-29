package epicsquid.mysticalworld.config;

import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.StringJoiner;

public class StonepetalConfig extends FeatureConfig<StonepetalConfig> {
  private final int repeats;
  private final int tries;
  private ForgeConfigSpec.IntValue configRepeats;
  private ForgeConfigSpec.IntValue configTries;

  public StonepetalConfig(int repeats, int tries, List<BiomeDictionary.Type> biomeTypes, List<BiomeDictionary.Type> biomeRestrictions) {
    super(biomeTypes, biomeRestrictions);
    this.tries = tries;
    this.repeats = repeats;
  }

  public int getRepeats() {
    return configRepeats.get();
  }

  public int getTries() {
    return configTries.get();
  }

  @Override
  public GenerationStage.Decoration getStage() {
    return GenerationStage.Decoration.VEGETAL_DECORATION;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Stonepetal Patch Generation").push("stone_petal");
    configTries = builder.comment("Number of tries per chunk to try placing stonepetals on stone (set to 0 to disable).").defineInRange("tries", tries, 0, 256);
    configRepeats = builder.comment("Number of times per chunk to repeat trying to place stonepetals on stone").defineInRange("repeats", repeats, 0, 256);
    StringJoiner sb = new StringJoiner(",");
    biomes.forEach(o -> sb.add(o.getName()));
    configBiomes = builder.comment("List of biome types to spawn (default [" + sb.toString() + "], pass empty list for every biome").define("biomes", sb.toString());
    StringJoiner sb2 = new StringJoiner(",");
    biomeRestrictions.forEach(biome -> sb2.add(biome.getName()));
    configBiomeRestrictions = builder.comment("Which biome types this tree shouldn't spawn in (default END, NETHER)").define("biomeRestrictions", sb2.toString());
    builder.pop();
  }
}
