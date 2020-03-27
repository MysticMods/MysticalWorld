package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class SoftStoneStairs extends StairsBlock {
  public SoftStoneStairs(Block.Properties properties) {
    super(() -> ModBlocks.SOFT_STONE.get().getDefaultState(), properties);
  }
}
