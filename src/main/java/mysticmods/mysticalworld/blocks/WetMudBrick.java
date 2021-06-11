package mysticmods.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class WetMudBrick extends Block {
  public WetMudBrick(Properties properties) {
    super(properties);
  }

  @Override
  public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    entityIn.setMotion(entityIn.getMotion().getX() * 0.4, entityIn.getMotion().getY(), entityIn.getMotion().getZ() * 0.4);
    super.onEntityCollision(state, worldIn, pos, entityIn);
  }
}
