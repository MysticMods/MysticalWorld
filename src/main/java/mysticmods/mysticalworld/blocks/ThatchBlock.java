package mysticmods.mysticalworld.blocks;

import noobanidus.libs.noobutil.block.WaterloggedBlock;

// TODO: Move this into WaterloggedBlock (with default false)
public class ThatchBlock extends WaterloggedBlock {
  public ThatchBlock(Properties props) {
    super(props);
    this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false));
  }
}