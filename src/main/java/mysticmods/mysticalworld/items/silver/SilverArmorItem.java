package mysticmods.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class SilverArmorItem extends ModifiedArmorItem implements ISilverItem {
  public SilverArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(Attributes.ATTACK_DAMAGE, getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.06f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      } else {
        map.put(Attributes.ATTACK_DAMAGE, getOrCreateModifier(Attributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.02f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      }

      map.put(ModModifiers.BLESSED.get(), getOrCreateModifier(ModModifiers.BLESSED.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Blessed addition", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
