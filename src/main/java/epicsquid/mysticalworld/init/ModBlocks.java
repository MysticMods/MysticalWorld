package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockSlabBase;
import epicsquid.mysticallib.block.BlockStairsBase;
import epicsquid.mysticallib.block.BlockWallBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.BlockThatch;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks {

  // All blocks
  public static Block thatch;



  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch").setCreativeTab(MysticalWorld.tab));


    if (ConfigManager.modules.rootsModuleEnabled) {

    }

    if (ConfigManager.modules.embersModuleEnabled) {

    }

    if (ConfigManager.modules.solarModuleEnabled) {

    }
  }

  private static void variants(RegisterContentEvent event, Block base, String name, Block... refs) {
    LibRegistry.addSlabPair(Material.ROCK, SoundType.STONE, 1.7f, name, base.getDefaultState(), new Block[] { refs[0], refs[1] }, true,
        base.getCreativeTabToDisplayOn());
    event.addBlock(refs[2] = new BlockStairsBase(base.getDefaultState(), SoundType.STONE, 1.7f, name + "_stairs").setModelCustom(true)
        .setCreativeTab(base.getCreativeTabToDisplayOn()));
    event.addBlock(
        refs[3] = new BlockWallBase(base, SoundType.STONE, 1.7f, name + "_wall").setModelCustom(true).setCreativeTab(base.getCreativeTabToDisplayOn()));
  }
}
