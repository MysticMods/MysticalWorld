package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticallib.block.NarrowPostBlock;
import epicsquid.mysticallib.block.WidePostBlock;
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
  public static RegistryObject<WidePostBlock> MUD_BLOCK_WIDE_POST = REGISTRY.registerBlock("mud_block_wide_post", REGISTRY.widePost(MUD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> MUD_BLOCK_SMALL_POST = REGISTRY.registerBlock("mud_block_small_post", REGISTRY.narrowPost(MUD_BLOCK), ModRegistries.SIG);

  public static RegistryObject<LogBlock> CHARRED_LOG = REGISTRY.registerBlock("charred_log", REGISTRY.log(MaterialColor.WOOD, () -> Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f)), ModRegistries.SIG);
  public static RegistryObject<Block> CHARRED_PLANKS = REGISTRY.registerBlock("charred_planks", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> CHARRED_STAIRS = REGISTRY.registerBlock("charred_stairs", REGISTRY.stair(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> CHARRED_SLAB = REGISTRY.registerBlock("charred_slab", REGISTRY.slab(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<WallBlock> CHARRED_WALL = REGISTRY.registerBlock("charred_wall", REGISTRY.wall(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> CHARRED_FENCE = REGISTRY.registerBlock("charred_fence", REGISTRY.fence(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> CHARRED_FENCE_GATE = REGISTRY.registerBlock("charred_fence_gate", REGISTRY.fenceGate(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> CHARRED_WIDE_POST = REGISTRY.registerBlock("charred_wide_post", REGISTRY.widePost(CHARRED_PLANKS), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> CHARRED_SMALL_POST = REGISTRY.registerBlock("charred_small_post", REGISTRY.narrowPost(CHARRED_PLANKS), ModRegistries.SIG);

  public static RegistryObject<Block> MUD_BRICK = REGISTRY.registerBlock("mud_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> MUD_BRICK_STAIRS = REGISTRY.registerBlock("mud_brick_stairs", REGISTRY.stair(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> MUD_BRICK_SLAB = REGISTRY.registerBlock("mud_brick_slab", REGISTRY.slab(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> MUD_BRICK_WALL = REGISTRY.registerBlock("mud_brick_wall", REGISTRY.wall(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> MUD_BRICK_FENCE = REGISTRY.registerBlock("mud_brick_fence", REGISTRY.fence(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> MUD_BRICK_FENCE_GATE = REGISTRY.registerBlock("mud_brick_fence_gate", REGISTRY.fenceGate(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> MUD_BRICK_WIDE_POST = REGISTRY.registerBlock("mud_brick_wide_post", REGISTRY.widePost(MUD_BRICK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> MUD_BRICK_SMALL_POST = REGISTRY.registerBlock("mud_brick_small_post", REGISTRY.narrowPost(MUD_BRICK), ModRegistries.SIG);

  public static RegistryObject<Block> TERRACOTTA_BRICK = REGISTRY.registerBlock("terracotta_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> TERRACOTTA_BRICK_STAIRS = REGISTRY.registerBlock("terracotta_brick_stairs", REGISTRY.stair(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> TERRACOTTA_BRICK_SLAB = REGISTRY.registerBlock("terracotta_brick_slab", REGISTRY.slab(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> TERRACOTTA_BRICK_WALL = REGISTRY.registerBlock("terracotta_brick_wall", REGISTRY.wall(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceBlock> TERRACOTTA_BRICK_FENCE = REGISTRY.registerBlock("terracotta_brick_fence", REGISTRY.fence(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = REGISTRY.registerBlock("terracotta_brick_fence_gate", REGISTRY.fenceGate(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = REGISTRY.registerBlock("terracotta_brick_wide_post", REGISTRY.widePost(TERRACOTTA_BRICK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = REGISTRY.registerBlock("terracotta_brick_small_post", REGISTRY.narrowPost(TERRACOTTA_BRICK), ModRegistries.SIG);

  public static RegistryObject<Block> IRON_BRICK = REGISTRY.registerBlock("iron_brick", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> IRON_BRICK_STAIRS = REGISTRY.registerBlock("iron_brick_stairs", REGISTRY.stair(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> IRON_BRICK_SLAB = REGISTRY.registerBlock("iron_brick_slab", REGISTRY.slab(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> IRON_BRICK_WALL = REGISTRY.registerBlock("iron_brick_wall", REGISTRY.wall(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> IRON_BRICK_WIDE_POST = REGISTRY.registerBlock("iron_brick_wide_post", REGISTRY.widePost(IRON_BRICK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> IRON_BRICK_SMALL_POST = REGISTRY.registerBlock("iron_brick_small_post", REGISTRY.narrowPost(IRON_BRICK), ModRegistries.SIG);

  public static RegistryObject<Block> SOFT_STONE = REGISTRY.registerBlock("soft_stone", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> SOFT_STONE_STAIRS = REGISTRY.registerBlock("soft_stone_stairs", REGISTRY.stair(SOFT_STONE), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> SOFT_STONE_SLAB = REGISTRY.registerBlock("soft_stone_slab", REGISTRY.slab(SOFT_STONE), ModRegistries.SIG);
  public static RegistryObject<WallBlock> SOFT_STONE_WALL = REGISTRY.registerBlock("soft_stone_wall", REGISTRY.wall(SOFT_STONE), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> SOFT_STONE_WIDE_POST = REGISTRY.registerBlock("soft_stone_wide_post", REGISTRY.widePost(SOFT_STONE), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> SOFT_STONE_SMALL_POST = REGISTRY.registerBlock("soft_stone_small_post", REGISTRY.narrowPost(SOFT_STONE), ModRegistries.SIG);

  public static RegistryObject<Block> CRACKED_STONE = REGISTRY.registerBlock("cracked_stone", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> CRACKED_STONE_STAIRS = REGISTRY.registerBlock("cracked_stone_stairs", REGISTRY.stair(CRACKED_STONE), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> CRACKED_STONE_SLAB = REGISTRY.registerBlock("cracked_stone_slab", REGISTRY.slab(CRACKED_STONE), ModRegistries.SIG);
  public static RegistryObject<WallBlock> CRACKED_STONE_WALL = REGISTRY.registerBlock("cracked_stone_wall", REGISTRY.wall(CRACKED_STONE), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> CRACKED_STONE_WIDE_POST = REGISTRY.registerBlock("cracked_stone_wide_post", REGISTRY.widePost(CRACKED_STONE), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> CRACKED_STONE_SMALL_POST = REGISTRY.registerBlock("cracked_stone_small_post", REGISTRY.narrowPost(CRACKED_STONE), ModRegistries.SIG);

  public static RegistryObject<Block> WEATHERED_STONE = REGISTRY.registerBlock("weathered_stone", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> WEATHERED_STONE_STAIRS = REGISTRY.registerBlock("weathered_stone_stairs", REGISTRY.stair(WEATHERED_STONE), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> WEATHERED_STONE_SLAB = REGISTRY.registerBlock("weathered_stone_slab", REGISTRY.slab(WEATHERED_STONE), ModRegistries.SIG);
  public static RegistryObject<WallBlock> WEATHERED_STONE_WALL = REGISTRY.registerBlock("weathered_stone_wall", REGISTRY.wall(WEATHERED_STONE), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> WEATHERED_STONE_WIDE_POST = REGISTRY.registerBlock("weathered_stone_wide_post", REGISTRY.widePost(WEATHERED_STONE), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> WEATHERED_STONE_SMALL_POST = REGISTRY.registerBlock("weathered_stone_small_post", REGISTRY.narrowPost(WEATHERED_STONE), ModRegistries.SIG);

   public static RegistryObject<Block> SMOOTH_OBSIDIAN = REGISTRY.registerBlock("smooth_obsidian", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> SMOOTH_OBSIDIAN_STAIRS = REGISTRY.registerBlock("smooth_obsidian_stairs", REGISTRY.stair(SMOOTH_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> SMOOTH_OBSIDIAN_SLAB = REGISTRY.registerBlock("smooth_obsidian_slab", REGISTRY.slab(SMOOTH_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<WallBlock> SMOOTH_OBSIDIAN_WALL = REGISTRY.registerBlock("smooth_obsidian_wall", REGISTRY.wall(SMOOTH_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> SMOOTH_OBSIDIAN_WIDE_POST = REGISTRY.registerBlock("smooth_obsidian_wide_post", REGISTRY.widePost(SMOOTH_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> SMOOTH_OBSIDIAN_SMALL_POST = REGISTRY.registerBlock("smooth_obsidian_small_post", REGISTRY.narrowPost(SMOOTH_OBSIDIAN), ModRegistries.SIG);

   public static RegistryObject<Block> WEATHERED_OBSIDIAN = REGISTRY.registerBlock("weathered_obsidian", REGISTRY.block(Block::new, () -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f)), ModRegistries.SIG);
  public static RegistryObject<StairsBlock> WEATHERED_OBSIDIAN_STAIRS = REGISTRY.registerBlock("weathered_obsidian_stairs", REGISTRY.stair(WEATHERED_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> WEATHERED_OBSIDIAN_SLAB = REGISTRY.registerBlock("weathered_obsidian_slab", REGISTRY.slab(WEATHERED_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<WallBlock> WEATHERED_OBSIDIAN_WALL = REGISTRY.registerBlock("weathered_obsidian_wall", REGISTRY.wall(WEATHERED_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> WEATHERED_OBSIDIAN_WIDE_POST = REGISTRY.registerBlock("weathered_obsidian_wide_post", REGISTRY.widePost(WEATHERED_OBSIDIAN), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> WEATHERED_OBSIDIAN_SMALL_POST = REGISTRY.registerBlock("weathered_obsidian_small_post", REGISTRY.narrowPost(WEATHERED_OBSIDIAN), ModRegistries.SIG);

  // Ore Blocks
  public static RegistryObject<BaseOreBlock> AMETHYST_ORE = REGISTRY.registerBlock(ModMaterials.AMETHYST.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.AMETHYST), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> COPPER_ORE = REGISTRY.registerBlock(ModMaterials.COPPER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.COPPER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> LEAD_ORE = REGISTRY.registerBlock(ModMaterials.LEAD.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.LEAD), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> QUICKSILVER_ORE = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.QUICKSILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> SILVER_ORE = REGISTRY.registerBlock(ModMaterials.SILVER.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.SILVER), ModRegistries.SMG);
  public static RegistryObject<BaseOreBlock> TIN_ORE = REGISTRY.registerBlock(ModMaterials.TIN.oreName(), REGISTRY.ore(BaseOreBlock::new, ModMaterials.TIN), ModRegistries.SMG);

  // Blocks
  public static RegistryObject<Block> AMETHYST_BLOCK = REGISTRY.registerBlock(ModMaterials.AMETHYST.blockName(), REGISTRY.block(Block::new, ModMaterials.AMETHYST.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> AMETHYST_STAIRS = REGISTRY.registerBlock("amethyst_stairs", REGISTRY.stair(AMETHYST_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> AMETHYST_SLAB = REGISTRY.registerBlock("amethyst_slab", REGISTRY.slab(AMETHYST_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> AMETHYST_WALL = REGISTRY.registerBlock("amethyst_wall", REGISTRY.wall(AMETHYST_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> AMETHYST_WIDE_POST = REGISTRY.registerBlock("amethyst_wide_post", REGISTRY.widePost(AMETHYST_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> AMETHYST_SMALL_POST = REGISTRY.registerBlock("amethyst_small_post", REGISTRY.narrowPost(AMETHYST_BLOCK), ModRegistries.SIG);

  public static RegistryObject<Block> COPPER_BLOCK = REGISTRY.registerBlock(ModMaterials.COPPER.blockName(), REGISTRY.block(Block::new, ModMaterials.COPPER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> COPPER_STAIRS = REGISTRY.registerBlock("copper_stairs", REGISTRY.stair(COPPER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> COPPER_SLAB = REGISTRY.registerBlock("copper_slab", REGISTRY.slab(COPPER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> COPPER_WALL = REGISTRY.registerBlock("copper_wall", REGISTRY.wall(COPPER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> COPPER_WIDE_POST = REGISTRY.registerBlock("copper_wide_post", REGISTRY.widePost(COPPER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> COPPER_SMALL_POST = REGISTRY.registerBlock("copper_small_post", REGISTRY.narrowPost(COPPER_BLOCK), ModRegistries.SIG);

  public static RegistryObject<Block> LEAD_BLOCK = REGISTRY.registerBlock(ModMaterials.LEAD.blockName(), REGISTRY.block(Block::new, ModMaterials.LEAD.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> LEAD_STAIRS = REGISTRY.registerBlock("lead_stairs", REGISTRY.stair(LEAD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> LEAD_SLAB = REGISTRY.registerBlock("lead_slab", REGISTRY.slab(LEAD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> LEAD_WALL = REGISTRY.registerBlock("lead_wall", REGISTRY.wall(LEAD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> LEAD_WIDE_POST = REGISTRY.registerBlock("lead_wide_post", REGISTRY.widePost(LEAD_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> LEAD_SMALL_POST = REGISTRY.registerBlock("lead_small_post", REGISTRY.narrowPost(LEAD_BLOCK), ModRegistries.SIG);

  public static RegistryObject<Block> QUICKSILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.QUICKSILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.QUICKSILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> QUICKSILVER_STAIRS = REGISTRY.registerBlock("quicksilver_stairs", REGISTRY.stair(QUICKSILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> QUICKSILVER_SLAB = REGISTRY.registerBlock("quicksilver_slab", REGISTRY.slab(QUICKSILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> QUICKSILVER_WALL = REGISTRY.registerBlock("quicksilver_wall", REGISTRY.wall(QUICKSILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> QUICKSILVER_WIDE_POST = REGISTRY.registerBlock("quicksilver_wide_post", REGISTRY.widePost(QUICKSILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> QUICKSILVER_SMALL_POST = REGISTRY.registerBlock("quicksilver_small_post", REGISTRY.narrowPost(QUICKSILVER_BLOCK), ModRegistries.SIG);

  public static RegistryObject<Block> SILVER_BLOCK = REGISTRY.registerBlock(ModMaterials.SILVER.blockName(), REGISTRY.block(Block::new, ModMaterials.SILVER.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> SILVER_STAIRS = REGISTRY.registerBlock("silver_stairs", REGISTRY.stair(SILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> SILVER_SLAB = REGISTRY.registerBlock("silver_slab", REGISTRY.slab(SILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> SILVER_WALL = REGISTRY.registerBlock("silver_wall", REGISTRY.wall(SILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> SILVER_WIDE_POST = REGISTRY.registerBlock("silver_wide_post", REGISTRY.widePost(SILVER_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> SILVER_SMALL_POST = REGISTRY.registerBlock("silver_small_post", REGISTRY.narrowPost(SILVER_BLOCK), ModRegistries.SIG);

  public static RegistryObject<Block> TIN_BLOCK = REGISTRY.registerBlock(ModMaterials.TIN.blockName(), REGISTRY.block(Block::new, ModMaterials.TIN.getBlockProps()), ModRegistries.SMG);
  public static RegistryObject<StairsBlock> TIN_STAIRS = REGISTRY.registerBlock("tin_stairs", REGISTRY.stair(TIN_BLOCK), ModRegistries.SIG);
  public static RegistryObject<SlabBlock> TIN_SLAB = REGISTRY.registerBlock("tin_slab", REGISTRY.slab(TIN_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WallBlock> TIN_WALL = REGISTRY.registerBlock("tin_wall", REGISTRY.wall(TIN_BLOCK), ModRegistries.SIG);
  public static RegistryObject<WidePostBlock> TIN_WIDE_POST = REGISTRY.registerBlock("tin_wide_post", REGISTRY.widePost(TIN_BLOCK), ModRegistries.SIG);
  public static RegistryObject<NarrowPostBlock> TIN_SMALL_POST = REGISTRY.registerBlock("tin_small_post", REGISTRY.narrowPost(TIN_BLOCK), ModRegistries.SIG);

  public static void load() {
  }
}
