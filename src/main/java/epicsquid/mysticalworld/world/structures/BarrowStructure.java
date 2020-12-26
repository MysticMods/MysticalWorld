package epicsquid.mysticalworld.world.structures;

import com.mojang.serialization.Codec;
import epicsquid.mysticalworld.MysticalWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import noobanidus.libs.noobutil.world.gen.structure.SimpleStructure;

public class BarrowStructure extends SimpleStructure {
  public BarrowStructure(Codec<NoFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public IStartFactory<NoFeatureConfig> getStartFactory() {
    return BarrowStructure.Start::new;
  }

  public static class Start extends SimpleStart {
    public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
      super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
    }

    public static ResourceLocation POOL = new ResourceLocation(MysticalWorld.MODID, "barrow_pool/start_pool");

    @Override
    protected ResourceLocation getPoolLocation() {
      return POOL;
    }

    @Override
    protected int getPoolSize() {
      return 50;
    }

    @Override
    protected void modifyStructure(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, Biome biome, NoFeatureConfig noFeatureConfig, BlockPos blockPos) {
    }
  }
}