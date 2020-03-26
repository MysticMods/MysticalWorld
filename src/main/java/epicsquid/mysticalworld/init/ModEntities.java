package epicsquid.mysticalworld.init;

import com.tterrag.registrate.util.LazySpawnEggItem;
import com.tterrag.registrate.util.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static epicsquid.mysticalworld.MysticalWorld.REGISTRATE;

// TODO: Entities need to convert to Registrate

@SuppressWarnings({"WeakerAccess", "ConstantConditions", "unchecked"})
public class ModEntities {
  private static <E extends Entity> NonNullFunction<Item.Properties, LazySpawnEggItem<E>> spawnEgg(RegistryEntry<EntityType<E>> entity, int color1, int color2) {
    return properties -> new LazySpawnEggItem<>(entity, color1, color2, properties);
  }

  public static RegistryEntry<EntityType<BeetleEntity>> BEETLE = REGISTRATE.entity("beetle", BeetleEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<DeerEntity>> DEER = REGISTRATE.entity("deer", DeerEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(1.0f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<FrogEntity>> FROG = REGISTRATE.entity("frog", FrogEntity::new, EntityClassification.AMBIENT)
      .properties(o -> o.size(0.5f, 0.5f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilverFoxEntity>> SILVER_FOX = REGISTRATE.entity("silver_fox", SilverFoxEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.75f, 0.75f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SproutEntity>> SPROUT = REGISTRATE.entity("sprout", SproutEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.5f, 1.0f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<EnderminiEntity>> ENDERMINI = REGISTRATE.entity("endermini", EnderminiEntity::new, EntityClassification.MONSTER)
      .properties(o -> o.size(0.3f, 1.45f).setTrackingRange(32).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<LavaCatEntity>> LAVA_CAT = REGISTRATE.entity("lava_cat", LavaCatEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.75f, 0.875f).setTrackingRange(34).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<OwlEntity>> OWL = REGISTRATE.entity("owl", OwlEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.5f, 0.9f).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3))
      .register();

  public static RegistryEntry<EntityType<SilkwormEntity>> SILKWORM = REGISTRATE.entity("silkworm", SilkwormEntity::new, EntityClassification.CREATURE)
      .properties(o -> o.size(0.8f, 0.6f).setTrackingRange(5).setShouldReceiveVelocityUpdates(false).setUpdateInterval(20))
      .register();

  public static RegistryEntry<LazySpawnEggItem<BeetleEntity>> SPAWN_BEETLE = REGISTRATE.item("beetle_spawn_egg", spawnEgg(BEETLE, 0x418594, 0x211D15))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_BEETLE), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<DeerEntity>> SPAWN_DEER = REGISTRATE.item("deer_spawn_egg", spawnEgg(DEER, 0xa18458, 0x5e4d33))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_DEER), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<FrogEntity>> SPAWN_FROG = REGISTRATE.item("frog_spawn_egg", spawnEgg(FROG, 0x285234, 0xDBE697))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_FROG), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<SproutEntity>> SPAWN_SPROUT = REGISTRATE.item("sprout_spawn_egg", spawnEgg(SPROUT, 0xe8f442, 0xd11f5a))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_SPROUT), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<SilverFoxEntity>> SPAWN_SILVER_FOX = REGISTRATE.item("silver_fox_spawn_egg", spawnEgg(SILVER_FOX, 0x9e9088, 0xF5E0D3))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_SILVER_FOX), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<EnderminiEntity>> SPAWN_ENDERMINI = REGISTRATE.item("endermini_spawn_egg", spawnEgg(ENDERMINI, 0xa11e78, 0x650cbe))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_ENDERMINI), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<LavaCatEntity>> SPAWN_LAVA_CAT = REGISTRATE.item("lava_cat_spawn_egg", spawnEgg(LAVA_CAT, 0xde3535, 0xe89613))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_LAVA_CAT), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<OwlEntity>> SPAWN_OWL = REGISTRATE.item("owl_spawn_egg", spawnEgg(OWL, 0x8c654a, 0xdec9ba))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_OWL), "item/template_spawn_egg"))
      .register();

  public static RegistryEntry<LazySpawnEggItem<SilkwormEntity>> SPAWN_SILKWORM = REGISTRATE.item("silkworm_spawn_egg", spawnEgg(SILKWORM, 0xd1cecd, 0x635e5b))
      .model((ctx, p) -> p.withExistingParent(p.name(ModEntities.SPAWN_SILKWORM), "item/template_spawn_egg"))
      .register();

  public static void load() {
  }

  public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
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
      // For hell biomes they have to spawn as monsters
      // or at least they had to in 1.12...
      // TODO
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.MONSTER).add(
          new Biome.SpawnListEntry(LAVA_CAT.get(), ConfigManager.LAVA_CAT_CONFIG.getChance(), ConfigManager.LAVA_CAT_CONFIG.getMin(),
              ConfigManager.LAVA_CAT_CONFIG.getMax())));
    }

    EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(FROG.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(SPROUT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(SILVER_FOX.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(BEETLE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(OWL.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING, OwlEntity::placement);
    EntitySpawnPlacementRegistry.register(LAVA_CAT.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LavaCatEntity::placement);
  }
}
