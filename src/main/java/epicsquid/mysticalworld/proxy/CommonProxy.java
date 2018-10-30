package epicsquid.mysticalworld.proxy;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.world.OreGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
    ModItems.registerOredict();
    GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
  }

  public void init(FMLInitializationEvent event) {
  }

  public void postInit(FMLPostInitializationEvent event) {
  }
}
