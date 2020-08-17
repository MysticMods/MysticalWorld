package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.Capabilities;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityProvider;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.network.ShoulderRide;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class CapabilityHandler {
  public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
    player.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent((cap) -> {
      if (cap.isShouldered()) {
        ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
        Networking.send(PacketDistributor.ALL.noArg(), message);
        try {
          PlayerShoulderCapability.setRightShoulder.invokeExact(player, cap.generateShoulderNBT());
        } catch (Throwable throwable) {
          MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
        }
      }
    });
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    for (ServerPlayerEntity other : server.getPlayerList().getPlayers()) {
      other.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent((cap) -> {
        if (cap.isShouldered()) {
          ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
          Networking.sendTo(message, player);
          try {
            PlayerShoulderCapability.setRightShoulder.invokeExact(other, cap.generateShoulderNBT());
          } catch (Throwable throwable) {
            MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
          }
        }
      });
    }
  }

  public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof SquidEntity) {
      event.addCapability(AnimalCooldownCapabilityProvider.IDENTIFIER, new AnimalCooldownCapabilityProvider());
    } else if (event.getObject() instanceof PlayerEntity) {
      event.addCapability(PlayerShoulderCapabilityProvider.IDENTIFIER, new PlayerShoulderCapabilityProvider());
    }
  }

  public static void onSquidMilked(PlayerInteractEvent.EntityInteract event) {
    PlayerEntity player = (PlayerEntity) event.getEntity();
    ItemStack heldItem = player.getHeldItem(event.getHand());
    if (!heldItem.isEmpty() && heldItem.getItem() instanceof GlassBottleItem) {
      if (event.getTarget() instanceof SquidEntity) {
        event.setCanceled(true);
        event.setCancellationResult(ActionResultType.SUCCESS);
        if (!event.getWorld().isRemote) {
          event.getTarget().getCapability(AnimalCooldownCapabilityProvider.ANIMAL_COOLDOWN_CAPABILITY).ifPresent(cap -> {
            if (cap.canHarvest()) {
              cap.setCooldown(20 * 15);
              event.getWorld().playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_COW_MILK, SoundCategory.PLAYERS, 0.5F, event.getWorld().rand.nextFloat() * 0.25F + 0.6F, true);
              if (!player.isCreative()) {
                heldItem.shrink(1);
              }
              player.inventory.addItemStackToInventory(new ItemStack(ModItems.INK_BOTTLE.get()));
            } else {
              player.sendStatusMessage(new TranslationTextComponent("message.squid.cooldown").setStyle(new Style().setColor(TextFormatting.BLUE).setBold(true)), true);
            }
          });
        }
      }
    }
  }
}
