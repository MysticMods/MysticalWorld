package epicsquid.mysticalworld.init;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public class ModEntities {
  private static final String BEETLE_ID = "beetle";
  private static final String DEER_ID = "deer";
  private static final String FROG_ID = "frog";
  private static final String SILVER_FOX_ID = "silver_fox";
  private static final String SPROUT_ID = "sprout";

  public static RegistryObject<EntityType<BeetleEntity>> BEETLE = ModRegistries.registerEntity(BEETLE_ID, ModRegistries.entity(BEETLE_ID, () -> EntityType.Builder.create(BeetleEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)));
  public static RegistryObject<EntityType<DeerEntity>> DEER = ModRegistries.registerEntity(DEER_ID, ModRegistries.entity(DEER_ID, () -> EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(1.0f, 1.0f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)));
  public static RegistryObject<EntityType<FrogEntity>> FROG = ModRegistries.registerEntity(FROG_ID, ModRegistries.entity(FROG_ID, () -> EntityType.Builder.create(FrogEntity::new, EntityClassification.AMBIENT).size(0.5f, 0.5f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)));
  public static RegistryObject<EntityType<FoxEntity>> SILVER_FOX = ModRegistries.registerEntity(SILVER_FOX_ID, ModRegistries.entity(SILVER_FOX_ID, () -> EntityType.Builder.create(FoxEntity::new, EntityClassification.CREATURE).size(0.75f, 0.75f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)));
  public static RegistryObject<EntityType<SproutEntity>> SPROUT = ModRegistries.registerEntity(SPROUT_ID, ModRegistries.entity(SPROUT_ID, () -> EntityType.Builder.create(SproutEntity::new, EntityClassification.CREATURE).size(0.5f, 1.0f).setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)));

  public static RegistryObject<SpawnEggItem> SPAWN_BEETLE = ModRegistries.registerItem(BEETLE_ID + "_spawn_egg", ModRegistries.spawnEgg(BEETLE, 0x418594, 0x211D15, ModRegistries.SIG));
  public static RegistryObject<SpawnEggItem> SPAWN_DEER = ModRegistries.registerItem(DEER_ID + "_spawn_egg", ModRegistries.spawnEgg(DEER, 0xa18458, 0x5e4d33, ModRegistries.SIG));
  public static RegistryObject<SpawnEggItem> SPAWN_FROG = ModRegistries.registerItem(FROG_ID + "_spawn_egg", ModRegistries.spawnEgg(FROG, 0x285234, 0xdbe697, ModRegistries.SIG));
  public static RegistryObject<SpawnEggItem> SPAWN_SPROUT = ModRegistries.registerItem(SPROUT_ID + "_spawn_egg", ModRegistries.spawnEgg(SPROUT, 0xe8f442, 0xd11f5a, ModRegistries.SIG));
  public static RegistryObject<SpawnEggItem> SPAWN_SILVER_FOX = ModRegistries.registerItem(SILVER_FOX_ID + "_spawn_egg", ModRegistries.spawnEgg(SILVER_FOX, 0xd46724, 0xf5e0d3, ModRegistries.SIG));

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
  }
}
