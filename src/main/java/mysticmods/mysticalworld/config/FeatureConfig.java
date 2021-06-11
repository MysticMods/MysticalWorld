package mysticmods.mysticalworld.config;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FeatureConfig<T extends FeatureConfig> extends AbstractConfig {
  // TODO: Caching

  protected List<BiomeDictionary.Type> biomes;
  protected List<BiomeDictionary.Type> biomeRestrictions;

  protected ForgeConfigSpec.ConfigValue<String> configBiomes;
  protected ForgeConfigSpec.ConfigValue<String> configBiomeRestrictions;

  protected Supplier<ConfiguredFeature<?, ?>> feature = null;
  protected Supplier<Supplier<ConfiguredFeature<?, ?>>> supplierFeature = null;
  protected Supplier<StructureFeature<?, ?>> structure = null;

  public FeatureConfig(List<BiomeDictionary.Type> biomeTypes, List<BiomeDictionary.Type> biomeRestrictions) {
    super();
    this.biomes = biomeTypes;
    this.biomeRestrictions = biomeRestrictions;
  }

  public T setFeature(ConfiguredFeature<?, ?> feature) {
    return setFeature(() -> feature);
  }

  public T setFeature(Supplier<ConfiguredFeature<?, ?>> feature) {
    this.feature = feature;
    return (T) this;
  }

  public T setSupplierFeature(Supplier<Supplier<ConfiguredFeature<?, ?>>> feature) {
    this.supplierFeature = feature;
    return (T) this;
  }

  public T setStructure(StructureFeature<?, ?> structure) {
    return setStructure(() -> structure);
  }

  public T setStructure(Supplier<StructureFeature<?, ?>> structure) {
    this.structure = structure;
    return (T) this;
  }

  @Nullable
  public Supplier<ConfiguredFeature<?, ?>> getFeature() {
    if (supplierFeature != null) {
      return supplierFeature.get();
    }
    return feature;
  }

  @Nullable
  public Supplier<StructureFeature<?, ?>> getStructure() {
    return structure;
  }

  public boolean isFeature() {
    return true;
  }

  public abstract boolean shouldSpawn();

  public abstract GenerationStage.Decoration getStage();

  private Set<BiomeDictionary.Type> storedBiomes = null;
  private Set<BiomeDictionary.Type> storedRestrictions = null;

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

  @Override
  public void reset() {
    storedBiomes = null;
    storedRestrictions = null;
  }
}
