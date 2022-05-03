package mysticmods.mysticalworld.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import noobanidus.libs.noobutil.util.VoxelUtil;

import javax.annotation.Nullable;

// TODO: Adjust models where they exceed 16x16 voxels
public class BonesBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
  protected static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

  private static final VoxelShape bone_pile_south_shape = Block.box(4, 0, 4, 12, 13, 12);
  private static final VoxelShape bone_pile_north_shape = VoxelUtil.rotateHorizontal(bone_pile_south_shape, Direction.SOUTH);
  private static final VoxelShape bone_pile_west_shape = VoxelUtil.rotateHorizontal(bone_pile_south_shape, Direction.EAST);
  private static final VoxelShape bone_pile_east_shape = VoxelUtil.rotateHorizontal(bone_pile_south_shape, Direction.WEST);

  private static final VoxelShape skeleton_south_shape = Block.box(1, 0, 0, 15, 8, 16);
  private static final VoxelShape skeleton_north_shape = VoxelUtil.rotateHorizontal(skeleton_south_shape, Direction.SOUTH);
  private static final VoxelShape skeleton_west_shape = VoxelUtil.rotateHorizontal(skeleton_south_shape, Direction.EAST);
  private static final VoxelShape skeleton_east_shapes = VoxelUtil.rotateHorizontal(skeleton_south_shape, Direction.WEST);

  private final BoneType type;

  public BonesBlock(Properties p_i48377_1_, BoneType type) {
    super(p_i48377_1_);
    this.type = type;
    this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
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
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    super.createBlockStateDefinition(builder);
    builder.add(WATERLOGGED);
    builder.add(FACING);
  }

  @Override
  public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    Direction facing = pState.getValue(FACING);
    VoxelShape shape = null;
    switch (type) {
      case BOTTOM:
      case TOP:
        switch (facing) {
          case EAST:
            shape = skeleton_east_shapes;
            break;
          case WEST:
            shape = skeleton_west_shape;
            break;
          case SOUTH:
            shape = skeleton_south_shape;
            break;
          case NORTH:
            shape = skeleton_north_shape;
            break;
        }
        break;
      case PILE:
        switch (facing) {
          case EAST:
            shape = bone_pile_east_shape;
            break;
          case WEST:
            shape = bone_pile_west_shape;
            break;
          case SOUTH:
            shape = bone_pile_south_shape;
            break;
          case NORTH:
            shape = bone_pile_north_shape;
            break;
        }
        break;
      default:
        shape = bone_pile_south_shape;
    }
    if (shape == null) {
      return bone_pile_south_shape;
    }
    return shape;
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    BlockState state = this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
    if (fluidState.getType() == Fluids.WATER) {
      state = state.setValue(WATERLOGGED, true);
    }

    return state;
  }

  @Override
  public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
    if (stateIn.getValue(WATERLOGGED)) {
      worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
    }

    return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
  }

  @Override
  public FluidState getFluidState(BlockState state) {
    return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
  }

  public enum BoneType {
    PILE, TOP, BOTTOM
  }
}
