package epicsquid.mysticalworld.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import epicsquid.mysticallib.data.DeferredBlockLootTableProvider;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationResults;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
public class MWLootTableProvider extends LootTableProvider {
  public MWLootTableProvider(DataGenerator dataGeneratorIn) {
    super(dataGeneratorIn);
  }

  @Override
  public String getName() {
    return "Mystical World Loot Table Provider";
  }

  @Override
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
    return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK));
  }

  @Override
  protected void validate(Map<ResourceLocation, LootTable> map, ValidationResults validationresults) {
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
      self(ModBlocks.MUD_BLOCK_STAIRS);
      self(ModBlocks.MUD_BLOCK_SLAB);
      self(ModBlocks.MUD_BLOCK_WALL);
      self(ModBlocks.MUD_BLOCK_FENCE);
      self(ModBlocks.MUD_BLOCK_FENCE_GATE);

      self(ModBlocks.MUD_BRICK);
      self(ModBlocks.MUD_BRICK_STAIRS);
      self(ModBlocks.MUD_BRICK_SLAB);
      self(ModBlocks.MUD_BRICK_WALL);
      self(ModBlocks.MUD_BRICK_FENCE);
      self(ModBlocks.MUD_BRICK_FENCE_GATE);

      self(ModBlocks.CHARRED_FENCE);
      self(ModBlocks.CHARRED_FENCE_GATE);
      self(ModBlocks.CHARRED_LOG);
      self(ModBlocks.CHARRED_PLANKS);
      self(ModBlocks.CHARRED_SLAB);
      self(ModBlocks.CHARRED_STAIRS);
      self(ModBlocks.CHARRED_WALL);

      self(ModBlocks.TERRACOTTA_BRICK);
      self(ModBlocks.TERRACOTTA_BRICK_STAIRS);
      self(ModBlocks.TERRACOTTA_BRICK_SLAB);
      self(ModBlocks.TERRACOTTA_BRICK_WALL);
      self(ModBlocks.TERRACOTTA_BRICK_FENCE);
      self(ModBlocks.TERRACOTTA_BRICK_FENCE_GATE);

      self(ModBlocks.THATCH);

      registerLootTable(ModBlocks.AUBERGINE_CROP.get(), b -> droppingAndBonusWhen(b, ModItems.AUBERGINE.get(), ModItems.AUBERGINE_SEEDS.get(), BlockStateProperty.builder(ModBlocks.AUBERGINE_CROP.get()).with(CropsBlock.AGE, 7)));

      orePieces(ModBlocks.AMETHYST_ORE, ModItems.AMETHYST_GEM);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
      return MysticalWorld.REGISTRY.getBlocks().stream().map(Supplier::get).collect(Collectors.toList());
    }
  }
}
