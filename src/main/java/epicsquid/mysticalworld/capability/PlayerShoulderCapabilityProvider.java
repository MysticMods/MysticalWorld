package epicsquid.mysticalworld.capability;

import epicsquid.mysticallib.MysticalLib;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerShoulderCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalLib.MODID, "player_shoulder_capability");

  @CapabilityInject(PlayerShoulderCapability.class)
  public static final Capability<PlayerShoulderCapability> PLAYER_SHOULDER_CAPABILITY = injected();

  private final PlayerShoulderCapability instance = PLAYER_SHOULDER_CAPABILITY.getDefaultInstance();

  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
    return capability == PLAYER_SHOULDER_CAPABILITY ? LazyOptional.of(() -> this.instance).cast() : LazyOptional.empty();
  }

  @Override
  public CompoundNBT serializeNBT() {
    return (CompoundNBT) PLAYER_SHOULDER_CAPABILITY.getStorage().writeNBT(PLAYER_SHOULDER_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    PLAYER_SHOULDER_CAPABILITY.getStorage().readNBT(PLAYER_SHOULDER_CAPABILITY, this.instance, null, nbt);
  }

  //This is here to get rid of all the ugly PLAYER_GROVE might be null
  private static final Object NULL = null;

  @SuppressWarnings("unchecked")
  private static <T> T injected() {
    return (T) NULL;
  }
}
