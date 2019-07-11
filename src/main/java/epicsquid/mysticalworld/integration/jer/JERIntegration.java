//package epicsquid.mysticalworld.integration.jer;
//
//import epicsquid.mysticalworld.config.ConfigManager;
//import epicsquid.mysticalworld.entity.*;
//import jeresources.api.IJERAPI;
//import jeresources.api.IMobRegistry;
//import jeresources.api.JERPlugin;
//import jeresources.api.conditionals.LightLevel;
//import net.minecraftforge.common.BiomeDictionary;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.stream.Stream;
//
//public class JERIntegration {
//  @JERPlugin
//  public static IJERAPI JERApi;
//
//  public static String[] getBiomeNames (String[] biomeNames) {
//    return Stream.of(biomeNames).map(biome -> StringUtils.capitalize(BiomeDictionary.Type.getType(biome).getName().toLowerCase())).toArray(String[]::new);
//  }
//
//  public static void init() {
//    IMobRegistry registry = JERApi.getMobRegistry();
//
//    BeetleEntity beetle = new BeetleEntity(null);
//    registry.register(beetle, LightLevel.any, getBiomeNames(ConfigManager.beetle.biomes), beetle.getLootTable());
//
//    DeerEntity deer = new DeerEntity(null);
//    registry.register(deer, LightLevel.any, getBiomeNames(ConfigManager.deer.biomes), deer.getLootTable());
//
//    FoxEntity fox = new FoxEntity(null);
//    registry.register(fox, LightLevel.any, getBiomeNames(ConfigManager.fox.biomes), fox.getLootTable());
//
//    FrogEntity frog = new FrogEntity(null);
//    registry.register(frog, LightLevel.any, getBiomeNames(ConfigManager.frog.biomes), frog.getLootTable());
//
//    SproutEntity sprout_green = new SproutEntity(null);
//    sprout_green.getDataManager().set(SproutEntity.variant, 0);
//    registry.register(sprout_green, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_green.getLootTable());
//
//    SproutEntity sprout_tan = new SproutEntity(null);
//    sprout_tan.getDataManager().set(SproutEntity.variant, 1);
//    registry.register(sprout_tan, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_tan.getLootTable());
//
//    SproutEntity sprout_red = new SproutEntity(null);
//    sprout_red.getDataManager().set(SproutEntity.variant, 2);
//    registry.register(sprout_red, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_red.getLootTable());
//  }
//}
//
