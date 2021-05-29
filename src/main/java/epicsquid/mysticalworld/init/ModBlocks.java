package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.block.*;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import javax.annotation.Nonnull;

public class ModBlocks {

  // All thaumcraft.blocks
  public static Block thatch, thatch_wall, thatch_double_slab, thatch_slab, thatch_stairs;
  public static BlockCropBase aubergine;
  public static Block gall_apple;
  public static Block mud_block, wet_mud_block, mud_brick, wet_mud_brick, charred_log, charred_planks;
  public static Block mud_brick_stair, mud_brick_slab, mud_brick_double_slab, mud_brick_wall, mud_brick_fence, mud_brick_button, mud_brick_pressure_plate, mud_brick_fence_gate;
  public static Block mud_block_stair, mud_block_slab, mud_block_double_slab, mud_block_wall, mud_block_fence, mud_block_button, mud_block_pressure_plate, mud_block_fence_gate;
  public static Block charred_stair, charred_slab, charred_double_slab, charred_wall, charred_fence, charred_button, charred_pressure_plate, charred_fence_gate;
  public static Block pearl_block, pearl_stair, pearl_slab, pearl_double_slab, pearl_wall, pearl_fence, pearl_button, pearl_pressure_plate, pearl_fence_gate;
  public static Block slime_eggs;

