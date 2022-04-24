package mysticmods.mysticalworld.capability;

import mysticmods.mysticalworld.init.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.LongTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimalCooldownCapability implements IAnimalCooldownCapability, ICapabilitySerializable<LongTag>, ICapabilityProvider {
  private long expiry = -1;

  @Override
  public long getExpiry() {
    return expiry;
  }

  @Override
  public void setExpiry(long value) {
    this.expiry = value;
  }

  @Override
  public void setCooldown(int value) {
    this.expiry = getGameTime() + value;
  }

  private long getGameTime() {
    Level overworld = ServerLifecycleHooks.getCurrentServer().getLevel(Level.OVERWORLD);
    if (overworld != null) {
      return overworld.getGameTime();
    }

    return 0;
  }

  @Override
  public boolean hasExpired() {
    return getGameTime() >= expiry;
  }

  @NotNull
  @Override
  public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
    return ModCapabilities.ANIMAL_COOLDOWN.orEmpty(cap, LazyOptional.of(() -> this));
  }

  @Override
  public LongTag serializeNBT() {
    return LongTag.valueOf(expiry);
  }

  @Override
  public void deserializeNBT(LongTag nbt) {
    this.expiry = nbt.getAsLong();
  }
}
