package mysticmods.mysticalworld.init;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import mysticmods.mysticalworld.MWTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static mysticmods.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: IMPROVE THIS A LOT

@SuppressWarnings("unchecked")
public class ModTags {
  @FunctionalInterface
  private interface Additionals<T> {
    void add(Tags.IOptionalNamedTag<T>... tags);
  }

  private static class BlockBuilder {
    private RegistrateTagsProvider<Block> provider;

    private BlockBuilder(RegistrateTagsProvider<Block> provider) {
      this.provider = provider;
    }

    private void add(Tags.IOptionalNamedTag<Block> tag, Supplier<? extends Block>... items) {
      provider.getOrCreateBuilder(tag).add(Stream.of(items).map(Supplier::get).toArray(Block[]::new));
    }

    private void add(Tags.IOptionalNamedTag<Block> tag, Block... items) {
      provider.getOrCreateBuilder(tag).add(items);
    }

    private void add(Tags.IOptionalNamedTag<Block> tag, Tags.IOptionalNamedTag<Block> tag2) {
      provider.getOrCreateBuilder(tag).addTags(tag2);
    }

    private Additionals<Block> additional(Tags.IOptionalNamedTag<Block> tag) {
      return (o) -> provider.getOrCreateBuilder(tag).addTags(o);
    }
  }

  private static class ItemBuilder {
    private RegistrateTagsProvider<Item> provider;

    private ItemBuilder(RegistrateTagsProvider<Item> provider) {
      this.provider = provider;
    }

    private void add(Tags.IOptionalNamedTag<Item> tag, Supplier<? extends IItemProvider>... items) {
      provider.getOrCreateBuilder(tag).add(Stream.of(items).map(Supplier::get).map(IItemProvider::asItem).toArray(Item[]::new));
    }

    private void add(Tags.IOptionalNamedTag<Item> tag, IItemProvider... items) {
      provider.getOrCreateBuilder(tag).add(Stream.of(items).map(IItemProvider::asItem).toArray(Item[]::new));
    }

    private void add(Tags.IOptionalNamedTag<Item> tag, Tags.IOptionalNamedTag<Item> tag2) {
      provider.getOrCreateBuilder(tag).addTags(tag2);
    }

    private Additionals<Item> additional(Tags.IOptionalNamedTag<Item> tag) {
      return (o) -> provider.getOrCreateBuilder(tag).addTags(o);
    }
  }

