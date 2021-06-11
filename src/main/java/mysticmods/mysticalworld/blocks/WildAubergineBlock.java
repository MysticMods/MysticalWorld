package mysticmods.mysticalworld.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class WildAubergineBlock extends BushBlock {
  private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

  public WildAubergineBlock(AbstractBlock.Properties builder) {
    super(builder);
  }

  @SuppressWarnings("deprecation")
  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }

  @Override
  protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
  }

  @Override
  public boolean ticksRandomly(BlockState state) {
    return false;
  }

  @SuppressWarnings("deprecation")
  @Override
  public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
  }

  @Override
  public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
    BlockPos blockpos = pos.down();
    if (state.getBlock() == this) {
      return true;
    }
    return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
  }
}
