package mysticmods.mysticalworld.world.structures;

import com.mojang.serialization.Codec;
import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature.StructureStartFactory;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import noobanidus.libs.noobutil.world.gen.structure.SimpleStructure;
import noobanidus.libs.noobutil.world.gen.structure.SimpleStructure.SimpleStart;

public class SandHouseStructure extends SimpleStructure {
  public SandHouseStructure(Codec<NoneFeatureConfiguration> codec) {
    super(codec);
  }

  @Override
  public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
    return SandHouseStructure.Start::new;
  }

  @Override
  protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeProvider, long seed, WorldgenRandom sharedSeed, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration config) {
    int startX = chunkX << 4;
    int startZ = chunkZ << 4;
    int min = Integer.MAX_VALUE;
    int max = 0;
    for (int x = 0; x < 16; x++) {
      for (int z = 0; z < 16; z++) {
        int height = chunkGenerator.getBaseHeight(startX + x, startZ + z, Heightmap.Types.WORLD_SURFACE_WG);
        if (height < min) {
          min = height;
        }
        if (height > max) {
          max = height;
        }
        if (max - min > 3) {
          return false;
        }
      }
    }

    return true;
  }

  public static class Start extends SimpleStart {
    public Start(StructureFeature<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, BoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
      super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }

    private static final ResourceLocation POOL = new ResourceLocation(MysticalWorld.MODID, "sand_house_pool/start_pool");

    @Override
    protected ResourceLocation getPoolLocation() {
      return POOL;
    }

    @Override
    protected int getPoolSize() {
      return 50;
    }

    @Override
    protected void modifyStructure(RegistryAccess dynamicRegistries, ChunkGenerator chunkGenerator, StructureManager templateManager, Biome biome, NoneFeatureConfiguration noFeatureConfig, BlockPos blockPos) {

    }
  }
}