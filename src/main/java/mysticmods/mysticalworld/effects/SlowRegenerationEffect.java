package mysticmods.mysticalworld.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SlowRegenerationEffect extends MobEffect {
  public SlowRegenerationEffect() {
    super(MobEffectCategory.BENEFICIAL, 0x0034e1eb);
  }

  @Override
  public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
    if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && entityLivingBaseIn.tickCount % 50 == 0) {
      entityLivingBaseIn.heal(1.0F);
    }
  }

  @Override
  public boolean isDurationEffectTick(int duration, int amplifier) {
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
