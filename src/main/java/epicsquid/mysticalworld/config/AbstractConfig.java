package epicsquid.mysticalworld.config;

import net.minecraftforge.common.ForgeConfigSpec;

public abstract class AbstractConfig {
  public AbstractConfig() {
    ConfigManager.CONFIGS.add(this);
  }

  public abstract void apply(ForgeConfigSpec.Builder builder);

  public abstract void reset ();
}
