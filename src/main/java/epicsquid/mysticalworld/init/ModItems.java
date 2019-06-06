package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemFoodBase;
import epicsquid.mysticallib.item.ItemKnifeBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.integration.roots.Knives;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

  // All mod items
  public static Item iron_dust, iron_dust_tiny, gold_dust, gold_dust_tiny, carapace, pelt, antlers, venison, cooked_venison;

  public static Item amethyst_knife, copper_knife, silver_knife;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {
    Item.ToolMaterial copper = EnumHelper.addToolMaterial("mysticalworld:copper", 2, 200, 4.0f, 2.0f, 7);
    Item.ToolMaterial silver = EnumHelper.addToolMaterial("mysticalworld:silver", 2, 175, 6.0f, 2.5f, 18);
    Item.ToolMaterial amethyst = EnumHelper.addToolMaterial("mysticalworld:amethyst", 3, 1561, 8.0f, 3.0f, 12);
    MaterialTypes.addMaterial("mysticalworld:copper", copper, 1.5f, -1.5f);
    MaterialTypes.addMaterial("mysticalworld:silver", silver, 1.5f, -1.0f);
    MaterialTypes.addMaterial("mysticalworld:amethyst", amethyst, 2f, -1.0f);

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
      event.addItem(venison = new ItemFoodBase("venison", 3, true).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(cooked_venison = new ItemFoodBase("cooked_venison", 7, true).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    }

    if (!Loader.isModLoaded("roots") || !Knives.initKnives(event)) {
      event.addItem(amethyst_knife = new ItemKnifeBase("amethyst_knife", MaterialTypes.material("mysticalworld:amethyst")).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(copper_knife = new ItemKnifeBase("copper_knife", MaterialTypes.material("mysticalworld:copper")).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addItem(silver_knife = new ItemKnifeBase("silver_knife", MaterialTypes.material("mysticalworld:silver")).setModelCustom(true).setCreativeTab(MysticalWorld.tab));
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
