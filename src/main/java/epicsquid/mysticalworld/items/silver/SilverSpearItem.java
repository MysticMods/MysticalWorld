package epicsquid.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedSpearItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

public class SilverSpearItem extends ModifiedSpearItem implements ISmitingItem {
  public SilverSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getAttributeModifiers(equipmentSlot), equipmentSlot, 2f);
  }
}
