package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.RegistryManager;
import epicsquid.mysticalworld.config.ConfigManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;

import java.util.HashSet;
import java.util.Set;

public class ModEntities {

  public static void registerMobSpawns() {

    Set<Biome> biomes = new HashSet<>();

    if (ConfigManager.DEER_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.DEER_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(RegistryManager.DEER, ConfigManager.DEER_CONFIG.getChance(), ConfigManager.DEER_CONFIG.getMin(),
              ConfigManager.DEER_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.SPROUT_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.SPROUT_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(RegistryManager.SPROUT, ConfigManager.SPROUT_CONFIG.getChance(), ConfigManager.SPROUT_CONFIG.getMin(),
              ConfigManager.SPROUT_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.BEETLE_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.BEETLE_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(RegistryManager.BEETLE, ConfigManager.BEETLE_CONFIG.getChance(), ConfigManager.BEETLE_CONFIG.getMin(),
              ConfigManager.BEETLE_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.FROG_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.FROG_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(RegistryManager.FROG, ConfigManager.FROG_CONFIG.getChance(), ConfigManager.FROG_CONFIG.getMin(),
              ConfigManager.FROG_CONFIG.getMax())));
    }

    biomes.clear();

    if (ConfigManager.SILVER_FOX_CONFIG.shouldRegister()) {
      for (String biomeName : ConfigManager.SILVER_FOX_CONFIG.getBiomes()) {
        biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.getType(biomeName)));
      }
      biomes.forEach(biome -> biome.getSpawns(EntityClassification.CREATURE).add(
          new Biome.SpawnListEntry(RegistryManager.SILVER_FOX, ConfigManager.SILVER_FOX_CONFIG.getChance(), ConfigManager.SILVER_FOX_CONFIG.getMin(),
              ConfigManager.SILVER_FOX_CONFIG.getMax())));
    }

    biomes.clear();

    EntitySpawnPlacementRegistry.register(RegistryManager.DEER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(RegistryManager.FROG, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(RegistryManager.SPROUT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(RegistryManager.SILVER_FOX, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
    EntitySpawnPlacementRegistry.register(RegistryManager.BEETLE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
        AnimalEntity::func_223316_b);
  }
}
