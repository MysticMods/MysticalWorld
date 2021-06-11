package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class CharredLogBlock extends RotatedPillarBlock {
  private final boolean wood;

  public CharredLogBlock(Properties properties, boolean wood) {
    super(properties);
    this.wood = wood;
  }

  @Nullable
  @Override
  public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
    if (toolType == ToolType.AXE) {
      if (wood) {
        return ModBlocks.STRIPPED_CHARRED_WOOD.get().getDefaultState().with(AXIS, state.get(AXIS));
      } else {
        return ModBlocks.STRIPPED_CHARRED_LOG.get().getDefaultState().with(AXIS, state.get(AXIS));
      }
    }
    return super.getToolModifiedState(state, world, pos, player, stack, toolType);
  }
}
