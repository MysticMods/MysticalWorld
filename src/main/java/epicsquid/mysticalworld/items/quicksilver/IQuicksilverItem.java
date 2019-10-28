package epicsquid.mysticalworld.items.quicksilver;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.Random;

public interface IQuicksilverItem {

  int CHANCE_BOUND = 100;

  default void drip(ItemStack stack, LivingEntity entity, Random rand, EquipmentSlotType type) {
    if (rand.nextInt(CHANCE_BOUND) == 1) {
      stack.damageItem(1, entity, livingEntity -> livingEntity.sendBreakAnimation(type));
    }
  }
}
