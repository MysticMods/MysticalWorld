package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.Set;
import java.util.stream.Collectors;

/*
public class DimensionConfig implements DecoratorConfiguration {
  public static final Codec<DimensionConfig> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
      ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions.stream().map(ResourceKey::location).collect(Collectors.toList()))).apply(codec, (r) -> new DimensionConfig(r.stream().map(o -> ResourceKey.create(Registry.DIMENSION_REGISTRY, o)).collect(Collectors.toSet()))));

  public final Set<ResourceKey<Level>> dimensions;

  public DimensionConfig(Set<ResourceKey<Level>> dimensions) {
    this.dimensions = dimensions;
  }
}*/
