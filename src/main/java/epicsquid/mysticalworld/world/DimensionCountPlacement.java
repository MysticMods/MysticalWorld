package epicsquid.mysticalworld.world;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DimensionCountPlacement extends Placement<DimensionCountRangeConfig> {
  public DimensionCountPlacement(Codec<DimensionCountRangeConfig> codec) {
    super(codec);
  }

  // TODO: Dimensional blacklisting?
/*  public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, DimensionCountRangeConfig configIn, BlockPos pos) {
    if (configIn.dimensions.contains(worldIn.getDimension().getType()) && configIn.count > 0) {
      return getPositions(random, configIn, pos);
    }
    return Stream.empty();
  }*/

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
    return getPositions(rand, config, pos);
  }
}
