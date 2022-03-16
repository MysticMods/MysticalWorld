package mysticmods.mysticalworld.world.placement;

/*
public class DimensionConfig implements DecoratorConfiguration {
  public static final Codec<DimensionConfig> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
      ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions.stream().map(ResourceKey::location).collect(Collectors.toList()))).apply(codec, (r) -> new DimensionConfig(r.stream().map(o -> ResourceKey.create(Registry.DIMENSION_REGISTRY, o)).collect(Collectors.toSet()))));

  public final Set<ResourceKey<Level>> dimensions;

  public DimensionConfig(Set<ResourceKey<Level>> dimensions) {
    this.dimensions = dimensions;
  }
}*/
