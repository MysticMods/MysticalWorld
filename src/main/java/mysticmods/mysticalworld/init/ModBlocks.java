/*
package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import mysticmods.mysticalworld.MWTags;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.blocks.*;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.IForgeRegistryEntry;
import noobanidus.libs.noobutil.block.BaseBlocks;
import noobanidus.libs.noobutil.data.generator.BlockGenerator;
import noobanidus.libs.noobutil.data.generator.BlockstateGenerator;
import noobanidus.libs.noobutil.data.generator.ItemModelGenerator;
import noobanidus.libs.noobutil.ingredient.ExcludingIngredient;
import noobanidus.libs.noobutil.loot.condition.LootConditions;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ModBlocks {

  private static <T extends IForgeRegistryEntry<?>> String boneName(T block) {
    String[] init = Objects.requireNonNull(block.getRegistryName()).getPath().split("_");
    return init[0] + "_" + init[1] + init[2];
  }

  public static <T extends Item> void boneModel(ItemModelProvider p, T item) {
    p.withExistingParent(name(item), new ResourceLocation(MysticalWorld.MODID, "block/" + boneName(item)));
  }

  private static <T extends IForgeRegistryEntry<?>> String name(T block) {
    return Objects.requireNonNull(block.getRegistryName()).getPath();
  }

  public static <T extends Block> void boneLoot(LootTableProvider p, T block) {
    p.add(LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block).when(LootConditions.HAS_SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))));
  }


  public static BlockEntry<UncannyGravelBlock> UNCANNY_GRAVEL = MysticalWorld.REGISTRATE.block("uncanny_gravel", Material.SAND, UncannyGravelBlock::new)
      .properties(o -> o.strength(0.6f).sound(SoundType.GRAVEL))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.UNCANNY_GRAVEL))
      .tag(Tags.Items.GRAVEL)
      .build()
      .tag(Tags.Blocks.GRAVEL)
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 8).pattern("GGG").pattern("GPG").pattern("GGG").define('G', Tags.Items.GRAVEL).define('P', Tags.Items.DYES_CYAN).unlockedBy("has_gravel", RegistrateRecipeProvider.has(Tags.Items.GRAVEL)).unlockedBy("has_purple_dye", RegistrateRecipeProvider.has(Tags.Items.DYES_CYAN)).save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_gravel"))
      )
      .defaultBlockstate()
      .defaultLoot()
      .defaultLang()
      .register();


  public static BlockEntry<SandBlock> UNCANNY_SAND = MysticalWorld.REGISTRATE.block("uncanny_sand", Material.SAND, (b) -> new SandBlock(0x6c36e0, b))
      .properties(o -> o.strength(0.5f).sound(SoundType.GRAVEL))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.UNCANNY_SAND))
      .tag(ItemTags.SAND)
      .build()
      .tag(BlockTags.SAND)
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 8).pattern("GGG").pattern("GPG").pattern("GGG").define('G', Tags.Items.SAND).define('P', Tags.Items.DYES_PURPLE).unlockedBy("has_sand", RegistrateRecipeProvider.has(Tags.Items.SAND)).unlockedBy("has_purple_dye", RegistrateRecipeProvider.has(Tags.Items.DYES_PURPLE)).save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_sand")))
      .register();

  //        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.UNCANNY_SAND.get()), Items.PURPLE_STAINED_GLASS, 0, 200);

  public static BlockEntry<PetrifiedFlowerBlock> STONEPETAL = MysticalWorld.REGISTRATE.block("stonepetal", Material.PLANT, PetrifiedFlowerBlock::new)
      .properties(o -> o.noCollission().instabreak().sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .item()
      .model(ItemModelGenerator::generated)
      .tag(ItemTags.FLOWERS)
      .build()
      .tag(BlockTags.FLOWERS)
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(Items.GRAY_DYE, 4).requires(ctx.getEntry()).unlockedBy("has_stonepetal", DataIngredient.items(ModBlocks.STONEPETAL.get()).getCritereon(p)).save(p, new ResourceLocation(MysticalWorld.MODID, "gray_dye_from_stonepetal")))
      .register();

  public static BlockEntry<AnywhereMushroomBlock> ANYWHERE_RED_MUSHROOM = MysticalWorld.REGISTRATE.block("red_mushroom", Material.PLANT, AnywhereMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(Blocks.RED_MUSHROOM)))))
      .loot((ctx, p) -> ctx.dropOther(p, Blocks.RED_MUSHROOM))
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .register();


  public static BlockEntry<AnywhereMushroomBlock> ANYWHERE_BROWN_MUSHROOM = MysticalWorld.REGISTRATE.block("brown_mushroom", Material.PLANT, AnywhereMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 1).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(Blocks.BROWN_MUSHROOM)))))
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .loot((ctx, p) -> ctx.dropOther(p, Blocks.BROWN_MUSHROOM))
      .register();


  public static BlockEntry<UncannyMushroomBlock> UNCANNY_MUSHROOM = MysticalWorld.REGISTRATE.block("uncanny_mushroom", Material.PLANT, UncannyMushroomBlock::new)
      .properties(o -> o.noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 9).hasPostProcess((a, b, c) -> true))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().setModels(new ConfiguredModel(p.models().cross(ctx.getName(), p.blockTexture(ModBlocks.UNCANNY_MUSHROOM.get())))))
      .item()
      .model(ItemModelGenerator::generated)
      .tag(Tags.Items.MUSHROOMS)
      .build()
      .tag(BlockTags.ENDERMAN_HOLDABLE)
      .recipe((ctx, p) -> {
        DataIngredient a = DataIngredient.items(ModBlocks.UNCANNY_MUSHROOM.get());
        ShapelessRecipeBuilder.shapeless(Items.PURPLE_DYE, 4).requires(ctx.getEntry()).unlockedBy("has_uncanny_mushroom", a.getCritereon(p)).save(p, new ResourceLocation(MysticalWorld.MODID, "purple_dye_from_uncanny_mushroom"));
      })
      .register();


  public static BlockEntry<FlowerPotBlock> POTTED_STONEPETAL = MysticalWorld.REGISTRATE.block("potted_stonepetal", Material.DECORATION, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.STONEPETAL, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/stonepetal")))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.createPotFlowerItemTable(ModBlocks.STONEPETAL.get())))
      .register();

  public static BlockEntry<FlowerPotBlock> POTTED_UNCANNY_MUSHROOM = MysticalWorld.REGISTRATE.block("potted_uncanny_mushroom", Material.DECORATION, (p) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.UNCANNY_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().withExistingParent(ctx.getName(), "minecraft:block/flower_pot_cross").texture("plant", "mysticalworld:block/uncanny_mushroom")))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.createPotFlowerItemTable(ModBlocks.UNCANNY_MUSHROOM.get())))
      .register();


  private static final NonNullUnaryOperator<Block.Properties> THATCH_PROPS = (o) -> o.strength(1f).sound(SoundType.GRASS);
  private static final NonNullUnaryOperator<Block.Properties> MUSHROOM_PROPS = (o) -> o.strength(0.2F).sound(SoundType.WOOD);

  public static BlockEntry<ThatchBlock> THATCH = MysticalWorld.REGISTRATE.block("thatch", Material.WOOD, ThatchBlock::new)
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.THATCH))
      .build()
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ModBlocks.THATCH.get(), 32).pattern("XY").pattern("YX").define('X', Blocks.HAY_BLOCK).define('Y', Tags.Items.CROPS_WHEAT).unlockedBy("has_hay", RegistrateRecipeProvider.has(Blocks.HAY_BLOCK)).unlockedBy("has_wheat", RegistrateRecipeProvider.has(Items.WHEAT)).save(p))
      .register();

  public static BlockEntry<GallAppleCropBlock> GALL_APPLE = MysticalWorld.REGISTRATE.block("gall_apple_crop", GallAppleCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(ModBlocks.GALL_APPLE.get(), RegistrateBlockLootTables.createCropDrops(ModBlocks.GALL_APPLE.get(), Items.AIR, ModItems.GALL_APPLE.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.GALL_APPLE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 3)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static BlockEntry<AubergineCropBlock> AUBERGINE_CROP = MysticalWorld.REGISTRATE.block("aubergine_crop", AubergineCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(ModBlocks.AUBERGINE_CROP.get(), RegistrateBlockLootTables.createCropDrops(ModBlocks.AUBERGINE_CROP.get(), ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.AUBERGINE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))))
      .blockstate(NonNullBiConsumer.noop())
      .register();

  public static BlockEntry<WildCropBlock> WILD_AUBERGINE = MysticalWorld.REGISTRATE.block("wild_aubergine", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.applyExplosionCondition(ModItems.AUBERGINE.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(ModItems.AUBERGINE.get())))).withPool(RegistrateBlockLootTables.applyExplosionCondition(ModItems.AUBERGINE_SEEDS.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(ModItems.AUBERGINE_SEEDS.get()))))))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .register();

  public static BlockEntry<WildCropBlock> WILD_WART = MysticalWorld.REGISTRATE.block("wild_wart", WildCropBlock::new)
      .properties(o -> Block.Properties.of(Material.PLANT).noCollission().strength(0f).sound(SoundType.CROP).randomTicks())
      .loot((p, t) -> p.add(t, LootTable.lootTable().withPool(RegistrateBlockLootTables.applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(Items.NETHER_WART)))).withPool(RegistrateBlockLootTables.applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.NETHER_WART))))))
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).partialState().addModels(new ConfiguredModel(p.models().crop(ctx.getName(), p.blockTexture(ctx.getEntry())))))
      .register();

  public static BlockEntry<HugeMushroomBlock> UNCANNY_MUSHROOM_BLOCK = MysticalWorld.REGISTRATE.block("uncanny_mushroom_block", Material.WOOD, HugeMushroomBlock::new)
      .properties(o -> o.strength(0.2F).sound(SoundType.WOOD).lightLevel(q -> 8))
      .loot((ctx, p) -> ctx.add(p, RegistrateBlockLootTables.createMushroomBlockDrop(p, ModBlocks.UNCANNY_MUSHROOM.get())))
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
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), p.blockTexture(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation(MysticalWorld.MODID, "block/uncanny_mushroom_block")))
      .build()
      .register();


  // MUSHROOM
  public static BlockEntry<Block> RED_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("red_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/red_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<Block> BROWN_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("brown_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/brown_mushroom_block")))
      .build()
      .register();

  public static BlockEntry<Block> STEM_MUSHROOM_FULL = MysticalWorld.REGISTRATE.block("stem_mushroom_full", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem"))))
      .item()
      .model((ctx, p) -> p.cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_stem")))
      .build()
      .recipe((ctx, p) -> {
        ShapelessRecipeBuilder.shapeless(ModBlocks.UNCANNY_MUSHROOM_FULL.get(), 1)
            .requires(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get())
            .unlockedBy("has_uncanny_mushroom_block", RegistrateRecipeProvider.has(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "full_uncanny_mushroom_block_from_uncanny_mushroom"));
        ShapelessRecipeBuilder.shapeless(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get(), 1)
            .requires(ModBlocks.UNCANNY_MUSHROOM_FULL.get())
            .unlockedBy("has_full_red_mushroom_block", RegistrateRecipeProvider.has(ModBlocks.UNCANNY_MUSHROOM_FULL.get()))
            .group("crafting")
            .save(p, new ResourceLocation(MysticalWorld.MODID, "plain_uncanny_mushroom_block_from_full_uncanny_mushroom_block"));


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

        ShapedRecipeBuilder.shaped(ModBlocks.UNCANNY_MUSHROOM_BLOCK.get().asItem(), 1)
            .pattern("XX")
            .pattern("XX")
            .define('X', ModBlocks.UNCANNY_MUSHROOM.get())
            .group("crafting")
            .unlockedBy("has_uncanny_mushroom", RegistrateRecipeProvider.has(ModBlocks.UNCANNY_MUSHROOM.get()))
            .save(p, new ResourceLocation(MysticalWorld.MODID, "uncanny_mushroom_block_from_mushrooms"));

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
      .register();

  // INSIDE

  public static BlockEntry<Block> MUSHROOM_INSIDE = MysticalWorld.REGISTRATE.block("mushroom_inside_block", Material.WOOD, Block::new)
      .properties(o -> Block.Properties.of(Material.WOOD).sound(SoundType.GRASS))
      .item()
      .model((ctx, p) -> p.blockItem(ModBlocks.MUSHROOM_INSIDE))
      .build()
      .blockstate((ctx, p) -> p.simpleBlock(ctx.getEntry(), p.models().cubeAll(ctx.getName(), new ResourceLocation("minecraft", "block/mushroom_block_inside"))))
      .register();

  // MUD BLOCK

  public static BlockEntry<WetMudBlock> WET_MUD_BLOCK = MysticalWorld.REGISTRATE.block("wet_mud_block", Material.DIRT, WetMudBlock::new)
      .properties((o) -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator.simpleBlockstate("block/wet_mud_block"))
      .register();

  private static final NonNullUnaryOperator<Block.Properties> STONE_PROPS = (o) -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2f);

  public static BlockEntry<Block> MUD_BLOCK = MysticalWorld.REGISTRATE.block("mud_block", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // MUD BRICK

  public static BlockEntry<WetMudBrick> WET_MUD_BRICK = MysticalWorld.REGISTRATE.block("wet_mud_brick", Material.DIRT, WetMudBrick::new)
      .properties(o -> o.sound(SoundType.SLIME_BLOCK).strength(1f))
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
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
      .register();

  // CHARRED STUFF

  // TODO: TAGS
  private static final NonNullUnaryOperator<Block.Properties> WOOD_PROPS = (o) -> o.sound(SoundType.WOOD)
.strength(2.0f).harvestTool(ToolType.AXE)
;

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
      .register();

  public static BlockEntry<CharredLogBlock> CHARRED_LOG = MysticalWorld.REGISTRATE.block("charred_log", (o) -> new CharredLogBlock(o, false))
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.logBlock(ctx.getEntry()))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
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
      .register();

  public static BlockEntry<RotatedPillarBlock> STRIPPED_CHARRED_LOG = MysticalWorld.REGISTRATE.log("stripped_charred_log")
      .properties(WOOD_PROPS)
      .tag(BlockTags.LOGS)
      .blockstate((ctx, p) -> p.logBlock(ctx.getEntry()))
      .item()
      .tag(ItemTags.LOGS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .register();

  public static BlockEntry<Block> CHARRED_PLANKS = MysticalWorld.REGISTRATE.block("charred_planks", Material.WOOD, Block::new)
      .properties(o -> o.sound(SoundType.WOOD).strength(2.0f, 3.0f))
      .tag(BlockTags.PLANKS)
      .item()
      .tag(ItemTags.PLANKS)
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // TERRACOTTA BRICK

  public static BlockEntry<Block> TERRACOTTA_BRICK = MysticalWorld.REGISTRATE.block("terracotta_brick", Material.STONE, Block::new)
      .properties(STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();
  // IRON BRICK

  private static final NonNullUnaryOperator<Block.Properties> IRON_PROPS = (o) -> o.sound(SoundType.METAL).requiresCorrectToolForDrops().strength(3.2f);

  public static BlockEntry<Block> IRON_BRICK = MysticalWorld.REGISTRATE.block("iron_brick", Material.METAL, Block::new)
      .properties(IRON_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> MysticalWorld.RECIPES.twoByTwo(() -> Items.IRON_NUGGET, ModBlocks.IRON_BRICK, null, 1, p))
      .register();

  // SOFT STONE

  private static final NonNullUnaryOperator<Block.Properties> SOFT_STONE_PROPS = o -> o.sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1f);
  private static final NonNullUnaryOperator<Block.Properties> BLACKENED_STONE_PROPS = SOFT_STONE_PROPS;

  public static BlockEntry<Block> SOFT_STONE = MysticalWorld.REGISTRATE.block("soft_stone", Block::new)
      .properties(SOFT_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> {
        p.stonecutting(DataIngredient.items(Items.SMOOTH_STONE), ModBlocks.SOFT_STONE);
        MysticalWorld.RECIPES.twoByTwo(() -> Items.SMOOTH_STONE, ModBlocks.SOFT_STONE, null, p);
      })
      .tag(Tags.Blocks.STONE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // BLACKENED STONE
  public static BlockEntry<Block> BLACKENED_STONE = MysticalWorld.REGISTRATE.block("blackened_stone", Block::new)
      .properties(BLACKENED_STONE_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(Tags.Items.STONE)
      .build()
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 4).pattern("AB").pattern("BA").define('A', Tags.Items.STONE).define('B', Ingredient.of(Items.COAL, Items.CHARCOAL)).unlockedBy("has_stone", RegistrateRecipeProvider.has(Tags.Items.STONE)).save(p))
      .tag(Tags.Blocks.STONE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // SMOOTH OBSIDIAN

  private static final NonNullUnaryOperator<Block.Properties> SOFT_OBSIDIAN_PROPS = o -> o.sound(SoundType.STONE).strength(25f, 600f);

  public static BlockEntry<SoftObsidian.SoftObsidianBlock> SOFT_OBSIDIAN = MysticalWorld.REGISTRATE.block("soft_obsidian", SoftObsidian.SoftObsidianBlock::new)
      .properties(SOFT_OBSIDIAN_PROPS)
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ItemModelGenerator::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .recipe((ctx, p) -> ShapedRecipeBuilder.shaped(ctx.getEntry(), 4).pattern("AB").pattern("BA").define('A', Tags.Items.STONE).define('B', ExcludingIngredient.create(Tags.Items.OBSIDIAN, ctx.getEntry())).unlockedBy("has_stone", RegistrateRecipeProvider.has(Tags.Items.STONE)).unlockedBy("has_obsidian", RegistrateRecipeProvider.has(Tags.Items.OBSIDIAN)).save(p))
      .register();

  // GRANITE QUARTZ
  public static BlockEntry<BaseBlocks.OreBlock> GRANITE_QUARTZ_ORE = MysticalWorld.REGISTRATE.block("granite_quartz_ore", BlockGenerator.oreBlock(ModMaterials.QUARTZ))
      .properties(o -> {
        ModMaterials.QUARTZ.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.QUARTZ_ORE)
      .build()
      .tag(MWTags.Blocks.QUARTZ_ORE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot((p, t) -> p.add(ModBlocks.GRANITE_QUARTZ_ORE.get(), RegistrateBlockLootTables.createOreDrop(t, Items.QUARTZ)))
      .register();

  // SAPPHIRE
  public static BlockEntry<BaseBlocks.OreBlock> SAPPHIRE_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.SAPPHIRE.oreName(), BlockGenerator.oreBlock(ModMaterials.SAPPHIRE))
      .properties(o -> {
        ModMaterials.SAPPHIRE.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SAPPHIRE_ORE)
      .build()
      .tag(MWTags.Blocks.SAPPHIRE_ORE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .loot((p, t) -> p.add(ModBlocks.SAPPHIRE_ORE.get(), RegistrateBlockLootTables.createOreDrop(t, ModItems.SAPPHIRE_GEM.get())))
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
      .tag(MWTags.Blocks.SAPPHIRE_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // COPPER
  public static BlockEntry<BaseBlocks.OreBlock> COPPER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.COPPER.oreName(), BlockGenerator.oreBlock(ModMaterials.COPPER))
      .properties(o -> {
        ModMaterials.COPPER.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.COPPER_ORE)
      .build()
      .tag(MWTags.Blocks.COPPER_ORE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();


  public static ResourceLocation RL = new ResourceLocation("mysticalworld:item/copper");

  // LEAD
  public static BlockEntry<BaseBlocks.OreBlock> LEAD_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.LEAD.oreName(), BlockGenerator.oreBlock(ModMaterials.LEAD))
      .properties(o -> {
        ModMaterials.LEAD.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.LEAD_ORE)
      .build()
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .tag(MWTags.Blocks.LEAD_ORE)
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
      .tag(MWTags.Blocks.LEAD_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // ORICHALCUM
  public static BlockEntry<Block> ORICHALCUM_BLOCK = MysticalWorld.REGISTRATE.block(ModMaterials.ORICHALCUM.blockName(), Material.METAL, Block::new)
      .properties(o -> {
        ModMaterials.ORICHALCUM.getBlockProps(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.ORICHALCUM_BLOCK)
      .build()
      .tag(MWTags.Blocks.ORICHALCUM_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // SILVER
  public static BlockEntry<BaseBlocks.OreBlock> SILVER_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.SILVER.oreName(), BlockGenerator.oreBlock(ModMaterials.SILVER))
      .properties(o -> {
        ModMaterials.SILVER.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.SILVER_ORE)
      .build()
      .tag(MWTags.Blocks.SILVER_ORE)
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
      .tag(MWTags.Blocks.SILVER_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // TIN
  public static BlockEntry<BaseBlocks.OreBlock> TIN_ORE = MysticalWorld.REGISTRATE.block(ModMaterials.TIN.oreName(), BlockGenerator.oreBlock(ModMaterials.TIN))
      .properties(o -> {
        ModMaterials.TIN.getOreBlockProperties(o);
        return o;
      })
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.TIN_ORE)
      .build()
      .tag(MWTags.Blocks.TIN_ORE)
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
      .tag(MWTags.Blocks.TIN_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  // TODO: Tags
  public static NonNullUnaryOperator<Block.Properties> PEARL_PROPS = o -> o.strength(1.2F, 1.2F).sound(SoundType.STONE)
.harvestTool(ToolType.PICKAXE).harvestLevel(1)
;

  public static BlockEntry<Block> PEARL_BLOCK = MysticalWorld.REGISTRATE.block("pearl_block", Material.STONE, Block::new)
      .properties(PEARL_PROPS)
      .item()
      .model(ItemModelGenerator::itemModel)
      .tag(MWTags.Items.PEARL_BLOCK)
      .build()
      .tag(MWTags.Blocks.PEARL_STORAGE)
      .blockstate(BlockstateGenerator::simpleBlockstate)
      .register();

  protected static NonNullUnaryOperator<BlockBehaviour.Properties> BONE_PROPS = (o) -> BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(0.2F).sound(SoundType.BONE_BLOCK);

  public static BlockEntry<BonesBlock> BONE_PILE_1 = MysticalWorld.REGISTRATE.block("bone_pile_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
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
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_2 = MysticalWorld.REGISTRATE.block("bone_pile_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_1.get()).unlockedBy("has_bone_pile_1", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_1.get())).save(p))
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_3 = MysticalWorld.REGISTRATE.block("bone_pile_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_2.get()).unlockedBy("has_bone_pile_2", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_2.get())).save(p))
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> BONE_PILE_4 = MysticalWorld.REGISTRATE.block("bone_pile_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.PILE))
      .properties(BONE_PROPS)
      .lang("Bone Pile")
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/bone_pile4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.BONE_PILE_3.get()).unlockedBy("has_bone_pile_3", RegistrateRecipeProvider.has(ModBlocks.BONE_PILE_3.get())).save(p))
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_1 = MysticalWorld.REGISTRATE.block("skeleton_bottom_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
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
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_2 = MysticalWorld.REGISTRATE.block("skeleton_bottom_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_1.get()).unlockedBy("has_skeleton_bottom_1", RegistrateRecipeProvider.has(ModBlocks.SKELETON_BOTTOM_1.get())).save(p)
      )
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> SKELETON_BOTTOM_3 = MysticalWorld.REGISTRATE.block("skeleton_bottom_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.BOTTOM))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_bottom3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_BOTTOM_2.get()).unlockedBy("has_skeleton_bottom_2", RegistrateRecipeProvider.has(ModBlocks.SKELETON_BOTTOM_2.get())).save(p)
      )
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_1 = MysticalWorld.REGISTRATE.block("skeleton_top_1", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top1"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
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
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_2 = MysticalWorld.REGISTRATE.block("skeleton_top_2", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top2"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_1.get()).unlockedBy("has_skeleton_top_1", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_1.get())).save(p)
      )
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_3 = MysticalWorld.REGISTRATE.block("skeleton_top_3", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top3"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_2.get()).unlockedBy("has_skeleton_top_2", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_2.get())).save(p)
      )
      .loot(boneLoot())
      .register();

  public static BlockEntry<BonesBlock> SKELETON_TOP_4 = MysticalWorld.REGISTRATE.block("skeleton_top_4", (p) -> new BonesBlock(p, BonesBlock.BoneType.TOP))
      .properties(BONE_PROPS)
      .blockstate((ctx, p) -> p.getVariantBuilder(ctx.getEntry()).forAllStates((state) -> ConfiguredModel.builder().modelFile(p.models().getExistingFile(new ResourceLocation(MysticalWorld.MODID, "block/skeleton_top4"))).rotationY((int) (state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360).build()))
      .lang("Skeletal Remains")
      .item()
      .model(ModBlocks::boneModel)
      .build()
      .recipe((ctx, p) ->
          ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1).requires(ModBlocks.SKELETON_TOP_3.get()).unlockedBy("has_skeleton_top_3", RegistrateRecipeProvider.has(ModBlocks.SKELETON_TOP_3.get())).save(p)
      )
      .loot(boneLoot())
      .register();


  public static void load() {
  }
}
*/
