package epicsquid.mysticalworld.block;

import epicsquid.mysticallib.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockSlimePuddle extends BlockBase {
  public static AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

  public BlockSlimePuddle(@Nonnull Material mat, @Nonnull SoundType type, float hardness, @Nonnull String name) {
    super(mat, type, hardness, name);
    this.slipperiness = 0.8F;
    this.useNeighborBrightness = true;
  }

  @Nonnull
  @Override
  public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
    return AABB;
  }

  @Override
  public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
  }

  @Override
  public boolean isTopSolid(IBlockState state) {
    return false;
  }

  @Override
  public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    return true;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @Override
  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    IBlockState iblockstate = worldIn.getBlockState(pos.down());
    return iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID;
  }

  @Override
  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    this.checkAndDropBlock(worldIn, pos, state);
  }

  private boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
    if (!this.canPlaceBlockAt(worldIn, pos)) {
      worldIn.destroyBlock(pos, true);
      return false;
    } else {
      return true;
    }
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Items.SLIME_BALL;
  }

  @Override
  public int quantityDropped(Random random) {
    return 1;
  }

  @Override
  public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
    return true;
  }

  @Override
  public int quantityDroppedWithBonus(int fortune, Random random) {
    return 1;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getRenderLayer() {
    return BlockRenderLayer.TRANSLUCENT;
  }

  @Override
  public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
    if (entityIn.isSneaking()) {
      super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    } else {
      entityIn.fall(fallDistance, 0.0F);
    }
  }


  @Override
  public void onLanded(World worldIn, Entity entityIn) {
    if (entityIn.isSneaking()) {
      super.onLanded(worldIn, entityIn);
    } else if (entityIn.motionY < 0.0D) {
      entityIn.motionY = -entityIn.motionY;

      if (!(entityIn instanceof EntityLivingBase)) {
        entityIn.motionY *= 0.8D;
      }
    }
  }

  @Override
  public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
      double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
      entityIn.motionX *= d0;
      entityIn.motionZ *= d0;
    }

    super.onEntityWalk(worldIn, pos, entityIn);
  }
}
