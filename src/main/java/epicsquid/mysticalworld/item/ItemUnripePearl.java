package epicsquid.mysticalworld.item;

import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemUnripePearl extends ItemBase {
  public ItemUnripePearl(@Nonnull String name) {
    super(name);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (!worldIn.isRemote) {
      int counter = 0;
      boolean flag;
      while (true) {
        double d0 = player.posX + (Util.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = player.posY + (double) (Util.rand.nextInt(64) - 32);
        double d2 = player.posZ + (Util.rand.nextDouble() - 0.5D) * 64.0D;
        flag = player.attemptTeleport(d0, d1, d2);
        counter++;
        if (flag || counter == 15) {
          break;
        }
      }
      if (flag) {
        player.world.playSound(null, player.prevPosX, player.prevPosY, player.prevPosZ, ModSounds.General.UNRIPE_PEARL, player.getSoundCategory(), 1.0F, 1.0F);
        player.playSound(ModSounds.Endermini.PORTAL, 1.0F, 1.0F);
        player.getCooldownTracker().setCooldown(this, 20);
        if (!player.capabilities.isCreativeMode) {
          itemstack.shrink(1);
        }
      }
    }

    player.addStat(StatList.getObjectUseStats(this));
    return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
  }
}
