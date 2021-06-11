package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedHoeItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

public class SilverHoeItem extends ModifiedHoeItem implements ISmitingItem {
  public SilverHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
    super(itemTier, attackDamage, attackSpeed, properties);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getAttributeModifiers(equipmentSlot), equipmentSlot, 1f);
  }
}
