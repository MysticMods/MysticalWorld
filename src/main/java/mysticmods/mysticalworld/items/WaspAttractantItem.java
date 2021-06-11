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

public class WaspAttractantItem extends Item {
  public WaspAttractantItem(Properties p_i48487_1_) {
    super(p_i48487_1_);
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext init) {
    BlockItemUseContext context = new BlockItemUseContext(init);
    ItemStack item = init.getItem();
    PlayerEntity player = init.getPlayer();
    Direction facing = init.getFace();
    BlockPos pos = init.getPos();
    World world = init.getWorld();

    if (facing == Direction.UP || facing == Direction.DOWN || player != null && !player.canPlayerEdit(pos.offset(facing), facing, item) || !world.getBlockState(pos.offset(facing)).isReplaceable(context)) {
      return ActionResultType.FAIL;
    }

    BlockState blockAt = world.getBlockState(pos);
    if (blockAt.isIn(Blocks.DARK_OAK_LOG) || blockAt.isIn(Blocks.OAK_LOG) || blockAt.isIn(Blocks.DARK_OAK_WOOD) || blockAt.isIn(Blocks.OAK_WOOD)) {
      if (!world.isRemote) {
        BlockState apple = ModBlocks.GALL_APPLE.get().getStateForPlacement(context);
        if (apple != null) {
          world.setBlockState(pos.offset(facing), apple, 10);
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

    return ActionResultType.func_233537_a_(world.isRemote);
  }
}
