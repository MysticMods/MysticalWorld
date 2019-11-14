package epicsquid.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class MudBlock extends Block {
  private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

  private final boolean smallerBlock;

  public MudBlock(Properties props, boolean smallerBlock) {
    super(props);
    this.smallerBlock = smallerBlock;
  }

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    entityIn.setMotion(entityIn.getMotion().getX() * 0.4, entityIn.getMotion().getY(), entityIn.getMotion().getZ() * 0.4);
    super.onEntityCollision(state, worldIn, pos, entityIn);
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    if (smallerBlock) {
      return SHAPE;
    } else {
      return super.getShape(state, worldIn, pos, context);
    }
  }
}