  /**
   * Register all thaumcraft.blocks
   */
  public static void registerBlocks(@Nonnull RegisterContentEvent event) {
    event.addBlock(thatch = new BlockThatch(Material.LEAVES, SoundType.PLANT, 0.8f, "thatch").setCreativeTab(MysticalWorld.tab));

    // Thatch
    Block[] slabs = new Block[2];
    LibRegistry.addSlabPair(Material.LEAVES, SoundType.PLANT, 1.7f, "thatch", thatch.getDefaultState(), slabs, MysticalWorld.tab);
    event.addBlock(thatch_stairs = new BlockStairsBase(thatch.getDefaultState(), SoundType.PLANT, 1.7f, "thatch" + "_stairs")).setCreativeTab(MysticalWorld.tab);
    event.addBlock(thatch_wall = new BlockWallBase(thatch, SoundType.PLANT, 1.7f, "thatch" + "_wall")).setCreativeTab(MysticalWorld.tab);
    thatch_slab = slabs[0];
    thatch_double_slab = slabs[1];

    event.addBlock(aubergine = new BlockAubergineCrop("aubergine_crop", EnumPlantType.Crop));
    event.addBlock(gall_apple = new BlockOakApple("gall_apple_crop"));
    event.addBlock(charred_log = new BlockLogBase("charred_log") {
      @Override
      public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
      }

      @Override
      public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
      }

      @Override
      public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
      }
    }.setCreativeTab(MysticalWorld.tab));
    event.addBlock(charred_planks = new BlockBase(Material.WOOD, SoundType.WOOD, 2.0f, "charred_planks") {
      @Override
      public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
      }

      @Override
      public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 0;
      }

      @Override
      public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
      }
    }.setCreativeTab(MysticalWorld.tab));
    Variants charred = variants(event, charred_planks, "charred", SoundType.WOOD, Material.WOOD);
    charred_slab = charred.slab;
    charred_stair = charred.stairs;
    charred_button = charred.button;
    charred_double_slab = charred.double_slab;
    charred_wall = charred.wall;
    charred_pressure_plate = charred.pressure_plate;
    event.addBlock(charred_fence = new BlockFenceBase(charred_planks, SoundType.WOOD, 2.0f, "charred_fence").setCreativeTab(MysticalWorld.tab));
    event.addBlock(charred_fence_gate = new BlockFenceGateBase(charred_planks, SoundType.WOOD, 2.0f, "charred_fence_gate").setCreativeTab(MysticalWorld.tab));

    event.addBlock(mud_block = new BlockBase(Material.ROCK, SoundType.STONE, 1.4f, "mud_block")).setCreativeTab(MysticalWorld.tab);
    event.addBlock(mud_brick = new BlockBase(Material.ROCK, SoundType.STONE, 1.4f, "mud_brick")).setCreativeTab(MysticalWorld.tab);
    event.addBlock(wet_mud_block = new BlockMud(Material.GROUND, SoundType.SLIME, 1.4f, "wet_mud_block")).setCreativeTab(MysticalWorld.tab);
    event.addBlock(wet_mud_brick = new BlockMud(Material.GROUND, SoundType.SLIME, 1.4f, "wet_mud_brick")).setCreativeTab(MysticalWorld.tab);
    Variants mud = variants(event, mud_block, "mud_block", SoundType.STONE, Material.ROCK);
    mud_block_slab = mud.slab;
    mud_block_double_slab = mud.double_slab;
    mud_block_stair = mud.stairs;
    mud_block_wall = mud.wall;
    mud_block_pressure_plate = mud.pressure_plate;
    mud_block_button = mud.button;
    event.addBlock(mud_block_fence = new BlockFenceBase(mud_block, SoundType.STONE, 2.0f, "mud_block_fence").setCreativeTab(MysticalWorld.tab));
    event.addBlock(mud_block_fence_gate = new BlockFenceGateBase(mud_block, SoundType.STONE, 2.0f, "mud_block_fence_gate").setCreativeTab(MysticalWorld.tab));
    mud = variants(event, mud_brick, "mud_brick", SoundType.STONE, Material.ROCK);
    mud_brick_slab = mud.slab;
    mud_brick_double_slab = mud.double_slab;
    mud_brick_stair = mud.stairs;
    mud_brick_wall = mud.wall;
    mud_brick_pressure_plate = mud.pressure_plate;
    mud_brick_button = mud.button;
    event.addBlock(mud_brick_fence = new BlockFenceBase(mud_brick, SoundType.STONE, 2.0f, "mud_brick_fence").setCreativeTab(MysticalWorld.tab));
    event.addBlock(mud_brick_fence_gate = new BlockFenceGateBase(mud_brick, SoundType.STONE, 2.0f, "mud_brick_fence_gate").setCreativeTab(MysticalWorld.tab));

    event.addBlock(pearl_block = new BlockBase(Material.ROCK, SoundType.STONE, 3.4f, "pearl_block")).setCreativeTab(MysticalWorld.tab);
    Variants pearl = variants(event, pearl_block, "pearl", SoundType.STONE, Material.ROCK);
    pearl_slab = pearl.slab;
    pearl_double_slab = pearl.double_slab;
    pearl_stair = pearl.stairs;
    pearl_wall = pearl.wall;
    pearl_pressure_plate = pearl.pressure_plate;
    pearl_button = pearl.button;
    event.addBlock(pearl_fence = new BlockFenceBase(pearl_block, SoundType.STONE, 3.0f, "pearl_fence").setCreativeTab(MysticalWorld.tab));
    event.addBlock(pearl_fence_gate = new BlockFenceGateBase(pearl_block, SoundType.STONE, 3.0f, "pearl_fence_gate").setCreativeTab(MysticalWorld.tab));

    event.addBlock(slime_eggs = new BlockSlimeEggs(Material.CLAY, SoundType.SLIME, 0.1f, "slime_eggs").setCreativeTab(MysticalWorld.tab));
  }

  private static Variants variants(RegisterContentEvent event, Block base, String name, SoundType sound, Material material) {
    Block[] slabs = new Block[2];
    Block stairs;
    Block wall;
    Block button;
    Block pressure_plate;
    LibRegistry.addSlabPair(material, sound, 1.7f, name, base.getDefaultState(), slabs, MysticalWorld.tab);
    event.addBlock(stairs = new BlockStairsBase(base.getDefaultState(), sound, 1.7f, name + "_stairs")).setCreativeTab(MysticalWorld.tab);
    event.addBlock(wall = new BlockWallBase(base, sound, 1.7f, name + "_wall")).setCreativeTab(MysticalWorld.tab);
    if (name.equals("pearl")) {
      event.addBlock(button = new BlockButtonStoneBase(base, sound, 1.7f, name + "_button")).setCreativeTab(MysticalWorld.tab);
      event.addBlock(pressure_plate = new BlockPressurePlateBase(base, BlockPressurePlateBase.PressurePlateType.PLAYER, sound, 1.7f, name + "_pressure_plate")).setCreativeTab(MysticalWorld.tab);
    } else {
      if (material.equals(Material.ROCK)) {
        event.addBlock(button = new BlockButtonStoneBase(base, sound, 1.7f, name + "_button")).setCreativeTab(MysticalWorld.tab);
        event.addBlock(pressure_plate = new BlockPressurePlateBase(base, BlockPressurePlateBase.PressurePlateType.MOBS, sound, 1.7f, name + "_pressure_plate")).setCreativeTab(MysticalWorld.tab);
      } else {
        event.addBlock(button = new BlockButtonWoodBase(base, sound, 1.7f, name + "_button")).setCreativeTab(MysticalWorld.tab);
        event.addBlock(pressure_plate = new BlockPressurePlateBase(base, BlockPressurePlateBase.PressurePlateType.ALL, sound, 1.7f, name + "_pressure_plate")).setCreativeTab(MysticalWorld.tab);
      }
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

    public Variants(Block[] slabs, Block stairs, Block wall, Block button, Block pressure_plate) {
      this.slab = slabs[0];
      this.double_slab = slabs[1];
      this.stairs = stairs;
      this.wall = wall;
      this.button = button;
      this.pressure_plate = pressure_plate;
    }
  }
}
