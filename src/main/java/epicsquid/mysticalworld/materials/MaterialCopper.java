package epicsquid.mysticalworld.materials;

import com.sun.swing.internal.plaf.metal.resources.metal;
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

public class MaterialCopper extends Material {
  public MaterialCopper(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    super(oredictNameSuffix, hardness, experience, level, minXP, maxXP, material, hasTool);
  }

  @Override
  public String name() {
    return "copper";
  }

  @Override
  public boolean isEnabled() {
    return ConfigManager.copper.enableCopper;
  }

  @Override
  public boolean hasOre() {
    return ConfigManager.copper.enableOres;
  }

  @Override
  public void initMaterial(@Nonnull RegisterContentEvent event) {
    event.addItem(setItem(new ItemBase(name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setNugget(new ItemBase(name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setDust(new ItemBase(name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setDustTiny(new ItemBase(name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setBlock(new BlockBase(net.minecraft.block.material.Material.IRON, SoundType.METAL, getHardness(), name() + "_block").setIsBeacon(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setOre(new BlockOreBase(net.minecraft.block.material.Material.ROCK, SoundType.STONE, getHardness(), name() + "_ore", null, getLevel(), getMinXP(), getMaxXP()).setCreativeTab(MysticalWorld.tab)));
  }

  @Override
  public void initOreDictionary() {
    if (isEnabled()) {
      if (ConfigManager.copper.enableIngots) {
        OreDictionary.registerOre("ingot" + getOredictNameSuffix(), getItem());
      }
      if (ConfigManager.copper.enableNuggets) {
        OreDictionary.registerOre("nugget" + getOredictNameSuffix(), getNugget());
      }
      if (hasGrindables()) {
        if (ConfigManager.copper.enableDusts) {
          OreDictionary.registerOre("dust" + getOredictNameSuffix(), getDust());
        }
        if (ConfigManager.copper.enableTinyDusts) {
          OreDictionary.registerOre("dustTiny" + getOredictNameSuffix(), getDustTiny());
        }
      }
      if (ConfigManager.copper.enableBlocks) {
        OreDictionary.registerOre("block" + getOredictNameSuffix(), getBlock());
      }
      if (hasOre() && ConfigManager.copper.enableOres) {
        OreDictionary.registerOre("ore" + getOredictNameSuffix(), getOre());
      }
    }
  }
}
