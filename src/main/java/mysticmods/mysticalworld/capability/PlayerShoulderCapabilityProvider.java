package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.api.Capabilities;
import mysticmods.mysticalworld.api.IPlayerShoulderCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// TODO: Update to the new capability system
public class PlayerShoulderCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalWorld.MODID, "player_shoulder_capability");

  private IPlayerShoulderCapability instance;

  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
    return capability == Capabilities.SHOULDER_CAPABILITY ? LazyOptional.of(() -> this.instance).cast() : LazyOptional.empty();
  }

  @Override
  public CompoundTag serializeNBT() {
    return null;
    /*    return (CompoundTag) Capabilities.SHOULDER_CAPABILITY.getStorage().writeNBT(Capabilities.SHOULDER_CAPABILITY, this.instance, null);*/
  }

  @Override
  public void deserializeNBT(CompoundTag nbt) {
    /*    Capabilities.SHOULDER_CAPABILITY.getStorage().readNBT(Capabilities.SHOULDER_CAPABILITY, this.instance, null, nbt);*/
  }
}
