package epicsquid.mysticalworld;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.items.ModItems;
import epicsquid.mysticalworld.setup.ModSetup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
      return new ItemStack(ModItems.CARAPACE);
    }
  };

  public static final ItemGroup METAL_ITEM_GROUP = new ItemGroup("mysticalworld.metals") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModItems.SILVER_INGOT);
    }
  };

  public static ModSetup setup = new ModSetup();

  public MysticalWorld() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);

    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
  }
}
