package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class MaskHandler {
  public static void onAttackEntity(AttackEntityEvent event) {
    PlayerEntity player = event.getPlayer();
    World world = player.world;
    if (world.isRemote) {
      return;
    }

    if (ConfigManager.HAT_CONFIG.getMaskChance() == -1) {
      return;
    }
    Entity targetEntity = event.getTarget();

    if (targetEntity.isAlive() && targetEntity instanceof LivingEntity) {
      LivingEntity target = (LivingEntity) targetEntity;
      ItemStack mask = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
      if (mask.getItem() == ModItems.BEETLE_MASK.get()) {
        if (world.rand.nextInt(ConfigManager.HAT_CONFIG.getMaskChance()) == 0) {
          if (ConfigManager.HAT_CONFIG.getMaskDurabilityDamage() != -1) {
            if (world.rand.nextInt(4) == 1) {
              target.attackEntityFrom(DamageSource.causeMobDamage(player), ConfigManager.HAT_CONFIG.getMaskAttackDamage());
              mask.damageItem(ConfigManager.HAT_CONFIG.getAntlerDamage(), player, (breaker) -> {
                breaker.sendBreakAnimation(EquipmentSlotType.HEAD);
              });
            }
          }
        }
      }
    }
  }
}
