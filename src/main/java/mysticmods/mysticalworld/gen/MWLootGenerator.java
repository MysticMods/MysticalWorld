package mysticmods.mysticalworld.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mysticmods.mysticalworld.init.deferred.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
      dropSelf(ModBlocks.UNCANNY_GRAVEL.get());
      dropSelf(ModBlocks.UNCANNY_SAND.get());
    }
  }
}
