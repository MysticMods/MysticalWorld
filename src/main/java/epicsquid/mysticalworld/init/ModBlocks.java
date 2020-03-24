package epicsquid.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.mysticallib.MysticalLib;
import epicsquid.mysticallib.block.BaseOreBlock;
import epicsquid.mysticallib.block.NarrowPostBlock;
import epicsquid.mysticallib.block.WidePostBlock;
import epicsquid.mysticallib.material.MaterialType;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.blocks.AubergineCropBlock;
import epicsquid.mysticalworld.blocks.ThatchBlock;
import epicsquid.mysticalworld.blocks.WetMudBlock;
import epicsquid.mysticalworld.blocks.WetMudBrick;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Objects;

import static epicsquid.mysticalworld.MysticalWorld.RECIPES;
import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

@SuppressWarnings("unused")
public class ModBlocks {

  @SuppressWarnings("unchecked")
  public static NonNullFunction<Block.Properties, BaseOreBlock> oreBlock(MaterialType material) {
    return (o) -> new BaseOreBlock(o, material.getMinXP(), material.getMaxXP());
  }

  public static <T extends Item> ItemModelBuilder itemModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider p) {
    return p.blockItem(ctx::getEntry);
  }

  public static <T extends Item> ItemModelBuilder inventoryModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider p) {
    return p.blockWithInventoryModel(ctx::getEntry);
  }

  public static <T extends Block> void simpleBlockState(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider p) {
    p.simpleBlock(ctx.getEntry());
  }

  public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> simpleBlockState(ResourceLocation parent) {
    return (ctx, p) -> p.simpleBlock(ctx.getEntry(), p.getExistingFile(parent));
  }

  public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> simpleBlockState(String parent) {
    return simpleBlockState(new ResourceLocation(MysticalWorld.MODID, parent));
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
      p.fenceInventory(name(ctx.getEntry()), p.blockTexture(parent.get()));
    };
  }

  public static <T extends WallBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> wall(RegistryEntry<Block> parent) {
    return (ctx, p) -> {
      p.wallBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.wallInventory(name(ctx.getEntry()), p.blockTexture(parent.get()));
    };
  }

  public static <T extends FenceGateBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gate(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.fenceGateBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends WidePostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> widePost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalLib.MODID, "wide_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends NarrowPostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> narrowPost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalLib.MODID, "narrow_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  private static <T extends IForgeRegistryEntry<?>> String name(T block) {
    return Objects.requireNonNull(block.getRegistryName()).getPath();
  }

  public static RegistryEntry<ThatchBlock> THATCH = REGISTRATE.block("thatch", ThatchBlock::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .item()
      .model(NonNullBiConsumer.noop())
      .build()
      .blockstate(NonNullBiConsumer.noop())
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shapedRecipe(ModBlocks.THATCH.get(), 32)
              .patternLine("XY")
              .patternLine("YX")
              .key('X', Blocks.HAY_BLOCK)
              .key('Y', Tags.Items.CROPS_WHEAT)
              .addCriterion("has_hay", p.hasItem(Blocks.HAY_BLOCK))
              .addCriterion("has_wheat", p.hasItem(Items.WHEAT))
              .build(p)
      )
      .register();

  public static RegistryEntry<AubergineCropBlock> AUBERGINE_CROP = REGISTRATE.block("aubergine_crop", AubergineCropBlock::new)
      .properties(o -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly())
      .blockstate(NonNullBiConsumer.noop())
      .register();

  // MUD BLOCK

  public static RegistryEntry<WetMudBlock> WET_MUD_BLOCK = REGISTRATE.block("wet_mud_block", WetMudBlock::new)
      .properties((o) -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(simpleBlockState("block/wet_mud_block"))
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shapedRecipe(ModBlocks.WET_MUD_BLOCK.get(), 8)
              .patternLine("XXX")
              .patternLine("XWX")
              .patternLine("XXX")
              .key('X', Blocks.DIRT)
              .key('W', Items.WATER_BUCKET)
              .addCriterion("has_dirt", p.hasItem(Blocks.DIRT))
              .build(p)
      )
      .register();

  public static RegistryEntry<Block> MUD_BLOCK = REGISTRATE.block("mud_block", Block::new)
      .properties(o -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.smelting(ModBlocks.WET_MUD_BLOCK, ModBlocks.MUD_BLOCK, 0.15f, false, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> MUD_BLOCK_STAIRS = REGISTRATE.stairs("mud_block_stairs", MUD_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BLOCK_SLAB = REGISTRATE.slab("mud_block_slab", MUD_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BLOCK_WALL = REGISTRATE.wall("mud_block_wall", MUD_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BLOCK_FENCE = REGISTRATE.fence("mud_block_fence", MUD_BLOCK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = REGISTRATE.gate("mud_block_fence_gate", MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SLAB, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> MUD_BLOCK_WIDE_POST = REGISTRATE.widePost("mud_block_wide_post", MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> MUD_BLOCK_SMALL_POST = REGISTRATE.narrowPost("mud_block_small_post", MUD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BLOCK))
      .register();

  // MUD BRICK

  public static RegistryEntry<WetMudBrick> WET_MUD_BRICK = REGISTRATE.block("wet_mud_brick", WetMudBrick::new)
      .properties(o -> Block.Properties.create(Material.EARTH).sound(SoundType.SLIME))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, p)
      )
      .register();

  public static RegistryEntry<Block> MUD_BRICK = REGISTRATE.block("mud_brick", Block::new)
      .properties(o -> Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        RECIPES.smelting(ModBlocks.WET_MUD_BRICK, ModBlocks.MUD_BRICK, 0.15f, false, p);
        RECIPES.twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BLOCK.get()), ModBlocks.MUD_BRICK.get())
            .addCriterion("has_mud_block", p.hasItem(ModBlocks.MUD_BLOCK.get()))
            .build(p, "mud_bricks_from_mud_blocks_stonecutting");
      })
      .register();

  public static RegistryEntry<StairsBlock> MUD_BRICK_STAIRS = REGISTRATE.stairs("mud_brick_stairs", MUD_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BRICK_SLAB = REGISTRATE.slab("mud_brick_slab", MUD_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BRICK_WALL = REGISTRATE.wall("mud_brick_wall", MUD_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BRICK_FENCE = REGISTRATE.fence("mud_brick_fence", MUD_BRICK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = REGISTRATE.gate("mud_brick_fence_gate", MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SLAB, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> MUD_BRICK_WIDE_POST = REGISTRATE.widePost("mud_brick_wide_post", MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> MUD_BRICK_SMALL_POST = REGISTRATE.narrowPost("mud_brick_small_post", MUD_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BRICK))
      .register();

  // CHARRED STUFF

  public static RegistryEntry<LogBlock> CHARRED_LOG = REGISTRATE.log("charred_log", MaterialColor.BROWN_TERRACOTTA)
      .properties(o -> o.sound(SoundType.WOOD).hardnessAndResistance(2.0f))
      .tag(BlockTags.LOGS)
      .item()
      .tag(ItemTags.LOGS)
      .model(ModBlocks::itemModel)
      .build()
      .register();

  public static RegistryEntry<Block> CHARRED_PLANKS = REGISTRATE.block("charred_planks", Material.WOOD, Block::new)
      .properties(o -> o.sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f))
      .tag(BlockTags.PLANKS)
      .item()
      .tag(ItemTags.PLANKS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) -> RECIPES.planks(ModBlocks.CHARRED_LOG, ctx::getEntry, p))
      .blockstate(ModBlocks::simpleBlockState)
      .register();


  public static RegistryEntry<StairsBlock> CHARRED_STAIRS = REGISTRATE.stairs("charred_stairs", CHARRED_PLANKS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<SlabBlock> CHARRED_SLAB = REGISTRATE.slab("charred_slab", CHARRED_PLANKS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceBlock> CHARRED_FENCE = REGISTRATE.fence("charred_fence", CHARRED_PLANKS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceGateBlock> CHARRED_FENCE_GATE = REGISTRATE.gate("charred_fence_gate", CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SLAB, null, p)
      )
      .blockstate(gate(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<WallBlock> CHARRED_WALL = REGISTRATE.wall("charred_wall", CHARRED_PLANKS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<WidePostBlock> CHARRED_WIDE_POST = REGISTRATE.widePost("charred_wide_post", CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<NarrowPostBlock> CHARRED_SMALL_POST = REGISTRATE.narrowPost("charred_small_post", CHARRED_PLANKS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.CHARRED_PLANKS))
      .register();

  // TERRACOTTA BRICK

  public static RegistryEntry<Block> TERRACOTTA_BRICK = REGISTRATE.block("terracotta_brick", Block::new)
      .properties(o -> o.sound(SoundType.STONE).hardnessAndResistance(2f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Items.TERRACOTTA), ModBlocks.TERRACOTTA_BRICK.get())
            .addCriterion("has_stone", p.hasItem(Items.TERRACOTTA))
            .build(p, "terracotta_bricks_from_terracotta_stonecutting");
        RECIPES.twoByTwo(() -> Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, null, p);
      })
      .register();

  public static RegistryEntry<StairsBlock> TERRACOTTA_BRICK_STAIRS = REGISTRATE.stairs("terracotta_brick_stairs", TERRACOTTA_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = REGISTRATE.slab("terracotta_brick_slab", TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<WallBlock> TERRACOTTA_BRICK_WALL = REGISTRATE.wall("terracotta_brick_wall", TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = REGISTRATE.fence("terracotta_brick_fence", TERRACOTTA_BRICK)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_FENCE, true, p)
      )
      .blockstate(fence(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = REGISTRATE.gate("terracotta_brick_fence_gate", TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.fenceGate(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SLAB, null, p)
      )
      .blockstate(gate(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = REGISTRATE.widePost("terracotta_brick_wide_post", TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = REGISTRATE.narrowPost("terracotta_brick_small_post", TERRACOTTA_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  // IRON BRICK

  public static RegistryEntry<Block> IRON_BRICK = REGISTRATE.block("iron_brick", Material.IRON, Block::new)
      .properties(o -> o.sound(SoundType.METAL).hardnessAndResistance(3.2f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p);
      })
      .register();

  public static RegistryEntry<StairsBlock> IRON_BRICK_STAIRS = REGISTRATE.stairs("iron_brick_stairs", IRON_BRICK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> IRON_BRICK_SLAB = REGISTRATE.slab("iron_brick_slab", IRON_BRICK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<WallBlock> IRON_BRICK_WALL = REGISTRATE.wall("iron_brick_wall", IRON_BRICK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> IRON_BRICK_WIDE_POST = REGISTRATE.widePost("iron_brick_wide_post", IRON_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> IRON_BRICK_SMALL_POST = REGISTRATE.narrowPost("iron_brick_small_post", IRON_BRICK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.IRON_BRICK))
      .register();

  // SOFT STONE

  public static RegistryEntry<Block> SOFT_STONE = REGISTRATE.block("soft_stone", Block::new)
      .properties(o -> o.sound(SoundType.STONE).hardnessAndResistance(1f))
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        RECIPES.twoByTwo(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Items.SMOOTH_STONE), ModBlocks.SOFT_STONE.get())
            .addCriterion("has_stone", p.hasItem(Items.SMOOTH_STONE))
            .build(p, "soft_stone_from_smooth_stone_stonecutting");
      })
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> SOFT_STONE_STAIRS = REGISTRATE.stairs("soft_stone_stairs", SOFT_STONE)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<SlabBlock> SOFT_STONE_SLAB = REGISTRATE.slab("soft_stone_slab", SOFT_STONE)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<WallBlock> SOFT_STONE_WALL = REGISTRATE.wall("soft_stone_wall", SOFT_STONE)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<WidePostBlock> SOFT_STONE_WIDE_POST = REGISTRATE.widePost("soft_stone_wide_post", SOFT_STONE)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<NarrowPostBlock> SOFT_STONE_SMALL_POST = REGISTRATE.narrowPost("soft_stone_small_post", SOFT_STONE)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_STONE))
      .register();

  // SMOOTH OBSIDIAN

  public static RegistryEntry<Block> SMOOTH_OBSIDIAN = REGISTRATE.block("smooth_obsidian", Block::new)
      .properties(o -> o.sound(SoundType.STONE).hardnessAndResistance(20f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        RECIPES.twoByTwo(() -> Items.OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Items.OBSIDIAN), ModBlocks.SMOOTH_OBSIDIAN.get())
            .addCriterion("has_obsidian", p.hasItem(Items.OBSIDIAN))
            .build(p, "smooth_obsidian_from_obsidian_stonecutting");
      })
      .register();

  public static RegistryEntry<StairsBlock> SMOOTH_OBSIDIAN_STAIRS = REGISTRATE.stairs("smooth_obsidian_stairs", SMOOTH_OBSIDIAN)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SMOOTH_OBSIDIAN))
      .register();

  public static RegistryEntry<SlabBlock> SMOOTH_OBSIDIAN_SLAB = REGISTRATE.slab("smooth_obsidian_slab", SMOOTH_OBSIDIAN)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SMOOTH_OBSIDIAN))
      .register();

  public static RegistryEntry<WallBlock> SMOOTH_OBSIDIAN_WALL = REGISTRATE.wall("smooth_obsidian_wall", SMOOTH_OBSIDIAN)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.SMOOTH_OBSIDIAN))
      .register();

  public static RegistryEntry<WidePostBlock> SMOOTH_OBSIDIAN_WIDE_POST = REGISTRATE.widePost("smooth_obsidian_wide_post", SMOOTH_OBSIDIAN)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SMOOTH_OBSIDIAN))
      .register();

  public static RegistryEntry<NarrowPostBlock> SMOOTH_OBSIDIAN_SMALL_POST = REGISTRATE.narrowPost("smooth_obsidian_small_post", SMOOTH_OBSIDIAN)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SMOOTH_OBSIDIAN))
      .register();

  // AMETHYST
  public static RegistryEntry<BaseOreBlock> AMETHYST_ORE = REGISTRATE.block(ModMaterials.AMETHYST.oreName(), oreBlock(ModMaterials.AMETHYST))
      .properties(ModMaterials.AMETHYST.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.AMETHYST_ORE)
      .build()
      .tag(MWTags.Blocks.AMETHYST_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> AMETHYST_BLOCK = REGISTRATE.block(ModMaterials.AMETHYST.blockName(), Block::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.AMETHYST_BLOCK)
      .build()
      .tag(MWTags.Blocks.AMETHYST_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.AMETHYST_GEM, ModBlocks.AMETHYST_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> AMETHYST_STAIRS = REGISTRATE.stairs("amethyst_stairs", AMETHYST_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> AMETHYST_SLAB = REGISTRATE.slab("amethyst_slab", AMETHYST_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> AMETHYST_WALL = REGISTRATE.wall("amethyst_wall", AMETHYST_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> AMETHYST_WIDE_POST = REGISTRATE.widePost("amethyst_wide_post", AMETHYST_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> AMETHYST_SMALL_POST = REGISTRATE.narrowPost("amethyst_small_post", AMETHYST_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.AMETHYST_BLOCK))
      .register();

  // COPPER
  public static RegistryEntry<BaseOreBlock> COPPER_ORE = REGISTRATE.block(ModMaterials.COPPER.oreName(), oreBlock(ModMaterials.COPPER))
      .properties(ModMaterials.COPPER.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.COPPER_ORE)
      .build()
      .tag(MWTags.Blocks.COPPER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> COPPER_BLOCK = REGISTRATE.block(ModMaterials.COPPER.blockName(), Block::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.COPPER_BLOCK)
      .build()
      .tag(MWTags.Blocks.COPPER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.COPPER_INGOT, ModBlocks.COPPER_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> COPPER_STAIRS = REGISTRATE.stairs("copper_stairs", COPPER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> COPPER_SLAB = REGISTRATE.slab("copper_slab", COPPER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> COPPER_WALL = REGISTRATE.wall("copper_wall", COPPER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> COPPER_WIDE_POST = REGISTRATE.widePost("copper_wide_post", COPPER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> COPPER_SMALL_POST = REGISTRATE.narrowPost("copper_small_post", COPPER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.COPPER_BLOCK))
      .register();


  // LEAD
  public static RegistryEntry<BaseOreBlock> LEAD_ORE = REGISTRATE.block(ModMaterials.LEAD.oreName(), oreBlock(ModMaterials.LEAD))
      .properties(ModMaterials.LEAD.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.LEAD_ORE)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .tag(MWTags.Blocks.LEAD_ORE)
      .register();

  public static RegistryEntry<Block> LEAD_BLOCK = REGISTRATE.block(ModMaterials.LEAD.blockName(), Block::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.LEAD_BLOCK)
      .build()
      .tag(MWTags.Blocks.LEAD_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.LEAD_INGOT, ModBlocks.LEAD_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> LEAD_STAIRS = REGISTRATE.stairs("lead_stairs", LEAD_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> LEAD_SLAB = REGISTRATE.slab("lead_slab", LEAD_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> LEAD_WALL = REGISTRATE.wall("lead_wall", LEAD_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> LEAD_WIDE_POST = REGISTRATE.widePost("lead_wide_post", LEAD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> LEAD_SMALL_POST = REGISTRATE.narrowPost("lead_small_post", LEAD_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.LEAD_BLOCK))
      .register();


  // QUICKSILVER
  public static RegistryEntry<BaseOreBlock> QUICKSILVER_ORE = REGISTRATE.block(ModMaterials.QUICKSILVER.oreName(), oreBlock(ModMaterials.QUICKSILVER))
      .properties(ModMaterials.QUICKSILVER.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUICKSILVER_ORE)
      .build()
      .tag(MWTags.Blocks.QUICKSILVER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> QUICKSILVER_BLOCK = REGISTRATE.block(ModMaterials.QUICKSILVER.blockName(), Block::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUICKSILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.QUICKSILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.QUICKSILVER_INGOT, ModBlocks.QUICKSILVER_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> QUICKSILVER_STAIRS = REGISTRATE.stairs("quicksilver_stairs", QUICKSILVER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> QUICKSILVER_SLAB = REGISTRATE.slab("quicksilver_slab", QUICKSILVER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> QUICKSILVER_WALL = REGISTRATE.wall("quicksilver_wall", QUICKSILVER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> QUICKSILVER_WIDE_POST = REGISTRATE.widePost("quicksilver_wide_post", QUICKSILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> QUICKSILVER_SMALL_POST = REGISTRATE.narrowPost("quicksilver_small_post", QUICKSILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  // SILVER
  public static RegistryEntry<BaseOreBlock> SILVER_ORE = REGISTRATE.block(ModMaterials.SILVER.oreName(), oreBlock(ModMaterials.SILVER))
      .properties(ModMaterials.SILVER.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.SILVER_ORE)
      .build()
      .tag(MWTags.Blocks.SILVER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> SILVER_BLOCK = REGISTRATE.block(ModMaterials.SILVER.blockName(), Block::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.SILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.SILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.SILVER_INGOT, ModBlocks.SILVER_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> SILVER_STAIRS = REGISTRATE.stairs("silver_stairs", SILVER_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> SILVER_SLAB = REGISTRATE.slab("silver_slab", SILVER_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> SILVER_WALL = REGISTRATE.wall("silver_wall", SILVER_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> SILVER_WIDE_POST = REGISTRATE.widePost("silver_wide_post", SILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> SILVER_SMALL_POST = REGISTRATE.narrowPost("silver_small_post", SILVER_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SILVER_BLOCK))
      .register();


  // TIN
  public static RegistryEntry<BaseOreBlock> TIN_ORE = REGISTRATE.block(ModMaterials.TIN.oreName(), oreBlock(ModMaterials.TIN))
      .properties(ModMaterials.TIN.getOreBlockProperties())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.TIN_ORE)
      .build()
      .tag(MWTags.Blocks.TIN_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> TIN_BLOCK = REGISTRATE.block(ModMaterials.TIN.blockName(), Block::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.TIN_BLOCK)
      .build()
      .tag(MWTags.Blocks.TIN_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.storage(MWTags.Items.TIN_INGOT, ModBlocks.TIN_BLOCK, p)
      )
      .register();

  public static RegistryEntry<StairsBlock> TIN_STAIRS = REGISTRATE.stairs("tin_stairs", TIN_BLOCK)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.stairs(ModBlocks.TIN_BLOCK, ModBlocks.TIN_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> TIN_SLAB = REGISTRATE.slab("tin_slab", TIN_BLOCK)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          RECIPES.slab(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> TIN_WALL = REGISTRATE.wall("tin_wall", TIN_BLOCK)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          RECIPES.wall(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SLAB, true, p)
      )
      .blockstate(wall(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> TIN_WIDE_POST = REGISTRATE.widePost("tin_wide_post", TIN_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> TIN_SMALL_POST = REGISTRATE.narrowPost("tin_small_post", TIN_BLOCK)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TIN_BLOCK))
      .register();


  public static void load() {
  }
}
