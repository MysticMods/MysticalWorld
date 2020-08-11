package epicsquid.mysticalworld.capability;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.Capabilities;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerShoulderCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalWorld.MODID, "player_shoulder_capability");

  private final IPlayerShoulderCapability instance = Capabilities.SHOULDER_CAPABILITY.getDefaultInstance();

  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
    return capability == Capabilities.SHOULDER_CAPABILITY ? LazyOptional.of(() -> this.instance).cast() : LazyOptional.empty();
  }

  @Override
  public CompoundNBT serializeNBT() {
    return (CompoundNBT) Capabilities.SHOULDER_CAPABILITY.getStorage().writeNBT(Capabilities.SHOULDER_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    Capabilities.SHOULDER_CAPABILITY.getStorage().readNBT(Capabilities.SHOULDER_CAPABILITY, this.instance, null, nbt);
  }
}
