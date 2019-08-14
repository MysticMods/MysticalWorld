package epicsquid.mysticalworld.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerShoulderCapability {
  private NBTTagCompound animalSerialized = new NBTTagCompound();
  private boolean shouldered = false;
  private Class<? extends Entity> clazz = null;
  boolean dirty = false;

  public PlayerShoulderCapability() {
  }

  public boolean isDirty() {
    return dirty;
  }

  public void markDirty() {
    this.dirty = true;
  }

  public void clean () {
    this.dirty = false;
  }

  public NBTTagCompound getAnimalSerialized () {
    return animalSerialized;
  }

  public boolean isShouldered () {
    return shouldered;
  }

  public Class<? extends Entity> getClazz () {
    return clazz;
  }

  public void drop () {
    this.animalSerialized = new NBTTagCompound();
    this.shouldered = false;
    this.clazz = null;
  }

  public void shoulder (Entity entity) {
    this.animalSerialized = entity.writeToNBT(new NBTTagCompound());
    this.shouldered = true;
    this.clazz = entity.getClass();
  }

  public NBTTagCompound writeNBT () {
    NBTTagCompound result = new NBTTagCompound();
    result.setTag("animalSerialized", animalSerialized);
    result.setBoolean("shouldered", shouldered);
    String clazz = "null";
    if (this.clazz != null) {
      clazz = this.clazz.toString();
    }
    result.setString("class", clazz);
    return result;
  }

  @SuppressWarnings("unchecked")
  public void readNBT (NBTBase incoming) {
    if (incoming instanceof NBTTagCompound) {
      NBTTagCompound tag = (NBTTagCompound) incoming;
      if (tag.hasKey("animalSerialized")) {
        this.animalSerialized = tag.getCompoundTag("animalSerialized");
      }
      if (tag.hasKey("shouldered")) {
        this.shouldered = tag.getBoolean("shouldered");
      }
      if (tag.hasKey("class")) {
        String className = tag.getString("class");
        if (className.equals("null")) {
          this.clazz = null;
        } else {
          try {
            this.clazz = (Class<? extends Entity>) Class.forName(className);
          } catch (ClassNotFoundException e) {
            this.clazz = null;
          }
        }
      }
    }
  }
}
