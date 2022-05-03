package mysticmods.mysticalworld.world.structures;

import mysticmods.mysticalworld.MysticalWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.Optional;

public class MWStructure extends StructureFeature<JigsawConfiguration> {

  public MWStructure() {
    super(JigsawConfiguration.CODEC, MWStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
  }

  @Override
  public GenerationStep.Decoration step() {
    return GenerationStep.Decoration.SURFACE_STRUCTURES;
  }

  private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
    return true;
/*    int startX = context.chunkPos().x << 4;
    int startZ = context.chunkPos().z << 4;
    int min = Integer.MAX_VALUE;
    int max = 0;
    for (int x = 0; x < 16; x++) {
      for (int z = 0; z < 16; z++) {
        int height = context.chunkGenerator().getBaseHeight(startX + x, startZ + z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
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

    return true;*/
  }

  public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
    if (!isFeatureChunk(context)) {
      return Optional.empty();
    }

    BlockPos position = context.chunkPos().getMiddleBlockPosition(0);
    int maxY = context.chunkGenerator().getFirstFreeHeight(position.getX(), position.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
    position = position.above(maxY);

    Optional<PieceGenerator<JigsawConfiguration>> pieceGenerator = JigsawPlacement.addPieces(
        context,
        PoolElementStructurePiece::new,
        position,
        false,
        false
    );

    return pieceGenerator;
  }
}
