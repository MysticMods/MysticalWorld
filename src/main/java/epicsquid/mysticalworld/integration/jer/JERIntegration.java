package epicsquid.mysticalworld.integration.jer;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.EntityBeetle;
import epicsquid.mysticalworld.entity.EntityDeer;
import epicsquid.mysticalworld.entity.EntityFox;
import epicsquid.mysticalworld.entity.EntityFrog;
import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class JERIntegration {
  @JERPlugin
  public static IJERAPI JERApi;

  public static String[] getBiomeNames (String[] biomeNames) {
    return Stream.of(biomeNames).map(biome -> StringUtils.capitalize(BiomeDictionary.Type.getType(biome).getName().toLowerCase())).toArray(String[]::new);
  }

  public static void init() {
    IMobRegistry registry = JERApi.getMobRegistry();

    EntityBeetle beetle = new EntityBeetle(null);
    registry.register(beetle, LightLevel.any, getBiomeNames(ConfigManager.beetle.biomes), beetle.getLootTable());

    EntityDeer deer = new EntityDeer(null);
    registry.register(deer, LightLevel.any, getBiomeNames(ConfigManager.deer.biomes), deer.getLootTable());

    EntityFox fox = new EntityFox(null);
    registry.register(fox, LightLevel.any, getBiomeNames(ConfigManager.fox.biomes), fox.getLootTable());

    EntityFrog frog = new EntityFrog(null);
    registry.register(frog, LightLevel.any, getBiomeNames(ConfigManager.frog.biomes), frog.getLootTable());
  }
}

