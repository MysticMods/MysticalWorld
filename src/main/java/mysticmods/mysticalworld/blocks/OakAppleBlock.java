package mysticmods.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import noobanidus.libs.noobutil.block.BaseBlocks;
import noobanidus.libs.noobutil.util.VoxelUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class OakAppleBlock extends BaseBlocks.CropsBlock {
  public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
  public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

  private static final VoxelShape[] south_shapes = new VoxelShape[]{
      Block.makeCuboidShape(7, 7, 15, 9, 9, 16),
      Block.makeCuboidShape(7, 7, 14, 9, 9, 16),
      Block.makeCuboidShape(6.5, 6.5, 14, 9.5, 9.5, 16),
      Block.makeCuboidShape(6.5, 6.5, 13, 9.5, 9.5, 16)
  };

  private static final VoxelShape[] north_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(south_shapes[0], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(south_shapes[1], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(south_shapes[2], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(south_shapes[3], Direction.SOUTH)
  };

  private static final VoxelShape[] west_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(south_shapes[0], Direction.EAST),
      VoxelUtil.rotateHorizontal(south_shapes[1], Direction.EAST),
      VoxelUtil.rotateHorizontal(south_shapes[2], Direction.EAST),
      VoxelUtil.rotateHorizontal(south_shapes[3], Direction.EAST)
  };

  private static final VoxelShape[] east_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(south_shapes[0], Direction.WEST),
      VoxelUtil.rotateHorizontal(south_shapes[1], Direction.WEST),
      VoxelUtil.rotateHorizontal(south_shapes[2], Direction.WEST),
      VoxelUtil.rotateHorizontal(south_shapes[3], Direction.WEST)
  };

  public OakAppleBlock(Block.Properties properties) {
    super(properties);
    this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
  }

  @Override
  public IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Override
  public int getMaxAge() {
    return 3;
  }

  @Override
  public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
    if (worldIn.isRemote) {
      return;
    }

    if (!this.canBlockStay(worldIn, pos, state)) {
      worldIn.destroyBlock(pos, true);
    } else {
      int i = state.get(AGE);

      if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
        worldIn.setBlockState(pos, state.with(AGE, i + 1), 2);
        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, worldIn.getBlockState(pos));
      }
    }
  }

  public boolean canBlockStay(World worldIn, BlockPos pos, BlockState state) {
    pos = pos.offset(state.get(FACING));
    BlockState iblockstate = worldIn.getBlockState(pos);
    Block block = iblockstate.getBlock();
    return block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD || block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD;
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context) {
    int i = state.get(AGE);

    switch (state.get(FACING)) {
      case SOUTH:
        return south_shapes[i];
      case NORTH:
      default:
        return north_shapes[i];
      case WEST:
        return west_shapes[i];
      case EAST:
        return east_shapes[i];
    }
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rot) {
    return state.with(FACING, rot.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return rotate(state, mirrorIn.toRotation(state.get(FACING)));
  }

  @Override
  protected boolean isValidGround(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
    return true;
  }

  @Override
  public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!this.canBlockStay(worldIn, pos, state)) {
      if (!worldIn.isRemote) {
        worldIn.destroyBlock(pos, true);
      }
    }
  }

  @Override
  public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return state.get(AGE) < 3;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
    return false;
  }

  @Override
  public void grow(World worldIn, BlockPos pos, BlockState state) {
    worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), 3);
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING, AGE);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    Direction enumfacing = Direction.fromAngle((double) placer.rotationYaw);
    worldIn.setBlockState(pos, state.with(FACING, enumfacing), 2);
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    Direction facing = context.getFace();
    if (!facing.getAxis().isHorizontal()) {
      facing = Direction.NORTH;
    }

    return this.getDefaultState().with(FACING, facing.getOpposite()).with(AGE, 0);
  }
}
