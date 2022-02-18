package mysticmods.mysticalworld.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UncannyGravelBlock extends GravelBlock {
  public UncannyGravelBlock(Properties properties) {
    super(properties);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
    return 0x3a50cf;
  }
}
