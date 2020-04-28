package epicsquid.mysticalworld.materials;

import epicsquid.mysticallib.block.BlockOreBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MaterialQuartz extends Material {
  public MaterialQuartz(@Nonnull String oredictNameSuffix, float hardness, float experience, int level, int minXP, int maxXP, Item.ToolMaterial material, boolean hasTool) {
    super(oredictNameSuffix, hardness, experience, level, minXP, maxXP, material, hasTool);
  }

  @Override
  public String name() {
    return "quartz";
  }

  @Override
  public boolean isEnabled() {
    return ConfigManager.quartz.enableQuartz;
  }

  @Override
  public boolean hasOre() {
    return ConfigManager.quartz.enableGraniteOres || ConfigManager.quartz.enableStoneOres;
  }

  public BlockOreBase stoneOre;
  public BlockOreBase graniteOre;

  @Nullable
  @Override
  public Block getOre() {
    if (hasOre()) {
      if (ConfigManager.quartz.enableGraniteOres) {
        return graniteOre;
      } else if (ConfigManager.quartz.enableStoneOres) {
        return stoneOre;
      }
    }

    return null;
  }

  @Override
  public void initMaterial(@Nonnull RegisterContentEvent event) {
    event.addBlock(stoneOre = (BlockOreBase) new BlockOreBase(net.minecraft.block.material.Material.ROCK, SoundType.STONE, getHardness(), name() + "_ore", null, getLevel(), getMinXP(), getMaxXP()).setCreativeTab(MysticalWorld.tab));
    event.addBlock(graniteOre = (BlockOreBase) new BlockOreBase(net.minecraft.block.material.Material.ROCK, SoundType.STONE, getHardness(), "granite_" + name() + "_ore", null, getLevel(), getMinXP(), getMaxXP()).setCreativeTab(MysticalWorld.tab));
    if (ConfigManager.quartz.enableGraniteOres) {
      setOre(graniteOre);
    } else {
      setOre(stoneOre);
    }
  }

  @Override
  public void initOreDictionary() {
    if (isEnabled()) {
      if (hasOre()) {
        OreDictionary.registerOre("oreQuartz", graniteOre);
        OreDictionary.registerOre("oreQuartz", stoneOre);
      }
    }
  }
}
