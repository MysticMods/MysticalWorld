package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.Capabilities;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.network.ShoulderRide;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class ShoulderHandler {
  // Temporarily disabled
  public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    PlayerEntity player = event.getPlayer();
    World world = event.getWorld();
    if (!world.isAirBlock(event.getPos().up())) {
      return;
    }

    if (!world.isRemote && event.getHand() == Hand.MAIN_HAND && player.isSneaking() && player.getHeldItemMainhand().isEmpty()) {
      if (!world.getBlockState(event.getPos()).isAir(world, event.getPos())) {
        return;
      }
      LazyOptional<IPlayerShoulderCapability> laycap = player.getCapability(Capabilities.SHOULDER_CAPABILITY);
      if (laycap.isPresent()) {
        IPlayerShoulderCapability cap = laycap.orElseThrow(IllegalStateException::new);
        if (cap.isShouldered()) {
          EntityType<?> type = ForgeRegistries.ENTITIES.getValue(cap.getRegistryName());
          if (type != null) {
            Entity animal = type.create(world);
            if (animal != null) {
              animal.read(cap.getAnimalSerialized());
              BlockPos pos = event.getPos();
              animal.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
              world.addEntity(animal);
              player.swingArm(Hand.MAIN_HAND);
              cap.drop();
              try {
                PlayerShoulderCapability.setRightShoulder.invokeExact(player, new CompoundNBT());
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

  public static void onDeath(LivingDeathEvent event) {
    LivingEntity living = event.getEntityLiving();
    if (living instanceof PlayerEntity) {
      PlayerEntity player = (PlayerEntity) living;
      World world = player.world;
      LazyOptional<IPlayerShoulderCapability> laycap = player.getCapability(Capabilities.SHOULDER_CAPABILITY, null);
      if (laycap.isPresent()) {
        IPlayerShoulderCapability cap = laycap.orElseThrow(IllegalStateException::new);
        if (cap.isShouldered()) {
          EntityType<?> type = ForgeRegistries.ENTITIES.getValue(cap.getRegistryName());
          if (type != null) {
            Entity animal = type.create(world);
            if (animal != null) {
              animal.read(cap.getAnimalSerialized());
              BlockPos pos = player.getPosition();
              animal.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
              world.addEntity(animal);
              player.swingArm(Hand.MAIN_HAND);
              cap.drop();
              try {
                PlayerShoulderCapability.setRightShoulder.invokeExact(player, new CompoundNBT());
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
