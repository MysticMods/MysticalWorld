package epicsquid.mysticalworld.config;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import noobanidus.libs.noobutil.block.BaseBlocks;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeConfig implements IConfig {
  // TODO: Caching

  private double chance;
  private List<BiomeDictionary.Type> biomes;

  private ForgeConfigSpec.DoubleValue configChance;
  private ForgeConfigSpec.ConfigValue<String> configBiomes;

  public TreeConfig(double chance, List<BiomeDictionary.Type> biomeTypes) {
    this.chance = chance;
    this.biomes = biomeTypes;
  }

  public double getChance() {
    return configChance.get();
  }

/*  public static Set<RegistryKey<World>> storedDimension = null;

  public Set<RegistryKey<World>> getDimensions() {
    if (storedDimension == null) {
      storedDimension = configDimensions.get().stream().map(o -> RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(o))).collect(Collectors.toSet());
    }

    return storedDimension;
  }*/

  private static Set<BiomeDictionary.Type> storedBiomes = null;

  public Set<BiomeDictionary.Type> getBiomes() {
    if (storedBiomes == null) {
      storedBiomes = Stream.of(configBiomes.get().split(",")).map(o -> BiomeDictionary.Type.getType(o)).collect(Collectors.toSet());
    }

    return storedBiomes;
  }

  @Override
  public void apply(ForgeConfigSpec.Builder builder) {
    builder.comment("Charred Tree Generation").push("charred_tree");
    configChance = builder.comment("Number of charred trees per chunk (set to 0 to disable).").defineInRange("oreChances", chance, 0, 256);
/*    configDimensions = builder.comment("The dimensions that these trees should spawn in as a list (default [\"minecraft:overworld\"], pass empty list for every dimension)").defineList("dimensions", dimensions.stream().map(RegistryKey::getLocation).map(ResourceLocation::toString).collect(Collectors.toList()), (o) -> true);*/
    StringBuilder sb = new StringBuilder();
    biomes.forEach(biome -> {
      sb.append(biome.getName());
      sb.append(",");
    });
    configBiomes = builder.comment("List of biome types to spawn (default [" + sb.toString() + "], pass empty list for every dimension").define("biomes", sb.toString());
    builder.pop();
  }
}
