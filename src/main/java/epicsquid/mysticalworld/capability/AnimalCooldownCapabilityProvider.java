package epicsquid.mysticalworld.capability;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AnimalCooldownCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<LongNBT> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalWorld.MODID, "animal_cooldown_capability");

  @CapabilityInject(AnimalCooldownCapability.class)
  public static final Capability<AnimalCooldownCapability> ANIMAL_COOLDOWN_CAPABILITY = injected();

  private final AnimalCooldownCapability instance = ANIMAL_COOLDOWN_CAPABILITY.getDefaultInstance();

  @Override
  public LongNBT serializeNBT() {
    return (LongNBT) ANIMAL_COOLDOWN_CAPABILITY.getStorage().writeNBT(ANIMAL_COOLDOWN_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(LongNBT nbt) {
    ANIMAL_COOLDOWN_CAPABILITY.getStorage().readNBT(ANIMAL_COOLDOWN_CAPABILITY, this.instance, null, nbt);
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
    return cap == ANIMAL_COOLDOWN_CAPABILITY ? LazyOptional.of(() -> (T) this.instance) : LazyOptional.empty();
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    return getCapability(cap);
  }

  //This is here to get rid of all the ugly PLAYER_GROVE might be null
  private static final Object NULL = null;

  @SuppressWarnings("unchecked")
  private static <T> T injected() {
    return (T) NULL;
  }
}
