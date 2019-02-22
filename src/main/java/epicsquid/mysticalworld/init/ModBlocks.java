package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.BlockBase;
import epicsquid.mysticallib.block.BlockCropBase;
import epicsquid.mysticallib.block.BlockLogBase;
import epicsquid.mysticallib.block.BlockMushroomBase;
import epicsquid.mysticallib.block.BlockSlabBase;
import epicsquid.mysticallib.block.BlockStairsBase;
import epicsquid.mysticallib.block.BlockWallBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.CustomPlantType;
import epicsquid.mysticalworld.block.BlockAubergineCrop;
import epicsquid.mysticalworld.block.BlockCloudBerryCrop;
import epicsquid.mysticalworld.block.BlockDewgoniaCrop;
import epicsquid.mysticalworld.block.BlockInfernalBulbCrop;
import epicsquid.mysticalworld.block.BlockMoonglowCrop;
import epicsquid.mysticalworld.block.BlockPereskiaCrop;
import epicsquid.mysticalworld.block.BlockRunicSoil;
import epicsquid.mysticalworld.block.BlockSpiritHerbCrop;
import epicsquid.mysticalworld.block.BlockStalicripeCrop;
import epicsquid.mysticalworld.block.BlockThatch;
import epicsquid.mysticalworld.block.BlockWildewheetCrop;
import epicsquid.mysticalworld.block.BlockWildrootCrop;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.util.EnumRunicSoilType;
import epicsquid.mysticalworld.world.HugeBaffleCap;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block thatch, caminite, caminite_stairs, caminite_slab, caminite_wall, caminite_bricks, caminite_bricks_stairs, caminite_bricks_slab, caminite_bricks_wall, solar_infused_stone, solar_infused_stone_stairs, solar_infused_stone_slab, solar_infused_stone_wall,
      baffle_cap_huge_stem, baffle_cap_huge_top, baffle_cap_mushroom, runic_soil_fire, runic_soil_water, runic_soil_air, runic_soil_earth, runic_soil;

  public static BlockCropBase moonglow, aubergine, pereskia, wildroot, spirit_herb,wildewheet, cloud_berry, infernal_bulb, dewgonia, stalicripe;

  // Runestones
  public static Block runestone, runestone_brick, chiseled_runestone, wildwoodLog;


  // Decoration
  public static Block runestone_slab, runestone_double_slab, runestone_stairs, runestone_wall;
  public static Block runestone_brick_slab, runestone_brick_double_slab, runestone_brick_stairs, runestone_brick_wall;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {

    BlockSlabBase slab_temp, double_slab_temp;

    if (ConfigManager.modules.rootsModuleEnabled) {
      // Roots
      event.addBlock(moonglow = new BlockMoonglowCrop("moonglow_crop", EnumPlantType.Crop));
      event.addBlock(aubergine = new BlockAubergineCrop("aubergine_crop", EnumPlantType.Crop));
      event.addBlock(pereskia = new BlockPereskiaCrop("pereskia_crop", EnumPlantType.Crop));
      event.addBlock(wildroot = new BlockWildrootCrop("wildroot_crop", EnumPlantType.Crop));
      event.addBlock(spirit_herb = new BlockSpiritHerbCrop("spirit_herb_crop", EnumPlantType.Crop));
      event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch")).setCreativeTab(MysticalWorld.tab);
      event.addBlock(baffle_cap_huge_stem = new BlockBase(Material.CACTUS, SoundType.SLIME, 0.8f, "baffle_cap_huge_stem").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addBlock(baffle_cap_huge_top = new BlockBase(Material.CACTUS, SoundType.SLIME, 0.8f, "baffle_cap_huge_top").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addBlock(baffle_cap_mushroom = new BlockMushroomBase("baffle_cap_mushroom", new HugeBaffleCap().getData()));
      event.addBlock(wildewheet = new BlockWildewheetCrop("wildewheet_crop", EnumPlantType.Crop));
      event.addBlock(cloud_berry = new BlockCloudBerryCrop("cloud_berry_crop", CustomPlantType.ELEMENT_AIR));
      event.addBlock(infernal_bulb = new BlockInfernalBulbCrop("infernal_bulb_crop", CustomPlantType.ELEMENT_FIRE));
      // TODO 1.13 make the dewgonia work only underwater
      event.addBlock(dewgonia = new BlockDewgoniaCrop("dewgonia_crop", CustomPlantType.ELEMENT_WATER));
      event.addBlock(stalicripe = new BlockStalicripeCrop("stalicripe_crop", CustomPlantType.ELEMENT_EARTH));
      event.addBlock(runic_soil = new BlockBase(Material.GROUND, SoundType.GROUND, 0.8f, "runic_soil").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

      event.addBlock(runic_soil_air = new BlockRunicSoil(Material.GROUND, SoundType.GROUND, "runic_soil_air", EnumRunicSoilType.AIR).setModelCustom(false).setCreativeTab(MysticalWorld.tab));
      event.addBlock(runic_soil_water = new BlockRunicSoil(Material.GROUND, SoundType.GROUND, "runic_soil_water", EnumRunicSoilType.WATER).setModelCustom(false).setCreativeTab(MysticalWorld.tab));
      event.addBlock(runic_soil_fire = new BlockRunicSoil(Material.GROUND, SoundType.GROUND, "runic_soil_fire", EnumRunicSoilType.FIRE).setModelCustom(false).setCreativeTab(MysticalWorld.tab));
      event.addBlock(runic_soil_earth = new BlockRunicSoil(Material.GROUND, SoundType.GROUND, "runic_soil_earth", EnumRunicSoilType.EARTH).setModelCustom(false).setCreativeTab(MysticalWorld.tab));

      // Post registration block setup
      ((BlockMushroomBase) baffle_cap_mushroom).setItemBlock(new ItemBlock(baffle_cap_mushroom).setRegistryName(LibRegistry.getActiveModid(), "baffle_cap_mushroom"));

      //Runestones
      event.addBlock(runestone = new BlockBase(Material.ROCK, SoundType.METAL, 1.4f, "runestone").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
      event.addBlock(runestone_brick = new BlockBase(Material.ROCK, SoundType.METAL, 1.4f, "runestone_brick").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
      event.addBlock(chiseled_runestone = new BlockBase(Material.ROCK, SoundType.METAL, 1.4f, "chiseled_runestone").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);

      event.addBlock(wildwoodLog = new BlockLogBase("wildwood_log")).setCreativeTab(MysticalWorld.tab);

      //Decoration
      variants(event, runestone, "runestone", runestone_slab, runestone_double_slab, runestone_stairs, runestone_wall);
      variants(event, runestone_brick, "runestone_brick", runestone_brick_slab, runestone_brick_double_slab, runestone_brick_stairs, runestone_brick_wall);
    }

    if (ConfigManager.modules.embersModuleEnabled) {
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
      event
          .addBlock(caminite_wall = new BlockWallBase(caminite, SoundType.STONE, 2.0f, "caminite_wall").setModelCustom(true).setCreativeTab(MysticalWorld.tab));

      event.addBlock(
          caminite_bricks = new BlockBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addBlock(
          caminite_bricks_stairs = new BlockStairsBase(caminite_bricks.getDefaultState(), SoundType.STONE, 2.0f, "caminite_bricks_stairs").setModelCustom(true)
              .setCreativeTab(MysticalWorld.tab));
      double_slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks_double_slab", caminite_bricks.getDefaultState(), true, null)
          .setModelCustom(true);
      slab_temp = new BlockSlabBase(Material.ROCK, SoundType.STONE, 2.0f, "caminite_bricks_slab", caminite_bricks.getDefaultState(), false, double_slab_temp)
          .setModelCustom(true);
      double_slab_temp.setSlab(slab_temp);
      event.addBlock(caminite_bricks_slab = slab_temp.setCreativeTab(MysticalWorld.tab));
      event.addBlock(double_slab_temp);
      event.addBlock(caminite_bricks_wall = new BlockWallBase(caminite_bricks, SoundType.STONE, 2.0f, "caminite_bricks_wall").setModelCustom(true)
          .setCreativeTab(MysticalWorld.tab));
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
