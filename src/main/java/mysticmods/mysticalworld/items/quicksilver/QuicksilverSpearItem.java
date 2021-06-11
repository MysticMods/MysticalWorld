package mysticmods.mysticalworld.items.quicksilver;

import mysticmods.mysticalworld.items.ModifiedSpearItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class QuicksilverSpearItem extends ModifiedSpearItem implements IQuicksilverItem {

  private int counter;
  private Random random = new Random();

  public QuicksilverSpearItem(IItemTier tier, int attackDamage, float attackSpeed, Properties props) {
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
}
