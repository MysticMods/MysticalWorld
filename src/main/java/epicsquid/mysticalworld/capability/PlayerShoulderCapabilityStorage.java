package epicsquid.mysticalworld.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerShoulderCapabilityStorage implements Capability.IStorage<PlayerShoulderCapability> {
  @Nullable
  @Override
  public INBT writeNBT(Capability<PlayerShoulderCapability> capability, PlayerShoulderCapability instance, Direction side) {
    return instance.writeNBT();
  }

  @Override
  public void readNBT(Capability<PlayerShoulderCapability> capability, PlayerShoulderCapability instance, Direction side, INBT nbt) {
    if (nbt instanceof CompoundNBT) {
      instance.readNBT((CompoundNBT) nbt);
    }
  }
}
