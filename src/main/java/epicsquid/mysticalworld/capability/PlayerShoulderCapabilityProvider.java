package epicsquid.mysticalworld.capability;

import epicsquid.mysticallib.MysticalLib;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerShoulderCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<NBTTagCompound> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalLib.MODID, "player_shoulder_capability");

  @CapabilityInject(PlayerShoulderCapability.class)
  public static final Capability<PlayerShoulderCapability> PLAYER_SHOULDER_CAPABILITY = injected();

  private final PlayerShoulderCapability instance = PLAYER_SHOULDER_CAPABILITY.getDefaultInstance();

  @Override
  public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
    return capability == PLAYER_SHOULDER_CAPABILITY;
  }

  @Nullable
  @Override
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    return capability == PLAYER_SHOULDER_CAPABILITY ? PLAYER_SHOULDER_CAPABILITY.cast(this.instance) : null;
  }

  @Override
  public NBTTagCompound serializeNBT() {
    return (NBTTagCompound) PLAYER_SHOULDER_CAPABILITY.getStorage().writeNBT(PLAYER_SHOULDER_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    PLAYER_SHOULDER_CAPABILITY.getStorage().readNBT(PLAYER_SHOULDER_CAPABILITY, this.instance, null, nbt);
  }

  //This is here to get rid of all the ugly PLAYER_GROVE might be null
  private static final Object NULL = null;

  @SuppressWarnings("unchecked")
  private static <T> T injected() {
    return (T) NULL;
  }
}
