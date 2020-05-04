package epicsquid.mysticalworld.blocks;

import epicsquid.mysticallib.block.AbstractWaterloggedBlock;

public class ThatchBlock extends AbstractWaterloggedBlock {
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