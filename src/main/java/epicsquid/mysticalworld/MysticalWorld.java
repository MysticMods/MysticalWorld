package epicsquid.mysticalworld;

import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = MysticalWorld.MODID, version = MysticalWorld.VERSION, name = MysticalWorld.NAME, dependencies = MysticalWorld.DEPENDENCIES)
public class MysticalWorld {
  public static final String MODID = "mysticalworld";
  public static final String DOMAIN = "mysticalworld";
  public static final String NAME = "Mystical World";
  public static final String VERSION = "@VERSION@";
  // Change the dependencies in gradle.properties!
  public static final String DEPENDENCIES = "after:maindependencies";

  public static ModContainer CONTAINER = null;

  @SidedProxy(clientSide = "epicsquid.mysticalworld.proxy.ClientProxy", serverSide = "epicsquid.mysticalworld.proxy.CommonProxy")
  public static CommonProxy proxy;

  public static Logger logger;

  @Instance(MODID)
  public static MysticalWorld instance;

  public static CreativeTabs tab = new CreativeTabs("mysticalworld") {
    @Override
    public String getTabLabel() {
      return "mysticalworld";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon() {
      return new ItemStack(ModItems.carapace, 1);
    }
  };

  public MysticalWorld() {
    ModModifiers.load();
  }

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    CONTAINER = Loader.instance().activeModContainer();
    MinecraftForge.EVENT_BUS.register(new RegistryManager());
    CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
    CapabilityManager.INSTANCE.register(PlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);
    logger = event.getModLog();
    proxy.preInit(event);
  }

  public static MysticalWorld getInstance() {
    return instance;
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    proxy.init(event);
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    proxy.postInit(event);
  }

  @EventHandler
  public void loadComplete(FMLLoadCompleteEvent event) {
    proxy.loadComplete(event);
  }

  @EventHandler
  public void serverStarted (FMLServerStartedEvent event) {
    proxy.serverStarted(event);
  }
}
