package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import noobanidus.libs.noobutil.util.MathUtil;

public class UnripePearlItem extends Item {

  public UnripePearlItem(Properties props) {
    super(props);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
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
        player.level.playSound(null, player.xo, player.yo, player.zo, ModSounds.UNRIPE_PEARL_USE.get(), player.getSoundSource(), 1.0F, 1.0F);
        player.playSound(ModSounds.ENDERMINI_PORTAL.get(), 1.0F, 1.0F);
        player.getCooldowns().addCooldown(this, 20);
        if (!player.isCreative()) {
          itemstack.shrink(1);
        }
      }
    }

    return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
  }
}
