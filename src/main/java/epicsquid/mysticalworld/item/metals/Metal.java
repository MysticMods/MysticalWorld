package epicsquid.mysticalworld.item.metals;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import scala.Char;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public enum Metal {

  copper("Copper"),
  tin("Tin"),
  silver("Silver"),
  lead("Lead"),
  nickel("Nickel"),
  aluminum("Aluminum"),
  zinc("Zinc"),
  invar("Invar"),
  electrum("Electrum"),
  brass("Brass"),
  bronze("Bronze"),
  dawnstone("Dawnstone"),
  sooty_iron("SootyIron")

  ;

  private @Nullable Item ingot;
  private @Nullable Block block;
  private @Nonnull String oredictNameSuffix;

  private Metal(@Nonnull String oredictNameSuffix) {
    this.oredictNameSuffix = oredictNameSuffix;
  }

  @Nonnull
  public String getOredictNameSuffix() {
    return oredictNameSuffix;
  }

  @Nullable
  public Item getIngot() {
    return ingot;
  }

  @Nonnull
  public Item setItem(@Nonnull Item item) {
    this.ingot = item;
    return this.ingot;
  }

  @Nullable
  public Block getBlock() {
    return block;
  }

  @Nonnull
  public Block setBlock(@Nonnull Block block) {
    this.block = block;
    return this.block;
  }

  public static void registerMetals(@Nonnull RegisterContentEvent event) {
    for (Metal metal : values()) {
      event.addItem(metal.setItem(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    }
  }

  public static void registerOreDict() {
    for (Metal metal : values()) {
      OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
    }
  }
}
