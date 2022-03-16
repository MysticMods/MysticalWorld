package mysticmods.mysticalworld.init.deferred.data;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BlockData {
  private static Map<RegistryObject<? extends Block>, RegistryObject<? extends Item>> BLOCK_TO_ITEM_MAP = new HashMap<>();
  private static Set<RegistryObject<? extends Block>> BLOCKS = new HashSet<>();

  public static <T extends Block> void storeBlock (RegistryObject<T> block) {
    BLOCKS.add(block);
  }

  public static <T extends Block> void mapItem(RegistryObject<T> result, RegistryObject<BlockItem> blockItem) {
    BLOCK_TO_ITEM_MAP.put(result, blockItem);
  }

  public static Item item (RegistryObject<? extends Block> block) {
    return BLOCK_TO_ITEM_MAP.get(block).get();
  }

  public static Set<RegistryObject<? extends Block>> getAllBlocks () {
    return BLOCKS;
  }
}
