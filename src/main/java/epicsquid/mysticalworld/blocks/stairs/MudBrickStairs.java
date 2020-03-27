package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class MudBrickStairs extends StairsBlock {
  public MudBrickStairs(Properties properties) {
    super(() -> ModBlocks.MUD_BRICK.get().getDefaultState(), properties);
  }
}
