package mysticmods.mysticalworld.items.quicksilver;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class QuicksilverAxeItem extends AxeItem implements IQuicksilverItem {

  private int counter;
  private Random random = new Random();

  public QuicksilverAxeItem(IItemTier tier, float attackDamage, float attackSpeed, Properties props) {
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
    BlockState blockstate = world.getBlockState(blockpos);
    Block block = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
    if (block != null) {
      PlayerEntity playerentity = context.getPlayer();
      world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
      if (!world.isRemote) {
        world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
      }

      return ActionResultType.SUCCESS;
    } else {
      return ActionResultType.PASS;
    }
  }
}
