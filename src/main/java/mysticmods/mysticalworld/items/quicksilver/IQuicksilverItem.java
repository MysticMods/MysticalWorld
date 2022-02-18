package mysticmods.mysticalworld.items.quicksilver;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public interface IQuicksilverItem {

  int CHANCE_BOUND = 100;

  default void drip(ItemStack stack, LivingEntity entity, Random rand, EquipmentSlot type) {
    if (rand.nextInt(CHANCE_BOUND) == 1) {
      stack.hurtAndBreak(1, entity, livingEntity -> livingEntity.broadcastBreakEvent(type));
    }
  }
}
