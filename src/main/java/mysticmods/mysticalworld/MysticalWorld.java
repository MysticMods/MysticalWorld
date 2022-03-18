package mysticmods.mysticalworld;

import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.deferred.ModSounds;
import mysticmods.mysticalworld.init.deferred.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.modifier.PlayerModifierRegistry;
import noobanidus.libs.noobutil.reference.ModData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalworld")
public class MysticalWorld {
  public static PlantType STONE_PLANT;

  public static final boolean DATA_GEN = true;

  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "mysticalworld";

  public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(MODID) {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get());
    }
  };

  public MysticalWorld() {
    ModData.setIdAndIdentifier(MODID, "MysticalWorld");
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    ModBlocks.register(modBus);
    ModItems.register(modBus);
    ModEffects.register(modBus);
    ModModifiers.register(modBus);
    ModRecipes.register(modBus);
    ModSounds.register(modBus);
    ModEntities.register(modBus);

    PlayerModifierRegistry.addModifier(ModModifiers.SERENDIPITY);
    PlayerModifierRegistry.addModifier(ModModifiers.BLESSED);
    PlayerModifierRegistry.addModifier(ModModifiers.SMITE);
    PlayerModifierRegistry.addModifier(ModModifiers.CARAPAX);
  }
}
