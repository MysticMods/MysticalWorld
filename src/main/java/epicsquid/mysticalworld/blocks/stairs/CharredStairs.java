package epicsquid.mysticalworld.blocks.stairs;

import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class CharredStairs extends StairsBlock {
  public CharredStairs(Properties properties) {
    super(() -> ModBlocks.CHARRED_PLANKS.get().getDefaultState(), properties);
  }
}
