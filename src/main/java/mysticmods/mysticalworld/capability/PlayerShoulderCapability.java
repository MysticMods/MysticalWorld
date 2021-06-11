package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class PlayerShoulderCapability implements IPlayerShoulderCapability {
  public static MethodHandle setRightShoulder = null;

  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    Method setRight = ObfuscationReflectionHelper.findMethod(PlayerEntity.class, "func_192029_h", CompoundNBT.class);
    setRight.setAccessible(true);
    try {
      setRightShoulder = lookup.unreflect(setRight);
    } catch (IllegalAccessException e) {
      MysticalWorld.LOG.error("Unable to unprotect setRightShoulder", e);
    }
  }

  private CompoundNBT animalSerialized = new CompoundNBT();
  private boolean shouldered = false;
  private ResourceLocation registryName = null;

  public PlayerShoulderCapability() {
  }

  @Override
  public CompoundNBT getAnimalSerialized() {
    return animalSerialized;
  }

  @Override
  public boolean isShouldered() {
    return shouldered;
  }

  @Override
  public ResourceLocation getRegistryName() {
    return registryName;
  }

  @Override
  @Nullable
  public EntityType<?> getEntityType(ResourceLocation registryName) {
    return ForgeRegistries.ENTITIES.getValue(registryName);
  }

  @Override
  @Nullable
  public EntityType<?> getEntityType() {
    return getEntityType(getRegistryName());
  }

  @Override
  public void drop() {
    this.animalSerialized = new CompoundNBT();
    this.shouldered = false;
    this.registryName = null;
  }

  @Override
  public void shoulder(Entity entity) {
    this.animalSerialized = new CompoundNBT();
    entity.writeUnlessPassenger(this.animalSerialized);
    this.shouldered = true;
    this.registryName = entity.getType().getRegistryName();
  }

  @Override
  public CompoundNBT writeNBT() {
    CompoundNBT result = new CompoundNBT();
    result.put("animalSerialized", animalSerialized);
    result.putBoolean("shouldered", shouldered);
    result.putString("registryName", registryName == null ? "" : registryName.toString());
    return result;
  }

  @Override
  public CompoundNBT generateShoulderNBT() {
    CompoundNBT result = new CompoundNBT();
    result.putBoolean("Silent", true);
    result.putString("id", registryName == null ? "minecraft:pig" : registryName.toString());
    return result;
  }

  @Override
  public void readNBT(CompoundNBT incoming) {
    CompoundNBT tag = incoming;
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
