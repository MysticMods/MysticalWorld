package epicsquid.mysticalworld.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import epicsquid.mysticallib.data.DeferredBlockLootTableProvider;
import epicsquid.mysticallib.data.DeferredEntityLootTableProvider;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.Smelt;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class MWLootTableProvider extends LootTableProvider {
  public MWLootTableProvider(DataGenerator dataGeneratorIn) {
    super(dataGeneratorIn);
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
    return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Entities::new, LootParameterSets.ENTITY));
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationResults validationresults) {
    super.validate(map, validationresults);
  }

  public static class Entities extends DeferredEntityLootTableProvider {
    @Override
    public void addTables() {
      /*
         LootTable.builder()
         .addLootPool(LootPool.builder()
          .rolls(ConstantRange.of(1))
          .addEntry(ItemLootEntry.builder(Items.FEATHER)
           .acceptFunction(SetCount.builder(RandomValueRange.of(0.0 F, 2.0 F)))
           .acceptFunction(LootingEnchantBonus.func_215915_a(RandomValueRange.of(0.0 F, 1.0 F)))))
         .addLootPool(LootPool.builder()
          .rolls(ConstantRange.of(1))
          .addEntry(ItemLootEntry.builder(Items.CHICKEN)
           .acceptFunction(Smelt.func_215953_b()
            .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, PROPERTY_ON_FIRE)))
           .acceptFunction(LootingEnchantBonus.func_215915_a(RandomValueRange.of(0.0 F, 1.0 F))))));
       */
    }
  }

  public static class Blocks extends DeferredBlockLootTableProvider {
    @Override
    protected void addTables() {
      self(ModBlocks.COPPER_ORE);
      self(ModBlocks.LEAD_ORE);
      self(ModBlocks.QUICKSILVER_ORE);
      self(ModBlocks.SILVER_ORE);
      self(ModBlocks.TIN_ORE);

      self(ModBlocks.AMETHYST_BLOCK);
      self(ModBlocks.COPPER_BLOCK);
      self(ModBlocks.LEAD_BLOCK);
      self(ModBlocks.QUICKSILVER_BLOCK);
      self(ModBlocks.SILVER_BLOCK);
      self(ModBlocks.TIN_BLOCK);

      self(ModBlocks.WET_MUD_BLOCK);
      self(ModBlocks.WET_MUD_BRICK);
      self(ModBlocks.MUD_BLOCK);
      self(ModBlocks.MUD_BRICK);

      self(ModBlocks.THATCH);

      registerLootTable(ModBlocks.AUBERGINE_CROP.get(), b -> droppingAndBonusWhen(b, ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), BlockStateProperty.builder(ModBlocks.AUBERGINE_CROP.get()).with(CropsBlock.AGE, 7)));

      orePieces(ModBlocks.AMETHYST_ORE, ModItems.AMETHYST_GEM);
    }
  }
}
