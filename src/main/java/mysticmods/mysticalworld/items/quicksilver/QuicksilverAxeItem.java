package mysticmods.mysticalworld.items.quicksilver;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Random;

import net.minecraft.world.item.Item.Properties;

public class QuicksilverAxeItem extends AxeItem implements IQuicksilverItem {

  private int counter;
  private final Random random = new Random();

  public QuicksilverAxeItem(Tier tier, float attackDamage, float attackSpeed, Properties props) {
    super(tier, attackDamage, attackSpeed, props);
  }

  @Override
  public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
    counter = counter >= 20 ? 1 : counter + 1;
    if (counter % 20 == 0 && entity instanceof LivingEntity) {
      drip(stack, (LivingEntity) entity, random, EquipmentSlot.MAINHAND);
    }
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    return true;
  }

  @Override
  public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos post, LivingEntity entity) {
    return true;
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level world = context.getLevel();
    BlockPos blockpos = context.getClickedPos();
    BlockState blockstate = world.getBlockState(blockpos);
    Block block = STRIPABLES.get(blockstate.getBlock());
    if (block != null) {
      Player playerentity = context.getPlayer();
      world.playSound(playerentity, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
      if (!world.isClientSide) {
        world.setBlock(blockpos, block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockstate.getValue(RotatedPillarBlock.AXIS)), 11);
      }

      return InteractionResult.SUCCESS;
    } else {
      return InteractionResult.PASS;
    }
  }
}
