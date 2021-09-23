package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedHoeItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

import net.minecraft.item.Item.Properties;

public class SilverHoeItem extends ModifiedHoeItem implements ISmitingItem {
  public SilverHoeItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
    super(itemTier, attackDamage, attackSpeed, properties);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getDefaultAttributeModifiers(equipmentSlot), equipmentSlot, 1f);
  }
}
