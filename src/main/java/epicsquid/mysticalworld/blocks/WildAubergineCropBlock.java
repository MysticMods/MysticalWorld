package epicsquid.mysticalworld.blocks;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class WildAubergineCropBlock extends CropsBlock {
  public static final IntegerProperty AGE = BlockStateProperties.AGE_0_1;
  private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

  public WildAubergineCropBlock(AbstractBlock.Properties builder) {
    super(builder);
    this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), 0));
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    return SHAPE;
  }

  @Override
  protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.COARSE_DIRT) || state.isIn(Blocks.PODZOL) || state.isIn(Blocks.FARMLAND);
  }

  @Override
  public IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Override
  public int getMaxAge() {
    return 0;
  }

  @Override
  protected int getAge(BlockState state) {
    return 0;
  }

  @Override
  public BlockState withAge(int age) {
    return this.getDefaultState();
  }

  @Override
  public boolean isMaxAge(BlockState state) {
    return true;
  }

  @Override
  public boolean ticksRandomly(BlockState state) {
    return false;
  }

  @Override
  public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
  }

  @Override
  public void grow(World worldIn, BlockPos pos, BlockState state) {
  }

  @Override
  protected int getBonemealAgeIncrease(World worldIn) {
    return 0;
  }

  @Override
  public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
    BlockPos blockpos = pos.down();
    if (state.getBlock() == this) {
      return true;
    }
    return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
  }

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
  }

  @Override
  public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
    return new ItemStack(ModItems.AUBERGINE.get());
  }

  @Override
  public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
    return false;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
    return false;
  }

  @Override
  public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(AGE);
  }
}
