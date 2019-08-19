package epicsquid.mysticalworld.events;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import epicsquid.mysticalworld.network.MessagePlayerShoulderUpdate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ShoulderHandler {
  @SubscribeEvent
  public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    EntityPlayer player = event.getEntityPlayer();
    World world = event.getWorld();

    if (!world.isRemote && event.getHand() == EnumHand.MAIN_HAND && player.isSneaking()) {
      PlayerShoulderCapability cap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
      if (cap != null) {
        if (cap.isShouldered()) {
          Entity animal = EntityList.createEntityByIDFromName(cap.getRegistryName(), world);
          if (animal != null) {
            animal.readFromNBT(cap.getAnimalSerialized());
            BlockPos pos = event.getPos();
            animal.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            world.spawnEntity(animal);
            player.swingArm(EnumHand.MAIN_HAND);
            cap.drop();
            event.setCanceled(true);
            if (!world.isRemote) {
              MessagePlayerShoulderUpdate message = new MessagePlayerShoulderUpdate(player, cap);
              PacketHandler.sendToAllTracking(message, player);
            }
          }
        }
      }
    }
  }
}
