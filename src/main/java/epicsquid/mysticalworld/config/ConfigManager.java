package epicsquid.mysticalworld.config;

import epicsquid.mysticalworld.MysticalWorld;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

@Config(modid = MysticalWorld.MODID)
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class ConfigManager {

  @Config.Comment(("Mystical world content modules. These modules are applied before any other config options."))
  public static ConfigMysticalWorldModules modules = new ConfigMysticalWorldModules();

  public static class ConfigMysticalWorldModules {

    @Config.Comment(("Enable Roots Content"))
    public boolean rootsModuleEnabled = true;

    @Config.Comment(("Enable Embers Content"))
    public boolean embersModuleEnabled = true;

    @Config.Comment(("Enable Mystical World Content"))
    public boolean mysticalWorldModuleEnabled = true;
  }

  @Config.Comment(("Mystical world metals and their components."))
  public static ConfigMysticalWorldMetals metals = new ConfigMysticalWorldMetals();

  public static class ConfigMysticalWorldMetals {

    @Config.Comment(("Enable Tiny Dusts for Metals"))
    public boolean enableTinyDusts = true;

    @Config.Comment(("Enable Dusts for Metals"))
    public boolean enableDusts = true;

    @Config.Comment(("Enable Ores for Metals"))
    public boolean enableOres = true;

    @Config.Comment(("Enable Blocks for Metals"))
    public boolean enableBlocks = true;

    @Config.Comment(("Enable Ingots for Metals"))
    public boolean enableIngots = true;

    @Config.Comment(("Enable Nuggets for Metals"))
    public boolean enableNuggets = true;

    @Config.Comment(("Enable Copper"))
    public boolean enableCopper = true;

    @Config.Comment(("Enable Tin"))
    public boolean enableTin = true;

    @Config.Comment(("Enable Silver"))
    public boolean enableSilver = true;

    @Config.Comment(("Enable Lead"))
    public boolean enableLead = true;

    @Config.Comment(("Enable Nickel"))
    public boolean enableNickel = true;

    @Config.Comment(("Enable Aluminum"))
    public boolean enableAluminum = true;

    @Config.Comment(("Enable Zinc"))
    public boolean enableZinc = true;

    @Config.Comment(("Enable Invar"))
    public boolean enableInvar = true;

    @Config.Comment(("Enable Electrum"))
    public boolean enableElectrum = true;

    @Config.Comment(("Enable Brass"))
    public boolean enableBrass = true;

    @Config.Comment(("Enable Bronze"))
    public boolean enableBronze = true;

    @Config.Comment(("Enable Dawnstone"))
    public boolean enableDawnstone = true;

    @Config.Comment(("Enable Sooty Iron"))
    public boolean enableSootyIron = true;
  }
}
