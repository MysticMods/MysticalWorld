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
import mysticmods.mysticalworld.loot.conditions.*;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@SuppressWarnings({"WeakerAccess", "ConstantConditions", "unchecked", "deprecation"})
@Mod.EventBusSubscriber(modid = MysticalWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
  private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int color1, int color2) {
    return properties -> new LazySpawnEggItem<>(entity, color1, color2, properties);
  }

  public static List<RegistryEntry<? extends LazySpawnEggItem<?>>> SPAWN_EGGS = new ArrayList<>();

  public static RegistryEntry<EntityType<BeetleEntity>> BEETLE = MysticalWorld.REGISTRATE.entity("beetle", BeetleEntity::new, MobCategory.CREATURE)
      .properties(o -> o.sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .loot((p, e) -> p.add(e, LootTable.lootTable()
          .withPool(LootPool.lootPool()
              .add(LootItem.lootTableItem(ModItems.CARAPACE.get())
                  .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                  .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(2, 4)))
              )
              .add(LootItem.lootTableItem(Items.SLIME_BALL)
                  .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                  .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 2)))
              )
              .setRolls(ConstantValue.exactly(1))
          )
      ))
      .register();

  public static RegistryEntry<EntityType<DeerEntity>> DEER = MysticalWorld.REGISTRATE.entity("deer", DeerEntity::new, MobCategory.CREATURE)
      .loot((p, e) ->
          p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.LEATHER)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 3)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.VENISON.get())
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 1)))
                      .apply(SmeltItemFunction.smelted()
                          .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.ANTLERS.get())
                      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                      .when(HasHornsCondition.builder())
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<FrogEntity>> FROG = MysticalWorld.REGISTRATE.entity("frog", FrogEntity::new, MobCategory.AMBIENT)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.SLIME_BALL)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 2)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilverFoxEntity>> SILVER_FOX = MysticalWorld.REGISTRATE.entity("silver_fox", SilverFoxEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.PELT.get())
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 2)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<HellSproutEntity>> HELL_SPROUT = MysticalWorld.REGISTRATE.entity("hell_sprout", HellSproutEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.NETHER_WART)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).fireImmune())
      .register();

  public static RegistryEntry<EntityType<SproutEntity>> SPROUT = MysticalWorld.REGISTRATE.entity("sprout", SproutEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.MELON_SLICE)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                  )
                  .when(IsColorCondition.builder("green"))
                  .setRolls(ConstantValue.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.AUBERGINE.get())
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                      .apply(SmeltItemFunction.smelted()
                          .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColorCondition.builder("purple"))
                  .setRolls(ConstantValue.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.BEETROOT)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                      .apply(SmeltItemFunction.smelted()
                          .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColorCondition.builder("red"))
                  .setRolls(ConstantValue.exactly(1))
              )
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.POTATO)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                      .apply(SmeltItemFunction.smelted()
                          .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build()))))
                  )
                  .when(IsColorCondition.builder("tan"))
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<EnderminiEntity>> ENDERMINI = MysticalWorld.REGISTRATE.entity("endermini", EnderminiEntity::new, MobCategory.MONSTER)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.YOUNG_PEARL.get())
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0, 3)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.3f, 1.45f).setTrackingRange(32).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<LavaCatEntity>> LAVA_CAT = MysticalWorld.REGISTRATE.entity("lava_cat", LavaCatEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.OBSIDIAN)
                      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                      .when(IsObsidianCondition.builder())
                      .when(LootItemRandomChanceCondition.randomChance(0.7f)))
                  .add(LootItem.lootTableItem(Items.COBBLESTONE)
                      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                      .when(IsObsidianCondition.builder()))
                  .add(LootItem.lootTableItem(Items.COBBLESTONE)
                      .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                      .when(IsLavaCondition.builder()))
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.75f, 0.875f).setTrackingRange(34).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<OwlEntity>> OWL = MysticalWorld.REGISTRATE.entity("owl", OwlEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.FEATHER)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilkwormEntity>> SILKWORM = MysticalWorld.REGISTRATE.entity("silkworm", SilkwormEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(ModItems.SILKWORM_EGG.get())
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.8f, 0.6f).setTrackingRange(5).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<DuckEntity>> DUCK = MysticalWorld.REGISTRATE.entity("duck", DuckEntity::new, MobCategory.CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
              .withPool(LootPool.lootPool()
                  .add(LootItem.lootTableItem(Items.FEATHER)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 3)))
                      .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1, 3)))
                  )
                  .setRolls(ConstantValue.exactly(1))
              )
          )
      )
      .properties(o -> o.sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<ClamEntity>> CLAM = MysticalWorld.REGISTRATE.entity("clam", ClamEntity::new, MobCategory.WATER_CREATURE)
      .loot((p, e) -> p.add(e, LootTable.lootTable()
          .withPool(LootPool.lootPool()
              .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                  .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                  .when(IsMatureCondition.builder())
                  .when(IsEnderCondition.builder())
              )
              .setRolls(ConstantValue.exactly(1))
          )
          .withPool(LootPool.lootPool()
              .add(LootItem.lootTableItem(ModItems.PEARL_GEM.get())
                  .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                  .when(IsMatureCondition.builder())
                  .when(IsEnderCondition.builder().invert()))
              .setRolls(ConstantValue.exactly(1)))
      ))
      .properties(o -> o.sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

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
    configMap.put(ModEntities.CLAM, ConfigManager.CLAM_CONFIG);
  }

  public static void registerEntity(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types) {
    for (Map.Entry<RegistryEntry<? extends EntityType<?>>, MobConfig> entry : configMap.entrySet()) {
      MobConfig conf = entry.getValue();
      if (conf.shouldRegister()) {
        Set<BiomeDictionary.Type> types2 = new HashSet<>(types);
        types2.retainAll(conf.getBiomes());
        if (!types2.isEmpty()) {
          if (conf.getRestriction() == BiomeDictionary.Type.NETHER && event.getCategory() == Biome.BiomeCategory.NETHER) {
            event.getSpawns().getSpawner(conf.getClassification()).add(new MobSpawnSettings.SpawnerData(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          } else if (conf.getRestriction() == BiomeDictionary.Type.OVERWORLD && event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
            event.getSpawns().getSpawner(conf.getClassification()).add(new MobSpawnSettings.SpawnerData(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          } else if (conf.getRestriction() == BiomeDictionary.Type.END && event.getCategory() == Biome.BiomeCategory.THEEND) {
            event.getSpawns().getSpawner(conf.getClassification()).add(new MobSpawnSettings.SpawnerData(entry.getKey().get(), conf.getChance(), conf.getMin(), conf.getMax()));
          }
        }
      }
    }
    if (event.getName() != null && event.getName().getNamespace().equals("mysticalbiomes") && event.getName().getPath().contains("sprout")) {
      event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.SPROUT.get(), 18, 2, 8));
    }
  }

  public static void registerEntities() {
    SpawnPlacements.register(DEER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(FROG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(SPROUT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(SILVER_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(BEETLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(OWL.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING, OwlEntity::placement);
    SpawnPlacements.register(LAVA_CAT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
    SpawnPlacements.register(HELL_SPROUT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
    SpawnPlacements.register(ENDERMINI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EnderminiEntity::checkMonsterSpawnRules);
    SpawnPlacements.register(DUCK.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    SpawnPlacements.register(CLAM.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ClamEntity::checkClamSpawnRules);
  }

  @SubscribeEvent
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
    event.put(ModEntities.CLAM.get(), ClamEntity.attributes().build());
  }

  public static void load() {
  }
}
