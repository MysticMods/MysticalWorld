package epicsquid.mysticalworld.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class Capabilities {
  @CapabilityInject(IPlayerShoulderCapability.class)
  public static Capability<IPlayerShoulderCapability> SHOULDER_CAPABILITY = null;
}
