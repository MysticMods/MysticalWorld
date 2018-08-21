package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.block.BlockCropBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.item.BlockAubergineCrop;
import epicsquid.mysticalworld.item.BlockMoonglowCrop;
import epicsquid.mysticalworld.item.BlockPereskiaCrop;
import epicsquid.mysticalworld.item.BlockSpiritHerbCrop;
import epicsquid.mysticalworld.item.BlockTerraMossCrop;
import epicsquid.mysticalworld.item.BlockWildrootCrop;
import net.minecraft.block.Block;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block moonglow, aubergine, pereskia, terra_moss, wildroot, spirit_herb;

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
  }
}
