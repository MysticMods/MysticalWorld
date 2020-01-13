package epicsquid.mysticalworld.blocks;

import epicsquid.mysticallib.block.AbstractWaterloggedBlock;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

public class ThatchBlock extends AbstractWaterloggedBlock {
  public ThatchBlock(Properties props) {
    super(props);
    this.setDefaultState(this.getStateContainer().getBaseState().with(WATERLOGGED, false));
  }

  @Nonnull
  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }
}