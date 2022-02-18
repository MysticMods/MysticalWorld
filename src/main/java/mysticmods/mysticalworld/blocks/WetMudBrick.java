package mysticmods.mysticalworld.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("deprecation")
public class WetMudBrick extends Block {
  public WetMudBrick(Properties properties) {
    super(properties);
  }

  @Override
  public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
    entityIn.setDeltaMovement(entityIn.getDeltaMovement().x() * 0.4, entityIn.getDeltaMovement().y(), entityIn.getDeltaMovement().z() * 0.4);
    super.entityInside(state, worldIn, pos, entityIn);
  }
}
