package mysticmods.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import noobanidus.libs.noobutil.util.VoxelUtil;

import javax.annotation.Nullable;

public class BonesBlock extends HorizontalBlock {
  // TODO: Just make these into blocks
  private static final VoxelShape[] bone_pile_south_shapes = new VoxelShape[]{
      VoxelUtil.multiOr(Block.box(9, 0, 5, 11, 12, 7), Block.box(5, 0, 5, 7, 12, 7), Block.box(4, 0, 4, 12, 7, 5), Block.box(4, 0, 5, 12, 1, 12), Block.box(6, 7, 4, 10, 8, 5), Block.box(6, 5, 11, 10, 6, 12), Block.box(5, 4, 11, 11, 5, 12), Block.box(5, 1, 11, 11, 2, 12), Block.box(7, 2, 11, 9, 4, 12), Block.box(4, 1, 5, 5, 4, 12), Block.box(11, 1, 5, 12, 4, 12), Block.box(7, 4, 10, 9, 9, 11), Block.box(9, 8, 10, 12, 9, 11), Block.box(9, 8, 7, 11, 9, 8), Block.box(5, 8, 7, 7, 9, 8), Block.box(10, 6, 7, 11, 7, 8), Block.box(5, 6, 7, 6, 7, 8), Block.box(11, 8, 7, 12, 9, 10), Block.box(4, 8, 7, 5, 9, 10), Block.box(11, 6, 7, 12, 7, 10), Block.box(4, 6, 7, 5, 7, 10), Block.box(11, 4, 8, 12, 5, 10), Block.box(4, 4, 8, 5, 5, 10), Block.box(9, 6, 10, 12, 7, 11), Block.box(9, 4, 10, 12, 5, 11), Block.box(4, 8, 10, 7, 9, 11), Block.box(4, 4, 10, 7, 5, 11), Block.box(4, 6, 10, 7, 7, 11), Block.box(12, 9, 7, 14, 10, 9)),
      VoxelUtil.multiOr(Block.box(-2, 0, 8, 10, 2, 10), Block.box(6, 0, 2, 8, 2, 14), Block.box(10, 2, 2, 12, 4, 14), Block.box(10, 0, 0, 12, 2, 12), Block.box(3, 0, 4, 5, 1, 6)),
      VoxelUtil.multiOr(Block.box(3, 0, 2, 5, 2, 14), Block.box(3, 7, 6, 4, 8, 7), Block.box(6, 4, 14, 7, 7, 15), Block.box(2, 5, 6, 3, 8, 7), Block.box(6, 3, 14, 9, 4, 15), Block.box(6, 0, 4, 9, 1, 5), Block.box(2, 4, 6, 5, 5, 7), Block.box(10, 0, 1, 12, 2, 5), Block.box(11, 0, 8, 12, 1, 9), Block.box(13, 0, 9, 14, 1, 10), Block.box(13, 0, 14, 14, 1, 15), Block.box(11, 1, 8, 12, 2, 9), Block.box(10, 0, 5, 11, 1, 7), Block.box(11, 0, 5, 12, 1, 6), Block.box(11, 0, 9, 13, 2, 13)),
      VoxelUtil.multiOr(Block.box(16, 0, 1, 18, 2, 13), Block.box(1, -2, 13, 9, 5, 14), Block.box(1, 4, 6, 9, 5, 13), Block.box(3, -3, 13, 7, -2, 14), Block.box(4, 0, 0, 10, 1, 1), Block.box(9, 0, 1, 10, 1, 4), Block.box(3, 0, 2, 5, 1, 4), Block.box(9, 1, 3, 10, 3, 5), Block.box(5, 1, 0, 9, 2, 1), Block.box(3, -1, 6, 7, 0, 7), Block.box(2, 0, 6, 8, 1, 7), Block.box(2, 3, 6, 8, 4, 7), Block.box(4, 1, 6, 6, 3, 7), Block.box(1, 1, 6, 2, 4, 13), Block.box(8, 1, 6, 9, 4, 13))
  };

