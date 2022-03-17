package mysticmods.mysticalworld.init.deferred;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;
import mysticmods.mysticalworld.MysticalWorld;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.config.MobConfig;
import mysticmods.mysticalworld.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModEntities {
  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MysticalWorld.MODID);
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MysticalWorld.MODID);
  public static BiMap<RegistryObject<? extends EntityType<?>>, MobConfig> configMap = HashBiMap.create();

  public static void register(IEventBus bus) {
    ENTITIES.register(bus);
    ITEMS.register(bus);
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

  public static final RegistryObject<EntityType<BeetleEntity>> BEETLE = ENTITIES.register("beetle", () -> EntityType.Builder.of(BeetleEntity::new, MobCategory.CREATURE).sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("beetle"));

  public static final RegistryObject<EntityType<DeerEntity>> DEER = ENTITIES.register("deer", () -> EntityType.Builder.of(DeerEntity::new, MobCategory.CREATURE).sized(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("deer"));

  public static final RegistryObject<EntityType<FrogEntity>> FROG = ENTITIES.register("frog", () -> EntityType.Builder.of(FrogEntity::new, MobCategory.AMBIENT).sized(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("frog"));

  public static final RegistryObject<EntityType<SilverFoxEntity>> SILVER_FOX = ENTITIES.register("silver_fox", () -> EntityType.Builder.of(SilverFoxEntity::new, MobCategory.CREATURE).sized(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("silver_fox"));

  public static final RegistryObject<EntityType<HellSproutEntity>> HELL_SPROUT = ENTITIES.register("hell_sprout", () -> EntityType.Builder.of(HellSproutEntity::new, MobCategory.CREATURE).sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).fireImmune().build("hell_sprout"));

  public static final RegistryObject<EntityType<SproutEntity>> SPROUT = ENTITIES.register("sprout", () -> EntityType.Builder.of(SproutEntity::new, MobCategory.CREATURE).sized(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("sprout"));

  public static final RegistryObject<EntityType<EnderminiEntity>> ENDERMINI = ENTITIES.register("endermini", () -> EntityType.Builder.of(EnderminiEntity::new, MobCategory.MONSTER).sized(0.3f, 1.45f).setTrackingRange(32).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("endermini"));

  public static final RegistryObject<EntityType<LavaCatEntity>> LAVA_CAT = ENTITIES.register("lava_cat", () -> EntityType.Builder.of(LavaCatEntity::new, MobCategory.CREATURE).sized(0.75f, 0.875f).setTrackingRange(34).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("lava_cat"));

  public static final RegistryObject<EntityType<OwlEntity>> OWL = ENTITIES.register("owl", () -> EntityType.Builder.of(OwlEntity::new, MobCategory.CREATURE).sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("owl"));

  public static final RegistryObject<EntityType<SilkwormEntity>> SILKWORM = ENTITIES.register("silkworm", () -> EntityType.Builder.of(SilkwormEntity::new, MobCategory.CREATURE).sized(0.8f, 0.6f).setTrackingRange(5).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("silkworm"));

  public static final RegistryObject<EntityType<DuckEntity>> DUCK = ENTITIES.register("duck", () -> EntityType.Builder.of(DuckEntity::new, MobCategory.CREATURE).sized(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("duck"));

  public static final RegistryObject<EntityType<ClamEntity>> CLAM = ENTITIES.register("clam", () -> EntityType.Builder.of(ClamEntity::new, MobCategory.WATER_CREATURE).sized(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).build("clam"));

  public static final RegistryObject<ForgeSpawnEggItem> BEETLE_SPAWN_EGG = ITEMS.register("beetle_spawn_egg", () -> new ForgeSpawnEggItem(BEETLE, 0x418594, 0x211D15, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> DEER_SPAWN_EGG = ITEMS.register("deer_spawn_egg", () -> new ForgeSpawnEggItem(DEER, 0xa18458, 0x5e4d33, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> FROG_SPAWN_EGG = ITEMS.register("frog_spawn_egg", () -> new ForgeSpawnEggItem(FROG, 0x285234, 0xDBE697, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> SILVER_FOX_SPAWN_EGG = ITEMS.register("silver_fox_spawn_egg", () -> new ForgeSpawnEggItem(SILVER_FOX, 0x9e9088, 0xF5E0D3, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> HELL_SPROUT_SPAWN_EGG = ITEMS.register("hell_sprout_spawn_egg", () -> new ForgeSpawnEggItem(HELL_SPROUT, 0x8a0303, 0xe8f442, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> SPROUT_SPAWN_EGG = ITEMS.register("sprout_spawn_egg", () -> new ForgeSpawnEggItem(SPROUT, 0xe8f442, 0xd11f5a, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> ENDERMINI_SPAWN_EGG = ITEMS.register("endermini_spawn_egg", () -> new ForgeSpawnEggItem(ENDERMINI, 0xa11e78, 0x650cbe, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> LAVA_CAT_SPAWN_EGG = ITEMS.register("lava_cat_spawn_egg", () -> new ForgeSpawnEggItem(LAVA_CAT, 0xde3535, 0xe89613, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> new ForgeSpawnEggItem(OWL, 0x8c654a, 0xdec9ba, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> SILKWORM_SPAWN_EGG = ITEMS.register("silkworm_spawn_egg", () -> new ForgeSpawnEggItem(SILKWORM, 0xd1cecd, 0x635e5b, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> DUCK_SPAWN_EGG = ITEMS.register("duck_spawn_egg", () -> new ForgeSpawnEggItem(DUCK, 0xe4d6a5, 0xe9ad36, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static final RegistryObject<ForgeSpawnEggItem> CLAM_SPAWN_EGG = ITEMS.register("clam_spawn_egg", () -> new ForgeSpawnEggItem(CLAM, 0xfffdd0, 0xfadadd, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

  public static Set<RegistryObject<? extends EntityType<?>>> getEntities() {
    return Sets.newHashSet(BEETLE, CLAM, DEER, BEETLE, DUCK, FROG, SILVER_FOX, HELL_SPROUT, ENDERMINI, SPROUT, SILKWORM, LAVA_CAT, OWL);
  }

  public static Set<RegistryObject<ForgeSpawnEggItem>> getSpawnEggs() {
    return Sets.newHashSet(BEETLE_SPAWN_EGG, CLAM_SPAWN_EGG, DEER_SPAWN_EGG, BEETLE_SPAWN_EGG, DUCK_SPAWN_EGG, FROG_SPAWN_EGG, SILVER_FOX_SPAWN_EGG, HELL_SPROUT_SPAWN_EGG, ENDERMINI_SPAWN_EGG, SPROUT_SPAWN_EGG, SILKWORM_SPAWN_EGG, LAVA_CAT_SPAWN_EGG, OWL_SPAWN_EGG);
  }


  static {

  }

  public static void registerEntity(BiomeLoadingEvent event, Set<BiomeDictionary.Type> types) {
    for (Map.Entry<RegistryObject<? extends EntityType<?>>, MobConfig> entry : configMap.entrySet()) {
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
}
