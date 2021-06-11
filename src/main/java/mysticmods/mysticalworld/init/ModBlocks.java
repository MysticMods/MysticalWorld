package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.blocks.*;
import mysticmods.mysticalworld.blocks.*;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistryEntry;
import noobanidus.libs.noobutil.block.BaseBlocks;
import noobanidus.libs.noobutil.material.MaterialType;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "WeakerAccess", "unchecked"})
public class ModBlocks {

  @SuppressWarnings("unchecked")
  public static NonNullFunction<Block.Properties, BaseBlocks.OreBlock> oreBlock(MaterialType material) {
    return (o) -> new BaseBlocks.OreBlock(o, material.getMinXP(), material.getMaxXP());
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

  public static <T extends Item> ItemModelBuilder generated(DataGenContext<Item, T> ctx, RegistrateItemModelProvider p) {
    return p.generated(ctx::getEntry, p.modLoc("block/" + p.name(ctx::getEntry)));
  }

  public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> simpleBlockState(ResourceLocation parent) {
    return (ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().getExistingFile(parent));
  }

  public static <T extends Block> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> simpleBlockState(String parent) {
    return simpleBlockState(new ResourceLocation(MysticalWorld.MODID, parent));
  }

  public static <T extends StairsBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> stairs(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> p.stairsBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends StairsBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> stairs(Supplier<? extends Block> parent) {
    return (ctx, p) -> p.stairsBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends SlabBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> slab(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> p.slabBlock(ctx.getEntry(), p.blockTexture(parent.get()), p.blockTexture(parent.get()));
  }

  public static <T extends SlabBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> slab(RegistryEntry<? extends Block> parent, Supplier<Block> visual) {
    return (ctx, p) -> p.slabBlock(ctx.getEntry(), p.blockTexture(parent.get()), p.blockTexture(visual.get()));
  }

  public static <T extends SlabBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> slab(Supplier<? extends Block> parent) {
    return (ctx, p) -> p.slabBlock(ctx.getEntry(), p.blockTexture(parent.get()), p.blockTexture(parent.get()));
  }

  public static <T extends FenceBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> fence(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> {
      p.fenceBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.models().fenceInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends FenceBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> fence(Supplier<? extends Block> parent) {
    return (ctx, p) -> {
      p.fenceBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.models().fenceInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends WallBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> wall(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> {
      p.wallBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.models().wallInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends WallBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> wall(Supplier<? extends Block> parent) {
    return (ctx, p) -> {
      p.wallBlock(ctx.getEntry(), p.blockTexture(parent.get()));
      p.models().wallInventory(name(ctx.getEntry()) + "_inventory", p.blockTexture(parent.get()));
    };
  }

  public static <T extends FenceGateBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gate(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> p.fenceGateBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends FenceGateBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> gate(Supplier<? extends Block> parent) {
    return (ctx, p) -> p.fenceGateBlock(ctx.getEntry(), p.blockTexture(parent.get()));
  }

  public static <T extends BaseBlocks.WidePostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> widePost(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().getBuilder(name(ctx.getEntry())).parent(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "wide_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends BaseBlocks.WidePostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> widePost(Supplier<? extends Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().getBuilder(name(ctx.getEntry())).parent(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "wide_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends BaseBlocks.NarrowPostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> narrowPost(RegistryEntry<? extends Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().getBuilder(name(ctx.getEntry())).parent(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "narrow_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  public static <T extends BaseBlocks.NarrowPostBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> narrowPost(Supplier<? extends Block> parent) {
    return (ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().getBuilder(name(ctx.getEntry())).parent(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "narrow_post"))).texture("wall", p.blockTexture(parent.get()))));
  }

  private static <T extends IForgeRegistryEntry<?>> String name(T block) {
    return Objects.requireNonNull(block.getRegistryName()).getPath();
  }

  public static NonNullFunction<Block.Properties, StairsBlock> stairsBlock(RegistryEntry<? extends Block> block) {
    return (b) -> new StairsBlock(() -> block.get().getDefaultState(), b);
  }

  public static NonNullFunction<Block.Properties, StairsBlock> stairsBlock(Supplier<? extends Block> block) {
    return (b) -> new StairsBlock(() -> block.get().getDefaultState(), b);
  }

  public static RegistryEntry<PetrifiedFlowerBlock> STONEPETAL = MysticalWorld.REGISTRATE.block("stonepetal", Material.PLANTS, PetrifiedFlowerBlock::new)
      .properties(o -> o.doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .item()
      .model(ModBlocks::generated)
      .build()
      .recipe((ctx, p) -> {
        DataIngredient a = DataIngredient.items(ModBlocks.STONEPETAL.get());
        ShapelessRecipeBuilder.shapelessRecipe(Items.GRAY_DYE, 4).addIngredient(ctx.getEntry()).addCriterion("has_stonepetal", a.getCritereon(p)).build(p, new ResourceLocation(MysticalWorld.MODID, "gray_dye_from_stonepetal"));
      })
      .register();

  public static RegistryEntry<FlowerPotBlock> POTTED_STONEPETAL = MysticalWorld.REGISTRATE.block("potted_stonepetal", Material.MISCELLANEOUS, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.STONEPETAL, AbstractBlock.Properties.from(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/stonepetal")))
      .loot((ctx, p) -> ctx.registerLootTable(p, RegistrateBlockLootTables.droppingAndFlowerPot(ModBlocks.STONEPETAL.get())))
      .register();

  private static NonNullUnaryOperator<Block.Properties> THATCH_PROPS = (o) -> o.hardnessAndResistance(1f).sound(SoundType.PLANT);
  private static NonNullUnaryOperator<Block.Properties> MUSHROOM_PROPS = (o) -> o.hardnessAndResistance(0.2F).sound(SoundType.WOOD);

  public static RegistryEntry<ThatchBlock> THATCH = MysticalWorld.REGISTRATE.block("thatch", Material.WOOD, ThatchBlock::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .item()
      .model(NonNullBiConsumer.noop())
      .build()
      .blockstate(NonNullBiConsumer.noop())
      .recipe((ctx, p) -> {
            ShapedRecipeBuilder.shapedRecipe(ModBlocks.THATCH.get(), 32)
                .patternLine("XY")
                .patternLine("YX")
                .key('X', Blocks.HAY_BLOCK)
                .key('Y', Tags.Items.CROPS_WHEAT)
                .addCriterion("has_hay", RegistrateRecipeProvider.hasItem(Blocks.HAY_BLOCK))
                .addCriterion("has_wheat", RegistrateRecipeProvider.hasItem(Items.WHEAT))
                .build(p);
            ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.SIMPLE_THATCH.get(), 1)
                .addIngredient(ModBlocks.THATCH.get())
                .addCriterion("has_thatch", RegistrateRecipeProvider.hasItem(ModBlocks.THATCH.get()))
                .build(p, MysticalWorld.RECIPES.rl("simple_thatch_from_thatch"));
            ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.THATCH.get(), 1)
                .addIngredient(ModBlocks.SIMPLE_THATCH.get())
                .addCriterion("has_thatch", RegistrateRecipeProvider.hasItem(ModBlocks.SIMPLE_THATCH.get()))
                .build(p, MysticalWorld.RECIPES.rl("thatch_from_simple_thatch"));
          }
      )
      .register();

  public static RegistryEntry<Block> SIMPLE_THATCH = MysticalWorld.REGISTRATE.block("simple_thatch", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.SIMPLE_THATCH))
      .build()
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.SIMPLE_THATCH.get(), 1)
            .addIngredient(ModBlocks.THATCH.get())
            .addCriterion("has_thatch", RegistrateRecipeProvider.hasItem(ModBlocks.THATCH.get()))
            .build(p, "simple_thatch_from_thatch");
        ShapelessRecipeBuilder.shapelessRecipe(ModBlocks.THATCH.get(), 1)
            .addIngredient(ModBlocks.SIMPLE_THATCH.get())
            .addCriterion("has_thatch", RegistrateRecipeProvider.hasItem(ModBlocks.SIMPLE_THATCH.get()))
            .build(p, "thatch_from_simple_thatch");
      })
      .register();

  public static RegistryEntry<StairsBlock> THATCH_STAIRS = MysticalWorld.REGISTRATE.block("thatch_stairs", Material.WOOD, stairsBlock(ModBlocks.THATCH))
      .properties(THATCH_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<SlabBlock> THATCH_SLAB = MysticalWorld.REGISTRATE.block("thatch_slab", Material.WOOD, SlabBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SIMPLE_THATCH), ModBlocks.THATCH_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SIMPLE_THATCH))
      .register();

  public static RegistryEntry<WallBlock> THATCH_WALL = MysticalWorld.REGISTRATE.block("thatch_wall", Material.WOOD, WallBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_WALL)
      )
      .blockstate(wall(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<FenceBlock> THATCH_FENCE = MysticalWorld.REGISTRATE.block("thatch_fence", Material.WOOD, FenceBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_FENCE, null)
      )
      .blockstate(fence(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<FenceGateBlock> THATCH_FENCE_GATE = MysticalWorld.REGISTRATE.block("thatch_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> THATCH_WIDE_POST = MysticalWorld.REGISTRATE.block("thatch_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.THATCH, ModBlocks.THATCH_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> THATCH_SMALL_POST = MysticalWorld.REGISTRATE.block("thatch_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.THATCH, ModBlocks.THATCH_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.THATCH))
      .register();

  public static RegistryEntry<OakAppleBlock> GALL_APPLE = MysticalWorld.REGISTRATE.block("gall_apple_crop", OakAppleBlock::new)
      .properties(o -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly())
      .loot((p, t) -> p.
          registerLootTable(ModBlocks.GALL_APPLE.get(), RegistrateBlockLootTables.
              droppingAndBonusWhen(ModBlocks.GALL_APPLE.get(), Items.AIR, ModItems.GALL_APPLE.get(), new BlockStateProperty.Builder(ModBlocks.GALL_APPLE.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 3)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static RegistryEntry<AubergineCropBlock> AUBERGINE_CROP = MysticalWorld.REGISTRATE.block("aubergine_crop", AubergineCropBlock::new)
      .properties(o -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly())
      .loot((p, t) -> p.
          registerLootTable(ModBlocks.AUBERGINE_CROP.get(), RegistrateBlockLootTables.
              droppingAndBonusWhen(ModBlocks.AUBERGINE_CROP.get(), ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), new BlockStateProperty.Builder(ModBlocks.AUBERGINE_CROP.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 7)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static RegistryEntry<WildAubergineBlock> WILD_AUBERGINE = MysticalWorld.REGISTRATE.block("wild_aubergine", WildAubergineBlock::new)
      .properties(o -> Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP).tickRandomly())
      .loot((p, t) -> p.registerLootTable(t, LootTable.builder().addLootPool(RegistrateBlockLootTables.withSurvivesExplosion(ModItems.AUBERGINE.get(), LootPool.builder().rolls(RandomValueRange.of(1, 3)).addEntry(ItemLootEntry.builder(ModItems.AUBERGINE.get())))).addLootPool(RegistrateBlockLootTables.withSurvivesExplosion(ModItems.AUBERGINE_SEEDS.get(), LootPool.builder().rolls(RandomValueRange.of(1, 2)).addEntry(ItemLootEntry.builder(ModItems.AUBERGINE_SEEDS.get()))))))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .partialState()
              .addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry()))))
      )
      .register();


  // MUSHROOM
  public static RegistryEntry<Block> RED_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("red_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block")));
      })
      .register();

  public static RegistryEntry<Block> BROWN_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("brown_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block")));
      })
      .register();

  public static RegistryEntry<Block> STEM_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("stem_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem")));
      })
      .register();

  public static RegistryEntry<StairsBlock> RED_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("red_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.RED_MUSHROOM_BLOCK))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_STAIRS, null, true)
      )
      .blockstate(stairs(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> RED_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("red_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.RED_MUSHROOM_FULL, () -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> RED_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("red_mushroom_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_WALL)
      )
      .blockstate(wall(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> RED_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("red_mushroom_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_FENCE, null)
      )
      .blockstate(fence(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<FenceGateBlock> RED_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("red_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> RED_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("red_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.RED_MUSHROOM_BLOCK, ModBlocks.RED_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> RED_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("red_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.RED_MUSHROOM_BLOCK, ModBlocks.RED_MUSHROOM_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  // BROWN

  public static RegistryEntry<StairsBlock> BROWN_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("brown_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_STAIRS, null, true)
      )
      .blockstate(stairs(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> BROWN_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("brown_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.BROWN_MUSHROOM_FULL, () -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> BROWN_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("brown_mushroom_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_WALL)
      )
      .blockstate(wall(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> BROWN_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_FENCE, null)
      )
      .blockstate(fence(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<FenceGateBlock> BROWN_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> BROWN_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK, ModBlocks.BROWN_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> BROWN_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.BROWN_MUSHROOM_BLOCK, ModBlocks.BROWN_MUSHROOM_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  // STEM

  public static RegistryEntry<StairsBlock> MUSHROOM_STEM_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_stem_stairs", Material.WOOD, stairsBlock(() -> Blocks.MUSHROOM_STEM))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_STAIRS, null, true)
      )
      .blockstate(stairs(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<SlabBlock> MUSHROOM_STEM_SLAB = MysticalWorld.REGISTRATE.block("mushroom_stem_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.STEM_MUSHROOM_FULL, () -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<WallBlock> MUSHROOM_STEM_WALL = MysticalWorld.REGISTRATE.block("mushroom_stem_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_WALL)
      )
      .blockstate(wall(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<FenceBlock> MUSHROOM_STEM_FENCE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_FENCE, null)
      )
      .blockstate(fence(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<FenceGateBlock> MUSHROOM_STEM_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> MUSHROOM_STEM_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.MUSHROOM_STEM, ModBlocks.MUSHROOM_STEM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_STEM_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.MUSHROOM_STEM, ModBlocks.MUSHROOM_STEM_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(() -> Blocks.MUSHROOM_STEM))
      .register();

  // INSIDE

  public static RegistryEntry<Block> MUSHROOM_INSIDE = MysticalWorld.REGISTRATE.block("mushroom_inside_block", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.create(Material.WOOD).sound(SoundType.PLANT))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.MUSHROOM_INSIDE))
      .build()
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_block_inside")));
      })
      .recipe((ctx, p) -> {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(MWTags.Items.MUSHROOM_BLOCKS), ctx.getEntry(), 0.125f, 200).addCriterion("has_mushroom", RegistrateRecipeProvider.hasItem(MWTags.Items.MUSHROOM_BLOCKS)).build(p, "mushroom_inside_from_smelting");
      })
      .register();

  public static RegistryEntry<StairsBlock> MUSHROOM_INSIDE_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_inside_stairs", Material.WOOD, stairsBlock(ModBlocks.MUSHROOM_INSIDE))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<SlabBlock> MUSHROOM_INSIDE_SLAB = MysticalWorld.REGISTRATE.block("mushroom_inside_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<WallBlock> MUSHROOM_INSIDE_WALL = MysticalWorld.REGISTRATE.block("mushroom_inside_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_WALL)
      )
      .blockstate(wall(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<FenceBlock> MUSHROOM_INSIDE_FENCE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_FENCE, null)
      )
      .blockstate(fence(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<FenceGateBlock> MUSHROOM_INSIDE_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> MUSHROOM_INSIDE_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUSHROOM_INSIDE, ModBlocks.MUSHROOM_INSIDE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_INSIDE_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUSHROOM_INSIDE, ModBlocks.MUSHROOM_INSIDE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUSHROOM_INSIDE))
      .register();

  // MUD BLOCK

  public static RegistryEntry<WetMudBlock> WET_MUD_BLOCK = MysticalWorld.REGISTRATE.block("wet_mud_block", Material.EARTH, WetMudBlock::new)
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
              .addCriterion("has_dirt", RegistrateRecipeProvider.hasItem(Blocks.DIRT))
              .build(p)
      )
      .register();

  private static NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.sound(SoundType.STONE).setRequiresTool().hardnessAndResistance(2f);

  public static RegistryEntry<Block> MUD_BLOCK = MysticalWorld.REGISTRATE.block("mud_block", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BLOCK), ModBlocks.MUD_BLOCK, 0.15f)
      )
      .register();

  public static RegistryEntry<StairsBlock> MUD_BLOCK_STAIRS = MysticalWorld.REGISTRATE.block("mud_block_stairs", Material.ROCK, stairsBlock(ModBlocks.MUD_BLOCK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BLOCK_SLAB = MysticalWorld.REGISTRATE.block("mud_block_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BLOCK_WALL = MysticalWorld.REGISTRATE.block("mud_block_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_WALL)
      )
      .blockstate(wall(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BLOCK_FENCE = MysticalWorld.REGISTRATE.block("mud_block_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_FENCE, null)
      )
      .blockstate(fence(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_block_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> MUD_BLOCK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_block_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> MUD_BLOCK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_block_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BLOCK))
      .register();

  // MUD BRICK

  public static RegistryEntry<WetMudBrick> WET_MUD_BRICK = MysticalWorld.REGISTRATE.block("wet_mud_brick", Material.EARTH, WetMudBrick::new)
      .properties(o -> o.sound(SoundType.SLIME).hardnessAndResistance(1f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, p)
      )
      .register();

  public static RegistryEntry<Block> MUD_BRICK = MysticalWorld.REGISTRATE.block("mud_brick", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BRICK), ModBlocks.MUD_BRICK, 0.15f);
        MysticalWorld.RECIPES.twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ModBlocks.MUD_BLOCK.get()), ModBlocks.MUD_BRICK.get())
            .addCriterion("has_mud_block", RegistrateRecipeProvider.hasItem(ModBlocks.MUD_BLOCK.get()))
            .build(p, "mud_bricks_from_mud_blocks_stonecutting");
      })
      .register();

  public static RegistryEntry<StairsBlock> MUD_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("mud_brick_stairs", Material.ROCK, stairsBlock(ModBlocks.MUD_BRICK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> MUD_BRICK_SLAB = MysticalWorld.REGISTRATE.block("mud_brick_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<WallBlock> MUD_BRICK_WALL = MysticalWorld.REGISTRATE.block("mud_brick_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_WALL)
      )
      .blockstate(wall(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> MUD_BRICK_FENCE = MysticalWorld.REGISTRATE.block("mud_brick_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_FENCE, null)
      )
      .blockstate(fence(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_brick_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> MUD_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_brick_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> MUD_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_brick_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.MUD_BRICK))
      .register();

  // CHARRED STUFF

  private static NonNullUnaryOperator<Block.Properties> WOOD_PROPS = (o) -> o.sound(SoundType.WOOD).hardnessAndResistance(2.0f).harvestTool(ToolType.AXE);

  public static RegistryEntry<CharredLogBlock> CHARRED_WOOD = MysticalWorld.REGISTRATE.block("charred_wood", (o) -> new CharredLogBlock(o, true))
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getEntry().getRegistryName().getPath(), p.blockTexture(ModBlocks.CHARRED_LOG.get())));
      })
      .item()
      .tag(ItemTags.LOGS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) -> {
        DataIngredient log = DataIngredient.items(ModBlocks.CHARRED_LOG.get());
        ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 3).addIngredient(log).addIngredient(log).addIngredient(log).addIngredient(log).addCriterion("has_charred_log", RegistrateRecipeProvider.hasItem(ModBlocks.CHARRED_LOG.get())).build(p, new ResourceLocation("mysticalworld", "charred_wood_from_logs"));
      })
      .register();

  public static RegistryEntry<CharredLogBlock> CHARRED_LOG = MysticalWorld.REGISTRATE.block("charred_log", (o) -> new CharredLogBlock(o, false))
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> {
        p.logBlock(ctx.getEntry());
      })
      .item()
      .tag(ItemTags.LOGS)
      .model(ModBlocks::itemModel)
      .build()
      .register();

  public static RegistryEntry<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = MysticalWorld.REGISTRATE.log("stripped_charred_wood")
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getEntry().getRegistryName().getPath(), p.blockTexture(ModBlocks.STRIPPED_CHARRED_LOG.get())));
      })
      .item()
      .tag(ItemTags.LOGS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) -> {
        DataIngredient log = DataIngredient.items(ModBlocks.STRIPPED_CHARRED_LOG.get());
        ShapelessRecipeBuilder.shapelessRecipe(ctx.getEntry(), 3).addIngredient(log).addIngredient(log).addIngredient(log).addIngredient(log).addCriterion("has_stripped_charred_log", RegistrateRecipeProvider.hasItem(ModBlocks.STRIPPED_CHARRED_LOG.get())).build(p, new ResourceLocation("mysticalworld", "stripped_charred_wood_from_logs"));
      })
      .register();

  public static RegistryEntry<RotatedPillarBlock> STRIPPED_CHARRED_LOG = MysticalWorld.REGISTRATE.log("stripped_charred_log")
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> {
        p.logBlock(ctx.getEntry());
      })
      .item()
      .tag(ItemTags.LOGS)
      .model(ModBlocks::itemModel)
      .build()
      .register();

  public static RegistryEntry<Block> CHARRED_PLANKS = MysticalWorld.REGISTRATE.block("charred_planks", Material.WOOD, Block::new)
      .properties(o -> o.sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f))
      .tag(BlockTags.PLANKS)
      .item()
      .tag(ItemTags.PLANKS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) -> p.planks(DataIngredient.items(ModBlocks.CHARRED_LOG), ctx::getEntry))
      .blockstate(ModBlocks::simpleBlockState)
      .register();


  public static RegistryEntry<StairsBlock> CHARRED_STAIRS = MysticalWorld.REGISTRATE.block("charred_stairs", Material.WOOD, stairsBlock(ModBlocks.CHARRED_PLANKS))
      .properties(WOOD_PROPS)
      .tag(BlockTags.WOODEN_STAIRS)
      .item()
      .tag(ItemTags.WOODEN_STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<SlabBlock> CHARRED_SLAB = MysticalWorld.REGISTRATE.block("charred_slab", Material.WOOD, SlabBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.WOODEN_SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceBlock> CHARRED_FENCE = MysticalWorld.REGISTRATE.block("charred_fence", Material.WOOD, FenceBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_FENCE, null)
      )
      .blockstate(fence(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<FenceGateBlock> CHARRED_FENCE_GATE = MysticalWorld.REGISTRATE.block("charred_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<WallBlock> CHARRED_WALL = MysticalWorld.REGISTRATE.block("charred_wall", Material.WOOD, WallBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_WALL)
      )
      .blockstate(wall(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> CHARRED_WIDE_POST = MysticalWorld.REGISTRATE.block("charred_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.CHARRED_PLANKS))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> CHARRED_SMALL_POST = MysticalWorld.REGISTRATE.block("charred_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.CHARRED_PLANKS))
      .register();

  // TERRACOTTA BRICK

  public static RegistryEntry<Block> TERRACOTTA_BRICK = MysticalWorld.REGISTRATE.block("terracotta_brick", Material.ROCK, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.stonecutting(DataIngredient.items(Items.TERRACOTTA), ModBlocks.TERRACOTTA_BRICK);
        MysticalWorld.RECIPES.twoByTwo(() -> Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, null, p);
      })
      .register();

  public static RegistryEntry<StairsBlock> TERRACOTTA_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("terracotta_brick_stairs", Material.ROCK, stairsBlock(ModBlocks.TERRACOTTA_BRICK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = MysticalWorld.REGISTRATE.block("terracotta_brick_slab", Material.ROCK, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<WallBlock> TERRACOTTA_BRICK_WALL = MysticalWorld.REGISTRATE.block("terracotta_brick_wall", Material.ROCK, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_WALL)
      )
      .blockstate(wall(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence", Material.ROCK, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_FENCE, null)
      )
      .blockstate(fence(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence_gate", Material.ROCK, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  // IRON BRICK

  private static NonNullUnaryOperator<Block.Properties> IRON_PROPS = (o) -> o.sound(SoundType.METAL).setRequiresTool().hardnessAndResistance(3.2f);

  public static RegistryEntry<Block> IRON_BRICK = MysticalWorld.REGISTRATE.block("iron_brick", Material.IRON, Block::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p);
      })
      .register();

  public static RegistryEntry<StairsBlock> IRON_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("iron_brick_stairs", Material.IRON, stairsBlock(ModBlocks.IRON_BRICK))
      .properties(IRON_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<SlabBlock> IRON_BRICK_SLAB = MysticalWorld.REGISTRATE.block("iron_brick_slab", Material.IRON, SlabBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<WallBlock> IRON_BRICK_WALL = MysticalWorld.REGISTRATE.block("iron_brick_wall", Material.IRON, WallBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_WALL)
      )
      .blockstate(wall(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> IRON_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("iron_brick_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.IRON_BRICK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> IRON_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("iron_brick_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.IRON_BRICK))
      .register();

  // SOFT STONE

  private static NonNullUnaryOperator<Block.Properties> SOFT_STONE_PROPS = o -> o.sound(SoundType.STONE).setRequiresTool().hardnessAndResistance(1f);
  private static NonNullUnaryOperator<Block.Properties> BLACKENED_STONE_PROPS = SOFT_STONE_PROPS;
  private static NonNullUnaryOperator<Block.Properties> CRACKED_STONE_PROPS = SOFT_STONE_PROPS;

  public static RegistryEntry<Block> SOFT_STONE = MysticalWorld.REGISTRATE.block("soft_stone", Block::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        p.stonecutting(DataIngredient.items(Items.SMOOTH_STONE), ModBlocks.SOFT_STONE);
        MysticalWorld.RECIPES.twoByTwo(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, null, p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> SOFT_STONE_STAIRS = MysticalWorld.REGISTRATE.block("soft_stone_stairs", Material.ROCK, stairsBlock(ModBlocks.SOFT_STONE))
      .properties(SOFT_STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<SlabBlock> SOFT_STONE_SLAB = MysticalWorld.REGISTRATE.block("soft_stone_slab", Material.ROCK, SlabBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<WallBlock> SOFT_STONE_WALL = MysticalWorld.REGISTRATE.block("soft_stone_wall", Material.ROCK, WallBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_WALL)
      )
      .blockstate(wall(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> SOFT_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_stone_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> SOFT_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_stone_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_STONE))
      .register();

  // CRACKED STONE
  public static RegistryEntry<Block> CRACKED_STONE = MysticalWorld.REGISTRATE.block("cracked_stone", Block::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        p.smelting(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.CRACKED_STONE, 0.125f);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> CRACKED_STONE_STAIRS = MysticalWorld.REGISTRATE.block("cracked_stone_stairs", Material.ROCK, stairsBlock(ModBlocks.CRACKED_STONE))
      .properties(CRACKED_STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.CRACKED_STONE), ModBlocks.CRACKED_STONE_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<SlabBlock> CRACKED_STONE_SLAB = MysticalWorld.REGISTRATE.block("cracked_stone_slab", Material.ROCK, SlabBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.CRACKED_STONE), ModBlocks.CRACKED_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<WallBlock> CRACKED_STONE_WALL = MysticalWorld.REGISTRATE.block("cracked_stone_wall", Material.ROCK, WallBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.CRACKED_STONE), ModBlocks.CRACKED_STONE_WALL)
      )
      .blockstate(wall(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> CRACKED_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("cracked_stone_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.CRACKED_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> CRACKED_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("cracked_stone_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(CRACKED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.CRACKED_STONE, ModBlocks.CRACKED_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.CRACKED_STONE))
      .register();

  // BLACKENED STONE
  public static RegistryEntry<Block> BLACKENED_STONE = MysticalWorld.REGISTRATE.block("blackened_stone", Block::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 4)
            .patternLine("AB")
            .patternLine("BA")
            .key('A', Tags.Items.STONE)
            .key('B', Ingredient.fromItems(Items.COAL, Items.CHARCOAL))
            .addCriterion("has_stone", RegistrateRecipeProvider.hasItem(Tags.Items.STONE))
            .build(p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> BLACKENED_STONE_STAIRS = MysticalWorld.REGISTRATE.block("blackened_stone_stairs", Material.ROCK, stairsBlock(ModBlocks.BLACKENED_STONE))
      .properties(BLACKENED_STONE_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.BLACKENED_STONE))
      .register();

  public static RegistryEntry<SlabBlock> BLACKENED_STONE_SLAB = MysticalWorld.REGISTRATE.block("blackened_stone_slab", Material.ROCK, SlabBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.BLACKENED_STONE))
      .register();

  public static RegistryEntry<WallBlock> BLACKENED_STONE_WALL = MysticalWorld.REGISTRATE.block("blackened_stone_wall", Material.ROCK, WallBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_WALL)
      )
      .blockstate(wall(ModBlocks.BLACKENED_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> BLACKENED_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("blackened_stone_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.BLACKENED_STONE, ModBlocks.BLACKENED_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.BLACKENED_STONE))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> BLACKENED_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("blackened_stone_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.BLACKENED_STONE, ModBlocks.BLACKENED_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.BLACKENED_STONE))
      .register();

  // SMOOTH OBSIDIAN

  private static NonNullUnaryOperator<Block.Properties> SOFT_OBSIDIAN_PROPS = o -> o.sound(SoundType.STONE).hardnessAndResistance(20f);

  public static RegistryEntry<Block> SOFT_OBSIDIAN = MysticalWorld.REGISTRATE.block("soft_obsidian", Block::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ModBlocks::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shapedRecipe(ctx.getEntry(), 4)
            .patternLine("AB")
            .patternLine("BA")
            .key('A', Tags.Items.STONE)
            .key('B', Tags.Items.OBSIDIAN)
            .addCriterion("has_stone", RegistrateRecipeProvider.hasItem(Tags.Items.STONE))
            .addCriterion("has_obsidian", RegistrateRecipeProvider.hasItem(Tags.Items.OBSIDIAN))
            .build(p);
      })
      .register();

  public static RegistryEntry<StairsBlock> SOFT_OBSIDIAN_STAIRS = MysticalWorld.REGISTRATE.block("soft_obsidian_stairs", Material.ROCK, stairsBlock(ModBlocks.SOFT_OBSIDIAN))
      .properties(SOFT_OBSIDIAN_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<SlabBlock> SOFT_OBSIDIAN_SLAB = MysticalWorld.REGISTRATE.block("soft_obsidian_slab", Material.ROCK, SlabBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_SLAB, null, true)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<WallBlock> SOFT_OBSIDIAN_WALL = MysticalWorld.REGISTRATE.block("soft_obsidian_wall", Material.ROCK, WallBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_WALL)
      )
      .blockstate(wall(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> SOFT_OBSIDIAN_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> SOFT_OBSIDIAN_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_OBSIDIAN))
      .register();

  // GRANITE QUARTZ
  public static RegistryEntry<BaseBlocks.OreBlock> GRANITE_QUARTZ_ORE = MysticalWorld.REGISTRATE.block("granite_quartz_ore", oreBlock(ModMaterials.QUARTZ))
      .properties(o -> {
        ModMaterials.QUARTZ.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUARTZ_ORE)
      .build()
      .tag(MWTags.Blocks.QUARTZ_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .loot((p, t) ->
          p.registerLootTable(ModBlocks.GRANITE_QUARTZ_ORE.get(), RegistrateBlockLootTables.droppingItemWithFortune(t, Items.QUARTZ))
      )
      .register();

  // AMETHYST
  public static RegistryEntry<BaseBlocks.OreBlock> AMETHYST_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.AMETHYST.oreName(), oreBlock(ModMaterials.AMETHYST))
      .properties(o -> {
        ModMaterials.AMETHYST.getOreBlockProperties(o);
        return o;
      })
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

  public static RegistryEntry<Block> AMETHYST_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.AMETHYST.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.AMETHYST_BLOCK)
      .build()
      .tag(MWTags.Blocks.AMETHYST_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> AMETHYST_STAIRS = MysticalWorld.REGISTRATE.block("amethyst_stairs", Material.IRON, stairsBlock(ModBlocks.AMETHYST_BLOCK))
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.AMETHYST_BLOCK), ModBlocks.AMETHYST_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> AMETHYST_SLAB = MysticalWorld.REGISTRATE.block("amethyst_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.AMETHYST_BLOCK), ModBlocks.AMETHYST_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> AMETHYST_WALL = MysticalWorld.REGISTRATE.block("amethyst_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.AMETHYST_BLOCK), ModBlocks.AMETHYST_WALL)
      )
      .blockstate(wall(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> AMETHYST_WIDE_POST = MysticalWorld.REGISTRATE.block("amethyst_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> AMETHYST_SMALL_POST = MysticalWorld.REGISTRATE.block("amethyst_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.AMETHYST.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.AMETHYST_BLOCK, ModBlocks.AMETHYST_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.AMETHYST_BLOCK))
      .register();

  // COPPER
  public static RegistryEntry<BaseBlocks.OreBlock> COPPER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.COPPER.oreName(), oreBlock(ModMaterials.COPPER))
      .properties(o -> {
        ModMaterials.COPPER.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.COPPER_ORE)
      .build()
      .tag(MWTags.Blocks.COPPER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> COPPER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.COPPER.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.COPPER_BLOCK)
      .build()
      .tag(MWTags.Blocks.COPPER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> COPPER_STAIRS = MysticalWorld.REGISTRATE.block("copper_stairs", Material.IRON, stairsBlock(ModBlocks.COPPER_BLOCK))
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.COPPER_BLOCK), ModBlocks.COPPER_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> COPPER_SLAB = MysticalWorld.REGISTRATE.block("copper_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.COPPER_BLOCK), ModBlocks.COPPER_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> COPPER_WALL = MysticalWorld.REGISTRATE.block("copper_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.COPPER_BLOCK), ModBlocks.COPPER_WALL)
      )
      .blockstate(wall(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> COPPER_WIDE_POST = MysticalWorld.REGISTRATE.block("copper_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.COPPER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> COPPER_SMALL_POST = MysticalWorld.REGISTRATE.block("copper_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.COPPER_BLOCK, ModBlocks.COPPER_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.COPPER_BLOCK))
      .register();


  // LEAD
  public static RegistryEntry<BaseBlocks.OreBlock> LEAD_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.oreName(), oreBlock(ModMaterials.LEAD))
      .properties(o -> {
        ModMaterials.LEAD.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.LEAD_ORE)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .tag(MWTags.Blocks.LEAD_ORE)
      .register();

  public static ResourceLocation RL = new ResourceLocation("mysticalworld:item/copper");

  public static RegistryEntry<Block> LEAD_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.LEAD_BLOCK)
      .build()
      .tag(MWTags.Blocks.LEAD_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> LEAD_STAIRS = MysticalWorld.REGISTRATE.block("lead_stairs", Material.IRON, stairsBlock(ModBlocks.LEAD_BLOCK))
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> LEAD_SLAB = MysticalWorld.REGISTRATE.block("lead_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> LEAD_WALL = MysticalWorld.REGISTRATE.block("lead_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_WALL)
      )
      .blockstate(wall(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> LEAD_WIDE_POST = MysticalWorld.REGISTRATE.block("lead_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.LEAD_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> LEAD_SMALL_POST = MysticalWorld.REGISTRATE.block("lead_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.LEAD_BLOCK))
      .register();


  // QUICKSILVER
  public static RegistryEntry<BaseBlocks.OreBlock> QUICKSILVER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.QUICKSILVER.oreName(), oreBlock(ModMaterials.QUICKSILVER))
      .properties(o -> {
        ModMaterials.QUICKSILVER.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUICKSILVER_ORE)
      .build()
      .tag(MWTags.Blocks.QUICKSILVER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> QUICKSILVER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.QUICKSILVER.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.QUICKSILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.QUICKSILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> QUICKSILVER_STAIRS = MysticalWorld.REGISTRATE.block("quicksilver_stairs", Material.IRON, stairsBlock(ModBlocks.QUICKSILVER_BLOCK))
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.QUICKSILVER_BLOCK), ModBlocks.QUICKSILVER_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> QUICKSILVER_SLAB = MysticalWorld.REGISTRATE.block("quicksilver_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.QUICKSILVER_BLOCK), ModBlocks.QUICKSILVER_SLAB, null, false)
      )
      .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> QUICKSILVER_WALL = MysticalWorld.REGISTRATE.block("quicksilver_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.QUICKSILVER_BLOCK), ModBlocks.QUICKSILVER_WALL)
      )
      .blockstate(wall(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> QUICKSILVER_WIDE_POST = MysticalWorld.REGISTRATE.block("quicksilver_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> QUICKSILVER_SMALL_POST = MysticalWorld.REGISTRATE.block("quicksilver_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.QUICKSILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  // SILVER
  public static RegistryEntry<BaseBlocks.OreBlock> SILVER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.oreName(), oreBlock(ModMaterials.SILVER))
      .properties(o -> {
        ModMaterials.SILVER.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.SILVER_ORE)
      .build()
      .tag(MWTags.Blocks.SILVER_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> SILVER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.SILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.SILVER_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> SILVER_STAIRS = MysticalWorld.REGISTRATE.block("silver_stairs", Material.IRON, stairsBlock(ModBlocks.SILVER_BLOCK))
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> SILVER_SLAB = MysticalWorld.REGISTRATE.block("silver_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_SLAB, null, false)
      )
     .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> SILVER_WALL = MysticalWorld.REGISTRATE.block("silver_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_WALL)
      )
      .blockstate(wall(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> SILVER_WIDE_POST = MysticalWorld.REGISTRATE.block("silver_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.SILVER_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> SILVER_SMALL_POST = MysticalWorld.REGISTRATE.block("silver_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.SILVER_BLOCK))
      .register();


  // TIN
  public static RegistryEntry<BaseBlocks.OreBlock> TIN_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.oreName(), oreBlock(ModMaterials.TIN))
      .properties(o -> {
        ModMaterials.TIN.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.TIN_ORE)
      .build()
      .tag(MWTags.Blocks.TIN_ORE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<Block> TIN_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.blockName(), Material.IRON, Block::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.TIN_BLOCK)
      .build()
      .tag(MWTags.Blocks.TIN_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> TIN_STAIRS = MysticalWorld.REGISTRATE.block("tin_stairs", Material.IRON, stairsBlock(ModBlocks.TIN_BLOCK))
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_STAIRS, null, false)
      )
      .blockstate(stairs(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> TIN_SLAB = MysticalWorld.REGISTRATE.block("tin_slab", Material.IRON, SlabBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_SLAB, null, false)
      )
     .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> TIN_WALL = MysticalWorld.REGISTRATE.block("tin_wall", Material.IRON, WallBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_WALL)
      )
      .blockstate(wall(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> TIN_WIDE_POST = MysticalWorld.REGISTRATE.block("tin_wide_post", Material.IRON, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.TIN_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> TIN_SMALL_POST = MysticalWorld.REGISTRATE.block("tin_small_post", Material.IRON, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SMALL_POST, null, false, p)
      )
      .blockstate(narrowPost(ModBlocks.TIN_BLOCK))
      .register();

  public static NonNullUnaryOperator<Block.Properties> PEARL_PROPS = o -> o.hardnessAndResistance(1.2F, 1.2F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1);

  public static RegistryEntry<Block> PEARL_BLOCK = MysticalWorld.REGISTRATE.block("pearl_block", Material.ROCK, Block::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.PEARL_BLOCK)
      .build()
      .tag(MWTags.Blocks.PEARL_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static RegistryEntry<StairsBlock> PEARL_STAIRS = MysticalWorld.REGISTRATE.block("pearl_stairs", Material.ROCK, stairsBlock(ModBlocks.PEARL_BLOCK))
      .properties(PEARL_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.PEARL_BLOCK))
      .register();

  public static RegistryEntry<SlabBlock> PEARL_SLAB = MysticalWorld.REGISTRATE.block("pearl_slab", Material.ROCK, SlabBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_SLAB, null, true)
      )
     .loot((p, t) -> p.registerLootTable(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.PEARL_BLOCK))
      .register();

  public static RegistryEntry<WallBlock> PEARL_WALL = MysticalWorld.REGISTRATE.block("pearl_wall", Material.ROCK, WallBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_WALL)
      )
      .blockstate(wall(ModBlocks.PEARL_BLOCK))
      .register();

  public static RegistryEntry<FenceBlock> PEARL_FENCE = MysticalWorld.REGISTRATE.block("pearl_fence", Material.ROCK, FenceBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) -> {
            p.fence(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_FENCE, null);
            p.stonecutting(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_FENCE, 2);
          }
      )
      .blockstate(fence(ModBlocks.PEARL_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.WidePostBlock> PEARL_WIDE_POST = MysticalWorld.REGISTRATE.block("pearl_wide_post", Material.ROCK, BaseBlocks.WidePostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.PEARL_BLOCK))
      .register();

  public static RegistryEntry<BaseBlocks.NarrowPostBlock> PEARL_SMALL_POST = MysticalWorld.REGISTRATE.block("pearl_small_post", Material.ROCK, BaseBlocks.NarrowPostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.PEARL_BLOCK))
      .register();

  public static void load() {
  }
}
