package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class AmethystStairs extends StairsBlock {
  public AmethystStairs(Block.Properties properties) {
    super(() -> ModBlocks.AMETHYST_BLOCK.get().getDefaultState(), properties);
  }
}
