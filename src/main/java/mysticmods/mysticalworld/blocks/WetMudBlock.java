package mysticmods.mysticalworld.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import noobanidus.libs.noobutil.block.WaterloggedBlock;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

@SuppressWarnings({"deprecation", "Duplicates"})
public class WetMudBlock extends WaterloggedBlock {
  private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
  private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

  public WetMudBlock(Properties props) {
    super(props);
    this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false));
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }
}