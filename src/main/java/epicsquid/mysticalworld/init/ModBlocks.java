package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockSlabBase;
import epicsquid.mysticallib.block.BlockStairsBase;
import epicsquid.mysticallib.block.BlockWallBase;
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
  public static Block moonglow, aubergine, pereskia, terra_moss, wildroot, spirit_herb, thatch, caminite, caminite_stairs, caminite_slab, caminite_wall,
      caminite_bricks, caminite_bricks_stairs, caminite_bricks_slab, caminite_bricks_wall;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {

    BlockSlabBase slab_temp, double_slab_temp;

    // Roots
    event.addBlock(moonglow = new BlockMoonglowCrop("moonglow_crop", EnumPlantType.Crop));
    event.addBlock(aubergine = new BlockAubergineCrop("aubergine_crop", EnumPlantType.Crop));
    event.addBlock(pereskia = new BlockPereskiaCrop("pereskia_crop", EnumPlantType.Crop));
    event.addBlock(terra_moss = new BlockTerraMossCrop("terra_moss_crop", EnumPlantType.Crop));
    event.addBlock(wildroot = new BlockWildrootCrop("wildroot_crop", EnumPlantType.Crop));
    event.addBlock(spirit_herb = new BlockSpiritHerbCrop("spirit_herb_crop", EnumPlantType.Crop));
    event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch")).setCreativeTab(MysticalWorld.tab);

    // Embers
    event.addBlock(caminite = new BlockBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addBlock(caminite_stairs = new BlockStairsBase(caminite.getDefaultState(), SoundType.STONE, 2.0f, "caminite_stairs").setModelCustom(true)
        .setCreativeTab(MysticalWorld.tab));
    double_slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_double_slab", caminite.getDefaultState(), true, null)
        .setModelCustom(true);
    slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_slab", caminite.getDefaultState(), false, double_slab_temp)
        .setModelCustom(true);
    double_slab_temp.setSlab(slab_temp);
    event.addBlock(caminite_slab = slab_temp.setCreativeTab(MysticalWorld.tab));
    event.addBlock(double_slab_temp);
    event.addBlock(caminite_wall = new BlockWallBase(caminite, SoundType.STONE, 2.0f, "caminite_wall").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

    event.addBlock(
        caminite_bricks = new BlockBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    event.addBlock(caminite_bricks_stairs = new BlockStairsBase(caminite_bricks.getDefaultState(), SoundType.STONE, 2.0f, "caminite_bricks_stairs").setModelCustom(true)
        .setCreativeTab(MysticalWorld.tab));
    double_slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks_double_slab", caminite_bricks.getDefaultState(), true, null)
        .setModelCustom(true);
    slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks_slab", caminite_bricks.getDefaultState(), false, double_slab_temp)
        .setModelCustom(true);
    double_slab_temp.setSlab(slab_temp);
    event.addBlock(caminite_bricks_slab = slab_temp.setCreativeTab(MysticalWorld.tab));
    event.addBlock(double_slab_temp);
    event.addBlock(caminite_bricks_wall = new BlockWallBase(caminite_bricks, SoundType.STONE, 2.0f, "caminite_bricks_wall").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

  }
}
