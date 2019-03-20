package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemFoodBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

  // All mod items
  public static Item iron_dust, iron_dust_tiny, gold_dust, gold_dust_tiny, carapace, pelt, antlers, venison, cooked_venison;

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
      event.addItem(antlers = new ItemBase("antlers").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(venison = new ItemFoodBase("venison", 3, 1.0f, true).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(cooked_venison = new ItemFoodBase("cooked_venison", 7, 1.0f, true).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    }

    if (ConfigManager.modules.rootsModuleEnabled) {
      // Crops
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
}
