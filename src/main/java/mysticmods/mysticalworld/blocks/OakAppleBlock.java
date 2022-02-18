package mysticmods.mysticalworld.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import noobanidus.libs.noobutil.block.BaseBlocks;
import noobanidus.libs.noobutil.util.VoxelUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class OakAppleBlock extends BaseBlocks.CropsBlock {
  public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
  public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

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
  public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
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

  public boolean canBlockStay(Level worldIn, BlockPos pos, BlockState state) {
    pos = pos.relative(state.getValue(FACING));
    BlockState iblockstate = worldIn.getBlockState(pos);
    Block block = iblockstate.getBlock();
    return block == Blocks.OAK_LOG || block == Blocks.OAK_WOOD || block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD;
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter source, BlockPos pos, CollisionContext context) {
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
  protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
    return true;
  }

  @Override
  public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    if (!this.canBlockStay(worldIn, pos, state)) {
      if (!worldIn.isClientSide) {
        worldIn.destroyBlock(pos, true);
      }
    }
  }

  @Override
  public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return state.getValue(AGE) < 3;
  }

  @Override
  public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
    return false;
  }

  @Override
  public void growCrops(Level worldIn, BlockPos pos, BlockState state) {
    worldIn.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 3);
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING, AGE);
  }

  @Override
  public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
    Direction enumfacing = Direction.fromYRot((double) placer.yRot);
    worldIn.setBlock(pos, state.setValue(FACING, enumfacing), 2);
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    Direction facing = context.getClickedFace();
    if (!facing.getAxis().isHorizontal()) {
      facing = Direction.NORTH;
    }

    return this.defaultBlockState().setValue(FACING, facing.getOpposite()).setValue(AGE, 0);
  }
}
