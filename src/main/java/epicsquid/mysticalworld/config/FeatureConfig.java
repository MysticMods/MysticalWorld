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

public abstract class FeatureConfig implements IConfig {
  // TODO: Caching

  protected double chance;
  protected List<BiomeDictionary.Type> biomes;
  protected List<BiomeDictionary.Type> biomeRestrictions;

  protected ForgeConfigSpec.DoubleValue configChance;
  protected ForgeConfigSpec.ConfigValue<String> configBiomes;
  protected ForgeConfigSpec.ConfigValue<String> configBiomeRestrictions;

  public FeatureConfig(double chance, List<BiomeDictionary.Type> biomeTypes, List<BiomeDictionary.Type> biomeRestrictions) {
    this.chance = chance;
    this.biomes = biomeTypes;
    this.biomeRestrictions = biomeRestrictions;
  }

  public double getChance() {
    return configChance.get();
  }

  private static Set<BiomeDictionary.Type> storedBiomes = null;
  private static Set<BiomeDictionary.Type> storedRestrictions = null;

  public Set<BiomeDictionary.Type> getBiomes() {
    if (storedBiomes == null) {
      storedBiomes = Stream.of(configBiomes.get().split(",")).map(o -> BiomeDictionary.Type.getType(o)).collect(Collectors.toSet());
    }

    return storedBiomes;
  }

  public Set<BiomeDictionary.Type> getBiomeRestrictions() {
    if (storedRestrictions == null) {
      storedRestrictions = Stream.of(configBiomeRestrictions.get().split(",")).map(o -> BiomeDictionary.Type.getType(o)).collect(Collectors.toSet());
    }

    return storedRestrictions;
  }

  @Override
  public abstract void apply(ForgeConfigSpec.Builder builder);
}
