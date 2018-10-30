package epicsquid.mysticalworld.world;

import java.util.Random;

import javax.annotation.Nonnull;

import epicsquid.mysticallib.world.IOreGenerator;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.item.metals.Metal;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class OreGenerator implements IOreGenerator {

  @Override
  public void generate(@Nonnull Random random, int chunkX, int chunkZ, @Nonnull World world, @Nonnull IChunkGenerator chunkGenerator,
      @Nonnull IChunkProvider chunkProvider) {

    if (world.provider.getDimension() == 0 && ConfigManager.metals.enableOres) {

      for (Metal metal : Metal.values()) {
        if (metal.hasOre() && metal.isEnabled()) {
          try {
            int perChunk = ConfigManager.oreGen.getClass().getField(metal.name() + "PerChunk").getInt(ConfigManager.oreGen);
            int veinSize = ConfigManager.oreGen.getClass().getField(metal.name() + "VeinSize").getInt(ConfigManager.oreGen);
            int maxY = ConfigManager.oreGen.getClass().getField(metal.name() + "MaxY").getInt(ConfigManager.oreGen);
            int minY = ConfigManager.oreGen.getClass().getField(metal.name() + "MinY").getInt(ConfigManager.oreGen);
            if (perChunk > 0) {
              generateOre(metal.getOre().getDefaultState(), world, random, chunkX, chunkZ, minY, maxY, veinSize, perChunk);
            }
          } catch (Exception e) {
            System.out.println("Error: Cannot find the specified metal in configs. Are you sure you added it?");
          }
        }
      }
    }
  }

}
