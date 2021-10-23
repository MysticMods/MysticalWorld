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
      .properties(o -> o.sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .loot((p, e) -> p.add(e, LootTable.lootTable()
          .withPool(LootPool.lootPool()
              .add(ItemLootEntry.lootTableItem(ModItems.CARAPACE.get())
                  .apply(SetCount.setCount(RandomValueRange.between(1, 3)))
                  .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
              )
              .add(ItemLootEntry.lootTableItem(Items.SLIME_BALL)
                  .apply(SetCount.setCount(RandomValueRange.between(0, 1)))
                  .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 2)))
              )
              .setRolls(ConstantRange.exactly(1))
          )
      ))
      .register();

  public static RegistryEntry<EntityType<DeerEntity>> DEER = MysticalWorld.REGISTRATE.entity("deer", DeerEntity::new, EntityClassification.CREATURE)
      .loot((p, e) ->
          p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.LEATHER)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0, 3)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.VENISON.get())
                      .apply(SetCount.setCount(RandomValueRange.between(1, 3)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0, 1)))
                      .apply(Smelt.smelted()
                          .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.ANTLERS.get())
                      .apply(SetCount.setCount(ConstantRange.exactly(1)))
                      .when(HasHorns.builder())
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<FrogEntity>> FROG = MysticalWorld.REGISTRATE.entity("frog", FrogEntity::new, EntityClassification.AMBIENT)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.SLIME_BALL)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 2)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilverFoxEntity>> SILVER_FOX = MysticalWorld.REGISTRATE.entity("silver_fox", SilverFoxEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.PELT.get())
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 2)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<HellSproutEntity>> HELL_SPROUT = MysticalWorld.REGISTRATE.entity("hell_sprout", HellSproutEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.NETHER_WART)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).fireImmune())
      .register();

  public static RegistryEntry<EntityType<SproutEntity>> SPROUT = MysticalWorld.REGISTRATE.entity("sprout", SproutEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.MELON_SLICE)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                  )
                  .when(IsColor.builder("green"))
                  .setRolls(ConstantRange.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.AUBERGINE.get())
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                      .apply(Smelt.smelted()
                          .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColor.builder("purple"))
                  .setRolls(ConstantRange.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.BEETROOT)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                      .apply(Smelt.smelted()
                          .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColor.builder("red"))
                  .setRolls(ConstantRange.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.POTATO)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                      .apply(Smelt.smelted()
                          .when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColor.builder("tan"))
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<EnderminiEntity>> ENDERMINI = MysticalWorld.REGISTRATE.entity("endermini", EnderminiEntity::new, EntityClassification.MONSTER)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.YOUNG_PEARL.get())
                      .apply(SetCount.setCount(RandomValueRange.between(0, 1)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0, 3)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.3f, 1.45f).setTrackingRange(32).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<LavaCatEntity>> LAVA_CAT = MysticalWorld.REGISTRATE.entity("lava_cat", LavaCatEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.OBSIDIAN)
                      .apply(SetCount.setCount(ConstantRange.exactly(1)))
                      .when(IsObsidian.builder())
                      .when(RandomChance.randomChance(0.7f)))
                  .add(ItemLootEntry.lootTableItem(Items.COBBLESTONE)
                      .apply(SetCount.setCount(ConstantRange.exactly(1)))
                      .when(IsObsidian.builder()))
                  .add(ItemLootEntry.lootTableItem(Items.COBBLESTONE)
                      .apply(SetCount.setCount(ConstantRange.exactly(1)))
                      .when(IsLava.builder()))
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.75f, 0.875f).setTrackingRange(34).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<OwlEntity>> OWL = MysticalWorld.REGISTRATE.entity("owl", OwlEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.FEATHER)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 3)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilkwormEntity>> SILKWORM = MysticalWorld.REGISTRATE.entity("silkworm", SilkwormEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(ModItems.SILKWORM_EGG.get())
                      .apply(SetCount.setCount(RandomValueRange.between(0, 2)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      // TODO: CHANGE UPDaTE INTERVAL
      .properties(o -> o.sized(0.8f, 0.6f).setTrackingRange(5).setShouldReceiveVelocityUpdates(true).setUpdateInterval(20))
      .register();

  public static RegistryEntry<EntityType<DuckEntity>> DUCK = MysticalWorld.REGISTRATE.entity("duck", DuckEntity::new, EntityClassification.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(ItemLootEntry.lootTableItem(Items.FEATHER)
                      .apply(SetCount.setCount(RandomValueRange.between(0, 3)))
                      .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(1, 3)))
                  )
                  .setRolls(ConstantRange.exactly(1))
              )
          )
      )
      // TODO: ADJUST SIZE???
      .properties(o -> o.sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  static {
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("beetle_spawn_egg", spawnEgg(ModEntities.BEETLE, 0x418594, 0x211D15)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("deer_spawn_egg", spawnEgg(ModEntities.DEER, 0xa18458, 0x5e4d33)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("frog_spawn_egg", spawnEgg(ModEntities.FROG, 0x285234, 0xDBE697)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silver_fox_spawn_egg", spawnEgg(ModEntities.SILVER_FOX, 0x9e9088, 0xF5E0D3)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("sprout_spawn_egg", spawnEgg(ModEntities.SPROUT, 0xe8f442, 0xd11f5a)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("endermini_spawn_egg", spawnEgg(ModEntities.ENDERMINI, 0xa11e78, 0x650cbe)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("lava_cat_spawn_egg", spawnEgg(ModEntities.LAVA_CAT, 0xde3535, 0xe89613)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("owl_spawn_egg", spawnEgg(ModEntities.OWL, 0x8c654a, 0xdec9ba)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("silkworm_spawn_egg", spawnEgg(ModEntities.SILKWORM, 0xd1cecd, 0x635e5b)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("hell_sprout_spawn_egg", spawnEgg(ModEntities.HELL_SPROUT, 0x8a0303, 0xe8f442)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
    SPAWN_EGGS.add(MysticalWorld.REGISTRATE.item("duck_spawn_egg", spawnEgg(ModEntities.DUCK, 0xe4d6a5, 0xe9ad36)).properties((p) -> p.tab(ItemGroup.TAB_MISC)).model((ctx, prov) -> prov.withExistingParent(ctx.getName(), new ResourceLocation("item/template_spawn_egg"))).register());
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
    configMap.put(ModEntities.DUCK, ConfigManager.DUCK_CONFIG);
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
    EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    EntitySpawnPlacementRegistry.register(FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    EntitySpawnPlacementRegistry.register(SPROUT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    EntitySpawnPlacementRegistry.register(SILVER_FOX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    EntitySpawnPlacementRegistry.register(BEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    EntitySpawnPlacementRegistry.register(OWL.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING, OwlEntity::placement);
    EntitySpawnPlacementRegistry.register(LAVA_CAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
    EntitySpawnPlacementRegistry.register(HELL_SPROUT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
    EntitySpawnPlacementRegistry.register(ENDERMINI.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EnderminiEntity::checkMonsterSpawnRules);
    EntitySpawnPlacementRegistry.register(DUCK.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
  }

  public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(ModEntities.BEETLE.get(), BeetleEntity.attributes().build());
    event.put(ModEntities.DEER.get(), DeerEntity.attributes().build());
    event.put(ModEntities.FROG.get(), FrogEntity.attributes().build());
    event.put(ModEntities.SILVER_FOX.get(), SilverFoxEntity.attributes().build());
    event.put(ModEntities.SPROUT.get(), SproutEntity.attributes().build());
    event.put(ModEntities.ENDERMINI.get(), EnderminiEntity.attributes().build());
    event.put(ModEntities.LAVA_CAT.get(), LavaCatEntity.attributes().build());
    event.put(ModEntities.OWL.get(), OwlEntity.attributes().build());
    event.put(ModEntities.SILKWORM.get(), SilkwormEntity.attributes().build());
    event.put(ModEntities.HELL_SPROUT.get(), HellSproutEntity.attributes().build());
    event.put(ModEntities.DUCK.get(), DuckEntity.attributes().build());
  }
}
