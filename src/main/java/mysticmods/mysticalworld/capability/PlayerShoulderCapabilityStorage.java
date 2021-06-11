package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerShoulderCapabilityStorage implements Capability.IStorage<IPlayerShoulderCapability> {
  @Nullable
  @Override
  public INBT writeNBT(Capability<IPlayerShoulderCapability> capability, IPlayerShoulderCapability instance, Direction side) {
    return instance.writeNBT();
  }

  @Override
  public void readNBT(Capability<IPlayerShoulderCapability> capability, IPlayerShoulderCapability instance, Direction side, INBT nbt) {
    if (nbt instanceof CompoundNBT) {
      instance.readNBT((CompoundNBT) nbt);
    }
  }
}
