package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EntityHandler {
  public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
    Entity entity = event.getTarget();
    if (!(entity instanceof VillagerEntity)) {
      return;
    }

    PlayerEntity player = event.getPlayer();
    ItemStack held = player.getHeldItemMainhand();
    if (held.getItem() != ModItems.ROTTEN_APPLE.get()) {
      return;
    }

    if (player.world.rand.nextInt(3) == 0) {
      VillagerEntity villagerentity = (VillagerEntity) entity;
      ZombieVillagerEntity zombievillagerentity = EntityType.ZOMBIE_VILLAGER.create(player.world);
      zombievillagerentity.copyLocationAndAnglesFrom(villagerentity);
      villagerentity.remove();
      zombievillagerentity.onInitialSpawn(player.world, player.world.getDifficultyForLocation(new BlockPos(zombievillagerentity)), SpawnReason.CONVERSION, null, null);
      zombievillagerentity.setVillagerData(villagerentity.getVillagerData());
      zombievillagerentity.setGossips(villagerentity.getGossip().serialize(NBTDynamicOps.INSTANCE).getValue());
      zombievillagerentity.setOffers(villagerentity.getOffers().write());
      zombievillagerentity.setEXP(villagerentity.getXp());
      zombievillagerentity.setChild(villagerentity.isChild());
      zombievillagerentity.setNoAI(villagerentity.isAIDisabled());
      if (villagerentity.hasCustomName()) {
        zombievillagerentity.setCustomName(villagerentity.getCustomName());
        zombievillagerentity.setCustomNameVisible(villagerentity.isCustomNameVisible());
      }

      player.world.addEntity(zombievillagerentity);
      player.world.playEvent(null, 1026, new BlockPos(player), 0);
    }

    if (!player.abilities.isCreativeMode) {
      held.shrink(1);
    }
  }
}
