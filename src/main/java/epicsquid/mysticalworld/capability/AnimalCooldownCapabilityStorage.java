package epicsquid.mysticalworld.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class AnimalCooldownCapabilityStorage implements Capability.IStorage<AnimalCooldownCapability> {
  @Nullable
  @Override
  public INBT writeNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, Direction side) {
    return new LongNBT(instance.getCooldown());
  }

  @Override
  public void readNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, Direction side, INBT nbt) {
    if (nbt.getId() == Constants.NBT.TAG_LONG) {
      instance.setCooldown(((LongNBT) nbt).getLong());
    } else {
      instance.setCooldown(0);
    }
  }
}
