package epicsquid.mysticalworld;

import java.util.Random;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.mysticalworld.item.ItemKnife;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager {

  @SubscribeEvent
  public void onBlockHarvested(HarvestDropsEvent event){
    if (event.getHarvester() != null){
      if (event.getHarvester().getHeldItem(EnumHand.MAIN_HAND) != ItemStack.EMPTY){
        if (event.getHarvester().getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemKnife){
          if (event.getState().getBlock() instanceof BlockLog){
            event.getDrops().clear();
            ItemStack bark = new ItemStack(ModItems.bark_oak,1);
            IBlockState s = event.getState();
            Block b = s.getBlock();
            if (b == Blocks.LOG){
              if (s.getValue(BlockOldLog.VARIANT) == EnumType.OAK){
                bark = new ItemStack(ModItems.bark_oak,1);
              }
              if (s.getValue(BlockOldLog.VARIANT) == EnumType.SPRUCE){
                bark = new ItemStack(ModItems.bark_spruce,1);
              }
              if (s.getValue(BlockOldLog.VARIANT) == EnumType.BIRCH){
                bark = new ItemStack(ModItems.bark_birch,1);
              }
              if (s.getValue(BlockOldLog.VARIANT) == EnumType.JUNGLE){
                bark = new ItemStack(ModItems.bark_jungle,1);
              }
            }
            if (b == Blocks.LOG2){
              if (s.getValue(BlockNewLog.VARIANT) == EnumType.ACACIA){
                bark = new ItemStack(ModItems.bark_acacia,1);
              }
              if (s.getValue(BlockNewLog.VARIANT) == EnumType.DARK_OAK){
                bark = new ItemStack(ModItems.bark_dark_oak,1);
              }
            }
            int count = new Random().nextInt(2)+1;
            for (int i = 0; i < count; i ++){
              if (!event.getWorld().isRemote){
                event.getWorld().spawnEntity(new EntityItem(event.getWorld(),event.getPos().getX()+0.5,event.getPos().getY()+0.5,event.getPos().getZ()+0.5,bark));
              }
            }
          }
        }
      }
    }
  }

}
