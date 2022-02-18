package mysticmods.mysticalworld.blocks;

import mysticmods.mysticalworld.init.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class CharredLogBlock extends RotatedPillarBlock {
  private final boolean wood;

  public CharredLogBlock(Properties properties, boolean wood) {
    super(properties);
    this.wood = wood;
  }

  @Nullable
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
  }
}
