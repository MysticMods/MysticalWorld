package epicsquid.mysticalworld.proxy;

import epicsquid.mysticalworld.entity.model.ModelHolder;
import epicsquid.mysticalworld.integration.jer.JERIntegration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
  @Override
  public void preInit(FMLPreInitializationEvent event) {
    super.preInit(event);
    ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new ModelHolder());
    ModelHolder.init();
  }

  @Override
  public void init(FMLInitializationEvent event) {
    super.init(event);
     if (Loader.isModLoaded("jeresources")) {
      JERIntegration.init();
    }
  }

  @Override
  public void postInit(FMLPostInitializationEvent event) {
    super.postInit(event);
  }
}
