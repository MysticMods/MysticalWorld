package mysticmods.mysticalworld.items.amethyst;

import com.google.common.collect.Multimap;
import mysticmods.mysticalworld.init.ModModifiers;
import mysticmods.mysticalworld.items.ModifiedArmorItem;
import mysticmods.mysticalworld.items.lead.ILeadItem;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

import net.minecraft.item.Item.Properties;

public class AmethystArmorItem extends ModifiedArmorItem implements ILeadItem {
  public AmethystArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<Attribute, AttributeModifier> map = super.getDefaultAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(ModModifiers.SERENDIPITY.get(), getOrCreateModifier(ModModifiers.SERENDIPITY.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.50f, AttributeModifier.Operation.ADDITION)));
      } else {
        map.put(ModModifiers.SERENDIPITY.get(), getOrCreateModifier(ModModifiers.SERENDIPITY.get(), () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.25f, AttributeModifier.Operation.ADDITION)));
      }

      map.put(Attributes.LUCK, getOrCreateModifier(Attributes.LUCK, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Luck multiplier", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
