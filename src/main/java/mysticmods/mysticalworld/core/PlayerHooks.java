package mysticmods.mysticalworld.core;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;

@SuppressWarnings("unused")
public class PlayerHooks {
  public static void spawnShoulderEntities(PlayerEntity player, DataParameter<CompoundNBT> left, DataParameter<CompoundNBT> right) {
    if (player.timeEntitySatOnShoulder + 20L < player.level.getGameTime()) {
      CompoundNBT leftNBT = player.getShoulderEntityLeft();
      CompoundNBT rightNBT = player.getShoulderEntityRight();
      if (!leftNBT.isEmpty() && !leftNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(leftNBT);
        player.getEntityData().set(left, new CompoundNBT());
      }
      if (!rightNBT.isEmpty() && !rightNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(rightNBT);
        player.getEntityData().set(right, new CompoundNBT());
      }
    }
  }
}
