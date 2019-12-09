package epicsquid.mysticalworld.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class PlayerShoulderCapability {
  private CompoundNBT animalSerialized = new CompoundNBT();
  private boolean shouldered = false;
  private ResourceLocation registryName = null;

  public PlayerShoulderCapability() {
  }

  public CompoundNBT getAnimalSerialized() {
    return animalSerialized;
  }

  public boolean isShouldered() {
    return shouldered;
  }

  public ResourceLocation getRegistryName() {
    return registryName;
  }

  public void drop() {
    this.animalSerialized = new CompoundNBT();
    this.shouldered = false;
    this.registryName = null;
  }

  public void shoulder(Entity entity) {
    this.animalSerialized = new CompoundNBT();
    entity.writeUnlessPassenger(this.animalSerialized);
    this.shouldered = true;
    this.registryName = entity.getType().getRegistryName();
  }

  public CompoundNBT writeNBT() {
    CompoundNBT result = new CompoundNBT();
    result.put("animalSerialized", animalSerialized);
    result.putBoolean("shouldered", shouldered);
    result.putString("registryName", registryName == null ? "" : registryName.toString());
    return result;
  }

  public void readNBT(CompoundNBT incoming) {
    CompoundNBT tag = (CompoundNBT) incoming;
    if (tag.contains("animalSerialized")) {
      this.animalSerialized = tag.getCompound("animalSerialized");
    }
    if (tag.contains("shouldered")) {
      this.shouldered = tag.getBoolean("shouldered");
    }
    if (tag.contains("registryName")) {
      this.registryName = tag.getString("registryName").isEmpty() ? null : new ResourceLocation(tag.getString("registryName"));
    }
  }
}
