package epicsquid.mysticalworld.world;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.placement.IPlacementConfig;

import java.util.Arrays;
import java.util.List;

public class DimensionCountRangeConfig implements IPlacementConfig {
  public final List<RegistryKey<World>> dimensions;
  public final int count;
  public final int bottomOffset;
  public final int topOffset;
  public final int maximum;

  public DimensionCountRangeConfig(int count, int bottomOffset, int topOffset, int maximum, List<RegistryKey<World>> dimensions) {
    this.dimensions = dimensions;
    this.count = count;
    this.bottomOffset = bottomOffset;
    this.topOffset = topOffset;
    this.maximum = maximum;
  }

  @Override
  public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
    return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("dimensions"), ops.createList(dimensions.stream().map(o -> o.serialize(ops))), ops.createString("count"), ops.createInt(this.count), ops.createString("bottom_offset"), ops.createInt(this.bottomOffset), ops.createString("top_offset"), ops.createInt(this.topOffset), ops.createString("maximum"), ops.createInt(this.maximum))));
  }

  public static RegistryKey<World> getKey (Dynamic<?> name) {
    return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(name.toString()));
  }

  public static DimensionCountRangeConfig deserialize(Dynamic<?> input) {
    List<RegistryKey<World>> list = input.get("dimensions").asList(DimensionCountRangeConfig::getKey);
    int i = input.get("count").asInt(0);
    int j = input.get("bottom_offset").asInt(0);
    int k = input.get("top_offset").asInt(0);
    int l = input.get("maximum").asInt(0);
    return new DimensionCountRangeConfig(i, j, k, l, list);
  }
}