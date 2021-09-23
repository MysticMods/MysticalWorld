package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import noobanidus.libs.noobutil.util.MathUtil;

import net.minecraft.item.Item.Properties;

public class UnripePearlItem extends Item {

  public UnripePearlItem(Properties props) {
    super(props);
  }

  @Override
  public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getItemInHand(hand);

    if (!world.isClientSide) {
      int counter = 0;
      boolean flag;
      do {
        double d0 = player.getX() + (MathUtil.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = player.getY() + (double) (MathUtil.rand.nextInt(64) - 32);
        double d2 = player.getZ() + (MathUtil.rand.nextDouble() - 0.5D) * 64.0D;
        flag = player.randomTeleport(d0, d1, d2, false);
        counter++;
      } while (!flag && counter != 15);
      if (flag) {
        player.level.playSound(null, player.xo, player.yo, player.zo, ModSounds.ENDERMINI_PORTAL.get(), player.getSoundSource(), 1.0F, 1.0F);
        player.playSound(ModSounds.ENDERMINI_PORTAL.get(), 1.0F, 1.0F);
        player.getCooldowns().addCooldown(this, 20);
        if (!player.isCreative()) {
          itemstack.shrink(1);
        }
      }
    }

    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
  }
}
