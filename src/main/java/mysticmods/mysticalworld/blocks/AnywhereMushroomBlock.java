package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ConfiguredFeatures;
import mysticmods.mysticalworld.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class AnywhereMushroomBlock extends MushroomBlock {
  public AnywhereMushroomBlock(Properties properties) {
    super(properties);
  }

  @Override
  public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
    BlockPos blockpos = pos.below();
    BlockState blockstate = worldIn.getBlockState(blockpos);
    if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
      return true;
    } else {
      return blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.core.Direction.UP, this);
    }
  }

  @Override
  public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
    world.removeBlock(pos, false);
    ConfiguredFeature<?, ?> configuredfeature;
    if (this == ModBlocks.UNCANNY_MUSHROOM.get()) {
      configuredfeature = ConfiguredFeatures.HUGE_UNCANNY_MUSHROOM;
    } else if (this == ModBlocks.ANYWHERE_BROWN_MUSHROOM.get()) {
      configuredfeature = Features.HUGE_BROWN_MUSHROOM;
    } else {
      if (this != ModBlocks.ANYWHERE_RED_MUSHROOM.get()) {
        world.setBlock(pos, state, 3);
        return false;
      }

      configuredfeature = Features.HUGE_RED_MUSHROOM;
    }

    if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos)) {
      return true;
    } else {
      world.setBlock(pos, state, 3);
      return false;
    }
  }
}
