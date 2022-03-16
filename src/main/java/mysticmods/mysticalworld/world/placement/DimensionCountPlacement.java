package mysticmods.mysticalworld.world.placement;

/*public class DimensionCountPlacement extends FeatureDecorator<DimensionCountRangeConfig> {
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
  public Stream<BlockPos> getPositions(DecorationContext helper, Random rand, DimensionCountRangeConfig config, BlockPos pos) {
    ServerLevel world = helper.level.getLevel();
    if (config.dimensions.contains(world.dimension())) {
      return getPositions(rand, config, pos);
    } else {
      return Stream.empty();
    }
  }
}*/
