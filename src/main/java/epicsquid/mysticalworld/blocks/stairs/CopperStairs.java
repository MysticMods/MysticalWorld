package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class CopperStairs extends StairsBlock {
  public CopperStairs(Block.Properties properties) {
    super(() -> ModBlocks.COPPER_BLOCK.get().getDefaultState(), properties);
  }
}
