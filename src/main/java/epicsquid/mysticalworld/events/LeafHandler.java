package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.config.ConfigManager;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.item.ItemSilkwormEgg;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class LeafHandler {
  public static Set<Block> LEAF_BLOCKS = null;
  public static Set<Item> LEAF_ITEMS = null;

  public static Set<Item> getLeafItems () {
    getLeafBlocks();
    return LEAF_ITEMS;
  }

  @Nullable
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

        Block block = ((ItemBlock)leaf.getItem()).getBlock();
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
      if (getLeafBlocks().contains(state.getBlock())) {
        if (event.getWorld().rand.nextInt(ConfigManager.silkworm.leafDropChance) == 0) {
          event.getDrops().add(new ItemStack(ModItems.silkworm_egg));
        }
        /*if (event.getWorld().rand.nextInt(ConfigManager.silkworm.leafSpawnChance) == 0) {
          BlockPos pos = event.getPos();
          ItemSilkwormEgg.doSpawnCreature(event.getWorld(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
          event.getWorld().playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1f, 1.4f + (event.getWorld().rand.nextFloat() - 0.5f));
        }*/
      }
    }
  }
}
