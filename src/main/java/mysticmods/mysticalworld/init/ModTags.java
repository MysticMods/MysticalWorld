package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import mysticmods.mysticalworld.MWTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import static mysticmods.mysticalworld.MysticalWorld.REGISTRATE;

@SuppressWarnings("unchecked")
public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, b -> {
      b.tag(Tags.Items.ORES).addTags(MWTags.Items.SAPPHIRE_ORE, MWTags.Items.LEAD_ORE, MWTags.Items.SILVER_ORE, MWTags.Items.TIN_ORE, MWTags.Items.QUARTZ_ORE);
      b.tag(Tags.Items.INGOTS).addTags(MWTags.Items.LEAD_INGOT, MWTags.Items.ORICHALCUM_INGOT, MWTags.Items.SILVER_INGOT, MWTags.Items.TIN_INGOT);
      b.tag(Tags.Items.NUGGETS).addTags(MWTags.Items.COPPER_NUGGET, MWTags.Items.LEAD_NUGGET, MWTags.Items.ORICHALCUM_NUGGET, MWTags.Items.SILVER_NUGGET, MWTags.Items.TIN_NUGGET);
      b.tag(Tags.Items.DUSTS).addTags(MWTags.Items.COPPER_DUST, MWTags.Items.LEAD_DUST, MWTags.Items.ORICHALCUM_DUST, MWTags.Items.SILVER_DUST, MWTags.Items.TIN_DUST, MWTags.Items.GOLD_DUST, MWTags.Items.IRON_DUST);
      b.tag(MWTags.Items.EGGPLANT).addTag(MWTags.Items.AUBERGINE);
      b.tag(Tags.Items.STONE).addTag(MWTags.Items.SOFT_STONE);
      b.tag(Tags.Items.GEMS).addTag(MWTags.Items.SAPPHIRE_GEM);
      b.tag(MWTags.Items.FORGE_KNIVES).addTag(MWTags.Items.KNIVES);

      b.tag(MWTags.Items.SLIME_BLOCK).add(Items.SLIME_BLOCK);
      b.tag(MWTags.Items.SLIME).add(Items.SLIME_BALL);
      b.tag(MWTags.Items.GEMS).add(Items.DIAMOND).addTag(MWTags.Items.SAPPHIRE_GEM).addTag(MWTags.Items.PEARL_GEM);
      b.tag(MWTags.Items.VEGETABLES).add(Items.CARROT, Items.BEETROOT);
      b.tag(MWTags.Items.COOKED_VEGETABLES).add(Items.BAKED_POTATO);
      b.tag(MWTags.Items.COOKED_SEAFOOD).add(Items.COOKED_COD, Items.COOKED_SALMON);

      b.tag(MWTags.Items.BOTTLES).add(Items.GLASS_BOTTLE);

      b.tag(MWTags.Items.SUGARS).add(Items.SUGAR, Items.APPLE, Items.COOKIE, Items.MELON_SLICE, Items.CAKE, Items.PUMPKIN_PIE);
      b.tag(MWTags.Items.PROTEINS).add(Items.ROTTEN_FLESH, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COD, Items.SALMON, Items.RABBIT, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.MUTTON, Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.COOKED_COD, Items.COOKED_SALMON, Items.EGG);

      b.copy(MWTags.Blocks.PURPUR, MWTags.Items.PURPUR);
      b.copy(MWTags.Blocks.NETHER_BRICKS, MWTags.Items.NETHER_BRICKS);
      b.copy(MWTags.Blocks.RED_NETHER_BRICKS, MWTags.Items.RED_NETHER_BRICKS);
      b.copy(MWTags.Blocks.TERRACOTTA, MWTags.Items.TERRACOTTA);
      b.copy(MWTags.Blocks.MUSHROOM_BLOCKS, MWTags.Items.MUSHROOM_BLOCKS);
      b.copy(MWTags.Blocks.RAW_TIN_STORAGE, MWTags.Items.RAW_TIN_STORAGE);
      b.copy(MWTags.Blocks.RAW_LEAD_STORAGE, MWTags.Items.RAW_LEAD_STORAGE);
      b.copy(MWTags.Blocks.RAW_SILVER_STORAGE, MWTags.Items.RAW_SILVER_STORAGE);
    });

    REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, b -> {
      b.tag(Tags.Blocks.ORES).addTags(MWTags.Blocks.SAPPHIRE_ORE, MWTags.Blocks.LEAD_ORE, MWTags.Blocks.SILVER_ORE, MWTags.Blocks.TIN_ORE, MWTags.Blocks.QUARTZ_ORE);
      b.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(MWTags.Blocks.SAPPHIRE_STORAGE, MWTags.Blocks.LEAD_STORAGE, MWTags.Blocks.ORICHALCUM_STORAGE, MWTags.Blocks.SILVER_STORAGE, MWTags.Blocks.TIN_STORAGE, MWTags.Blocks.PEARL_STORAGE);

      b.tag(MWTags.Blocks.PURPUR).add(Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR);
      b.tag(MWTags.Blocks.NETHER_BRICKS).add(Blocks.NETHER_BRICKS);
      b.tag(MWTags.Blocks.RED_NETHER_BRICKS).add(Blocks.RED_NETHER_BRICKS);
      b.tag(MWTags.Blocks.TERRACOTTA).add(Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.TERRACOTTA);
      b.tag(MWTags.Blocks.MUSHROOM_BLOCKS).add(Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
      b.tag(MWTags.Blocks.BASE_STONE_GRANITE).add(Blocks.GRANITE);
      b.tag(MWTags.Blocks.EGGPLANT_CROP).addTag(MWTags.Blocks.AUBERGINE_CROP);
      b.tag(MWTags.Blocks.SUPPORTS_WILD_AUBERGINE).add(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND);
      b.tag(MWTags.Blocks.SUPPORTS_STONEPETAL).add(Blocks.GRAVEL).addTag(Tags.Blocks.STONE).addTag(Tags.Blocks.ORES);
      b.tag(MWTags.Blocks.SUPPORTS_GALL_APPLE).add(Blocks.OAK_LOG, Blocks.OAK_WOOD, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD);
      b.tag(MWTags.Blocks.SUPPORTS_LAVA_CAT_SPAWN).add(Blocks.NETHERRACK, Blocks.OBSIDIAN, Blocks.MAGMA_BLOCK, Blocks.SOUL_SAND, Blocks.SOUL_SOIL, Blocks.NETHER_BRICKS, Blocks.BONE_BLOCK, Blocks.NETHER_WART_BLOCK, Blocks.NETHER_GOLD_ORE, Blocks.ANCIENT_DEBRIS, Blocks.NETHER_QUARTZ_ORE, Blocks.RED_NETHER_BRICKS, Blocks.CHISELED_NETHER_BRICKS, Blocks.BASALT, Blocks.POLISHED_BASALT, Blocks.CRACKED_NETHER_BRICKS, Blocks.BLACKSTONE, Blocks.CHISELED_POLISHED_BLACKSTONE, Blocks.GILDED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.CRIMSON_NYLIUM, Blocks.CRIMSON_HYPHAE, Blocks.WARPED_WART_BLOCK, Blocks.GRAVEL, Blocks.GLOWSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.WARPED_NYLIUM, Blocks.SHROOMLIGHT); // TODO: Subconvert these into tags
    });
  }

  public static void load() {

  }
}
