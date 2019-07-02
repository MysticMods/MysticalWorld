package epicsquid.mysticalworld.world;

import epicsquid.mysticallib.world.IOreGenerator;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.materials.Gem;
import epicsquid.mysticalworld.materials.Metal;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OreGenerator implements IOreGenerator {

  public Map<String, Config> configMap = new HashMap<>();

  @Override
  public void generate(@Nonnull Random random, int chunkX, int chunkZ, @Nonnull World world, @Nonnull IChunkGenerator chunkGenerator,
                       @Nonnull IChunkProvider chunkProvider) {

    if (world.provider.getDimension() == 0 && ConfigManager.metals.enableOres) {
      for (Metal metal : Metal.values()) {
        if (metal.hasOre() && metal.isEnabled()) {
          Config conf = getConfig(metal.name());
          if (conf != null && conf.perChunk > 0 && metal.getOre() != null) {
              generateOre(metal.getOre().getDefaultState(), world, random, chunkX, chunkZ, conf.minY, conf.maxY, conf.veinSize, conf.perChunk);
          } else if (conf == null) {
            System.out.println("Error: Cannot find the specified metal in configs. Are you sure you added it?");
          }
        }
      }
    }
    if (world.provider.getDimension() == 0 && ConfigManager.gems.enableGems) {
      for (Gem gem : Gem.values()) {
        if (gem.hasOre() && gem.isEnabled()) {
          Config conf = getConfig(gem.name());
          if (conf != null && conf.perChunk > 0 && gem.getOre() != null) {
              generateOre(gem.getOre().getDefaultState(), world, random, chunkX, chunkZ, conf.minY, conf.maxY, conf.veinSize, conf.perChunk);
          } else if (conf == null) {
            System.out.println("Error: Cannot find the specified gem in configs. Are you sure you added it?");
          }
        }
      }
    }
  }

  @Nullable
  private Config getConfig(String name) {
    name = name.toLowerCase();
    if (configMap.containsKey(name)) {
      return configMap.get(name);
    }

    Config conf = Config.fromConfig(name);
    configMap.put(name, conf);

    return configMap.get(name);
  }

  public static class Config {
    public int perChunk;
    public int veinSize;
    public int maxY;
    public int minY;

    public Config(int perChunk, int veinSize, int maxY, int minY) {
      this.perChunk = perChunk;
      this.veinSize = veinSize;
      this.maxY = maxY;
      this.minY = minY;
    }

    public static Config fromConfig (String name) {
      int perChunk, veinSize, maxY, minY;
      try {
        perChunk = ConfigManager.oreGen.getClass().getField(name + "PerChunk").getInt(ConfigManager.oreGen);
        veinSize = ConfigManager.oreGen.getClass().getField(name + "VeinSize").getInt(ConfigManager.oreGen);
        maxY = ConfigManager.oreGen.getClass().getField(name + "MaxY").getInt(ConfigManager.oreGen);
        minY = ConfigManager.oreGen.getClass().getField(name + "MinY").getInt(ConfigManager.oreGen);
      } catch (Exception e) {
        return null;
      }

      return new Config(perChunk, veinSize, maxY, minY);
    }
  }
}
