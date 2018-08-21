package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.block.BlockCropBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.item.BlockAubergineCrop;
import epicsquid.mysticalworld.item.BlockMoonglowCrop;
import net.minecraft.block.Block;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block moonglow, aubergine;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(moonglow = new BlockMoonglowCrop("moonglow", EnumPlantType.Crop));
    event.addBlock(aubergine = new BlockAubergineCrop("aubergine", EnumPlantType.Crop));
  }
}
