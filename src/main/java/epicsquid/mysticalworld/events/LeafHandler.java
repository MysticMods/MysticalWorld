package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class LeafHandler {
  public static Set<Block> LEAF_BLOCKS = null;
  public static Set<Item> LEAF_ITEMS = null;

  public static boolean isLeafBlock(Block block) {
    return getLeafBlocks().contains(block);
  }

  public static Set<Item> getLeafItems() {
    getLeafBlocks();
    return LEAF_ITEMS;
  }

  public static Set<Block> getLeafBlocks() {
    if (LEAF_BLOCKS == null) {
      LEAF_BLOCKS = new HashSet<>();
      LEAF_ITEMS = new HashSet<>();

      List<ItemStack> leaves = OreDictionary.getOres("treeLeaves");

      for (ItemStack leaf : leaves) {
        Item item = leaf.getItem();
        if (!(item instanceof ItemBlock)) {
          continue;
        }

        Block block = ((ItemBlock) leaf.getItem()).getBlock();
        LEAF_BLOCKS.add(block);
        LEAF_ITEMS.add(Item.getItemFromBlock(block));
      }
    }

    return LEAF_BLOCKS;
  }

  @SubscribeEvent
  public static void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
    if (ConfigManager.silkworm.enabled && ConfigManager.silkworm.leafDrops && !event.getWorld().isRemote) {
      IBlockState state = event.getState();
      if (isLeafBlock(state.getBlock())) {
        if (event.getWorld().rand.nextInt(ConfigManager.safeInt(ConfigManager.silkworm.leafDropChance)) == 0) {
          event.getDrops().add(new ItemStack(ModItems.silkworm_egg));
        }
      }
    }
  }
}
