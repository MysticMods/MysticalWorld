package mysticmods.mysticalworld.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import mysticmods.mysticalworld.init.deferred.ModItems;
import mysticmods.mysticalworld.init.deferred.data.BlockData;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import noobanidus.libs.noobutil.loot.condition.LootConditions;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MWLootGenerator extends LootTableProvider {
  private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables = ImmutableList.of(Pair.of(MWBlockLoot::new, LootContextParamSets.BLOCK));

  public MWLootGenerator(DataGenerator pGenerator) {
    super(pGenerator);
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
    return tables;
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    map.forEach((id, table) -> LootTables.validate(validationtracker, id, table));
  }

  public static class MWBlockLoot extends BlockLoot {
    private final Set<RegistryObject<? extends Block>> doneBlocks = new HashSet<>();

    private void boneLoot(RegistryObject<? extends Block> block) {
      add(block.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(block.get()).when(LootConditions.HAS_SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))));
    }

    protected void gen(RegistryObject<? extends Block> block) {
      if (doneBlocks.contains(block)) {
        throw new IllegalStateException("Already generated for " + block);
      }

      doneBlocks.add(block);
    }

    protected void validate() {
      Set<RegistryObject<? extends Block>> missing = new HashSet<>(BlockData.getAllBlocks());
      missing.removeAll(doneBlocks);

      for (RegistryObject<? extends Block> block : missing) {
        MysticalWorld.LOG.error("Missing loot table for " + block.getId());
      }

      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing loot tables for " + missing.size() + " blocks");
      }
    }

    @Override
    protected void addTables() {
      add(ModBlocks.POTTED_STONEPETAL.get(), createPotFlowerItemTable(ModBlocks.STONEPETAL.get()));
      gen(ModBlocks.POTTED_STONEPETAL);
      add(ModBlocks.GALL_APPLE.get(), createCropDrops(ModBlocks.GALL_APPLE.get(), Items.AIR, ModItems.GALL_APPLE.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.GALL_APPLE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 3))));
      gen(ModBlocks.GALL_APPLE);
      add(ModBlocks.AUBERGINE_CROP.get(), createCropDrops(ModBlocks.AUBERGINE_CROP.get(), ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), new LootItemBlockStatePropertyCondition.Builder(ModBlocks.AUBERGINE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))));
      gen(ModBlocks.AUBERGINE_CROP);
      add(ModBlocks.WILD_AUBERGINE_CROP.get(), LootTable.lootTable().withPool(applyExplosionCondition(ModItems.AUBERGINE.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(ModItems.AUBERGINE.get())))).withPool(applyExplosionCondition(ModItems.AUBERGINE_SEEDS.get(), LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(ModItems.AUBERGINE_SEEDS.get())))));
      gen(ModBlocks.WILD_AUBERGINE_CROP);
      add(ModBlocks.WILD_WART.get(), LootTable.lootTable().withPool(applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 3)).add(LootItem.lootTableItem(Items.NETHER_WART)))).withPool(applyExplosionCondition(Items.NETHER_WART, LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.NETHER_WART)))));
      gen(ModBlocks.WILD_WART);
      add(ModBlocks.SAPPHIRE_ORE.get(), createOreDrop(ModBlocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE_GEM.get()));
      gen(ModBlocks.SAPPHIRE_ORE);
      add(ModBlocks.GRANITE_QUARTZ_ORE.get(), createOreDrop(ModBlocks.GRANITE_QUARTZ_ORE.get(), Items.QUARTZ));
      gen(ModBlocks.GRANITE_QUARTZ_ORE);

      boneLoot(ModBlocks.BONE_PILE_1);
      gen(ModBlocks.BONE_PILE_1);
      boneLoot(ModBlocks.BONE_PILE_2);
      gen(ModBlocks.BONE_PILE_2);
      boneLoot(ModBlocks.BONE_PILE_3);
      gen(ModBlocks.BONE_PILE_3);
      boneLoot(ModBlocks.BONE_PILE_4);
      gen(ModBlocks.BONE_PILE_4);
      boneLoot(ModBlocks.SKELETON_BOTTOM_1);
      gen(ModBlocks.SKELETON_BOTTOM_1);
      boneLoot(ModBlocks.SKELETON_BOTTOM_2);
      gen(ModBlocks.SKELETON_BOTTOM_2);
      boneLoot(ModBlocks.SKELETON_BOTTOM_3);
      gen(ModBlocks.SKELETON_BOTTOM_3);
      boneLoot(ModBlocks.SKELETON_TOP_1);
      gen(ModBlocks.SKELETON_TOP_1);
      boneLoot(ModBlocks.SKELETON_TOP_2);
      gen(ModBlocks.SKELETON_TOP_2);
      boneLoot(ModBlocks.SKELETON_TOP_3);
      gen(ModBlocks.SKELETON_TOP_3);
      boneLoot(ModBlocks.SKELETON_TOP_4);
      gen(ModBlocks.SKELETON_TOP_4);

      for (RegistryObject<? extends Block> block : BlockData.getAllBlocks()) {
        if (doneBlocks.contains(block)) {
          continue;
        }

        dropSelf(block.get());
        gen(block);
      }

      validate();
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
      return doneBlocks.stream().map(RegistryObject::get).collect(Collectors.toList());
    }
  }
}
