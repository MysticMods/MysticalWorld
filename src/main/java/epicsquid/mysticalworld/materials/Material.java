package epicsquid.mysticalworld.materials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticallib.block.BlockOreBase;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Used to add the various metals and metal components used in Mystical World and sub mods
 */
public abstract class Material implements IMaterial {
  private Item ingot;
  private Item nugget;
  private Item dust;
  private Item dustTiny;
  private Block block;
  private Block ore;
  private final float hardness;
  private final String oredictNameSuffix;
  private final float experience;
  private final int level;
  private final int minXP;
  private final int maxXP;
  private final Item.ToolMaterial material;
  private boolean hasTool;

  public Material(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    this.oredictNameSuffix = oredictNameSuffix;
    this.hardness = hardness;
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
  public Item getItem() {
    return ingot;
  }

  @Override
  @Nonnull
  public Item setItem(@Nonnull Item item) {
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
  public abstract boolean isEnabled();

  @Override
  public boolean hasGrindables() {
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

  @Override
  public abstract void initMaterial (@Nonnull RegisterContentEvent event);

  @Override
  public abstract void initOreDictionary();
    /*for (Material metal : values()) {

      } else if (metal.isEnabled() && metal == Material.silver) {
        if (ConfigManager.silver.enableIngots) {
          OreDictionary.registerOre("ingot" + metal.getOredictNameSuffix(), metal.getItem());
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
  }*/
}
