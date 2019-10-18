package epicsquid.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.util.BlockRenderLayer;

import javax.annotation.Nonnull;

public class ThatchBlock extends Block {

  public ThatchBlock(Properties props) {
    super(props);
  }

  @Nonnull
  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }
}