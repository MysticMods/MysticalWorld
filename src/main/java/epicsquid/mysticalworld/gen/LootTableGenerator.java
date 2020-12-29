package epicsquid.mysticalworld.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.EnchantRandomly;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTableGenerator extends LootTableProvider {
  private static ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");
  private static ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");

  private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(Pair.of(ChestLootTables::new, LootParameterSets.CHEST));

  public LootTableGenerator(DataGenerator dataGeneratorIn) {
    super(dataGeneratorIn);
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
    return tables;
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
    map.forEach((p_218436_2_, p_218436_3_) -> {
      LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_);
    });
  }

  @SuppressWarnings("Duplicates")
  public static class ChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {

      // Hut/Ruined Hut chest
      consumer.accept(
          HUT,
          LootTable.builder()
              .addLootPool(
                  LootPool.builder()
                      .rolls(ConstantRange.of(6))
                      .addEntry(ItemLootEntry.builder(Items.GRASS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(8.0f, 15.0f))))
                      .addEntry(ItemLootEntry.builder(Items.FERN).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(8.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.PUMPKIN).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
                      .addEntry(ItemLootEntry.builder(Items.HAY_BLOCK).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.DRIED_KELP_BLOCK).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.PACKED_ICE).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.COBWEB).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.RED_MUSHROOM).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BROWN_MUSHROOM).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SWEET_BERRIES).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(5.0F, 16.0F))))
                      .addEntry(ItemLootEntry.builder(Items.POPPY).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
                      .addEntry(ItemLootEntry.builder(Items.DANDELION).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 10.0F))))
                      .addEntry(ItemLootEntry.builder(Items.OXEYE_DAISY).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))))
                      .addEntry(ItemLootEntry.builder(Items.AZURE_BLUET).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.VINE).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.CACTUS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 8.0F))))
                      .addEntry(ItemLootEntry.builder(Items.TALL_GRASS).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LARGE_FERN).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BLUE_ORCHID).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.DEAD_BUSH).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 6.0f))))
                      .addEntry(ItemLootEntry.builder(Items.STRING).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(10, 20))))
                      .addEntry(ItemLootEntry.builder(Items.WHEAT_SEEDS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(10, 20))))
                      .addEntry(ItemLootEntry.builder(Items.BEETROOT_SEEDS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(10, 20))))
                      .addEntry(ItemLootEntry.builder(Items.PUMPKIN_SEEDS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(10, 20))))
                      .addEntry(ItemLootEntry.builder(Items.LILY_PAD).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(10, 20))))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(2, 4))
                      .addEntry(ItemLootEntry.builder(Items.RED_TULIP).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.ORANGE_TULIP).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.PINK_TULIP).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LILY_OF_THE_VALLEY).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.ALLIUM).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.CORNFLOWER).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SUNFLOWER).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LILAC).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.ROSE_BUSH).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BAMBOO).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SEAGRASS).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SEA_PICKLE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BREAD).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.WHEAT).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.AUBERGINE.get()).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.COOKED_AUBERGINE.get()).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.APPLE).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.CARROT).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.POTATO).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BEETROOT).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SPIDER_EYE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.AUBERGINE_SALAD.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.DANDELION_CORNFLOWER_SALAD.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.CACTUS_DANDELION_SALAD.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.BEETROOT_SALAD.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.STEWED_EGGPLANT.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.VINEGAR.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.PEONY_CORDIAL.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.ROSE_CORDIAL.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.LILAC_CORDIAL.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.CACTUS_SYRUP.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.APPLE_CORDIAL.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.DANDELION_CORDIAL.get()).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(0, 2))
                      .addEntry(ItemLootEntry.builder(Items.WITHER_ROSE).weight(1).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(ModItems.CARAPACE.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(ModItems.ANTLERS.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(ModItems.SILK_COCOON.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(ModItems.SPINDLE.get()).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.FEATHER).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.FLOWER_POT).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(Items.LAPIS_LAZULI).weight(4).acceptFunction(SetCount.builder(RandomValueRange.of(6.0f, 18.0f))))
                      .addEntry(ItemLootEntry.builder(Items.TROPICAL_FISH).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.PUFFERFISH).weight(6).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(Items.SALMON).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.COD).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.CAKE).weight(3))
                      .addEntry(ItemLootEntry.builder(Items.COOKIE).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(6.0f, 18.0f))))
                      .addEntry(ItemLootEntry.builder(Items.COCOA_BEANS).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.PUMPKIN_PIE).weight(9).acceptFunction(SetCount.builder(RandomValueRange.of(4.0f, 12.0f))))
                      .addEntry(ItemLootEntry.builder(Items.EXPERIENCE_BOTTLE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 5.0f))))
                      .addEntry(ItemLootEntry.builder(Items.NAME_TAG).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 3.0f))))
                      .addEntry(ItemLootEntry.builder(Items.HONEY_BOTTLE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 3.0f))))
                      .addEntry(ItemLootEntry.builder(Items.NAUTILUS_SHELL).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 4.0f))))
                      .addEntry(EmptyLootEntry.func_216167_a().weight(8))
              )
      );



      // BARROW

      consumer.accept(
          BARROW,
          LootTable.builder()
              .addLootPool(
                  LootPool.builder()
                      .rolls(ConstantRange.of(6))
                      .addEntry(ItemLootEntry.builder(Items.WATER_BUCKET).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 3.0f))))
                      .addEntry(ItemLootEntry.builder(Items.CLAY_BALL).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(8.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.LAPIS_LAZULI).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
                      .addEntry(ItemLootEntry.builder(Items.COAL).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BOOK).weight(22).acceptFunction(SetCount.builder(RandomValueRange.of(9.0F, 21.0F))))
                      .addEntry(ItemLootEntry.builder(Items.BONE).weight(16).acceptFunction(SetCount.builder(RandomValueRange.of(6.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SHEARS).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(Items.CHAIN).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.ARROW).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 15.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SADDLE))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(1, 3))
                      .addEntry(ItemLootEntry.builder(Items.LAVA_BUCKET).weight(12).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))
                      .addEntry(ItemLootEntry.builder(Items.PRISMARINE_CRYSTALS).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.PRISMARINE_SHARD).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.QUARTZ).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 7.0F))))
                      .addEntry(ItemLootEntry.builder(Items.GOLD_INGOT).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F))))
                      .addEntry(ItemLootEntry.builder(Items.REDSTONE).weight(8).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 9.0F))))
                      .addEntry(ItemLootEntry.builder(Items.GUNPOWDER).weight(4).acceptFunction(SetCount.builder(RandomValueRange.of(6.0F, 12.0F))))
                      .addEntry(ItemLootEntry.builder(Items.GLOWSTONE_DUST).weight(4).acceptFunction(SetCount.builder(RandomValueRange.of(3.0F, 6.0F))))
                      .addEntry(ItemLootEntry.builder(Items.SPONGE).weight(1))
                      .addEntry(ItemLootEntry.builder(Items.MAGMA_CREAM).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))))
              )
              .addLootPool(
                  LootPool.builder()
                      .rolls(RandomValueRange.of(0, 2))
                      .addEntry(ItemLootEntry.builder(Items.ENDER_PEARL).weight(1).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 5.0F))))
                      .addEntry(ItemLootEntry.builder(Items.FIREWORK_ROCKET).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(ModItems.ANTLER_HAT.get()).weight(3).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(ModItems.BEETLE_MASK.get()).weight(6).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(Items.OBSIDIAN).weight(5).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 7.0f))))
                      .addEntry(ItemLootEntry.builder(Items.NETHER_WART_BLOCK).weight(3).acceptFunction(SetCount.builder(RandomValueRange.of(2.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(Items.PHANTOM_MEMBRANE).weight(2).acceptFunction(SetCount.builder(RandomValueRange.of(1.0f, 4.0f))))
                      .addEntry(ItemLootEntry.builder(ModItems.LEAD_BOOTS.get()).weight(6).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(ModItems.COPPER_HELMET.get()).weight(8).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(ModItems.COPPER_CHESTPLATE.get()).weight(12).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(ModItems.QUICKSILVER_LEGGINGS.get()).weight(2).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(ItemLootEntry.builder(Items.CROSSBOW).weight(3).acceptFunction(EnchantRandomly.func_215900_c()))
                      .addEntry(EmptyLootEntry.func_216167_a().weight(4))
              )
      );
    }
  }
}
