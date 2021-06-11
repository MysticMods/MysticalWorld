package mysticmods.mysticalworld.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SlowRegenerationEffect extends Effect {
  public SlowRegenerationEffect() {
    super(EffectType.BENEFICIAL, 0x0034e1eb);
  }

  @Override
  public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
    if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && entityLivingBaseIn.ticksExisted % 50 == 0) {
      entityLivingBaseIn.heal(1.0F);
    }
  }

  @Override
  public boolean isReady(int duration, int amplifier) {
    int k = 50 >> amplifier;
    if (k > 0) {
      return duration % k == 0;
    } else {
      return true;
    }
  }

  @Override
  public boolean isBeneficial() {
    return true;
  }
}
