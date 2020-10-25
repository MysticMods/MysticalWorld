package epicsquid.mysticalworld.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class PlayerShoulderCapability {
  private NBTTagCompound animalSerialized = new NBTTagCompound();
  private boolean shouldered = false;
  private ResourceLocation registryName = null;

  public PlayerShoulderCapability() {
  }

  public NBTTagCompound getAnimalSerialized() {
    return animalSerialized;
  }

  public boolean isShouldered() {
    return shouldered;
  }

  public ResourceLocation getRegistryName() {
    return registryName;
  }

  public void drop() {
    this.animalSerialized = new NBTTagCompound();
    this.shouldered = false;
    this.registryName = null;
  }

  public void shoulder(Entity entity) {
    this.animalSerialized = entity.writeToNBT(new NBTTagCompound());
    this.shouldered = true;
    this.registryName = EntityList.getKey(entity);
  }

  public NBTTagCompound writeNBT() {
    NBTTagCompound result = new NBTTagCompound();
    result.setTag("animalSerialized", animalSerialized);
    result.setBoolean("shouldered", shouldered);
    result.setString("registryName", registryName == null ? "" : registryName.toString());
    return result;
  }

  public void readNBT(NBTBase incoming) {
    if (incoming instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) incoming;
      if (tag.hasKey("animalSerialized")) {
        this.animalSerialized = tag.getCompoundTag("animalSerialized");
      }
      if (tag.hasKey("shouldered")) {
        this.shouldered = tag.getBoolean("shouldered");
      }
      if (tag.hasKey("registryName")) {
        this.registryName = tag.getString("registryName").isEmpty() ? null : new ResourceLocation(tag.getString("registryName"));
      }
    }
  }
}
