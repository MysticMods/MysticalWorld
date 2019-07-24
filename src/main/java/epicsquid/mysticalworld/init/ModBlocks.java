package epicsquid.mysticalworld.init;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.*;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.BlockAubergineCrop;
import epicsquid.mysticalworld.blocks.BlockThatch;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.common.EnumPlantType;

public class ModBlocks {

  // All blocks
  public static Block thatch, cobblestone_door;
  public static BlockCropBase aubergine;
  public static Block mud_block, wet_mud_block, mud_brick, wet_mud_brick;
  public static Block mud_brick_stair, mud_brick_slab, mud_brick_double_slab, mud_brick_wall, mud_brick_fence, mud_brick_button, mud_brick_pressure_plate, mud_brick_door, mud_brick_trapdoor;
  public static Block mud_block_stair, mud_block_slab, mud_block_double_slab, mud_block_wall, mud_block_fence, mud_block_button, mud_block_pressure_plate, mud_block_door, mud_block_trapdoor;

  /**
   * Register all blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch").setCreativeTab(MysticalWorld.tab));
    //event.addBlock(cobblestone_door = new BlockDoorBase(Blocks.COBBLESTONE, SoundType.STONE, 10f, "cobblestone_door").setCreativeTab(MysticalWorld.tab));

    if (ConfigManager.modules.rootsModuleEnabled) {

    }

    if (ConfigManager.modules.embersModuleEnabled) {

    }

    if (ConfigManager.modules.solarModuleEnabled) {

    }

    event.addBlock(aubergine = new BlockAubergineCrop("aubergine_crop", EnumPlantType.Crop));

    event.addBlock(wet_mud_block = new BlockBase(Material.GROUND, SoundType.SLIME, 1.4f, "wet_mud_block").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
    event.addBlock(wet_mud_brick = new BlockBase(Material.GROUND, SoundType.SLIME, 1.4f, "wet_mud_brick").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
    event.addBlock(mud_block = new BlockBase(Material.ROCK, SoundType.STONE, 1.4f, "mud_block").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
    event.addBlock(mud_brick = new BlockBase(Material.ROCK, SoundType.STONE, 1.4f, "mud_brick").setModelCustom(true)).setCreativeTab(MysticalWorld.tab);
    Variants mud = variants(event, mud_block, "mud_block", SoundType.STONE, Material.ROCK);
    mud_block_slab = mud.slab;
    mud_block_double_slab = mud.double_slab;
    mud_block_stair = mud.stairs;
    mud_block_wall = mud.wall;
    mud_block_pressure_plate = mud.pressure_plate;
    mud_block_button = mud.button;
    event.addBlock(mud_block_fence = new BlockFenceBase(mud_block, SoundType.STONE, 2.0f, "mud_block_fence").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    mud = variants(event, mud_brick, "mud_brick", SoundType.STONE, Material.ROCK);
    mud_brick_slab = mud.slab;
    mud_brick_double_slab = mud.double_slab;
    mud_brick_stair = mud.stairs;
    mud_brick_wall = mud.wall;
    mud_brick_pressure_plate = mud.pressure_plate;
    mud_brick_button = mud.button;
    event.addBlock(mud_brick_fence = new BlockFenceBase(mud_brick, SoundType.STONE, 2.0f, "mud_brick_fence").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
  }

  private static Variants variants(RegisterContentEvent event, Block base, String name, SoundType sound, Material material) {
    Block[] slabs = new Block[2];
    Block stairs;
    Block wall;
    Block button;
    Block pressure_plate;
    LibRegistry.addSlabPair(material, sound, 1.7f, name, base.getDefaultState(), slabs, true,
        base.getCreativeTab());
    event.addBlock(stairs = new BlockStairsBase(base.getDefaultState(), sound, 1.7f, name + "_stairs").setModelCustom(true)
        .setCreativeTab(base.getCreativeTab()));
    event.addBlock(
        wall = new BlockWallBase(base, sound, 1.7f, name + "_wall").setModelCustom(true).setCreativeTab(base.getCreativeTab()));
    if (material.equals(Material.ROCK)) {
      event.addBlock(button = new BlockButtonStoneBase(base, sound, 1.7f, name + "_button").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addBlock(pressure_plate = new BlockPressurePlateBase(base, BlockPressurePlateBase.PressurePlateType.MOBS, sound, 1.7f, name + "_pressure_plate").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    } else {
      event.addBlock(button = new BlockButtonWoodBase(base, sound, 1.7f, name + "_button").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
      event.addBlock(pressure_plate = new BlockPressurePlateBase(base, BlockPressurePlateBase.PressurePlateType.ALL, sound, 1.7f, name + "_pressure_plate").setModelCustom(true).setCreativeTab(MysticalWorld.tab));
    }
    return new Variants(slabs, stairs, wall, button, pressure_plate);
  }

  private static class Variants {
    public Block stairs;
    public Block wall;
    public Block slab;
    public Block double_slab;
    public Block button;
    public Block pressure_plate;

    public Variants (Block[] slabs, Block stairs, Block wall, Block button, Block pressure_plate) {
      this.slab = slabs[0];
      this.double_slab = slabs[1];
      this.stairs = stairs;
      this.wall = wall;
      this.button = button;
      this.pressure_plate = pressure_plate;
    }
  }
}
