package mysticmods.mysticalworld.events;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.capability.AnimalCooldownCapabilityProvider;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import mysticmods.mysticalworld.capability.PlayerShoulderCapabilityProvider;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.network.ShoulderRide;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.Style;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class CapabilityHandler {
  public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    ServerPlayer player = (ServerPlayer) event.getPlayer();
    // Shim to fix mismatched data from left/right switch.
    if (player.getShoulderEntityRight().contains("id", Constants.NBT.TAG_STRING) && player.getShoulderEntityRight().getString("id").equals("mysticalworld:beetle")) {
      try {
        PlayerShoulderCapability.setRightShoulder.invokeExact((Player) player, new CompoundTag());
      } catch (Throwable e) {
        MysticalWorld.LOG.error("Unable to clear " + player + "'s right shoulder! Oh dear.", e);
      }
    }

    player.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent((cap) -> {
      if (cap.isShouldered()) {
        ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
        Networking.send(PacketDistributor.ALL.noArg(), message);
        try {
          PlayerShoulderCapability.setLeftShoulder.invokeExact((Player)player, cap.generateShoulderNBT());
        } catch (Throwable throwable) {
          MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
        }
      }
    });
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    for (ServerPlayer other : server.getPlayerList().getPlayers()) {
      other.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent((cap) -> {
        if (cap.isShouldered()) {
          ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
          Networking.sendTo(message, player);
          try {
            PlayerShoulderCapability.setLeftShoulder.invokeExact((Player)other, cap.generateShoulderNBT());
          } catch (Throwable throwable) {
            MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
          }
        }
      });
    }
  }

  public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof Squid) {
      event.addCapability(AnimalCooldownCapabilityProvider.IDENTIFIER, new AnimalCooldownCapabilityProvider());
    } else if (event.getObject() instanceof Player) {
      event.addCapability(PlayerShoulderCapabilityProvider.IDENTIFIER, new PlayerShoulderCapabilityProvider());
    }
  }

  public static void onSquidMilked(PlayerInteractEvent.EntityInteract event) {
    Player player = (Player) event.getEntity();
    ItemStack heldItem = player.getItemInHand(event.getHand());
    if (!heldItem.isEmpty() && heldItem.getItem() instanceof BottleItem) {
      if (event.getTarget() instanceof Squid) {
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
        if (!event.getWorld().isClientSide) {
          event.getTarget().getCapability(AnimalCooldownCapabilityProvider.ANIMAL_COOLDOWN_CAPABILITY).ifPresent(cap -> {
            if (cap.canHarvest()) {
              cap.setCooldown(20 * 15);
              event.getWorld().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.SQUID_MILK.get(), SoundSource.PLAYERS, 0.5F, event.getWorld().random.nextFloat() * 0.25F + 0.6F, true);
              if (!player.isCreative()) {
                heldItem.shrink(1);
              }
              player.inventory.add(new ItemStack(ModItems.INK_BOTTLE.get()));
            } else {
              player.displayClientMessage(new TranslatableComponent("message.squid.cooldown").setStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.BLUE)).withBold(true)), true);
            }
          });
        }
      }
    }
  }
}
