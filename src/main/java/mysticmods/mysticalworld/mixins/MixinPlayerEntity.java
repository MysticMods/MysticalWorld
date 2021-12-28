package mysticmods.mysticalworld.mixins;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
  @Inject(method = "removeEntitiesOnShoulder", at = @At("HEAD"), cancellable = true)
  protected void avoidRemovingBeetles(CallbackInfo info) {
    PlayerEntity player = (PlayerEntity) (Object) this;

    if (player.timeEntitySatOnShoulder + 20L < player.level.getGameTime()) {
      CompoundNBT leftNBT = player.getShoulderEntityLeft();
      CompoundNBT rightNBT = player.getShoulderEntityRight();
      if (!leftNBT.isEmpty() && !leftNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(leftNBT);
        try {
          PlayerShoulderCapability.setLeftShoulder.invokeExact(player, new CompoundNBT());
        } catch (Throwable e) {
          MysticalWorld.LOG.error("Unable to unset left shoulder entity data for player " + player, e);
        }
      }
      if (!rightNBT.isEmpty() && !rightNBT.getString("id").equals("mysticalworld:beetle")) {
        player.respawnEntityOnShoulder(rightNBT);
        try {
          PlayerShoulderCapability.setRightShoulder.invokeExact(player, new CompoundNBT());
        } catch (Throwable e) {
          MysticalWorld.LOG.error("Unable to unset right shoulder entity data for player " + player, e);
        }
      }
    }
    info.cancel();
  }
}
