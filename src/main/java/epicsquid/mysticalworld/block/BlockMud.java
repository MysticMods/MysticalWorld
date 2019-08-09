package epicsquid.mysticalworld.block;

import epicsquid.mysticallib.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockMud extends BlockBase {
  protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

  public static final PropertyInteger DRYNESS = PropertyInteger.create("dryness", 0, 5);

  private Block dryEquivalent;

  public BlockMud(@Nonnull Material mat, @Nonnull SoundType type, float hardness, @Nonnull String name) {
    super(mat, type, hardness, name);
    this.setDefaultState(this.getDefaultState().withProperty(DRYNESS, 5));
    this.setTickRandomly(true);
  }

  public Block getDryEquivalent() {
    return dryEquivalent;
  }

  public BlockMud setDryEquivalent(Block dryEquivalent) {
    this.dryEquivalent = dryEquivalent;
    return this;
  }

  @Override
  @SuppressWarnings("deprecation")
  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(DRYNESS, meta);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(DRYNESS);
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, DRYNESS);
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    super.updateTick(worldIn, pos, state, rand);

    int i = getMetaFromState(state);
    if (i == 0) {
      worldIn.setBlockState(pos, getDryEquivalent().getDefaultState(), 2);
    } else {
      worldIn.setBlockState(pos, state.withProperty(DRYNESS, i - 1), 2);
    }
  }

  public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    entityIn.motionX *= 0.4D;
    entityIn.motionZ *= 0.4D;
  }

  @Nonnull
  @Override
  public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
    return MUD_AABB;
  }
}