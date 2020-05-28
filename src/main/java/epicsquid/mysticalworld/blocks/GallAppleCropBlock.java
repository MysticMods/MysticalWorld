package epicsquid.mysticalworld.blocks;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class GallAppleCropBlock extends HorizontalBlock implements IGrowable {
  public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

  protected static final VoxelShape[] NORTH_AABB = new VoxelShape[]{
      Block.makeCuboidShape(0.4375, 0.4375, 0, 0.5625, 0.5625, 0.0625),
      Block.makeCuboidShape(0.4375, 0.4375, 0, 0.5625, 0.5625, 0.125),
      Block.makeCuboidShape(0.40625, 0.40625, 0, 0.59375, 0.59375, 0.125),
      Block.makeCuboidShape(0.40625, 0.40625, 0, 0.59375, 0.59375, 0.1875)
  };
  protected static final VoxelShape[] SOUTH_AABB = new VoxelShape[]{
      Block.makeCuboidShape(0.4375, 0.4375, 0.9375, 0.5625, 0.5625, 1),
      Block.makeCuboidShape(0.4375, 0.4375, 0.875, 0.5625, 0.5625, 1),
      Block.makeCuboidShape(0.40625, 0.40625, 0.875, 0.59375, 0.59375, 1),
      Block.makeCuboidShape(0.40625, 0.40625, 0.8125, 0.59375, 0.59375, 1)
  };
  protected static final VoxelShape[] WEST_AABB = new VoxelShape[]{
      Block.makeCuboidShape(0, 0.4375, 0.4375, 0.0625, 0.5625, 0.5625),
      Block.makeCuboidShape(0, 0.4375, 0.4375, 0.125, 0.5625, 0.5625),
      Block.makeCuboidShape(0, 0.40625, 0.40625, 0.125, 0.59375, 0.59375),
      Block.makeCuboidShape(0, 0.40625, 0.40625, 0.1875, 0.59375, 0.59375)
  };
  protected static final VoxelShape[] EAST_AABB = new VoxelShape[]{
      Block.makeCuboidShape(0.9375, 0.4375, 0.4375, 1, 0.5625, 0.5625),
      Block.makeCuboidShape(0.875, 0.4375, 0.4375, 1, 0.5625, 0.5625),
      Block.makeCuboidShape(0.875, 0.40625, 0.40625, 1, 0.59375, 0.59375),
      Block.makeCuboidShape(0.8125, 0.40625, 0.40625, 1, 0.59375, 0.59375)
  };

  public GallAppleCropBlock(Properties builder) {
    super(builder);
    this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(AGE, 0));
  }

  @Override
  public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
    int i = state.get(AGE);
    if (i <= 2 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, worldIn.rand.nextInt(5) == 0)) {
      worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
      net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
    }
  }

  @Override
  public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
    Block block = worldIn.getBlockState(pos.offset(state.get(HORIZONTAL_FACING))).getBlock();
    return block.isIn(BlockTags.OAK_LOGS) || block.isIn(BlockTags.DARK_OAK_LOGS);
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    int i = state.get(AGE);
    switch (state.get(HORIZONTAL_FACING)) {
      case SOUTH:
        return SOUTH_AABB[i];
      case NORTH:
      default:
        return NORTH_AABB[i];
      case WEST:
        return WEST_AABB[i];
      case EAST:
        return EAST_AABB[i];
    }
  }

  @Override
  @Nullable
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    BlockState blockstate = this.getDefaultState();
    IWorldReader iworldreader = context.getWorld();
    BlockPos blockpos = context.getPos();

    for (Direction direction : context.getNearestLookingDirections()) {
      if (direction.getAxis().isHorizontal()) {
        blockstate = blockstate.with(HORIZONTAL_FACING, direction);
        if (blockstate.isValidPosition(iworldreader, blockpos)) {
          return blockstate;
        }
      }
    }

    return null;
  }

  @Override
  public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    return facing == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  @Override
  public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return state.get(AGE) <= 2;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
    return true;
  }

  @Override
  public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
    worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), 2);
  }

  @Override
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(HORIZONTAL_FACING, AGE);
  }
}
