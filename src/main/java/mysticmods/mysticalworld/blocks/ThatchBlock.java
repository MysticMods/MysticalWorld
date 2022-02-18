package mysticmods.mysticalworld.blocks;

import noobanidus.libs.noobutil.block.WaterloggedBlock;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ThatchBlock extends WaterloggedBlock {
  public ThatchBlock(Properties props) {
    super(props);
    this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false));
  }
}