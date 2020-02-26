package epicsquid.mysticalworld.data;

import epicsquid.mysticallib.data.DeferredItemTagsProvider;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import static epicsquid.mysticalworld.MWTags.Blocks;
import static epicsquid.mysticalworld.MWTags.Items.*;

public class MWItemTagsProvider extends DeferredItemTagsProvider {
  public MWItemTagsProvider(DataGenerator generatorIn) {
    super(generatorIn, "Mystical World Item Tags Provider");
  }

  @Override
  protected void registerTags() {
    addItemsToTag(AMETHYST_ORE, ModBlocks.AMETHYST_ORE);
    addItemsToTag(COPPER_ORE, ModBlocks.COPPER_ORE);
    addItemsToTag(LEAD_ORE, ModBlocks.LEAD_ORE);
    addItemsToTag(QUICKSILVER_ORE, ModBlocks.QUICKSILVER_ORE);
    addItemsToTag(SILVER_ORE, ModBlocks.SILVER_ORE);
    addItemsToTag(TIN_ORE, ModBlocks.TIN_ORE);
    appendToTag(Tags.Items.ORES, AMETHYST_ORE, COPPER_ORE, LEAD_ORE, QUICKSILVER_ORE, SILVER_ORE, TIN_ORE);

    addItemsToTag(AUBERGINE, ModItems.AUBERGINE);
    appendToTag(SF_EGGPLANT, AUBERGINE);

    addItemsToTag(CARROT, () -> Items.CARROT);
    appendToTag(SF_CARROT, CARROT);

    addItemsToTag(BEETROOT, () -> Items.BEETROOT);
    appendToTag(SF_BEETROOT, BEETROOT);

    addItemsToTag(Tags.Items.STRING, ModItems.SILK_THREAD);

    addItemsToTag(AMETHYST_BLOCK, ModBlocks.AMETHYST_BLOCK);
    addItemsToTag(COPPER_BLOCK, ModBlocks.COPPER_BLOCK);
    addItemsToTag(LEAD_BLOCK, ModBlocks.LEAD_BLOCK);
    addItemsToTag(QUICKSILVER_BLOCK, ModBlocks.QUICKSILVER_BLOCK);
    addItemsToTag(SILVER_BLOCK, ModBlocks.SILVER_BLOCK);
    addItemsToTag(TIN_BLOCK, ModBlocks.TIN_BLOCK);
    addItemsToTag(SLIME_BLOCK, () -> Items.SLIME_BLOCK);
    appendToTag(Tags.Items.STORAGE_BLOCKS, AMETHYST_BLOCK, COPPER_BLOCK, LEAD_BLOCK, QUICKSILVER_BLOCK, SILVER_BLOCK, TIN_BLOCK, SLIME_BLOCK);

    addItemsToTag(SOFT_STONE, ModBlocks.SOFT_STONE);
    addItemsToTag(CRACKED_SOFT_STONE, ModBlocks.CRACKED_STONE, ModBlocks.WEATHERED_STONE);
    appendToTag(Tags.Items.STONE, SOFT_STONE, CRACKED_SOFT_STONE);

    addItemsToTag(WEATHERED_OBSIDIAN, ModBlocks.WEATHERED_OBSIDIAN);
    addItemsToTag(SMOOTH_OBSIDIAN, ModBlocks.SMOOTH_OBSIDIAN);
    appendToTag(Tags.Items.OBSIDIAN, WEATHERED_OBSIDIAN, SMOOTH_OBSIDIAN);

    addItemsToTag(SLIME, () -> Items.SLIME_BALL);

    addItemsToTag(AMETHYST_GEM, ModItems.AMETHYST_GEM);
    appendToTag(Tags.Items.GEMS, AMETHYST_GEM);

    addItemsToTag(COPPER_INGOT, ModItems.COPPER_INGOT);
    addItemsToTag(LEAD_INGOT, ModItems.LEAD_INGOT);
    addItemsToTag(QUICKSILVER_INGOT, ModItems.QUICKSILVER_INGOT);
    addItemsToTag(SILVER_INGOT, ModItems.SILVER_INGOT);
    addItemsToTag(TIN_INGOT, ModItems.TIN_INGOT);
    appendToTag(Tags.Items.INGOTS, COPPER_INGOT, LEAD_INGOT, QUICKSILVER_INGOT, SILVER_INGOT, TIN_INGOT);

    addItemsToTag(COPPER_NUGGET, ModItems.COPPER_NUGGET);
    addItemsToTag(LEAD_NUGGET, ModItems.LEAD_NUGGET);
    addItemsToTag(QUICKSILVER_NUGGET, ModItems.QUICKSILVER_NUGGET);
    addItemsToTag(SILVER_NUGGET, ModItems.SILVER_NUGGET);
    addItemsToTag(TIN_NUGGET, ModItems.TIN_NUGGET);
    appendToTag(Tags.Items.NUGGETS, COPPER_NUGGET, LEAD_NUGGET, QUICKSILVER_NUGGET, SILVER_NUGGET, TIN_NUGGET);

    addItemsToTag(COPPER_DUST, ModItems.COPPER_DUST);
    addItemsToTag(LEAD_DUST, ModItems.LEAD_DUST);
    addItemsToTag(QUICKSILVER_DUST, ModItems.QUICKSILVER_DUST);
    addItemsToTag(SILVER_DUST, ModItems.SILVER_DUST);
    addItemsToTag(TIN_DUST, ModItems.TIN_DUST);
    addItemsToTag(GOLD_DUST, ModItems.GOLD_DUST);
    addItemsToTag(IRON_DUST, ModItems.IRON_DUST);
    appendToTag(Tags.Items.DUSTS, COPPER_DUST, LEAD_DUST, QUICKSILVER_DUST, SILVER_DUST, TIN_DUST, GOLD_DUST, IRON_DUST);

    addItemsToTag(SWORDS, ModItems.AMETHYST_SWORD, ModItems.CACTUS_SWORD, ModItems.COPPER_SWORD, ModItems.LEAD_SWORD, ModItems.QUICKSILVER_SWORD, ModItems.SILVER_SWORD, ModItems.TIN_SWORD);

    addItemsToTag(KNIVES, ModItems.AMETHYST_KNIFE, ModItems.CACTUS_KNIFE, ModItems.COPPER_KNIFE, ModItems.DIAMOND_KNIFE, ModItems.GOLD_KNIFE, ModItems.IRON_KNIFE, ModItems.LEAD_KNIFE, ModItems.QUICKSILVER_KNIFE, ModItems.SILVER_KNIFE, ModItems.STONE_KNIFE, ModItems.TIN_KNIFE, ModItems.WOODEN_KNIFE);

    addItemsToTag(GEMS, ModItems.AMETHYST_GEM, () -> Items.DIAMOND);

    addItemsToTag(VEGETABLES, () -> Items.CARROT, () -> Items.BEETROOT);
    addItemsToTag(COOKED_VEGETABLES, ModItems.COOKED_BEETROOT, ModItems.COOKED_CARROT, () -> Items.BAKED_POTATO);

    addItemsToTag(SILVER_ITEMS, ModItems.SILVER_SWORD, ModItems.SILVER_KNIFE, ModItems.SILVER_AXE, ModItems.SILVER_HOE, ModItems.SILVER_PICKAXE, ModItems.SILVER_SHOVEL, ModItems.SILVER_BOOTS, ModItems.SILVER_CHESTPLATE, ModItems.SILVER_HELMET, ModItems.SILVER_LEGGINGS, ModItems.SILVER_SPEAR);
    addItemsToTag(COPPER_ITEMS, ModItems.COPPER_SWORD, ModItems.COPPER_KNIFE, ModItems.COPPER_AXE, ModItems.COPPER_HOE, ModItems.COPPER_PICKAXE, ModItems.COPPER_SHOVEL, ModItems.COPPER_BOOTS, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_HELMET, ModItems.COPPER_LEGGINGS, ModItems.COPPER_SPEAR);
    addItemsToTag(QUICKSILVER_ITEMS, ModItems.QUICKSILVER_SWORD, ModItems.QUICKSILVER_KNIFE, ModItems.QUICKSILVER_AXE, ModItems.QUICKSILVER_HOE, ModItems.QUICKSILVER_PICKAXE, ModItems.QUICKSILVER_SHOVEL, ModItems.QUICKSILVER_BOOTS, ModItems.QUICKSILVER_CHESTPLATE, ModItems.QUICKSILVER_HELMET, ModItems.QUICKSILVER_LEGGINGS, ModItems.QUICKSILVER_SPEAR);
    addItemsToTag(TIN_ITEMS, ModItems.TIN_SWORD, ModItems.TIN_KNIFE, ModItems.TIN_AXE, ModItems.TIN_HOE, ModItems.TIN_PICKAXE, ModItems.TIN_SHOVEL, ModItems.TIN_BOOTS, ModItems.TIN_CHESTPLATE, ModItems.TIN_HELMET, ModItems.TIN_LEGGINGS, ModItems.TIN_SPEAR);
    addItemsToTag(LEAD_ITEMS, ModItems.LEAD_SWORD, ModItems.LEAD_KNIFE, ModItems.LEAD_AXE, ModItems.LEAD_HOE, ModItems.LEAD_PICKAXE, ModItems.LEAD_SHOVEL, ModItems.LEAD_BOOTS, ModItems.LEAD_CHESTPLATE, ModItems.LEAD_HELMET, ModItems.LEAD_LEGGINGS, ModItems.LEAD_SPEAR);

    copy(Blocks.FENCES, FENCES);
    copy(Blocks.SLABS, SLABS);
    copy(Blocks.WALLS, WALLS);
    copy(Blocks.STAIRS, STAIRS);

    copy(Blocks.WOODEN_FENCES, WOODEN_FENCES);
    copy(Blocks.WOODEN_SLABS, WOODEN_SLABS);
    copy(Blocks.WOODEN_STAIRS, WOODEN_STAIRS);

    copy(Blocks.LOGS, LOGS);
    copy(Blocks.PLANKS, PLANKS);

    copy(Blocks.PURPUR, PURPUR);
    copy(Blocks.NETHER_BRICKS, NETHER_BRICKS);
    copy(Blocks.RED_NETHER_BRICKS, RED_NETHER_BRICKS);
    copy(Blocks.TERRACOTTA, TERRACOTTA);
  }
}
