package mysticmods.mysticalworld.capability;

public interface IAnimalCooldownCapability {
  long getExpiry();

  void setExpiry(long value);

  void setCooldown(int value);

  boolean hasExpired();
}
