package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ConfiguredFeatures;
import mysticmods.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class AnywhereMushroomBlock extends MushroomBlock {
  public AnywhereMushroomBlock(Properties properties) {
    super(properties);
  }

  @Override
  public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
    BlockPos blockpos = pos.below();
    BlockState blockstate = worldIn.getBlockState(blockpos);
    if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
      return true;
    } else {
      return blockstate.canSustainPlant(worldIn, blockpos, net.minecraft.util.Direction.UP, this);
    }
  }

  @Override
  public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand) {
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
