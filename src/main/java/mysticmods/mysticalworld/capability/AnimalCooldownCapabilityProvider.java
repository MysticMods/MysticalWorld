package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.nbt.LongTag;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class AnimalCooldownCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<LongTag> {
  public static final ResourceLocation IDENTIFIER = new ResourceLocation(MysticalWorld.MODID, "animal_cooldown_capability");

  @CapabilityInject(AnimalCooldownCapability.class)
  public static final Capability<AnimalCooldownCapability> ANIMAL_COOLDOWN_CAPABILITY = injected();

  private final AnimalCooldownCapability instance = ANIMAL_COOLDOWN_CAPABILITY.getDefaultInstance();

  @Override
  public LongTag serializeNBT() {
    return (LongTag) ANIMAL_COOLDOWN_CAPABILITY.getStorage().writeNBT(ANIMAL_COOLDOWN_CAPABILITY, this.instance, null);
  }

  @Override
  public void deserializeNBT(LongTag nbt) {
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
