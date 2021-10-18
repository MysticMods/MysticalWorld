package mysticmods.mysticalworld.events;

import mysticmods.mysticalworld.capability.AnimalCooldownCapabilityProvider;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class CapabilityHandler {
/*  public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
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
  }*/

  public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof SquidEntity) {
      event.addCapability(AnimalCooldownCapabilityProvider.IDENTIFIER, new AnimalCooldownCapabilityProvider());
/*    } else if (event.getObject() instanceof PlayerEntity) {
      event.addCapability(PlayerShoulderCapabilityProvider.IDENTIFIER, new PlayerShoulderCapabilityProvider());*/
    }
  }

  public static void onSquidMilked(PlayerInteractEvent.EntityInteract event) {
    PlayerEntity player = (PlayerEntity) event.getEntity();
    ItemStack heldItem = player.getItemInHand(event.getHand());
    if (!heldItem.isEmpty() && heldItem.getItem() instanceof GlassBottleItem) {
      if (event.getTarget() instanceof SquidEntity) {
        event.setCanceled(true);
        event.setCancellationResult(ActionResultType.SUCCESS);
        if (!event.getWorld().isClientSide) {
          event.getTarget().getCapability(AnimalCooldownCapabilityProvider.ANIMAL_COOLDOWN_CAPABILITY).ifPresent(cap -> {
            if (cap.canHarvest()) {
              cap.setCooldown(20 * 15);
              event.getWorld().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.SQUID_MILK.get(), SoundCategory.PLAYERS, 0.5F, event.getWorld().random.nextFloat() * 0.25F + 0.6F, true);
              if (!player.isCreative()) {
                heldItem.shrink(1);
              }
              player.inventory.add(new ItemStack(ModItems.INK_BOTTLE.get()));
            } else {
              player.displayClientMessage(new TranslationTextComponent("message.squid.cooldown").setStyle(Style.EMPTY.withColor(Color.fromLegacyFormat(TextFormatting.BLUE)).withBold(true)), true);
            }
          });
        }
      }
    }
  }
}
