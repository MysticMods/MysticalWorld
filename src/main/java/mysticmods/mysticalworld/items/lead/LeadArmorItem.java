package mysticmods.mysticalworld.items.lead;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

import net.minecraft.item.Item.Properties;

public class LeadArmorItem extends ModifiedArmorItem implements ILeadItem {
  public LeadArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.04f;
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        val = 0.08f;
      }
      final float val2 = val;
      map.put(Attributes.KNOCKBACK_RESISTANCE, getOrCreateModifier(Attributes.KNOCKBACK_RESISTANCE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Knockback resistance", val2, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
