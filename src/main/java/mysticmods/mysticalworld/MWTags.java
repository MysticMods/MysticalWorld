package mysticmods.mysticalworld;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.SerializationTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class MWTags {
  public static class Blocks extends MWTags {
    public static Tags.IOptionalNamedTag<Block> PURPUR = compatTag("purpur");
    public static Tags.IOptionalNamedTag<Block> NETHER_BRICKS = compatTag("nether_bricks");
    public static Tags.IOptionalNamedTag<Block> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static Tags.IOptionalNamedTag<Block> TERRACOTTA = compatTag("terracotta");
    public static Tags.IOptionalNamedTag<Block> CROPS = compatTag("crops");
    public static Tags.IOptionalNamedTag<Block> AUBERGINE_CROP = compatTag("crops/aubergine");
    public static Tags.IOptionalNamedTag<Block> EGGPLANT_CROP = compatTag("crops/eggplant");

    public static Tags.IOptionalNamedTag<Block> QUARTZ_ORE = compatTag("ores/quartz");
    public static Tags.IOptionalNamedTag<Block> SAPPHIRE_ORE = compatTag("ores/sapphire");
    public static Tags.IOptionalNamedTag<Block> COPPER_ORE = compatTag("ores/copper");
    public static Tags.IOptionalNamedTag<Block> LEAD_ORE = compatTag("ores/lead");
    public static Tags.IOptionalNamedTag<Block> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static Tags.IOptionalNamedTag<Block> SILVER_ORE = compatTag("ores/silver");
    public static Tags.IOptionalNamedTag<Block> TIN_ORE = compatTag("ores/tin");

    public static Tags.IOptionalNamedTag<Block> SAPPHIRE_STORAGE = compatTag("storage_blocks/sapphire");
    public static Tags.IOptionalNamedTag<Block> COPPER_STORAGE = compatTag("storage_blocks/copper");
    public static Tags.IOptionalNamedTag<Block> LEAD_STORAGE = compatTag("storage_blocks/lead");
    public static Tags.IOptionalNamedTag<Block> QUICKSILVER_STORAGE = compatTag("storage_blocks/quicksilver");
    public static Tags.IOptionalNamedTag<Block> SILVER_STORAGE = compatTag("storage_blocks/silver");
    public static Tags.IOptionalNamedTag<Block> TIN_STORAGE = compatTag("storage_blocks/tin");
    public static Tags.IOptionalNamedTag<Block> PEARL_STORAGE = compatTag("storage_blocks/pearl");
    public static Tags.IOptionalNamedTag<Block> MUSHROOM_BLOCKS = compatTag("mushroom_blocks");

    public static Tags.IOptionalNamedTag<Block> BASE_STONE_GRANITE = compatTag("base_stone_granite");

    static Tags.IOptionalNamedTag<Block> modTag(String name) {
      return BlockTags.createOptional(new ResourceLocation(MysticalWorld.MODID, name));
    }

    static Tags.IOptionalNamedTag<Block> compatTag(String name) {
      return BlockTags.createOptional(new ResourceLocation("forge", name));
    }

    public static Tag<Block> resolve (ResourceLocation location) {
      return SerializationTags.getInstance().getOrEmpty(Registry.BLOCK_REGISTRY).getTag(location);
    }
  }

  public static class Items extends MWTags {
    public static Tags.IOptionalNamedTag<Item> SWORDS = compatTag("swords");
    public static Tags.IOptionalNamedTag<Item> KNIVES = modTag("knives");
    public static Tags.IOptionalNamedTag<Item> GEMS = modTag("gems");
    public static Tags.IOptionalNamedTag<Item> VEGETABLES = compatTag("vegetables");
    public static Tags.IOptionalNamedTag<Item> COOKED_VEGETABLES = compatTag("cooked_vegetables");
    public static Tags.IOptionalNamedTag<Item> COOKED_SEAFOOD = compatTag("cooked_seafood");

    public static Tags.IOptionalNamedTag<Item> STONEPETAL = modTag("stonepetal");

    public static Tags.IOptionalNamedTag<Item> AUBERGINE = compatTag("crops/aubergine");
    public static Tags.IOptionalNamedTag<Item> EGGPLANT = compatTag("crops/eggplant");

    public static Tags.IOptionalNamedTag<Item> CARAPACE = modTag("carapace");

    public static Tags.IOptionalNamedTag<Item> COPPER_ITEMS = modTag("copper_items");
    public static Tags.IOptionalNamedTag<Item> LEAD_ITEMS = modTag("lead_items");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_ITEMS = modTag("quicksilver_items");
    public static Tags.IOptionalNamedTag<Item> SILVER_ITEMS = modTag("silver_items");
    public static Tags.IOptionalNamedTag<Item> TIN_ITEMS = modTag("tin_items");
    public static Tags.IOptionalNamedTag<Item> QUARTZ_ORE = compatTag("ores/quartz");
    public static Tags.IOptionalNamedTag<Item> SAPPHIRE_ORE = compatTag("ores/sapphire");
    public static Tags.IOptionalNamedTag<Item> COPPER_ORE = compatTag("ores/copper");
    public static Tags.IOptionalNamedTag<Item> LEAD_ORE = compatTag("ores/lead");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_ORE = compatTag("ores/quicksilver");
    public static Tags.IOptionalNamedTag<Item> SILVER_ORE = compatTag("ores/silver");
    public static Tags.IOptionalNamedTag<Item> TIN_ORE = compatTag("ores/tin");
    public static Tags.IOptionalNamedTag<Item> SAPPHIRE_BLOCK = compatTag("storage_blocks/sapphire");
    public static Tags.IOptionalNamedTag<Item> COPPER_BLOCK = compatTag("storage_blocks/copper");
    public static Tags.IOptionalNamedTag<Item> LEAD_BLOCK = compatTag("storage_blocks/lead");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_BLOCK = compatTag("storage_blocks/quicksilver");
    public static Tags.IOptionalNamedTag<Item> SILVER_BLOCK = compatTag("storage_blocks/silver");
    public static Tags.IOptionalNamedTag<Item> TIN_BLOCK = compatTag("storage_blocks/tin");
    public static Tags.IOptionalNamedTag<Item> PEARL_BLOCK = compatTag("storage_blocks/pearl");
    public static Tags.IOptionalNamedTag<Item> SLIME_BLOCK = compatTag("storage_blocks/slime");
    public static Tags.IOptionalNamedTag<Item> SLIME = compatTag("slime");
    public static Tags.IOptionalNamedTag<Item> PEARL_GEM = compatTag("gems/pearl");
    public static Tags.IOptionalNamedTag<Item> SAPPHIRE_GEM = compatTag("gems/sapphire");
    public static Tags.IOptionalNamedTag<Item> COPPER_INGOT = compatTag("ingots/copper");
    public static Tags.IOptionalNamedTag<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_INGOT = compatTag("ingots/quicksilver");
    public static Tags.IOptionalNamedTag<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static Tags.IOptionalNamedTag<Item> TIN_INGOT = compatTag("ingots/tin");
    public static Tags.IOptionalNamedTag<Item> COPPER_NUGGET = compatTag("nuggets/copper");
    public static Tags.IOptionalNamedTag<Item> LEAD_NUGGET = compatTag("nuggets/lead");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_NUGGET = compatTag("nuggets/quicksilver");
    public static Tags.IOptionalNamedTag<Item> SILVER_NUGGET = compatTag("nuggets/silver");
    public static Tags.IOptionalNamedTag<Item> TIN_NUGGET = compatTag("nuggets/tin");
    public static Tags.IOptionalNamedTag<Item> COPPER_DUST = compatTag("dusts/copper");
    public static Tags.IOptionalNamedTag<Item> LEAD_DUST = compatTag("dusts/lead");
    public static Tags.IOptionalNamedTag<Item> QUICKSILVER_DUST = compatTag("dusts/quicksilver");
    public static Tags.IOptionalNamedTag<Item> SILVER_DUST = compatTag("dusts/silver");
    public static Tags.IOptionalNamedTag<Item> TIN_DUST = compatTag("dusts/tin");
    public static Tags.IOptionalNamedTag<Item> GOLD_DUST = compatTag("dusts/gold");
    public static Tags.IOptionalNamedTag<Item> IRON_DUST = compatTag("dusts/iron");

    public static Tags.IOptionalNamedTag<Item> SOFT_STONE = modTag("soft_stone");
    /*    public static Tags.IOptionalNamedTag<Item> SOFT_OBSIDIAN = modTag("soft_obsidian");*/

    public static Tags.IOptionalNamedTag<Item> PURPUR = compatTag("purpur");
    public static Tags.IOptionalNamedTag<Item> NETHER_BRICKS = compatTag("nether_bricks");
    public static Tags.IOptionalNamedTag<Item> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static Tags.IOptionalNamedTag<Item> TERRACOTTA = compatTag("terracotta");

    public static Tags.IOptionalNamedTag<Item> MUSHROOM_BLOCKS = compatTag("mushroom_blocks");

    public static Tags.IOptionalNamedTag<Item> FORGE_KNIVES = compatTag("tools/knife");
    public static Tags.IOptionalNamedTag<Item> SEEDS = compatTag("seeds");
    public static Tags.IOptionalNamedTag<Item> SUGARS = modTag("sugars");
    public static Tags.IOptionalNamedTag<Item> PROTEINS = modTag("proteins");

    static Tags.IOptionalNamedTag<Item> modTag(String name) {
      return ItemTags.createOptional(new ResourceLocation(MysticalWorld.MODID, name));
    }

    static Tags.IOptionalNamedTag<Item> compatTag(String name) {
      return ItemTags.createOptional(new ResourceLocation("forge", name));
    }

    public static Tag<Item> resolve (ResourceLocation location) {
      return SerializationTags.getInstance().getOrEmpty(Registry.ITEM_REGISTRY).getTag(location);
    }
  }

  public static class Potions extends MWTags {
    public static Tag.Named<Potion> RANDOM_BLACKLIST = compatTag("random_potion_blacklist");

    static Tag.Named<Potion> modTag (String name) {
      return ForgeTagHandler.createOptionalTag(ForgeRegistries.POTIONS, new ResourceLocation(MysticalWorld.MODID, name));
    }

    static Tag.Named<Potion> compatTag (String name) {
      return ForgeTagHandler.createOptionalTag(ForgeRegistries.POTIONS, new ResourceLocation("forge", name));
    }
  }
}
