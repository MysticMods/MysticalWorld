package epicsquid.mysticalworld.items.silver;

import com.google.common.collect.Multimap;
import epicsquid.mysticalworld.items.ModifiedArmorItem;
import epicsquid.mysticalworld.materials.MaterialType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;

public class SilverArmorItem extends ModifiedArmorItem implements ISilverItem {
  public SilverArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
    super(materialIn, slot, builder);
  }

  @Override
  public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
    Multimap<String, AttributeModifier> map = super.getAttributeModifiers(equipmentSlot);

    if (this.slot == equipmentSlot) {
      /*if (slot == EquipmentSlotType.CHEST || slot == EquipmentSlotType.LEGS) {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), getOrCreateModifier(SharedMonsterAttributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.02f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      } else {
        map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), getOrCreateModifier(SharedMonsterAttributes.ATTACK_DAMAGE, () -> new AttributeModifier(MaterialType.ARMOR_MODIFIERS[slot.getIndex()], "Attack damage multiplier", 0.06f, AttributeModifier.Operation.MULTIPLY_TOTAL)));
      }*/
    }

    return map;
  }
}
