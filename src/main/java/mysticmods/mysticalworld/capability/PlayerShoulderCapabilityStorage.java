package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerShoulderCapabilityStorage implements Capability.IStorage<IPlayerShoulderCapability> {
  @Nullable
  @Override
  public Tag writeNBT(Capability<IPlayerShoulderCapability> capability, IPlayerShoulderCapability instance, Direction side) {
    return instance.writeNBT();
  }

  @Override
  public void readNBT(Capability<IPlayerShoulderCapability> capability, IPlayerShoulderCapability instance, Direction side, Tag nbt) {
    if (nbt instanceof CompoundTag) {
      instance.readNBT((CompoundTag) nbt);
    }
  }
}
