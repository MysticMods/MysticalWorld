package mysticmods.mysticalworld.capability;

public interface IAnimalCooldownCapability {
  boolean canHarvest();
  int getExpiry ();
  void setCooldown(int cooldown);
  void setExpiry (int expiry);
}
