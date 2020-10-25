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

public class AnimalCooldownCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<NBTTagCompound> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalLib.MODID, "new_animal_cooldown_capability");

  @CapabilityInject(AnimalCooldownCapability.class)
  public static final Capability<AnimalCooldownCapability> ANIMAL_COOLDOWN_CAPABILITY = injected();

  private final AnimalCooldownCapability instance = ANIMAL_COOLDOWN_CAPABILITY.getDefaultInstance();

  @Override
  public NBTTagCompound serializeNBT() {
    return (NBTTagCompound) ANIMAL_COOLDOWN_CAPABILITY.getStorage().writeNBT(ANIMAL_COOLDOWN_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    ANIMAL_COOLDOWN_CAPABILITY.getStorage().readNBT(ANIMAL_COOLDOWN_CAPABILITY, this.instance, null, nbt);
  }

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return capability == ANIMAL_COOLDOWN_CAPABILITY;
  }

  @Override
  public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
    return capability == ANIMAL_COOLDOWN_CAPABILITY ? ANIMAL_COOLDOWN_CAPABILITY.cast(this.instance) : null;
  }

  //This is here to get rid of all the ugly PLAYER_GROVE might be null
  private static final Object NULL = null;

  @SuppressWarnings("unchecked")
  private static <T> T injected() {
    return (T) NULL;
  }
}
