package epicsquid.mysticalworld;

import com.tterrag.registrate.util.nullness.NonNullSupplier;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.events.LeafHandler;
import epicsquid.mysticalworld.events.MaskHandler;
import epicsquid.mysticalworld.events.global.GrassHandler;
import epicsquid.mysticalworld.events.mappings.Remaps;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.setup.ClientSetup;
import epicsquid.mysticalworld.setup.ModSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mysticalworld")
public class MysticalWorld {
/*  public static OreFeatureConfig.FillerBlockType ORE_GEN = OreFeatureConfig.FillerBlockType.create("mysticalworld", "mysticalworld", o -> q -> OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD.test(q).or(OreFeatureConfig.FillerBlockType.NETHERRACK.getTargetBlockPredicate()).or(q -> {
    if (q == null) {
      return false;
    }

    return Tags.Blocks.END_STONES.contains(q.getBlock());
  }).test(o));*/

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

  public static ModSetup setup = new ModSetup();

  public MysticalWorld() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

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

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
      modBus.addListener(ClientSetup::init);
      //MinecraftForge.EVENT_BUS.addListener(TooltipHandler::onTooltip);
    });


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

    setup.registerListeners();

    modBus.addListener(ConfigManager.HAT_CONFIG::onConfigReload);
  }
}
