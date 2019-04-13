package epicsquid.mysticalworld.proxy;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.integration.jer.JERIntegration;
import epicsquid.mysticalworld.world.BarrowGenerator;
import epicsquid.mysticalworld.world.OreGenerator;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
    GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
  }

  public void init(FMLInitializationEvent event) {
    ModItems.registerOredict();
    if (Loader.isModLoaded("jeresources")) {
      JERIntegration.init();
    }

    int barrowWeight = ConfigManager.BarrowWeight;
    if (barrowWeight != 0) {
      GameRegistry.registerWorldGenerator(new BarrowGenerator(), barrowWeight);
    }
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
