package epicsquid.mysticalworld.item.metals;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public enum Metal {

  copper("Copper", 3.5f, 0.65f),
  silver("Silver", 5f, 0.35f),
//  tin("Tin", 4f),
//  lead("Lead", 5f),
//  nickel("Nickel", 5f),
//  aluminum("Aluminum", 5f),
//  zinc("Zinc", 5f),
//  invar(
//      "Invar", 5f) {
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  }, electrum("Electrum", 5f) {
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  }, brass("Brass", 5f) {
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  }, bronze("Bronze", 5f) {
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  }, dawnstone("Dawnstone", 5f, true) {
//    @Override
//    public boolean hasGrindables() {
//      return false;
//    }
//
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  }, sooty_iron("SootyIron", 5f, true) {
//    @Override
//    public boolean hasGrindables() {
//      return false;
//    }
//
//    @Override
//    public boolean hasOre() {
//      return false;
//    }
//
//  },
  ;

  private Item ingot;
  private Item nugget;
  private Item dust;
  private Item dustTiny;
  private Block block;
  private Block ore;
  private final float hardness;
  private final String oredictNameSuffix;
  private final boolean isEmbers;
  private final float experience;

  Metal(@Nonnull String oredictNameSuffix, float hardness, float experience, boolean isEmbers) {
    this.oredictNameSuffix = oredictNameSuffix;
    this.hardness = hardness;
    this.isEmbers = isEmbers;
    this.experience = experience;
  }

  Metal(@Nonnull String oredictNameSuffix, float hardness, float experience) {
    this(oredictNameSuffix, hardness, experience, false);
  }

  public float getHardness() {
    return hardness;
  }

  public float getExperience () {
    return experience;
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

  public boolean isEnabled() {
    if (isEmbers) {
      try {
        return ConfigManager.modules.embersModuleEnabled && ConfigManager.metals.getClass().getField("enable" + getOredictNameSuffix()).getBoolean(ConfigManager.metals);
      } catch (Exception e) {
        System.out.println("Error: Cannot find the specified metal in configs. Are you sure you added it?");
        return ConfigManager.modules.embersModuleEnabled;
      }
    } else {
      try {
        return ConfigManager.modules.mysticalWorldModuleEnabled && ConfigManager.metals.getClass().getField("enable" + getOredictNameSuffix()).getBoolean(ConfigManager.metals);
      } catch (Exception e) {
        System.out.println("Error: Cannot find the specified metal in configs. Are you sure you added it?");
        return ConfigManager.modules.mysticalWorldModuleEnabled;
      }
    }
  }

  public boolean hasGrindables() {
    return true;
  }

  public boolean hasOre() {
    return true;
  }

  public static void registerMetals(@Nonnull RegisterContentEvent event) {
    for (Metal metal : values()) {
      if (metal.isEnabled()) {
        if (ConfigManager.metals.enableIngots) {
          event.addItem(metal.setIngot(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (ConfigManager.metals.enableNuggets) {
          event.addItem(metal.setNugget(new ItemBase(metal.name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.metals.enableDusts) {
            event.addItem(metal.setDust(new ItemBase(metal.name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
          if (ConfigManager.metals.enableTinyDusts) {
            event.addItem(metal.setDustTiny(new ItemBase(metal.name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
        }
        if (ConfigManager.metals.enableBlocks) {
          event.addBlock(metal.setBlock(new BlockBase(Material.IRON, SoundType.METAL, metal.getHardness(), metal.name() + "_block").setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasOre() && ConfigManager.metals.enableOres) {
          event.addBlock(metal.setOre(new BlockBase(Material.ROCK, SoundType.STONE, metal.getHardness(), metal.name() + "_ore").setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab)));
        }
      }
    }
  }

  public static void registerOreDict() {
    for (Metal metal : values()) {
      if (metal.isEnabled()) {
        if (ConfigManager.metals.enableIngots) {
          OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
        }
        if (ConfigManager.metals.enableNuggets) {
          OreDictionary.registerOre("nugget" + metal.getOredictNameSuffix(), metal.getNugget());
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.metals.enableDusts) {
            OreDictionary.registerOre("dust" + metal.getOredictNameSuffix(), metal.getDust());
          }
          if (ConfigManager.metals.enableTinyDusts) {
            OreDictionary.registerOre("dustTiny" + metal.getOredictNameSuffix(), metal.getDustTiny());
          }
        }
        if (ConfigManager.metals.enableBlocks) {
          OreDictionary.registerOre("block" + metal.getOredictNameSuffix(), metal.getBlock());
        }
        if (metal.hasOre() && ConfigManager.metals.enableOres) {
          OreDictionary.registerOre("ore" + metal.getOredictNameSuffix(), metal.getOre());
        }
      }
    }
  }
}
