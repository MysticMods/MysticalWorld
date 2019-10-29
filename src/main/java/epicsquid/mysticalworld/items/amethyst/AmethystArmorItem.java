package epicsquid.mysticalworld.items.amethyst;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import epicsquid.mysticalworld.items.lead.ILeadItem;
import epicsquid.mysticalworld.materials.MaterialType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;

public class AmethystArmorItem extends ModifiedArmorItem implements ILeadItem {
  public AmethystArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      /*if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(SharedMonsterAttributes.LUCK.getName(), getOrCreateModifier(SharedMonsterAttributes.LUCK, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Luck base", 0.5f, AttributeModifier.Operation.ADDITION)));
      } else {
        map.put(SharedMonsterAttributes.LUCK.getName(), getOrCreateModifier(SharedMonsterAttributes.LUCK, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Luck multiplier", 0.25f, AttributeModifier.Operation.ADDITION)));
      }*/
    }

    return map;
  }
}
