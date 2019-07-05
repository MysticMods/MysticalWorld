package epicsquid.mysticalworld.world;

import epicsquid.mysticallib.world.IOreGenerator;
import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.materials.Gem;
import epicsquid.mysticalworld.materials.Metal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OreGenerator implements IOreGenerator {

  public Map<String, Config> configMap = new HashMap<>();

  private void generateOre(String oreName, @Nonnull IBlockState ore, @Nonnull World world, @Nonnull Random random, int x, int z, int minY, int maxY, int size, int numberToGenerate) {
    for (int i = 0; i < numberToGenerate; i++) {
      BlockPos pos = new BlockPos(x * 16 + random.nextInt(16), random.nextInt(maxY - minY) + minY, z * 16 + random.nextInt(16));
      WorldGenMinableDebug generator = new WorldGenMinableDebug(oreName, ore, size);
      generator.generate(world, random, pos);
    }
  }

  @Override
  public void generate(@Nonnull Random random, int chunkX, int chunkZ, @Nonnull World world, @Nonnull IChunkGenerator chunkGenerator,
                       @Nonnull IChunkProvider chunkProvider) {

    if (world.provider.getDimension() == 0 && ConfigManager.metals.enableOres) {
      for (Metal metal : Metal.values()) {
        // This could be done much better
        if (metal == Metal.copper && !ConfigManager.metals.enableCopper) continue;
        if (metal == Metal.silver && !ConfigManager.metals.enableSilver) continue;
        if (metal.hasOre() && metal.isEnabled()) {
          Config conf = getConfig(metal.name());
          if (conf != null && conf.perChunk > 0 && metal.getOre() != null) {
            generateOre(metal.getOredictNameSuffix(), metal.getOre().getDefaultState(), world, random, chunkX, chunkZ, conf.minY, conf.maxY, conf.veinSize, conf.perChunk);
          }
        }
      }
    }
    if (world.provider.getDimension() == 0 && ConfigManager.gems.enableOres) {
      for (Gem gem : Gem.values()) {
        // TODO: Do this better
        if (gem == Gem.amethyst && !ConfigManager.gems.enableAmethyst) continue;
        if (gem.hasOre() && gem.isEnabled()) {
          Config conf = getConfig(gem.name());
          if (conf != null && conf.perChunk > 0 && gem.getOre() != null) {
            generateOre(gem.getOredictNameSuffix(), gem.getOre().getDefaultState(), world, random, chunkX, chunkZ, conf.minY, conf.maxY, conf.veinSize, conf.perChunk);
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

    public static Config fromConfig(String name) {
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
