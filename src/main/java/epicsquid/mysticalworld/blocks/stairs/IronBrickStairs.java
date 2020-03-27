package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class IronBrickStairs extends StairsBlock {
  public IronBrickStairs(Block.Properties props) {
    super(() -> ModBlocks.IRON_BRICK.get().getDefaultState(), props);
  }
}
