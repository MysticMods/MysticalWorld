package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockOreBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

public class MaterialAmethyst extends Material {
  public MaterialAmethyst(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    super(oredictNameSuffix, hardness, experience, level, minXP, maxXP, material, hasTool);
  }

  @Override
  public String name() {
    return "amethyst";
  }

  @Override
  public boolean isEnabled() {
    return ConfigManager.amethyst.enableAmethyst;
  }

  @Override
  public boolean hasGrindables() {
    return false;
  }

  @Override
  public boolean hasOre() {
    return ConfigManager.amethyst.enableOres;
  }

  @Override
  public void initMaterial(@Nonnull RegisterContentEvent event) {
    event.addItem(setItem(new ItemBase(name() + "_gem").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setBlock(new BlockBase(net.minecraft.block.material.Material.IRON, SoundType.METAL, getHardness(), name() + "_block").setIsBeacon(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setOre(new BlockOreBase(net.minecraft.block.material.Material.ROCK, SoundType.STONE, getHardness(), name() + "_ore", getItem(), getLevel(), getMinXP(), getMaxXP()).setCreativeTab(MysticalWorld.tab)));
  }

  @Override
  public void initOreDictionary() {
    if (isEnabled()) {
      if (ConfigManager.amethyst.enableGems) {
        OreDictionary.registerOre("gem" + getOredictNameSuffix(), getItem());
      }
      if (ConfigManager.amethyst.enableBlocks) {
        OreDictionary.registerOre("block" + getOredictNameSuffix(), getBlock());
      }
      if (hasOre() && ConfigManager.amethyst.enableOres) {
        OreDictionary.registerOre("ore" + getOredictNameSuffix(), getOre());
      }
    }
  }
}
