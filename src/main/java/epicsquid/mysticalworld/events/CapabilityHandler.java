package epicsquid.mysticalworld.events;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityProvider;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import epicsquid.mysticalworld.network.MessagePlayerShoulderUpdate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class CapabilityHandler {

  @SubscribeEvent
  public static void attackCapability(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof EntitySquid) {
      event.addCapability(AnimalCooldownCapabilityProvider.IDENTIFIER, new AnimalCooldownCapabilityProvider());
    } else if (event.getObject() instanceof EntityPlayer) {
      event.addCapability(PlayerShoulderCapabilityProvider.IDENTIFIER, new PlayerShoulderCapabilityProvider());
    }
  }

  @SubscribeEvent
  public void onTracking(PlayerEvent.StartTracking event) {
    Entity target = event.getTarget();
    EntityPlayer victim = event.getEntityPlayer();
    if (target instanceof EntityPlayerMP) {
      PlayerShoulderCapability cap = target.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
      if (cap != null) {
        MessagePlayerShoulderUpdate packet = new MessagePlayerShoulderUpdate((EntityPlayer) target, cap);
        PacketHandler.INSTANCE.sendTo(packet, (EntityPlayerMP) victim);
      }
    }
  }

  // TODO: Is this really required?
  @SubscribeEvent
  public void onJoinWorld(EntityJoinWorldEvent event) {
    Entity entity = event.getEntity();
    if (entity instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP) entity;
      PlayerShoulderCapability cap = player.getCapability(PlayerShoulderCapabilityProvider.PLAYER_SHOULDER_CAPABILITY, null);
      if (cap != null) {
        MessagePlayerShoulderUpdate packet = new MessagePlayerShoulderUpdate(player, cap);
        PacketHandler.INSTANCE.sendTo(packet, player);
      }
    }
  }
}
