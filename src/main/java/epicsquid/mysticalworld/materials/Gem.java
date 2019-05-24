package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticallib.block.BlockOreBase;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum Gem implements IGem {
  amethyst("Amethyst", 3.0f, 0.7f, 2, 3, 7);

  private Item gem;
  private Block block;
  private Block ore;
  private final int level;
  private final float hardness;
  private final String oredictNameSuffix;
  private final float experience;
  private final int minXP;
  private final int maxXP;

  Gem(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP) {
    this.oredictNameSuffix = oredictNameSuffix;
    this.hardness = hardness;
    this.experience = experience;
    this.level = level;
    this.minXP = minXP;
    this.maxXP = maxXP;
  }

  @Override
  public int getMinXP() {
    return minXP;
  }

  @Override
  public int getMaxXP() {
    return maxXP;
  }

  @Nullable
  @Override
  public Item getGem() {
    return gem;
  }

  @Nonnull
  @Override
  public Item setGem(@Nonnull Item item) {
    this.gem = item;
    return item;
  }

  @Override
  public float getHardness() {
    return hardness;
  }

  @Override
  public float getExperience() {
    return experience;
  }

  @Nonnull
  @Override
  public String getOredictNameSuffix() {
    return oredictNameSuffix;
  }

  @Nullable
  @Override
  public Block getBlock() {
    return block;
  }

  @Nonnull
  @Override
  public Block setBlock(@Nonnull Block block) {
    this.block = block;
    return block;
  }

  @Nullable
  @Override
  public Block getOre() {
    return ore;
  }

  @Nonnull
  @Override
  public Block setOre(@Nonnull Block ore) {
    this.ore = ore;
    return ore;
  }

  @Override
  public boolean isEnabled() {
    // TODO: Configure it
    if (this == amethyst) {
      return ConfigManager.gems.enableAmethyst;
    }

    return false;
  }

  @Override
  public boolean hasGrindables() {
    return false;
  }

  @Override
  public boolean hasOre() {
    if (this == amethyst) {
      return true;
    }

    return false;
  }

  @Override
  public int getLevel() {
    return level;
  }

  public static void registerGems(@Nonnull RegisterContentEvent event) {
    for (Gem gem : values()) {
      if (gem.isEnabled()) {
        if (ConfigManager.gems.enableGems) {
          event.addItem(gem.setGem(new ItemBase(gem.name() + "_gem").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
        }
        if (ConfigManager.gems.enableBlocks) {
          event.addBlock(gem.setBlock(new BlockBase(Material.IRON, SoundType.METAL, gem.getHardness(), gem.name() + "_block").setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab)));
        }
        if (gem.hasOre() && ConfigManager.gems.enableOres) {
          event.addBlock(gem.setOre(new BlockOreBase(Material.ROCK, SoundType.STONE, gem.getHardness(), gem.name() + "_ore", gem.getGem(), gem.getLevel(), gem.getMinXP(), gem.getMaxXP()).setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab)));
        }
      }
    }
  }

  public static void registerOreDict() {
    for (Gem gem : values()) {
      if (gem.isEnabled()) {
        if (ConfigManager.gems.enableGems) {
          OreDictionary.registerOre("gem" + gem.getOredictNameSuffix(), gem.getGem());
        }
        if (ConfigManager.gems.enableBlocks) {
          OreDictionary.registerOre("block" + gem.getOredictNameSuffix(), gem.getBlock());
        }
        if (gem.hasOre() && ConfigManager.gems.enableOres) {
          OreDictionary.registerOre("ore" + gem.getOredictNameSuffix(), gem.getOre());
        }
      }
    }
  }
}
