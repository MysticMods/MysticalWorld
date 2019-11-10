package epicsquid.mysticalworld.data;

import epicsquid.mysticallib.data.DeferredRecipeProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.Tags;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MWRecipeProvider extends DeferredRecipeProvider {
  public MWRecipeProvider(DataGenerator generatorIn) {
    super(generatorIn, MysticalWorld.MODID);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    dye(ModItems.INK_BOTTLE, Items.BLACK_DYE.delegate, 1, 2, consumer);

    singleItem(ModItems.AUBERGINE, ModItems.AUBERGINE_SEEDS, 1, 1, consumer);

    smelting(ModBlocks.WET_MUD_BLOCK, ModBlocks.MUD_BLOCK, 0.15f, false, consumer);
    smelting(ModBlocks.WET_MUD_BRICK, ModBlocks.MUD_BRICK, 0.15f, false, consumer);

    twoByTwo(ModBlocks.WET_MUD_BLOCK, ModBlocks.WET_MUD_BRICK, null, consumer);
    twoByTwo(ModBlocks.MUD_BLOCK, ModBlocks.MUD_BRICK, null, consumer);

    axe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_AXE, null, consumer);
    axe(ModItems.COPPER_INGOT, ModItems.COPPER_AXE, null, consumer);
    axe(ModItems.SILVER_INGOT, ModItems.SILVER_AXE, null, consumer);
    axe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_AXE, null, consumer);
    axe(ModItems.TIN_INGOT, ModItems.TIN_AXE, null, consumer);
    axe(() -> Items.CACTUS, ModItems.CACTUS_AXE, null, consumer);

    storage(ModItems.AMETHYST_GEM, ModBlocks.AMETHYST_BLOCK, consumer);
    storage(ModItems.COPPER_INGOT, ModBlocks.COPPER_BLOCK, consumer);
    storage(ModItems.SILVER_INGOT, ModBlocks.SILVER_BLOCK, consumer);
    storage(ModItems.QUICKSILVER_INGOT, ModBlocks.QUICKSILVER_BLOCK, consumer);
    storage(ModItems.TIN_INGOT, ModBlocks.TIN_BLOCK, consumer);

    storage(ModItems.COPPER_NUGGET, ModItems.COPPER_INGOT, consumer);
    storage(ModItems.SILVER_NUGGET, ModItems.SILVER_INGOT, consumer);
    storage(ModItems.QUICKSILVER_NUGGET, ModItems.QUICKSILVER_INGOT, consumer);
    storage(ModItems.TIN_NUGGET, ModItems.TIN_INGOT, consumer);

    storage(ModItems.UNRIPE_ENDER_PEARL, () -> Items.ENDER_PEARL, consumer);

    pickaxe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_PICKAXE, null, consumer);
    pickaxe(ModItems.COPPER_INGOT, ModItems.COPPER_PICKAXE, null, consumer);
    pickaxe(ModItems.SILVER_INGOT, ModItems.SILVER_PICKAXE, null, consumer);
    pickaxe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_PICKAXE, null, consumer);
    pickaxe(ModItems.TIN_INGOT, ModItems.TIN_PICKAXE, null, consumer);
    pickaxe(() -> Items.CACTUS, ModItems.CACTUS_PICKAXE, null, consumer);

    sword(ModItems.AMETHYST_GEM, ModItems.AMETHYST_SWORD, null, consumer);
    sword(ModItems.COPPER_INGOT, ModItems.COPPER_SWORD, null, consumer);
    sword(ModItems.SILVER_INGOT, ModItems.SILVER_SWORD, null, consumer);
    sword(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SWORD, null, consumer);
    sword(ModItems.TIN_INGOT, ModItems.TIN_SWORD, null, consumer);
    sword(() -> Items.CACTUS, ModItems.CACTUS_SWORD, null, consumer);

    shovel(ModItems.AMETHYST_GEM, ModItems.AMETHYST_SHOVEL, null, consumer);
    shovel(ModItems.COPPER_INGOT, ModItems.COPPER_SHOVEL, null, consumer);
    shovel(ModItems.SILVER_INGOT, ModItems.SILVER_SHOVEL, null, consumer);
    shovel(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_SHOVEL, null, consumer);
    shovel(ModItems.TIN_INGOT, ModItems.TIN_SHOVEL, null, consumer);
    shovel(() -> Items.CACTUS, ModItems.CACTUS_SHOVEL, null, consumer);

    hoe(ModItems.AMETHYST_GEM, ModItems.AMETHYST_HOE, null, consumer);
    hoe(ModItems.COPPER_INGOT, ModItems.COPPER_HOE, null, consumer);
    hoe(ModItems.SILVER_INGOT, ModItems.SILVER_HOE, null, consumer);
    hoe(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HOE, null, consumer);
    hoe(ModItems.TIN_INGOT, ModItems.TIN_HOE, null, consumer);
    hoe(() -> Items.CACTUS, ModItems.CACTUS_HOE, null, consumer);

    knife(ModItems.AMETHYST_GEM, ModItems.AMETHYST_KNIFE, null, consumer);
    knife(ModItems.COPPER_INGOT, ModItems.COPPER_KNIFE, null, consumer);
    knife(ModItems.SILVER_INGOT, ModItems.SILVER_KNIFE, null, consumer);
    knife(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_KNIFE, null, consumer);
    knife(ModItems.TIN_INGOT, ModItems.TIN_KNIFE, null, consumer);

    knife(() -> Items.DIAMOND, ModItems.DIAMOND_KNIFE, null, consumer);
    knife(() -> Items.GOLD_INGOT, ModItems.GOLD_KNIFE, null, consumer);
    knife(() -> Items.IRON_INGOT, ModItems.IRON_KNIFE, null, consumer);
    knife(ItemTags.PLANKS, ModItems.WOODEN_KNIFE, null, consumer);
    knife(net.minecraftforge.common.Tags.Items.COBBLESTONE, ModItems.STONE_KNIFE, null, consumer);
    knife(() -> Items.CACTUS, ModItems.CACTUS_KNIFE, null, consumer);

    helmet(ModItems.AMETHYST_GEM, ModItems.AMETHYST_HELMET, null, consumer);
    helmet(ModItems.COPPER_INGOT, ModItems.COPPER_HELMET, null, consumer);
    helmet(ModItems.SILVER_INGOT, ModItems.SILVER_HELMET, null, consumer);
    helmet(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_HELMET, null, consumer);
    helmet(ModItems.TIN_INGOT, ModItems.TIN_HELMET, null, consumer);

    chest(ModItems.AMETHYST_GEM, ModItems.AMETHYST_CHESTPLATE, null, consumer);
    chest(ModItems.COPPER_INGOT, ModItems.COPPER_CHESTPLATE, null, consumer);
    chest(ModItems.SILVER_INGOT, ModItems.SILVER_CHESTPLATE, null, consumer);
    chest(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_CHESTPLATE, null, consumer);
    chest(ModItems.TIN_INGOT, ModItems.TIN_CHESTPLATE, null, consumer);

    boots(ModItems.AMETHYST_GEM, ModItems.AMETHYST_BOOTS, null, consumer);
    boots(ModItems.COPPER_INGOT, ModItems.COPPER_BOOTS, null, consumer);
    boots(ModItems.SILVER_INGOT, ModItems.SILVER_BOOTS, null, consumer);
    boots(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_BOOTS, null, consumer);
    boots(ModItems.TIN_INGOT, ModItems.TIN_BOOTS, null, consumer);

    legs(ModItems.AMETHYST_GEM, ModItems.AMETHYST_LEGGINGS, null, consumer);
    legs(ModItems.COPPER_INGOT, ModItems.COPPER_LEGGINGS, null, consumer);
    legs(ModItems.SILVER_INGOT, ModItems.SILVER_LEGGINGS, null, consumer);
    legs(ModItems.QUICKSILVER_INGOT, ModItems.QUICKSILVER_LEGGINGS, null, consumer);
    legs(ModItems.TIN_INGOT, ModItems.TIN_LEGGINGS, null, consumer);

    ore(Tags.Items.AMETHYST_ORE, ModItems.AMETHYST_GEM, 0.3f, consumer);
    ore(Tags.Items.COPPER_ORE, ModItems.COPPER_INGOT, 0.3f, consumer);
    ore(Tags.Items.LEAD_ORE, ModItems.LEAD_INGOT, 0.3f, consumer);
    ore(Tags.Items.QUICKSILVER_ORE, ModItems.QUICKSILVER_INGOT, 0.3f, consumer);
    ore(Tags.Items.SILVER_ORE, ModItems.SILVER_INGOT, 0.3f, consumer);
    ore(Tags.Items.TIN_ORE, ModItems.TIN_INGOT, 0.3f, consumer);

    food(ModItems.VENISON, ModItems.COOKED_VENISON, 0.15f, consumer);
    food(() -> Items.CARROT, ModItems.COOKED_CARROT, 0.15f, consumer);
    food(() -> Items.BEETROOT, ModItems.COOKED_BEETROOT, 0.15f, consumer);
    food(ModItems.AUBERGINE, ModItems.COOKED_AUBERGINE, 0.15f, consumer);
    food(ModItems.RAW_SQUID, ModItems.COOKED_SQUID, 0.15f, consumer);

    recycle(Tags.Items.SILVER_ITEMS, ModItems.SILVER_NUGGET, 0.15f, consumer);
    recycle(Tags.Items.COPPER_ITEMS, ModItems.COPPER_NUGGET, 0.15f, consumer);
    recycle(Tags.Items.QUICKSILVER_ITEMS, ModItems.QUICKSILVER_NUGGET, 0.15f, consumer);
    recycle(Tags.Items.TIN_ITEMS, ModItems.TIN_NUGGET, 0.15f, consumer);
    recycle(Tags.Items.LEAD_ITEMS, ModItems.LEAD_NUGGET, 0.15f, consumer);

    recycle(ModItems.GOLD_KNIFE, () -> Items.GOLD_NUGGET, 0.15f, consumer);
    recycle(ModItems.IRON_KNIFE, () -> Items.IRON_NUGGET, 0.15f, consumer);

    ShapelessRecipeBuilder.shapelessRecipe(ModItems.STUFFED_AUBERGINE.get(), 1).addIngredient(ModItems.COOKED_AUBERGINE.get()).addIngredient(Tags.Items.VEGETABLES).addIngredient(Tags.Items.VEGETABLES).addIngredient(Tags.Items.COOKED_VEGETABLES).addCriterion("has_cooked_aubergine", this.hasItem(ModItems.COOKED_AUBERGINE.get())).build(consumer);

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
        .key('A', Tags.Items.GEMS)
        .key('E', net.minecraftforge.common.Tags.Items.GEMS_EMERALD)
        .addCriterion("has_squid", this.hasItem(ModItems.COOKED_SQUID.get()))
        .build(consumer);
  }
}
