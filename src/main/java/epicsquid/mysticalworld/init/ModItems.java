package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.item.ItemKnife;
import epicsquid.mysticalworld.item.ItemTerraSpore;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

  // All mod items
  public static Item iron_dust, iron_dust_tiny, gold_dust, gold_dust_tiny, carapace, pelt, moonglow_seed, moonglow_leaf, aubergine, aubergine_seed, pereskia_bulb, pereskia, terra_spores, terra_moss, spirit_herb, wildewheet,
      spirit_herb_seed, wildroot, baffle_cap, teapot, bark_oak, bark_birch, bark_spruce, bark_jungle, bark_dark_oak, bark_acacia, wildewheet_seed, cloud_berry, infernal_bulb,
      stalicripe, dewgonia;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {

    // Mob Drops
    event.addItem(carapace = new ItemBase("carapace").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    if (ConfigManager.modules.mysticalWorldModuleEnabled) {
      // Vanilla Metal Dusts
      event.addItem(pelt = new ItemBase("pelt").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(iron_dust = new ItemBase("iron_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(iron_dust_tiny = new ItemBase("iron_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(gold_dust = new ItemBase("gold_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(gold_dust_tiny = new ItemBase("gold_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
//      event.addItem(teapot = new ItemTeapot("teapot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    }

    if (ConfigManager.modules.rootsModuleEnabled) {
      // Crops
      event.addItem(moonglow_seed = new ItemSeedBase("moonglow_seed", ModBlocks.moonglow, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(moonglow_leaf = new ItemBase("moonglow_leaf").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(aubergine_seed = new ItemSeedBase("aubergine_seed", ModBlocks.aubergine, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(aubergine = new ItemBase("aubergine").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(pereskia_bulb = new ItemSeedBase("pereskia_bulb", ModBlocks.pereskia, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(pereskia = new ItemBase("pereskia").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(terra_moss = new ItemBase("terra_moss").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(spirit_herb_seed = new ItemSeedBase("spirit_herb_seed", ModBlocks.spirit_herb, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(spirit_herb = new ItemBase("spirit_herb").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(wildroot = new ItemSeedBase("wildroot", ModBlocks.wildroot, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(wildewheet = new ItemBase("wildewheet").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(wildewheet_seed = new ItemSeedBase("wildewheet_seed", ModBlocks.wildewheet, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(cloud_berry = new ItemSeedBase("cloud_berry", ModBlocks.cloud_berry, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(infernal_bulb = new ItemSeedBase("infernal_bulb", ModBlocks.infernal_bulb, Blocks.MAGMA).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(dewgonia = new ItemSeedBase("dewgonia", ModBlocks.dewgonia, Blocks.SAND).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(stalicripe = new ItemSeedBase("stalicripe", ModBlocks.stalicripe, Blocks.STONE).setModelCustom(true).setCreativeTab(MysticalWorld.tab));

      event.addItem(terra_spores = new ItemTerraSpore("terra_spores").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

      // Barks and Knifes
      event.addItem(bark_oak = new ItemBase("bark_oak").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(bark_spruce = new ItemBase("bark_spruce").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(bark_birch = new ItemBase("bark_birch").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(bark_jungle = new ItemBase("bark_jungle").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(bark_dark_oak = new ItemBase("bark_dark_oak").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(bark_acacia = new ItemBase("bark_acacia").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

      // KEEP AT END
      registerSeedDrops();
    }
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
    if (ConfigManager.modules.mysticalWorldModuleEnabled) {
      if(ConfigManager.metals.enableDusts){
        OreDictionary.registerOre("dustIron", iron_dust);
        OreDictionary.registerOre("dustGold", gold_dust);
      }
      if(ConfigManager.metals.enableTinyDusts){
        OreDictionary.registerOre("dustTinyIron", iron_dust_tiny);
        OreDictionary.registerOre("dustTinyGold", gold_dust_tiny);
      }
    }
  }

  private static void registerSeedDrops() {
    MinecraftForge.addGrassSeed(new ItemStack(ModItems.terra_spores, 1), 5);
    MinecraftForge.addGrassSeed(new ItemStack(ModItems.wildroot, 1), 5);
  }
}
