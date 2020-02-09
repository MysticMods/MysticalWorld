package epicsquid.mysticalworld.core;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.world.World;

public class PlayerHooks {
  public static void spawnShoulderEntities(PlayerEntity player, DataParameter<CompoundNBT> left, DataParameter<CompoundNBT> right) {
    if (player.field_223730_e + 20L < player.world.getGameTime()) {
      CompoundNBT leftNBT = player.getLeftShoulderEntity();
      CompoundNBT rightNBT = player.getRightShoulderEntity();
      if (!leftNBT.isEmpty() && !leftNBT.getString("id").equals("mysticalworld:beetle")) {
        player.spawnShoulderEntity(leftNBT);
        player.getDataManager().set(left, new CompoundNBT());
      }
      if (!rightNBT.isEmpty() && !rightNBT.getString("id").equals("mysticalworld:beetle")) {
        player.spawnShoulderEntity(rightNBT);
        player.getDataManager().set(right, new CompoundNBT());
      }
    }
  }

}
