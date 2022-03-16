package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.init.deferred.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WaspAttractantItem extends Item {
  public WaspAttractantItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public InteractionResult useOn(UseOnContext init) {
    BlockPlaceContext context = new BlockPlaceContext(init);
    ItemStack item = init.getItemInHand();
    Player player = init.getPlayer();
    Direction facing = init.getClickedFace();
    BlockPos pos = init.getClickedPos();
    Level world = init.getLevel();

    if (facing == Direction.UP || facing == Direction.DOWN || player != null && !player.mayUseItemAt(pos.relative(facing), facing, item) || !world.getBlockState(pos.relative(facing)).canBeReplaced(context)) {
      return InteractionResult.FAIL;
    }

    BlockState blockAt = world.getBlockState(pos);
    if (blockAt.is(Blocks.DARK_OAK_LOG) || blockAt.is(Blocks.OAK_LOG) || blockAt.is(Blocks.DARK_OAK_WOOD) || blockAt.is(Blocks.OAK_WOOD)) {
      if (!world.isClientSide) {
        BlockState apple = ModBlocks.GALL_APPLE.get().getStateForPlacement(context);
        if (apple != null) {
          world.setBlock(pos.relative(facing), apple, 10);
          if (player == null || !player.isCreative()) {
            item.shrink(1);
          }
        } else {
          return InteractionResult.FAIL;
        }
      }
    } else {
      return InteractionResult.FAIL;
    }

    return InteractionResult.sidedSuccess(world.isClientSide);
  }
}
