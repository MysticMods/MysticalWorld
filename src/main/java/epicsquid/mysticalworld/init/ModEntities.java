package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.loot.conditions.HasHorns;
import epicsquid.mysticalworld.loot.conditions.IsColor;
import epicsquid.mysticalworld.loot.conditions.IsLava;
import epicsquid.mysticalworld.loot.conditions.IsObsidian;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.EntityHasProperty;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.Smelt;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Entities need to convert to Registrate

@SuppressWarnings({"WeakerAccess", "ConstantConditions", "unchecked", "deprecation"})
public class ModEntities {
  private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int color1, int color2) {
    return properties -> new LazySpawnEggItem<>(entity, color1, color2, properties);
  }

  public static List<RegistryEntry<? extends LazySpawnEggItem<?>>> SPAWN_EGGS = new ArrayList<>();

  public static RegistryEntry<EntityType<BeetleEntity>> BEETLE = REGISTRATE.entity("beetle", BeetleEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
          .addLootPool(LootPool.builder()
              .addEntry(ItemLootEntry.builder(ModItems.CARAPACE.get())
                  .acceptFunction(SetCount.builder(RandomValueRange.of(0, 1)))
                  .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 2)))
              )
              .addEntry(ItemLootEntry.builder(Items.SLIME_BALL)
                  .acceptFunction(SetCount.builder(RandomValueRange.of(0, 1)))
                  .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 2)))
              )
              .rolls(ConstantRange.of(1))
          )
      ))
      .register();

  public static RegistryEntry<EntityType<DeerEntity>> DEER = REGISTRATE.entity("deer", DeerEntity::new, EntityClassification.CREATURE)
      .loot((p, e) ->
          p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.LEATHER)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 3)))
                  )
                  .rolls(ConstantRange.of(1))
              )
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.VENISON.get())
                      .acceptFunction(SetCount.builder(RandomValueRange.of(1, 3)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 1)))
                      .acceptFunction(Smelt.func_215953_b()
                          .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))))
                  )
                  .rolls(ConstantRange.of(1))
              )
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.ANTLERS.get())
                      .acceptFunction(SetCount.builder(ConstantRange.of(1)))
                      .acceptCondition(HasHorns.builder())
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<FrogEntity>> FROG = REGISTRATE.entity("frog", FrogEntity::new, EntityClassification.AMBIENT)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.SLIME_BALL)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 2)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilverFoxEntity>> SILVER_FOX = REGISTRATE.entity("silver_fox", SilverFoxEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.PELT.get())
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 2)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<HellSproutEntity>> HELL_SPROUT = REGISTRATE.entity("hell_sprout", HellSproutEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.NETHER_WART)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).immuneToFire())
      .register();

  public static RegistryEntry<EntityType<SproutEntity>> SPROUT = REGISTRATE.entity("sprout", SproutEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.MELON_SLICE)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                  )
                  .acceptCondition(IsColor.builder("green"))
                  .rolls(ConstantRange.of(1))
              )
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.AUBERGINE.get())
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                      .acceptFunction(Smelt.func_215953_b()
                          .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))))
                  )
                  .acceptCondition(IsColor.builder("purple"))
                  .rolls(ConstantRange.of(1))
              )
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.BEETROOT)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                      .acceptFunction(Smelt.func_215953_b()
                          .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))))
                  )
                  .acceptCondition(IsColor.builder("red"))
                  .rolls(ConstantRange.of(1))
              )
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.POTATO)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                      .acceptFunction(Smelt.func_215953_b()
                          .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))))
                  )
                  .acceptCondition(IsColor.builder("tan"))
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<EnderminiEntity>> ENDERMINI = REGISTRATE.entity("endermini", EnderminiEntity::new, EntityClassification.MONSTER)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.YOUNG_PEARL.get())
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 1)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0, 3)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.3f, 1.45f).setTrackingRange(32).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<LavaCatEntity>> LAVA_CAT = REGISTRATE.entity("lava_cat", LavaCatEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.OBSIDIAN)
                      .acceptFunction(SetCount.builder(ConstantRange.of(1)))
                      .acceptCondition(IsObsidian.builder())
                      .acceptCondition(RandomChance.builder(0.7f)))
                  .addEntry(ItemLootEntry.builder(Items.COBBLESTONE)
                      .acceptFunction(SetCount.builder(ConstantRange.of(1)))
                      .acceptCondition(IsObsidian.builder()))
                  .addEntry(ItemLootEntry.builder(Items.COBBLESTONE)
                      .acceptFunction(SetCount.builder(ConstantRange.of(1)))
                      .acceptCondition(IsLava.builder()))
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.75f, 0.875f).setTrackingRange(34).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<OwlEntity>> OWL = REGISTRATE.entity("owl", OwlEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(Items.FEATHER)
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 3)))
                      .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(1, 3)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilkwormEntity>> SILKWORM = REGISTRATE.entity("silkworm", SilkwormEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()
              .addLootPool(LootPool.builder()
                  .addEntry(ItemLootEntry.builder(ModItems.SILKWORM_EGG.get())
                      .acceptFunction(SetCount.builder(RandomValueRange.of(0, 2)))
                  )
                  .rolls(ConstantRange.of(1))
              )
          )
      )
      .properties(o -> o.size(0.8f, 0.6f).setTrackingRange(5).setShouldReceiveVelocityUpdates(false).setUpdateInterval(20))
      .register();

  public static RegistryEntry<EntityType<SpiritDeerEntity>> SPIRIT_DEER = REGISTRATE.entity("spirit_deer", SpiritDeerEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()))
      .properties(o -> o.size(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SpiritBeetleEntity>> SPIRIT_BEETLE = REGISTRATE.entity("spirit_beetle", SpiritBeetleEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.registerLootTable(e, LootTable.builder()))
      .properties(o -> o.size(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  static {
    SPAWN_EGGS.add(REGISTRATE.item("beetle_spawn_egg", spawnEgg(ModEntities.BEETLE, 0x418594, 0x211D15)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("deer_spawn_egg", spawnEgg(ModEntities.DEER, 0xa18458, 0x5e4d33)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("frog_spawn_egg", spawnEgg(ModEntities.FROG, 0x285234, 0xDBE697)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("silver_fox_spawn_egg", spawnEgg(ModEntities.SILVER_FOX, 0x9e9088, 0xF5E0D3)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("sprout_spawn_egg", spawnEgg(ModEntities.SPROUT, 0xe8f442, 0xd11f5a)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("endermini_spawn_egg", spawnEgg(ModEntities.ENDERMINI, 0xa11e78, 0x650cbe)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("lava_cat_spawn_egg", spawnEgg(ModEntities.LAVA_CAT, 0xde3535, 0xe89613)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("owl_spawn_egg", spawnEgg(ModEntities.OWL, 0x8c654a, 0xdec9ba)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("silkworm_spawn_egg", spawnEgg(ModEntities.SILKWORM, 0xd1cecd, 0x635e5b)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(REGISTRATE.item("hell_sprout_spawn_egg", spawnEgg(ModEntities.HELL_SPROUT, 0x8a0303, 0xe8f442)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
  }

  public static void load() {
  }

  public static void registerEntities() {
    Set<Biome> biomes = new HashSet<>();

    if (ConfigManager.DEER_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.DEER_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(DEER.get(), ConfigManager.DEER_CONFIG.getChance(), ConfigManager.DEER_CONFIG.getMin(),
              ConfigManager.DEER_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.SPROUT_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.SPROUT_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(SPROUT.get(), ConfigManager.SPROUT_CONFIG.getChance(), ConfigManager.SPROUT_CONFIG.getMin(),
              ConfigManager.SPROUT_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.BEETLE_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.BEETLE_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(BEETLE.get(), ConfigManager.BEETLE_CONFIG.getChance(), ConfigManager.BEETLE_CONFIG.getMin(),
              ConfigManager.BEETLE_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.FROG_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.FROG_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(FROG.get(), ConfigManager.FROG_CONFIG.getChance(), ConfigManager.FROG_CONFIG.getMin(),
              ConfigManager.FROG_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.SILVER_FOX_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.SILVER_FOX_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(SILVER_FOX.get(), ConfigManager.SILVER_FOX_CONFIG.getChance(), ConfigManager.SILVER_FOX_CONFIG.getMin(),
              ConfigManager.SILVER_FOX_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.ENDERMINI_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.ENDERMINI_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.MONSTER).add(
          new Biome.SpawnListEntry(ENDERMINI.get(), ConfigManager.ENDERMINI_CONFIG.getChance(), ConfigManager.ENDERMINI_CONFIG.getMin(), ConfigManager.ENDERMINI_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.OWL_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.OWL_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(OWL.get(), ConfigManager.OWL_CONFIG.getChance(), ConfigManager.OWL_CONFIG.getMin(),
              ConfigManager.OWL_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.LAVA_CAT_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.LAVA_CAT_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.MONSTER).add(
          new Biome.SpawnListEntry(LAVA_CAT.get(), ConfigManager.LAVA_CAT_CONFIG.getChance(), ConfigManager.LAVA_CAT_CONFIG.getMin(),
              ConfigManager.LAVA_CAT_CONFIG.getMax())));
    }

    if (ConfigManager.HELL_SPROUT_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.HELL_SPROUT_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.MONSTER).add(
          new Biome.SpawnListEntry(HELL_SPROUT.get(), ConfigManager.HELL_SPROUT_CONFIG.getChance(), ConfigManager.HELL_SPROUT_CONFIG.getMin(),
              ConfigManager.HELL_SPROUT_CONFIG.getMax())));
    }

    biomes.clear();

    EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::canAnimalSpawn);
    EntitySpawnPlacementRegistry.register(FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::canAnimalSpawn);
    EntitySpawnPlacementRegistry.register(SPROUT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::canAnimalSpawn);
    EntitySpawnPlacementRegistry.register(SILVER_FOX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::canAnimalSpawn);
    EntitySpawnPlacementRegistry.register(BEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::canAnimalSpawn);
    EntitySpawnPlacementRegistry.register(OWL.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING, OwlEntity::placement);
    EntitySpawnPlacementRegistry.register(LAVA_CAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
    EntitySpawnPlacementRegistry.register(HELL_SPROUT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
  }
}
