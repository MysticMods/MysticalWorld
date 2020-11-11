package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.init.ModModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class DamageHandler {
  public static void onAttackDamage(LivingDamageEvent event) {
    DamageSource source = event.getSource();
    LivingEntity target = event.getEntityLiving();
    if (target.isEntityUndead()) {
      if (source != null) {
        Entity trueAttacker = source.getTrueSource();
        if (trueAttacker instanceof PlayerEntity) {
          PlayerEntity player = (PlayerEntity) trueAttacker;
          float smiteAmount = (float) player.getAttributeValue(ModModifiers.SMITE.get());
          event.setAmount(event.getAmount() + smiteAmount);
        }
      }
    }
    if (target instanceof PlayerEntity) {
      if (source != null) {
        Entity trueAttacker = source.getTrueSource();
        if (trueAttacker instanceof LivingEntity) {
          if (((LivingEntity) trueAttacker).isEntityUndead()) {
            PlayerEntity player = (PlayerEntity) target;
            float blessedAmount = (float) player.getAttributeValue(ModModifiers.BLESSED.get());
            if (blessedAmount > 0) {
              trueAttacker.attackEntityFrom(DamageSource.ON_FIRE, blessedAmount);
            }
          }
        }
      }
    }
  }
}
