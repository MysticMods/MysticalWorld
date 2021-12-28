package mysticmods.mysticalworld.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.ModBlocks;
import mysticmods.mysticalworld.init.ModItems;
import mysticmods.mysticalworld.loot.functions.RandomPotion;
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
  private static final ResourceLocation HUT = new ResourceLocation(MysticalWorld.MODID, "hut");
  private static final ResourceLocation BARROW = new ResourceLocation(MysticalWorld.MODID, "barrow");
  private static final ResourceLocation BREWING = new ResourceLocation(MysticalWorld.MODID, "brewing");
  private static final ResourceLocation HOUSE = new ResourceLocation(MysticalWorld.MODID, "sand_house");

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
    map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
  }

  @SuppressWarnings("Duplicates")
  public static class ChestLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {

      // Hut/Ruined Hut chest
      consumer.accept(
          HUT,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(3, 6))
                      .bonusRolls(2, 4)
                      .add(ItemLootEntry.lootTableItem(Items.GRASS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(8.0f, 15.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FERN).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(8.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.PUMPKIN).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 7.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.HAY_BLOCK).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DRIED_KELP_BLOCK).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.PACKED_ICE).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.COBWEB).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(4.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(4.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SWEET_BERRIES).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(5.0F, 16.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.POPPY).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(3.0F, 7.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DANDELION).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(3.0F, 10.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.OXEYE_DAISY).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 6.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.AZURE_BLUET).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.VINE).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.CACTUS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(3.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.TALL_GRASS).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LARGE_FERN).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BLUE_ORCHID).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DEAD_BUSH).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 6.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.STRING).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.WHEAT_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.BEETROOT_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.LILY_PAD).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 3))
                      .bonusRolls(2, 3)
                      .add(ItemLootEntry.lootTableItem(Items.RED_TULIP).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.ORANGE_TULIP).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.PINK_TULIP).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LILY_OF_THE_VALLEY).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.ALLIUM).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.CORNFLOWER).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SUNFLOWER).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LILAC).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.ROSE_BUSH).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BAMBOO).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SEAGRASS).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SEA_PICKLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BREAD).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.WHEAT).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE.get()).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.COOKED_AUBERGINE.get()).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.APPLE).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.CARROT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.POTATO).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BEETROOT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SPIDER_EYE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.DANDELION_CORNFLOWER_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CACTUS_DANDELION_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETROOT_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.STEWED_EGGPLANT.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.VINEGAR.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.PEONY_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.ROSE_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.LILAC_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CACTUS_SYRUP.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.APPLE_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.DANDELION_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .bonusRolls(1, 2)
                      .add(ItemLootEntry.lootTableItem(Items.WITHER_ROSE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(0.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CARAPACE.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.ANTLERS.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.SILK_COCOON.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.SPINDLE.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FEATHER).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FLOWER_POT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.LAPIS_LAZULI).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(6.0f, 18.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.TROPICAL_FISH).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.PUFFERFISH).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.SALMON).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.COD).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.CAKE).setWeight(3))
                      .add(ItemLootEntry.lootTableItem(Items.COOKIE).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(6.0f, 18.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.COCOA_BEANS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.PUMPKIN_PIE).setWeight(9).apply(SetCount.setCount(RandomValueRange.between(4.0f, 12.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0f, 5.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.NAME_TAG).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.HONEY_BOTTLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.NAUTILUS_SHELL).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0f, 4.0f))))
                      .add(EmptyLootEntry.emptyItem().setWeight(8))
              )
      );


      // BARROW

      consumer.accept(
          BARROW,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(2, 5))
                      .bonusRolls(1f, 5f)
                      .add(ItemLootEntry.lootTableItem(Items.WATER_BUCKET).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.CLAY_BALL).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(8.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LAPIS_LAZULI).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 7.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.COAL).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BOOK).setWeight(22).apply(SetCount.setCount(RandomValueRange.between(9.0F, 21.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BONE).setWeight(16).apply(SetCount.setCount(RandomValueRange.between(6.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SHEARS).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(Items.CHAIN).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.ARROW).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(4.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SADDLE))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 2))
                      .bonusRolls(1, 2)
                      .add(ItemLootEntry.lootTableItem(Items.LAVA_BUCKET).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.PRISMARINE_CRYSTALS).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.PRISMARINE_SHARD).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.QUARTZ).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(3.0F, 7.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.GOLD_INGOT).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.REDSTONE).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.GUNPOWDER).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(6.0F, 12.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.GLOWSTONE_DUST).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(3.0F, 6.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SPONGE).setWeight(1))
                      .add(ItemLootEntry.lootTableItem(Items.MAGMA_CREAM).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .bonusRolls(1, 2)
                      .add(ItemLootEntry.lootTableItem(Items.ENDER_PEARL).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(0.0F, 5.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.FIREWORK_ROCKET).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.ANTLER_HAT.get()).setWeight(3).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETLE_HELMET.get()).setWeight(6).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETLE_CHESTPLATE.get()).setWeight(1).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETLE_BOOTS.get()).setWeight(6).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETLE_LEGGINGS.get()).setWeight(3).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(Items.OBSIDIAN).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.NETHER_WART_BLOCK).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(2.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.PHANTOM_MEMBRANE).setWeight(2))
                      .add(ItemLootEntry.lootTableItem(ModItems.LEAD_BOOTS.get()).setWeight(6).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.COPPER_HELMET.get()).setWeight(8).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.COPPER_CHESTPLATE.get()).setWeight(12).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(ModItems.QUICKSILVER_LEGGINGS.get()).setWeight(2).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(ItemLootEntry.lootTableItem(Items.CROSSBOW).setWeight(3).apply(EnchantRandomly.randomApplicableEnchantment()))
                      .add(EmptyLootEntry.emptyItem().setWeight(4))
              )
      );

      // SAND HOUSE

      consumer.accept(
          HOUSE,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(3, 6))
                      .bonusRolls(2, 4)
                      .add(ItemLootEntry.lootTableItem(Items.GRASS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(8.0f, 15.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FERN).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(8.0F, 15.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DRIED_KELP_BLOCK).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(3.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SWEET_BERRIES).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(5.0F, 16.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.CACTUS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(3.0F, 8.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.TALL_GRASS).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.LARGE_FERN).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(3.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BLUE_ORCHID).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.DEAD_BUSH).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 6.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.STRING).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.WHEAT_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.BEETROOT_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.PUMPKIN_SEEDS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.LILY_PAD).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(10, 20))))
                      .add(ItemLootEntry.lootTableItem(Items.RABBIT_FOOT).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(2, 6))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(1, 3))
                      .bonusRolls(2, 3)
                      .add(ItemLootEntry.lootTableItem(Items.BAMBOO).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SEAGRASS).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SEA_PICKLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BREAD).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.WHEAT).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE.get()).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.COOKED_AUBERGINE.get()).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.APPLE).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.CARROT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.POTATO).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.BEETROOT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(Items.SPIDER_EYE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.DANDELION_CORNFLOWER_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CACTUS_DANDELION_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.BEETROOT_SALAD.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.STEWED_EGGPLANT.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.VINEGAR.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.PEONY_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.ROSE_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.LILAC_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CACTUS_SYRUP.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.APPLE_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.DANDELION_CORDIAL.get()).setWeight(3).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))))
              )
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .bonusRolls(1, 2)
                      .add(ItemLootEntry.lootTableItem(Items.WITHER_ROSE).setWeight(1).apply(SetCount.setCount(RandomValueRange.between(0.0F, 4.0F))))
                      .add(ItemLootEntry.lootTableItem(ModItems.CARAPACE.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.ANTLERS.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.SILK_COCOON.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(ModItems.SPINDLE.get()).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FEATHER).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.FLOWER_POT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.LAPIS_LAZULI).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(6.0f, 18.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.TROPICAL_FISH).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.PUFFERFISH).setWeight(6).apply(SetCount.setCount(RandomValueRange.between(2.0f, 4.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.SALMON).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.COD).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.CAKE).setWeight(3))
                      .add(ItemLootEntry.lootTableItem(Items.COOKIE).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(6.0f, 18.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.COCOA_BEANS).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(2.0f, 7.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.PUMPKIN_PIE).setWeight(9).apply(SetCount.setCount(RandomValueRange.between(4.0f, 12.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.EXPERIENCE_BOTTLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0f, 5.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.NAME_TAG).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.HONEY_BOTTLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(1.0f, 3.0f))))
                      .add(ItemLootEntry.lootTableItem(Items.NAUTILUS_SHELL).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1.0f, 4.0f))))
                      .add(EmptyLootEntry.emptyItem().setWeight(8))
              )
      );

      consumer.accept(
          BREWING,
          LootTable.lootTable()
              .withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 2))
                      .bonusRolls(1, 3)
                      .add(ItemLootEntry.lootTableItem(Items.WATER_BUCKET).setWeight(8))
                      .add(ItemLootEntry.lootTableItem(Items.SEA_PICKLE).setWeight(2).apply(SetCount.setCount(RandomValueRange.between(3f, 10f))))
                      .add(ItemLootEntry.lootTableItem(Items.WHEAT).setWeight(12).apply(SetCount.setCount(RandomValueRange.between(5f, 25f))))
                      .add(ItemLootEntry.lootTableItem(Items.POTATO).setWeight(7).apply(SetCount.setCount(RandomValueRange.between(8f, 15f))))
                      .add(ItemLootEntry.lootTableItem(Items.BROWN_MUSHROOM).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(2f, 10f))))
                      .add(ItemLootEntry.lootTableItem(Items.RED_MUSHROOM).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(2f, 10f))))
                      .add(ItemLootEntry.lootTableItem(ModBlocks.UNCANNY_MUSHROOM.get()).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(2f, 12f))))
              ).withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 1))
                      .bonusRolls(1f, 3f)
                      .add(ItemLootEntry.lootTableItem(Items.GLOWSTONE_DUST).setWeight(8).apply(SetCount.setCount(RandomValueRange.between(6f, 12f))))
                      .add(ItemLootEntry.lootTableItem(Items.REDSTONE).setWeight(25).apply(SetCount.setCount(RandomValueRange.between(4f, 18f))))
                      .add(ItemLootEntry.lootTableItem(Items.SUGAR).setWeight(30).apply(SetCount.setCount(RandomValueRange.between(10f, 35f))))
                      .add(ItemLootEntry.lootTableItem(Items.NETHER_WART).setWeight(5).apply(SetCount.setCount(RandomValueRange.between(1f, 4f))))
                      .add(ItemLootEntry.lootTableItem(Items.GUNPOWDER).setWeight(15).apply(SetCount.setCount(RandomValueRange.between(8f, 20f))))
                      .add(ItemLootEntry.lootTableItem(Items.BLAZE_POWDER).setWeight(4).apply(SetCount.setCount(RandomValueRange.between(2f, 8f))))
              ).withPool(
                  LootPool.lootPool()
                      .setRolls(RandomValueRange.between(0, 1))
                      .bonusRolls(1f, 2f)
                      .add(ItemLootEntry.lootTableItem(Items.POTION).setWeight(3).apply(RandomPotion.builder()))
              )
      );
    }
  }
}
