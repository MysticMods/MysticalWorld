/*package epicsquid.mysticalworld.events;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.init.ModBlocks;
import net.minecraft.block.BlockPotato;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MysticalWorld.MODID)
public class PoisonHandler {
  @SubscribeEvent
  public static void onItemRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
    EntityPlayer player = event.getEntityPlayer();
    ItemStack stack = event.getItemStack();
    IBlockState state = player.world.getBlockState(event.getPos());
    if (!player.world.isRemote && state.getBlock() == Blocks.POTATOES) {
      boolean poison = false;
      if (stack.getItem() == Items.SPIDER_EYE || stack.getItem() == Items.FERMENTED_SPIDER_EYE || stack.getItem() == Items.ROTTEN_FLESH) {
        poison = true;
      } else {
        PotionType type = PotionUtils.getPotionFromItem(stack);
        if (type != PotionTypes.EMPTY && type.getEffects().stream().anyMatch(o -> o.getPotion() == MobEffects.POISON)) {
          poison = true;
        }
      }
      if (poison) {
        stack.shrink(1);
        int age = state.getValue(BlockPotato.AGE);
        IBlockState poisoned = ModBlocks.poisoned_potato.getDefaultState().withProperty(BlockPoisonedPotatoCrop.AGE, age);
        player.world.setBlockState(event.getPos(), poisoned);
        event.setCanceled(true);
        event.setCancellationResult(EnumActionResult.SUCCESS);
      }
    }
  }
}*/
