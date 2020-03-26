package epicsquid.mysticalworld;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.function.Function;

public class MWTags {
  public static class Blocks extends MWTags {
    public static Tag<Block> LOGS = modTag("logs");
    public static Tag<Block> PLANKS = modTag("planks");

    public static Tag<Block> SOFT_STONE = modTag("soft_stone");
    public static Tag<Block> CRACKED_SOFT_STONE = modTag("cracked_stone");

    public static Tag<Block> SMOOTH_OBSIDIAN = modTag("smooth_obsidian");
    public static Tag<Block> WEATHERED_OBSIDIAN = modTag("weathered_obsidian");

    public static Tag<Block> WOODEN_SLABS = modTag("wooden_slabs");
    public static Tag<Block> WOODEN_STAIRS = modTag("wooden_stairs");
    public static Tag<Block> WOODEN_FENCES = modTag("wooden_fences");

    public static Tag<Block> SLABS = modTag("slabs");
    public static Tag<Block> STAIRS = modTag("stairs");
    public static Tag<Block> FENCES = modTag("fences");
    public static Tag<Block> WALLS = modTag("walls");

    public static Tag<Block> PURPUR = compatTag("purpur");
    public static Tag<Block> NETHER_BRICKS = compatTag("nether_bricks");
    public static Tag<Block> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static Tag<Block> TERRACOTTA = compatTag("terracotta");

    public static Tag<Block> AMETHYST_ORE = compatTag("ores/amethyst");
    public static Tag<Block> COPPER_ORE = compatTag("ores/copper");
    public static Tag<Block> LEAD_ORE = compatTag("ores/lead");
    public static Tag<Block> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static Tag<Block> SILVER_ORE = compatTag("ores/silver");
    public static Tag<Block> TIN_ORE = compatTag("ores/tin");

    public static Tag<Block> AMETHYST_STORAGE = compatTag("storage_blocks/amethyst");
    public static Tag<Block> COPPER_STORAGE = compatTag("storage_blocks/copper");
    public static Tag<Block> LEAD_STORAGE = compatTag("storage_blocks/lead");
    public static Tag<Block> QUICKSILVER_STORAGE = compatTag("storage_blocks/quicksilver");
    public static Tag<Block> SILVER_STORAGE = compatTag("storage_blocks/silver");
    public static Tag<Block> TIN_STORAGE = compatTag("storage_blocks/tin");

    static Tag<Block> tag(String modid, String name) {
      return tag(BlockTags.Wrapper::new, modid, name);
    }

    static Tag<Block> modTag(String name) {
      return tag(MysticalWorld.MODID, name);
    }

    static Tag<Block> compatTag(String name) {
      return tag("forge", name);
    }
  }

  public static class Items extends MWTags {
    public static Tag<Item> SWORDS = compatTag("swords");
    public static Tag<Item> KNIVES = modTag("knives");
    public static Tag<Item> GEMS = compatTag("gems");
    public static Tag<Item> VEGETABLES = compatTag("vegetables");
    public static Tag<Item> CROPS = compatTag("crops");
    public static Tag<Item> COOKED_VEGETABLES = compatTag("cooked_vegetables");

    public static Tag<Item> AUBERGINE = compatTag("crops/aubergine");
    public static Tag<Item> EGGPLANT = compatTag("crops/eggplant");

    public static Tag<Item> COPPER_ITEMS = modTag("copper_items");
    public static Tag<Item> LEAD_ITEMS = modTag("lead_items");
    public static Tag<Item> QUICKSILVER_ITEMS = modTag("quicksilver_items");
    public static Tag<Item> SILVER_ITEMS = modTag("silver_items");
    public static Tag<Item> TIN_ITEMS = modTag("tin_items");
    public static Tag<Item> AMETHYST_ORE = compatTag("ores/amethyst");
    public static Tag<Item> COPPER_ORE = compatTag("ores/copper");
    public static Tag<Item> LEAD_ORE = compatTag("ores/lead");
    public static Tag<Item> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static Tag<Item> SILVER_ORE = compatTag("ores/silver");
    public static Tag<Item> TIN_ORE = compatTag("ores/tin");
    public static Tag<Item> AMETHYST_BLOCK = compatTag("storage_blocks/amethyst");
    public static Tag<Item> COPPER_BLOCK = compatTag("storage_blocks/copper");
    public static Tag<Item> LEAD_BLOCK = compatTag("storage_blocks/lead");
    public static Tag<Item> QUICKSILVER_BLOCK = compatTag("storage_blocks/quicksilver");
    public static Tag<Item> SILVER_BLOCK = compatTag("storage_blocks/silver");
    public static Tag<Item> TIN_BLOCK = compatTag("storage_blocks/tin");
    public static Tag<Item> SLIME_BLOCK = compatTag("storage_blocks/slime");
    public static Tag<Item> SLIME = compatTag("slime");
    public static Tag<Item> AMETHYST_GEM = compatTag("gems/amethyst");
    public static Tag<Item> COPPER_INGOT = compatTag("ingots/copper");
    public static Tag<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static Tag<Item> QUICKSILVER_INGOT = compatTag("ingots/quicksilver");
    public static Tag<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static Tag<Item> TIN_INGOT = compatTag("ingots/tin");
    public static Tag<Item> COPPER_NUGGET = compatTag("nuggets/copper");
    public static Tag<Item> LEAD_NUGGET = compatTag("nuggets/lead");
    public static Tag<Item> QUICKSILVER_NUGGET = compatTag("nuggets/quicksilver");
    public static Tag<Item> SILVER_NUGGET = compatTag("nuggets/silver");
    public static Tag<Item> TIN_NUGGET = compatTag("nuggets/tin");
    public static Tag<Item> COPPER_DUST = compatTag("dusts/copper");
    public static Tag<Item> LEAD_DUST = compatTag("dusts/lead");
    public static Tag<Item> QUICKSILVER_DUST = compatTag("dusts/quicksilver");
    public static Tag<Item> SILVER_DUST = compatTag("dusts/silver");
    public static Tag<Item> TIN_DUST = compatTag("dusts/tin");
    public static Tag<Item> GOLD_DUST = compatTag("dusts/gold");
    public static Tag<Item> IRON_DUST = compatTag("dusts/iron");

    public static Tag<Item> SOFT_STONE = modTag("soft_stone");
    public static Tag<Item> SOFT_OBSIDIAN = modTag("soft_obsidian");

    public static Tag<Item> PURPUR = compatTag("purpur");
    public static Tag<Item> NETHER_BRICKS = compatTag("nether_bricks");
    public static Tag<Item> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static Tag<Item> TERRACOTTA = compatTag("terracotta");

    static Tag<Item> tag(String modid, String name) {
      return tag(ItemTags.Wrapper::new, modid, name);
    }

    static Tag<Item> modTag(String name) {
      return tag(MysticalWorld.MODID, name);
    }

    static Tag<Item> mcTag (String name) {
      return tag("minecraft", name);
    }

    static Tag<Item> compatTag(String name) {
      return tag("forge", name);
    }
  }

  static <T extends Tag<?>> T tag(Function<ResourceLocation, T> creator, String modid, String name) {
    return creator.apply(new ResourceLocation(modid, name));
  }

  static <T extends Tag<?>> T modTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, MysticalWorld.MODID, name);
  }

  static <T extends Tag<?>> T compatTag(Function<ResourceLocation, T> creator, String name) {
    return tag(creator, "forge", name);
  }
}