  static {
    REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, p -> {
      ItemBuilder b = new ItemBuilder(p);
      b.additional(Tags.Items.ORES).add(MWTags.Items.AMETHYST_ORE, MWTags.Items.COPPER_ORE, MWTags.Items.LEAD_ORE, MWTags.Items.QUICKSILVER_ORE, MWTags.Items.SILVER_ORE, MWTags.Items.TIN_ORE, MWTags.Items.QUARTZ_ORE);

      b.add(MWTags.Items.SEEDS, ModItems.AUBERGINE_SEEDS);

      b.add(MWTags.Items.AUBERGINE, ModItems.AUBERGINE);
      b.add(MWTags.Items.EGGPLANT, MWTags.Items.AUBERGINE);

      b.add(Tags.Items.STRING, ModItems.SILK_THREAD);

      b.add(MWTags.Items.SLIME_BLOCK, Items.SLIME_BLOCK);

      b.add(MWTags.Items.SOFT_STONE, ModBlocks.SOFT_STONE);
      b.add(Tags.Items.STONE, ModBlocks.SOFT_STONE);

      b.add(MWTags.Items.SLIME, Items.SLIME_BALL);
      b.add(MWTags.Items.STONEPETAL, ModBlocks.STONEPETAL);

      b.add(Tags.Items.GEMS, MWTags.Items.AMETHYST_GEM);

      b.additional(Tags.Items.INGOTS).add(MWTags.Items.COPPER_INGOT, MWTags.Items.LEAD_INGOT, MWTags.Items.QUICKSILVER_INGOT, MWTags.Items.SILVER_INGOT, MWTags.Items.TIN_INGOT);
      b.additional(Tags.Items.NUGGETS).add(MWTags.Items.COPPER_NUGGET, MWTags.Items.LEAD_NUGGET, MWTags.Items.QUICKSILVER_NUGGET, MWTags.Items.SILVER_NUGGET, MWTags.Items.TIN_NUGGET);
      b.additional(Tags.Items.DUSTS).add(MWTags.Items.COPPER_DUST, MWTags.Items.LEAD_DUST, MWTags.Items.QUICKSILVER_DUST, MWTags.Items.SILVER_DUST, MWTags.Items.TIN_DUST, MWTags.Items.GOLD_DUST, MWTags.Items.IRON_DUST);

      b.add(MWTags.Items.GEMS, ModItems.AMETHYST_GEM, () -> Items.DIAMOND);

      b.add(MWTags.Items.VEGETABLES, Items.CARROT, Items.BEETROOT);
      b.add(MWTags.Items.VEGETABLES, ModItems.AUBERGINE);
      b.add(MWTags.Items.COOKED_VEGETABLES, ModItems.COOKED_BEETROOT, ModItems.COOKED_CARROT, () -> Items.BAKED_POTATO, ModItems.COOKED_AUBERGINE);
      b.add(MWTags.Items.COOKED_SEAFOOD, ModItems.COOKED_SQUID, () -> Items.COOKED_COD, () -> Items.COOKED_SALMON);

      b.add(MWTags.Items.SWORDS, ModItems.AMETHYST_SWORD, ModItems.CACTUS_SWORD, ModItems.COPPER_SWORD, ModItems.LEAD_SWORD, ModItems.QUICKSILVER_SWORD, ModItems.SILVER_SWORD, ModItems.TIN_SWORD);
      b.add(MWTags.Items.KNIVES, ModItems.AMETHYST_KNIFE, ModItems.CACTUS_KNIFE, ModItems.COPPER_KNIFE, ModItems.DIAMOND_KNIFE, ModItems.GOLD_KNIFE, ModItems.IRON_KNIFE, ModItems.LEAD_KNIFE, ModItems.QUICKSILVER_KNIFE, ModItems.SILVER_KNIFE, ModItems.STONE_KNIFE, ModItems.TIN_KNIFE, ModItems.WOODEN_KNIFE);
      b.add(MWTags.Items.FORGE_KNIVES, MWTags.Items.KNIVES);

      b.add(MWTags.Items.SILVER_ITEMS, ModItems.SILVER_SWORD, ModItems.SILVER_KNIFE, ModItems.SILVER_AXE, ModItems.SILVER_HOE, ModItems.SILVER_PICKAXE, ModItems.SILVER_SHOVEL, ModItems.SILVER_BOOTS, ModItems.SILVER_CHESTPLATE, ModItems.SILVER_HELMET, ModItems.SILVER_LEGGINGS, ModItems.SILVER_SPEAR);
      b.add(MWTags.Items.COPPER_ITEMS, ModItems.COPPER_SWORD, ModItems.COPPER_KNIFE, ModItems.COPPER_AXE, ModItems.COPPER_HOE, ModItems.COPPER_PICKAXE, ModItems.COPPER_SHOVEL, ModItems.COPPER_BOOTS, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_HELMET, ModItems.COPPER_LEGGINGS, ModItems.COPPER_SPEAR);
      b.add(MWTags.Items.QUICKSILVER_ITEMS, ModItems.QUICKSILVER_SWORD, ModItems.QUICKSILVER_KNIFE, ModItems.QUICKSILVER_AXE, ModItems.QUICKSILVER_HOE, ModItems.QUICKSILVER_PICKAXE, ModItems.QUICKSILVER_SHOVEL, ModItems.QUICKSILVER_BOOTS, ModItems.QUICKSILVER_CHESTPLATE, ModItems.QUICKSILVER_HELMET, ModItems.QUICKSILVER_LEGGINGS, ModItems.QUICKSILVER_SPEAR);
      b.add(MWTags.Items.TIN_ITEMS, ModItems.TIN_SWORD, ModItems.TIN_KNIFE, ModItems.TIN_AXE, ModItems.TIN_HOE, ModItems.TIN_PICKAXE, ModItems.TIN_SHOVEL, ModItems.TIN_BOOTS, ModItems.TIN_CHESTPLATE, ModItems.TIN_HELMET, ModItems.TIN_LEGGINGS, ModItems.TIN_SPEAR);
      b.add(MWTags.Items.LEAD_ITEMS, ModItems.LEAD_SWORD, ModItems.LEAD_KNIFE, ModItems.LEAD_AXE, ModItems.LEAD_HOE, ModItems.LEAD_PICKAXE, ModItems.LEAD_SHOVEL, ModItems.LEAD_BOOTS, ModItems.LEAD_CHESTPLATE, ModItems.LEAD_HELMET, ModItems.LEAD_LEGGINGS, ModItems.LEAD_SPEAR);

      b.add(MWTags.Items.SUGARS, Items.SUGAR, Items.APPLE, Items.COOKIE, Items.MELON_SLICE, Items.CAKE, Items.PUMPKIN_PIE);
      b.add(MWTags.Items.PROTEINS, Items.ROTTEN_FLESH, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COD, Items.SALMON, Items.RABBIT, Items.COOKED_RABBIT, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON, Items.MUTTON, Items.COOKED_CHICKEN, Items.COOKED_BEEF, Items.COOKED_COD, Items.COOKED_SALMON, Items.EGG);
      b.add(MWTags.Items.PROTEINS, ModItems.VENISON, ModItems.COOKED_VENISON, ModItems.RAW_SQUID, ModItems.COOKED_SQUID, ModItems.EPIC_SQUID);

      p.copy(MWTags.Blocks.PURPUR, MWTags.Items.PURPUR);
      p.copy(MWTags.Blocks.NETHER_BRICKS, MWTags.Items.NETHER_BRICKS);
      p.copy(MWTags.Blocks.RED_NETHER_BRICKS, MWTags.Items.RED_NETHER_BRICKS);
      p.copy(MWTags.Blocks.TERRACOTTA, MWTags.Items.TERRACOTTA);
      p.copy(MWTags.Blocks.MUSHROOM_BLOCKS, MWTags.Items.MUSHROOM_BLOCKS);
    });

    REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, p -> {
      BlockBuilder b = new BlockBuilder(p);

      b.add(MWTags.Blocks.PURPUR, Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR);
      b.add(MWTags.Blocks.NETHER_BRICKS, Blocks.NETHER_BRICKS);
      b.add(MWTags.Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
      b.add(MWTags.Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.TERRACOTTA, ModBlocks.TERRACOTTA_BRICK.get());
      b.add(MWTags.Blocks.MUSHROOM_BLOCKS, Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK);
      b.add(MWTags.Blocks.MUSHROOM_BLOCKS, ModBlocks.MUSHROOM_INSIDE.get());
      b.add(MWTags.Blocks.BASE_STONE_GRANITE, Blocks.GRANITE);
      b.additional(Tags.Blocks.STORAGE_BLOCKS).add(MWTags.Blocks.AMETHYST_STORAGE, MWTags.Blocks.COPPER_STORAGE, MWTags.Blocks.LEAD_STORAGE, MWTags.Blocks.QUICKSILVER_STORAGE, MWTags.Blocks.SILVER_STORAGE, MWTags.Blocks.TIN_STORAGE, MWTags.Blocks.PEARL_STORAGE);
      b.add(MWTags.Blocks.CROPS, ModBlocks.AUBERGINE_CROP.get());
      b.add(MWTags.Blocks.AUBERGINE_CROP, ModBlocks.AUBERGINE_CROP.get());
      b.add(MWTags.Blocks.EGGPLANT_CROP, ModBlocks.AUBERGINE_CROP.get());

      p.getOrCreateBuilder(BlockTags.BASE_STONE_OVERWORLD).add(ModBlocks.SOFT_STONE.get());
    });
  }

  public static void load() {

  }
}
