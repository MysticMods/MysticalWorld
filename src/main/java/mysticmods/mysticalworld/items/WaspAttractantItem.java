package mysticmods.mysticalworld.items;

import mysticmods.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class WaspAttractantItem extends Item {
  public WaspAttractantItem(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public ActionResultType useOn(ItemUseContext init) {
    BlockItemUseContext context = new BlockItemUseContext(init);
    ItemStack item = init.getItemInHand();
    PlayerEntity player = init.getPlayer();
    Direction facing = init.getClickedFace();
    BlockPos pos = init.getClickedPos();
    World world = init.getLevel();

    if (facing == Direction.UP || facing == Direction.DOWN || player != null && !player.mayUseItemAt(pos.relative(facing), facing, item) || !world.getBlockState(pos.relative(facing)).canBeReplaced(context)) {
      return ActionResultType.FAIL;
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
          return ActionResultType.FAIL;
        }
      }
    } else {
      return ActionResultType.FAIL;
    }

    return ActionResultType.sidedSuccess(world.isClientSide);
  }
}
