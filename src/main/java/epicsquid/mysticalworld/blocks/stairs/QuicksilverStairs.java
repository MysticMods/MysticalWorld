package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class QuicksilverStairs extends StairsBlock {
  public QuicksilverStairs(Block.Properties properties) {
    super(() -> ModBlocks.QUICKSILVER_BLOCK.get().getDefaultState(), properties);
  }
}
