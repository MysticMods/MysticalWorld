package mysticmods.mysticalworld.blocks;

import noobanidus.libs.noobutil.block.WaterloggedBlock;

public class ThatchBlock extends WaterloggedBlock {
  public ThatchBlock(Properties props) {
    super(props);
    this.setDefaultState(this.getStateContainer().getBaseState().with(WATERLOGGED, false));
  }
}