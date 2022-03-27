package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import mysticmods.mysticalworld.MWTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import static mysticmods.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Block tool types and tiers
@SuppressWarnings("unchecked")
public class ModTags {
  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, b -> {
      b.tag(Tags.Items.ORES).addTags(MWTags.Items.SAPPHIRE_ORE, MWTags.Items.LEAD_ORE, MWTags.Items.SILVER_ORE, MWTags.Items.TIN_ORE, MWTags.Items.QUARTZ_ORE);

      b.tag(MWTags.Items.EGGPLANT).addTag(MWTags.Items.AUBERGINE);

      b.tag(Tags.Items.STRING).add(ModItems.SILK_THREAD.get());

      b.tag(MWTags.Items.SLIME_BLOCK).add(Items.SLIME_BLOCK);

      b.tag(Tags.Items.STONE).addTag(MWTags.Items.SOFT_STONE);

      b.tag(MWTags.Items.SLIME).add(Items.SLIME_BALL);

      b.tag(Tags.Items.GEMS).addTag(MWTags.Items.SAPPHIRE_GEM);

      b.tag(Tags.Items.INGOTS).addTags(MWTags.Items.LEAD_INGOT, MWTags.Items.ORICHALCUM_INGOT, MWTags.Items.SILVER_INGOT, MWTags.Items.TIN_INGOT);
      b.tag(Tags.Items.NUGGETS).addTags(MWTags.Items.COPPER_NUGGET, MWTags.Items.LEAD_NUGGET, MWTags.Items.ORICHALCUM_NUGGET, MWTags.Items.SILVER_NUGGET, MWTags.Items.TIN_NUGGET);
      b.tag(Tags.Items.DUSTS).addTags(MWTags.Items.COPPER_DUST, MWTags.Items.LEAD_DUST, MWTags.Items.ORICHALCUM_DUST, MWTags.Items.SILVER_DUST, MWTags.Items.TIN_DUST, MWTags.Items.GOLD_DUST, MWTags.Items.IRON_DUST);

      b.tag(MWTags.Items.GEMS).add(ModItems.SAPPHIRE_GEM.get(), Items.DIAMOND);

      b.tag(MWTags.Items.VEGETABLES).add(Items.CARROT, Items.BEETROOT);
      b.tag(MWTags.Items.VEGETABLES).add(ModItems.AUBERGINE.get());
      b.tag(MWTags.Items.COOKED_VEGETABLES).add(ModItems.COOKED_BEETROOT.get(), ModItems.COOKED_CARROT.get(), Items.BAKED_POTATO, ModItems.COOKED_AUBERGINE.get());
      b.tag(MWTags.Items.COOKED_SEAFOOD).add(ModItems.COOKED_SQUID.get(), Items.COOKED_COD, Items.COOKED_SALMON);

      b.tag(MWTags.Items.SWORDS).add(ModItems.SAPPHIRE_SWORD.get(), ModItems.CACTUS_SWORD.get(), ModItems.COPPER_SWORD.get(), ModItems.LEAD_SWORD.get(), ModItems.ORICHALCUM_SWORD.get(), ModItems.SILVER_SWORD.get(), ModItems.TIN_SWORD.get());
      b.tag(MWTags.Items.KNIVES).add(ModItems.SAPPHIRE_KNIFE.get(), ModItems.CACTUS_KNIFE.get(), ModItems.COPPER_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLD_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.LEAD_KNIFE.get(), ModItems.ORICHALCUM_KNIFE.get(), ModItems.SILVER_KNIFE.get(), ModItems.STONE_KNIFE.get(), ModItems.TIN_KNIFE.get(), ModItems.WOODEN_KNIFE.get());
      b.tag(MWTags.Items.FORGE_KNIVES).addTag(MWTags.Items.KNIVES);

      b.tag(MWTags.Items.SILVER_ITEMS).add(ModItems.SILVER_SWORD.get(), ModItems.SILVER_KNIFE.get(), ModItems.SILVER_AXE.get(), ModItems.SILVER_HOE.get(), ModItems.SILVER_PICKAXE.get(), ModItems.SILVER_SHOVEL.get(), ModItems.SILVER_BOOTS.get(), ModItems.SILVER_CHESTPLATE.get(), ModItems.SILVER_HELMET.get(), ModItems.SILVER_LEGGINGS.get());
      b.tag(MWTags.Items.COPPER_ITEMS).add(ModItems.COPPER_SWORD.get(), ModItems.COPPER_KNIFE.get(), ModItems.COPPER_AXE.get(), ModItems.COPPER_HOE.get(), ModItems.COPPER_PICKAXE.get(), ModItems.COPPER_SHOVEL.get(), ModItems.COPPER_BOOTS.get(), ModItems.COPPER_CHESTPLATE.get(), ModItems.COPPER_HELMET.get(), ModItems.COPPER_LEGGINGS.get());
      b.tag(MWTags.Items.ORICHALCUM_ITEMS).add(ModItems.ORICHALCUM_SWORD.get(), ModItems.ORICHALCUM_KNIFE.get(), ModItems.ORICHALCUM_AXE.get(), ModItems.ORICHALCUM_HOE.get(), ModItems.ORICHALCUM_PICKAXE.get(), ModItems.ORICHALCUM_SHOVEL.get(), ModItems.ORICHALCUM_BOOTS.get(), ModItems.ORICHALCUM_CHESTPLATE.get(), ModItems.ORICHALCUM_HELMET.get(), ModItems.ORICHALCUM_LEGGINGS.get());
      b.tag(MWTags.Items.TIN_ITEMS).add(ModItems.TIN_SWORD.get(), ModItems.TIN_KNIFE.get(), ModItems.TIN_AXE.get(), ModItems.TIN_HOE.get(), ModItems.TIN_PICKAXE.get(), ModItems.TIN_SHOVEL.get(), ModItems.TIN_BOOTS.get(), ModItems.TIN_CHESTPLATE.get(), ModItems.TIN_HELMET.get(), ModItems.TIN_LEGGINGS.get());
      b.tag(MWTags.Items.LEAD_ITEMS).add(ModItems.LEAD_SWORD.get(), ModItems.LEAD_KNIFE.get(), ModItems.LEAD_AXE.get(), ModItems.LEAD_HOE.get(), ModItems.LEAD_PICKAXE.get(), ModItems.LEAD_SHOVEL.get(), ModItems.LEAD_BOOTS.get(), ModItems.LEAD_CHESTPLATE.get(), ModItems.LEAD_HELMET.get(), ModItems.LEAD_LEGGINGS.get());

      b.tag(MWTags.Items.SUGARS).add(Items.SUGAR, Items.APPLE, Items.COOKIE, Items.MELON_SLICE, Items.CAKE, Items.PUMPKIN_PIE);
      b.tag(MWTags.Items.PROTEINS).add(Items.ROTTEN_FLESH, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COD, Items.SALMON, Items.RABBIT, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.MUTTON, Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.COOKED_COD, Items.COOKED_SALMON, Items.EGG);
      b.tag(MWTags.Items.PROTEINS).add(ModItems.VENISON.get(), ModItems.COOKED_VENISON.get(), ModItems.RAW_SQUID.get(), ModItems.COOKED_SQUID.get(), ModItems.EPIC_SQUID.get());

      b.copy(MWTags.Blocks.PURPUR, MWTags.Items.PURPUR);
      b.copy(MWTags.Blocks.NETHER_BRICKS, MWTags.Items.NETHER_BRICKS);
      b.copy(MWTags.Blocks.RED_NETHER_BRICKS, MWTags.Items.RED_NETHER_BRICKS);
      b.copy(MWTags.Blocks.TERRACOTTA, MWTags.Items.TERRACOTTA);
      b.copy(MWTags.Blocks.MUSHROOM_BLOCKS, MWTags.Items.MUSHROOM_BLOCKS);

      b.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.SAPPHIRE_GEM.get(), ModItems.LEAD_INGOT.get(), ModItems.ORICHALCUM_INGOT.get(), ModItems.SILVER_INGOT.get(), ModItems.TIN_INGOT.get(), ModItems.PEARL_GEM.get());
    });

    REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, b -> {
      b.tag(Tags.Blocks.ORES).addTags(MWTags.Blocks.SAPPHIRE_ORE, MWTags.Blocks.LEAD_ORE, MWTags.Blocks.SILVER_ORE, MWTags.Blocks.TIN_ORE, MWTags.Blocks.QUARTZ_ORE);

      b.tag(MWTags.Blocks.PURPUR).add(Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR);
      b.tag(MWTags.Blocks.NETHER_BRICKS).add(Blocks.NETHER_BRICKS);
      b.tag(MWTags.Blocks.RED_NETHER_BRICKS).add(Blocks.RED_NETHER_BRICKS);
      b.tag(MWTags.Blocks.TERRACOTTA).add(Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK.get());
      b.tag(MWTags.Blocks.MUSHROOM_BLOCKS).add(Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
      b.tag(MWTags.Blocks.MUSHROOM_BLOCKS).add(ModBlocks.MUSHROOM_INSIDE.get());
      b.tag(MWTags.Blocks.BASE_STONE_GRANITE).add(Blocks.GRANITE);
      b.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(MWTags.Blocks.SAPPHIRE_STORAGE, MWTags.Blocks.LEAD_STORAGE, MWTags.Blocks.ORICHALCUM_STORAGE, MWTags.Blocks.SILVER_STORAGE, MWTags.Blocks.TIN_STORAGE, MWTags.Blocks.PEARL_STORAGE);
      b.tag(BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.SAPPHIRE_BLOCK.get(), ModBlocks.LEAD_BLOCK.get(), ModBlocks.ORICHALCUM_BLOCK.get(), ModBlocks.SILVER_BLOCK.get(), ModBlocks.TIN_BLOCK.get(), ModBlocks.PEARL_BLOCK.get());
      b.tag(MWTags.Blocks.CROPS).add(ModBlocks.AUBERGINE_CROP.get());
      b.tag(MWTags.Blocks.AUBERGINE_CROP).add(ModBlocks.AUBERGINE_CROP.get());
      b.tag(MWTags.Blocks.EGGPLANT_CROP).add(ModBlocks.AUBERGINE_CROP.get());

      b.tag(BlockTags.BASE_STONE_OVERWORLD).add(ModBlocks.SOFT_STONE.get());
    });
  }

  public static void load() {

  }
}
