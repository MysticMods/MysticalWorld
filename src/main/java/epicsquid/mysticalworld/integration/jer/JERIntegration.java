package epicsquid.mysticalworld.integration.jer;

import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
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

    EntitySprout sprout_green = new EntitySprout(null);
    sprout_green.getDataManager().set(EntitySprout.variant, 0);
    registry.register(sprout_green, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_green.getLootTable());

    EntitySprout sprout_tan = new EntitySprout(null);
    sprout_tan.getDataManager().set(EntitySprout.variant, 1);
    registry.register(sprout_tan, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_tan.getLootTable());

    EntitySprout sprout_red = new EntitySprout(null);
    sprout_red.getDataManager().set(EntitySprout.variant, 2);
    registry.register(sprout_red, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_red.getLootTable());

    EntityEndermini endermini = new EntityEndermini(null);
    registry.register(endermini, LightLevel.any, Collections.singletonList("The End").toArray(new String[0]), endermini.getLootTable());
  }
}

