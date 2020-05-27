package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemWaspAttractant extends ItemBase {
  public ItemWaspAttractant(@Nonnull String name) {
    super(name);
  }

  @Nonnull
  public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
      return EnumActionResult.FAIL;
    } else {

      IBlockState iblockstate = worldIn.getBlockState(pos);
      Block block = iblockstate.getBlock();

      if ((block == Blocks.LOG && iblockstate.getValue(BlockOldLog.VARIANT) == BlockPlanks.EnumType.OAK) || block == Blocks.LOG2 && iblockstate.getValue(BlockNewLog.VARIANT) == BlockPlanks.EnumType.DARK_OAK) {
        if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
          return EnumActionResult.FAIL;
        }

        pos = pos.offset(facing);

        if (worldIn.isAirBlock(pos)) {
          IBlockState iblockstate1 = ModBlocks.gall_apple.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
          worldIn.setBlockState(pos, iblockstate1, 10);

          if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
          }

          return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.FAIL;
      }

      return EnumActionResult.PASS;
    }
  }
}
