package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedSwordItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

public class SilverSwordItem extends ModifiedSwordItem implements ISmitingItem {
  public SilverSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getAttributeModifiers(equipmentSlot), equipmentSlot, 2f);
  }
}
