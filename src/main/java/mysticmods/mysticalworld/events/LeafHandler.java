package mysticmods.mysticalworld.events;

import com.google.common.collect.Sets;
import mysticmods.mysticalworld.config.ConfigManager;
import mysticmods.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Set;

public class LeafHandler {
  private static Set<Block> LEAF_BLOCKS = null;
  private static Set<Item> LEAF_ITEMS = null;

  public static Set<Item> getLeafItems() {
    getLeafBlocks();
    return LEAF_ITEMS;
  }

  public static Set<Block> getLeafBlocks() {
    if (LEAF_BLOCKS == null) {
      ITag.INamedTag<Item> leaf_items = ItemTags.LEAVES;
      LEAF_ITEMS = Sets.newHashSet(leaf_items.getValues());
      ITag.INamedTag<Block> leaf_blocks = BlockTags.LEAVES;
      LEAF_BLOCKS = Sets.newHashSet(leaf_blocks.getValues());
    }

    return LEAF_BLOCKS;
  }

  public static void onBlockDrops(BlockEvent.BreakEvent event) {
    if (ConfigManager.SILKWORM_CONFIG.getLeafDropsEnabled() && !event.getWorld().isClientSide()) {
      BlockState state = event.getState();
      if (getLeafBlocks().contains(state.getBlock())) {
        if (event.getWorld().getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getLeafDropChance()) == 0) {
          BlockPos pos = event.getPos();
          event.getWorld().addFreshEntity(new ItemEntity((World) event.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.SILKWORM_EGG.get())));
        }
      }
    }
  }
}
