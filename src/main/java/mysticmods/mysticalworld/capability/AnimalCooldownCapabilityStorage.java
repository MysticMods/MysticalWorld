package mysticmods.mysticalworld.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class AnimalCooldownCapabilityStorage implements Capability.IStorage<AnimalCooldownCapability> {
  @Nullable
  @Override
  public Tag writeNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, Direction side) {
    return LongTag.valueOf(instance.getCooldown());
  }

  @Override
  public void readNBT(Capability<AnimalCooldownCapability> capability, AnimalCooldownCapability instance, Direction side, Tag nbt) {
    if (nbt.getId() == Constants.NBT.TAG_LONG) {
      instance.setActualCooldown(((LongTag) nbt).getAsLong());
    } else {
      instance.setActualCooldown(0);
    }
  }
}
