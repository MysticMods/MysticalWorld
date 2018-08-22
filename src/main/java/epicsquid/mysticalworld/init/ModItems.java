package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {

  // All mod items
  public static Item carapace, pelt, moonglow_seed, moonglow_leaf, aubergine, aubergine_seed, pereskia_bulb, pereskia, terra_moss_seed, terra_moss, spirit_herb,
      spirit_herb_seed, wildroot, copper_ingot, tin_ingot, silver_ingot, lead_ingot, nickel_ingot, alu_ingot, zinc_ingot, invar_ingot, electrum_ingot,
      brass_ingot, bronze_ingot, dawnstone_ingot, sooty_iron_ingot;

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {

    // Mob Drops
    event.addItem(carapace = new ItemBase("carapace").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(pelt = new ItemBase("pelt").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    // Crops
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

    // Metals
    event.addItem(copper_ingot = new ItemBase("copper_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(tin_ingot = new ItemBase("tin_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(silver_ingot = new ItemBase("silver_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(lead_ingot = new ItemBase("lead_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(nickel_ingot = new ItemBase("nickel_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(alu_ingot = new ItemBase("aluminium_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(zinc_ingot = new ItemBase("zinc_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(invar_ingot = new ItemBase("invar_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(electrum_ingot = new ItemBase("electrum_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(brass_ingot = new ItemBase("brass_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(bronze_ingot = new ItemBase("bronze_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    // Embers Alloys
    event.addItem(dawnstone_ingot = new ItemBase("dawnstone_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addItem(sooty_iron_ingot = new ItemBase("sooty_iron_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
    OreDictionary.registerOre("ingotCopper", copper_ingot);
    OreDictionary.registerOre("ingotTin", tin_ingot);
    OreDictionary.registerOre("ingotSilver", silver_ingot);
    OreDictionary.registerOre("ingotLead", lead_ingot);
    OreDictionary.registerOre("ingotNickel", nickel_ingot);
    OreDictionary.registerOre("ingotAluminium", alu_ingot);
    OreDictionary.registerOre("ingotAluminum", alu_ingot);
    OreDictionary.registerOre("ingotZinc", zinc_ingot);
    OreDictionary.registerOre("ingotInvar", invar_ingot);
    OreDictionary.registerOre("ingotElectrum", electrum_ingot);
    OreDictionary.registerOre("ingotBrass", brass_ingot);
    OreDictionary.registerOre("ingotBronze", bronze_ingot);
    OreDictionary.registerOre("ingotDawnstone", dawnstone_ingot);
    OreDictionary.registerOre("ingotSootyIron", sooty_iron_ingot);
  }
}
