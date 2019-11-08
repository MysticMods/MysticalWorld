package epicsquid.mysticalworld.items;

import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class UnripePearlItem extends Item {

  public UnripePearlItem(Properties props) {
    super(props);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (!world.isRemote) {
      int counter = 0;
      boolean flag;
      do {
        double d0 = player.posX + (Util.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = player.posY + (double) (Util.rand.nextInt(64) - 32);
        double d2 = player.posZ + (Util.rand.nextDouble() - 0.5D) * 64.0D;
        flag = player.attemptTeleport(d0, d1, d2, false);
        counter++;
      } while (!flag && counter != 15);
      if (flag) {
        player.world.playSound(null, player.prevPosX, player.prevPosY, player.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, player.getSoundCategory(), 1.0F, 1.0F);
        player.playSound(ModSounds.Endermini.PORTAL, 1.0F, 1.0F);
        player.getCooldownTracker().setCooldown(this, 20);
        if (!player.isCreative()) {
          itemstack.shrink(1);
        }
      }
    }

    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
  }
}
