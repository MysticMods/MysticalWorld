package epicsquid.mysticalworld.init;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.entity.render.*;
import epicsquid.mysticalworld.proxy.ClientProxy;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ModEntities {

  public static void registerMobs() {
    //EGG COLORS GO: MAIN THEN SPOTS
    LibRegistry.registerEntity(EntityFox.class, 0xD46724, 0xF5E0D3);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityFox.class, new RenderFox.Factory());
    LibRegistry.registerEntity(EntityFrog.class, 0x285234, 0xDBE697);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityFrog.class, new RenderFrog.Factory());
    LibRegistry.registerEntity(EntityBeetle.class, 0x418594, 0x211D15);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityBeetle.class, new RenderBeetle.Factory());
    LibRegistry.registerEntity(EntitySprout.class, 0xe8f442, 0xd11f5a);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntitySprout.class, new RenderSprout.Factory());
    LibRegistry.registerEntity(EntityDeer.class, Util.intColor(161, 132, 88), Util.intColor(94, 77, 51));
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityDeer.class, new RenderDeer.Factory());
    LibRegistry.registerEntity(EntityEndermini.class, Util.intColor(161, 30, 120), Util.intColor(101, 12, 190));
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityEndermini.class, new RenderEndermini.Factory());
    LibRegistry.registerEntity(EntityOwl.class, 0x8c654a, 0xdec9ba);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityOwl.class, new RenderOwl.Factory());
    LibRegistry.registerEntity(EntityLavaCat.class, 0xde3535, 0xe89613);
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityLavaCat.class, new RenderLavaCat.Factory());
    LibRegistry.registerEntity(EntitySilkworm.class, 0xd1cecd, 0x635e5b );
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntitySilkworm.class, new RenderSilkworm.Factory());
  }

  public static void registerLootTables () {
    Stream.of(EntityFox.LOOT_TABLE, EntityFrog.LOOT_TABLE, EntityBeetle.LOOT_TABLE, EntitySprout.LOOT_TABLE_GREEN, EntitySprout.LOOT_TABLE_PURPLE, EntitySprout.LOOT_TABLE_RED, EntitySprout.LOOT_TABLE_TAN, EntityEndermini.LOOT_TABLE, EntityDeer.LOOT_TABLE, EntityOwl.LOOT_TABLE, EntityLavaCat.LOOT_TABLE, EntitySilkworm.LOOT_TABLE).forEach(LootTableList::register);
  }

  public static void registerMobSpawn() {
    Set<Biome> biomes = new HashSet<>();

    if (ConfigManager.mobs.spawnDeer) {
      for (String biomeName : ConfigManager.deer.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityDeer.class, ConfigManager.deer.rate, ConfigManager.deer.min, ConfigManager.deer.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnFox) {
      for (String biomeName : ConfigManager.fox.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityFox.class, ConfigManager.fox.rate, ConfigManager.fox.min, ConfigManager.fox.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnBeetle) {
      for (String biomeName : ConfigManager.beetle.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityBeetle.class, ConfigManager.beetle.rate, ConfigManager.beetle.min, ConfigManager.beetle.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnFrog) {
      for (String biomeName : ConfigManager.frog.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityFrog.class, ConfigManager.frog.rate, ConfigManager.frog.min, ConfigManager.frog.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnSprout) {
      for (String biomeName : ConfigManager.sprout.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntitySprout.class, ConfigManager.sprout.rate, ConfigManager.sprout.min, ConfigManager.sprout.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnEndermini) {
      EntityRegistry.addSpawn(EntityEndermini.class, ConfigManager.endermini.rate, ConfigManager.endermini.min, ConfigManager.endermini.max, EnumCreatureType.CREATURE, BiomeDictionary.getBiomes(Type.END).toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnOwl) {
      for (String biomeName : ConfigManager.owl.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityOwl.class, ConfigManager.owl.rate, ConfigManager.owl.min, ConfigManager.owl.max, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    biomes.clear();

    if (ConfigManager.mobs.spawnLavaCat) {
      for (String biomeName : ConfigManager.lavaCat.biomes) {
        Type type = Type.getType(biomeName);
        biomes.addAll(BiomeDictionary.getBiomes(type));
      }
      EntityRegistry.addSpawn(EntityLavaCat.class, ConfigManager.lavaCat.rate, ConfigManager.lavaCat.min, ConfigManager.lavaCat.max, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

    biomes.clear();
  }
}
