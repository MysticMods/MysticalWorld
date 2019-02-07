package epicsquid.mysticalworld;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = MysticalWorld.MODID, version = MysticalWorld.VERSION, name = MysticalWorld.NAME, dependencies = MysticalWorld.DEPENDENCIES)
public class MysticalWorld {
  public static final String MODID = "mysticalworld";
  public static final String DOMAIN = "mysticalworld";
  public static final String NAME = "Mystical World";
  public static final String VERSION = "@VERSION@";
  public static final String DEPENDENCIES = "required-before:mysticallib";

  public static ModContainer CONTAINER = null;

  @SidedProxy(clientSide = "epicsquid.mysticalworld.proxy.ClientProxy", serverSide = "epicsquid.mysticalworld.proxy.CommonProxy")
  public static CommonProxy proxy;

  @Instance(MODID) public static MysticalWorld instance;

  public static CreativeTabs tab = new CreativeTabs("mysticalworld") {
    @Override
    public String getTabLabel() {
      return "mysticalworld";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
      return new ItemStack(ModItems.carapace, 1);
    }
  };

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    CONTAINER = Loader.instance().activeModContainer();
    MinecraftForge.EVENT_BUS.register(new RegistryManager());
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
}
