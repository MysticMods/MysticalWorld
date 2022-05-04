package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
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
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;
import noobanidus.libs.noobutil.block.BaseBlocks;
import noobanidus.libs.noobutil.data.generator.BlockstateGenerator;
import noobanidus.libs.noobutil.data.generator.ItemModelGenerator;
import noobanidus.libs.noobutil.ingredient.ExcludingIngredient;
import noobanidus.libs.noobutil.loot.condition.LootConditions;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ModBlocks {
  private static final NonNullUnaryOperator<Block.Properties> THATCH_PROPS = (o) -> o.strength(1f).sound(SoundType.GRASS);
  private static final NonNullUnaryOperator<Block.Properties> MUSHROOM_PROPS = (o) -> o.strength(0.2F).sound(SoundType.WOOD);
  private static final NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2f);
  private static final NonNullUnaryOperator<Block.Properties> WOOD_PROPS = (o) -> o.sound(SoundType.WOOD);
  private static final NonNullUnaryOperator<Block.Properties> PEARL_PROPS = o -> o.strength(1.2F, 1.2F).sound(SoundType.STONE);
  private static final NonNullUnaryOperator<Block.Properties> CARAPACE_PROPS = o -> o.strength(1f, 1f).sound(SoundType.CALCITE);
  private static final NonNullUnaryOperator<Block.Properties> IRON_PROPS = (o) -> o.sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.2f);
  private static final NonNullUnaryOperator<Block.Properties> SOFT_STONE_PROPS = o -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1f);
  private static final NonNullUnaryOperator<Block.Properties> BLACKENED_STONE_PROPS = SOFT_STONE_PROPS;
  private static final NonNullUnaryOperator<Block.Properties> SOFT_OBSIDIAN_PROPS = o -> o.sound(SoundType.STONE).strength(25f, 600f);
  private static final NonNullUnaryOperator<Block.Properties> ORE_PROPERTIES = o -> BlockBehaviour.Properties.copy(Blocks.IRON_ORE);
  private static final NonNullUnaryOperator<Block.Properties> DEEPSLATE_ORE_PROPERTIES = o -> BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE);

  private static <T extends IForgeRegistryEntry<?>> String boneName(T block) {
    String[] init = Objects.requireNonNull(block.getRegistryName()).getPath().split("_");
    return init[0] + "_" + init[1] + init[2];
  }

  public static <T extends Item> void boneModel(DataGenContext<Item, T> ctx, RegistrateItemModelProvider p) {
    p.withExistingParent(name(ctx.getEntry()), new ResourceLocation(MysticalWorld.MODID, "block/" + boneName(ctx.get())));
  }

  private static <T extends IForgeRegistryEntry<?>> String name(T block) {
    return Objects.requireNonNull(block.getRegistryName()).getPath();
  }

  public static NonNullFunction<Block.Properties, StairBlock> stairsBlock(RegistryEntry<? extends Block> block) {
    return (b) -> new StairBlock(() -> block.get().defaultBlockState(), b) {
      @SuppressWarnings({"NullableProblems", "deprecation"})
      @Override
      public PushReaction getPistonPushReaction(BlockState pState) {
        if (this == ModBlocks.SOFT_OBSIDIAN_STAIRS.get()) {
          return PushReaction.BLOCK;
        }
        return super.getPistonPushReaction(pState);
      }
    };
  }

  public static NonNullFunction<Block.Properties, StairBlock> stairsBlock(Supplier<? extends Block> block) {
    return (b) -> new StairBlock(() -> block.get().defaultBlockState(), b) {
      @SuppressWarnings({"NullableProblems", "deprecation"})
      @Override
      public PushReaction getPistonPushReaction(BlockState pState) {
        if (this == ModBlocks.SOFT_OBSIDIAN_STAIRS.get()) {
          return PushReaction.BLOCK;
        }
        return super.getPistonPushReaction(pState);
      }
    };
  }

  public static <T extends Block> NonNullBiConsumer<RegistrateBlockLootTables, T> boneLoot() {
    return (t, p) -> t.add(p, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(p).when(LootConditions.HAS_SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))));
  }

  // ** Sans block item **
  public static BlockEntry<FlowerPotBlock> POTTED_STONEPETAL = MysticalWorld.REGISTRATE.block("potted_stonepetal", Material.DECORATION, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.STONEPETAL, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/stonepetal")))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.createPotFlowerItemTable(ModBlocks.STONEPETAL.get())))
      .tag(BlockTags.FLOWER_POTS)
      .register();

  public static BlockEntry<OakAppleBlock> GALL_APPLE = MysticalWorld.REGISTRATE.block("gall_apple_crop", OakAppleBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.
          add(ModBlocks.GALL_APPLE.get(), RegistrateBlockLootTables.
              createCropDrops(ModBlocks.GALL_APPLE.get(), Items.AIR, ModItems.GALL_APPLE.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.GALL_APPLE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 3)))))
      .blockstate(NonNullBiConsumer.noop())
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<AubergineCropBlock> AUBERGINE_CROP = MysticalWorld.REGISTRATE.block("aubergine_crop", AubergineCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.
          add(ModBlocks.AUBERGINE_CROP.get(), RegistrateBlockLootTables.
              createCropDrops(ModBlocks.AUBERGINE_CROP.get(), ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.AUBERGINE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))))
      .blockstate(NonNullBiConsumer.noop())
      .tag(MWTags.Blocks.CROPS, MWTags.Blocks.AUBERGINE_CROP, BlockTags.CROPS, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<WildCropBlock> WILD_AUBERGINE = MysticalWorld.REGISTRATE.block("wild_aubergine", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.applyExplosionCondition(ModItems.AUBERGINE.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(ModItems.AUBERGINE.get())))).withPool(RegistrateBlockLootTables.applyExplosionCondition(ModItems.AUBERGINE_SEEDS.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(ModItems.AUBERGINE_SEEDS.get()))))))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .partialState()
              .addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry()))))
      )
      .tag(BlockTags.CROPS, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<WildCropBlock> WILD_WART = MysticalWorld.REGISTRATE.block("wild_wart", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(Items.NETHER_WART)))).withPool(RegistrateBlockLootTables.applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.NETHER_WART))))))
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry())
              .partialState()
              .addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry()))))
      )
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  // ** WITH BLOCK ITEM **
  private static <T extends Block> NonNullBiConsumer<RegistrateBlockLootTables, T> oreLoot(Supplier<Item> drops) {
    return (ctx, p) -> ctx.add(p, RegistrateBlockLootTables.createOreDrop(p, drops.get()));
  }

  public static BlockEntry<OreBlock> GRANITE_QUARTZ_ORE = MysticalWorld.REGISTRATE.block("granite_quartz_ore", (p) -> new OreBlock(p, UniformInt.of(2, 5)))
      .properties(ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.QUARTZ_ORE)
      .build()
      .tag(MWTags.Blocks.QUARTZ_ORE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(() -> Items.QUARTZ))
      .register();

  public static BlockEntry<OreBlock> TIN_ORE = MysticalWorld.REGISTRATE.block("tin_ore", OreBlock::new)
      .properties(ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.TIN_ORE)
      .build()
      .tag(MWTags.Blocks.TIN_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.RAW_TIN))
      .register();

  public static BlockEntry<OreBlock> DEEPSLATE_TIN_ORE = MysticalWorld.REGISTRATE.block("deepslate_tin_ore", OreBlock::new)
      .properties(DEEPSLATE_ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.TIN_ORE)
      .build()
      .tag(MWTags.Blocks.TIN_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL, Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.RAW_TIN))
      .register();

  public static BlockEntry<OreBlock> LEAD_ORE = MysticalWorld.REGISTRATE.block("lead_ore", OreBlock::new)
      .properties(ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.LEAD_ORE)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .tag(MWTags.Blocks.LEAD_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
      .loot(oreLoot(ModItems.RAW_LEAD))
      .register();

  public static BlockEntry<OreBlock> DEEPSLATE_LEAD_ORE = MysticalWorld.REGISTRATE.block("deepslate_lead_ore", OreBlock::new)
      .properties(DEEPSLATE_ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.LEAD_ORE)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .tag(MWTags.Blocks.LEAD_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)
      .loot(oreLoot(ModItems.RAW_LEAD))
      .register();

  public static BlockEntry<OreBlock> SILVER_ORE = MysticalWorld.REGISTRATE.block("silver_ore", OreBlock::new)
      .properties(ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SILVER_ORE)
      .build()
      .tag(MWTags.Blocks.SILVER_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.RAW_SILVER))
      .register();

  public static BlockEntry<OreBlock> DEEPSLATE_SILVER_ORE = MysticalWorld.REGISTRATE.block("deepslate_silver_ore", OreBlock::new)
      .properties(DEEPSLATE_ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SILVER_ORE)
      .build()
      .tag(MWTags.Blocks.SILVER_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.RAW_SILVER))
      .register();

  public static BlockEntry<OreBlock> SAPPHIRE_ORE = MysticalWorld.REGISTRATE.block("sapphire_ore", (p) -> new OreBlock(p, UniformInt.of(3, 7)))
      .properties(ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SAPPHIRE_ORE)
      .build()
      .tag(MWTags.Blocks.SAPPHIRE_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.SAPPHIRE_GEM))
      .register();

  public static BlockEntry<OreBlock> DEEPSLATE_SAPPHIRE_ORE = MysticalWorld.REGISTRATE.block("deepslate_sapphire_ore", (p) -> new OreBlock(p, UniformInt.of(3, 7)))
      .properties(DEEPSLATE_ORE_PROPERTIES)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SAPPHIRE_ORE)
      .build()
      .tag(MWTags.Blocks.SAPPHIRE_ORE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot(oreLoot(ModItems.SAPPHIRE_GEM))
      .register();

  public static BlockEntry<Block> RAW_TIN_BLOCK = MysticalWorld.REGISTRATE.block("raw_tin_block", Material.METAL, Block::new)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(MWTags.Blocks.RAW_TIN_STORAGE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shaped(ctx.getEntry())
              .pattern("###")
              .pattern("###")
              .pattern("###")
              .define('#', MWTags.Items.RAW_TIN)
              .unlockedBy("has_raw_tin", RegistrateRecipeProvider.has(MWTags.Items.RAW_TIN))
              .save(p)
      )
      .register();

  public static BlockEntry<Block> RAW_LEAD_BLOCK = MysticalWorld.REGISTRATE.block("raw_lead_block", Material.METAL, Block::new)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(MWTags.Blocks.RAW_LEAD_STORAGE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shaped(ctx.getEntry())
              .pattern("###")
              .pattern("###")
              .pattern("###")
              .define('#', MWTags.Items.RAW_LEAD)
              .unlockedBy("has_raw_tin", RegistrateRecipeProvider.has(MWTags.Items.RAW_LEAD))
              .save(p)
      )
      .register();

  public static BlockEntry<Block> RAW_SILVER_BLOCK = MysticalWorld.REGISTRATE.block("raw_silver_block", Material.METAL, Block::new)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(MWTags.Blocks.RAW_SILVER_STORAGE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shaped(ctx.getEntry())
              .pattern("###")
              .pattern("###")
              .pattern("###")
              .define('#', MWTags.Items.RAW_SILVER)
              .unlockedBy("has_raw_tin", RegistrateRecipeProvider.has(MWTags.Items.RAW_SILVER))
              .save(p)
      )
      .register();

  public static BlockEntry<ThatchBlock> THATCH = MysticalWorld.REGISTRATE.block("thatch", Material.WOOD, ThatchBlock::new)
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.THATCH))
      .build()
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModBlocks.THATCH.get(), 32)
          .pattern("XY")
          .pattern("YX")
          .define('X', Blocks.HAY_BLOCK)
          .define('Y', Tags.Items.CROPS_WHEAT)
          .unlockedBy("has_hay", RegistrateRecipeProvider.has(Blocks.HAY_BLOCK))
          .unlockedBy("has_wheat", RegistrateRecipeProvider.has(Items.WHEAT))
          .save(p)
      )
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  // MUSHROOM
  public static BlockEntry<Block> RED_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("red_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block")))
      .build()
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<Block> BROWN_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("brown_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block")))
      .build()
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<Block> STEM_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("stem_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem")))
      .build()
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapeless(ModBlocks.RED_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.RED_MUSHROOM_BLOCK)
            .unlockedBy("has_vanilla_red_mushroom", RegistrateRecipeProvider.has(Blocks.RED_MUSHROOM_BLOCK))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_red_mushroom_block_from_red_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.RED_MUSHROOM_BLOCK, 1)
            .requires(ModBlocks.RED_MUSHROOM_FULL.get())
            .unlockedBy("has_full_red_mushroom_block", RegistrateRecipeProvider.has(ModBlocks.RED_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_red_mushroom_block_from_full_red_mushroom_block"));
        ShapedRecipeBuilder.shaped(Blocks.RED_MUSHROOM_BLOCK, 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', Items.RED_MUSHROOM)
            .group("crafting")
            .unlockedBy("has_red_mushroom", RegistrateRecipeProvider.has(Items.RED_MUSHROOM))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "red_mushroom_block_from_mushrooms"));
        ShapedRecipeBuilder.shaped(Blocks.BROWN_MUSHROOM_BLOCK, 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', Items.BROWN_MUSHROOM)
            .unlockedBy("has_brown_mushroom", RegistrateRecipeProvider.has(Items.BROWN_MUSHROOM))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "brown_mushroom_block_from_mushrooms"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.BROWN_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.BROWN_MUSHROOM_BLOCK)
            .group("crafting")
            .unlockedBy("has_vanilla_brown_mushroom", RegistrateRecipeProvider.has(Blocks.BROWN_MUSHROOM_BLOCK))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_brown_mushroom_block_from_brown_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.BROWN_MUSHROOM_BLOCK, 1)
            .requires(ModBlocks.BROWN_MUSHROOM_FULL.get())
            .unlockedBy("has_full_brown_mushroom_block", RegistrateRecipeProvider.has(ModBlocks.BROWN_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_brown_mushroom_block_from_full_brown_mushroom_block"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.STEM_MUSHROOM_FULL.get(), 1)
            .requires(Blocks.MUSHROOM_STEM)
            .unlockedBy("has_vanilla_stem_mushroom", RegistrateRecipeProvider.has(Blocks.MUSHROOM_STEM))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_stem_mushroom_block_from_stem_mushroom"));
        ShapelessRecipeBuilder.shapeless(Blocks.MUSHROOM_STEM, 1)
            .requires(ModBlocks.STEM_MUSHROOM_FULL.get())
            .unlockedBy("has_full_stem_mushroom_block", RegistrateRecipeProvider.has(ModBlocks.STEM_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "vanilla_stem_mushroom_block_from_full_stem_mushroom_block"));
      })
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<Block> MUSHROOM_INSIDE = MysticalWorld.REGISTRATE.block("mushroom_inside_block", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.MUSHROOM_INSIDE))
      .build()
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_block_inside"))))
      .recipe((ctx, p) -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(MWTags.Items.MUSHROOM_BLOCKS), ctx.getEntry(), 0.125f, 200).unlockedBy("has_mushroom", RegistrateRecipeProvider.has(MWTags.Items.MUSHROOM_BLOCKS)).save(p, "mushroom_inside_from_smelting"))
      .tag(MWTags.Blocks.MUSHROOM_BLOCKS, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<WetMudBlock> WET_MUD_BLOCK = MysticalWorld.REGISTRATE.block("wet_mud_block", Material.DIRT, WetMudBlock::new)
      .properties((o) -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator.simpleBlockstate("block/wet_mud_block"))
      .recipe((ctx, p) ->
          ShapedRecipeBuilder.shaped(ModBlocks.WET_MUD_BLOCK.get(), 8)
              .pattern("XXX")
              .pattern("XWX")
              .pattern("XXX")
              .define('X', Blocks.DIRT)
              .define('W', Items.WATER_BUCKET)
              .unlockedBy("has_dirt", RegistrateRecipeProvider.has(Blocks.DIRT))
              .save(p)
      )
      .tag(BlockTags.MINEABLE_WITH_SHOVEL)
      .register();

  public static BlockEntry<Block> WET_MUD_BRICK = MysticalWorld.REGISTRATE.block("wet_mud_brick", Material.DIRT, Block::new)
      .properties(o -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, p)
      )
      .tag(BlockTags.MINEABLE_WITH_SHOVEL)
      .register();


  public static BlockEntry<Block> MUD_BLOCK = MysticalWorld.REGISTRATE.block("mud_block", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) ->
          p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BLOCK), ModBlocks.MUD_BLOCK, 0.15f)
      )
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<Block> MUD_BRICK = MysticalWorld.REGISTRATE.block("mud_brick", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> {
        p.smelting(DataIngredient.items(ModBlocks.WET_MUD_BRICK), ModBlocks.MUD_BRICK, 0.15f);
        MysticalWorld.RECIPES.twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, p);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.MUD_BLOCK.get()), ModBlocks.MUD_BRICK.get())
            .unlockedBy("has_mud_block", RegistrateRecipeProvider.has(ModBlocks.MUD_BLOCK.get()))
            .save(p, "mud_bricks_from_mud_blocks_stonecutting");
      })
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<Block> TERRACOTTA_BRICK = MysticalWorld.REGISTRATE.block("terracotta_brick", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> {
        p.stonecutting(DataIngredient.items(Items.TERRACOTTA), ModBlocks.TERRACOTTA_BRICK);
        MysticalWorld.RECIPES.twoByTwo(() -> Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, null, p);
      })
      .tag(MWTags.Blocks.TERRACOTTA, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<Block> IRON_BRICK = MysticalWorld.REGISTRATE.block("iron_brick", Material.METAL, Block::new)
      .properties(IRON_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();


  // SOFT STONE

  public static BlockEntry<Block> SOFT_STONE = MysticalWorld.REGISTRATE.block("soft_stone", Block::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SOFT_STONE)
      .build()
      .recipe((ctx, p) -> {
        p.stonecutting(DataIngredient.items(Items.SMOOTH_STONE), ModBlocks.SOFT_STONE);
        MysticalWorld.RECIPES.twoByTwo(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, null, p);
      })
      .tag(MWTags.Blocks.SOFT_STONE, BlockTags.BASE_STONE_OVERWORLD, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();


  // BLACKENED STONE
  public static BlockEntry<Block> BLACKENED_STONE = MysticalWorld.REGISTRATE.block("blackened_stone", Block::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 4)
          .pattern("AB")
          .pattern("BA")
          .define('A', Tags.Items.STONE)
          .define('B', Ingredient.of(Items.COAL, Items.CHARCOAL))
          .unlockedBy("has_stone", RegistrateRecipeProvider.has(Tags.Items.STONE))
          .save(p))
      .tag(Tags.Blocks.STONE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();


  // SMOOTH OBSIDIAN
  public static BlockEntry<SoftObsidian.SoftObsidianBlock> SOFT_OBSIDIAN = MysticalWorld.REGISTRATE.block("soft_obsidian", SoftObsidian.SoftObsidianBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 4)
          .pattern("AB")
          .pattern("BA")
          .define('A', Tags.Items.STONE)
          .define('B', ExcludingIngredient.create(Tags.Items.OBSIDIAN, ctx.getEntry()))
          .unlockedBy("has_stone", RegistrateRecipeProvider.has(Tags.Items.STONE))
          .unlockedBy("has_obsidian", RegistrateRecipeProvider.has(Tags.Items.OBSIDIAN))
          .save(p))
      .register();


  public static BlockEntry<CharredLogBlock> CHARRED_WOOD = MysticalWorld.REGISTRATE.block("charred_wood", (o) -> new CharredLogBlock(o, true))
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getEntry().getRegistryName().getPath(), p.blockTexture(ModBlocks.CHARRED_LOG.get()))))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) -> {
        DataIngredient log = DataIngredient.items(ModBlocks.CHARRED_LOG.get());
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 3).requires(log).requires(log).requires(log).requires(log).unlockedBy("has_charred_log", RegistrateRecipeProvider.has(ModBlocks.CHARRED_LOG.get())).save(p, new ResourceLocation("mysticalworld", "charred_wood_from_logs"));
      })
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<CharredLogBlock> CHARRED_LOG = MysticalWorld.REGISTRATE.block("charred_log", (o) -> new CharredLogBlock(o, false))
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.logBlock(ctx.getEntry()))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<RotatedPillarBlock> STRIPPED_CHARRED_WOOD = MysticalWorld.REGISTRATE.log("stripped_charred_wood")
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(Objects.requireNonNull(ctx.getEntry().getRegistryName()).getPath(), p.blockTexture(ModBlocks.STRIPPED_CHARRED_LOG.get()))))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) -> {
        DataIngredient log = DataIngredient.items(ModBlocks.STRIPPED_CHARRED_LOG.get());
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 3).requires(log).requires(log).requires(log).requires(log).unlockedBy("has_stripped_charred_log", RegistrateRecipeProvider.has(ModBlocks.STRIPPED_CHARRED_LOG.get())).save(p, new ResourceLocation("mysticalworld", "stripped_charred_wood_from_logs"));
      })
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<RotatedPillarBlock> STRIPPED_CHARRED_LOG = MysticalWorld.REGISTRATE.log("stripped_charred_log")
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.logBlock(ctx.getEntry()))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<Block> CHARRED_PLANKS = MysticalWorld.REGISTRATE.block("charred_planks", Material.WOOD, Block::new)
      .properties(o -> o.sound(SoundType.WOOD).strength(2.0f, 3.0f))
      .tag(BlockTags.PLANKS)
      .item()
      .tag(ItemTags.PLANKS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) -> p.planks(DataIngredient.items(ModBlocks.CHARRED_LOG), ctx::getEntry))
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<Block> SAPPHIRE_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.SAPPHIRE.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SAPPHIRE_BLOCK)
      .build()
      .tag(MWTags.Blocks.SAPPHIRE_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> LEAD_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.LEAD_BLOCK)
      .build()
      .tag(MWTags.Blocks.LEAD_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> ORICHALCUM_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.ORICHALCUM.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.ORICHALCUM_BLOCK)
      .build()
      .tag(MWTags.Blocks.ORICHALCUM_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> SILVER_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SILVER_BLOCK)
      .build()
      .tag(MWTags.Blocks.SILVER_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> TIN_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.TIN_BLOCK)
      .build()
      .tag(MWTags.Blocks.TIN_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> PEARL_BLOCK = MysticalWorld.REGISTRATE.block("pearl_block", Material.STONE, Block::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.PEARL_BLOCK)
      .build()
      .tag(MWTags.Blocks.PEARL_STORAGE, BlockTags.BEACON_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<Block> CARAPACE_BLOCK = MysticalWorld.REGISTRATE.block("carapace_block", Material.WOOL, Block::new)
      .properties(CARAPACE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.CARAPACE_BLOCK)
      .build()
      .tag(MWTags.Blocks.CARAPACE_STORAGE, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  public static BlockEntry<SlabBlock> THATCH_SLAB = MysticalWorld.REGISTRATE.block("thatch_slab", Material.WOOD, SlabBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.THATCH))
      .register();

  public static BlockEntry<SlabBlock> RED_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("red_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.RED_MUSHROOM_FULL, () -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> BROWN_MUSHROOM_SLAB = MysticalWorld.REGISTRATE.block("brown_mushroom_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.BROWN_MUSHROOM_FULL, () -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> MUSHROOM_STEM_SLAB = MysticalWorld.REGISTRATE.block("mushroom_stem_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.STEM_MUSHROOM_FULL, () -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<SlabBlock> MUSHROOM_INSIDE_SLAB = MysticalWorld.REGISTRATE.block("mushroom_inside_slab", Material.WOOD, SlabBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<SlabBlock> MUD_BLOCK_SLAB = MysticalWorld.REGISTRATE.block("mud_block_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> MUD_BRICK_SLAB = MysticalWorld.REGISTRATE.block("mud_brick_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<SlabBlock> CHARRED_SLAB = MysticalWorld.REGISTRATE.block("charred_slab", Material.WOOD, SlabBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<SlabBlock> TERRACOTTA_BRICK_SLAB = MysticalWorld.REGISTRATE.block("terracotta_brick_slab", Material.STONE, SlabBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<SlabBlock> IRON_BRICK_SLAB = MysticalWorld.REGISTRATE.block("iron_brick_slab", Material.METAL, SlabBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.IRON_BRICK))
      .register();

  public static BlockEntry<SlabBlock> SOFT_STONE_SLAB = MysticalWorld.REGISTRATE.block("soft_stone_slab", Material.STONE, SlabBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.SOFT_STONE))
      .register();

  public static BlockEntry<SlabBlock> BLACKENED_STONE_SLAB = MysticalWorld.REGISTRATE.block("blackened_stone_slab", Material.STONE, SlabBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.BLACKENED_STONE))
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianSlabBlock> SOFT_OBSIDIAN_SLAB = MysticalWorld.REGISTRATE.block("soft_obsidian_slab", Material.STONE, SoftObsidian.SoftObsidianSlabBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static BlockEntry<SlabBlock> SAPPHIRE_SLAB = MysticalWorld.REGISTRATE.block("sapphire_slab", Material.METAL, SlabBlock::new)
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.SAPPHIRE_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> LEAD_SLAB = MysticalWorld.REGISTRATE.block("lead_slab", Material.METAL, SlabBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.LEAD_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> ORICHALCUM_SLAB = MysticalWorld.REGISTRATE.block("orichalcum_slab", Material.METAL, SlabBlock::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.ORICHALCUM_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> SILVER_SLAB = MysticalWorld.REGISTRATE.block("silver_slab", Material.METAL, SlabBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.SILVER_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> TIN_SLAB = MysticalWorld.REGISTRATE.block("tin_slab", Material.METAL, SlabBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_SLAB, null, false)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.TIN_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> PEARL_SLAB = MysticalWorld.REGISTRATE.block("pearl_slab", Material.STONE, SlabBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<SlabBlock> CARAPACE_SLAB = MysticalWorld.REGISTRATE.block("carapace_slab", Material.STONE, SlabBlock::new)
      .properties(CARAPACE_PROPS)
      .item()
      .tag(ItemTags.SLABS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.SLABS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.slab(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_SLAB, null, true)
      )
      .loot((p, t) -> p.add(t, RegistrateBlockLootTables.createSlabItemTable(t)))
      .blockstate(BlockstateGenerator.slab(ModBlocks.CARAPACE_BLOCK))
      .register();


  public static BlockEntry<StairBlock> THATCH_STAIRS = MysticalWorld.REGISTRATE.block("thatch_stairs", Material.WOOD, stairsBlock(ModBlocks.THATCH))
      .properties(THATCH_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.THATCH))
      .register();

  public static BlockEntry<StairBlock> RED_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("red_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.RED_MUSHROOM_BLOCK))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<StairBlock> BROWN_MUSHROOM_STAIRS = MysticalWorld.REGISTRATE.block("brown_mushroom_stairs", Material.WOOD, stairsBlock(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<StairBlock> MUSHROOM_STEM_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_stem_stairs", Material.WOOD, stairsBlock(() -> Blocks.MUSHROOM_STEM))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<StairBlock> MUSHROOM_INSIDE_STAIRS = MysticalWorld.REGISTRATE.block("mushroom_inside_stairs", Material.WOOD, stairsBlock(ModBlocks.MUSHROOM_INSIDE))
      .properties(MUSHROOM_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<StairBlock> MUD_BLOCK_STAIRS = MysticalWorld.REGISTRATE.block("mud_block_stairs", Material.STONE, stairsBlock(ModBlocks.MUD_BLOCK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<StairBlock> MUD_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("mud_brick_stairs", Material.STONE, stairsBlock(ModBlocks.MUD_BRICK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<StairBlock> CHARRED_STAIRS = MysticalWorld.REGISTRATE.block("charred_stairs", Material.WOOD, stairsBlock(ModBlocks.CHARRED_PLANKS))
      .properties(WOOD_PROPS)
      .tag(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE)
      .item()
      .tag(ItemTags.WOODEN_STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<StairBlock> TERRACOTTA_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("terracotta_brick_stairs", Material.STONE, stairsBlock(ModBlocks.TERRACOTTA_BRICK))
      .properties(STONE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<StairBlock> IRON_BRICK_STAIRS = MysticalWorld.REGISTRATE.block("iron_brick_stairs", Material.METAL, stairsBlock(ModBlocks.IRON_BRICK))
      .properties(IRON_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.IRON_BRICK))
      .register();

  public static BlockEntry<StairBlock> SOFT_STONE_STAIRS = MysticalWorld.REGISTRATE.block("soft_stone_stairs", Material.STONE, stairsBlock(ModBlocks.SOFT_STONE))
      .properties(SOFT_STONE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.SOFT_STONE))
      .register();

  public static BlockEntry<StairBlock> BLACKENED_STONE_STAIRS = MysticalWorld.REGISTRATE.block("blackened_stone_stairs", Material.STONE, stairsBlock(ModBlocks.BLACKENED_STONE))
      .properties(BLACKENED_STONE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.BLACKENED_STONE))
      .register();

  public static BlockEntry<StairBlock> SOFT_OBSIDIAN_STAIRS = MysticalWorld.REGISTRATE.block("soft_obsidian_stairs", Material.STONE, stairsBlock(ModBlocks.SOFT_OBSIDIAN))
      .properties(SOFT_OBSIDIAN_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static BlockEntry<StairBlock> SAPPHIRE_STAIRS = MysticalWorld.REGISTRATE.block("sapphire_stairs", Material.METAL, stairsBlock(ModBlocks.SAPPHIRE_BLOCK))
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.SAPPHIRE_BLOCK))
      .register();

  public static BlockEntry<StairBlock> LEAD_STAIRS = MysticalWorld.REGISTRATE.block("lead_stairs", Material.METAL, stairsBlock(ModBlocks.LEAD_BLOCK))
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.LEAD_BLOCK))
      .register();

  public static BlockEntry<StairBlock> ORICHALCUM_STAIRS = MysticalWorld.REGISTRATE.block("orichalcum_stairs", Material.METAL, stairsBlock(ModBlocks.ORICHALCUM_BLOCK))
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.ORICHALCUM_BLOCK))
      .register();

  public static BlockEntry<StairBlock> SILVER_STAIRS = MysticalWorld.REGISTRATE.block("silver_stairs", Material.METAL, stairsBlock(ModBlocks.SILVER_BLOCK))
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.SILVER_BLOCK))
      .register();

  public static BlockEntry<StairBlock> TIN_STAIRS = MysticalWorld.REGISTRATE.block("tin_stairs", Material.METAL, stairsBlock(ModBlocks.TIN_BLOCK))
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_STAIRS, null, false)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.TIN_BLOCK))
      .register();

  public static BlockEntry<StairBlock> PEARL_STAIRS = MysticalWorld.REGISTRATE.block("pearl_stairs", Material.STONE, stairsBlock(ModBlocks.PEARL_BLOCK))
      .properties(PEARL_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<StairBlock> CARAPACE_STAIRS = MysticalWorld.REGISTRATE.block("carapace_stairs", Material.STONE, stairsBlock(ModBlocks.CARAPACE_BLOCK))
      .properties(CARAPACE_PROPS)
      .tag(BlockTags.STAIRS, BlockTags.MINEABLE_WITH_PICKAXE)
      .item()
      .tag(ItemTags.STAIRS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.stairs(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_STAIRS, null, true)
      )
      .blockstate(BlockstateGenerator.stairs(ModBlocks.CARAPACE_BLOCK))
      .register();

  // FENCES HERE
  public static BlockEntry<FenceBlock> THATCH_FENCE = MysticalWorld.REGISTRATE.block("thatch_fence", Material.WOOD, FenceBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.THATCH))
      .register();

  public static BlockEntry<FenceBlock> RED_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("red_mushroom_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<FenceBlock> BROWN_MUSHROOM_FENCE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<FenceBlock> MUSHROOM_STEM_FENCE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<FenceBlock> MUSHROOM_INSIDE_FENCE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence", Material.WOOD, FenceBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<FenceBlock> MUD_BLOCK_FENCE = MysticalWorld.REGISTRATE.block("mud_block_fence", Material.STONE, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<FenceBlock> MUD_BRICK_FENCE = MysticalWorld.REGISTRATE.block("mud_brick_fence", Material.STONE, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<FenceBlock> CHARRED_FENCE = MysticalWorld.REGISTRATE.block("charred_fence", Material.WOOD, FenceBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WOODEN_FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<FenceBlock> TERRACOTTA_BRICK_FENCE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence", Material.STONE, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<FenceBlock> PEARL_FENCE = MysticalWorld.REGISTRATE.block("pearl_fence", Material.STONE, FenceBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) -> {
            p.fence(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_FENCE, null);
            p.stonecutting(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_FENCE, 2);
          }
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<FenceBlock> CARAPACE_FENCE = MysticalWorld.REGISTRATE.block("carapace_fence", Material.STONE, FenceBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.FENCES)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.FENCES, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.fence(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_FENCE, null)
      )
      .blockstate(BlockstateGenerator.fence(ModBlocks.CARAPACE_BLOCK))
      .register();

  public static BlockEntry<WallBlock> THATCH_WALL = MysticalWorld.REGISTRATE.block("thatch_wall", Material.WOOD, WallBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.THATCH))
      .register();

  public static BlockEntry<WallBlock> RED_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("red_mushroom_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_WALL)
      )
      .blockstate(BlockstateGenerator.wall(() -> Blocks.RED_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<WallBlock> BROWN_MUSHROOM_WALL = MysticalWorld.REGISTRATE.block("brown_mushroom_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_WALL)
      )
      .blockstate(BlockstateGenerator.wall(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .register();

  public static BlockEntry<WallBlock> MUSHROOM_STEM_WALL = MysticalWorld.REGISTRATE.block("mushroom_stem_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_WALL)
      )
      .blockstate(BlockstateGenerator.wall(() -> Blocks.MUSHROOM_STEM))
      .register();

  public static BlockEntry<WallBlock> MUSHROOM_INSIDE_WALL = MysticalWorld.REGISTRATE.block("mushroom_inside_wall", Material.WOOD, WallBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.MUSHROOM_INSIDE))
      .register();

  public static BlockEntry<WallBlock> MUD_BLOCK_WALL = MysticalWorld.REGISTRATE.block("mud_block_wall", Material.STONE, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.MUD_BLOCK))
      .register();

  public static BlockEntry<WallBlock> MUD_BRICK_WALL = MysticalWorld.REGISTRATE.block("mud_brick_wall", Material.STONE, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.MUD_BRICK))
      .register();

  public static BlockEntry<WallBlock> CHARRED_WALL = MysticalWorld.REGISTRATE.block("charred_wall", Material.WOOD, WallBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.CHARRED_PLANKS))
      .register();

  public static BlockEntry<WallBlock> TERRACOTTA_BRICK_WALL = MysticalWorld.REGISTRATE.block("terracotta_brick_wall", Material.STONE, WallBlock::new)
      .properties(STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.TERRACOTTA_BRICK))
      .register();

  public static BlockEntry<WallBlock> IRON_BRICK_WALL = MysticalWorld.REGISTRATE.block("iron_brick_wall", Material.METAL, WallBlock::new)
      .properties(IRON_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.IRON_BRICK))
      .register();

  public static BlockEntry<WallBlock> SOFT_STONE_WALL = MysticalWorld.REGISTRATE.block("soft_stone_wall", Material.STONE, WallBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.SOFT_STONE))
      .register();

  public static BlockEntry<WallBlock> BLACKENED_STONE_WALL = MysticalWorld.REGISTRATE.block("blackened_stone_wall", Material.STONE, WallBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.BLACKENED_STONE))
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianWallBlock> SOFT_OBSIDIAN_WALL = MysticalWorld.REGISTRATE.block("soft_obsidian_wall", Material.STONE, SoftObsidian.SoftObsidianWallBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.SOFT_OBSIDIAN))
      .register();

  public static BlockEntry<WallBlock> SAPPHIRE_WALL = MysticalWorld.REGISTRATE.block("sapphire_wall", Material.METAL, WallBlock::new)
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.SAPPHIRE_BLOCK))
      .register();

  public static BlockEntry<WallBlock> LEAD_WALL = MysticalWorld.REGISTRATE.block("lead_wall", Material.METAL, WallBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.LEAD_BLOCK))
      .register();

  public static BlockEntry<WallBlock> ORICHALCUM_WALL = MysticalWorld.REGISTRATE.block("orichalcum_wall", Material.METAL, WallBlock::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.ORICHALCUM_BLOCK))
      .register();

  public static BlockEntry<WallBlock> SILVER_WALL = MysticalWorld.REGISTRATE.block("silver_wall", Material.METAL, WallBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.SILVER_BLOCK))
      .register();

  public static BlockEntry<WallBlock> TIN_WALL = MysticalWorld.REGISTRATE.block("tin_wall", Material.METAL, WallBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.TIN_BLOCK))
      .register();

  public static BlockEntry<WallBlock> PEARL_WALL = MysticalWorld.REGISTRATE.block("pearl_wall", Material.STONE, WallBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.PEARL_BLOCK))
      .register();

  public static BlockEntry<WallBlock> CARAPACE_WALL = MysticalWorld.REGISTRATE.block("carapace_wall", Material.STONE, WallBlock::new)
      .properties(CARAPACE_PROPS)
      .item()
      .tag(ItemTags.WALLS)
      .model(ItemModelGenerator::inventoryModel)
      .build()
      .tag(BlockTags.WALLS, BlockTags.MINEABLE_WITH_PICKAXE)
      .recipe((ctx, p) ->
          p.wall(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_WALL)
      )
      .blockstate(BlockstateGenerator.wall(ModBlocks.CARAPACE_BLOCK))
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> THATCH_BUTTON = MysticalWorld.REGISTRATE.block("thatch_button", Material.WOOD, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.THATCH))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.WoodButtonBlock> RED_MUSHROOM_BUTTON = MysticalWorld.REGISTRATE.block("red_mushroom_button", Material.WOOD, BaseBlocks.WoodButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.WOODEN_BUTTONS)
      .build()
      .tag(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(() -> Blocks.RED_MUSHROOM_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.WoodButtonBlock> BROWN_MUSHROOM_BUTTON = MysticalWorld.REGISTRATE.block("brown_mushroom_button", Material.WOOD, BaseBlocks.WoodButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.WOODEN_BUTTONS)
      .build()
      .tag(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.WoodButtonBlock> MUSHROOM_STEM_BUTTON = MysticalWorld.REGISTRATE.block("mushroom_stem_button", Material.WOOD, BaseBlocks.WoodButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.WOODEN_BUTTONS)
      .build()
      .tag(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(() -> Blocks.MUSHROOM_STEM))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.WoodButtonBlock> MUSHROOM_INSIDE_BUTTON = MysticalWorld.REGISTRATE.block("mushroom_inside_button", Material.WOOD, BaseBlocks.WoodButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.WOODEN_BUTTONS)
      .build()
      .tag(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.MUSHROOM_INSIDE))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> MUD_BLOCK_BUTTON = MysticalWorld.REGISTRATE.block("mud_block_button", Material.STONE, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.MUD_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> MUD_BRICK_BUTTON = MysticalWorld.REGISTRATE.block("mud_brick_button", Material.STONE, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.MUD_BRICK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.WoodButtonBlock> CHARRED_BUTTON = MysticalWorld.REGISTRATE.block("charred_button", Material.WOOD, BaseBlocks.WoodButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.WOODEN_BUTTONS)
      .build()
      .tag(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.CHARRED_PLANKS))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_BUTTON, 1, 1);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> TERRACOTTA_BRICK_BUTTON = MysticalWorld.REGISTRATE.block("terracotta_brick_button", Material.STONE, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.TERRACOTTA_BRICK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> IRON_BRICK_BUTTON = MysticalWorld.REGISTRATE.block("iron_brick_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.IRON_BRICK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> SOFT_STONE_BUTTON = MysticalWorld.REGISTRATE.block("soft_stone_button", Material.STONE, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.SOFT_STONE))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> BLACKENED_STONE_BUTTON = MysticalWorld.REGISTRATE.block("blackened_stone_button", Material.STONE, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.BLACKENED_STONE))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_BUTTON);
      })
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianButtonBlock> SOFT_OBSIDIAN_BUTTON = MysticalWorld.REGISTRATE.block("soft_obsidian_button", Material.STONE, SoftObsidian.SoftObsidianButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.SOFT_OBSIDIAN))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> SAPPHIRE_BUTTON = MysticalWorld.REGISTRATE.block("sapphire_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.SAPPHIRE_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> COPPER_BUTTON = MysticalWorld.REGISTRATE.block("copper_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(() -> Blocks.COPPER_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(Blocks.COPPER_BLOCK), ModBlocks.COPPER_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(Blocks.COPPER_BLOCK), ModBlocks.COPPER_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> LEAD_BUTTON = MysticalWorld.REGISTRATE.block("lead_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.LEAD_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> ORICHALCUM_BUTTON = MysticalWorld.REGISTRATE.block("orichalcum_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.ORICHALCUM_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> SILVER_BUTTON = MysticalWorld.REGISTRATE.block("silver_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.SILVER_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> TIN_BUTTON = MysticalWorld.REGISTRATE.block("tin_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.TIN_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> PEARL_BUTTON = MysticalWorld.REGISTRATE.block("pearl_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.PEARL_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.StoneButtonBlock> CARAPACE_BUTTON = MysticalWorld.REGISTRATE.block("carapace_button", Material.METAL, BaseBlocks.StoneButtonBlock::new)
      .properties(o -> BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.METAL))
      .item()
      .model(ItemModelGenerator::inventoryModel)
      .tag(ItemTags.BUTTONS)
      .build()
      .tag(BlockTags.BUTTONS, BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate(BlockstateGenerator.button(ModBlocks.CARAPACE_BLOCK))
      .recipe((ctx, p) -> {
        p.singleItem(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_BUTTON, 1, 1);
        p.stonecutting(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_BUTTON);
      })
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> THATCH_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("thatch_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.THATCH))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.THATCH))
            .unlockedBy("has_thatch_block", DataIngredient.items(ModBlocks.THATCH).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> RED_MUSHROOM_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("red_mushroom_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).noCollission().strength(0.5f).sound(SoundType.WOOD))
      .blockstate(BlockstateGenerator.pressurePlate(() -> Blocks.RED_MUSHROOM_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK))
            .unlockedBy("has_red_mushroom_block", DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> BROWN_MUSHROOM_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("brown_mushroom_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).noCollission().strength(0.5f).sound(SoundType.WOOD))
      .blockstate(BlockstateGenerator.pressurePlate(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK))
            .unlockedBy("has_brown_mushroom_block", DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> MUSHROOM_STEM_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("mushroom_stem_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).noCollission().strength(0.5f).sound(SoundType.WOOD))
      .blockstate(BlockstateGenerator.pressurePlate(() -> Blocks.MUSHROOM_STEM))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(Blocks.MUSHROOM_STEM))
            .unlockedBy("has_mushroom_sem_block", DataIngredient.items(Blocks.MUSHROOM_STEM).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> MUSHROOM_INSIDE_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("mushroom_inside_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).noCollission().strength(0.5f).sound(SoundType.WOOD))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.MUSHROOM_INSIDE))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.MUSHROOM_INSIDE))
            .unlockedBy("has_mushroom_inside_block", DataIngredient.items(ModBlocks.MUSHROOM_INSIDE).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> MUD_BLOCK_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("mud_block_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.MUD_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.MUD_BLOCK))
            .unlockedBy("has_mud_block", DataIngredient.items(ModBlocks.MUD_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> MUD_BRICK_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("mud_brick_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.MUD_BRICK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.MUD_BRICK))
            .unlockedBy("has_mud_brick", DataIngredient.items(ModBlocks.MUD_BRICK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> CHARRED_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("charred_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).noCollission().strength(0.5f).sound(SoundType.WOOD))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.CHARRED_PLANKS))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.CHARRED_PLANKS))
            .unlockedBy("has_charred_block", DataIngredient.items(ModBlocks.CHARRED_PLANKS).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> TERRACOTTA_BRICK_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("terracotta_brick_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.TERRACOTTA_BRICK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.TERRACOTTA_BRICK))
            .unlockedBy("has_terracotta_brick", DataIngredient.items(ModBlocks.TERRACOTTA_BRICK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> IRON_BRICK_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("iron_brick_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.IRON_BRICK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.IRON_BRICK))
            .unlockedBy("has_iron_brick_block", DataIngredient.items(ModBlocks.IRON_BRICK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.IRON_BRICK), ModBlocks.IRON_BRICK_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> SOFT_STONE_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("soft_stone_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0.5f).sound(SoundType.STONE))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.SOFT_STONE))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.SOFT_STONE))
            .unlockedBy("has_soft_stone", DataIngredient.items(ModBlocks.SOFT_STONE).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.SOFT_STONE), ModBlocks.SOFT_STONE_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> BLACKENED_STONE_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("blackened_stone_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0.5f).sound(SoundType.STONE))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.BLACKENED_STONE))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.BLACKENED_STONE))
            .unlockedBy("has_blackened_stone", DataIngredient.items(ModBlocks.BLACKENED_STONE).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.BLACKENED_STONE), ModBlocks.BLACKENED_STONE_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianPressurePlateBlock> SOFT_OBSIDIAN_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("soft_obsidian_pressure_plate", (p) -> new SoftObsidian.SoftObsidianPressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0.5f).sound(SoundType.STONE))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.SOFT_OBSIDIAN))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.SOFT_OBSIDIAN))
            .unlockedBy("has_soft_obsidian", DataIngredient.items(ModBlocks.SOFT_OBSIDIAN).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.SOFT_OBSIDIAN), ModBlocks.SOFT_OBSIDIAN_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> SAPPHIRE_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("sapphire_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.SAPPHIRE_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK))
            .unlockedBy("has_sapphire_block", DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.SAPPHIRE_BLOCK), ModBlocks.SAPPHIRE_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> COPPER_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("copper_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(() -> Blocks.COPPER_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(Blocks.COPPER_BLOCK))
            .unlockedBy("has_copper_block", DataIngredient.items(Blocks.COPPER_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(Blocks.COPPER_BLOCK), ModBlocks.COPPER_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> LEAD_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("lead_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.LEAD_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.LEAD_BLOCK))
            .unlockedBy("has_lead_block", DataIngredient.items(ModBlocks.LEAD_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.LEAD_BLOCK), ModBlocks.LEAD_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> ORICHALCUM_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("orichalcum_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.ORICHALCUM_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK))
            .unlockedBy("has_orichalcum_block", DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.ORICHALCUM_BLOCK), ModBlocks.ORICHALCUM_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> SILVER_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("silver_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.SILVER_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.SILVER_BLOCK))
            .unlockedBy("has_silver_block", DataIngredient.items(ModBlocks.SILVER_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.SILVER_BLOCK), ModBlocks.SILVER_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> TIN_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("tin_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.METAL))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.TIN_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.TIN_BLOCK))
            .unlockedBy("has_tin_block", DataIngredient.items(ModBlocks.TIN_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.TIN_BLOCK), ModBlocks.TIN_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> PEARL_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("pearl_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.STONE))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.PEARL_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.PEARL_BLOCK))
            .unlockedBy("has_tin_block", DataIngredient.items(ModBlocks.PEARL_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.PEARL_BLOCK), ModBlocks.PEARL_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.PressurePlateBlock> CARAPACE_PRESSURE_PLATE = MysticalWorld.REGISTRATE.block("carapace_pressure_plate", (p) -> new BaseBlocks.PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, p))
      .properties(o -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).noCollission().strength(0.5f).sound(SoundType.STONE))
      .blockstate(BlockstateGenerator.pressurePlate(ModBlocks.CARAPACE_BLOCK))
      .recipe((ctx, p) -> {
        ShapedRecipeBuilder.shaped(ctx.getEntry(), 1)
            .pattern("XX")
            .define('X', DataIngredient.items(ModBlocks.CARAPACE_BLOCK))
            .unlockedBy("has_tin_block", DataIngredient.items(ModBlocks.CARAPACE_BLOCK).getCritereon(p))
            .save(p, p.safeId(ctx.getEntry()));
        p.stonecutting(DataIngredient.items(ModBlocks.CARAPACE_BLOCK), ModBlocks.CARAPACE_PRESSURE_PLATE);
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(BlockTags.STONE_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<FenceGateBlock> THATCH_FENCE_GATE = MysticalWorld.REGISTRATE.block("thatch_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.THATCH), ModBlocks.THATCH_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.THATCH))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> RED_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("red_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.RED_MUSHROOM_BLOCK), ModBlocks.RED_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(() -> Blocks.RED_MUSHROOM_BLOCK))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> BROWN_MUSHROOM_FENCE_GATE = MysticalWorld.REGISTRATE.block("brown_mushroom_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.BROWN_MUSHROOM_BLOCK), ModBlocks.BROWN_MUSHROOM_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> MUSHROOM_STEM_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_stem_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(Blocks.MUSHROOM_STEM), ModBlocks.MUSHROOM_STEM_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(() -> Blocks.MUSHROOM_STEM))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> MUSHROOM_INSIDE_FENCE_GATE = MysticalWorld.REGISTRATE.block("mushroom_inside_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUSHROOM_INSIDE), ModBlocks.MUSHROOM_INSIDE_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.MUSHROOM_INSIDE))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> MUD_BLOCK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_block_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BLOCK), ModBlocks.MUD_BLOCK_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.MUD_BLOCK))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<FenceGateBlock> MUD_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("mud_brick_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.MUD_BRICK), ModBlocks.MUD_BRICK_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.MUD_BRICK))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<FenceGateBlock> CHARRED_FENCE_GATE = MysticalWorld.REGISTRATE.block("charred_fence_gate", Material.WOOD, FenceGateBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.CHARRED_PLANKS), ModBlocks.CHARRED_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.CHARRED_PLANKS))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<FenceGateBlock> TERRACOTTA_BRICK_FENCE_GATE = MysticalWorld.REGISTRATE.block("terracotta_brick_fence_gate", Material.STONE, FenceGateBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          p.fenceGate(DataIngredient.items(ModBlocks.TERRACOTTA_BRICK), ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, null)
      )
      .blockstate(BlockstateGenerator.gate(ModBlocks.TERRACOTTA_BRICK))
      .tag(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_PICKAXE)
      .register();


  public static BlockEntry<BaseBlocks.WidePostBlock> THATCH_WIDE_POST = MysticalWorld.REGISTRATE.block("thatch_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.THATCH, ModBlocks.THATCH_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.THATCH))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> RED_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("red_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.RED_MUSHROOM_BLOCK, ModBlocks.RED_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(() -> Blocks.RED_MUSHROOM_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> BROWN_MUSHROOM_WIDE_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK, ModBlocks.BROWN_MUSHROOM_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(() -> Blocks.BROWN_MUSHROOM_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUSHROOM_STEM_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.MUSHROOM_STEM, ModBlocks.MUSHROOM_STEM_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(() -> Blocks.MUSHROOM_STEM))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUSHROOM_INSIDE_WIDE_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUSHROOM_INSIDE, ModBlocks.MUSHROOM_INSIDE_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.MUSHROOM_INSIDE))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUD_BLOCK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_block_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.MUD_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> MUD_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("mud_brick_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.MUD_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> CHARRED_WIDE_POST = MysticalWorld.REGISTRATE.block("charred_wide_post", Material.WOOD, BaseBlocks.WidePostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.CHARRED_PLANKS))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> TERRACOTTA_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.TERRACOTTA_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> IRON_BRICK_WIDE_POST = MysticalWorld.REGISTRATE.block("iron_brick_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.IRON_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> SOFT_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_stone_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.SOFT_STONE))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> BLACKENED_STONE_WIDE_POST = MysticalWorld.REGISTRATE.block("blackened_stone_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.BLACKENED_STONE, ModBlocks.BLACKENED_STONE_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.BLACKENED_STONE))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianWidePostBlock> SOFT_OBSIDIAN_WIDE_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_wide_post", Material.STONE, SoftObsidian.SoftObsidianWidePostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.SOFT_OBSIDIAN))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> SAPPHIRE_WIDE_POST = MysticalWorld.REGISTRATE.block("sapphire_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SAPPHIRE_BLOCK, ModBlocks.SAPPHIRE_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.SAPPHIRE_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> COPPER_WIDE_POST = MysticalWorld.REGISTRATE.block("copper_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(() -> Blocks.COPPER_BLOCK, ModBlocks.COPPER_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(() -> Blocks.COPPER_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> LEAD_WIDE_POST = MysticalWorld.REGISTRATE.block("lead_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.LEAD_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> ORICHALCUM_WIDE_POST = MysticalWorld.REGISTRATE.block("orichalcum_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.ORICHALCUM_BLOCK, ModBlocks.ORICHALCUM_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.ORICHALCUM_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> SILVER_WIDE_POST = MysticalWorld.REGISTRATE.block("silver_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.SILVER_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> TIN_WIDE_POST = MysticalWorld.REGISTRATE.block("tin_wide_post", Material.METAL, BaseBlocks.WidePostBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_WIDE_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.TIN_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> PEARL_WIDE_POST = MysticalWorld.REGISTRATE.block("pearl_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.PEARL_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.WidePostBlock> CARAPACE_WIDE_POST = MysticalWorld.REGISTRATE.block("carapace_wide_post", Material.STONE, BaseBlocks.WidePostBlock::new)
      .properties(CARAPACE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.widePost(ModBlocks.CARAPACE_BLOCK, ModBlocks.CARAPACE_WIDE_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.widePost(ModBlocks.CARAPACE_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> THATCH_SMALL_POST = MysticalWorld.REGISTRATE.block("thatch_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(THATCH_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.THATCH, ModBlocks.THATCH_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.THATCH))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> RED_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("red_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.RED_MUSHROOM_BLOCK, ModBlocks.RED_MUSHROOM_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(() -> Blocks.RED_MUSHROOM_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> BROWN_MUSHROOM_SMALL_POST = MysticalWorld.REGISTRATE.block("brown_mushroom_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.BROWN_MUSHROOM_BLOCK, ModBlocks.BROWN_MUSHROOM_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(() -> Blocks.BROWN_MUSHROOM_BLOCK))

      .tag(BlockTags.MINEABLE_WITH_AXE)

      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_STEM_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_stem_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.MUSHROOM_STEM, ModBlocks.MUSHROOM_STEM_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(() -> Blocks.MUSHROOM_STEM))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUSHROOM_INSIDE_SMALL_POST = MysticalWorld.REGISTRATE.block("mushroom_inside_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(MUSHROOM_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUSHROOM_INSIDE, ModBlocks.MUSHROOM_INSIDE_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.MUSHROOM_INSIDE))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUD_BLOCK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_block_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.MUD_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> MUD_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("mud_brick_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.MUD_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> CHARRED_SMALL_POST = MysticalWorld.REGISTRATE.block("charred_small_post", Material.WOOD, BaseBlocks.NarrowPostBlock::new)
      .properties(WOOD_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.CHARRED_PLANKS))
      .tag(BlockTags.MINEABLE_WITH_AXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> TERRACOTTA_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("terracotta_brick_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.TERRACOTTA_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> IRON_BRICK_SMALL_POST = MysticalWorld.REGISTRATE.block("iron_brick_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(IRON_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.IRON_BRICK, ModBlocks.IRON_BRICK_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.IRON_BRICK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> SOFT_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_stone_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SOFT_STONE, ModBlocks.SOFT_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.SOFT_STONE))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> BLACKENED_STONE_SMALL_POST = MysticalWorld.REGISTRATE.block("blackened_stone_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.BLACKENED_STONE, ModBlocks.BLACKENED_STONE_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.BLACKENED_STONE))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<SoftObsidian.SoftObsidianNarrowPostBlock> SOFT_OBSIDIAN_SMALL_POST = MysticalWorld.REGISTRATE.block("soft_obsidian_small_post", Material.STONE, SoftObsidian.SoftObsidianNarrowPostBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SOFT_OBSIDIAN, ModBlocks.SOFT_OBSIDIAN_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.SOFT_OBSIDIAN))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> SAPPHIRE_SMALL_POST = MysticalWorld.REGISTRATE.block("sapphire_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.SAPPHIRE.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SAPPHIRE_BLOCK, ModBlocks.SAPPHIRE_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.SAPPHIRE_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> COPPER_SMALL_POST = MysticalWorld.REGISTRATE.block("copper_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.COPPER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(() -> Blocks.COPPER_BLOCK, ModBlocks.COPPER_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(() -> Blocks.COPPER_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> LEAD_SMALL_POST = MysticalWorld.REGISTRATE.block("lead_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.LEAD.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.LEAD_BLOCK, ModBlocks.LEAD_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.LEAD_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> ORICHALCUM_SMALL_POST = MysticalWorld.REGISTRATE.block("orichalcum_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.ORICHALCUM_BLOCK, ModBlocks.ORICHALCUM_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.ORICHALCUM_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> SILVER_SMALL_POST = MysticalWorld.REGISTRATE.block("silver_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.SILVER.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.SILVER_BLOCK, ModBlocks.SILVER_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.SILVER_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> TIN_SMALL_POST = MysticalWorld.REGISTRATE.block("tin_small_post", Material.METAL, BaseBlocks.NarrowPostBlock::new)
      .properties(o -> {
        ModMaterials.TIN.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.TIN_BLOCK, ModBlocks.TIN_SMALL_POST, null, false, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.TIN_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BaseBlocks.NarrowPostBlock> PEARL_SMALL_POST = MysticalWorld.REGISTRATE.block("pearl_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.PEARL_BLOCK, ModBlocks.PEARL_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.PEARL_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();


  public static BlockEntry<BaseBlocks.NarrowPostBlock> CARAPACE_SMALL_POST = MysticalWorld.REGISTRATE.block("carapace_small_post", Material.STONE, BaseBlocks.NarrowPostBlock::new)
      .properties(CARAPACE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .recipe((ctx, p) ->
          MysticalWorld.RECIPES.narrowPost(ModBlocks.CARAPACE_BLOCK, ModBlocks.CARAPACE_SMALL_POST, null, true, p)
      )
      .blockstate(BlockstateGenerator.narrowPost(ModBlocks.CARAPACE_BLOCK))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  protected static NonNullUnaryOperator<BlockBehaviour.Properties> BONE_PROPS = (o) -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.2F).sound(SoundType.BONE_BLOCK);

  public static BlockEntry<BonesBlock> BONE_PILE_1 = MysticalWorld.REGISTRATE.block("bone_pile_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
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
            .unlockedBy("has_bones", RegistrateRecipeProvider.has(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_4.get()).unlockedBy("has_bone_pile_4", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_4.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "bone_pile_1_from_bone_pile_4"));
      })
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_2 = MysticalWorld.REGISTRATE.block("bone_pile_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_1.get()).unlockedBy("has_bone_pile_1", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_1.get())).save(p))
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_3 = MysticalWorld.REGISTRATE.block("bone_pile_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_2.get()).unlockedBy("has_bone_pile_2", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_2.get())).save(p))
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_4 = MysticalWorld.REGISTRATE.block("bone_pile_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) ->
          p.getVariantBuilder(ctx.getEntry()).forAllStates((state) ->
              ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()
          )
      )
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_3.get()).unlockedBy("has_bone_pile_3", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_3.get())).save(p))
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_1 = MysticalWorld.REGISTRATE.block("skeleton_bottom_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
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
            .pattern("BB ")
            .pattern("BBB")
            .define('B', Tags.Items.BONES)
            .unlockedBy("has_bones", RegistrateRecipeProvider.has(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_3.get()).unlockedBy("has_skeleton_bottom_3", RegistrateRecipeProvider.has(ModBlocks.SKELETON_BOTTOM_3.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "skeleton_bottom_1_from_skeleton_bottom_4"));
      })
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_2 = MysticalWorld.REGISTRATE.block("skeleton_bottom_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
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
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_1.get()).unlockedBy("has_skeleton_bottom_1", RegistrateRecipeProvider.has(ModBlocks.SKELETON_BOTTOM_1.get())).save(p)
      )
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_3 = MysticalWorld.REGISTRATE.block("skeleton_bottom_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
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
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_2.get()).unlockedBy("has_skeleton_bottom_2", RegistrateRecipeProvider.has(ModBlocks.SKELETON_BOTTOM_2.get())).save(p)
      )
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_1 = MysticalWorld.REGISTRATE.block("skeleton_top_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
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
            .pattern("BBB")
            .pattern(" BB")
            .pattern("BBB")
            .define('B', Tags.Items.BONES)
            .unlockedBy("has_bones", RegistrateRecipeProvider.has(Tags.Items.BONES))
            .save(p);
        ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_4.get()).unlockedBy("has_skeleton_top_4", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_4.get())).save(p, new ResourceLocation(MysticalWorld.MODID, "skeleton_top_1_from_skeleton_top_4"));
      })
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_2 = MysticalWorld.REGISTRATE.block("skeleton_top_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
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
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_1.get()).unlockedBy("has_skeleton_top_1", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_1.get())).save(p)
      )
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_3 = MysticalWorld.REGISTRATE.block("skeleton_top_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
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
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_2.get()).unlockedBy("has_skeleton_top_2", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_2.get())).save(p)
      )
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_4 = MysticalWorld.REGISTRATE.block("skeleton_top_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
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
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_3.get()).unlockedBy("has_skeleton_top_3", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_3.get())).save(p)
      )
      .loot(boneLoot())
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .register();

  public static BlockEntry<PetrifiedFlowerBlock> STONEPETAL = MysticalWorld.REGISTRATE.block("stonepetal", Material.PLANT, PetrifiedFlowerBlock::new)
      .properties(o -> o.noCollission().instabreak().sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .item()
      .model(ItemModelGenerator::generated)
      .tag(MWTags.Items.STONEPETAL)
      .build()
      .tag(MWTags.Blocks.STONEPETAL, BlockTags.MINEABLE_WITH_HOE)
      .recipe((ctx, p) -> {
        DataIngredient a = DataIngredient.items(ModBlocks.STONEPETAL.get());
        ShapelessRecipeBuilder.shapeless(Items.GRAY_DYE, 4).requires(ctx.getEntry()).unlockedBy("has_stonepetal", a.getCritereon(p)).save(p, new ResourceLocation(MysticalWorld.MODID, "gray_dye_from_stonepetal"));
      })
      .register();


  public static void load() {
  }
}
