package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedKnifeItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;

public class SilverKnifeItem extends ModifiedKnifeItem implements ISmitingItem {
  public SilverKnifeItem(IItemTier tier, float attackDamage, float attackSpeed, Properties props) {
    super(tier, attackDamage, attackSpeed, props);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    return modifyAttributes(super.getAttributeModifiers(equipmentSlot), equipmentSlot, 2f);
  }
}
