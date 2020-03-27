package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.StairsBlock;

public class TerracottaBrickStairs extends StairsBlock {
  public TerracottaBrickStairs(Properties properties) {
    super(() -> ModBlocks.TERRACOTTA_BRICK.get().getDefaultState(), properties);
  }
}
