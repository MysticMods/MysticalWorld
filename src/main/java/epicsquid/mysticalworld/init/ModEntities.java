package epicsquid.mysticalworld.init;

import java.util.HashSet;
import java.util.Set;

import epicsquid.mysticallib.LibRegistry;
import epicsquid.mysticallib.util.Util;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntityBeetle;
import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.EntityFox;
import epicsquid.mysticalworld.entity.EntityFrog;
import epicsquid.mysticalworld.entity.render.RenderBeetle;
import epicsquid.mysticalworld.entity.render.RenderDeer;
import epicsquid.mysticalworld.entity.render.RenderFox;
import epicsquid.mysticalworld.entity.render.RenderFrog;
import epicsquid.mysticalworld.proxy.ClientProxy;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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

    LibRegistry.registerEntity(EntityDeer.class, Util.intColor(161, 132, 88), Util.intColor(94, 77, 51));
    if (MysticalWorld.proxy instanceof ClientProxy)
      LibRegistry.registerEntityRenderer(EntityDeer.class, new RenderDeer.Factory());
  }

  public static void registerMobSpawn() {
    Set<Biome> biomes = new HashSet<>();

    if (ConfigManager.modules.rootsModuleEnabled && ConfigManager.mobs.spawnDeer) {
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
      biomes.addAll(BiomeDictionary.getBiomes(Type.CONIFEROUS));
      biomes.addAll(BiomeDictionary.getBiomes(Type.PLAINS));
      EntityRegistry.addSpawn(EntityDeer.class, 12, 4, 6, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    if (ConfigManager.modules.mysticalWorldModuleEnabled && ConfigManager.mobs.spawnFox) {
      biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.COLD));
      biomes.addAll(BiomeDictionary.getBiomes(Type.CONIFEROUS));
      EntityRegistry.addSpawn(EntityFox.class, 8, 1, 3, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    if (ConfigManager.modules.mysticalWorldModuleEnabled && ConfigManager.mobs.spawnFrog) {
      biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.SWAMP));
      biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
      EntityRegistry.addSpawn(EntityFrog.class, 12, 4, 7, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }

    if (ConfigManager.modules.mysticalWorldModuleEnabled && ConfigManager.mobs.spawnBeetle) {
      biomes = new HashSet<>();
      biomes.addAll(BiomeDictionary.getBiomes(Type.FOREST));
      biomes.addAll(BiomeDictionary.getBiomes(Type.SWAMP));
      biomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
      biomes.addAll(BiomeDictionary.getBiomes(Type.PLAINS));
      EntityRegistry.addSpawn(EntityBeetle.class, 10, 2, 5, EnumCreatureType.CREATURE, biomes.toArray(new Biome[0]));
    }
  }
}
