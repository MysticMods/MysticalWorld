package epicsquid.mysticalworld.items.quicksilver;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class QuicksilverHoeItem extends HoeItem implements IQuicksilverItem {

  private int counter;
  private Random random = new Random();

  public QuicksilverHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
    super(itemTier, attackDamage, attackSpeed, properties);
  }


  @Override
  public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
    counter = counter >= 20 ? 1 : counter + 1;
    if (counter % 20 == 0 && entity instanceof LivingEntity) {
      drip(stack, (LivingEntity) entity, random, EquipmentSlotType.MAINHAND);
    }
  }

  @Override
  public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    return true;
  }

  @Override
  public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos post, LivingEntity entity) {
    return true;
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    World world = context.getWorld();
    BlockPos blockpos = context.getPos();
    int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
    if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
    if (context.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
      BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
      if (blockstate != null) {
        PlayerEntity playerentity = context.getPlayer();
        world.playSound(playerentity, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isRemote) {
          world.setBlockState(blockpos, blockstate, 11);
        }

        return ActionResultType.SUCCESS;
      }
    }

    return ActionResultType.PASS;
  }
}
