package mysticmods.mysticalworld.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.player.Player;

@SuppressWarnings("unused")
public class PlayerHooks {
  public static void spawnShoulderEntities(Player player, EntityDataAccessor<CompoundTag> left, EntityDataAccessor<CompoundTag> right) {
    if (player.timeEntitySatOnShoulder + 20L < player.level.getGameTime()) {
      CompoundTag leftNBT = player.getShoulderEntityLeft();
      CompoundTag rightNBT = player.getShoulderEntityRight();
      if (!leftNBT.isEmpty() && !leftNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(leftNBT);
        player.getEntityData().set(left, new CompoundTag());
      }
      if (!rightNBT.isEmpty() && !rightNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(rightNBT);
        player.getEntityData().set(right, new CompoundTag());
      }
    }
  }
}
