package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.SpiritBeetleEntity;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.items.AntlerHatItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
          BlockPos playerPos = player.getPosition();
          if (world.getEntitiesWithinAABB(SpiritBeetleEntity.class, AntlerHatItem.BOX.offset(playerPos)).size() >= 5) {
            return;
          }

          BlockPos pos;

          int tries = 100;
          while (true) {
            tries--;
            if (tries <= 0) {
              return;
            }
            pos = playerPos.add(world.rand.nextInt(4) - 2, 0, world.rand.nextInt(4) - 2);
            if (!world.isAirBlock(pos)) {
              continue;
            }
            if (player.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 5) {
              continue;
            }

            break;
          }
          SpiritBeetleEntity spiritBeetle = ModEntities.SPIRIT_BEETLE.get().create(world);
          if (spiritBeetle != null) {
            spiritBeetle.setAttackTarget(target);
            spiritBeetle.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, player.rotationYaw, player.rotationPitch);
            spiritBeetle.noClip = true;
            world.addEntity(spiritBeetle);
            if (ConfigManager.HAT_CONFIG.getMaskDurabilityDamage() != -1) {
              if (world.rand.nextInt(4) == 1) {
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
}
