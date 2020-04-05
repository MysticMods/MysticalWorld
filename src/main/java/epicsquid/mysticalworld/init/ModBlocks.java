package epicsquid.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
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
import epicsquid.mysticalworld.blocks.stairs.*;
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
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Objects;

import static epicsquid.mysticalworld.MysticalWorld.RECIPES;
import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Registrate is DONE for this file

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
      p.fenceInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends WallBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> wall(RegistryEntry<Block> parent) {
    return (ctx, p) -> {
      p.wallBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.wallInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends FenceGateBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gate(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.fenceGateBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends WidePostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> widePost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalWorld.MODID, "wide_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends NarrowPostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> narrowPost(RegistryEntry<Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.getBuilder(name(ctx.getEntry())).parent(p.getExistingFile(new ResourceLocation(MysticalWorld.MODID, "narrow_post"))).texture("wall", p.blockTexture(parent.get()))));
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
      .loot((p, t) -> p.registerLootTable(ModBlocks.AUBERGINE_CROP.get(), RegistrateBlockLootTables.droppingAndBonusWhen(t, ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), BlockStateProperty.builder(ModBlocks.AUBERGINE_CROP.get()).with(CropsBlock.AGE, 7))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  // MUD BLOCK

  public static RegistryEntry<WetMudBlock> WET_MUD_BLOCK = REGISTRATE.block("wet_mud_block", Material.EARTH, WetMudBlock::new)
      .properties((o) -> o.sound(SoundType.SLIME).hardnessAndResistance(1f))
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

  private static NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.sound(SoundType.STONE).hardnessAndResistance(2f);

  public static RegistryEntry<Block> MUD_BLOCK = REGISTRATE.block("mud_block", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          p.smelting(ModBlocks.WET_MUD_BLOCK, ModBlocks.MUD_BLOCK, 0.15f, p)
      )
      .register();

  public static RegistryEntry<MudBlockStairs> MUD_BLOCK_STAIRS = REGISTRATE.block("mud_block_stairs", Material.ROCK, MudBlockStairs::new)
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BLOCK_SLAB = REGISTRATE.block("mud_block_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BLOCK_WALL = REGISTRATE.block("mud_block_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WALL, p)
      )
      .blockstate(wall(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BLOCK_FENCE = REGISTRATE.block("mud_block_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_FENCE, null, p)
      )
      .blockstate(fence(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = REGISTRATE.block("mud_block_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> MUD_BLOCK_WIDE_POST = REGISTRATE.block("mud_block_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> MUD_BLOCK_SMALL_POST = REGISTRATE.block("mud_block_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BLOCK))
      .register();

  // MUD BRICK

  public static RegistryEntry<WetMudBrick> WET_MUD_BRICK = REGISTRATE.block("wet_mud_brick", Material.EARTH, WetMudBrick::new)
      .properties(o -> o.sound(SoundType.SLIME).hardnessAndResistance(1f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          RECIPES.twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, p)
      )
      .register();

  public static RegistryEntry<Block> MUD_BRICK = REGISTRATE.block("mud_brick", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.smelting(ModBlocks.WET_MUD_BRICK, ModBlocks.MUD_BRICK, 0.15f, p);
        RECIPES.twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BLOCK.get()), ModBlocks.MUD_BRICK.get())
            .addCriterion("has_mud_block", p.hasItem(ModBlocks.MUD_BLOCK.get()))
            .build(p, "mud_bricks_from_mud_blocks_stonecutting");
      })
      .register();

  public static RegistryEntry<MudBrickStairs> MUD_BRICK_STAIRS = REGISTRATE.block("mud_brick_stairs", Material.ROCK, MudBrickStairs::new)
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BRICK_SLAB = REGISTRATE.block("mud_brick_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BRICK_WALL = REGISTRATE.block("mud_brick_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WALL, p)
      )
      .blockstate(wall(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BRICK_FENCE = REGISTRATE.block("mud_brick_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_FENCE, null, p)
      )
      .blockstate(fence(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = REGISTRATE.block("mud_brick_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> MUD_BRICK_WIDE_POST = REGISTRATE.block("mud_brick_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> MUD_BRICK_SMALL_POST = REGISTRATE.block("mud_brick_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BRICK))
      .register();

  // CHARRED STUFF

  private static NonNullUnaryOperator<Block.Properties> WOOD_PROPS = (o) -> o.sound(SoundType.WOOD).hardnessAndResistance(2.0f);

  public static RegistryEntry<LogBlock> CHARRED_LOG = REGISTRATE.log("charred_log", MaterialColor.BROWN_TERRACOTTA)
      .properties(WOOD_PROPS)
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
      .recipe((ctx, p) -> p.planks(ModBlocks.CHARRED_LOG, ctx::getEntry, p))
      .blockstate(ModBlocks::simpleBlockState)
      .register();


  public static RegistryEntry<CharredStairs> CHARRED_STAIRS = REGISTRATE.block("charred_stairs", Material.WOOD, CharredStairs::new)
      .properties(WOOD_PROPS)
      .tag(BlockTags.WOODEN_STAIRS)
      .item()
      .tag(ItemTags.WOODEN_STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<SlabBlock> CHARRED_SLAB = REGISTRATE.block("charred_slab", Material.WOOD, SlabBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.WOODEN_SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceBlock> CHARRED_FENCE = REGISTRATE.block("charred_fence", Material.WOOD, FenceBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_FENCE, null, p)
      )
      .blockstate(fence(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceGateBlock> CHARRED_FENCE_GATE = REGISTRATE.block("charred_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<WallBlock> CHARRED_WALL = REGISTRATE.block("charred_wall", Material.WOOD, WallBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WALL, p)
      )
      .blockstate(wall(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<WidePostBlock> CHARRED_WIDE_POST = REGISTRATE.block("charred_wide_post", Material.WOOD, WidePostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<NarrowPostBlock> CHARRED_SMALL_POST = REGISTRATE.block("charred_small_post", Material.WOOD, NarrowPostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.CHARRED_PLANKS))
      .register();

  // TERRACOTTA BRICK

  public static RegistryEntry<Block> TERRACOTTA_BRICK = REGISTRATE.block("terracotta_brick", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.stonecutting(() -> Items.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, p);
        RECIPES.twoByTwo(() -> Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, null, p);
      })
      .register();

  public static RegistryEntry<TerracottaBrickStairs> TERRACOTTA_BRICK_STAIRS = REGISTRATE.block("terracotta_brick_stairs", Material.ROCK, TerracottaBrickStairs::new)
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = REGISTRATE.block("terracotta_brick_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<WallBlock> TERRACOTTA_BRICK_WALL = REGISTRATE.block("terracotta_brick_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WALL, p)
      )
      .blockstate(wall(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = REGISTRATE.block("terracotta_brick_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_FENCE, null, p)
      )
      .blockstate(fence(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = REGISTRATE.block("terracotta_brick_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, null, p)
      )
      .blockstate(gate(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = REGISTRATE.block("terracotta_brick_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = REGISTRATE.block("terracotta_brick_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  // IRON BRICK

  private static NonNullUnaryOperator<Block.Properties> IRON_PROPS = (o) -> o.sound(SoundType.METAL).hardnessAndResistance(3.2f);

  public static RegistryEntry<Block> IRON_BRICK = REGISTRATE.block("iron_brick", Material.IRON, Block::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p);
      })
      .register();

  public static RegistryEntry<IronBrickStairs> IRON_BRICK_STAIRS = REGISTRATE.block("iron_brick_stairs", Material.IRON, IronBrickStairs::new)
      .properties(IRON_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> IRON_BRICK_SLAB = REGISTRATE.block("iron_brick_slab", Material.IRON, SlabBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<WallBlock> IRON_BRICK_WALL = REGISTRATE.block("iron_brick_wall", Material.IRON, WallBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WALL, p)
      )
      .blockstate(wall(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<WidePostBlock> IRON_BRICK_WIDE_POST = REGISTRATE.block("iron_brick_wide_post", Material.IRON, WidePostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<NarrowPostBlock> IRON_BRICK_SMALL_POST = REGISTRATE.block("iron_brick_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.IRON_BRICK))
      .register();

  // SOFT STONE

  private static NonNullUnaryOperator<Block.Properties> SOFT_STONE_PROPS = o -> o.sound(SoundType.STONE).hardnessAndResistance(1f);
  private static NonNullUnaryOperator<Block.Properties> CRACKED_STONE_PROPS = SOFT_STONE_PROPS;

  public static RegistryEntry<Block> SOFT_STONE = REGISTRATE.block("soft_stone", Block::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        p.stonecutting(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, p);
        RECIPES.twoByTwo(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, null, p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<SoftStoneStairs> SOFT_STONE_STAIRS = REGISTRATE.block("soft_stone_stairs", Material.ROCK, SoftStoneStairs::new)
      .properties(SOFT_STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<SlabBlock> SOFT_STONE_SLAB = REGISTRATE.block("soft_stone_slab", Material.ROCK, SlabBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<WallBlock> SOFT_STONE_WALL = REGISTRATE.block("soft_stone_wall", Material.ROCK, WallBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WALL, p)
      )
      .blockstate(wall(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<WidePostBlock> SOFT_STONE_WIDE_POST = REGISTRATE.block("soft_stone_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<NarrowPostBlock> SOFT_STONE_SMALL_POST = REGISTRATE.block("soft_stone_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_STONE))
      .register();

  // CRACKED STONE
  public static RegistryEntry<Block> CRACKED_STONE = REGISTRATE.block("cracked_stone", Block::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        p.smelting(ModBlocks.SOFT_STONE, ModBlocks.CRACKED_STONE, 0.125f, p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<SoftStoneStairs> CRACKED_STONE_STAIRS = REGISTRATE.block("cracked_stone_stairs", Material.ROCK, SoftStoneStairs::new)
      .properties(CRACKED_STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<SlabBlock> CRACKED_STONE_SLAB = REGISTRATE.block("cracked_stone_slab", Material.ROCK, SlabBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<WallBlock> CRACKED_STONE_WALL = REGISTRATE.block("cracked_stone_wall", Material.ROCK, WallBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_WALL, p)
      )
      .blockstate(wall(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<WidePostBlock> CRACKED_STONE_WIDE_POST = REGISTRATE.block("cracked_stone_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<NarrowPostBlock> CRACKED_STONE_SMALL_POST = REGISTRATE.block("cracked_stone_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.CRACKED_STONE))
      .register();

  // SMOOTH OBSIDIAN

  private static NonNullUnaryOperator<Block.Properties> SOFT_OBSIDIAN_PROPS = o -> o.sound(SoundType.STONE).hardnessAndResistance(20f);

  public static RegistryEntry<Block> SOFT_OBSIDIAN = REGISTRATE.block("soft_obsidian", Block::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ModBlocks::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.stonecutting(() -> Items.OBSIDIAN, ModBlocks.SOFT_OBSIDIAN, p);
        RECIPES.twoByTwo(() -> Items.OBSIDIAN, ModBlocks.SOFT_OBSIDIAN, null, p);
      })
      .register();

  public static RegistryEntry<SoftObsidianStairs> SOFT_OBSIDIAN_STAIRS = REGISTRATE.block("soft_obsidian_stairs", Material.ROCK, SoftObsidianStairs::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_STAIRS, null, true, p)
      )
      .blockstate(stairs(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<SlabBlock> SOFT_OBSIDIAN_SLAB = REGISTRATE.block("soft_obsidian_slab", Material.ROCK, SlabBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_SLAB, null, true, p)
      )
      .blockstate(slab(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<WallBlock> SOFT_OBSIDIAN_WALL = REGISTRATE.block("soft_obsidian_wall", Material.ROCK, WallBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_WALL, p)
      )
      .blockstate(wall(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<WidePostBlock> SOFT_OBSIDIAN_WIDE_POST = REGISTRATE.block("soft_obsidian_wide_post", Material.ROCK, WidePostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<NarrowPostBlock> SOFT_OBSIDIAN_SMALL_POST = REGISTRATE.block("soft_obsidian_small_post", Material.ROCK, NarrowPostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_OBSIDIAN))
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
      .loot((p, t) ->
          p.registerLootTable(ModBlocks.AMETHYST_ORE.get(), RegistrateBlockLootTables.droppingItemWithFortune(t, ModItems.AMETHYST_GEM.get()))
      )
      .register();

  public static RegistryEntry<Block> AMETHYST_BLOCK = REGISTRATE.block(ModMaterials.AMETHYST.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.AMETHYST_BLOCK)
      .build()
      .tag(MWTags.Blocks.AMETHYST_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<AmethystStairs> AMETHYST_STAIRS = REGISTRATE.block("amethyst_stairs", Material.IRON, AmethystStairs::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> AMETHYST_SLAB = REGISTRATE.block("amethyst_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> AMETHYST_WALL = REGISTRATE.block("amethyst_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_WALL, p)
      )
      .blockstate(wall(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> AMETHYST_WIDE_POST = REGISTRATE.block("amethyst_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> AMETHYST_SMALL_POST = REGISTRATE.block("amethyst_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.AMETHYST.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SMALL_POST, null, false, p)
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

  public static RegistryEntry<Block> COPPER_BLOCK = REGISTRATE.block(ModMaterials.COPPER.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.COPPER_BLOCK)
      .build()
      .tag(MWTags.Blocks.COPPER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<CopperStairs> COPPER_STAIRS = REGISTRATE.block("copper_stairs", Material.IRON, CopperStairs::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> COPPER_SLAB = REGISTRATE.block("copper_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> COPPER_WALL = REGISTRATE.block("copper_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_WALL, p)
      )
      .blockstate(wall(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> COPPER_WIDE_POST = REGISTRATE.block("copper_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> COPPER_SMALL_POST = REGISTRATE.block("copper_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.COPPER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SMALL_POST, null, false, p)
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

  public static ResourceLocation RL = new ResourceLocation("mysticalworld:item/copper");

  public static RegistryEntry<Block> LEAD_BLOCK = REGISTRATE.block(ModMaterials.LEAD.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.LEAD_BLOCK)
      .build()
      .tag(MWTags.Blocks.LEAD_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<LeadStairs> LEAD_STAIRS = REGISTRATE.block("lead_stairs", Material.IRON, LeadStairs::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> LEAD_SLAB = REGISTRATE.block("lead_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> LEAD_WALL = REGISTRATE.block("lead_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_WALL, p)
      )
      .blockstate(wall(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> LEAD_WIDE_POST = REGISTRATE.block("lead_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> LEAD_SMALL_POST = REGISTRATE.block("lead_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.LEAD.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SMALL_POST, null, false, p)
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

  public static RegistryEntry<Block> QUICKSILVER_BLOCK = REGISTRATE.block(ModMaterials.QUICKSILVER.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUICKSILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.QUICKSILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<QuicksilverStairs> QUICKSILVER_STAIRS = REGISTRATE.block("quicksilver_stairs", Material.IRON, QuicksilverStairs::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> QUICKSILVER_SLAB = REGISTRATE.block("quicksilver_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> QUICKSILVER_WALL = REGISTRATE.block("quicksilver_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_WALL, p)
      )
      .blockstate(wall(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> QUICKSILVER_WIDE_POST = REGISTRATE.block("quicksilver_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> QUICKSILVER_SMALL_POST = REGISTRATE.block("quicksilver_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.QUICKSILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SMALL_POST, null, false, p)
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

  public static RegistryEntry<Block> SILVER_BLOCK = REGISTRATE.block(ModMaterials.SILVER.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.SILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.SILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<SilverStairs> SILVER_STAIRS = REGISTRATE.block("silver_stairs", Material.IRON, SilverStairs::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> SILVER_SLAB = REGISTRATE.block("silver_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> SILVER_WALL = REGISTRATE.block("silver_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_WALL, p)
      )
      .blockstate(wall(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> SILVER_WIDE_POST = REGISTRATE.block("silver_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> SILVER_SMALL_POST = REGISTRATE.block("silver_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.SILVER.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SMALL_POST, null, false, p)
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

  public static RegistryEntry<Block> TIN_BLOCK = REGISTRATE.block(ModMaterials.TIN.blockName(), Material.IRON, Block::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.TIN_BLOCK)
      .build()
      .tag(MWTags.Blocks.TIN_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<TinStairs> TIN_STAIRS = REGISTRATE.block("tin_stairs", Material.IRON, TinStairs::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(ModBlocks.TIN_BLOCK, ModBlocks.TIN_STAIRS, null, false, p)
      )
      .blockstate(stairs(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> TIN_SLAB = REGISTRATE.block("tin_slab", Material.IRON, SlabBlock::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SLAB, null, false, p)
      )
      .blockstate(slab(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> TIN_WALL = REGISTRATE.block("tin_wall", Material.IRON, WallBlock::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(ModBlocks.TIN_BLOCK, ModBlocks.TIN_WALL, p)
      )
      .blockstate(wall(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<WidePostBlock> TIN_WIDE_POST = REGISTRATE.block("tin_wide_post", Material.IRON, WidePostBlock::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.widePost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<NarrowPostBlock> TIN_SMALL_POST = REGISTRATE.block("tin_small_post", Material.IRON, NarrowPostBlock::new)
      .properties(ModMaterials.TIN.getBlockProps())
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          RECIPES.narrowPost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.TIN_BLOCK))
      .register();


  public static void load() {
  }
}
