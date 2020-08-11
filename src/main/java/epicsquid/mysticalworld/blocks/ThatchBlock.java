package epicsquid.mysticalworld.blocks;

import noobanidus.libs.noobutil.block.WaterloggedBlock;

public class ThatchBlock extends WaterloggedBlock {
  public ThatchBlock(Properties props) {
    super(props);
    this.setDefaultState(this.getStateContainer().getBaseState().with(WATERLOGGED, false));
  }

  // TODO: Move this to client setup
/*  @Nonnull
  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }*/
}