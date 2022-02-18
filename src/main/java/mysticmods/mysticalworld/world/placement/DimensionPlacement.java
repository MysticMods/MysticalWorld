package mysticmods.mysticalworld.world.placement;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class DimensionPlacement extends FeatureDecorator<DimensionConfig> {
  public DimensionPlacement(Codec<DimensionConfig> codec) {
    super(codec);
  }

  public static DimensionPlacement create() {
    return new DimensionPlacement(DimensionConfig.CODEC);
  }

  @Override
  public Stream<BlockPos> getPositions(DecorationContext helper, Random rand, DimensionConfig config, BlockPos pos) {
    ServerLevel world = helper.level.getLevel();
    if (config.dimensions.contains(world.dimension())) {
      return Stream.of(pos);
    } else {
      return Stream.empty();
    }
  }
}
