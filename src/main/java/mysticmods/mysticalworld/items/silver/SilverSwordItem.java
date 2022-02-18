package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedSwordItem;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;

import net.minecraft.world.item.Item.Properties;

public class SilverSwordItem extends ModifiedSwordItem implements ISmitingItem {
  public SilverSwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
    super(tier, attackDamageIn, attackSpeedIn, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    return modifyAttributes(super.getDefaultAttributeModifiers(equipmentSlot), equipmentSlot, 2f);
  }
}
