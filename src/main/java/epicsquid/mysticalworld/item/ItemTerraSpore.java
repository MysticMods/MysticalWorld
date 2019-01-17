package epicsquid.mysticalworld.item;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTerraSpore extends ItemBase {

  public ItemTerraSpore(@Nonnull String name) {
    super(name);
  }

  @Override
  @Nonnull
  public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (world.getBlockState(pos).getBlock() == Blocks.COBBLESTONE && isWaterAround(pos, world)) {
      world.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState());
      return EnumActionResult.SUCCESS;
    }
    return EnumActionResult.PASS;
  }

  private boolean isWaterAround(BlockPos pos, World world) {
    for (EnumFacing dir : EnumFacing.HORIZONTALS) {
      if (world.getBlockState(pos.offset(dir)).getBlock() == Blocks.WATER) {
        return true;
      }
    }
    return false;
  }
}
