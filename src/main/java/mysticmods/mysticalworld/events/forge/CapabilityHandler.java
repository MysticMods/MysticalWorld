package mysticmods.mysticalworld.events.forge;

import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.capability.AnimalCooldownCapability;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.network.ShoulderRide;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class CapabilityHandler {

  // TODO: Check to see what busses these are fired on
  @SubscribeEvent
  public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    ServerPlayer player = (ServerPlayer) event.getPlayer();
    // Shim to fix mismatched data from left/right switch.
    if (player.getShoulderEntityRight().contains("id", Tag.TAG_STRING) && player.getShoulderEntityRight().getString("id").equals("mysticalworld:beetle")) {
      try {
        PlayerShoulderCapability.setRightShoulder.invokeExact((Player) player, new CompoundTag());
      } catch (Throwable e) {
        MysticalWorld.LOG.error("Unable to clear " + player + "'s right shoulder! Oh dear.", e);
      }
    }

    player.getCapability(Capabilities.PLAYER_SHOULDER).ifPresent((cap) -> {
      if (cap.isShouldered()) {
        ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
        Networking.send(PacketDistributor.ALL.noArg(), message);
        try {
          PlayerShoulderCapability.setLeftShoulder.invokeExact((Player) player, cap.generateShoulderNBT());
        } catch (Throwable throwable) {
          MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
        }
      }
    });
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    for (ServerPlayer other : server.getPlayerList().getPlayers()) {
      other.getCapability(Capabilities.PLAYER_SHOULDER).ifPresent((cap) -> {
        if (cap.isShouldered()) {
          ShoulderRide message = new ShoulderRide(event.getPlayer(), cap);
          Networking.sendTo(message, player);
          try {
            PlayerShoulderCapability.setLeftShoulder.invokeExact((Player) other, cap.generateShoulderNBT());
          } catch (Throwable throwable) {
            MysticalWorld.LOG.error("Unable to fake player having a shoulder entity", throwable);
          }
        }
      });
    }
  }

  // TODO: Glow squid ink?
  @SubscribeEvent
  public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject().getType() == EntityType.SQUID || event.getObject().getType() == EntityType.GLOW_SQUID) {
      event.addCapability(Capabilities.ANIMAL_COOLDOWN_ID, new AnimalCooldownCapability());
    } else if (event.getObject().getType() == EntityType.PLAYER) {
      event.addCapability(Capabilities.PLAYER_SHOULDER_ID, new PlayerShoulderCapability());
    }
  }

  @SubscribeEvent
  public static void onSquidMilked(PlayerInteractEvent.EntityInteract event) {
    Player player = (Player) event.getEntity();
    ItemStack heldItem = player.getItemInHand(event.getHand());
    if (heldItem.is(MWTags.Items.BOTTLES)) {
      if (event.getTarget().getType() == EntityType.SQUID || event.getTarget().getType() == EntityType.GLOW_SQUID) {
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
        if (!event.getWorld().isClientSide) {
          event.getTarget().getCapability(Capabilities.ANIMAL_COOLDOWN).ifPresent(cap -> {
            if (cap.hasExpired()) {
              cap.setCooldown(20 * 15);
              event.getWorld().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.SQUID_MILK.get(), SoundSource.PLAYERS, 0.5F, event.getWorld().random.nextFloat() * 0.25F + 0.6F, true);
              if (!player.isCreative()) {
                heldItem.shrink(1);
              }
              player.getInventory().add(new ItemStack(ModItems.INK_BOTTLE.get()));
            } else {
              player.displayClientMessage(new TranslatableComponent("message.squid.cooldown").setStyle(Style.EMPTY.withColor(TextColor.fromLegacyFormat(ChatFormatting.BLUE)).withBold(true)), true);
            }
          });
        }
      }
    }
  }
}
