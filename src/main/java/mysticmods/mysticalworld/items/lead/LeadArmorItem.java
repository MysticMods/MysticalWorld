package mysticmods.mysticalworld.items.lead;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class LeadArmorItem extends ModifiedArmorItem implements ILeadItem {
  public LeadArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      float val = 0.04f;
      if (slot == EquipmentSlot.CHEST || slot == EquipmentSlot.LEGS) {
        val = 0.08f;
      }
      final float val2 = val;
      map.put(Attributes.KNOCKBACK_RESISTANCE, getOrCreateModifier(Attributes.KNOCKBACK_RESISTANCE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Knockback resistance", val2, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
