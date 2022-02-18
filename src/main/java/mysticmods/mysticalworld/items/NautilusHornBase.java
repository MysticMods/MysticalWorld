package mysticmods.mysticalworld.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class NautilusHornBase extends Item {
  private final int duration;

  public NautilusHornBase(Properties properties, int duration) {
    super(properties);
    this.duration = duration;
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);

    if (!world.isClientSide) {
      player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, duration));
      itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
    }

    return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
  }

  @Override
  public boolean isFoil(ItemStack stack) {
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
