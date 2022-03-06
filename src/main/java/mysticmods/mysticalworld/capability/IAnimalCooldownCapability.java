package mysticmods.mysticalworld.capability;

import net.minecraft.world.level.Level;
import net.minecraftforge.server.ServerLifecycleHooks;

public interface IAnimalCooldownCapability {
  boolean canHarvest();
  int getExpiry ();
  void setCooldown(int cooldown);
  void setExpiry (int expiry);
}
