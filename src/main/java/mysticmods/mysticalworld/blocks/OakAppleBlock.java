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
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
  public static final DirectionProperty FACING = HorizontalBlock.FACING;

  private static final VoxelShape[] south_shapes = new VoxelShape[]{
      Block.box(7, 7, 15, 9, 9, 16),
      Block.box(7, 7, 14, 9, 9, 16),
      Block.box(6.5, 6.5, 14, 9.5, 9.5, 16),
      Block.box(6.5, 6.5, 13, 9.5, 9.5, 16)
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
    this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(AGE, 0));
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
    if (worldIn.isClientSide) {
      return;
    }

    if (!this.canBlockStay(worldIn, pos, state)) {
      worldIn.destroyBlock(pos, true);
    } else {
      int i = state.getValue(AGE);

      if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
        worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, worldIn.getBlockState(pos));
      }
    }
  }

  public boolean canBlockStay(World worldIn, BlockPos pos, BlockState state) {
    pos = pos.relative(state.getValue(FACING));
    BlockState iblockstate = worldIn.getBlockState(pos);
    Block block = iblockstate.getBlock();
    return block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD || block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD;
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context) {
    int i = state.getValue(AGE);

    switch (state.getValue(FACING)) {
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
    return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirrorIn) {
    return rotate(state, mirrorIn.getRotation(state.getValue(FACING)));
  }

  @Override
  protected boolean mayPlaceOn(BlockState pState, IBlockReader pLevel, BlockPos pPos) {
    return true;
  }

  @Override
  public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!this.canBlockStay(worldIn, pos, state)) {
      if (!worldIn.isClientSide) {
        worldIn.destroyBlock(pos, true);
      }
    }
  }

  @Override
  public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return state.getValue(AGE) < 3;
  }

  @Override
  public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
    return false;
  }

  @Override
  public void growCrops(World worldIn, BlockPos pos, BlockState state) {
    worldIn.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 3);
  }

  @Override
  protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING, AGE);
  }

  @Override
  public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    Direction enumfacing = Direction.fromYRot((double) placer.yRot);
    worldIn.setBlock(pos, state.setValue(FACING, enumfacing), 2);
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    Direction facing = context.getClickedFace();
    if (!facing.getAxis().isHorizontal()) {
      facing = Direction.NORTH;
    }

    return this.defaultBlockState().setValue(FACING, facing.getOpposite()).setValue(AGE, 0);
  }
}
