package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.StairsBlock;

public class CharredStairs extends StairsBlock {
  public CharredStairs(Properties properties) {
    super(() -> ModBlocks.CHARRED_PLANKS.get().getDefaultState(), properties);
  }
}
