package epicsquid.mysticalworld.events;

public class EntityHandler {
 /* public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event) {
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
      // TODO: Recopy this
*//*      VillagerEntity villagerentity = (VillagerEntity) entity;
      ZombieVillagerEntity zombievillagerentity = EntityType.ZOMBIE_VILLAGER.create(player.world);
      zombievillagerentity.copyLocationAndAnglesFrom(villagerentity);
      villagerentity.remove();
      zombievillagerentity.onInitialSpawn(player.world, player.world.getDifficultyForLocation(zombievillagerentity.getPosition()), SpawnReason.CONVERSION, null, null);
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
      player.world.playEvent(null, 1026, new BlockPos(player), 0);*//*
    }

    if (!player.abilities.isCreativeMode) {
      held.shrink(1);
    }
  }*/
}
