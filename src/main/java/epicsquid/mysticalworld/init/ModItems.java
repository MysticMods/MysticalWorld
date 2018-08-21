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
  public static Item carapace, pelt, moonglow_seed, moonglow_leaf;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {
    event.addItem(carapace = new ItemBase("carapace").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pelt = new ItemBase("pelt").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(moonglow_seed = new ItemSeedBase("moonglow_seed", ModBlocks.moonglow, Blocks.DIRT).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(moonglow_leaf = new ItemBase("moonglow_leaf").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
  }
}
