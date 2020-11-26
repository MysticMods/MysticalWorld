package epicsquid.mysticalworld;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static net.minecraft.tags.ITag.INamedTag;

public class MWTags {
  public static class Blocks extends MWTags {
    public static INamedTag<Block> PURPUR = compatTag("purpur");
    public static INamedTag<Block> NETHER_BRICKS = compatTag("nether_bricks");
    public static INamedTag<Block> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static INamedTag<Block> TERRACOTTA = compatTag("terracotta");

    public static INamedTag<Block> AMETHYST_ORE = compatTag("ores/amethyst");
    public static INamedTag<Block> COPPER_ORE = compatTag("ores/copper");
    public static INamedTag<Block> LEAD_ORE = compatTag("ores/lead");
    public static INamedTag<Block> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static INamedTag<Block> SILVER_ORE = compatTag("ores/silver");
    public static INamedTag<Block> TIN_ORE = compatTag("ores/tin");

    public static INamedTag<Block> AMETHYST_STORAGE = compatTag("storage_blocks/amethyst");
    public static INamedTag<Block> COPPER_STORAGE = compatTag("storage_blocks/copper");
    public static INamedTag<Block> LEAD_STORAGE = compatTag("storage_blocks/lead");
    public static INamedTag<Block> QUICKSILVER_STORAGE = compatTag("storage_blocks/quicksilver");
    public static INamedTag<Block> SILVER_STORAGE = compatTag("storage_blocks/silver");
    public static INamedTag<Block> TIN_STORAGE = compatTag("storage_blocks/tin");

    static INamedTag<Block> modTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation(MysticalWorld.MODID, name));
    }

    static INamedTag<Block> compatTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation("forge", name));
    }
  }

  public static class Items extends MWTags {
    public static INamedTag<Item> SWORDS = compatTag("swords");
    public static INamedTag<Item> KNIVES = modTag("knives");
    public static INamedTag<Item> GEMS = modTag("gems");
    public static INamedTag<Item> VEGETABLES = compatTag("vegetables");
/*    public static INamedTag<Item> CROPS = compatTag("crops");*/
    public static INamedTag<Item> COOKED_VEGETABLES = compatTag("cooked_vegetables");

    public static INamedTag<Item> AUBERGINE = compatTag("crops/aubergine");
    public static INamedTag<Item> EGGPLANT = compatTag("crops/eggplant");

    public static INamedTag<Item> COPPER_ITEMS = modTag("copper_items");
    public static INamedTag<Item> LEAD_ITEMS = modTag("lead_items");
    public static INamedTag<Item> QUICKSILVER_ITEMS = modTag("quicksilver_items");
    public static INamedTag<Item> SILVER_ITEMS = modTag("silver_items");
    public static INamedTag<Item> TIN_ITEMS = modTag("tin_items");
    public static INamedTag<Item> AMETHYST_ORE = compatTag("ores/amethyst");
    public static INamedTag<Item> COPPER_ORE = compatTag("ores/copper");
    public static INamedTag<Item> LEAD_ORE = compatTag("ores/lead");
    public static INamedTag<Item> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static INamedTag<Item> SILVER_ORE = compatTag("ores/silver");
    public static INamedTag<Item> TIN_ORE = compatTag("ores/tin");
    public static INamedTag<Item> AMETHYST_BLOCK = compatTag("storage_blocks/amethyst");
    public static INamedTag<Item> COPPER_BLOCK = compatTag("storage_blocks/copper");
    public static INamedTag<Item> LEAD_BLOCK = compatTag("storage_blocks/lead");
    public static INamedTag<Item> QUICKSILVER_BLOCK = compatTag("storage_blocks/quicksilver");
    public static INamedTag<Item> SILVER_BLOCK = compatTag("storage_blocks/silver");
    public static INamedTag<Item> TIN_BLOCK = compatTag("storage_blocks/tin");
    public static INamedTag<Item> SLIME_BLOCK = compatTag("storage_blocks/slime");
    public static INamedTag<Item> SLIME = compatTag("slime");
    public static INamedTag<Item> AMETHYST_GEM = compatTag("gems/amethyst");
    public static INamedTag<Item> COPPER_INGOT = compatTag("ingots/copper");
    public static INamedTag<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static INamedTag<Item> QUICKSILVER_INGOT = compatTag("ingots/quicksilver");
    public static INamedTag<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static INamedTag<Item> TIN_INGOT = compatTag("ingots/tin");
    public static INamedTag<Item> COPPER_NUGGET = compatTag("nuggets/copper");
    public static INamedTag<Item> LEAD_NUGGET = compatTag("nuggets/lead");
    public static INamedTag<Item> QUICKSILVER_NUGGET = compatTag("nuggets/quicksilver");
    public static INamedTag<Item> SILVER_NUGGET = compatTag("nuggets/silver");
    public static INamedTag<Item> TIN_NUGGET = compatTag("nuggets/tin");
    public static INamedTag<Item> COPPER_DUST = compatTag("dusts/copper");
    public static INamedTag<Item> LEAD_DUST = compatTag("dusts/lead");
    public static INamedTag<Item> QUICKSILVER_DUST = compatTag("dusts/quicksilver");
    public static INamedTag<Item> SILVER_DUST = compatTag("dusts/silver");
    public static INamedTag<Item> TIN_DUST = compatTag("dusts/tin");
    public static INamedTag<Item> GOLD_DUST = compatTag("dusts/gold");
    public static INamedTag<Item> IRON_DUST = compatTag("dusts/iron");

    public static INamedTag<Item> SOFT_STONE = modTag("soft_stone");
/*    public static INamedTag<Item> SOFT_OBSIDIAN = modTag("soft_obsidian");*/

    public static INamedTag<Item> PURPUR = compatTag("purpur");
    public static INamedTag<Item> NETHER_BRICKS = compatTag("nether_bricks");
    public static INamedTag<Item> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static INamedTag<Item> TERRACOTTA = compatTag("terracotta");

    static INamedTag<Item> modTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation(MysticalWorld.MODID, name));
    }

    static INamedTag<Item> compatTag(String name) {
      return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", name));
    }
  }
}
