package mysticmods.mysticalworld;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.events.LeafHandler;
import mysticmods.mysticalworld.events.MaskHandler;
import mysticmods.mysticalworld.events.global.GrassHandler;
import mysticmods.mysticalworld.events.mappings.Remaps;
import mysticmods.mysticalworld.gen.LootTableGenerator;
import mysticmods.mysticalworld.gen.PotionTagGenerator;
import mysticmods.mysticalworld.init.*;
import mysticmods.mysticalworld.integration.dynamictrees.DynamicTrees;
import mysticmods.mysticalworld.setup.ClientInit;
import mysticmods.mysticalworld.setup.CommonSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
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
    public ItemStack makeIcon() {
      return new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get());
    }
  };

  public MysticalWorld() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(this::onDataGen);

    REGISTRATE = CustomRegistrate.create(MODID);
    REGISTRATE.itemGroup(NonNullSupplier.of(() -> ITEM_GROUP));

    // This is literally to ensure that they static declarations are loaded
    // before we attempt to actually register stuff.
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

    modBus.addListener(CommonSetup::init);
    modBus.addListener(CommonSetup::loadComplete);
    modBus.addListener(ModEntities::registerAttributes);
    modBus.addGenericListener(GlobalLootModifierSerializer.class, GrassHandler::registerModifiers);

    MinecraftForge.EVENT_BUS.addListener(CommonSetup::serverStarting);
    MinecraftForge.EVENT_BUS.addListener(CommonSetup::serverAboutToStart);
    MinecraftForge.EVENT_BUS.addListener(LeafHandler::onBlockDrops);
    MinecraftForge.EVENT_BUS.addListener(MaskHandler::onAttackEntity);

    MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::onBiomeLoad);
    MinecraftForge.EVENT_BUS.addListener(ModFeatures::onWorldLoad);

    CommonSetup.registerListeners();

    modBus.addListener(ConfigManager::configReload);

    PlayerModifierRegistry.addModifier(ModModifiers.SERENDIPITY);
    PlayerModifierRegistry.addModifier(ModModifiers.BLESSED);
    PlayerModifierRegistry.addModifier(ModModifiers.SMITE);

    if (ModList.get().isLoaded("dynamictrees")) {
      DynamicTrees.init();
    }
  }

  public void onDataGen(GatherDataEvent event) {
    if (event.includeServer()) {
      ModLoot.load();
      ModLoot.CONDITION_REGISTRY.registration();
      ModLoot.FUNCTION_REGISTRY.registration();
      event.getGenerator().addProvider(new LootTableGenerator(event.getGenerator()));
      event.getGenerator().addProvider(new PotionTagGenerator(event.getGenerator(), event.getExistingFileHelper()));
    }
  }
}
