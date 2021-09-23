package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DimensionCountPlacement extends Placement<DimensionCountRangeConfig> {
  public DimensionCountPlacement(Codec<DimensionCountRangeConfig> codec) {
    super(codec);
  }

  public static DimensionCountPlacement create() {
    return new DimensionCountPlacement(DimensionCountRangeConfig.CODEC);
  }

  protected Stream<BlockPos> getPositions(Random random, DimensionCountRangeConfig p_212852_2_, BlockPos pos) {
    return IntStream.range(0, p_212852_2_.count).mapToObj((p_227453_3_) -> {
      int i = random.nextInt(16) + pos.getX();
      int j = random.nextInt(16) + pos.getZ();
      int k = random.nextInt(p_212852_2_.maximum - p_212852_2_.topOffset) + p_212852_2_.bottomOffset;
      return new BlockPos(i, k, j);
    });
  }

  @Override
  public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, DimensionCountRangeConfig config, BlockPos pos) {
    ServerWorld world = helper.level.getLevel();
    if (config.dimensions.contains(world.dimension())) {
      return getPositions(rand, config, pos);
    } else {
      return Stream.empty();
    }
  }
}
