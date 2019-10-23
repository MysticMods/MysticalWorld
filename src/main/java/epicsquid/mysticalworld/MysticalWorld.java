package epicsquid.mysticalworld;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModEntities;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.init.ModRegistries;
import epicsquid.mysticalworld.setup.ModSetup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalworld")
public class MysticalWorld {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "mysticalworld";

  public static final ItemGroup ITEM_GROUP = new ItemGroup("mysticalworld") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.CARAPACE.get());
    }
  };

  public static final ItemGroup METAL_ITEM_GROUP = new ItemGroup("mysticalworld.metals") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.SILVER_INGOT.get());
    }
  };

  public static ModSetup setup = new ModSetup();

  public MysticalWorld() {
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    modBus.addListener(setup::init);
    ModItems.init();
    ModBlocks.init();
    ModRegistries.BLOCKS.register(modBus);
    ModRegistries.ITEMS.register(modBus);
    ModRegistries.ENTITIES.register(modBus);
    //modBus.addListener(ModEntities::registerEntities);

    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
  }
}
