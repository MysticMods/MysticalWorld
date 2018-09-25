package epicsquid.mysticalworld.item.metals;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public enum Metal {

  copper("Copper", 3.5f),
  tin("Tin", 4f),
  silver("Silver", 5f),
  lead("Lead", 5f),
  nickel("Nickel", 5f),
  aluminum("Aluminum", 5f),
  zinc("Zinc", 5f),
  invar("Invar", 5f) {

    @Override
    protected boolean hasOre() {
      return false;
    }

  },
  electrum("Electrum", 5f) {

    @Override
    protected boolean hasOre() {
      return false;
    }

  },
  brass("Brass", 5f) {

    @Override
    protected boolean hasOre() {
      return false;
    }

  },
  bronze("Bronze", 5f) {

    @Override
    protected boolean hasOre() {
      return false;
    }

  },
  dawnstone("Dawnstone", 5f) {

    @Override
    protected boolean hasGrindables() {
      return false;
    }

    @Override
    protected boolean hasOre() {
      return false;
    }

  },
  sooty_iron("SootyIron", 5f){

    @Override
    protected boolean hasGrindables() {
      return false;
    }

    @Override
    protected boolean hasOre() {
      return false;
    }

  },

  ;

  private @Nullable Item ingot;
  private @Nullable Item nugget;
  private @Nullable Item dust;
  private @Nullable Item dustTiny;
  private @Nullable Block block;
  private @Nullable Block ore;
  private final float hardness;
  private final @Nonnull String oredictNameSuffix;

  private Metal(@Nonnull String oredictNameSuffix, float hardness) {
    this.oredictNameSuffix = oredictNameSuffix;
    this.hardness = hardness;
  }

  public float getHardness() {
    return hardness;
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
  public Item getDust() {
    return dust;
  }

  @Nonnull
  public Item setDust(@Nonnull Item dust) {
    this.dust = dust;
    return this.dust;
  }

  @Nullable
  public Item getDustTiny() {
    return dustTiny;
  }

  @Nonnull
  public Item setDustTiny(@Nonnull Item dustTiny) {
    this.dustTiny = dustTiny;
    return this.dustTiny;
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

  @Nullable
  public Block getOre() {
    return ore;
  }

  @Nonnull
  public Block setOre(@Nonnull Block ore) {
    this.ore = ore;
    return this.ore;
  }

  protected boolean hasGrindables() {
    return true;
  }

  protected boolean hasOre() { return true; }

  public static void registerMetals(@Nonnull RegisterContentEvent event) {
    for (Metal metal : values()) {
      event.addItem(metal.setIngot(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      event.addItem(metal.setNugget(new ItemBase(metal.name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      if (metal.hasGrindables()) {
        event.addItem(metal.setDust(new ItemBase(metal.name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        event.addItem(metal.setDustTiny(new ItemBase(metal.name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      }
      event.addBlock(metal.setBlock(
          new BlockBase(Material.IRON, SoundType.METAL, metal.getHardness(), metal.name() + "_block").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      if (metal.hasOre()) {
        event.addBlock(metal.setOre(new BlockBase(Material.ROCK, SoundType.STONE, metal.getHardness(), metal.name() + "_ore").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
      }
    }
  }

  public static void registerOreDict() {
    for (Metal metal : values()) {
      OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
      OreDictionary.registerOre("nugget" + metal.getOredictNameSuffix(), metal.getNugget());
      if (metal.hasGrindables()) {
        OreDictionary.registerOre("dust" + metal.getOredictNameSuffix(), metal.getDust());
        OreDictionary.registerOre("dustTiny" + metal.getOredictNameSuffix(), metal.getDustTiny());
      }
      OreDictionary.registerOre("block" + metal.getOredictNameSuffix(), metal.getBlock());
      if (metal.hasOre()) {
        OreDictionary.registerOre("ore" + metal.getOredictNameSuffix(), metal.getOre());
      }
    }
  }
}
