package mysticmods.mysticalworld.api;

public interface IAnimalCooldownCapability {
  long getExpiry();

  void setExpiry(long value);

  void setCooldown(int value);

  boolean hasExpired();
}
