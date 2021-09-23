package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.placement.IPlacementConfig;

import java.util.Set;
import java.util.stream.Collectors;

public class DimensionConfig implements IPlacementConfig {
  public static final Codec<DimensionConfig> CODEC = RecordCodecBuilder.create((codec) -> codec.group(
      ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions.stream().map(RegistryKey::location).collect(Collectors.toList()))).apply(codec, (r) -> new DimensionConfig(r.stream().map(o -> RegistryKey.create(Registry.DIMENSION_REGISTRY, o)).collect(Collectors.toSet()))));

  public final Set<RegistryKey<World>> dimensions;

  public DimensionConfig(Set<RegistryKey<World>> dimensions) {
    this.dimensions = dimensions;
  }
}