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

public class WildCropBlock extends BushBlock {
  private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

  public WildCropBlock(AbstractBlock.Properties builder) {
    super(builder);
  }

  @SuppressWarnings("deprecation")
  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }

  @Override
  protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT) || state.is(Blocks.PODZOL) || state.is(Blocks.FARMLAND);
  }

  @Override
  public boolean isRandomlyTicking(BlockState state) {
    return false;
  }

  @SuppressWarnings("deprecation")
  @Override
  public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
  }

  @Override
  public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
    BlockPos blockpos = pos.below();
    if (state.getBlock() == this) {
      return true;
    }
    return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
  }

  @SuppressWarnings("deprecation")
  @Override
  public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
  }
}
