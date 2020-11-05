package epicsquid.mysticalworld.integration.jer;

import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.init.ModItems;
import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.JERPlugin;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public class JERIntegration {
  @JERPlugin
  public static IJERAPI JERApi;

  public static String[] getBiomeNames(String[] biomeNames) {
    return Stream.of(biomeNames).map(biome -> StringUtils.capitalize(BiomeDictionary.Type.getType(biome).getName().toLowerCase())).toArray(String[]::new);
  }

  public static void init() {
    IMobRegistry registry = JERApi.getMobRegistry();
    IPlantRegistry plantRegistry = JERApi.getPlantRegistry();

    World world = JERApi.getWorld();

    EntityBeetle beetle = new EntityBeetle(world);
    registry.register(beetle, LightLevel.any, getBiomeNames(ConfigManager.beetle.biomes), beetle.getLootTable());

    EntityDeer deer = new EntityDeer(world);
    registry.register(deer, LightLevel.any, getBiomeNames(ConfigManager.deer.biomes), deer.getLootTable());

    EntityFox fox = new EntityFox(world);
    registry.register(fox, LightLevel.any, getBiomeNames(ConfigManager.fox.biomes), fox.getLootTable());

    EntityFrog frog = new EntityFrog(world);
    registry.register(frog, LightLevel.any, getBiomeNames(ConfigManager.frog.biomes), frog.getLootTable());

    EntitySprout sprout_green = new EntitySprout(world);
    sprout_green.getDataManager().set(EntitySprout.variant, 0);
    registry.register(sprout_green, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_green.getLootTable());

    EntitySprout sprout_tan = new EntitySprout(world);
    sprout_tan.getDataManager().set(EntitySprout.variant, 1);
    registry.register(sprout_tan, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_tan.getLootTable());

    EntitySprout sprout_red = new EntitySprout(world);
    sprout_red.getDataManager().set(EntitySprout.variant, 2);
    registry.register(sprout_red, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_red.getLootTable());

    EntitySprout sprout_purple = new EntitySprout(world);
    sprout_purple.getDataManager().set(EntitySprout.variant, 3);
    registry.register(sprout_purple, LightLevel.any, getBiomeNames(ConfigManager.sprout.biomes), sprout_purple.getLootTable());

    EntityHellSprout sprout_hell = new EntityHellSprout(world);
    registry.register(sprout_hell, LightLevel.any, getBiomeNames(ConfigManager.hellSprout.biomes), sprout_hell.getLootTable());

    EntityEndermini endermini = new EntityEndermini(world);
    registry.register(endermini, LightLevel.any, new String[]{"The End"}, endermini.getLootTable());

    EntityLavaCat lavacat = new EntityLavaCat(world);
    registry.register(lavacat, LightLevel.any, new String[]{"The Nether"}, lavacat.getLootTable());

    EntitySilkworm silkworm = new EntitySilkworm(world);
    registry.register(silkworm, LightLevel.any, new String[]{"(No biome)"}, silkworm.getLootTable());

    plantRegistry.register((ItemSeedBase) ModItems.aubergine_seed,
        new PlantDrop(new ItemStack(ModItems.aubergine), 1, 1),
        new PlantDrop(new ItemStack(ModItems.aubergine_seed), 1, 4));
  }
}

