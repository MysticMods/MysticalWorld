package epicsquid.mysticalworld.items.amethyst;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.init.ModModifiers;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import epicsquid.mysticalworld.items.lead.ILeadItem;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import noobanidus.libs.noobutil.material.MaterialType;

public class AmethystArmorItem extends ModifiedArmorItem implements ILeadItem {
  public AmethystArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(ModModifiers.SERENDIPITY.getName(), getOrCreateModifier(ModModifiers.SERENDIPITY, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.50f, AttributeModifier.Operation.ADDITION)));
      } else {
        map.put(ModModifiers.SERENDIPITY.getName(), getOrCreateModifier(ModModifiers.SERENDIPITY, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Serendipity addition", 0.25f, AttributeModifier.Operation.ADDITION)));
      }

      map.put(SharedMonsterAttributes.LUCK.getName(), getOrCreateModifier(SharedMonsterAttributes.LUCK, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Luck multiplier", 1f, AttributeModifier.Operation.ADDITION)));
    }

    return map;
  }
}
