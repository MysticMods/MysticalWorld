package epicsquid.mysticalworld.events;

import com.google.common.collect.Sets;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
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
      Tag<Item> leaf_items = new ItemTags.Wrapper(new ResourceLocation("minecraft", "leaves"));
      LEAF_ITEMS = Sets.newHashSet(leaf_items.getAllElements());
      Tag<Block> leaf_blocks = new BlockTags.Wrapper(new ResourceLocation("minecraft", "leaves"));
      LEAF_BLOCKS = Sets.newHashSet(leaf_blocks.getAllElements());
    }

    return LEAF_BLOCKS;
  }

  public static void onBlockDrops(BlockEvent.BreakEvent event) {
    if (ConfigManager.SILKWORM_CONFIG.getLeafDropsEnabled() && !event.getWorld().isRemote()) {
      BlockState state = event.getState();
      if (getLeafBlocks().contains(state.getBlock())) {
        if (event.getWorld().getRandom().nextInt(ConfigManager.SILKWORM_CONFIG.getLeafDropChance()) == 0) {
          BlockPos pos = event.getPos();
          event.getWorld().addEntity(new ItemEntity((World) event.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.SILKWORM_EGG.get())));
        }
      }
    }
  }
}
