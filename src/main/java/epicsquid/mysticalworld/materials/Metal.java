package epicsquid.mysticalworld.materials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticallib.block.BlockOreBase;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public enum Metal implements IMetal {
  copper("Copper", 3.5f, 0.65f, 1, -1, -1, MaterialTypes.material("mysticalworld:copper"), true),
  silver("Silver", 5f, 0.35f, 2, -1, -1, MaterialTypes.material("mysticalworld:silver"), true);

//  tin("Tin", 4f), <-- Probably going
//  lead("Lead", 5f), <-- May be used with HPL
//  nickel("Nickel", 5f),
//  aluminum("Aluminum", 5f),
//  zinc("Zinc", 5f)

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
  private final int level;
  private final int minXP;
  private final int maxXP;
  private final Item.ToolMaterial material;
  private boolean hasTool;

  Metal(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    this.oredictNameSuffix = oredictNameSuffix;
    this.hardness = hardness;
    this.isEmbers = false; // TODO
    this.experience = experience;
    this.level = level;
    this.minXP = minXP;
    this.maxXP = maxXP;
    this.material = material;
    this.hasTool = hasTool;
  }

  @Override
  public boolean hasTool() {
    return hasTool;
  }

  @Override
  public Item.ToolMaterial getMaterial() {
    return material;
  }

  @Override
  public float getHardness() {
    return hardness;
  }

  @Override
  public float getExperience() {
    return experience;
  }

  @Override
  @Nonnull
  public String getOredictNameSuffix() {
    return oredictNameSuffix;
  }

  @Override
  @Nullable
  public Item getIngot() {
    return ingot;
  }

  @Override
  @Nonnull
  public Item setIngot(@Nonnull Item item) {
    this.ingot = item;
    return this.ingot;
  }

  @Override
  @Nullable
  public Item getDust() {
    return dust;
  }

  @Override
  @Nonnull
  public Item setDust(@Nonnull Item dust) {
    this.dust = dust;
    return this.dust;
  }

  @Override
  @Nullable
  public Item getDustTiny() {
    return dustTiny;
  }

  @Override
  @Nonnull
  public Item setDustTiny(@Nonnull Item dustTiny) {
    this.dustTiny = dustTiny;
    return this.dustTiny;
  }

  @Override
  @Nullable
  public Block getBlock() {
    return block;
  }

  @Override
  @Nonnull
  public Block setBlock(@Nonnull Block block) {
    this.block = block;
    block.setHarvestLevel("pickaxe", getLevel());
    return this.block;
  }

  @Override
  @Nullable
  public Item getNugget() {
    return nugget;
  }

  @Override
  @Nonnull
  public Item setNugget(@Nonnull Item nugget) {
    this.nugget = nugget;
    return this.nugget;
  }

  @Override
  @Nullable
  public Block getOre() {
    return ore;
  }

  @Override
  @Nonnull
  public Block setOre(@Nonnull Block ore) {
    this.ore = ore;
    return this.ore;
  }

  @Override
  public boolean isEnabled() {
    if (this == Metal.copper) {
      return ConfigManager.copper.enableCopper;
    } else if (this == Metal.silver) {
      return ConfigManager.silver.enableSilver;
    } else {
      return false;
    }
  }

  @Override
  public boolean hasGrindables() {
    return true;
  }

  @Override
  public boolean hasOre() {
    return true;
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public int getMinXP() {
    return minXP;
  }

  @Override
  public int getMaxXP() {
    return maxXP;
  }

  public static void registerMetals(@Nonnull RegisterContentEvent event) {
    for (Metal metal : values()) {
      if (metal.isEnabled() && metal == Metal.copper) {
        if (ConfigManager.copper.enableIngots) {
          event.addItem(metal.setIngot(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (ConfigManager.copper.enableNuggets) {
          event.addItem(metal.setNugget(new ItemBase(metal.name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.copper.enableDusts) {
            event.addItem(metal.setDust(new ItemBase(metal.name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
          if (ConfigManager.copper.enableTinyDusts) {
            event.addItem(metal.setDustTiny(new ItemBase(metal.name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
        }
        if (ConfigManager.copper.enableBlocks) {
          event.addBlock(metal.setBlock(new BlockBase(Material.IRON, SoundType.METAL, metal.getHardness(), metal.name() + "_block").setModelCustom(true).setIsBeacon(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasOre() && ConfigManager.copper.enableOres) {
          event.addBlock(metal.setOre(new BlockOreBase(Material.ROCK, SoundType.STONE, metal.getHardness(), metal.name() + "_ore", null, metal.getLevel(), metal.getMinXP(), metal.getMaxXP()).setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
      } else if (metal.isEnabled() && metal == Metal.silver) {
        if (ConfigManager.silver.enableIngots) {
          event.addItem(metal.setIngot(new ItemBase(metal.name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (ConfigManager.silver.enableNuggets) {
          event.addItem(metal.setNugget(new ItemBase(metal.name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.silver.enableDusts) {
            event.addItem(metal.setDust(new ItemBase(metal.name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
          if (ConfigManager.silver.enableTinyDusts) {
            event.addItem(metal.setDustTiny(new ItemBase(metal.name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
          }
        }
        if (ConfigManager.silver.enableBlocks) {
          event.addBlock(metal.setBlock(new BlockBase(Material.IRON, SoundType.METAL, metal.getHardness(), metal.name() + "_block").setModelCustom(true).setIsBeacon(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (metal.hasOre() && ConfigManager.silver.enableOres) {
          event.addBlock(metal.setOre(new BlockOreBase(Material.ROCK, SoundType.STONE, metal.getHardness(), metal.name() + "_ore", null, metal.getLevel(), metal.getMinXP(), metal.getMaxXP()).setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
      }
    }
  }

  public static void registerOreDict() {
    for (Metal metal : values()) {
      if (metal.isEnabled() && metal == Metal.copper) {
        if (ConfigManager.copper.enableIngots) {
          OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
        }
        if (ConfigManager.copper.enableNuggets) {
          OreDictionary.registerOre("nugget" + metal.getOredictNameSuffix(), metal.getNugget());
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.copper.enableDusts) {
            OreDictionary.registerOre("dust" + metal.getOredictNameSuffix(), metal.getDust());
          }
          if (ConfigManager.copper.enableTinyDusts) {
            OreDictionary.registerOre("dustTiny" + metal.getOredictNameSuffix(), metal.getDustTiny());
          }
        }
        if (ConfigManager.copper.enableBlocks) {
          OreDictionary.registerOre("block" + metal.getOredictNameSuffix(), metal.getBlock());
        }
        if (metal.hasOre() && ConfigManager.copper.enableOres) {
          OreDictionary.registerOre("ore" + metal.getOredictNameSuffix(), metal.getOre());
        }
      } else if (metal.isEnabled() && metal == Metal.silver) {
        if (ConfigManager.silver.enableIngots) {
          OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getIngot());
        }
        if (ConfigManager.silver.enableNuggets) {
          OreDictionary.registerOre("nugget" + metal.getOredictNameSuffix(), metal.getNugget());
        }
        if (metal.hasGrindables()) {
          if (ConfigManager.silver.enableDusts) {
            OreDictionary.registerOre("dust" + metal.getOredictNameSuffix(), metal.getDust());
          }
          if (ConfigManager.silver.enableTinyDusts) {
            OreDictionary.registerOre("dustTiny" + metal.getOredictNameSuffix(), metal.getDustTiny());
          }
        }
        if (ConfigManager.silver.enableBlocks) {
          OreDictionary.registerOre("block" + metal.getOredictNameSuffix(), metal.getBlock());
        }
        if (metal.hasOre() && ConfigManager.silver.enableOres) {
          OreDictionary.registerOre("ore" + metal.getOredictNameSuffix(), metal.getOre());
        }
      }
    }

    if (ConfigManager.gold.enableDusts) {
      OreDictionary.registerOre("dustGold", ModItems.gold_dust);
    }
    if (ConfigManager.gold.enableTinyDusts) {
      OreDictionary.registerOre("dustTinyGold", ModItems.gold_dust_tiny);
    }
    if (ConfigManager.iron.enableDusts) {
      OreDictionary.registerOre("dustIron", ModItems.iron_dust);
    }
    if (ConfigManager.iron.enableTinyDusts) {
      OreDictionary.registerOre("dustTinyIron", ModItems.iron_dust_tiny);
    }
  }
}
