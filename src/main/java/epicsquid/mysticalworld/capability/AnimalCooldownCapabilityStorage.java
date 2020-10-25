package epicsquid.mysticalworld.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class AnimalCooldownCapabilityStorage implements Capability.IStorage<AnimalCooldownCapability> {
  @Nullable
  @Override
  public NBTBase writeNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, EnumFacing side) {
    return instance.writeNBT();
  }

  @Override
  public void readNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, EnumFacing side, NBTBase nbt) {
    instance.readNBT(nbt);
  }
}
