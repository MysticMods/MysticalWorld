package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.StairsBlock;

public class MudBlockStairs extends StairsBlock {
  public MudBlockStairs(Properties properties) {
    super(() -> ModBlocks.MUD_BLOCK.get().getDefaultState(), properties);
  }
}
