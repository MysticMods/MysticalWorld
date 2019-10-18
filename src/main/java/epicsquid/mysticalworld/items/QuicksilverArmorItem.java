package epicsquid.mysticalworld.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

// TODO find a way to block damaging the item
public class QuicksilverArmorItem extends ArmorItem implements IQuicksilverItem {

  private int counter;
  private Random random = new Random();

  public QuicksilverArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
    super(material, slot, props);
  }

  @Override
  public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
    counter = counter >= 20 ? 1 : counter + 1;
    if (counter % 20 == 0 && entity instanceof LivingEntity) {
      drip(stack, (LivingEntity) entity, random, getEquipmentSlot());
    }
  }
}
