package epicsquid.mysticalworld.item.metals;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public enum Metal {

  copper("Copper"), tin("Tin"), silver("Silver"), lead("Lead"), nickel("Nickel"), aluminum("Aluminum"), zinc("Zinc"), invar("Invar"), electrum(
      "Electrum"), brass("Brass"), bronze("Bronze"), dawnstone("Dawnstone"), sooty_iron("SootyIron");

  private @Nullable Item ingot;
  private @Nullable Item nugget;
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
  public Item setIngot(@Nonnull Item item) {
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

  @Nullable
  public Item getNugget() {
    return nugget;
  }

  @Nonnull
  public Item setNugget(@Nonnull Item nugget) {
    this.nugget = nugget;
    return this.nugget;
  }

  public static void registerMetals(@Nonnull RegisterContentEvent event) {
    for (Metal metal : values()) {
      event.addItem(metal.setIngot(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      event.addItem(metal.setNugget(new ItemBase(metal.name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    }
  }

  public static void registerOreDict() {
    for (Metal metal : values()) {
      OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
      OreDictionary.registerOre("nugget" + metal.getOredictNameSuffix(), metal.getNugget());
    }
  }
}
