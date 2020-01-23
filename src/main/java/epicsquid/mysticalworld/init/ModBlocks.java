package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.blocks.WetMudBlock;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.blocks.WetMudBrick;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRY;

@SuppressWarnings("unused")
public class ModBlocks {
  public static RegistryObject<ThatchBlock> THATCH = REGISTRY.registerBlock("thatch", REGISTRY.block(ThatchBlock::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT)), ModRegistries.SIG);
  public static RegistryObject<AubergineCropBlock> AUBERGINE_CROP = REGISTRY.registerBlockWithoutItem("aubergine_crop", REGISTRY.block(AubergineCropBlock::new, () -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly()));
  public static RegistryObject<WetMudBlock> WET_MUD_BLOCK = REGISTRY.registerBlock("wet_mud_block", REGISTRY.block(WetMudBlock::new, () -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME)), ModRegistries.SIG);
  public static RegistryObject<WetMudBrick> WET_MUD_BRICK = REGISTRY.registerBlock("wet_mud_brick", REGISTRY.block(WetMudBrick::new, () -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME)), ModRegistries.SIG);

  public static RegistryObject<Block> MUD_BLOCK = REGISTRY.registerBlock("mud_block", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> MUD_BLOCK_STAIRS = REGISTRY.registerBlock("mud_block_stairs", REGISTRY.stair(MUD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> MUD_BLOCK_SLAB = REGISTRY.registerBlock("mud_block_slab", REGISTRY.slab(MUD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> MUD_BLOCK_WALL = REGISTRY.registerBlock("mud_block_wall", REGISTRY.wall(MUD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> MUD_BLOCK_FENCE = REGISTRY.registerBlock("mud_block_fence", REGISTRY.fence(MUD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> MUD_BLOCK_FENCE_GATE = REGISTRY.registerBlock("mud_block_fence_gate", REGISTRY.fenceGate(MUD_BLOCK), ModRegistries.SIG);
  //public static RegistryObject<StoneButtonBlock> MUD_BLOCK_BUTTON = REGISTRY.registerBlock("mud_block_button", REGISTRY.stoneButton(MUD_BLOCK), ModRegistries.SIG);
  //public static RegistryObject<TrapDoorBlock> MUD_BLOCK_TRAPDOOR = REGISTRY.registerBlock("mud_block_trapdoor", REGISTRY.trapDoor(MUD_BLOCK), ModRegistries.SIG);
  //public static RegistryObject<PressurePlateBlock> MUD_BLOCK_PRESSURE_PLATE = REGISTRY.registerBlock("mud_block_pressure_plate", REGISTRY.pressurePlate(MUD_BLOCK, PressurePlateBlock.Sensitivity.MOBS), ModRegistries.SIG);

  public static RegistryObject<LogBlock> CHARRED_LOG = REGISTRY.registerBlock("charred_log", REGISTRY.log(MaterialColor.WOOD, () -> Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f)), ModRegistries.SIG);
  public static RegistryObject<Block> CHARRED_PLANKS = REGISTRY.registerBlock("charred_planks", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> CHARRED_STAIRS = REGISTRY.registerBlock("charred_stairs", REGISTRY.stair(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> CHARRED_SLAB = REGISTRY.registerBlock("charred_slab", REGISTRY.slab(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<WallBlock> CHARRED_WALL = REGISTRY.registerBlock("charred_wall", REGISTRY.wall(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> CHARRED_FENCE = REGISTRY.registerBlock("charred_fence", REGISTRY.fence(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> CHARRED_FENCE_GATE = REGISTRY.registerBlock("charred_fence_gate", REGISTRY.fenceGate(CHARRED_PLANKS), ModRegistries.SIG);

  public static RegistryObject<Block> MUD_BRICK = REGISTRY.registerBlock("mud_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> MUD_BRICK_STAIRS = REGISTRY.registerBlock("mud_brick_stairs", REGISTRY.stair(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> MUD_BRICK_SLAB = REGISTRY.registerBlock("mud_brick_slab", REGISTRY.slab(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> MUD_BRICK_WALL = REGISTRY.registerBlock("mud_brick_wall", REGISTRY.wall(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> MUD_BRICK_FENCE = REGISTRY.registerBlock("mud_brick_fence", REGISTRY.fence(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> MUD_BRICK_FENCE_GATE = REGISTRY.registerBlock("mud_brick_fence_gate", REGISTRY.fenceGate(MUD_BRICK), ModRegistries.SIG);

  public static RegistryObject<Block> TERRACOTTA_BRICK = REGISTRY.registerBlock("terracotta_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> TERRACOTTA_BRICK_STAIRS = REGISTRY.registerBlock("terracotta_brick_stairs", REGISTRY.stair(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> TERRACOTTA_BRICK_SLAB = REGISTRY.registerBlock("terracotta_brick_slab", REGISTRY.slab(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> TERRACOTTA_BRICK_WALL = REGISTRY.registerBlock("terracotta_brick_wall", REGISTRY.wall(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> TERRACOTTA_BRICK_FENCE = REGISTRY.registerBlock("terracotta_brick_fence", REGISTRY.fence(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = REGISTRY.registerBlock("terracotta_brick_fence_gate", REGISTRY.fenceGate(TERRACOTTA_BRICK), ModRegistries.SIG);

  public static RegistryObject<Block> IRON_BRICK = REGISTRY.registerBlock("iron_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> IRON_BRICK_STAIRS = REGISTRY.registerBlock("iron_brick_stairs", REGISTRY.stair(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> IRON_BRICK_SLAB = REGISTRY.registerBlock("iron_brick_slab", REGISTRY.slab(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> IRON_BRICK_WALL = REGISTRY.registerBlock("iron_brick_wall", REGISTRY.wall(IRON_BRICK), ModRegistries.SIG);

  // Ore Blocks
  public static RegistryObject<BaseOreBlock> AMETHYST_ORE = REGISTRY.registerBlock(ModMaterials.AMETHYST.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.AMETHYST), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> COPPER_ORE = REGISTRY.registerBlock(ModMaterials.COPPER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.COPPER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> LEAD_ORE = REGISTRY.registerBlock(ModMaterials.LEAD.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.LEAD), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> QUICKSILVER_ORE = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.QUICKSILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> SILVER_ORE = REGISTRY.registerBlock(ModMaterials.SILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.SILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> TIN_ORE = REGISTRY.registerBlock(ModMaterials.TIN.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.TIN), ModRegistries.SMG);

  // Blocks
  public static RegistryObject<Block> AMETHYST_BLOCK = REGISTRY.registerBlock(ModMaterials.AMETHYST.blockName(), REGISTRY.block(Block::new, ModMaterials.AMETHYST.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> COPPER_BLOCK = REGISTRY.registerBlock(ModMaterials.COPPER.blockName(), REGISTRY.block(Block::new, ModMaterials.COPPER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> LEAD_BLOCK = REGISTRY.registerBlock(ModMaterials.LEAD.blockName(), REGISTRY.block(Block::new, ModMaterials.LEAD.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> QUICKSILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.QUICKSILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> SILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.SILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.SILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<Block> TIN_BLOCK = REGISTRY.registerBlock(ModMaterials.TIN.blockName(), REGISTRY.block(Block::new, ModMaterials.TIN.getBlockProps()), ModRegistries.SMG);

  public static void load() {
  }
}
