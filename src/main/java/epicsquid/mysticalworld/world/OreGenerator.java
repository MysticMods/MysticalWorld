package epicsquid.mysticalworld.world;

import epicsquid.mysticallib.world.IOreGenerator;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.materials.Gem;
import epicsquid.mysticalworld.materials.Metal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class OreGenerator implements IOreGenerator {

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

    if (world.provider.getDimension() == 0) {
      if (ConfigManager.copper.enableCopper && ConfigManager.copper.enableOres) {
        generateOre(Metal.copper.getOredictNameSuffix(), Objects.requireNonNull(Metal.copper.getOre()).getDefaultState(), world, random, chunkX, chunkZ, ConfigManager.oreGen.copperMinY, ConfigManager.oreGen.copperMaxY, ConfigManager.oreGen.copperVeinSize, ConfigManager.oreGen.copperPerChunk);
      }
      if (ConfigManager.silver.enableSilver && ConfigManager.silver.enableOres) {
        generateOre(Metal.silver.getOredictNameSuffix(), Objects.requireNonNull(Metal.silver.getOre()).getDefaultState(), world, random, chunkX, chunkZ, ConfigManager.oreGen.silverMinY, ConfigManager.oreGen.silverMaxY, ConfigManager.oreGen.silverVeinSize, ConfigManager.oreGen.silverPerChunk);
        if (ConfigManager.amethyst.enableAmethyst && ConfigManager.amethyst.enableOres) {
          generateOre(Gem.amethyst.getOredictNameSuffix(), Objects.requireNonNull(Gem.amethyst.getOre()).getDefaultState(), world, random, chunkX, chunkZ, ConfigManager.oreGen.amethystMinY, ConfigManager.oreGen.amethystMaxY, ConfigManager.oreGen.amethystVeinSize, ConfigManager.oreGen.amethystPerChunk);
        }
      }
    }
  }
}
