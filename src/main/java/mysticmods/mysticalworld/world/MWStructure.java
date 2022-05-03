package mysticmods.mysticalworld.world;

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
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MWStructure extends StructureFeature<JigsawConfiguration> {

  public MWStructure() {
    super(JigsawConfiguration.CODEC, MWStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
  }

  @Override
  public GenerationStep.Decoration step() {
    return GenerationStep.Decoration.SURFACE_STRUCTURES;
  }

  @NotNull
  public static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
    BlockPos position = context.chunkPos().getMiddleBlockPosition(0);
    int maxY = context.chunkGenerator().getFirstFreeHeight(position.getX(), position.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());
    position = position.above(maxY);

    return JigsawPlacement.addPieces(
        context,
        PoolElementStructurePiece::new,
        position,
        false,
        false
    );
  }
}
