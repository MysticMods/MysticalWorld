package mysticmods.mysticalworld.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;

// TODO: Stripping
public class CharredLogBlock extends RotatedPillarBlock {
  private final boolean wood;

  public CharredLogBlock(Properties properties, boolean wood) {
    super(properties);
    this.wood = wood;
  }

/*  @Nullable
  @Override
  public BlockState getToolModifiedState(BlockState state, Level world, BlockPos pos, Player player, ItemStack stack, ToolType toolType) {
    if (toolType == ToolType.AXE) {
      if (wood) {
        return ModBlocks.STRIPPED_CHARRED_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
      } else {
        return ModBlocks.STRIPPED_CHARRED_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
      }
    }
    return super.getToolModifiedState(state, world, pos, player, stack, toolType);
  }*/
}
