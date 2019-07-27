package epicsquid.mysticalworld.world;

import epicsquid.mysticalworld.world.tree.WorldGenBurntTree;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGeneratorTrees implements IWorldGenerator {
  public static int DEAD_TREE_CHANCE = 200;
  public static int DEAD_TREE_MAX_GEN = 20;
  public WorldGenBurntTree burnTree = new WorldGenBurntTree(false);

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    // TODO: Move into config
    // TODO: Don't generate in superflat
    // TODO: Does this work?

    int xPos = chunkX * 16 + 8;
    int zPos = chunkZ * 16 + 8;

    BlockPos chunkPos = new BlockPos(xPos, 0, zPos);

    Biome biome = world.getChunk(chunkPos).getBiome(chunkPos, world.getBiomeProvider());

    if (random.nextInt(DEAD_TREE_CHANCE) != 0) {
      return;
    }

    List<BiomeDictionary.Type> invalidTypes = Arrays.asList(BiomeDictionary.Type.VOID, BiomeDictionary.Type.END, BiomeDictionary.Type.WATER, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.MESA, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER);

    if (invalidTypes.stream().anyMatch(type -> BiomeDictionary.hasType(biome, type))) {
      return;
    }

    for (int i = 0; i < DEAD_TREE_MAX_GEN; i++) {
      int x = chunkPos.getX() + (5 - random.nextInt(11));
      int z = chunkPos.getZ() + (5 - random.nextInt(11));
      int y = world.getHeight(x, z);
      BlockPos pos = new BlockPos(x, y, z);

      if (Blocks.SAPLING.canPlaceBlockAt(world, pos)) {
        burnTree.generate(world, random, pos);
      }
    }
  }
}
