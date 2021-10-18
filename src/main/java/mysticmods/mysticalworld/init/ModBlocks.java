package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.blocks.*;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
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
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
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

  private static <T extends IForgeRegistryEntry<?>> String boneName(T block) {
    String[] init = Objects.requireNonNull(block.getRegistryName()).getPath().split("_");
    return init[0] + "_" + init[1] + init[2];
  }

  public static <T extends Item> ItemModelBuilder boneModel (DataGenContext<Item, T> ctx, RegistrateItemModelProvider p) {
    return p.withExistingParent(name(ctx.getEntry()), new ResourceLocation(MysticalWorld.MODID, "block/" + boneName(ctx.get())));
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

  public static <T extends SlabBlock> NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> slab(RegistryEntry<? extends T> parent) {
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
    return (b) -> new StairsBlock(() -> block.get().defaultBlockState(), b) {
      @Override
      public PushReaction getPistonPushReaction(BlockState pState) {
        if (this == ModBlocks.SOFT_OBSIDIAN_STAIRS.get()) {
          return PushReaction.BLOCK;
        }
        return super.getPistonPushReaction(pState);
      }
    };
  }

  public static NonNullFunction<Block.Properties, StairsBlock> stairsBlock(Supplier<? extends Block> block) {
    return (b) -> new StairsBlock(() -> block.get().defaultBlockState(), b) {
      @Override
      public PushReaction getPistonPushReaction(BlockState pState) {
        if (this == ModBlocks.SOFT_OBSIDIAN_STAIRS.get()) {
          return PushReaction.BLOCK;
        }
        return super.getPistonPushReaction(pState);
      }
    };
  }

  public static BlockEntry<UncannyGravelBlock> UNCANNY_GRAVEL = MysticalWorld.REGISTRATE.block("uncanny_gravel", Material.SAND, UncannyGravelBlock::new).properties(o -> o.strength(0.6f).sound(SoundType.GRAVEL))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.UNCANNY_GRAVEL))
      .tag(Tags.Items.GRAVEL)
      .build()
      .tag(Tags.Blocks.GRAVEL)
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 8)
          .pattern("GGG")
          .pattern("GPG")
          .pattern("GGG")
          .define('G', Tags.Items.GRAVEL)
          .define('P', Tags.Items.DYES_CYAN)
          .unlockedBy("has_gravel", RegistrateRecipeProvider.hasItem(Tags.Items.GRAVEL))
          .unlockedBy("has_purple_dye", RegistrateRecipeProvider.hasItem(Tags.Items.DYES_CYAN))
          .save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_gravel"))
      )
      .register();

  public static BlockEntry<SandBlock> UNCANNY_SAND = MysticalWorld.REGISTRATE.block("uncanny_sand", Material.SAND, (b) -> new SandBlock(0x6c36e0, b)).properties(o -> o.strength(0.5f).sound(SoundType.GRAVEL))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.UNCANNY_SAND))
      .tag(ItemTags.SAND)
      .build()
      .tag(BlockTags.SAND)
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 8)
            .pattern("GGG")
            .pattern("GPG")
            .pattern("GGG")
            .define('G', Tags.Items.SAND)
            .define('P', Tags.Items.DYES_PURPLE)
            .unlockedBy("has_sand", RegistrateRecipeProvider.hasItem(Tags.Items.SAND))
            .unlockedBy("has_purple_dye", RegistrateRecipeProvider.hasItem(Tags.Items.DYES_PURPLE))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_sand"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.UNCANNY_SAND.get()), Items.PURPLE_STAINED_GLASS, 0, 200);
      })
      .register();

  public static BlockEntry<PetrifiedFlowerBlock> STONEPETAL = MysticalWorld.REGISTRATE.block("stonepetal", Material.PLANT, PetrifiedFlowerBlock::new)
      .properties(o -> o.noCollission().instabreak().sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .item()
      .model((ctx, p) -> generated(ctx, p))
      .tag(ItemTags.FLOWERS)
      .build()
      .tag(BlockTags.FLOWERS)
      .recipe((ctx, p) -> {
        DataIngredient a = DataIngredient.items(ModBlocks.STONEPETAL.get());
        ShapelessRecipeBuilder.shapeless(Items.GRAY_DYE, 4).requires(ctx.getEntry()).unlockedBy("has_stonepetal", a.getCritereon(p)).save(p, new ResourceLocation(MysticalWorld.MODID, "gray_dye_from_stonepetal"));
      })
      .register();

  public static BlockEntry<AnywhereMushroomBlock> ANYWHERE_RED_MUSHROOM = MysticalWorld.REGISTRATE.block("red_mushroom", Material.PLANT, AnywhereMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(Blocks.RED_MUSHROOM)))))
      .loot((ctx, p) -> {
        ctx.dropOther(p, Blocks.RED_MUSHROOM);
      })
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .register();

  public static BlockEntry<AnywhereMushroomBlock> ANYWHERE_BROWN_MUSHROOM = MysticalWorld.REGISTRATE.block("brown_mushroom", Material.PLANT, AnywhereMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(Blocks.BROWN_MUSHROOM)))))
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .loot((ctx, p) -> {
        ctx.dropOther(p, Blocks.BROWN_MUSHROOM);
      })
      .register();

  public static BlockEntry<UncannyMushroomBlock> UNCANNY_MUSHROOM = MysticalWorld.REGISTRATE.block("uncanny_mushroom", Material.PLANT, UncannyMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 9).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ModBlocks.UNCANNY_MUSHROOM.get())))))
      .item()
      .model(ModBlocks::generated)
      .tag(Tags.Items.MUSHROOMS)
      .build()
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .recipe((ctx, p) -> {
        DataIngredient a = DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM.get());
        ShapelessRecipeBuilder.shapeless(Items.PURPLE_DYE, 4).requires(ctx.getEntry()).unlockedBy("has_uncanny_mushroom", a.getCritereon(p)).save(p, new ResourceLocation(MysticalWorld.MODID, "purple_dye_from_uncanny_mushroom"));
      })
      .register();

  public static BlockEntry<FlowerPotBlock> POTTED_STONEPETAL = MysticalWorld.REGISTRATE.block("potted_stonepetal", Material.DECORATION, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.STONEPETAL, AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/stonepetal")))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.droppingAndFlowerPot(ModBlocks.STONEPETAL.get())))
      .register();

  public static BlockEntry<FlowerPotBlock> POTTED_UNCANNY_MUSHROOM = MysticalWorld.REGISTRATE.block("potted_uncanny_mushroom", Material.DECORATION, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.UNCANNY_MUSHROOM, AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/uncanny_mushroom")))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.droppingAndFlowerPot(ModBlocks.UNCANNY_MUSHROOM.get())))
      .register();

  private static NonNullUnaryOperator<Block.Properties> THATCH_PROPS = (o) -> o.strength(1f).sound(SoundType.GRASS);
  private static NonNullUnaryOperator<Block.Properties> MUSHROOM_PROPS = (o) -> o.strength(0.2F).sound(SoundType.WOOD);

  public static BlockEntry<ThatchBlock> THATCH = MysticalWorld.REGISTRATE.block("thatch", Material.WOOD, ThatchBlock::new)
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.THATCH))
      .build()
      .recipe((ctx, p) -> {
            ShapedRecipeBuilder.shaped(ModBlocks.THATCH.get(), 32)
                .pattern("XY")
                .pattern("YX")
                .define('X', Blocks.HAY_BLOCK)
                .define('Y', Tags.Items.CROPS_WHEAT)
                .unlockedBy("has_hay", RegistrateRecipeProvider.hasItem(Blocks.HAY_BLOCK))
                .unlockedBy("has_wheat", RegistrateRecipeProvider.hasItem(Items.WHEAT))
                .save(p);
          }
      )
      .register();

  public static BlockEntry<StairsBlock> THATCH_STAIRS = MysticalWorld.REGISTRATE.block("thatch_stairs", Material.WOOD, stairsBlock(ModBlocks.THATCH))
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

  public static BlockEntry<SlabBlock> THATCH_SLAB = MysticalWorld.REGISTRATE.block("thatch_slab", Material.WOOD, SlabBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.THATCH))
      .register();

  public static BlockEntry<WallBlock> THATCH_WALL = MysticalWorld.REGISTRATE.block("thatch_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<FenceBlock> THATCH_FENCE = MysticalWorld.REGISTRATE.block("thatch_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> THATCH_FENCE_GATE = MysticalWorld.REGISTRATE.block("thatch_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.THATCH))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> THATCH_WIDE_POST = MysticalWorld.REGISTRATE.block("thatch_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.THATCH, ModBlocks.THATCH_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.THATCH))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> THATCH_SMALL_POST = MysticalWorld.REGISTRATE.block("thatch_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.THATCH, ModBlocks.THATCH_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.THATCH))
      .register();

  public static BlockEntry<OakAppleBlock> GALL_APPLE = MysticalWorld.REGISTRATE.block("gall_apple_crop", OakAppleBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.
          add(ModBlocks.GALL_APPLE.get(), RegistrateBlockLootTables.
              droppingAndBonusWhen(ModBlocks.GALL_APPLE.get(), Items.AIR, ModItems.GALL_APPLE.get(), new BlockStateProperty.Builder(ModBlocks.GALL_APPLE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, 3)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static BlockEntry<AubergineCropBlock> AUBERGINE_CROP = MysticalWorld.REGISTRATE.block("aubergine_crop", AubergineCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.
          add(ModBlocks.AUBERGINE_CROP.get(), RegistrateBlockLootTables.
              droppingAndBonusWhen(ModBlocks.AUBERGINE_CROP.get(), ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), new BlockStateProperty.Builder(ModBlocks.AUBERGINE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, 7)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static BlockEntry<WildCropBlock> WILD_AUBERGINE = MysticalWorld.REGISTRATE.block("wild_aubergine", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.withSurvivesExplosion(ModItems.AUBERGINE.get(), LootPool.lootPool().setRolls(RandomValueRange.between(1, 3)).add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE.get())))).withPool(RegistrateBlockLootTables.withSurvivesExplosion(ModItems.AUBERGINE_SEEDS.get(), LootPool.lootPool().setRolls(RandomValueRange.between(1, 2)).add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE_SEEDS.get()))))))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .partialState()
              .addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry()))))
      )
      .register();

  public static BlockEntry<WildCropBlock> WILD_WART = MysticalWorld.REGISTRATE.block("wild_wart", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.withSurvivesExplosion(Items.NETHER_WART, LootPool.lootPool().setRolls(RandomValueRange.between(1, 3)).add(ItemLootEntry.lootTableItem(Items.NETHER_WART)))).withPool(RegistrateBlockLootTables.withSurvivesExplosion(Items.NETHER_WART, LootPool.lootPool().setRolls(RandomValueRange.between(1, 2)).add(ItemLootEntry.lootTableItem(Items.NETHER_WART))))))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .partialState()
              .addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry()))))
      )
      .register();

  public static BlockEntry<HugeMushroomBlock> UNCANNY_MUSHROOM_BLOCK = MysticalWorld.REGISTRATE.block("uncanny_mushroom_block", Material.WOOD, HugeMushroomBlock::new)
      .properties(o -> o.strength(0.2F).sound(SoundType.WOOD).lightLevel(q -> 8))
      .loot((ctx, p) -> {
        ctx.add(p, RegistrateBlockLootTables.droppingItemRarely(p, ModBlocks.UNCANNY_MUSHROOM.get()));
      })
      .blockstate((ctx, p) -> {
        ModelFile model = p.models().withExistingParent(ctx.getName(), new ResourceLocation("minecraft", "block/template_single_face")).texture("texture", p.models().modLoc("block/uncanny_mushroom_block"));
        ModelFile inside = p.models().getExistingFile(new ResourceLocation("minecraft", "block/mushroom_block_inside"));

        p.getMultipartBuilder(ctx.getEntry())
            .part().modelFile(model).addModel().condition(HugeMushroomBlock.NORTH, true).end()
            .part().modelFile(model).uvLock(true).rotationY(90).addModel().condition(HugeMushroomBlock.EAST, true).end()
            .part().modelFile(model).uvLock(true).rotationY(180).addModel().condition(HugeMushroomBlock.SOUTH, true).end()
            .part().modelFile(model).uvLock(true).rotationY(270).addModel().condition(HugeMushroomBlock.WEST, true).end()
            .part().modelFile(model).uvLock(true).rotationX(270).addModel().condition(HugeMushroomBlock.UP, true).end()
            .part().modelFile(model).uvLock(true).rotationX(90).addModel().condition(HugeMushroomBlock.DOWN, true).end()
            .part().modelFile(inside).addModel().condition(HugeMushroomBlock.NORTH, false).end()
            .part().modelFile(inside).uvLock(false).rotationY(90).addModel().condition(HugeMushroomBlock.EAST, false).end()
            .part().modelFile(inside).uvLock(false).rotationY(180).addModel().condition(HugeMushroomBlock.SOUTH, false).end()
            .part().modelFile(inside).uvLock(false).rotationY(270).addModel().condition(HugeMushroomBlock.WEST, false).end()
            .part().modelFile(inside).uvLock(false).rotationX(270).addModel().condition(HugeMushroomBlock.UP, false).end()
            .part().modelFile(inside).uvLock(false).rotationX(90).addModel().condition(HugeMushroomBlock.DOWN, false).end();
      })
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation(MysticalWorld.MODID, "block/uncanny_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<Block> UNCANNY_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("uncanny_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> o.strength(0.2F).sound(SoundType.WOOD).lightLevel(q -> 8))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), p.blockTexture(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get())));
      })
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation(MysticalWorld.MODID, "block/uncanny_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<StairsBlock> UNCANNY_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("uncanny_mushroom_stairs", Material.WOOD, stairsBlock(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM_BLOCK), ModBlocks.UNCANNY_MUSHROOM_STAIRS, null, true)
      )
      .blockstate(stairs(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> UNCANNY_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("uncanny_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()), ModBlocks.UNCANNY_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.UNCANNY_MUSHROOM_FULL, () -> ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()))
      .register();

  public static BlockEntry<WallBlock> UNCANNY_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("uncanny_mushroom_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WALLS)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()), ModBlocks.UNCANNY_MUSHROOM_WALL)
      )
      .blockstate(wall(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<FenceBlock> UNCANNY_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("uncanny_mushroom_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ModBlocks::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM_BLOCK), ModBlocks.UNCANNY_MUSHROOM_FENCE, null)
      )
      .blockstate(fence(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<FenceGateBlock> UNCANNY_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("uncanny_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM_BLOCK), ModBlocks.UNCANNY_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> UNCANNY_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("uncanny_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.UNCANNY_MUSHROOM_BLOCK, ModBlocks.UNCANNY_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> UNCANNY_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("uncanny_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.UNCANNY_MUSHROOM_BLOCK, ModBlocks.UNCANNY_MUSHROOM_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.UNCANNY_MUSHROOM_BLOCK))
      .register();

  // MUSHROOM
  public static BlockEntry<Block> RED_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("red_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block")));
      })
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<Block> BROWN_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("brown_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block")));
      })
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<Block> STEM_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("stem_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem")));
      })
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem")))
      .build()
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapeless(ModBlocks.UNCANNY_MUSHROOM_FULL.get(), 1)
            .requires(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get())
            .unlockedBy("has_uncanny_mushroom_block", RegistrateRecipeProvider.hasItem(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_uncanny_mushroom_block_from_uncanny_mushroom"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get(), 1)
            .requires(ModBlocks.UNCANNY_MUSHROOM_FULL.get())
            .unlockedBy("has_full_red_mushroom_block", RegistrateRecipeProvider.hasItem(ModBlocks.UNCANNY_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "plain_uncanny_mushroom_block_from_full_uncanny_mushroom_block"));

        ShapelessRecipeBuilder.shapeless(ModBlocks.RED_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.RED_MUSHROOM_BLOCK)
            .unlockedBy("has_vanilla_red_mushroom", RegistrateRecipeProvider.hasItem(Blocks.RED_MUSHROOM_BLOCK))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_red_mushroom_block_from_red_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.RED_MUSHROOM_BLOCK, 1)
            .requires(ModBlocks.RED_MUSHROOM_FULL.get())
            .unlockedBy("has_full_red_mushroom_block", RegistrateRecipeProvider.hasItem(ModBlocks.RED_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_red_mushroom_block_from_full_red_mushroom_block"));

        ShapedRecipeBuilder.shaped(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get().asItem(), 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', ModBlocks.UNCANNY_MUSHROOM.get())
            .group("crafting")
            .unlockedBy("has_uncanny_mushroom", RegistrateRecipeProvider.hasItem(ModBlocks.UNCANNY_MUSHROOM.get()))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_mushroom_block_from_mushrooms"));
        ShapedRecipeBuilder.shaped(Blocks.RED_MUSHROOM_BLOCK, 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', Items.RED_MUSHROOM)
            .group("crafting")
            .unlockedBy("has_red_mushroom", RegistrateRecipeProvider.hasItem(Items.RED_MUSHROOM))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "red_mushroom_block_from_mushrooms"));
        ShapedRecipeBuilder.shaped(Blocks.BROWN_MUSHROOM_BLOCK, 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', Items.BROWN_MUSHROOM)
            .unlockedBy("has_brown_mushroom", RegistrateRecipeProvider.hasItem(Items.BROWN_MUSHROOM))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "brown_mushroom_block_from_mushrooms"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.BROWN_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.BROWN_MUSHROOM_BLOCK)
            .group("crafting")
            .unlockedBy("has_vanilla_brown_mushroom", RegistrateRecipeProvider.hasItem(Blocks.BROWN_MUSHROOM_BLOCK))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_brown_mushroom_block_from_brown_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.BROWN_MUSHROOM_BLOCK, 1)
            .requires(ModBlocks.BROWN_MUSHROOM_FULL.get())
            .unlockedBy("has_full_brown_mushroom_block", RegistrateRecipeProvider.hasItem(ModBlocks.BROWN_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_brown_mushroom_block_from_full_brown_mushroom_block"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.STEM_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.MUSHROOM_STEM)
            .unlockedBy("has_vanilla_stem_mushroom", RegistrateRecipeProvider.hasItem(Blocks.MUSHROOM_STEM))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_stem_mushroom_block_from_stem_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.MUSHROOM_STEM, 1)
            .requires(ModBlocks.STEM_MUSHROOM_FULL.get())
            .unlockedBy("has_full_stem_mushroom_block", RegistrateRecipeProvider.hasItem(ModBlocks.STEM_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_stem_mushroom_block_from_full_stem_mushroom_block"));
      })
      .register();

  public static BlockEntry<StairsBlock> RED_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("red_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.RED_MUSHROOM_BLOCK))
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

  public static BlockEntry<SlabBlock> RED_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("red_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.RED_MUSHROOM_FULL, () -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<WallBlock> RED_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("red_mushroom_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<FenceBlock> RED_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("red_mushroom_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> RED_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("red_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> RED_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("red_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.RED_MUSHROOM_BLOCK, ModBlocks.RED_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> RED_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("red_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<StairsBlock> BROWN_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("brown_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.BROWN_MUSHROOM_BLOCK))
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

  public static BlockEntry<SlabBlock> BROWN_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("brown_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.BROWN_MUSHROOM_FULL, () -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<WallBlock> BROWN_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("brown_mushroom_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<FenceBlock> BROWN_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> BROWN_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> BROWN_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK, ModBlocks.BROWN_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> BROWN_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<StairsBlock> MUSHROOM_STEM_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_stem_stairs", Material.WOOD, stairsBlock(() -> Blocks.MUSHROOM_STEM))
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

  public static BlockEntry<SlabBlock> MUSHROOM_STEM_SLAB = MysticalWorld.REGISTRATE.block("mushroom_stem_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.STEM_MUSHROOM_FULL, () -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<WallBlock> MUSHROOM_STEM_WALL = MysticalWorld.REGISTRATE.block("mushroom_stem_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<FenceBlock> MUSHROOM_STEM_FENCE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> MUSHROOM_STEM_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_FENCE_GATE, null)
      )
      .blockstate(gate(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUSHROOM_STEM_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.MUSHROOM_STEM, ModBlocks.MUSHROOM_STEM_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_STEM_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<Block> MUSHROOM_INSIDE = MysticalWorld.REGISTRATE.block("mushroom_inside_block", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.MUSHROOM_INSIDE))
      .build()
      .blockstate((ctx, p) -> {
        p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_block_inside")));
      })
      .recipe((ctx, p) -> {
        CookingRecipeBuilder.smelting(Ingredient.of(MWTags.Items.MUSHROOM_BLOCKS), ctx.getEntry(), 0.125f, 200).unlockedBy("has_mushroom", RegistrateRecipeProvider.hasItem(MWTags.Items.MUSHROOM_BLOCKS)).save(p, "mushroom_inside_from_smelting");
      })
      .register();

  public static BlockEntry<StairsBlock> MUSHROOM_INSIDE_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_inside_stairs", Material.WOOD, stairsBlock(ModBlocks.MUSHROOM_INSIDE))
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

  public static BlockEntry<SlabBlock> MUSHROOM_INSIDE_SLAB = MysticalWorld.REGISTRATE.block("mushroom_inside_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<WallBlock> MUSHROOM_INSIDE_WALL = MysticalWorld.REGISTRATE.block("mushroom_inside_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<FenceBlock> MUSHROOM_INSIDE_FENCE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> MUSHROOM_INSIDE_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUSHROOM_INSIDE_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUSHROOM_INSIDE, ModBlocks.MUSHROOM_INSIDE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_INSIDE_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<WetMudBlock> WET_MUD_BLOCK = MysticalWorld.REGISTRATE.block("wet_mud_block", Material.DIRT, WetMudBlock::new)
      .properties((o) -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(simpleBlockState("block/wet_mud_block"))
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shaped(ModBlocks.WET_MUD_BLOCK.get(), 8)
              .pattern("XXX")
              .pattern("XWX")
              .pattern("XXX")
              .define('X', Blocks.DIRT)
              .define('W', Items.WATER_BUCKET)
              .unlockedBy("has_dirt", RegistrateRecipeProvider.hasItem(Blocks.DIRT))
              .save(p)
      )
      .register();

  private static NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2f);

  public static BlockEntry<Block> MUD_BLOCK = MysticalWorld.REGISTRATE.block("mud_block", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BLOCK), ModBlocks.MUD_BLOCK, 0.15f)
      )
      .register();

  public static BlockEntry<StairsBlock> MUD_BLOCK_STAIRS = MysticalWorld.REGISTRATE.block("mud_block_stairs", Material.STONE, stairsBlock(ModBlocks.MUD_BLOCK))
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

  public static BlockEntry<SlabBlock> MUD_BLOCK_SLAB = MysticalWorld.REGISTRATE.block("mud_block_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<WallBlock> MUD_BLOCK_WALL = MysticalWorld.REGISTRATE.block("mud_block_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<FenceBlock> MUD_BLOCK_FENCE = MysticalWorld.REGISTRATE.block("mud_block_fence", Material.STONE, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_block_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUD_BLOCK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_block_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUD_BLOCK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_block_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<WetMudBrick> WET_MUD_BRICK = MysticalWorld.REGISTRATE.block("wet_mud_brick", Material.DIRT, WetMudBrick::new)
      .properties(o -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, p)
      )
      .register();

  public static BlockEntry<Block> MUD_BRICK = MysticalWorld.REGISTRATE.block("mud_brick", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BRICK), ModBlocks.MUD_BRICK, 0.15f);
        MysticalWorld.RECIPES.twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, p);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.MUD_BLOCK.get()), ModBlocks.MUD_BRICK.get())
            .unlocks("has_mud_block", RegistrateRecipeProvider.hasItem(ModBlocks.MUD_BLOCK.get()))
            .save(p, "mud_bricks_from_mud_blocks_stonecutting");
      })
      .register();

  public static BlockEntry<StairsBlock> MUD_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("mud_brick_stairs", Material.STONE, stairsBlock(ModBlocks.MUD_BRICK))
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

  public static BlockEntry<SlabBlock> MUD_BRICK_SLAB = MysticalWorld.REGISTRATE.block("mud_brick_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<WallBlock> MUD_BRICK_WALL = MysticalWorld.REGISTRATE.block("mud_brick_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<FenceBlock> MUD_BRICK_FENCE = MysticalWorld.REGISTRATE.block("mud_brick_fence", Material.STONE, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_brick_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUD_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_brick_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUD_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_brick_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
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

  private static NonNullUnaryOperator<Block.Properties> WOOD_PROPS = (o) -> o.sound(SoundType.WOOD).strength(2.0f).harvestTool(ToolType.AXE);

  public static BlockEntry<CharredLogBlock> CHARRED_WOOD = MysticalWorld.REGISTRATE.block("charred_wood", (o) -> new CharredLogBlock(o, true))
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
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 3).requires(log).requires(log).requires(log).requires(log).unlockedBy("has_charred_log", RegistrateRecipeProvider.hasItem(ModBlocks.CHARRED_LOG.get())).save(p, new ResourceLocation("mysticalworld", "charred_wood_from_logs"));
      })
      .register();

  public static BlockEntry<CharredLogBlock> CHARRED_LOG = MysticalWorld.REGISTRATE.block("charred_log", (o) -> new CharredLogBlock(o, false))
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

  public static BlockEntry<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = MysticalWorld.REGISTRATE.log("stripped_charred_wood")
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
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 3).requires(log).requires(log).requires(log).requires(log).unlockedBy("has_stripped_charred_log", RegistrateRecipeProvider.hasItem(ModBlocks.STRIPPED_CHARRED_LOG.get())).save(p, new ResourceLocation("mysticalworld", "stripped_charred_wood_from_logs"));
      })
      .register();

  public static BlockEntry<RotatedPillarBlock> STRIPPED_CHARRED_LOG = MysticalWorld.REGISTRATE.log("stripped_charred_log")
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

  public static BlockEntry<Block> CHARRED_PLANKS = MysticalWorld.REGISTRATE.block("charred_planks", Material.WOOD, Block::new)
      .properties(o -> o.sound(SoundType.WOOD).strength(2.0f, 3.0f))
      .tag(BlockTags.PLANKS)
      .item()
      .tag(ItemTags.PLANKS)
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) -> p.planks(DataIngredient.items(ModBlocks.CHARRED_LOG), ctx::getEntry))
      .blockstate(ModBlocks::simpleBlockState)
      .register();


  public static BlockEntry<StairsBlock> CHARRED_STAIRS = MysticalWorld.REGISTRATE.block("charred_stairs", Material.WOOD, stairsBlock(ModBlocks.CHARRED_PLANKS))
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

  public static BlockEntry<SlabBlock> CHARRED_SLAB = MysticalWorld.REGISTRATE.block("charred_slab", Material.WOOD, SlabBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.WOODEN_SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<FenceBlock> CHARRED_FENCE = MysticalWorld.REGISTRATE.block("charred_fence", Material.WOOD, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> CHARRED_FENCE_GATE = MysticalWorld.REGISTRATE.block("charred_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<WallBlock> CHARRED_WALL = MysticalWorld.REGISTRATE.block("charred_wall", Material.WOOD, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> CHARRED_WIDE_POST = MysticalWorld.REGISTRATE.block("charred_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> CHARRED_SMALL_POST = MysticalWorld.REGISTRATE.block("charred_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
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

  public static BlockEntry<Block> TERRACOTTA_BRICK = MysticalWorld.REGISTRATE.block("terracotta_brick", Material.STONE, Block::new)
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

  public static BlockEntry<StairsBlock> TERRACOTTA_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("terracotta_brick_stairs", Material.STONE, stairsBlock(ModBlocks.TERRACOTTA_BRICK))
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

  public static BlockEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = MysticalWorld.REGISTRATE.block("terracotta_brick_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<WallBlock> TERRACOTTA_BRICK_WALL = MysticalWorld.REGISTRATE.block("terracotta_brick_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence", Material.STONE, FenceBlock::new)
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

  public static BlockEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, null)
      )
      .blockstate(gate(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
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

  private static NonNullUnaryOperator<Block.Properties> IRON_PROPS = (o) -> o.sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.2f);

  public static BlockEntry<Block> IRON_BRICK = MysticalWorld.REGISTRATE.block("iron_brick", Material.METAL, Block::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        MysticalWorld.RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p);
      })
      .register();

  public static BlockEntry<StairsBlock> IRON_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("iron_brick_stairs", Material.METAL, stairsBlock(ModBlocks.IRON_BRICK))
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

  public static BlockEntry<SlabBlock> IRON_BRICK_SLAB = MysticalWorld.REGISTRATE.block("iron_brick_slab", Material.METAL, SlabBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.IRON_BRICK))
      .register();

  public static BlockEntry<WallBlock> IRON_BRICK_WALL = MysticalWorld.REGISTRATE.block("iron_brick_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> IRON_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("iron_brick_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WIDE_POST, null, false, p)
      )
      .blockstate(widePost(ModBlocks.IRON_BRICK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> IRON_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("iron_brick_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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

  private static final NonNullUnaryOperator<Block.Properties> SOFT_STONE_PROPS = o -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1f);
  private static final NonNullUnaryOperator<Block.Properties> BLACKENED_STONE_PROPS = SOFT_STONE_PROPS;

  public static BlockEntry<Block> SOFT_STONE = MysticalWorld.REGISTRATE.block("soft_stone", Block::new)
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

  public static BlockEntry<StairsBlock> SOFT_STONE_STAIRS = MysticalWorld.REGISTRATE.block("soft_stone_stairs", Material.STONE, stairsBlock(ModBlocks.SOFT_STONE))
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

  public static BlockEntry<SlabBlock> SOFT_STONE_SLAB = MysticalWorld.REGISTRATE.block("soft_stone_slab", Material.STONE, SlabBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SOFT_STONE))
      .register();

  public static BlockEntry<WallBlock> SOFT_STONE_WALL = MysticalWorld.REGISTRATE.block("soft_stone_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> SOFT_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_stone_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_STONE))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> SOFT_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_stone_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.SOFT_STONE))
      .register();

  // BLACKENED STONE
  public static BlockEntry<Block> BLACKENED_STONE = MysticalWorld.REGISTRATE.block("blackened_stone", Block::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 4)
            .pattern("AB")
            .pattern("BA")
            .define('A', Tags.Items.STONE)
            .define('B', Ingredient.of(Items.COAL, Items.CHARCOAL))
            .unlockedBy("has_stone", RegistrateRecipeProvider.hasItem(Tags.Items.STONE))
            .save(p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static BlockEntry<StairsBlock> BLACKENED_STONE_STAIRS = MysticalWorld.REGISTRATE.block("blackened_stone_stairs", Material.STONE, stairsBlock(ModBlocks.BLACKENED_STONE))
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

  public static BlockEntry<SlabBlock> BLACKENED_STONE_SLAB = MysticalWorld.REGISTRATE.block("blackened_stone_slab", Material.STONE, SlabBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.BLACKENED_STONE))
      .register();

  public static BlockEntry<WallBlock> BLACKENED_STONE_WALL = MysticalWorld.REGISTRATE.block("blackened_stone_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> BLACKENED_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("blackened_stone_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.BLACKENED_STONE, ModBlocks.BLACKENED_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.BLACKENED_STONE))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> BLACKENED_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("blackened_stone_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
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

  private static NonNullUnaryOperator<Block.Properties> SOFT_OBSIDIAN_PROPS = o -> o.sound(SoundType.STONE).strength(25f, 600f);

  public static BlockEntry<SoftObsidian.SoftObsidianBlock> SOFT_OBSIDIAN = MysticalWorld.REGISTRATE.block("soft_obsidian", SoftObsidian.SoftObsidianBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ModBlocks::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 4)
            .pattern("AB")
            .pattern("BA")
            .define('A', Tags.Items.STONE)
            .define('B', Tags.Items.OBSIDIAN)
            .unlockedBy("has_stone", RegistrateRecipeProvider.hasItem(Tags.Items.STONE))
            .unlockedBy("has_obsidian", RegistrateRecipeProvider.hasItem(Tags.Items.OBSIDIAN))
            .save(p);
      })
      .register();

  public static BlockEntry<StairsBlock> SOFT_OBSIDIAN_STAIRS = MysticalWorld.REGISTRATE.block("soft_obsidian_stairs", Material.STONE, stairsBlock(ModBlocks.SOFT_OBSIDIAN))
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

  public static BlockEntry<SoftObsidian.SoftObsidianSlabBlock> SOFT_OBSIDIAN_SLAB = MysticalWorld.REGISTRATE.block("soft_obsidian_slab", Material.STONE, SoftObsidian.SoftObsidianSlabBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianWallBlock> SOFT_OBSIDIAN_WALL = MysticalWorld.REGISTRATE.block("soft_obsidian_wall", Material.STONE, SoftObsidian.SoftObsidianWallBlock::new)
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

  public static BlockEntry<SoftObsidian.SoftObsidianWidePostBlock> SOFT_OBSIDIAN_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_wide_post", Material.STONE, SoftObsidian.SoftObsidianWidePostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianNarrowPostBlock> SOFT_OBSIDIAN_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_small_post", Material.STONE, SoftObsidian.SoftObsidianNarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> GRANITE_QUARTZ_ORE = MysticalWorld.REGISTRATE.block("granite_quartz_ore", oreBlock(ModMaterials.QUARTZ))
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
          p.add(ModBlocks.GRANITE_QUARTZ_ORE.get(), RegistrateBlockLootTables.droppingItemWithFortune(t, Items.QUARTZ))
      )
      .register();

  // AMETHYST
  public static BlockEntry<BaseBlocks.OreBlock> AMETHYST_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.AMETHYST.oreName(), oreBlock(ModMaterials.AMETHYST))
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
          p.add(ModBlocks.AMETHYST_ORE.get(), RegistrateBlockLootTables.droppingItemWithFortune(t, ModItems.AMETHYST_GEM.get()))
      )
      .register();

  public static BlockEntry<Block> AMETHYST_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.AMETHYST.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> AMETHYST_STAIRS = MysticalWorld.REGISTRATE.block("amethyst_stairs", Material.METAL, stairsBlock(ModBlocks.AMETHYST_BLOCK))
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

  public static BlockEntry<SlabBlock> AMETHYST_SLAB = MysticalWorld.REGISTRATE.block("amethyst_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.AMETHYST_BLOCK))
      .register();

  public static BlockEntry<WallBlock> AMETHYST_WALL = MysticalWorld.REGISTRATE.block("amethyst_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> AMETHYST_WIDE_POST = MysticalWorld.REGISTRATE.block("amethyst_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> AMETHYST_SMALL_POST = MysticalWorld.REGISTRATE.block("amethyst_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> COPPER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.COPPER.oreName(), oreBlock(ModMaterials.COPPER))
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

  public static BlockEntry<Block> COPPER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.COPPER.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> COPPER_STAIRS = MysticalWorld.REGISTRATE.block("copper_stairs", Material.METAL, stairsBlock(ModBlocks.COPPER_BLOCK))
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

  public static BlockEntry<SlabBlock> COPPER_SLAB = MysticalWorld.REGISTRATE.block("copper_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.COPPER_BLOCK))
      .register();

  public static BlockEntry<WallBlock> COPPER_WALL = MysticalWorld.REGISTRATE.block("copper_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> COPPER_WIDE_POST = MysticalWorld.REGISTRATE.block("copper_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> COPPER_SMALL_POST = MysticalWorld.REGISTRATE.block("copper_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> LEAD_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.oreName(), oreBlock(ModMaterials.LEAD))
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

  public static BlockEntry<Block> LEAD_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> LEAD_STAIRS = MysticalWorld.REGISTRATE.block("lead_stairs", Material.METAL, stairsBlock(ModBlocks.LEAD_BLOCK))
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

  public static BlockEntry<SlabBlock> LEAD_SLAB = MysticalWorld.REGISTRATE.block("lead_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.LEAD_BLOCK))
      .register();

  public static BlockEntry<WallBlock> LEAD_WALL = MysticalWorld.REGISTRATE.block("lead_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> LEAD_WIDE_POST = MysticalWorld.REGISTRATE.block("lead_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> LEAD_SMALL_POST = MysticalWorld.REGISTRATE.block("lead_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> QUICKSILVER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.QUICKSILVER.oreName(), oreBlock(ModMaterials.QUICKSILVER))
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

  public static BlockEntry<Block> QUICKSILVER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.QUICKSILVER.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> QUICKSILVER_STAIRS = MysticalWorld.REGISTRATE.block("quicksilver_stairs", Material.METAL, stairsBlock(ModBlocks.QUICKSILVER_BLOCK))
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

  public static BlockEntry<SlabBlock> QUICKSILVER_SLAB = MysticalWorld.REGISTRATE.block("quicksilver_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.QUICKSILVER_BLOCK))
      .register();

  public static BlockEntry<WallBlock> QUICKSILVER_WALL = MysticalWorld.REGISTRATE.block("quicksilver_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> QUICKSILVER_WIDE_POST = MysticalWorld.REGISTRATE.block("quicksilver_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> QUICKSILVER_SMALL_POST = MysticalWorld.REGISTRATE.block("quicksilver_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> SILVER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.oreName(), oreBlock(ModMaterials.SILVER))
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

  public static BlockEntry<Block> SILVER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> SILVER_STAIRS = MysticalWorld.REGISTRATE.block("silver_stairs", Material.METAL, stairsBlock(ModBlocks.SILVER_BLOCK))
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

  public static BlockEntry<SlabBlock> SILVER_SLAB = MysticalWorld.REGISTRATE.block("silver_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.SILVER_BLOCK))
      .register();

  public static BlockEntry<WallBlock> SILVER_WALL = MysticalWorld.REGISTRATE.block("silver_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> SILVER_WIDE_POST = MysticalWorld.REGISTRATE.block("silver_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> SILVER_SMALL_POST = MysticalWorld.REGISTRATE.block("silver_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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
  public static BlockEntry<BaseBlocks.OreBlock> TIN_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.oreName(), oreBlock(ModMaterials.TIN))
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

  public static BlockEntry<Block> TIN_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.blockName(), Material.METAL, Block::new)
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

  public static BlockEntry<StairsBlock> TIN_STAIRS = MysticalWorld.REGISTRATE.block("tin_stairs", Material.METAL, stairsBlock(ModBlocks.TIN_BLOCK))
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

  public static BlockEntry<SlabBlock> TIN_SLAB = MysticalWorld.REGISTRATE.block("tin_slab", Material.METAL, SlabBlock::new)
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
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.TIN_BLOCK))
      .register();

  public static BlockEntry<WallBlock> TIN_WALL = MysticalWorld.REGISTRATE.block("tin_wall", Material.METAL, WallBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> TIN_WIDE_POST = MysticalWorld.REGISTRATE.block("tin_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
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

  public static BlockEntry<BaseBlocks.NarrowPostBlock> TIN_SMALL_POST = MysticalWorld.REGISTRATE.block("tin_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
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

  public static NonNullUnaryOperator<Block.Properties> PEARL_PROPS = o -> o.strength(1.2F, 1.2F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1);

  public static BlockEntry<Block> PEARL_BLOCK = MysticalWorld.REGISTRATE.block("pearl_block", Material.STONE, Block::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .tag(MWTags.Items.PEARL_BLOCK)
      .build()
      .tag(MWTags.Blocks.PEARL_STORAGE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  public static BlockEntry<StairsBlock> PEARL_STAIRS = MysticalWorld.REGISTRATE.block("pearl_stairs", Material.STONE, stairsBlock(ModBlocks.PEARL_BLOCK))
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

  public static BlockEntry<SlabBlock> PEARL_SLAB = MysticalWorld.REGISTRATE.block("pearl_slab", Material.STONE, SlabBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ModBlocks::itemModel)
      .build()
      .tag(BlockTags.SLABS)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.droppingSlab(t)))
      .blockstate(slab(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<WallBlock> PEARL_WALL = MysticalWorld.REGISTRATE.block("pearl_wall", Material.STONE, WallBlock::new)
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

  public static BlockEntry<FenceBlock> PEARL_FENCE = MysticalWorld.REGISTRATE.block("pearl_fence", Material.STONE, FenceBlock::new)
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

  public static BlockEntry<BaseBlocks.WidePostBlock> PEARL_WIDE_POST = MysticalWorld.REGISTRATE.block("pearl_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_WIDE_POST, null, true, p)
      )
      .blockstate(widePost(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> PEARL_SMALL_POST = MysticalWorld.REGISTRATE.block("pearl_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ModBlocks::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_SMALL_POST, null, true, p)
      )
      .blockstate(narrowPost(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_1 = MysticalWorld.REGISTRATE.block("bone_pile_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE, 0))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Bone Pile")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("BB")
            .pattern("BB")
            .pattern("BB")
            .define('B', Tags.Items.BONES)
            .unlockedBy("has_bones", RegistrateRecipeProvider.hasItem(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_4.get()).unlockedBy("has_bone_pile_4", RegistrateRecipeProvider.hasItem(ModBlocks.BONE_PILE_4.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "bone_pile_1_from_bone_pile_4"));
      })
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_2 = MysticalWorld.REGISTRATE.block("bone_pile_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE, 1))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_1.get()).unlockedBy("has_bone_pile_1", RegistrateRecipeProvider.hasItem(ModBlocks.BONE_PILE_1.get())).save(p))
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_3 = MysticalWorld.REGISTRATE.block("bone_pile_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE, 2))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_2.get()).unlockedBy("has_bone_pile_2", RegistrateRecipeProvider.hasItem(ModBlocks.BONE_PILE_2.get())).save(p))
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_4 = MysticalWorld.REGISTRATE.block("bone_pile_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE, 2))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_3.get()).unlockedBy("has_bone_pile_3", RegistrateRecipeProvider.hasItem(ModBlocks.BONE_PILE_3.get())).save(p))
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_1 = MysticalWorld.REGISTRATE.block("skeleton_bottom_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM, 0))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("BBB")
            .pattern("BBB")
            .pattern("B B")
            .define('B', Tags.Items.BONES)
            .unlockedBy("has_bones", RegistrateRecipeProvider.hasItem(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_3.get()).unlockedBy("has_skeleton_bottom_3", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_BOTTOM_3.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "skeleton_bottom_1_from_skeleton_bottom_4"));
      })
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_2 = MysticalWorld.REGISTRATE.block("skeleton_bottom_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM, 0))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_1.get()).unlockedBy("has_skeleton_bottom_1", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_BOTTOM_1.get())).save(p)
      )
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_3 = MysticalWorld.REGISTRATE.block("skeleton_bottom_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM, 2))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_2.get()).unlockedBy("has_skeleton_bottom_2", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_BOTTOM_2.get())).save(p)
      )
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_1 = MysticalWorld.REGISTRATE.block("skeleton_top_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP, 0))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("B B")
            .pattern("BBB")
            .pattern("BBB")
            .define('B', Tags.Items.BONES)
            .unlockedBy("has_bones", RegistrateRecipeProvider.hasItem(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_4.get()).unlockedBy("has_skeleton_top_4", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_TOP_4.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "skeleton_top_1_from_skeleton_top_4"));
      })
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_2 = MysticalWorld.REGISTRATE.block("skeleton_top_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP, 0))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_1.get()).unlockedBy("has_skeleton_top_1", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_TOP_1.get())).save(p)
      )
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_3 = MysticalWorld.REGISTRATE.block("skeleton_top_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP, 2))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_2.get()).unlockedBy("has_skeleton_top_2", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_TOP_2.get())).save(p)
      )
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_4 = MysticalWorld.REGISTRATE.block("skeleton_top_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP, 2))
      .properties(o -> AbstractBlock.Properties.copy(Blocks.BONE_BLOCK))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()

          )
      )
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_3.get()).unlockedBy("has_skeleton_top_3", RegistrateRecipeProvider.hasItem(ModBlocks.SKELETON_TOP_3.get())).save(p)
      )
      .register();

  public static void load() {
  }
}
