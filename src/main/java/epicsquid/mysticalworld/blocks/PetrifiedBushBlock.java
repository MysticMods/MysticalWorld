package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.Tags;

public class PetrifiedBushBlock extends DeadBushBlock {
  public PetrifiedBushBlock(AbstractBlock.Properties builder) {
    super(builder);
  }

  @Override
  protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
    Block block = state.getBlock();
    return block.isIn(Tags.Blocks.STONE) || block.isIn(Tags.Blocks.GRAVEL);
  }

  @Override
  public PlantType getPlantType(IBlockReader world, BlockPos pos) {
    return MysticalWorld.STONE_PLANT;
  }
}
