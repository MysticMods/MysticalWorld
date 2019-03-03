package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockSlabBase;
import epicsquid.mysticallib.block.BlockStairsBase;
import epicsquid.mysticallib.block.BlockWallBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ModBlocks {

  // All blocks
  public static Block solar_infused_stone, solar_infused_stone_stairs, solar_infused_stone_slab, solar_infused_stone_wall;



  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {

    BlockSlabBase slab_temp, double_slab_temp;

    if (ConfigManager.modules.rootsModuleEnabled) {

    }

    if (ConfigManager.modules.embersModuleEnabled) {

    }

    if (ConfigManager.modules.solarModuleEnabled) {
      //Solar
      event.addBlock(solar_infused_stone = new BlockBase(Material.ROCK, SoundType.STONE, 1.4f, "solar_infused_stone").setCreativeTab(MysticalWorld.tab));
      event.addBlock(
          solar_infused_stone_stairs = new BlockStairsBase(solar_infused_stone.getDefaultState(), SoundType.STONE, 2.0f, "solar_infused_stone_stairs").setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab));
      double_slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "solar_infused_stone_double_slab", solar_infused_stone.getDefaultState(), true, null)
          .setModelCustom(true);
      slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "solar_infused_stone_slab", solar_infused_stone.getDefaultState(), false, double_slab_temp)
          .setModelCustom(true);
      double_slab_temp.setSlab(slab_temp);
      event.addBlock(solar_infused_stone_slab = slab_temp.setCreativeTab(MysticalWorld.tab));
      event.addBlock(double_slab_temp);
      event.addBlock(solar_infused_stone_wall = new BlockWallBase(solar_infused_stone, SoundType.STONE, 2.0f, "solar_infused_stone_wall").setModelCustom(true)
          .setCreativeTab(MysticalWorld.tab));
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
