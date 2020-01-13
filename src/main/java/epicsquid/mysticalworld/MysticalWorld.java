package epicsquid.mysticalworld;

import epicsquid.mysticallib.registry.ModRegistry;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.events.DamageHandler;
import epicsquid.mysticalworld.events.EntityHandler;
import epicsquid.mysticalworld.events.LeafHandler;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.setup.ClientSetup;
import epicsquid.mysticalworld.setup.ModSetup;
import epicsquid.mysticalworld.setup.ModifyLoot;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.ServerLifecycleEvent;
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

  public static final ModRegistry REGISTRY = new ModRegistry(MODID);

  public static ModSetup setup = new ModSetup();

  public MysticalWorld() {
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    // This is literally to ensure that they static declarations are loaded
    // before we attempt to actually register stuff.
    ModItems.load();
    ModBlocks.load();
    ModEntities.load();
    ModRecipes.load();
    ModModifiers.load();
    ModSounds.load();

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
      modBus.addListener(ClientSetup::init);
    });

    modBus.addListener(setup::init);
    modBus.addListener(setup::gatherData);

    modBus.addGenericListener(EntityType.class, EventPriority.LOWEST, ModEntities::registerEntities);
    modBus.addGenericListener(Item.class, EventPriority.LOWEST, ModItems::registerItems);

    MinecraftForge.EVENT_BUS.addListener(setup::serverStarting);
    MinecraftForge.EVENT_BUS.addListener(setup::serverAboutToStart);

    REGISTRY.registerEventBus(modBus);

    setup.registerListeners();
  }
}
