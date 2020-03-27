package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class LeadStairs extends StairsBlock {
  public LeadStairs(Block.Properties properties) {
    super(() -> ModBlocks.LEAD_BLOCK.get().getDefaultState(), properties);
  }
}
