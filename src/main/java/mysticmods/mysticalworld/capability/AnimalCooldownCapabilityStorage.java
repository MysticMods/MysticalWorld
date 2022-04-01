package mysticmods.mysticalworld.capability;

// TODO: This got moved around in 1.17
/*public class AnimalCooldownCapabilityStorage implements Capability.IStorage<AnimalCooldownCapability> {
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
}*/
