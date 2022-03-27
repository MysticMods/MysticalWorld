package mysticmods.mysticalworld.events.forge;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModModifiers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= MysticalWorld.MODID)
public class DamageHandler {
  @SubscribeEvent
  public static void onAttackDamage(LivingDamageEvent event) {
    DamageSource source = event.getSource();
    LivingEntity target = event.getEntityLiving();
    if (target.isInvertedHealAndHarm()) {
      if (source != null) {
        Entity trueAttacker = source.getEntity();
        if (trueAttacker instanceof Player) {
          Player player = (Player) trueAttacker;
          float smiteAmount = (float) player.getAttributeValue(ModModifiers.SMITE.get());
          event.setAmount(event.getAmount() + smiteAmount);
        }
      }
    }
    if (target instanceof Player && !(target instanceof FakePlayer)) {
      if (source != null) {
        Entity trueAttacker = source.getEntity();
        if (trueAttacker instanceof LivingEntity) {
          if (((LivingEntity) trueAttacker).isInvertedHealAndHarm()) {
            Player player = (Player) target;
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
