package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedShovelItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;

import net.minecraft.world.item.Item.Properties;

public class SilverShovelItem extends ModifiedShovelItem implements ISmitingItem {
  public SilverShovelItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    return modifyAttributes(super.getDefaultAttributeModifiers(equipmentSlot), equipmentSlot, 1f);
  }
}
