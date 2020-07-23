package epicsquid.mysticalworld.world.pieces;

import epicsquid.mysticalworld.init.ModPieces;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class HutPiece extends ScatteredStructurePiece {
  public HutPiece(Random random, int x, int z) {
    super(ModPieces.HUT, random, x, 64, z, 7, 7, 9);
  }

   public HutPiece(TemplateManager templateManager, CompoundNBT tag) {
     super(ModPieces.HUT, tag);
   }

  @Override
  public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPosIn) {
    return false;
  }
}
