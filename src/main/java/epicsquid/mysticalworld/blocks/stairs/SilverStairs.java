package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class SilverStairs extends StairsBlock {
  public SilverStairs(Block.Properties properties) {
    super(() -> ModBlocks.SILVER_BLOCK.get().getDefaultState(), properties);
  }
}
