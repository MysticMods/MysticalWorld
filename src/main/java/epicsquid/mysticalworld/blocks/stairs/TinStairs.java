package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class TinStairs extends StairsBlock {
  public TinStairs(Block.Properties properties) {
    super(() -> ModBlocks.TIN_BLOCK.get().getDefaultState(), properties);
  }
}
