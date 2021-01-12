package epicsquid.mysticalworld;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.events.LeafHandler;
import epicsquid.mysticalworld.events.MaskHandler;
import epicsquid.mysticalworld.events.global.GrassHandler;
import epicsquid.mysticalworld.events.mappings.Remaps;
import epicsquid.mysticalworld.gen.LootTableGenerator;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.setup.ClientInit;
import epicsquid.mysticalworld.setup.CommonSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.data.RecipeGenerator;
import noobanidus.libs.noobutil.modifiers.PlayerModifierRegistry;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalworld")
public class MysticalWorld {
  public static PlantType STONE_PLANT;

  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "mysticalworld";

  public static CustomRegistrate REGISTRATE;
  public static RecipeGenerator RECIPES = new RecipeGenerator(MODID);

  public static final ItemGroup ITEM_GROUP = new ItemGroup("mysticalworld") {
    @Override
    public ItemStack createIcon() {
      return new ItemStack(ModBlocks.AMETHYST_BLOCK.get());
    }
  };

  public static CommonSetup setup = new CommonSetup();

  public MysticalWorld() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(this::onDataGen);

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(NonNullSupplier.of(() -> ITEM_GROUP));

    // This is literally to ensure that they static declarations are loaded
    // before we attempt to actually register stuff.
    ModLoot.load();
    ModBlocks.load();
    ModItems.load();
    ModEntities.load();
    ModRecipes.load();
    ModModifiers.load();
    ModSounds.load();
    ModEffects.load();
    ModLang.load();
    ModTags.load();
    ModFeatures.load();

    DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientInit::init);

    modBus.addListener(setup::init);
    modBus.addListener(setup::loadComplete);
    modBus.addGenericListener(GlobalLootModifierSerializer.class, GrassHandler::registerModifiers);

    MinecraftForge.EVENT_BUS.addListener(setup::serverStarting);
    MinecraftForge.EVENT_BUS.addListener(setup::serverAboutToStart);
    MinecraftForge.EVENT_BUS.addListener(LeafHandler::onBlockDrops);
    MinecraftForge.EVENT_BUS.addListener(MaskHandler::onAttackEntity);

    MinecraftForge.EVENT_BUS.addGenericListener(Block.class, Remaps::remapBlockEvent);
    MinecraftForge.EVENT_BUS.addGenericListener(Item.class, Remaps::remapItemEvent);
    MinecraftForge.EVENT_BUS.addGenericListener(EntityType.class, Remaps::remapEntityEvent);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::onBiomeLoad);
    MinecraftForge.EVENT_BUS.addListener(ModFeatures::onWorldLoad);

    setup.registerListeners();

    modBus.addListener(ConfigManager::configReload);

    PlayerModifierRegistry.addModifier(ModModifiers.SERENDIPITY);
    PlayerModifierRegistry.addModifier(ModModifiers.BLESSED);
    PlayerModifierRegistry.addModifier(ModModifiers.SMITE);
  }

  public void onDataGen (GatherDataEvent event) {
    if (event.includeServer()) {
      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
    }
  }
}
