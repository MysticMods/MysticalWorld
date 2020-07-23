package epicsquid.mysticalworld.world.structures;

import com.mojang.datafixers.Dynamic;
import epicsquid.mysticalworld.world.pieces.HutPiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class HutStructure extends ScatteredStructure<NoFeatureConfig> {
  public HutStructure(Function<Dynamic<?>, NoFeatureConfig> config) {
    super(NoFeatureConfig::deserialize);
  }

  @Override
  protected int getSeedModifier() {
    return 0;
  }

  @Override
  public IStartFactory getStartFactory() {
    return null;
  }

  @Override
  public String getStructureName() {
    return ""; //return name.toString();
  }

  @Override
  public int getSize() {
    return 1;
  }

  public static class Start extends MarginedStructureStart {
    public Start(Structure<?> structureIn, int chunkX, int chunkZ, Biome biomeIn, MutableBoundingBox boundsIn, int referenceIn, long seed) {
      super(structureIn, chunkX, chunkZ, biomeIn, boundsIn, referenceIn, seed);
    }

    @Override
    public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
      HutPiece swamphutpiece = new HutPiece(this.rand, chunkX * 16, chunkZ * 16);
      this.components.add(swamphutpiece);
      this.recalculateStructureSize();
    }
  }
}
