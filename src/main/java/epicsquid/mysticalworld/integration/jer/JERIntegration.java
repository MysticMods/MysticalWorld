//package epicsquid.mysticalworld.integration.jer;
//
//import epicsquid.mysticalworld.config.ConfigManager;
//import epicsquid.mysticalworld.entity.*;
//import epicsquid.mysticalworld.init.ModEntities;
//import jeresources.api.IJERAPI;
//import jeresources.api.IMobRegistry;
//import jeresources.api.IPlantRegistry;
//import jeresources.api.JERPlugin;
//import jeresources.api.conditionals.LightLevel;
//import net.minecraft.world.World;
//import net.minecraftforge.common.BiomeDictionary;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//
//public class JERIntegration {
//  @JERPlugin
//  public static IJERAPI JERApi;
//
//  public static String[] getBiomeNames(List<String> biomeNames) {
//    return biomeNames.stream().map(biome -> StringUtils.capitalize(BiomeDictionary.Type.getType(biome).getName().toLowerCase())).toArray(String[]::new);
//  }
//
//  public static void init() {
//    IMobRegistry registry = JERApi.getMobRegistry();
//    IPlantRegistry plantRegistry = JERApi.getPlantRegistry();
//
//    World world = JERApi.getWorld();
//
//    BeetleEntity beetle = ModEntities.BEETLE.get().create(world);
//    registry.register(beetle, LightLevel.any, getBiomeNames(ConfigManager.BEETLE_CONFIG.getBiomes()), beetle.getLootTable());
//
//    DeerEntity deer = ModEntities.DEER.get().create(world);
//    registry.register(deer, LightLevel.any, getBiomeNames(ConfigManager.DEER_CONFIG.getBiomes()), deer.getLootTable());
//
//    FoxEntity fox = ModEntities.SILVER_FOX.get().create(world);
//    registry.register(fox, LightLevel.any, getBiomeNames(ConfigManager.SILVER_FOX_CONFIG.getBiomes()), fox.getLootTable());
//
//    FrogEntity frog = ModEntities.FROG.get().create(world);
//    registry.register(frog, LightLevel.any, getBiomeNames(ConfigManager.FROG_CONFIG.getBiomes()), frog.getLootTable());
//
//    SproutEntity sprout = ModEntities.SPROUT.get().create(world);
//    sprout.getDataManager().set(SproutEntity.variant, 0);
//    registry.register(sprout, LightLevel.any, getBiomeNames(ConfigManager.SPROUT_CONFIG.getBiomes()), sprout.getLootTable());
//
//    sprout = ModEntities.SPROUT.get().create(world);
//    sprout.getDataManager().set(SproutEntity.variant, 1);
//    registry.register(sprout, LightLevel.any, getBiomeNames(ConfigManager.SPROUT_CONFIG.getBiomes()), sprout.getLootTable());
//
//    sprout = ModEntities.SPROUT.get().create(world);
//    sprout.getDataManager().set(SproutEntity.variant, 2);
//    registry.register(sprout, LightLevel.any, getBiomeNames(ConfigManager.SPROUT_CONFIG.getBiomes()), sprout.getLootTable());
//
//    sprout = ModEntities.SPROUT.get().create(world);
//    sprout.getDataManager().set(SproutEntity.variant, 3);
//    registry.register(sprout, LightLevel.any, getBiomeNames(ConfigManager.SPROUT_CONFIG.getBiomes()), sprout.getLootTable());
//
//    SilkwormEntity silkworm = ModEntities.SILKWORM.get().create(world);
//    registry.register(silkworm, LightLevel.any, new String[]{}, silkworm.getLootTable());
//
//    LavaCatEntity lavaCat = ModEntities.LAVA_CAT.get().create(world);
//    registry.register(lavaCat, LightLevel.any, getBiomeNames(ConfigManager.LAVA_CAT_CONFIG.getBiomes()), lavaCat.getLootTable());
//
//    OwlEntity owl = ModEntities.OWL.get().create(world);
//    registry.register(owl, LightLevel.any, getBiomeNames(ConfigManager.OWL_CONFIG.getBiomes()), owl.getLootTable());
//  }
//}
//
