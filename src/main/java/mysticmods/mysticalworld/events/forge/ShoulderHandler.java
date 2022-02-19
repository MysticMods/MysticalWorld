package mysticmods.mysticalworld.events.forge;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import mysticmods.mysticalworld.capability.PlayerShoulderCapability;
import mysticmods.mysticalworld.network.Networking;
import mysticmods.mysticalworld.network.ShoulderRide;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ShoulderHandler {
  @SubscribeEvent
  public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    Player player = event.getPlayer();
    Level world = event.getWorld();
    if (!world.isEmptyBlock(event.getPos().above())) {
      return;
    }

    if (!world.isClientSide() && event.getHand() == InteractionHand.MAIN_HAND && player.isCrouching() && player.getMainHandItem().isEmpty()) {
      player.getCapability(Capabilities.SHOULDER_CAPABILITY).ifPresent(cap -> {
        if (cap.isShouldered()) {
          EntityType<?> type = ForgeRegistries.ENTITIES.getValue(cap.getRegistryName());
          if (type != null) {
            Entity animal = type.create(world);
            if (animal != null) {
              animal.load(cap.getAnimalSerialized());
              BlockPos pos = event.getPos();
              animal.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
              world.addFreshEntity(animal);
              player.swing(InteractionHand.MAIN_HAND);
              cap.drop();
              try {
                PlayerShoulderCapability.setLeftShoulder.invokeExact(player, new CompoundTag());
              } catch (Throwable throwable) {
                MysticalWorld.LOG.error("Unable to unset player having a shoulder entity", throwable);
              }
              event.setCanceled(true);
              ShoulderRide message = new ShoulderRide(player, cap);
              Networking.send(PacketDistributor.ALL.noArg(), message);
            }
          }
        }
      });
    }
  }

  @SubscribeEvent
  public static void onDeath(LivingDeathEvent event) {
    LivingEntity living = event.getEntityLiving();
    if (living instanceof Player player) {
      Level world = player.level;
      LazyOptional<IPlayerShoulderCapability> laycap = player.getCapability(Capabilities.SHOULDER_CAPABILITY, null);
      if (laycap.isPresent()) {
        IPlayerShoulderCapability cap = laycap.orElseThrow(IllegalStateException::new);
        if (cap.isShouldered()) {
          EntityType<?> type = ForgeRegistries.ENTITIES.getValue(cap.getRegistryName());
          if (type != null) {
            Entity animal = type.create(world);
            if (animal != null) {
              animal.load(cap.getAnimalSerialized());
              Vec3 pos = player.position();
              animal.setPos(pos.x, pos.y, pos.z);
              world.addFreshEntity(animal);
              player.swing(InteractionHand.MAIN_HAND);
              cap.drop();
              try {
                PlayerShoulderCapability.setLeftShoulder.invokeExact(player, new CompoundTag());
              } catch (Throwable throwable) {
                MysticalWorld.LOG.error("Unable to unset player having a shoulder entity", throwable);
              }
              event.setCanceled(true);
              ShoulderRide message = new ShoulderRide(player, cap);
              Networking.send(PacketDistributor.ALL.noArg(), message);
            }
          }
        }
      }
    }
  }
}
