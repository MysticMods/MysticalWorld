package epicsquid.mysticalworld.data;

import epicsquid.mysticallib.data.DeferredRecipeProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.MWTags;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class MWRecipeProvider extends DeferredRecipeProvider {
  public MWRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn, MysticalWorld.MODID);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    dye(ModItems.INK_BOTTLE, Items.BLACK_DYE.delegate, 1, 2, consumer);
    dye(ModItems.CARAPACE, Items.BLUE_DYE.delegate, 1, 2, consumer);

    singleItemUnfinished(ModItems.ANTLERS, () -> Items.BONE_MEAL, 1, 9).build(consumer, new ResourceLocation(MysticalWorld.MODID, "antlers_to_bonemeal"));
    singleItem(ModItems.AUBERGINE, ModItems.AUBERGINE_SEEDS, 1, 1, consumer);
    singleItem(ModItems.SILK_COCOON, ModItems.SILK_THREAD, 1, 3, consumer);
    singleItemUnfinished(ModItems.PELT, () -> Items.LEATHER, 1, 1).build(consumer, new ResourceLocation(MysticalWorld.MODID, "pelt_to_leather"));

    smelting(ModBlocks.WET_MUD_BLOCK, ModBlocks.MUD_BLOCK, 0.15f, false, consumer);
    smelting(ModBlocks.WET_MUD_BRICK, ModBlocks.MUD_BRICK, 0.15f, false, consumer);

    twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, consumer);
    twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, consumer);
    twoByTwo(() -> Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK, null, consumer);

    planks(ModBlocks.CHARRED_LOG, ModBlocks.CHARRED_PLANKS, consumer);
    stairs(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_STAIRS, "stairs", false, consumer);
    slab(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_SLAB, "slabs", false, consumer);
    fence(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_FENCE, "fence", consumer);
    fenceGate(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_FENCE_GATE, "fence_gate", consumer);
    wall(ModBlocks.CHARRED_PLANKS, ModBlocks.CHARRED_WALL, false, consumer);

    stairs(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_STAIRS, "stairs", false, consumer);
    slab(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_SLAB, "slabs", false, consumer);
    fence(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_FENCE, "fence", consumer);
    fenceGate(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_FENCE_GATE, "fence_gate", consumer);
    wall(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BLOCK_WALL, false, consumer);

    stairs(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_STAIRS, "stairs", false, consumer);
    slab(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_SLAB, "slabs", false, consumer);
    fence(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_FENCE, "fence", consumer);
    fenceGate(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_FENCE_GATE, "fence_gate", consumer);
    wall(ModBlocks.MUD_BRICK, ModBlocks.MUD_BRICK_WALL, false, consumer);

    stairs(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_STAIRS, "stairs", false, consumer);
    slab(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_SLAB, "slabs", false, consumer);
    fence(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_FENCE, "fence", consumer);
    fenceGate(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_FENCE_GATE, "fence_gate", consumer);
    wall(ModBlocks.TERRACOTTA_BRICK, ModBlocks.TERRACOTTA_BRICK_WALL, false, consumer);

    axe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_AXE, "axe", consumer);
    axe(ModItems.COPPER_INGOT, ModItems.COPPER_AXE, "axe", consumer);
    axe(ModItems.SILVER_INGOT, ModItems.SILVER_AXE, "axe", consumer);
    axe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_AXE, "axe", consumer);
    axe(ModItems.TIN_INGOT, ModItems.TIN_AXE, "axe", consumer);
    axe(ModItems.LEAD_INGOT, ModItems.LEAD_AXE, "axe", consumer);
    axe(() -> Items.CACTUS, ModItems.CACTUS_AXE, "axe", consumer);

    storage(ModItems.AMETHYST_GEM, ModBlocks.AMETHYST_BLOCK, consumer);
    storage(ModItems.COPPER_INGOT, ModBlocks.COPPER_BLOCK, consumer);
    storage(ModItems.SILVER_INGOT, ModBlocks.SILVER_BLOCK, consumer);
    storage(ModItems.QUICKSILVER_INGOT, ModBlocks.QUICKSILVER_BLOCK, consumer);
    storage(ModItems.TIN_INGOT, ModBlocks.TIN_BLOCK, consumer);
    storage(ModItems.LEAD_INGOT, ModBlocks.LEAD_BLOCK, consumer);

    storage(ModItems.COPPER_NUGGET, ModItems.COPPER_INGOT, consumer);
    storage(ModItems.SILVER_NUGGET, ModItems.SILVER_INGOT, consumer);
    storage(ModItems.QUICKSILVER_NUGGET, ModItems.QUICKSILVER_INGOT, consumer);
    storage(ModItems.TIN_NUGGET, ModItems.TIN_INGOT, consumer);
    storage(ModItems.LEAD_NUGGET, ModItems.LEAD_INGOT, consumer);

    singleItemUnfinished(ModItems.UNRIPE_ENDER_PEARL, () -> Items.ENDER_PEARL, 9, 1).build(consumer, new ResourceLocation(MysticalWorld.MODID, "ender_pearl_from_unripe_pearls"));

    pickaxe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_PICKAXE, "pickaxe", consumer);
    pickaxe(ModItems.COPPER_INGOT, ModItems.COPPER_PICKAXE, "pickaxe", consumer);
    pickaxe(ModItems.SILVER_INGOT, ModItems.SILVER_PICKAXE, "pickaxe", consumer);
    pickaxe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_PICKAXE, "pickaxe", consumer);
    pickaxe(ModItems.TIN_INGOT, ModItems.TIN_PICKAXE, "pickaxe", consumer);
    pickaxe(ModItems.LEAD_INGOT, ModItems.LEAD_PICKAXE, "pickaxe", consumer);
    pickaxe(() -> Items.CACTUS, ModItems.CACTUS_PICKAXE, "pickaxe", consumer);

    sword(ModItems.AMETHYST_GEM, ModItems.AMETHYST_SWORD, "sword", consumer);
    sword(ModItems.COPPER_INGOT, ModItems.COPPER_SWORD, "sword", consumer);
    sword(ModItems.SILVER_INGOT, ModItems.SILVER_SWORD, "sword", consumer);
    sword(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SWORD, "sword", consumer);
    sword(ModItems.TIN_INGOT, ModItems.TIN_SWORD, "sword", consumer);
    sword(ModItems.LEAD_INGOT, ModItems.LEAD_SWORD, "sword", consumer);
    sword(() -> Items.CACTUS, ModItems.CACTUS_SWORD, "sword", consumer);

    shovel(ModItems.AMETHYST_GEM, ModItems.AMETHYST_SHOVEL, "shovel", consumer);
    shovel(ModItems.COPPER_INGOT, ModItems.COPPER_SHOVEL, "shovel", consumer);
    shovel(ModItems.SILVER_INGOT, ModItems.SILVER_SHOVEL, "shovel", consumer);
    shovel(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SHOVEL, "shovel", consumer);
    shovel(ModItems.TIN_INGOT, ModItems.TIN_SHOVEL, "shovel", consumer);
    shovel(ModItems.LEAD_INGOT, ModItems.LEAD_SHOVEL, "shovel", consumer);
    shovel(() -> Items.CACTUS, ModItems.CACTUS_SHOVEL, "shovel", consumer);

    hoe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_HOE, "hoe", consumer);
    hoe(ModItems.COPPER_INGOT, ModItems.COPPER_HOE, "hoe", consumer);
    hoe(ModItems.SILVER_INGOT, ModItems.SILVER_HOE, "hoe", consumer);
    hoe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HOE, "hoe", consumer);
    hoe(ModItems.TIN_INGOT, ModItems.TIN_HOE, "hoe", consumer);
    hoe(ModItems.LEAD_INGOT, ModItems.LEAD_HOE, "hoe", consumer);
    hoe(() -> Items.CACTUS, ModItems.CACTUS_HOE, "hoe", consumer);

    knife(ModItems.AMETHYST_GEM, ModItems.AMETHYST_KNIFE, "knife", consumer);
    knife(ModItems.COPPER_INGOT, ModItems.COPPER_KNIFE, "knife", consumer);
    knife(ModItems.SILVER_INGOT, ModItems.SILVER_KNIFE, "knife", consumer);
    knife(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_KNIFE, "knife", consumer);
    knife(ModItems.TIN_INGOT, ModItems.TIN_KNIFE, "knife", consumer);
    knife(ModItems.LEAD_INGOT, ModItems.LEAD_KNIFE, "knife", consumer);

    knife(() -> Items.DIAMOND, ModItems.DIAMOND_KNIFE, "knife", consumer);
    knife(() -> Items.GOLD_INGOT, ModItems.GOLD_KNIFE, "knife", consumer);
    knife(() -> Items.IRON_INGOT, ModItems.IRON_KNIFE, "knife", consumer);
    knife(ItemTags.PLANKS, ModItems.WOODEN_KNIFE, "knife", consumer);
    knife(net.minecraftforge.common.Tags.Items.COBBLESTONE, ModItems.STONE_KNIFE, "knife", consumer);
    knife(() -> Items.CACTUS, ModItems.CACTUS_KNIFE, "knife", consumer);

    helmet(ModItems.AMETHYST_GEM, ModItems.AMETHYST_HELMET, "helmet", consumer);
    helmet(ModItems.COPPER_INGOT, ModItems.COPPER_HELMET, "helmet", consumer);
    helmet(ModItems.SILVER_INGOT, ModItems.SILVER_HELMET, "helmet", consumer);
    helmet(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HELMET, "helmet", consumer);
    helmet(ModItems.TIN_INGOT, ModItems.TIN_HELMET, "helmet", consumer);
    helmet(ModItems.LEAD_INGOT, ModItems.LEAD_HELMET, "helmet", consumer);

    chest(ModItems.AMETHYST_GEM, ModItems.AMETHYST_CHESTPLATE, "chestplate", consumer);
    chest(ModItems.COPPER_INGOT, ModItems.COPPER_CHESTPLATE, "chestplate", consumer);
    chest(ModItems.SILVER_INGOT, ModItems.SILVER_CHESTPLATE, "chestplate", consumer);
    chest(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_CHESTPLATE, "chestplate", consumer);
    chest(ModItems.TIN_INGOT, ModItems.TIN_CHESTPLATE, "chestplate", consumer);
    chest(ModItems.LEAD_INGOT, ModItems.LEAD_CHESTPLATE, "chestplate", consumer);

    boots(ModItems.AMETHYST_GEM, ModItems.AMETHYST_BOOTS, "boots", consumer);
    boots(ModItems.COPPER_INGOT, ModItems.COPPER_BOOTS, "boots", consumer);
    boots(ModItems.SILVER_INGOT, ModItems.SILVER_BOOTS, "boots", consumer);
    boots(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_BOOTS, "boots", consumer);
    boots(ModItems.TIN_INGOT, ModItems.TIN_BOOTS, "boots", consumer);
    boots(ModItems.LEAD_INGOT, ModItems.LEAD_BOOTS, "boots", consumer);

    legs(ModItems.AMETHYST_GEM, ModItems.AMETHYST_LEGGINGS, "leggings", consumer);
    legs(ModItems.COPPER_INGOT, ModItems.COPPER_LEGGINGS, "leggings", consumer);
    legs(ModItems.SILVER_INGOT, ModItems.SILVER_LEGGINGS, "leggings", consumer);
    legs(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_LEGGINGS, "leggings", consumer);
    legs(ModItems.TIN_INGOT, ModItems.TIN_LEGGINGS, "leggings", consumer);
    legs(ModItems.LEAD_INGOT, ModItems.LEAD_LEGGINGS, "leggings", consumer);

    ore(MWTags.Items.AMETHYST_ORE, ModItems.AMETHYST_GEM, 0.3f, consumer);
    ore(MWTags.Items.COPPER_ORE, ModItems.COPPER_INGOT, 0.3f, consumer);
    ore(MWTags.Items.LEAD_ORE, ModItems.LEAD_INGOT, 0.3f, consumer);
    ore(MWTags.Items.QUICKSILVER_ORE, ModItems.QUICKSILVER_INGOT, 0.3f, consumer);
    ore(MWTags.Items.SILVER_ORE, ModItems.SILVER_INGOT, 0.3f, consumer);
    ore(MWTags.Items.TIN_ORE, ModItems.TIN_INGOT, 0.3f, consumer);

    food(ModItems.VENISON, ModItems.COOKED_VENISON, 0.15f, consumer);
    food(MWTags.Items.CARROT, ModItems.COOKED_CARROT, 0.15f, consumer);
    food(MWTags.Items.BEETROOT, ModItems.COOKED_BEETROOT, 0.15f, consumer);
    food(MWTags.Items.SF_EGGPLANT, ModItems.COOKED_AUBERGINE, 0.15f, consumer);
    food(ModItems.RAW_SQUID, ModItems.COOKED_SQUID, 0.15f, consumer);

    recycle(MWTags.Items.SILVER_ITEMS, ModItems.SILVER_NUGGET, 0.15f, consumer);
    recycle(MWTags.Items.COPPER_ITEMS, ModItems.COPPER_NUGGET, 0.15f, consumer);
    recycle(MWTags.Items.QUICKSILVER_ITEMS, ModItems.QUICKSILVER_NUGGET, 0.15f, consumer);
    recycle(MWTags.Items.TIN_ITEMS, ModItems.TIN_NUGGET, 0.15f, consumer);
    recycle(MWTags.Items.LEAD_ITEMS, ModItems.LEAD_NUGGET, 0.15f, consumer);

    recycle(ModItems.GOLD_KNIFE, () -> Items.GOLD_NUGGET, 0.15f, consumer);
    recycle(ModItems.IRON_KNIFE, () -> Items.IRON_NUGGET, 0.15f, consumer);

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.STUFFED_AUBERGINE.get(), 1).addIngredient(ModItems.COOKED_AUBERGINE.get()).addIngredient(MWTags.Items.VEGETABLES).addIngredient(MWTags.Items.VEGETABLES).addIngredient(MWTags.Items.COOKED_VEGETABLES).addCriterion("has_cooked_aubergine", this.hasItem(ModItems.COOKED_AUBERGINE.get())).build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.WET_MUD_BLOCK.get(), 8)
        .patternLine("XXX")
        .patternLine("XWX")
        .patternLine("XXX")
        .key('X', Blocks.DIRT)
        .key('W', Items.WATER_BUCKET)
        .addCriterion("has_dirt", this.hasItem(Blocks.DIRT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModBlocks.THATCH.get(), 32)
        .patternLine("XY")
        .patternLine("YX")
        .key('X', Blocks.HAY_BLOCK)
        .key('Y', Items.WHEAT)
        .addCriterion("has_hay", this.hasItem(Blocks.HAY_BLOCK))
        .addCriterion("has_wheat", this.hasItem(Items.WHEAT))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModItems.EPIC_SQUID.get(), 2)
        .patternLine("CAC")
        .patternLine("AEA")
        .patternLine("CAC")
        .key('C', ModItems.COOKED_SQUID.get())
        .key('A', MWTags.Items.GEMS)
        .key('E', net.minecraftforge.common.Tags.Items.GEMS_EMERALD)
        .addCriterion("has_squid", this.hasItem(ModItems.COOKED_SQUID.get()))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModItems.SPINDLE.get(), 1)
        .patternLine(" S ")
        .patternLine("XXX")
        .patternLine(" T ")
        .key('S', net.minecraftforge.common.Tags.Items.RODS_WOODEN)
        .key('X', ItemTags.WOODEN_SLABS)
        .key('T', Items.TRIPWIRE_HOOK)
        .addCriterion("has_slab", this.hasItem(ItemTags.WOODEN_SLABS))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(ModItems.GLISTERING_HORN.get(), 1)
        .patternLine("XXX")
        .patternLine("XHX")
        .patternLine("XXX")
        .key('X', MWTags.Items.SILVER_INGOT)
        .key('H', ModItems.NAUTILUS_HORN.get())
        .addCriterion("has_horn", this.hasItem(ModItems.NAUTILUS_HORN.get()))
        .build(consumer);

    // Fix Minecraft default recipes! >:0
    ShapedRecipeBuilder.shapedRecipe(Items.FISHING_ROD, 1)
        .patternLine("  X")
        .patternLine(" XS")
        .patternLine("X S")
        .key('X', Tags.Items.RODS_WOODEN)
        .key('S', Tags.Items.STRING)
        .addCriterion("has_string", this.hasItem(Tags.Items.STRING))
        .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(Items.SCAFFOLDING, 6)
        .patternLine("XSX")
        .patternLine("X X")
        .patternLine("X X")
        .key('X', Items.BAMBOO)
        .key('S', Tags.Items.STRING)
        .addCriterion("has_bamboo", this.hasItem(Items.BAMBOO))
        .build(consumer);

    // String -> wool
    ShapedRecipeBuilder.shapedRecipe(Blocks.WHITE_WOOL, 1)
        .patternLine("XX")
        .patternLine("XX")
        .key('X', Tags.Items.STRING)
        .addCriterion("has_string", this.hasItem(Tags.Items.STRING))
        .build(consumer);

    // Bow
    ShapedRecipeBuilder.shapedRecipe(Items.BOW, 1)
        .patternLine(" XS")
        .patternLine("X S")
        .patternLine(" XS")
        .key('X', Tags.Items.RODS_WOODEN)
        .key('S', Tags.Items.STRING)
        .addCriterion("has_string", this.hasItem(Tags.Items.STRING))
        .build(consumer);

    // Loom
    ShapedRecipeBuilder.shapedRecipe(Blocks.LOOM, 1)
        .patternLine("SS")
        .patternLine("XX")
        .key('X', ItemTags.PLANKS)
        .key('S', Tags.Items.STRING)
        .addCriterion("has_string", this.hasItem(Tags.Items.STRING))
        .build(consumer);

    // Crossbow
    ShapedRecipeBuilder.shapedRecipe(Items.CROSSBOW, 1)
        .patternLine("XIX")
        .patternLine("STS")
        .patternLine(" X ")
        .key('X', Tags.Items.RODS_WOODEN)
        .key('S', Tags.Items.STRING)
        .key('I', Tags.Items.INGOTS_IRON)
        .key('T', Items.TRIPWIRE_HOOK)
        .addCriterion("has_string", this.hasItem(Tags.Items.STRING))
        .addCriterion("has_iron", this.hasItem(Tags.Items.INGOTS_IRON))
        .build(consumer);

    // Lead
    ShapedRecipeBuilder.shapedRecipe(Items.LEAD, 2)
        .patternLine("SS ")
        .patternLine("SB ")
        .patternLine("  S")
        .key('S', Tags.Items.STRING)
        .key('B', Tags.Items.SLIMEBALLS)
        .addCriterion("has_slime", this.hasItem(Tags.Items.SLIMEBALLS))
        .build(consumer);

    // Rotten Apple
    ShapedRecipeBuilder.shapedRecipe(ModItems.ROTTEN_APPLE.get(), 1)
        .patternLine("WLW")
        .patternLine("LAL")
        .patternLine("WLW")
        .key('W', Items.ROTTEN_FLESH)
        .key('A', Items.APPLE)
        .key('L', MWTags.Items.LEAD_INGOT)
        .addCriterion("has_apple", this.hasItem(Items.APPLE))
        .build(consumer);
  }
}
