package epicsquid.mysticalworld.items.quicksilver;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class QuicksilverShovelItem extends ShovelItem implements IQuicksilverItem {

  private int counter;
  private Random random = new Random();

  public QuicksilverShovelItem(IItemTier tier, float attackDamage, float attackSpeed, Properties props) {
    super(tier, attackDamage, attackSpeed, props);
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
    if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir(world, blockpos.up())) {
      BlockState blockstate = SHOVEL_LOOKUP.get(world.getBlockState(blockpos).getBlock());
      if (blockstate != null) {
        PlayerEntity playerentity = context.getPlayer();
        world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isRemote) {
          world.setBlockState(blockpos, blockstate, 11);
        }

        return ActionResultType.SUCCESS;
      }
    }

    return ActionResultType.PASS;
  }
}
