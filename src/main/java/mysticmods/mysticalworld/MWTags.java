package mysticmods.mysticalworld;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class MWTags {
  public static class Blocks extends MWTags {
    public static TagKey<Block> PURPUR = compatTag("purpur");
    public static TagKey<Block> NETHER_BRICKS = compatTag("nether_bricks");
    public static TagKey<Block> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static TagKey<Block> TERRACOTTA = compatTag("terracotta");
    public static TagKey<Block> CROPS = compatTag("crops");
    public static TagKey<Block> AUBERGINE_CROP = compatTag("crops/aubergine");
    public static TagKey<Block> EGGPLANT_CROP = compatTag("crops/eggplant");

    public static TagKey<Block> QUARTZ_ORE = compatTag("ores/quartz");
    public static TagKey<Block> SAPPHIRE_ORE = compatTag("ores/sapphire");
    public static TagKey<Block> LEAD_ORE = compatTag("ores/lead");
    public static TagKey<Block> SILVER_ORE = compatTag("ores/silver");
    public static TagKey<Block> TIN_ORE = compatTag("ores/tin");

    public static TagKey<Block> SAPPHIRE_STORAGE = compatTag("storage_blocks/sapphire");
    public static TagKey<Block> LEAD_STORAGE = compatTag("storage_blocks/lead");
    public static TagKey<Block> RAW_LEAD_STORAGE = compatTag("storage_blocks/raw_lead");
    public static TagKey<Block> ORICHALCUM_STORAGE = compatTag("storage_blocks/orichalcum");
    public static TagKey<Block> SILVER_STORAGE = compatTag("storage_blocks/silver");
    public static TagKey<Block> RAW_SILVER_STORAGE = compatTag("storage_blocks/raw_silver");
    public static TagKey<Block> TIN_STORAGE = compatTag("storage_blocks/tin");
    public static TagKey<Block> RAW_TIN_STORAGE = compatTag("storage_blocks/raw_tin");
    public static TagKey<Block> PEARL_STORAGE = compatTag("storage_blocks/pearl");
    public static TagKey<Block> MUSHROOM_BLOCKS = compatTag("mushroom_blocks");
    public static TagKey<Block> CARAPACE_STORAGE = compatTag("storage_blocks/carapace");

    public static TagKey<Block> BASE_STONE_GRANITE = compatTag("base_stone_granite");

    public static TagKey<Block> SOFT_STONE = modTag("soft_stone");
    public static TagKey<Block> STONEPETAL = compatTag("flowers/stonepetal");

    public static TagKey<Block> SUPPORTS_WILD_AUBERGINE = modTag("supports_wild_aubergine");
    public static TagKey<Block> SUPPORTS_STONEPETAL = modTag("supports_stonepetal");
    public static TagKey<Block> SUPPORTS_GALL_APPLE = modTag("supports_gall_apple");
    public static TagKey<Block> SUPPORTS_LAVA_CAT_SPAWN = modTag("supports_lava_cat_spawn");

    static TagKey<Block> modTag(String name) {
      return BlockTags.create(new ResourceLocation(MysticalWorld.MODID, name));
    }

    static TagKey<Block> compatTag(String name) {
      return BlockTags.create(new ResourceLocation("forge", name));
    }
  }

  public static class Items extends MWTags {
    public static TagKey<Item> SWORDS = compatTag("swords");
    public static TagKey<Item> KNIVES = modTag("knives");
    public static TagKey<Item> GEMS = modTag("gems");
    public static TagKey<Item> VEGETABLES = compatTag("vegetables");
    public static TagKey<Item> COOKED_VEGETABLES = compatTag("cooked_vegetables");
    public static TagKey<Item> COOKED_SEAFOOD = compatTag("cooked_seafood");

    public static TagKey<Item> STONEPETAL = modTag("stonepetal");

    public static TagKey<Item> AUBERGINE = compatTag("crops/aubergine");
    public static TagKey<Item> EGGPLANT = compatTag("crops/eggplant");

    public static TagKey<Item> CARAPACE = modTag("carapace");

    public static TagKey<Item> COPPER_ITEMS = modTag("copper_items");
    public static TagKey<Item> LEAD_ITEMS = modTag("lead_items");
    public static TagKey<Item> ORICHALCUM_ITEMS = modTag("orichalcum_items");
    public static TagKey<Item> SILVER_ITEMS = modTag("silver_items");
    public static TagKey<Item> TIN_ITEMS = modTag("tin_items");
    public static TagKey<Item> QUARTZ_ORE = compatTag("ores/quartz");
    public static TagKey<Item> SAPPHIRE_ORE = compatTag("ores/sapphire");
    public static TagKey<Item> LEAD_ORE = compatTag("ores/lead");
    public static TagKey<Item> SILVER_ORE = compatTag("ores/silver");
    public static TagKey<Item> TIN_ORE = compatTag("ores/tin");
    public static TagKey<Item> SAPPHIRE_BLOCK = compatTag("storage_blocks/sapphire");
    public static TagKey<Item> LEAD_BLOCK = compatTag("storage_blocks/lead");
    public static TagKey<Item> ORICHALCUM_BLOCK = compatTag("storage_blocks/orichalcum");
    public static TagKey<Item> SILVER_BLOCK = compatTag("storage_blocks/silver");
    public static TagKey<Item> TIN_BLOCK = compatTag("storage_blocks/tin");
    public static TagKey<Item> RAW_LEAD_STORAGE = compatTag("storage_blocks/raw_lead");
    public static TagKey<Item> RAW_SILVER_STORAGE = compatTag("storage_blocks/raw_silver");
    public static TagKey<Item> RAW_TIN_STORAGE = compatTag("storage_blocks/raw_tin");
    public static TagKey<Item> PEARL_BLOCK = compatTag("storage_blocks/pearl");
    public static TagKey<Item> CARAPACE_BLOCK = compatTag("storage_blocks/carapace");
    public static TagKey<Item> SLIME_BLOCK = compatTag("storage_blocks/slime");
    public static TagKey<Item> SLIME = compatTag("slime");
    public static TagKey<Item> PEARL_GEM = compatTag("gems/pearl");
    public static TagKey<Item> SAPPHIRE_GEM = compatTag("gems/sapphire");
    public static TagKey<Item> LEAD_INGOT = compatTag("ingots/lead");
    public static TagKey<Item> ORICHALCUM_INGOT = compatTag("ingots/orichalcum");
    public static TagKey<Item> SILVER_INGOT = compatTag("ingots/silver");
    public static TagKey<Item> TIN_INGOT = compatTag("ingots/tin");
    public static TagKey<Item> COPPER_NUGGET = compatTag("nuggets/copper");
    public static TagKey<Item> LEAD_NUGGET = compatTag("nuggets/lead");
    public static TagKey<Item> ORICHALCUM_NUGGET = compatTag("nuggets/orichalcum");
    public static TagKey<Item> SILVER_NUGGET = compatTag("nuggets/silver");
    public static TagKey<Item> TIN_NUGGET = compatTag("nuggets/tin");
    public static TagKey<Item> COPPER_DUST = compatTag("dusts/copper");
    public static TagKey<Item> LEAD_DUST = compatTag("dusts/lead");
    public static TagKey<Item> ORICHALCUM_DUST = compatTag("dusts/orichalcum");
    public static TagKey<Item> SILVER_DUST = compatTag("dusts/silver");
    public static TagKey<Item> TIN_DUST = compatTag("dusts/tin");
    public static TagKey<Item> GOLD_DUST = compatTag("dusts/gold");
    public static TagKey<Item> IRON_DUST = compatTag("dusts/iron");
    public static TagKey<Item> RAW_LEAD = compatTag("raw_materials/lead");
    public static TagKey<Item> RAW_SILVER = compatTag("raw_materials/silver");
    public static TagKey<Item> RAW_ORICHALCUM = compatTag("raw_materials/orichalcum");
    public static TagKey<Item> RAW_TIN = compatTag("raw_materials/tin");

    public static TagKey<Item> SOFT_STONE = modTag("soft_stone");

    public static TagKey<Item> PURPUR = compatTag("purpur");
    public static TagKey<Item> NETHER_BRICKS = compatTag("nether_bricks");
    public static TagKey<Item> RED_NETHER_BRICKS = compatTag("red_nether_bricks");
    public static TagKey<Item> TERRACOTTA = compatTag("terracotta");

    public static TagKey<Item> MUSHROOM_BLOCKS = compatTag("mushroom_blocks");

    public static TagKey<Item> FORGE_KNIVES = compatTag("tools/knife");
    public static TagKey<Item> SEEDS = compatTag("seeds");
    public static TagKey<Item> SUGARS = modTag("sugars");
    public static TagKey<Item> PROTEINS = modTag("proteins");

    public static TagKey<Item> BOTTLES = compatTag("bottles");

    public static TagKey<Item> OWL_FOOD = modTag("owl_food");

    static TagKey<Item> modTag(String name) {
      return ItemTags.create(new ResourceLocation(MysticalWorld.MODID, name));
    }

    static TagKey<Item> compatTag(String name) {
      return ItemTags.create(new ResourceLocation("forge", name));
    }
  }

  public static class Potions extends MWTags {
    public static TagKey<Potion> RANDOM_BLACKLIST = compatTag("random_potion_blacklist");

    static TagKey<Potion> modTag(String name) {
      return TagKey.create(Registry.POTION_REGISTRY, new ResourceLocation(MysticalWorld.MODID, name));
    }

    static TagKey<Potion> compatTag(String name) {
      return TagKey.create(Registry.POTION_REGISTRY, new ResourceLocation("forge", name));
    }
  }

  public static class Biomes extends MWTags {
    public static TagKey<Biome> HAS_HUT = modTag("has_structure/hut");
    public static TagKey<Biome> HAS_RUINED_HUT = modTag("has_structure/ruined_hut");
    public static TagKey<Biome> HAS_BARROW = modTag("has_structure/barrow");
    public static TagKey<Biome> HAS_DESERT_HUT = modTag("has_structure/desert_hut");

    static TagKey<Biome> modTag(String name) {
      return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MysticalWorld.MODID, name));
    }

    static TagKey<Biome> compatTag(String name) {
      return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("forge", name));
    }
  }


}
