package mysticmods.mysticalworld.init;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.MobConfig;
import mysticmods.mysticalworld.entity.*;
import mysticmods.mysticalworld.loot.conditions.HasHorns;
import mysticmods.mysticalworld.loot.conditions.IsColor;
import mysticmods.mysticalworld.loot.conditions.IsLava;
import mysticmods.mysticalworld.loot.conditions.IsObsidian;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.*;

// TODO: Entities need to convert to Registrate

@SuppressWarnings({"WeakerAccess", "ConstantConditions", "unchecked", "deprecation"})
public class ModEntities {
  private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int color1, int color2) {
    return properties -> new LazySpawnEggItem<>(entity, color1, color2, properties);
  }

  public static List<RegistryEntry<? extends LazySpawnEggItem<?>>> SPAWN_EGGS = new ArrayList<>();

  public static RegistryEntry<EntityType<BeetleEntity>> BEETLE = MysticalWorld.REGISTRATE.entity("beetle", BeetleEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<DeerEntity>> DEER = MysticalWorld.REGISTRATE.entity("deer", DeerEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<FrogEntity>> FROG = MysticalWorld.REGISTRATE.entity("frog", FrogEntity::new, EntityClassification.AMBIENT)
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

  public static RegistryEntry<EntityType<SilverFoxEntity>> SILVER_FOX = MysticalWorld.REGISTRATE.entity("silver_fox", SilverFoxEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<HellSproutEntity>> HELL_SPROUT = MysticalWorld.REGISTRATE.entity("hell_sprout", HellSproutEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<SproutEntity>> SPROUT = MysticalWorld.REGISTRATE.entity("sprout", SproutEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<EnderminiEntity>> ENDERMINI = MysticalWorld.REGISTRATE.entity("endermini", EnderminiEntity::new, EntityClassification.MONSTER)
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

  public static RegistryEntry<EntityType<LavaCatEntity>> LAVA_CAT = MysticalWorld.REGISTRATE.entity("lava_cat", LavaCatEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<OwlEntity>> OWL = MysticalWorld.REGISTRATE.entity("owl", OwlEntity::new, EntityClassification.CREATURE)
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

  public static RegistryEntry<EntityType<SilkwormEntity>> SILKWORM = MysticalWorld.REGISTRATE.entity("silkworm", SilkwormEntity::new, EntityClassification.CREATURE)
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

  static {
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("beetle_spawn_egg", spawnEgg(ModEntities.BEETLE, 0x418594, 0x211D15)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("deer_spawn_egg", spawnEgg(ModEntities.DEER, 0xa18458, 0x5e4d33)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("frog_spawn_egg", spawnEgg(ModEntities.FROG, 0x285234, 0xDBE697)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silver_fox_spawn_egg", spawnEgg(ModEntities.SILVER_FOX, 0x9e9088, 0xF5E0D3)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("sprout_spawn_egg", spawnEgg(ModEntities.SPROUT, 0xe8f442, 0xd11f5a)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("endermini_spawn_egg", spawnEgg(ModEntities.ENDERMINI, 0xa11e78, 0x650cbe)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("lava_cat_spawn_egg", spawnEgg(ModEntities.LAVA_CAT, 0xde3535, 0xe89613)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("owl_spawn_egg", spawnEgg(ModEntities.OWL, 0x8c654a, 0xdec9ba)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silkworm_spawn_egg", spawnEgg(ModEntities.SILKWORM, 0xd1cecd, 0x635e5b)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("hell_sprout_spawn_egg", spawnEgg(ModEntities.HELL_SPROUT, 0x8a0303, 0xe8f442)).properties((p) -> p.group(ItemGroup.MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
  }

  public static BiMap<RegistryEntry<? extends EntityType<?>>, MobConfig> configMap = HashBiMap.create();

  static {
    configMap.put(ModEntities.BEETLE, ConfigManager.BEETLE_CONFIG);
    configMap.put(ModEntities.DEER, ConfigManager.DEER_CONFIG);
    configMap.put(ModEntities.FROG, ConfigManager.FROG_CONFIG);
    configMap.put(ModEntities.SILVER_FOX, ConfigManager.SILVER_FOX_CONFIG);
    configMap.put(ModEntities.SPROUT, ConfigManager.SPROUT_CONFIG);
    configMap.put(ModEntities.ENDERMINI, ConfigManager.ENDERMINI_CONFIG);
    configMap.put(ModEntities.LAVA_CAT, ConfigManager.LAVA_CAT_CONFIG);
    configMap.put(ModEntities.OWL, ConfigManager.OWL_CONFIG);
    configMap.put(ModEntities.HELL_SPROUT, ConfigManager.HELL_SPROUT_CONFIG);
  }

  public static void registerEntity(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types) {
    for (Map.Entry<RegistryEntry<? extends EntityType<?>>, MobConfig> entry : configMap.entrySet()) {
      MobConfig conf = entry.getValue();
      if (conf.shouldRegister()) {
        Set<BiomeDictionary.Type> types2 = new HashSet<>(types);
        types2.retainAll(conf.getBiomes());
        if (!types2.isEmpty()) {
          if (conf.getRestriction() == BiomeDictionary.Type.NETHER && event.getCategory() == Biome.Category.NETHER) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          } else if (conf.getRestriction() == BiomeDictionary.Type.OVERWORLD && event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          } else if (conf.getRestriction() == BiomeDictionary.Type.END && event.getCategory() == Biome.Category.THEEND) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          }
        }
      }
    }
    if (event.getName() != null && event.getName().getNamespace().equals("mysticalbiomes") && event.getName().getPath().contains("sprout")) {
      event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(ModEntities.SPROUT.get(), 18, 2, 8));
    }
  }

  public static void load() {
  }

  public static void registerEntities() {
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

  public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(ModEntities.BEETLE.get(), BeetleEntity.attributes().create());
    event.put(ModEntities.DEER.get(), DeerEntity.attributes().create());
    event.put(ModEntities.FROG.get(), FrogEntity.attributes().create());
    event.put(ModEntities.SILVER_FOX.get(), SilverFoxEntity.attributes().create());
    event.put(ModEntities.SPROUT.get(), SproutEntity.attributes().create());
    event.put(ModEntities.ENDERMINI.get(), EnderminiEntity.attributes().create());
    event.put(ModEntities.LAVA_CAT.get(), LavaCatEntity.attributes().create());
    event.put(ModEntities.OWL.get(), OwlEntity.attributes().create());
    event.put(ModEntities.SILKWORM.get(), SilkwormEntity.attributes().create());
    event.put(ModEntities.HELL_SPROUT.get(), HellSproutEntity.attributes().create());
  }
}