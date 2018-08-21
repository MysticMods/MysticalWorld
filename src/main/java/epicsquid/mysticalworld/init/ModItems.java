package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ModItems {

  // All mod items
  public static Item carapace, pelt, moonglow_seed, moonglow_leaf, aubergine, aubergine_seed, pereskia_bulb, pereskia, terra_moss_seed, terra_moss, spirit_herb,
      spirit_herb_seed, wildroot;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {
    event.addItem(carapace = new ItemBase("carapace").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pelt = new ItemBase("pelt").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(moonglow_seed = new ItemSeedBase("moonglow_seed", ModBlocks.moonglow, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(moonglow_leaf = new ItemBase("moonglow_leaf").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(aubergine_seed = new ItemSeedBase("aubergine_seed", ModBlocks.aubergine, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(aubergine = new ItemBase("aubergine").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pereskia_bulb = new ItemSeedBase("pereskia_bulb", ModBlocks.pereskia, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pereskia = new ItemBase("pereskia").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(terra_moss_seed = new ItemSeedBase("terra_moss_seed", ModBlocks.terra_moss, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(terra_moss = new ItemBase("terra_moss").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(spirit_herb_seed = new ItemSeedBase("spirit_herb_seed", ModBlocks.spirit_herb, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(spirit_herb = new ItemBase("spirit_herb").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(wildroot = new ItemSeedBase("wildroot", ModBlocks.wildroot, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
  }
}
