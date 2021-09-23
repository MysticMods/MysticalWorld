package mysticmods.mysticalworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraft.block.AbstractBlock.Properties;

@SuppressWarnings("deprecation")
public class WetMudBrick extends Block {
  public WetMudBrick(Properties properties) {
    super(properties);
  }

  @Override
  public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
    entityIn.setDeltaMovement(entityIn.getDeltaMovement().x() * 0.4, entityIn.getDeltaMovement().y(), entityIn.getDeltaMovement().z() * 0.4);
    super.entityInside(state, worldIn, pos, entityIn);
  }
}
