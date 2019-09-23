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

public class MaterialSilver extends Material {
  public MaterialSilver(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    super(oredictNameSuffix, hardness, experience, level, minXP, maxXP, material, hasTool);
  }

  @Override
  public String name() {
    return "silver";
  }

  @Override
  public boolean isEnabled() {
    return ConfigManager.silver.enableSilver;
  }

  @Override
  public boolean hasOre() {
    return ConfigManager.silver.enableOres;
  }

  @Override
  public void initMaterial(@Nonnull RegisterContentEvent event) {
    event.addItem(setItem(new ItemBase(name() + "_ingot").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setNugget(new ItemBase(name() + "_nugget").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setDust(new ItemBase(name() + "_dust").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addItem(setDustTiny(new ItemBase(name() + "_dust_tiny").setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setBlock(new BlockBase(net.minecraft.block.material.Material.IRON, SoundType.METAL, getHardness(), name() + "_block").setModelCustom(true).setIsBeacon(true).setCreativeTab(MysticalWorld.tab)));
    event.addBlock(setOre(new BlockOreBase(net.minecraft.block.material.Material.ROCK, SoundType.STONE, getHardness(), name() + "_ore", null, getLevel(), getMinXP(), getMaxXP()).setModelCustom(true).setCreativeTab(MysticalWorld.tab)));
  }

  @Override
  public void initOreDictionary() {
    if (isEnabled()) {
      if (ConfigManager.silver.enableIngots) {
        OreDictionary.registerOre("ingot" + getOredictNameSuffix(), getItem());
      }
      if (ConfigManager.silver.enableNuggets) {
        OreDictionary.registerOre("nugget" + getOredictNameSuffix(), getNugget());
      }
      if (hasGrindables()) {
        if (ConfigManager.silver.enableDusts) {
          OreDictionary.registerOre("dust" + getOredictNameSuffix(), getDust());
        }
        if (ConfigManager.silver.enableTinyDusts) {
          OreDictionary.registerOre("dustTiny" + getOredictNameSuffix(), getDustTiny());
        }
      }
      if (ConfigManager.silver.enableBlocks) {
        OreDictionary.registerOre("block" + getOredictNameSuffix(), getBlock());
      }
      if (hasOre() && ConfigManager.silver.enableOres) {
        OreDictionary.registerOre("ore" + getOredictNameSuffix(), getOre());
      }
    }
  }
}
