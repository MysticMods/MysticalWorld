package mysticmods.mysticalworld.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.GravelBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UncannyGravelBlock extends GravelBlock {
  public UncannyGravelBlock(Properties properties) {
    super(properties);
  }

  @Override
  @OnlyIn(Dist.CLIENT)
  public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
    return 0x3a50cf;
  }
}
