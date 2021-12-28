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
  public static MethodHandle setLeftShoulder = null;

  static {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    Method setLeft = ObfuscationReflectionHelper.findMethod(PlayerEntity.class, "func_192029_h", CompoundNBT.class);

    setLeft.setAccessible(true);
    Method setRight = ObfuscationReflectionHelper.findMethod(PlayerEntity.class, "func_192031_i", CompoundNBT.class);
    try {
      setLeftShoulder = lookup.unreflect(setLeft);
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
    entity.save(this.animalSerialized);
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
    if (incoming.contains("animalSerialized")) {
      this.animalSerialized = incoming.getCompound("animalSerialized");
    }
    if (incoming.contains("shouldered")) {
      this.shouldered = incoming.getBoolean("shouldered");
    }
    if (incoming.contains("registryName")) {
      this.registryName = incoming.getString("registryName").isEmpty() ? null : new ResourceLocation(incoming.getString("registryName"));
    }
  }
}
