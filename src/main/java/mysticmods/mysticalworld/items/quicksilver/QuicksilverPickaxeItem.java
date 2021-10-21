package mysticmods.mysticalworld.items.quicksilver;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import net.minecraft.item.Item.Properties;

public class QuicksilverPickaxeItem extends PickaxeItem implements IQuicksilverItem {

  private int counter;
  private final Random random = new Random();

  public QuicksilverPickaxeItem(IItemTier tier, int attackDamage, float attackSpeed, Properties props) {
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
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    return true;
  }

  @Override
  public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos post, LivingEntity entity) {
    return true;
  }
}
