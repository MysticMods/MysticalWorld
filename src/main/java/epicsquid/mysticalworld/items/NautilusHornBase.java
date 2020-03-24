package epicsquid.mysticalworld.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class NautilusHornBase extends Item {
  private int duration;

  public NautilusHornBase(Properties properties, int duration) {
    super(properties);
    this.duration = duration;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack itemstack = player.getHeldItem(hand);

    if (!world.isRemote) {
      player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, duration));
      itemstack.damageItem(1, player, (p) -> p.sendBreakAnimation(hand));
    }

    return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
  }

  @Override
  public boolean hasEffect(ItemStack stack) {
    return duration == 500;
  }

  public static class NautilusHorn extends NautilusHornBase {
    public NautilusHorn(Properties properties) {
      super(properties, 200);
    }
  }

  public static class GlisteringHorn extends NautilusHornBase {
    public GlisteringHorn(Properties properties) {
      super(properties, 500);
    }
  }
}
