package epicsquid.mysticalworld.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.mysticallib.block.BaseOreBlock;
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
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
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

  public static <T extends IForgeRegistryEntry<?>> String name(T block) {
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
      .tag(Tags.Blocks.STONE)
      .blockstate(ModBlocks::simpleBlockState)
      .register();

  // SMOOTH OBSIDIAN

  public static RegistryEntry<Block> SOFT_OBSIDIAN = REGISTRATE.block("soft_obsidian", Block::new)
      .properties(o -> o.sound(SoundType.STONE).hardnessAndResistance(20f))
      .item()
      .tag(Tags.Items.OBSIDIAN)
      .model(ModBlocks::itemModel)
      .build()
      .tag(Tags.Blocks.OBSIDIAN)
      .blockstate(ModBlocks::simpleBlockState)
      .recipe((ctx, p) -> {
        RECIPES.twoByTwo(() -> Items.OBSIDIAN, ModBlocks.SOFT_OBSIDIAN, null, p);
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(Items.OBSIDIAN), ModBlocks.SOFT_OBSIDIAN.get())
            .addCriterion("has_obsidian", p.hasItem(Items.OBSIDIAN))
            .build(p, "soft_obsidian_from_obsidian_stonecutting");
      })
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


  public static void load() {
  }
}