  private static final VoxelShape[] bone_pile_north_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[0], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[1], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[2], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[3], Direction.SOUTH)
  };

  private static final VoxelShape[] bone_pile_west_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[0], Direction.EAST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[1], Direction.EAST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[2], Direction.EAST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[3], Direction.EAST)
  };

  private static final VoxelShape[] bone_pile_east_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[0], Direction.WEST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[1], Direction.WEST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[2], Direction.WEST),
      VoxelUtil.rotateHorizontal(bone_pile_south_shapes[3], Direction.WEST)
  };

  private static final VoxelShape[] skeleton_top_south_shapes = new VoxelShape[]{
      VoxelUtil.multiOr(Block.box(1, 0, -3, 3, 2, 9), Block.box(13, 0, -3, 15, 2, 9), Block.box(4, 0, 9, 12, 1, 16), Block.box(4, 1, 15, 12, 8, 16), Block.box(6, 0, 8, 10, 1, 9), Block.box(5, 5, 8, 11, 6, 9), Block.box(5, 2, 8, 6, 5, 9), Block.box(10, 2, 8, 11, 5, 9), Block.box(5, 1, 9, 6, 3, 11), Block.box(10, 1, 9, 11, 3, 11), Block.box(6, 5, 9, 10, 6, 10), Block.box(6, 7, 10, 10, 8, 11), Block.box(5, 7, 11, 11, 8, 12), Block.box(5, 7, 14, 11, 8, 15), Block.box(7, 7, 12, 9, 8, 14), Block.box(4, 1, 12, 5, 8, 15), Block.box(11, 1, 12, 12, 8, 15), Block.box(7, 0, 2, 9, 1, 7), Block.box(9, 0, 6, 12, 1, 7), Block.box(9, 3, 6, 11, 4, 7), Block.box(5, 3, 6, 7, 4, 7), Block.box(10, 3, 4, 11, 4, 5), Block.box(5, 3, 4, 6, 4, 5), Block.box(11, 1, 6, 12, 4, 7), Block.box(4, 1, 6, 5, 4, 7), Block.box(11, 1, 4, 12, 4, 5), Block.box(4, 1, 4, 5, 4, 5), Block.box(11, 1, 2, 12, 3, 3), Block.box(4, 1, 2, 5, 3, 3), Block.box(9, 0, 4, 12, 1, 5), Block.box(9, 0, 2, 12, 1, 3), Block.box(4, 0, 6, 7, 1, 7), Block.box(4, 0, 2, 7, 1, 3), Block.box(4, 0, 4, 7, 1, 5), Block.box(7, 0, -1, 9, 1, 1)),
      VoxelUtil.multiOr(Block.box(1, 0, -3, 3, 2, 9), Block.box(13, 0, -4, 15, 2, 8), Block.box(4, 0, 9, 12, 1, 16), Block.box(4, 1, 15, 12, 8, 16), Block.box(6, 0, 8, 10, 1, 9), Block.box(5, 5, 6, 11, 6, 7), Block.box(5, 2, 6, 6, 5, 7), Block.box(10, 2, 6, 11, 5, 7), Block.box(5, 1, 7, 6, 3, 9), Block.box(10, 1, 7, 11, 3, 9), Block.box(6, 5, 7, 10, 6, 8), Block.box(6, 7, 10, 10, 8, 11), Block.box(5, 7, 11, 11, 8, 12), Block.box(5, 7, 14, 11, 8, 15), Block.box(7, 7, 12, 9, 8, 14), Block.box(4, 1, 12, 5, 8, 15), Block.box(11, 1, 12, 12, 8, 15), Block.box(7, 0, 2, 9, 1, 7), Block.box(9, 0, 6, 12, 1, 7), Block.box(5, 3, 6, 6, 4, 7), Block.box(10, 0, 0, 11, 1, 1), Block.box(11, 1, 6, 12, 4, 7), Block.box(4, 1, 6, 5, 4, 7), Block.box(11, 0, 0, 12, 1, 3), Block.box(2, 1, 2, 3, 3, 3), Block.box(9, 0, 4, 12, 1, 5), Block.box(4, 0, 6, 7, 1, 7), Block.box(2, 0, 2, 5, 1, 3), Block.box(4, 0, 4, 7, 1, 5), Block.box(7, 0, -1, 9, 1, 1)),
      VoxelUtil.multiOr(Block.box(1, 0, -3, 3, 2, 9), Block.box(4, 0, 9, 12, 1, 16), Block.box(4, 1, 15, 12, 8, 16), Block.box(6, 0, 8, 10, 1, 9), Block.box(5, 2, 8, 6, 5, 9), Block.box(10, 2, 8, 11, 5, 9), Block.box(5, 1, 9, 6, 3, 11), Block.box(4, 1, 13, 5, 3, 15), Block.box(10, 1, 9, 11, 3, 11), Block.box(6, 1, 10, 10, 2, 11), Block.box(5, 1, 11, 11, 2, 12), Block.box(7, 1, 12, 9, 2, 14), Block.box(11, 1, 12, 12, 8, 15), Block.box(7, 0, 2, 9, 1, 7), Block.box(9, 0, 6, 12, 1, 7), Block.box(9, 3, 6, 11, 4, 7), Block.box(9, 7, 14, 11, 8, 15), Block.box(9, 5, 8, 11, 6, 9), Block.box(6, 1, 8, 8, 2, 9), Block.box(10, 1, 4, 11, 2, 5), Block.box(9, 5, 9, 10, 6, 10), Block.box(7, 1, 9, 8, 2, 10), Block.box(5, 3, 4, 6, 4, 5), Block.box(11, 1, 6, 12, 4, 7), Block.box(4, 1, 6, 5, 4, 7), Block.box(4, 3, 14, 5, 6, 15), Block.box(4, 1, 4, 5, 4, 5), Block.box(4, 1, 12, 5, 4, 13), Block.box(11, 1, 2, 12, 3, 3), Block.box(9, 0, 4, 12, 1, 5), Block.box(9, 0, 2, 12, 1, 3), Block.box(4, 0, 6, 7, 1, 7), Block.box(4, 0, 2, 7, 1, 3), Block.box(4, 0, 4, 7, 1, 5), Block.box(7, 0, 0, 9, 1, 2), Block.box(13, 0, -2, 15, 2, 2), Block.box(13, 0, 3, 14, 1, 4), Block.box(7, -2, 4, 8, -1, 5), Block.box(16, 0, 11, 17, 1, 12), Block.box(14, 1, 5, 15, 2, 6), Block.box(13, 0, 2, 14, 1, 4), Block.box(14, 0, 2, 15, 1, 3), Block.box(13, 0, 4, 15, 2, 8)),
      VoxelUtil.multiOr(Block.box(1, 0, -3, 3, 2, 9), Block.box(4, 0, 9, 12, 1, 16), Block.box(4, 1, 15, 12, 8, 16), Block.box(6, 0, 8, 10, 1, 9), Block.box(7, 1, 11, 11, 2, 12), Block.box(5, 7, 14, 9, 8, 15), Block.box(7, 7, 12, 8, 8, 14), Block.box(4, 1, 12, 5, 8, 15), Block.box(11, 1, 12, 12, 6, 15), Block.box(7, 0, 2, 9, 1, 7), Block.box(9, 0, 6, 12, 1, 7), Block.box(11, 0, 2, 12, 2, 3), Block.box(4, 0, 2, 7, 1, 3), Block.box(4, 0, 4, 7, 1, 5), Block.box(7, 0, -1, 9, 1, 1))
  };

  private static final VoxelShape[] skeleton_top_north_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[0], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[1], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[2], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[3], Direction.SOUTH)
  };

  private static final VoxelShape[] skeleton_top_west_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[0], Direction.EAST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[1], Direction.EAST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[2], Direction.EAST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[3], Direction.EAST)
  };

  private static final VoxelShape[] skeleton_top_east_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[0], Direction.WEST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[1], Direction.WEST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[2], Direction.WEST),
      VoxelUtil.rotateHorizontal(skeleton_top_south_shapes[3], Direction.WEST)
  };

  private static final VoxelShape[] skeleton_bottom_south_shapes = new VoxelShape[]{
      VoxelUtil.multiOr(Block.box(5, 0, 0,7, 2, 12), Block.box(9, 0, 0,11, 2, 12), Block.box(4, 0, 13,12, 1, 14), Block.box(4, 0, 15,6, 1, 16), Block.box(4, 1, 13,5, 3, 16), Block.box(4, 3, 13,6, 4, 16), Block.box(10, 3, 13,12, 4, 16), Block.box(11, 1, 13,12, 3, 16), Block.box(4, 0, 14,7, 1, 15), Block.box(6, 3, 13,7, 4, 14), Block.box(9, 3, 13,10, 4, 14), Block.box(9, 0, 14,12, 1, 15), Block.box(10, 0, 15,12, 1, 16)),
      VoxelUtil.multiOr(Block.box(5, 0, -1,7, 2, 11), Block.box(4, 0, 15,6, 1, 16), Block.box(4, 1, 13,5, 3, 16), Block.box(4, 3, 13,6, 4, 16), Block.box(10, 0, 12,12, 1, 15), Block.box(4, 0, 14,7, 1, 15), Block.box(6, 0, 13,7, 1, 14), Block.box(8, 1, 12,9, 2, 13), Block.box(11, 1, 13,12, 2, 15), Block.box(9, 0, 0,11, 2, 4), Block.box(9, 0, 5,10, 1, 6), Block.box(3, -2, 6,4, -1, 7), Block.box(12, 0, 13,13, 1, 14), Block.box(10, 1, 7,11, 2, 8), Block.box(9, 0, 4,10, 1, 6), Block.box(10, 0, 4,11, 1, 5), Block.box(9, 0, 6,11, 2, 10)),
      VoxelUtil.multiOr(Block.box(6, 0, 2,8, 2, 14), Block.box(9, 1, 3,11, 3, 15), Block.box(4, 0, 13,12, 1, 14), Block.box(4, 0, 15,6, 1, 16), Block.box(4, 1, 13,5, 3, 16), Block.box(4, 3, 13,6, 4, 16), Block.box(12, 0, 13,13, 2, 16), Block.box(4, 0, 14,7, 1, 15), Block.box(6, 3, 13,7, 4, 14), Block.box(9, 0, 14,10, 3, 15), Block.box(9, 0, 15,10, 2, 16))
  };

  private static final VoxelShape[] skeleton_bottom_north_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[0], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[1], Direction.SOUTH),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[2], Direction.SOUTH)
  };

  private static final VoxelShape[] skeleton_bottom_west_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[0], Direction.EAST),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[1], Direction.EAST),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[2], Direction.EAST)
  };

  private static final VoxelShape[] skeleton_bottom_east_shapes = new VoxelShape[]{
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[0], Direction.WEST),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[1], Direction.WEST),
      VoxelUtil.rotateHorizontal(skeleton_bottom_south_shapes[2], Direction.WEST)
  };

  private final BoneType type;
  private final int variant;

  public BonesBlock(Properties p_i48377_1_, BoneType type, int variant) {
    super(p_i48377_1_);
    this.type = type;
    this.variant = variant;
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
  protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
    Direction facing = pState.getValue(FACING);
    VoxelShape[] shapeArray = null;
    switch (type) {
      case TOP:
        switch (facing) {
          case EAST:
            shapeArray = skeleton_top_east_shapes;
            break;
          case WEST:
            shapeArray = skeleton_top_west_shapes;
            break;
          case SOUTH:
            shapeArray = skeleton_top_south_shapes;
            break;
          case NORTH:
            shapeArray = skeleton_top_north_shapes;
            break;
        }
        break;
      case BOTTOM:
        switch (facing) {
          case EAST:
            shapeArray = skeleton_bottom_east_shapes;
            break;
          case WEST:
            shapeArray = skeleton_bottom_west_shapes;
            break;
          case SOUTH:
            shapeArray = skeleton_bottom_south_shapes;
            break;
          case NORTH:
            shapeArray = skeleton_bottom_north_shapes;
            break;
        }
        break;
      case PILE:
        switch (facing) {
          case EAST:
            shapeArray = bone_pile_east_shapes;
            break;
          case WEST:
            shapeArray = bone_pile_west_shapes;
            break;
          case SOUTH:
            shapeArray = bone_pile_south_shapes;
            break;
          case NORTH:
            shapeArray = bone_pile_north_shapes;
            break;
        }
        break;
      default:
        shapeArray = bone_pile_south_shapes;
    }
    if (shapeArray == null) {
      return bone_pile_south_shapes[0];
    }
    if (variant < shapeArray.length) {
      return shapeArray[variant];
    } else {
      return shapeArray[0];
    }
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockItemUseContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }

  public enum BoneType {
    PILE, TOP, BOTTOM;
  }
}
