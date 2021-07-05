package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DimensionPlacement extends Placement<DimensionConfig> {
  public DimensionPlacement(Codec<DimensionConfig> codec) {
    super(codec);
  }

  public static DimensionPlacement create() {
    return new DimensionPlacement(DimensionConfig.CODEC);
  }

  @Override
  public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, DimensionConfig config, BlockPos pos) {
    ServerWorld world = helper.field_242889_a.getWorld();
    if (config.dimensions.contains(world.getDimensionKey())) {
      return Stream.of(pos);
    } else {
      return Stream.empty();
    }
  }
}
