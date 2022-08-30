package mysticmods.mysticalworld.config;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FeatureConfig extends AbstractConfig {
  protected List<TagKey<Biome>> biomes;
  protected List<TagKey<Biome>> biomeRestrictions;

  protected ForgeConfigSpec.ConfigValue<String> configBiomes;
  protected ForgeConfigSpec.ConfigValue<String> configBiomeRestrictions;

  protected Supplier<ConfiguredStructureFeature<?, ?>> structure = null;

  public FeatureConfig(List<TagKey<Biome>> biomeTypes, List<TagKey<Biome>> biomeRestrictions) {
    super();
    this.biomes = biomeTypes;
    this.biomeRestrictions = biomeRestrictions;
  }

  public boolean isFeature() {
    return true;
  }

  public abstract GenerationStep.Decoration getStage();

  private Set<TagKey<Biome>> storedBiomes = null;
  private Set<TagKey<Biome>> storedRestrictions = null;

  public abstract boolean shouldSpawn();

  public Set<TagKey<Biome>> getBiomes() {
    if (storedBiomes == null) {
      storedBiomes = Stream.of(configBiomes.get().split(",")).map(TagKey<Biome>::getType).collect(Collectors.toSet());
    }

    return storedBiomes;
  }

  public Set<TagKey<Biome>> getBiomeRestrictions() {
    if (storedRestrictions == null) {
      storedRestrictions = Stream.of(configBiomeRestrictions.get().split(",")).map(TagKey<Biome>::getType).collect(Collectors.toSet());
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
