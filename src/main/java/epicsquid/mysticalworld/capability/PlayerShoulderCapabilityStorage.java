package epicsquid.mysticalworld.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerShoulderCapabilityStorage implements Capability.IStorage<PlayerShoulderCapability> {
  @Nullable
  @Override
  public NBTBase writeNBT(Capability<PlayerShoulderCapability> capability, PlayerShoulderCapability instance, EnumFacing side) {
    return instance.writeNBT();
  }

  @Override
  public void readNBT(Capability<PlayerShoulderCapability> capability, PlayerShoulderCapability instance, EnumFacing side, NBTBase nbt) {
    instance.readNBT(nbt);
  }
}
