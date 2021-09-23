package mysticmods.mysticalworld.capability;

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
    return LongNBT.valueOf(instance.getCooldown());
  }

  @Override
  public void readNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, Direction side, INBT nbt) {
    if (nbt.getId() == Constants.NBT.TAG_LONG) {
      instance.setActualCooldown(((LongNBT) nbt).getAsLong());
    } else {
      instance.setActualCooldown(0);
    }
  }
}
