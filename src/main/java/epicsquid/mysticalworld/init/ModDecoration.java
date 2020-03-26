package epicsquid.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import epicsquid.mysticallib.MysticalLib;
import epicsquid.mysticallib.block.NarrowPostBlock;
import epicsquid.mysticallib.block.WidePostBlock;
import epicsquid.mysticalworld.MWTags;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import static epicsquid.mysticalworld.MysticalWorld.RECIPES;
import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

public class ModDecoration {
  public static RegistryEntry<StairsBlock> MUD_BLOCK_STAIRS = REGISTRATE.stairs("mud_block_stairs", () -> ModBlocks.MUD_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> MUD_BLOCK_SLAB = REGISTRATE.slab("mud_block_slab", () -> ModBlocks.MUD_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = REGISTRATE.gate("mud_block_fence_gate", () -> ModBlocks.MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> MUD_BLOCK_WALL = REGISTRATE.wall("mud_block_wall", () -> ModBlocks.MUD_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<FenceBlock> MUD_BLOCK_FENCE = REGISTRATE.fence("mud_block_fence", () -> ModBlocks.MUD_BLOCK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> MUD_BLOCK_WIDE_POST = REGISTRATE.widePost("mud_block_wide_post", () -> ModBlocks.MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> MUD_BLOCK_SMALL_POST = REGISTRATE.narrowPost("mud_block_small_post", () -> ModBlocks.MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BLOCK, ModDecoration.MUD_BLOCK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> MUD_BRICK_STAIRS = REGISTRATE.stairs("mud_brick_stairs", () -> ModBlocks.MUD_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<SlabBlock> MUD_BRICK_SLAB = REGISTRATE.slab("mud_brick_slab", () -> ModBlocks.MUD_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = REGISTRATE.gate("mud_brick_fence_gate", () -> ModBlocks.MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<WallBlock> MUD_BRICK_WALL = REGISTRATE.wall("mud_brick_wall", () -> ModBlocks.MUD_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<FenceBlock> MUD_BRICK_FENCE = REGISTRATE.fence("mud_brick_fence", () -> ModBlocks.MUD_BRICK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<WidePostBlock> MUD_BRICK_WIDE_POST = REGISTRATE.widePost("mud_brick_wide_post", () -> ModBlocks.MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<NarrowPostBlock> MUD_BRICK_SMALL_POST = REGISTRATE.narrowPost("mud_brick_small_post", () -> ModBlocks.MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BRICK, ModDecoration.MUD_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BRICK))
      .register();
  public static RegistryEntry<StairsBlock> CHARRED_STAIRS = REGISTRATE.stairs("charred_stairs", () -> ModBlocks.CHARRED_PLANKS)
      .tag(BlockTags.WOODEN_STAIRS)
      .item()
      .tag(ItemTags.WOODEN_STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<SlabBlock> CHARRED_SLAB = REGISTRATE.slab("charred_slab", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .tag(ItemTags.WOODEN_SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.WOODEN_SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<WallBlock> CHARRED_WALL = REGISTRATE.wall("charred_wall", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<FenceGateBlock> CHARRED_FENCE_GATE = REGISTRATE.gate("charred_fence_gate", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<FenceBlock> CHARRED_FENCE = REGISTRATE.fence("charred_fence", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<WidePostBlock> CHARRED_WIDE_POST = REGISTRATE.widePost("charred_wide_post", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<NarrowPostBlock> CHARRED_SMALL_POST = REGISTRATE.narrowPost("charred_small_post", () -> ModBlocks.CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.CHARRED_PLANKS, ModDecoration.CHARRED_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.CHARRED_PLANKS))
      .register();
  public static RegistryEntry<StairsBlock> TERRACOTTA_BRICK_STAIRS = REGISTRATE.stairs("terracotta_brick_stairs", () -> ModBlocks.TERRACOTTA_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = REGISTRATE.slab("terracotta_brick_slab", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = REGISTRATE.gate("terracotta_brick_fence_gate", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<WallBlock> TERRACOTTA_BRICK_WALL = REGISTRATE.wall("terracotta_brick_wall", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = REGISTRATE.fence("terracotta_brick_fence", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = REGISTRATE.widePost("terracotta_brick_wide_post", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = REGISTRATE.narrowPost("terracotta_brick_small_post", () -> ModBlocks.TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TERRACOTTA_BRICK, ModDecoration.TERRACOTTA_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TERRACOTTA_BRICK))
      .register();
  public static RegistryEntry<StairsBlock> IRON_BRICK_STAIRS = REGISTRATE.stairs("iron_brick_stairs", () -> ModBlocks.IRON_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.IRON_BRICK, ModDecoration.IRON_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.IRON_BRICK))
      .register();
  public static RegistryEntry<SlabBlock> IRON_BRICK_SLAB = REGISTRATE.slab("iron_brick_slab", () -> ModBlocks.IRON_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.IRON_BRICK, ModDecoration.IRON_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.IRON_BRICK))
      .register();
  public static RegistryEntry<WallBlock> IRON_BRICK_WALL = REGISTRATE.wall("iron_brick_wall", () -> ModBlocks.IRON_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.IRON_BRICK, ModDecoration.IRON_BRICK_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.IRON_BRICK))
      .register();
  public static RegistryEntry<WidePostBlock> IRON_BRICK_WIDE_POST = REGISTRATE.widePost("iron_brick_wide_post", () -> ModBlocks.IRON_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.IRON_BRICK, ModDecoration.IRON_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.IRON_BRICK))
      .register();
  public static RegistryEntry<NarrowPostBlock> IRON_BRICK_SMALL_POST = REGISTRATE.narrowPost("iron_brick_small_post", () -> ModBlocks.IRON_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.IRON_BRICK, ModDecoration.IRON_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.IRON_BRICK))
      .register();
  public static RegistryEntry<StairsBlock> SOFT_STONE_STAIRS = REGISTRATE.stairs("soft_stone_stairs", () -> ModBlocks.SOFT_STONE)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SOFT_STONE, ModDecoration.SOFT_STONE_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SOFT_STONE))
      .register();
  public static RegistryEntry<SlabBlock> SOFT_STONE_SLAB = REGISTRATE.slab("soft_stone_slab", () -> ModBlocks.SOFT_STONE)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SOFT_STONE, ModDecoration.SOFT_STONE_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SOFT_STONE))
      .register();
  public static RegistryEntry<WallBlock> SOFT_STONE_WALL = REGISTRATE.wall("soft_stone_wall", () -> ModBlocks.SOFT_STONE)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SOFT_STONE, ModDecoration.SOFT_STONE_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.SOFT_STONE))
      .register();
  public static RegistryEntry<WidePostBlock> SOFT_STONE_WIDE_POST = REGISTRATE.widePost("soft_stone_wide_post", () -> ModBlocks.SOFT_STONE)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SOFT_STONE, ModDecoration.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_STONE))
      .register();
  public static RegistryEntry<NarrowPostBlock> SOFT_STONE_SMALL_POST = REGISTRATE.narrowPost("soft_stone_small_post", () -> ModBlocks.SOFT_STONE)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModDecoration.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_STONE))
      .register();
  public static RegistryEntry<StairsBlock> SOFT_OBSIDIAN_STAIRS = REGISTRATE.stairs("soft_obsidian_stairs", () -> ModBlocks.SOFT_OBSIDIAN)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SOFT_OBSIDIAN, ModDecoration.SOFT_OBSIDIAN_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SOFT_OBSIDIAN))
      .register();
  public static RegistryEntry<SlabBlock> SOFT_OBSIDIAN_SLAB = REGISTRATE.slab("soft_obsidian_slab", () -> ModBlocks.SOFT_OBSIDIAN)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SOFT_OBSIDIAN, ModDecoration.SOFT_OBSIDIAN_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SOFT_OBSIDIAN))
      .register();
  public static RegistryEntry<WallBlock> SOFT_OBSIDIAN_WALL = REGISTRATE.wall("soft_obsidian_wall", () -> ModBlocks.SOFT_OBSIDIAN)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SOFT_OBSIDIAN, ModDecoration.SOFT_OBSIDIAN_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.SOFT_OBSIDIAN))
      .register();
  public static RegistryEntry<WidePostBlock> SOFT_OBSIDIAN_WIDE_POST = REGISTRATE.widePost("soft_obsidian_wide_post", () -> ModBlocks.SOFT_OBSIDIAN)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SOFT_OBSIDIAN, ModDecoration.SOFT_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_OBSIDIAN))
      .register();
  public static RegistryEntry<NarrowPostBlock> SOFT_OBSIDIAN_SMALL_POST = REGISTRATE.narrowPost("soft_obsidian_small_post", () -> ModBlocks.SOFT_OBSIDIAN)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SOFT_OBSIDIAN, ModDecoration.SOFT_OBSIDIAN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<StairsBlock> AMETHYST_STAIRS = REGISTRATE.stairs("amethyst_stairs", () -> ModBlocks.AMETHYST_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.AMETHYST_BLOCK, ModDecoration.AMETHYST_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.AMETHYST_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> AMETHYST_SLAB = REGISTRATE.slab("amethyst_slab", () -> ModBlocks.AMETHYST_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.AMETHYST_BLOCK, ModDecoration.AMETHYST_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.AMETHYST_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> AMETHYST_WALL = REGISTRATE.wall("amethyst_wall", () -> ModBlocks.AMETHYST_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.AMETHYST_BLOCK, ModDecoration.AMETHYST_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.AMETHYST_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> AMETHYST_WIDE_POST = REGISTRATE.widePost("amethyst_wide_post", () -> ModBlocks.AMETHYST_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.AMETHYST_BLOCK, ModDecoration.AMETHYST_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.AMETHYST_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> AMETHYST_SMALL_POST = REGISTRATE.narrowPost("amethyst_small_post", () -> ModBlocks.AMETHYST_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.AMETHYST_BLOCK, ModDecoration.AMETHYST_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.AMETHYST_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> COPPER_STAIRS = REGISTRATE.stairs("copper_stairs", () -> ModBlocks.COPPER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.COPPER_BLOCK, ModDecoration.COPPER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.COPPER_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> COPPER_SLAB = REGISTRATE.slab("copper_slab", () -> ModBlocks.COPPER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.COPPER_BLOCK, ModDecoration.COPPER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.COPPER_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> COPPER_WALL = REGISTRATE.wall("copper_wall", () -> ModBlocks.COPPER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.COPPER_BLOCK, ModDecoration.COPPER_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.COPPER_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> COPPER_WIDE_POST = REGISTRATE.widePost("copper_wide_post", () -> ModBlocks.COPPER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.COPPER_BLOCK, ModDecoration.COPPER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.COPPER_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> COPPER_SMALL_POST = REGISTRATE.narrowPost("copper_small_post", () -> ModBlocks.COPPER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.COPPER_BLOCK, ModDecoration.COPPER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.COPPER_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> LEAD_STAIRS = REGISTRATE.stairs("lead_stairs", () -> ModBlocks.LEAD_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.LEAD_BLOCK, ModDecoration.LEAD_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.LEAD_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> LEAD_SLAB = REGISTRATE.slab("lead_slab", () -> ModBlocks.LEAD_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.LEAD_BLOCK, ModDecoration.LEAD_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.LEAD_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> LEAD_WALL = REGISTRATE.wall("lead_wall", () -> ModBlocks.LEAD_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.LEAD_BLOCK, ModDecoration.LEAD_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.LEAD_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> LEAD_WIDE_POST = REGISTRATE.widePost("lead_wide_post", () -> ModBlocks.LEAD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.LEAD_BLOCK, ModDecoration.LEAD_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.LEAD_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> LEAD_SMALL_POST = REGISTRATE.narrowPost("lead_small_post", () -> ModBlocks.LEAD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.LEAD_BLOCK, ModDecoration.LEAD_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.LEAD_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> QUICKSILVER_STAIRS = REGISTRATE.stairs("quicksilver_stairs", () -> ModBlocks.QUICKSILVER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.QUICKSILVER_BLOCK, ModDecoration.QUICKSILVER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.QUICKSILVER_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> QUICKSILVER_SLAB = REGISTRATE.slab("quicksilver_slab", () -> ModBlocks.QUICKSILVER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.QUICKSILVER_BLOCK, ModDecoration.QUICKSILVER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.QUICKSILVER_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> QUICKSILVER_WALL = REGISTRATE.wall("quicksilver_wall", () -> ModBlocks.QUICKSILVER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.QUICKSILVER_BLOCK, ModDecoration.QUICKSILVER_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.QUICKSILVER_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> QUICKSILVER_WIDE_POST = REGISTRATE.widePost("quicksilver_wide_post", () -> ModBlocks.QUICKSILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.QUICKSILVER_BLOCK, ModDecoration.QUICKSILVER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.QUICKSILVER_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> QUICKSILVER_SMALL_POST = REGISTRATE.narrowPost("quicksilver_small_post", () -> ModBlocks.QUICKSILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.QUICKSILVER_BLOCK, ModDecoration.QUICKSILVER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.QUICKSILVER_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> SILVER_STAIRS = REGISTRATE.stairs("silver_stairs", () -> ModBlocks.SILVER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SILVER_BLOCK, ModDecoration.SILVER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SILVER_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> SILVER_SLAB = REGISTRATE.slab("silver_slab", () -> ModBlocks.SILVER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SILVER_BLOCK, ModDecoration.SILVER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SILVER_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> SILVER_WALL = REGISTRATE.wall("silver_wall", () -> ModBlocks.SILVER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SILVER_BLOCK, ModDecoration.SILVER_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.SILVER_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> SILVER_WIDE_POST = REGISTRATE.widePost("silver_wide_post", () -> ModBlocks.SILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SILVER_BLOCK, ModDecoration.SILVER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SILVER_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> SILVER_SMALL_POST = REGISTRATE.narrowPost("silver_small_post", () -> ModBlocks.SILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SILVER_BLOCK, ModDecoration.SILVER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SILVER_BLOCK))
      .register();
  public static RegistryEntry<StairsBlock> TIN_STAIRS = REGISTRATE.stairs("tin_stairs", () -> ModBlocks.TIN_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.TIN_BLOCK, ModDecoration.TIN_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.TIN_BLOCK))
      .register();
  public static RegistryEntry<SlabBlock> TIN_SLAB = REGISTRATE.slab("tin_slab", () -> ModBlocks.TIN_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.TIN_BLOCK, ModDecoration.TIN_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.TIN_BLOCK))
      .register();
  public static RegistryEntry<WallBlock> TIN_WALL = REGISTRATE.wall("tin_wall", () -> ModBlocks.TIN_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TIN_BLOCK, ModDecoration.TIN_WALL, true, p)
      )
      .blockstate(wall(ModBlocks.TIN_BLOCK))
      .register();
  public static RegistryEntry<WidePostBlock> TIN_WIDE_POST = REGISTRATE.widePost("tin_wide_post", () -> ModBlocks.TIN_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TIN_BLOCK, ModDecoration.TIN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TIN_BLOCK))
      .register();
  public static RegistryEntry<NarrowPostBlock> TIN_SMALL_POST = REGISTRATE.narrowPost("tin_small_post", () -> ModBlocks.TIN_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TIN_BLOCK, ModDecoration.TIN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TIN_BLOCK))
      .register();

  public static void load () {
  }

  public static <T extends StairsBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> stairs(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.stairsBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends SlabBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> slab(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.slabBlock(ctx.getEntry(), p.blockTexture(parent.get()), p.blockTexture(parent.get()));
  }

  public static <T extends FenceBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> fence(RegistryEntry<Block> parent) {
    return (ctx, p) -> {
      p.fenceBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.fenceInventory(ModBlocks.name(ctx.getEntry()), p.blockTexture(parent.get()));
    };
  }

  public static <T extends WallBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> wall(RegistryEntry<Block> parent) {
    return (ctx, p) -> {
      p.wallBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.wallInventory(ModBlocks.name(ctx.getEntry()), p.blockTexture(parent.get()));
    };
  }

  public static <T extends FenceGateBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gate(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.fenceGateBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends WidePostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> widePost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(ModBlocks.name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalLib.MODID, "wide_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends NarrowPostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> narrowPost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(ModBlocks.name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalLib.MODID, "narrow_post"))).texture("wall", p.blockTexture(parent.get()))));
  }
}
