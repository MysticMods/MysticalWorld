package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.block.BlockCropBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import net.minecraft.block.Block;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block moonglow;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(moonglow = new BlockCropBase("moonglow", EnumPlantType.Crop, ModItems.moonglow_seed, ModItems.moonglow_leaf));
  }
}
