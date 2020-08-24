package epicsquid.mysticalworld.block;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockOakApple extends BlockBase implements IGrowable {
  public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
  public static final PropertyDirection FACING = BlockHorizontal.FACING;

  protected static final AxisAlignedBB[] gall_apple_NORTH_AABB = new AxisAlignedBB[]{
      new AxisAlignedBB(0.4375, 0.4375, 0, 0.5625, 0.5625, 0.0625), //0.6, 0.6, 0.0625),
      new AxisAlignedBB(0.4375, 0.4375, 0, 0.5625, 0.5625, 0.125), //0.6, 0.6, 0.125),
      new AxisAlignedBB(0.40625, 0.40625, 0, 0.59375, 0.59375, 0.125),
      new AxisAlignedBB(0.40625, 0.40625, 0, 0.59375, 0.59375, 0.1875)
  };
  protected static final AxisAlignedBB[] gall_apple_SOUTH_AABB = new AxisAlignedBB[]{
      new AxisAlignedBB(0.4375, 0.4375, 0.9375, 0.5625, 0.5625, 1),
      new AxisAlignedBB(0.4375, 0.4375, 0.875, 0.5625, 0.5625, 1),
      new AxisAlignedBB(0.40625, 0.40625, 0.875, 0.59375, 0.59375, 1),
      new AxisAlignedBB(0.40625, 0.40625, 0.8125, 0.59375, 0.59375, 1)
  };
  protected static final AxisAlignedBB[] gall_apple_WEST_AABB = new AxisAlignedBB[]{
      new AxisAlignedBB(0, 0.4375, 0.4375, 0.0625, 0.5625, 0.5625),
      new AxisAlignedBB(0, 0.4375, 0.4375, 0.125, 0.5625, 0.5625),
      new AxisAlignedBB(0, 0.40625, 0.40625, 0.125, 0.59375, 0.59375),
      new AxisAlignedBB(0, 0.40625, 0.40625, 0.1875, 0.59375, 0.59375)
  };
  protected static final AxisAlignedBB[] gall_apple_EAST_AABB = new AxisAlignedBB[]{
      new AxisAlignedBB(0.9375, 0.4375, 0.4375, 1, 0.5625, 0.5625),
      new AxisAlignedBB(0.875, 0.4375, 0.4375, 1, 0.5625, 0.5625),
      new AxisAlignedBB(0.875, 0.40625, 0.40625, 1, 0.59375, 0.59375),
      new AxisAlignedBB(0.8125, 0.40625, 0.40625, 1, 0.59375, 0.59375)
  };

  public BlockOakApple(@Nonnull String name) {
    super(Material.PLANTS, SoundType.PLANT, 0.0f, name);
    this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(AGE, 0));
    this.setTickRandomly(true);
    this.setItemBlock(null);
    this.useNeighborBrightness = true;
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (!this.canBlockStay(worldIn, pos, state)) {
      this.dropBlock(worldIn, pos, state);
    } else {
      int i = state.getValue(AGE);

      if (i < 3 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
        worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
      }
    }
  }

  public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
    pos = pos.offset(state.getValue(FACING));
    IBlockState iblockstate = worldIn.getBlockState(pos);
    return iblockstate.getBlock() == Blocks.LOG && (iblockstate.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.OAK || iblockstate.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.DARK_OAK);
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    int i = state.getValue(AGE);

    switch (state.getValue(FACING)) {
      case SOUTH:
        return gall_apple_SOUTH_AABB[i];
      case NORTH:
      default:
        return gall_apple_NORTH_AABB[i];
      case WEST:
        return gall_apple_WEST_AABB[i];
      case EAST:
        return gall_apple_EAST_AABB[i];
    }
  }

  @Override
  public IBlockState withRotation(IBlockState state, Rotation rot) {
    return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
  }

  @Override
  public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    if (!this.canBlockStay(worldIn, pos, state)) {
      this.dropBlock(worldIn, pos, state);
    }
  }

  private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    this.dropBlockAsItem(worldIn, pos, state, 0);
  }

  @Override
  public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    if (state.getValue(AGE) == 3) {
      return ModItems.gall_apple;
    }

    return Items.AIR;
  }

  @Override
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
    if (state.getValue(AGE) == 3) {
      return new ItemStack(ModItems.gall_apple);
    }

    return ItemStack.EMPTY;
  }

  @Override
  public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
    return state.getValue(AGE) < 3;
  }

  @Override
  public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    return false;
  }

  @Override
  public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
    worldIn.setBlockState(pos, state.withProperty(AGE, state.getValue(AGE) + 1), 3);
  }

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.CUTOUT;
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(AGE, (meta & 15) >> 2);
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    int i = 0;
    i = i | state.getValue(FACING).getHorizontalIndex();
    i = i | state.getValue(AGE) << 2;
    return i;
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING, AGE);
  }

  @Override
  public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    return BlockFaceShape.UNDEFINED;
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    EnumFacing enumfacing = EnumFacing.fromAngle((double) placer.rotationYaw);
    worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
  }

  @Override
  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    if (!facing.getAxis().isHorizontal()) {
      facing = EnumFacing.NORTH;
    }

    return this.getDefaultState().withProperty(FACING, facing.getOpposite()).withProperty(AGE, 0);
  }

}
