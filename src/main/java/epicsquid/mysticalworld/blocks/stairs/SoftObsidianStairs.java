package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class SoftObsidianStairs extends StairsBlock {
  public SoftObsidianStairs (Block.Properties properties) {
    super(() -> ModBlocks.SOFT_OBSIDIAN.get().getDefaultState(), properties);
  }
}
