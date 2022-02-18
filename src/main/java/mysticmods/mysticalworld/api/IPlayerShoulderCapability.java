package mysticmods.mysticalworld.api;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import javax.annotation.Nullable;

public interface IPlayerShoulderCapability {
  CompoundTag getAnimalSerialized();

  boolean isShouldered();

  ResourceLocation getRegistryName();

  @Nullable
  EntityType<?> getEntityType(ResourceLocation registryName);

  @Nullable
  EntityType<?> getEntityType();

  void drop();

  void shoulder(Entity entity);

  CompoundTag writeNBT();

  CompoundTag generateShoulderNBT();

  void readNBT(CompoundTag incoming);
}
