package epicsquid.mysticalworld.items.lead;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class LeadArmorItem extends ModifiedArmorItem implements ILeadItem {
  public LeadArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.10f;
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        val = 0.20f;
      }
      final float val2 = val;
      map.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), getOrCreateModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Knockback resistance", val2, AttributeModifier.Operation.MULTIPLY_BASE)));
    }

    return map;
  }
}
