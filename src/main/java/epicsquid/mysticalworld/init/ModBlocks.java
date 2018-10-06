package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.block.BlockAubergineCrop;
import epicsquid.mysticalworld.block.BlockMoonglowCrop;
import epicsquid.mysticalworld.block.BlockPereskiaCrop;
import epicsquid.mysticalworld.block.BlockSpiritHerbCrop;
import epicsquid.mysticalworld.block.BlockTerraMossCrop;
import epicsquid.mysticalworld.block.BlockThatch;
import epicsquid.mysticalworld.block.BlockWildrootCrop;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block moonglow, aubergine, pereskia, terra_moss, wildroot, spirit_herb, thatch;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(moonglow = new BlockMoonglowCrop("moonglow_crop", EnumPlantType.Crop));
    event.addBlock(aubergine = new BlockAubergineCrop("aubergine_crop", EnumPlantType.Crop));
    event.addBlock(pereskia = new BlockPereskiaCrop("pereskia_crop", EnumPlantType.Crop));
    event.addBlock(terra_moss = new BlockTerraMossCrop("terra_moss_crop", EnumPlantType.Crop));
    event.addBlock(wildroot = new BlockWildrootCrop("wildroot_crop", EnumPlantType.Crop));
    event.addBlock(spirit_herb = new BlockSpiritHerbCrop("spirit_herb_crop", EnumPlantType.Crop));
    event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch")).setCreativeTab(MysticalWorld.tab);
  }
}
