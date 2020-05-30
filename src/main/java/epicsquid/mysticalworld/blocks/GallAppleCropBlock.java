package epicsquid.mysticalworld.blocks;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import noobanidus.libs.noobutil.util.VoxelUtil;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "NullableProblems"})
public class GallAppleCropBlock extends HorizontalBlock implements IGrowable {
  public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

  public static List<VoxelShape> SOUTH = Arrays.asList(Block.makeCuboidShape(7, 7, 15,9, 9, 16), Block.makeCuboidShape(7, 7, 14,9, 9, 16), Block.makeCuboidShape(6.5, 6.5, 14,9.5, 9.5, 16), Block.makeCuboidShape(6.5, 6.5, 13,9.5, 9.5, 16));

  public static List<VoxelShape> NORTH = SOUTH.stream().map(o -> VoxelUtil.rotate(o, Rotation.CLOCKWISE_180)).collect(Collectors.toList());

  public static List<VoxelShape> EAST = NORTH.stream().map(o -> VoxelUtil.rotate(o, Rotation.CLOCKWISE_90)).collect(Collectors.toList());

  public static List<VoxelShape> WEST = NORTH.stream().map(o -> VoxelUtil.rotate(o, Rotation.COUNTERCLOCKWISE_90)).collect(Collectors.toList());

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
        return SOUTH.get(i);
      case NORTH:
      default:
        return NORTH.get(i);
      case WEST:
        return WEST.get(i);
      case EAST:
        return EAST.get(i);
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
