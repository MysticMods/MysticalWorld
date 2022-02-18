package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;

import java.util.Set;
import java.util.stream.Collectors;

public class DimensionCountRangeConfig implements DecoratorConfiguration {
  public static final Codec<DimensionCountRangeConfig> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
      Codec.INT.fieldOf("count").forGetter((config) -> config.count),
      Codec.INT.fieldOf("bottomOffset").forGetter((config) -> config.bottomOffset),
      Codec.INT.fieldOf("topOffset").forGetter((config) -> config.topOffset),
      Codec.INT.fieldOf("maximum").forGetter((config) -> config.maximum),
      ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions.stream().map(ResourceKey::location).collect(Collectors.toList()))).apply(codec, (c, b, t, m, r) -> new DimensionCountRangeConfig(c, b, t, m, r.stream().map(o -> ResourceKey.create(Registry.DIMENSION_REGISTRY, o)).collect(Collectors.toSet()))));

  public final Set<ResourceKey<Level>> dimensions;
  public final int count;
  public final int bottomOffset;
  public final int topOffset;
  public final int maximum;

  public DimensionCountRangeConfig(int count, int bottomOffset, int topOffset, int maximum, Set<ResourceKey<Level>> dimensions) {
    this.dimensions = dimensions;
    this.count = count;
    this.bottomOffset = bottomOffset;
    this.topOffset = topOffset;
    this.maximum = maximum;
  }
}