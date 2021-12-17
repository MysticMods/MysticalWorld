package mysticmods.mysticalworld.events;

import mysticmods.mysticalworld.init.ModModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class DamageHandler {
  public static void onAttackDamage(LivingDamageEvent event) {
    DamageSource source = event.getSource();
    LivingEntity target = event.getEntityLiving();
    if (target.isInvertedHealAndHarm()) {
      if (source != null) {
        Entity trueAttacker = source.getEntity();
        if (trueAttacker instanceof PlayerEntity) {
          PlayerEntity player = (PlayerEntity) trueAttacker;
          float smiteAmount = (float) player.getAttributeValue(ModModifiers.SMITE.get());
          event.setAmount(event.getAmount() + smiteAmount);
        }
      }
    }
    if (target instanceof PlayerEntity && !(target instanceof FakePlayer)) {
      if (source != null) {
        Entity trueAttacker = source.getEntity();
        if (trueAttacker instanceof LivingEntity) {
          if (((LivingEntity) trueAttacker).isInvertedHealAndHarm()) {
            PlayerEntity player = (PlayerEntity) target;
            float blessedAmount = (float) player.getAttributeValue(ModModifiers.BLESSED.get());
            if (blessedAmount > 0) {
              trueAttacker.hurt(DamageSource.ON_FIRE, blessedAmount);
            }
          }
        }
      }
    }
  }
}
