package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedShovelItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

import net.minecraft.item.Item.Properties;

public class SilverShovelItem extends ModifiedShovelItem implements ISmitingItem {
  public SilverShovelItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getDefaultAttributeModifiers(equipmentSlot), equipmentSlot, 1f);
  }
}
