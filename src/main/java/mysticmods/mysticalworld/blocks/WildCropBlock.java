package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.MWTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

// TODO: Noobutil this base
public class WildCropBlock extends BushBlock {
  private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

  public WildCropBlock(BlockBehaviour.Properties builder) {
    super(builder);
  }

  @SuppressWarnings("deprecation")
  @Override
  public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }



  @Override
  protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
    return state.is(MWTags.Blocks.SUPPORTS_WILD_AUBERGINE);
  }

  @Override
  public boolean isRandomlyTicking(BlockState state) {
    return false;
  }

  @SuppressWarnings("deprecation")
  @Override
  public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
    return this.mayPlaceOn(worldIn.getBlockState(pos.below()), worldIn, pos.below());
  }
}
